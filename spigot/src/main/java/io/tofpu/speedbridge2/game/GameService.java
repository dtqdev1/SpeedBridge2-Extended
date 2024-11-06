package io.tofpu.speedbridge2.game;

import io.tofpu.speedbridge2.arena.Arena;
import io.tofpu.speedbridge2.arena.ArenaManager;
import io.tofpu.speedbridge2.island.Island;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameService {
    private final ArenaManager<Integer> arenaManager;
    private final Map<UUID, Game> gameMap = new HashMap<>();

    public GameService(ArenaManager<Integer> arenaManager) {
        this.arenaManager = arenaManager;
    }

    public boolean startGame(Player player, Island island) {
        UUID playerId = player.getUniqueId();
        // if the player is already in a game
        if (gameMap.containsKey(playerId)) {
            return false;
        }

        Arena arena = arenaManager.generateArena(island.slot(), island.schematic());
        Game game = new Game(player, island, arena);
        gameMap.put(playerId, game);

        game.start();
        return true;
    }

    public boolean stopGame(Player player) {
        Game game = gameMap.remove(player.getUniqueId());
        if (game == null) {
            return false;
        }

        game.destroy();
        arenaManager.releaseArena(game.island().slot());
        return true;
    }
}
