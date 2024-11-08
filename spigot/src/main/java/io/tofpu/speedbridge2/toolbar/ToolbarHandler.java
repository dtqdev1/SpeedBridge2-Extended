package io.tofpu.speedbridge2.toolbar;

import io.tofpu.toolbar.ToolbarAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class ToolbarHandler {
    private final ToolbarAPI toolbarAPI;

    public ToolbarHandler(JavaPlugin plugin) {
        this.toolbarAPI = new ToolbarAPI(plugin);
    }

    public void enable() {
        this.toolbarAPI.enable();
    }

    public void disable() {
        this.toolbarAPI.disable();
    }

    public ToolbarAPI toolbarAPI() {
        return toolbarAPI;
    }
}
