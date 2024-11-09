package io.tofpu.speedbridge2.schematic.clipboard;

import io.tofpu.multiworldedit.ClipboardWrapper;
import io.tofpu.multiworldedit.MultiWorldEditAPI;
import java.io.File;

public class ClipboardResolver {
    public ClipboardWrapper resolveClipboard(File schematicFile) {
        return MultiWorldEditAPI.getMultiWorldEdit().read(schematicFile);
    }
}
