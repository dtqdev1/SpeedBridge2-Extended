package io.tofpu.speedbridge2.setup;

import io.tofpu.speedbridge2.schematic.Schematic;

public class SetupInfo {
    private final int slot;
    private final Schematic schematic;

    public SetupInfo(int slot, Schematic schematic) {
        this.slot = slot;
        this.schematic = schematic;
    }

    public int slot() {
        return slot;
    }

    public Schematic schematic() {
        return schematic;
    }
}
