package io.tofpu.speedbridge2.game;

import io.tofpu.speedbridge2.arena.Arena;
import io.tofpu.speedbridge2.island.Island;
import io.tofpu.speedbridge2.util.Position;
import org.bukkit.entity.Player;

// todo: handle game logic here
//  - add toolbar items
//  - add game listeners
public class Game {
    private final Player player;
    private final Island island;
    private final Arena arena;

    public Game(Player player, Island island, Arena arena) {
        this.player = player;
        this.island = island;
        this.arena = arena;
    }

    public void start() {
        Position gamePosition = arena.getPosition()
                .add(island.location());
        player.teleport(gamePosition.toLocation(arena.world()));
    }

    public void destroy() {
        arena.destroy();
    }

    public Player player() {
        return player;
    }

    public Island island() {
        return island;
    }

    public Arena arena() {
        return arena;
    }
}
