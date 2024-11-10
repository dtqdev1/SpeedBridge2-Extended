package io.tofpu.speedbridge2.lobby;

import org.bukkit.entity.Player;

/**
 * This interface is responsible for teleporting players to the lobby.
 */
public interface LobbyTeleporter {
    /**
     * Teleports the given player to the lobby. If the lobby has not been set, the spawn location of the world (first world in the server)
     * will be used.
     *
     * @param player the player to teleport
     */
    void teleportToLobby(Player player);
}
