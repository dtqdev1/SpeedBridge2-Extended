package io.tofpu.speedbridge2.domain.island.object.extra;

import io.tofpu.speedbridge2.domain.common.Message;
import io.tofpu.speedbridge2.domain.common.util.BridgeUtil;
import io.tofpu.speedbridge2.domain.island.object.Island;
import io.tofpu.speedbridge2.domain.player.object.BridgePlayer;
import io.tofpu.speedbridge2.domain.player.object.GamePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractMap;
import java.util.Map;

public class NullIsland extends Island {
    public NullIsland() {
        super(-1, "null");
    }

    @Override
    public Map.Entry<GamePlayer, GameIsland> generateGame(final BridgePlayer player) {
        return new AbstractMap.SimpleImmutableEntry<>(null, null);
    }

    @Override
    public void leaveGame(final BridgePlayer bridgePlayer) {
        BridgeUtil.sendMessage(bridgePlayer.getPlayer(),
                Message.INSTANCE.NOT_IN_A_ISLAND);
    }

    @Override
    public GameIsland findGameByPlayer(final GamePlayer gamePlayer) {
        return null;
    }

    @Override
    public void setCategory(final String anotherCategory) {}

    @Override
    public boolean selectSchematic(final @NotNull String schematicName) {
        return false;
    }
}
