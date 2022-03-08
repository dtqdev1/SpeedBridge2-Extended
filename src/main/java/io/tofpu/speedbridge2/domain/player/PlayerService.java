package io.tofpu.speedbridge2.domain.player;

import io.tofpu.speedbridge2.domain.player.object.BridgePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public final class PlayerService {
    public static final @NotNull PlayerService INSTANCE = new PlayerService();

    private final @NotNull PlayerHandler playerHandler;

    public PlayerService() {
        this.playerHandler = new PlayerHandler();
    }

    public @Nullable BridgePlayer get(final @NotNull UUID uuid) {
        return this.playerHandler.get(uuid);
    }

    public @Nullable BridgePlayer remove(final @NotNull UUID uniqueId) {
        return playerHandler.remove(uniqueId);
    }

    public @Nullable BridgePlayer internalRefresh(final @NotNull Player player) {
        return playerHandler.internalRefresh(player.getName(), player.getUniqueId());
    }

    public @Nullable BridgePlayer invalidate(final @NotNull Player player) {
        return playerHandler.invalidate(player.getUniqueId());
    }

    public CompletableFuture<BridgePlayer> load(final UUID uid) {
        return playerHandler.load(uid);
    }

    public Collection<BridgePlayer> getBridgePlayers() {
        return playerHandler.getBridgePlayers();
    }

    public void reset(final UUID uuid) {
        playerHandler.reset(uuid);
    }
}
