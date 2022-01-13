package io.tofpu.speedbridge2.plugin;

import io.tofpu.speedbridge2.command.PluginCommand;
import io.tofpu.speedbridge2.database.manager.DatabaseManager;
import io.tofpu.speedbridge2.domain.SchematicGeneration;
import io.tofpu.speedbridge2.domain.service.IslandService;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpeedBridgePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        DatabaseManager.load(this).thenRun(() -> {
            final IslandService service = IslandService.INSTANCE;
            service.load();
        });

        SchematicGeneration.INSTANCE.load();

        getCommand("bridge").setExecutor(new PluginCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        DatabaseManager.shutdown();
    }
}
