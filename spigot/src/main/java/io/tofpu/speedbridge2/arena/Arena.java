package io.tofpu.speedbridge2.arena;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import io.tofpu.speedbridge2.schematic.Schematic;
import io.tofpu.speedbridge2.util.Position;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Arena {
    private final World world;
    private final Position position;
    private final Schematic schematic;

    private final Vector minPoint;
    private final Vector maxPoint;

    private final ArenaGeneration generation;
    private OccupationState occupationState = OccupationState.FREE;
    private GenerationState generationState = GenerationState.NOT_GENERATED;

    public Arena(World world, Position position, Schematic schematic) {
        this.world = world;
        this.position = position;
        this.schematic = schematic;

        Clipboard clipboard = schematic.clipboard();
        Vector origin = clipboard.getOrigin();
        Region region = clipboard.getRegion();
        this.minPoint = relative(region.getMinimumPoint(), position, origin);
        this.maxPoint = relative(region.getMaximumPoint(), position, origin);

        this.generation = new ArenaGeneration(world, clipboard, position);
    }

    private static Vector relative(Vector minimumPoint, Position position, Vector clipboardOrigin) {
        return minimumPoint.subtract(clipboardOrigin).add(position.getX(), position.getY(), position.getZ());
    }

    public void generate() {
        // avoid generating the same arena twice
        if (has(GenerationState.GENERATED)) {
            return;
        }
        generation.generate();
        // mark the arena as generated to avoid generating it again
        set(GenerationState.GENERATED);
    }

    public void destroy() {
        // avoid destroying the arena twice
        if (!has(GenerationState.GENERATED)) {
            return;
        }
        generation.destroy();
        // mark the arena as destroyed to avoid destroying it again
        set(GenerationState.DESTROYED);
    }

    public void teleport(Player player) {
        player.teleport(new Location(world, position.getX(), position.getY(), position.getZ()));
    }

    public CuboidRegion getRegion() {
        return new CuboidRegion(minPoint, maxPoint);
    }

    public Position getPosition() {
        return position;
    }

    public Schematic getSchematic() {
        return schematic;
    }

    public void set(OccupationState occupationState) {
        this.occupationState = occupationState;
    }

    public void set(GenerationState generationState) {
        this.generationState = generationState;
    }

    public boolean has(GenerationState generationState) {
        return this.generationState == generationState;
    }

    public boolean has(OccupationState occupationState) {
        return this.occupationState == occupationState;
    }

    public World world() {
        return world;
    }

    public enum OccupationState {
        /**
         * The arena is free and can be used.
         */
        FREE,
        /**
         * The arena is occupied and cannot be used.
         */
        OCCUPIED
    }

    public enum GenerationState {
        /**
         * The arena has not been generated yet.
         */
        NOT_GENERATED,
        /**
         * The arena has been generated.
         */
        GENERATED,
        /**
         * The arena has been destroyed.
         */
        DESTROYED
    }
}
