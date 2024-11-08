package io.tofpu.speedbridge2.game.toolbar.item;

import io.tofpu.speedbridge2.game.Game;
import io.tofpu.toolbar.toolbar.tool.Tool;
import io.tofpu.toolbar.toolbar.tool.action.ToolAction;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class GameItem extends Tool {
    public GameItem(String id, ItemStack item, ToolAction<? extends Event> action) {
        super(id, item, action);
    }

    public GameItem(String id, ItemStack item) {
        super(id, item);
    }

    protected void ifGamePresent(Consumer<Game> consumer) {

    }
}
