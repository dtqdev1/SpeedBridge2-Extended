package io.tofpu.speedbridge2.schematic;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import io.tofpu.multiworldedit.ClipboardWrapper;
import java.io.File;

public class Schematic {
    private final String name;
    private final File file;
    private final ClipboardWrapper clipboardWrapper;

    public Schematic(String name, File file, ClipboardWrapper clipboardWrapper) {
        this.name = name;
        this.file = file;
        this.clipboardWrapper = clipboardWrapper;
    }

    public String name() {
        return name;
    }

    public File file() {
        return file;
    }

    public Clipboard clipboard() {
        return clipboardWrapper.to();
    }
}
