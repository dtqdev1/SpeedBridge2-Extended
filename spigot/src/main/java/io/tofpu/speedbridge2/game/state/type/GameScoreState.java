package io.tofpu.speedbridge2.game.state.type;

import io.tofpu.speedbridge2.game.Game;
import io.tofpu.speedbridge2.game.GamePlayer;
import io.tofpu.speedbridge2.game.state.AbstractGameState;
import io.tofpu.speedbridge2.game.state.GameStateType;

public class GameScoreState extends AbstractGameState {
    public GameScoreState(Game game) {
        super(game, GameStateType.SCORE);
    }

    @Override
    public void handle() {
        GamePlayer gamePlayer = game.gamePlayer();

        long elapsedTimerInMillis = game.gamePlayer().elapsedTimerInMillis();
        game.gamePlayer().clearTimer();

        // todo: format the score, register it, increment the total wins, etc.

        game.experienceConfiguration().score().apply(gamePlayer.player());
        game.setState(GameStateType.RESET);
    }
}
