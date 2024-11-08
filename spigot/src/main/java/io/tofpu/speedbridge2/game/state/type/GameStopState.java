package io.tofpu.speedbridge2.game.state.type;

import io.tofpu.speedbridge2.game.Game;
import io.tofpu.speedbridge2.game.state.AbstractGameState;
import io.tofpu.speedbridge2.game.state.GameStateType;
import org.bukkit.Location;

import java.util.function.Consumer;

public class GameStopState extends AbstractGameState {
    private final Consumer<Game> onStopConsumer;

    public GameStopState(Game game, Consumer<Game> onStopConsumer) {
        super(game, GameStateType.STOP);
        this.onStopConsumer = onStopConsumer;
    }

    @Override
    public void handle() {
        // todo: we'll need to teleport the player to the lobby
        Location lobbyLocation = null;
        game.gamePlayer().player().teleport(lobbyLocation);

        onStopConsumer.accept(game);
    }
}
