package io.tofpu.speedbridge2.domain.game;

import io.tofpu.speedbridge2.domain.BridgePlayer;
import io.tofpu.speedbridge2.domain.Island;
import io.tofpu.speedbridge2.domain.schematic.IslandPlot;
import io.tofpu.speedbridge2.domain.schematic.SchematicManager;
import io.tofpu.speedbridge2.util.BridgeUtil;
import io.tofpu.speedbridge2.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class GameIsland {
    private static final String STYLE =
            "<gold>" + MessageUtil.Symbols.WARNING.getSymbol() + "<yellow> ";
    private static final String ISLAND_RESET = STYLE + "The island has been reset!";

    private final Island island;
    private final GamePlayer gamePlayer;
    private final IslandPlot islandPlot;

    public static GameIsland of(final Island island, final GamePlayer gamePlayer) {
        return new GameIsland(island, gamePlayer);
    }

    private GameIsland(final Island island, final GamePlayer gamePlayer) {
        this.island = island;
        this.gamePlayer = gamePlayer;

        // setting the player's queue to true
        this.gamePlayer.startQueue();

        this.islandPlot = SchematicManager.INSTANCE.reservePlot(this);

        // reset the player's queue
        this.gamePlayer.resetQueue();
    }

    public void onJoin() {
        final Player player = gamePlayer.getBridgePlayer().getPlayer();

        player.getInventory().clear();
        player.getInventory().setItem(0, new ItemStack(Material.WOOL,
                64));
        
        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);

        player.setGameMode(GameMode.SURVIVAL);
    }

    public void resetGame() {
        gamePlayer.resetBlocks();
        gamePlayer.resetTimer();

        this.gamePlayer.teleport(islandPlot);

        final Player player = gamePlayer.getBridgePlayer().getPlayer();

        player.getInventory().setItem(0,
                new ItemStack(Material.WOOL,
                64));

        BridgeUtil.sendMessage(player, ISLAND_RESET);
    }

    public void remove() {
        final Player player = gamePlayer.getBridgePlayer().getPlayer();

        // TODO: change this
        player.getInventory().clear();
        player.teleport(Bukkit.getWorld("world").getSpawnLocation());

        // remove the blocks
        gamePlayer.resetBlocks();

        // free the plot
        SchematicManager.INSTANCE.freePlot(this);

        // set the player's game to null, as they're leaving the island
        gamePlayer.setCurrentGame(null);
    }

    public Island getIsland() {
        return island;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public IslandPlot getIslandPlot() {
        return islandPlot;
    }
}
