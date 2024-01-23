package com.github.tofpu.speedbridge2.common.setup.listener;

import com.github.tofpu.speedbridge2.common.gameextra.land.PlayerLandReserver;
import com.github.tofpu.speedbridge2.common.island.IslandService;
import com.github.tofpu.speedbridge2.common.lobby.LobbyService;
import com.github.tofpu.speedbridge2.common.setup.IslandSetupData;
import com.github.tofpu.speedbridge2.common.setup.IslandSetupPlayer;
import com.github.tofpu.speedbridge2.common.setup.event.StopIslandSetupEvent;
import com.github.tofpu.speedbridge2.event.Listener;
import com.github.tofpu.speedbridge2.event.dispatcher.EventListener;
import com.github.tofpu.speedbridge2.event.dispatcher.ListeningState;

public class IslandSetupListener implements Listener {

    private final IslandService islandService;
    private final LobbyService lobbyService;
    private final PlayerLandReserver landReserver;

    public IslandSetupListener(IslandService islandService, LobbyService lobbyService, PlayerLandReserver landReserver) {
        this.islandService = islandService;
        this.lobbyService = lobbyService;
        this.landReserver = landReserver;
    }

    @EventListener(state = ListeningState.MONITORING, ignoreCancelled = true)
    public void on(final StopIslandSetupEvent event) {
        IslandSetupData data = event.islandSetup().data();
        if (data.origin() == null) return;
        try {
            islandService.register(data.slot(), data.origin(), data.schematicName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        IslandSetupPlayer setupPlayer = data.player();
        landReserver.releaseSpot(setupPlayer.id());

        setupPlayer.player().teleport(lobbyService.position());
    }
}
