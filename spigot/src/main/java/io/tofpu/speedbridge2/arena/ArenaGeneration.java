package io.tofpu.speedbridge2.arena;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import io.tofpu.multiworldedit.ClipboardWrapper;
import io.tofpu.multiworldedit.EditSessionWrapper;
import io.tofpu.multiworldedit.MultiWorldEditAPI;
import io.tofpu.multiworldedit.VectorWrapper;
import io.tofpu.speedbridge2.util.Position;
import java.io.IOException;
import org.bukkit.Material;
import org.bukkit.World;

public class ArenaGeneration {
    private final World world;
    private final Clipboard clipboard;
    private final Position position;

    public ArenaGeneration(World world, Clipboard clipboard, Position position) {
        this.world = world;
        this.clipboard = clipboard;
        this.position = position;
    }

    /**
     * Generates the arena in the world using the provided {@link Clipboard} and {@link Position} data.
     */
    public void generate() {
        final BukkitWorld bukkitWorld = new BukkitWorld(world);

        try (final EditSessionWrapper editSessionWrapper =
                MultiWorldEditAPI.getMultiWorldEdit().create(bukkitWorld, -1)) {
            final EditSession editSession = editSessionWrapper.to();

            final Operation operation = MultiWorldEditAPI.getMultiWorldEdit()
                    .create(clipboard, editSession, bukkitWorld)
                    .to(position.getX(), position.getY(), position.getZ())
                    .ignoreAirBlocks(true)
                    .build();

            Operations.completeLegacy(operation);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (MaxChangedBlocksException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Destroys the arena in the world using the provided {@link Clipboard} and {@link Position} data.
     */
    public void destroy() {
        final ClipboardWrapper clipboardWrapper =
                MultiWorldEditAPI.getMultiWorldEdit().create(clipboard);

        final VectorWrapper minimumPoint = clipboardWrapper.getMinimumPoint();
        final VectorWrapper maximumPoint = clipboardWrapper.getMaximumPoint();

        final int offset = 2;

        final int plotX = position.getX() - offset;
        final int plotY = position.getY() - offset;
        final int plotZ = position.getZ() - offset;

        // resetting the blocks
        for (int x = 0; x < maximumPoint.getX() - minimumPoint.getX() + offset; x++) {
            for (int y = 0; y < maximumPoint.getY() - minimumPoint.getY() + offset; y++) {
                for (int z = 0; z < maximumPoint.getZ() - minimumPoint.getZ() + offset; z++) {
                    final int blockX = plotX + x;
                    final int blockY = plotY + y;
                    final int blockZ = plotZ + z;

                    world.getBlockAt(blockX, blockY, blockZ).setType(Material.AIR);
                }
            }
        }
    }
}
