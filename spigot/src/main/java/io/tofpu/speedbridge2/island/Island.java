package io.tofpu.speedbridge2.island;

import io.tofpu.speedbridge2.schematic.Schematic;
import org.bukkit.Location;

public class Island {
    private final int slot;
    private final Schematic schematic;
    private final Location absoluteLocation;

    public Island(int slot, Schematic schematic, Location absoluteLocation) {
        this.slot = slot;
        this.schematic = schematic;
        this.absoluteLocation = absoluteLocation;
    }

    public int slot() {
        return slot;
    }

    public Schematic schematic() {
        return schematic;
    }

    public Location location() {
        return absoluteLocation;
    }
}
