package io.tofpu.speedbridge2.game.state.type;

import io.tofpu.speedbridge2.game.Game;
import io.tofpu.speedbridge2.game.GamePlayer;
import io.tofpu.speedbridge2.game.state.AbstractGameState;
import io.tofpu.speedbridge2.game.state.GameStateType;
import org.bukkit.Location;
import org.bukkit.Material;

public class GameResetState extends AbstractGameState {

    public GameResetState(Game game) {
        super(game, GameStateType.RESET);
    }

    @Override
    public void handle() {
        GamePlayer gamePlayer = game.gamePlayer();
        removePlacedBlocks(gamePlayer);

        // apply the reset experience
        game.experienceConfiguration().reset().apply(gamePlayer.player());
        game.setState(GameStateType.START);
    }

    private static void removePlacedBlocks(GamePlayer gamePlayer) {
        for (Location placedBlock : gamePlayer.placedBlocks()) {
            placedBlock.getBlock().setType(Material.AIR);
        }
        gamePlayer.placedBlocks().clear();
    }
}
