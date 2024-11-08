package io.tofpu.speedbridge2.game.toolbar.item;

import io.tofpu.speedbridge2.game.GameService;
import io.tofpu.speedbridge2.game.state.GameStateType;
import io.tofpu.toolbar.toolbar.tool.action.ToolAction;
import io.tofpu.toolbar.toolbar.tool.action.ToolActionUtil;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class ResetGameItem extends GameItem {

    protected static final String ID = "resetGame";

    public ResetGameItem(ItemStack item, GameService gameService) {
        super(ID, item, ToolActionUtil.listenFor(PlayerInteractAtEntityEvent.class, handle(gameService)));
    }

    private static ToolAction<PlayerInteractAtEntityEvent> handle(GameService gameService) {
        return (toolbar, event) -> gameService.ifGamePresent(event.getPlayer().getUniqueId(), game -> game.setState(GameStateType.RESET));
    }
}
