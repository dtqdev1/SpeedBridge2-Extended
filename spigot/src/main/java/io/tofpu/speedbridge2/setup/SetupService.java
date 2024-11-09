package io.tofpu.speedbridge2.setup;

import io.tofpu.speedbridge2.Constants;
import io.tofpu.speedbridge2.arena.Arena;
import io.tofpu.speedbridge2.arena.ArenaManager;
import io.tofpu.speedbridge2.island.Island;
import io.tofpu.speedbridge2.island.IslandService;
import io.tofpu.speedbridge2.schematic.Schematic;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SetupService {
    private final IslandService islandService;
    private final ArenaManager<Integer> arenaManager;

    private final Map<UUID, IslandSetup> playerSetups = new HashMap<>();

    public SetupService(IslandService islandService, World world) {
        this.islandService = islandService;
        this.arenaManager = new ArenaManager<>(world, Constants.ArenaPositioning.SETUP);
    }

    public boolean createSetup(Player player, SetupInfo setupInfo) {
        if (playerSetups.containsKey(player.getUniqueId())) {
            return false;
        }

        // todo: handle the exceptions
        Schematic schematic = setupInfo.schematic();

        UUID playerId = player.getUniqueId();
        Arena arena = arenaManager.generateArena(setupInfo.slot(), schematic);
        if (arena == null) {
            throw new IllegalStateException("Could not create arena!");
        }

        IslandSetup setup = new IslandSetup(this, player, setupInfo.slot(), schematic, arena);
        this.playerSetups.put(playerId, setup);
        setup.start();
        return true;
    }

    public boolean cancelSetup(@NotNull Player player) {
        IslandSetup setup = playerSetups.get(player.getUniqueId());
        if (setup == null) {
            return false;
        }

        cleanUp(setup.slot(), player);
        return true;
    }

    public void finishSetup(IslandSetup setup) {
        cleanUp(setup.slot(),setup.player());

        if (!setup.canBeFinished()) {
            // todo: throw exception here? as it's unexpected
            return;
        }

        Island island = new Island(setup.slot(), setup.schematic(), setup.spawnPoint());
        islandService.registerIsland(island);
    }

    public void cleanUp(int slot, Player player) {
        this.playerSetups.remove(player.getUniqueId());
        arenaManager.destroyArena(slot, () -> teleportPlayerToLobby(player));
    }

    private void teleportPlayerToLobby(Player player) {
        // todo: lobbyLocation, if not available, teleport to world at 0 index default spawn
//        player.teleport(lobbyLocation);
    }
}
