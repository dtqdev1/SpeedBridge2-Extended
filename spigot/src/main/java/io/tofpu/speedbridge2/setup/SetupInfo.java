package io.tofpu.speedbridge2.setup;

public class SetupInfo {
    private final int slot;
    private final String schematic;

    public SetupInfo(int slot, String schematic) {
        this.slot = slot;
        this.schematic = schematic;
    }

    public int slot() {
        return slot;
    }

    public String schematicName() {
        return schematic;
    }
}
