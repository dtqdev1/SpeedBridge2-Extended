package io.tofpu.speedbridge2.lobby;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

// todo: add repository for data persistence
public class LobbyService implements LobbyTeleporter {
    private Location lobby;

    public void lobby(Location lobby) {
        this.lobby = lobby;
    }

    @Override
    public void teleportToLobby(Player player) {
        if (lobby == null) {
            lobby = Bukkit.getWorlds().get(0).getSpawnLocation();
        }
        player.teleport(lobby);
    }
}
