package io.tofpu.speedbridge2.game;

import io.tofpu.speedbridge2.arena.Arena;
import io.tofpu.speedbridge2.arena.ArenaManager;
import io.tofpu.speedbridge2.game.config.GameConfigManager;
import io.tofpu.speedbridge2.game.config.GameConfiguration;
import io.tofpu.speedbridge2.game.config.item.GameItemConfiguration;
import io.tofpu.speedbridge2.game.listener.GameListener;
import io.tofpu.speedbridge2.game.state.GameStateProvider;
import io.tofpu.speedbridge2.game.state.GameStateType;
import io.tofpu.speedbridge2.game.toolbar.GameToolbar;
import io.tofpu.speedbridge2.game.toolbar.item.GameItem;
import io.tofpu.speedbridge2.game.toolbar.item.LeaveGameItem;
import io.tofpu.speedbridge2.game.toolbar.item.ResetGameItem;
import io.tofpu.speedbridge2.island.Island;
import io.tofpu.speedbridge2.util.listener.ListenerRegistration;
import io.tofpu.toolbar.ToolbarAPI;
import io.tofpu.toolbar.toolbar.ToolWithSlot;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import static io.tofpu.speedbridge2.game.toolbar.GameToolbarHelper.toolItemMapper;

public class GameService {
    private final ArenaManager<Integer> arenaManager;
    private final ToolbarAPI toolbarAPI;
    private final GameConfigManager gameConfigManager;
    private final ListenerRegistration listenerRegistration;
    private final Map<UUID, Game> gameMap = new HashMap<>();

    public GameService(ArenaManager<Integer> arenaManager, ToolbarAPI toolbarAPI, GameConfigManager gameConfigManager, ListenerRegistration listenerRegistration) {
        this.arenaManager = arenaManager;
        this.toolbarAPI = toolbarAPI;
        this.gameConfigManager = gameConfigManager;
        this.listenerRegistration = listenerRegistration;
    }

    public void enable() {
        GameConfiguration configData = gameConfigManager.getConfigData();
        GameItemConfiguration itemConfig = configData.item();
        //noinspection unchecked
        ToolWithSlot<GameItem>[] gameItems = new ToolWithSlot[]{toolItemMapper(itemConfig.resetGame(), item -> new ResetGameItem(item.item(), this)), toolItemMapper(itemConfig.leaveGame(), item -> new LeaveGameItem(item.item(), this))};
        this.toolbarAPI.registerToolbar(new GameToolbar("game", gameItems));

        listenerRegistration.register(new GameListener(this));
    }

    public boolean startGame(Player player, Island island) {
        UUID playerId = player.getUniqueId();
        // if the player is already in a game
        if (isPlaying(playerId)) {
            return false;
        }

        Arena arena = arenaManager.generateArena(island.slot(), island.schematic());
        Game game = new Game(player, island, arena, gameConfigManager.getConfigData().experience(), new GameStateProvider(this::releaseGame, listenerRegistration));
        gameMap.put(playerId, game);

        game.setState(GameStateType.START);

        return true;
    }

    public void releaseGame(Player player) {
        ifGamePresent(player.getUniqueId(), this::releaseGame);
    }

    private void releaseGame(Game game) {
        game.stateProvider().unregisterAllListeners();

        UUID playerId = game.gamePlayer().player().getUniqueId();
        gameMap.remove(playerId);
        arenaManager.releaseArena(game.island().slot());
    }

    public boolean stopGame(Player player) {
        Game game = gameMap.remove(player.getUniqueId());
        if (game == null) {
            return false;
        }
        game.setState(GameStateType.STOP);
        return true;
    }

    public void ifGamePresent(UUID playerId, Consumer<Game> consumer) {
        Game game = gameMap.get(playerId);
        if (game != null) {
            consumer.accept(game);
        }
    }

    public void resetGame(Player player) {

    }

    public Game getGame(UUID playerId) {
        return gameMap.get(playerId);
    }

    public boolean isPlaying(UUID playerId) {
        return gameMap.containsKey(playerId);
    }
}
