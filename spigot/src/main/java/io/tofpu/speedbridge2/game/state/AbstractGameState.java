package io.tofpu.speedbridge2.game.state;

import io.tofpu.speedbridge2.game.Game;
import io.tofpu.speedbridge2.util.listener.ListenerRegistration;

// todo: add compatibility state method. That way, a state can stop the transition to another state
//  if deemed necessary
public abstract class AbstractGameState {
    protected final Game game;
    private final GameStateType type;

    public AbstractGameState(Game game, GameStateType type) {
        this.game = game;
        this.type = type;
    }

    public static AbstractGameState identity(Game game) {
        return new Nothing(game);
    }

    public abstract void handle();
    public void registerListeners(ListenerRegistration listenerRegistration) {}
    public void unregisterListeners(ListenerRegistration listenerRegistration) {}

    public GameStateType type() {
        return type;
    }

    static class Nothing extends AbstractGameState {
        public Nothing(Game game) {
            super(game, GameStateType.INITIALIZE);
        }

        @Override
        public void handle() {

        }
    }
}
