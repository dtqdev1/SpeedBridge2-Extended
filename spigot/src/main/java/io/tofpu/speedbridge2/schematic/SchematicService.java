package io.tofpu.speedbridge2.schematic;

import io.tofpu.multiworldedit.ClipboardWrapper;
import io.tofpu.speedbridge2.schematic.clipboard.ClipboardResolver;

import java.io.File;

public class SchematicService {
    private final File schematicDirectory;
    private final SchematicFileResolver fileResolver;
    private final ClipboardResolver clipboardResolver;

    public SchematicService(File schematicDirectory) {
        this.schematicDirectory = schematicDirectory;
        this.fileResolver = new SchematicFileResolver();
        this.clipboardResolver = new ClipboardResolver();
    }

    public Schematic resolveSchematic(String name) {
        File file = resolveSchematicFile(name);
        if (file == null) {
            // Todo: throw custom exception?
            throw new IllegalArgumentException("Schematic " + name + " does not exist!");
        }

        ClipboardWrapper clipboardWrapper = clipboardResolver.resolveClipboard(file);
        if (clipboardWrapper == null) {
            // Todo: throw custom exception?
            throw new IllegalArgumentException("Schematic " + name + " is not a valid schematic!");
        }
        return new Schematic(name, file, clipboardWrapper);
    }

    private File resolveSchematicFile(String name) {
        return fileResolver.resolveSchematicFile(schematicDirectory, name);
    }
}
