package io.tofpu.speedbridge2.listener.island;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import io.tofpu.dynamicclass.meta.AutoRegister;
import io.tofpu.speedbridge2.domain.player.object.BridgePlayer;
import io.tofpu.speedbridge2.domain.island.object.extra.GameIsland;
import io.tofpu.speedbridge2.domain.player.object.GamePlayer;
import io.tofpu.speedbridge2.domain.island.plot.IslandPlot;
import io.tofpu.speedbridge2.domain.player.PlayerService;
import io.tofpu.speedbridge2.listener.GameListener;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

@AutoRegister
public final class IslandRegionListener extends GameListener {
    @EventHandler(ignoreCancelled = true)
    private void onPlayerMove(final @NotNull PlayerMoveEvent event) {
        final BridgePlayer bridgePlayer = PlayerService.INSTANCE.get(event.getPlayer()
                .getUniqueId());
        if (bridgePlayer == null ||!bridgePlayer.isPlaying()) {
            return;
        }
        final GamePlayer gamePlayer = bridgePlayer.getGamePlayer();
        final GameIsland gameIsland = gamePlayer.getCurrentGame();
        final IslandPlot islandPlot = gameIsland.getIslandPlot();
        final Region region = islandPlot.region();

        final Location location = event.getTo();
        final Vector vector = new Vector(location.getX(), location.getY(), location.getZ());

        final boolean isInRegion = CuboidRegion.makeCuboid(region).contains(vector);

        if (!isInRegion) {
            gameIsland.resetGame();
        }
    }
}
