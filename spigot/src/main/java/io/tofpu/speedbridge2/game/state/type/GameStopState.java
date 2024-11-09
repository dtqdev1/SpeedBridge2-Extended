package io.tofpu.speedbridge2.game.state.type;

import io.tofpu.speedbridge2.game.Game;
import io.tofpu.speedbridge2.game.state.AbstractGameState;
import io.tofpu.speedbridge2.game.state.GameStateType;
import io.tofpu.speedbridge2.game.toolbar.GameEquipmentHandler;
import java.util.function.Consumer;
import org.bukkit.Location;

public class GameStopState extends AbstractGameState {
    private final Consumer<Game> onStopConsumer;
    private final GameEquipmentHandler equipmentHandler;

    public GameStopState(Game game, Consumer<Game> onStopConsumer, GameEquipmentHandler equipmentHandler) {
        super(game, GameStateType.STOP);
        this.onStopConsumer = onStopConsumer;
        this.equipmentHandler = equipmentHandler;
    }

    @Override
    public void handle() {
        equipmentHandler.unequip(game.gamePlayer().player());

        // todo: we'll need to teleport the player to the lobby
        Location lobbyLocation = null;
        game.gamePlayer().player().teleport(lobbyLocation);

        onStopConsumer.accept(game);
    }
}
