package io.tofpu.speedbridge2.util.listener;

import java.util.function.Function;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A simple interface for registering listeners.
 */
public interface ListenerRegistration {
    static ListenerRegistration create(JavaPlugin plugin) {
        return new Impl(plugin);
    }

    void register(Listener listener);

    void register(Function<JavaPlugin, Listener> listenerFactory);

    void unregister(Listener listener);

    class Impl implements ListenerRegistration {
        private final JavaPlugin plugin;

        public Impl(JavaPlugin plugin) {
            this.plugin = plugin;
        }

        @Override
        public void register(Listener listener) {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        }

        @Override
        public void register(Function<JavaPlugin, Listener> listenerFactory) {
            register(listenerFactory.apply(plugin));
        }

        @Override
        public void unregister(Listener listener) {
            HandlerList.unregisterAll(listener);
        }
    }
}
