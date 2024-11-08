package io.tofpu.speedbridge2.game.state;

import io.tofpu.speedbridge2.game.Game;
import io.tofpu.speedbridge2.game.state.type.GameResetState;
import io.tofpu.speedbridge2.game.state.type.GameScoreState;
import io.tofpu.speedbridge2.game.state.type.GameStartState;
import io.tofpu.speedbridge2.game.state.type.GameStopState;
import io.tofpu.speedbridge2.game.toolbar.GameEquipmentHandler;
import io.tofpu.speedbridge2.util.listener.ListenerRegistration;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class GameStateProvider {
    private final Consumer<Game> onStopConsumer;
    private final ListenerRegistration listenerRegistration;
    private final Map<GameStateType, AbstractGameState> stateCache = new HashMap<>();
    private final GameEquipmentHandler equipmentHandler;

    public GameStateProvider(Consumer<Game> onStopConsumer, ListenerRegistration listenerRegistration, GameEquipmentHandler equipmentHandler) {
        this.onStopConsumer = onStopConsumer;
        this.listenerRegistration = listenerRegistration;
        this.equipmentHandler = equipmentHandler;
    }

    public AbstractGameState provideState(GameStateType state, Game game) {
        return stateCache.computeIfAbsent(state, s -> createState(s, game));
    }

    private AbstractGameState createState(GameStateType state, Game game) {
        AbstractGameState stateInstance = null;
        switch (state) {
            case START:
                stateInstance = new GameStartState(game, equipmentHandler);
                break;
            case RESET:
                stateInstance= new GameResetState(game);
                break;
            case SCORE:
                stateInstance = new GameScoreState(game);
                break;
            case STOP:
                stateInstance = new GameStopState(game, onStopConsumer, equipmentHandler);
                break;
        }

        if (stateInstance == null) {
            throw new IllegalArgumentException("Unknown game state: " + state);
        }

        return registerListeners(stateInstance);
    }

    public void unregisterAllListeners() {
        for (AbstractGameState state : stateCache.values()) {
            state.unregisterListeners(listenerRegistration);
        }
    }

    public AbstractGameState registerListeners(AbstractGameState state) {
        state.registerListeners(listenerRegistration);
        return state;
    }
}
