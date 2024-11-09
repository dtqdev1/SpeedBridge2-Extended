package io.tofpu.speedbridge2.game.toolbar;

import io.tofpu.speedbridge2.game.config.item.GameItemConfiguration;
import io.tofpu.speedbridge2.game.toolbar.item.GameItem;
import io.tofpu.toolbar.toolbar.ItemSlot;
import io.tofpu.toolbar.toolbar.ToolWithSlot;
import java.util.function.Function;

public class GameToolbarHelper {
    public static ToolWithSlot<GameItem> toolItemMapper(
            GameItemConfiguration.Item config, Function<GameItemConfiguration.Item, GameItem> itemFunction) {
        ItemSlot slot = ItemSlot.atIndex(config.slot());
        GameItem gameItem = itemFunction.apply(config);
        return new ToolWithSlot<>(gameItem, slot);
    }
}
