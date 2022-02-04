package io.tofpu.speedbridge2.domain.leaderboard.loader;

import com.google.common.cache.CacheLoader;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import io.tofpu.speedbridge2.domain.common.database.wrapper.DatabaseQuery;
import io.tofpu.speedbridge2.domain.leaderboard.meta.BoardRetrieve;
import io.tofpu.speedbridge2.domain.leaderboard.wrapper.BoardPlayer;
import io.tofpu.speedbridge2.domain.player.misc.score.Score;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public final class PersonalBoardLoader extends CacheLoader<UUID, BoardPlayer> implements BoardRetrieve<BoardPlayer> {
    public static final PersonalBoardLoader INSTANCE = new PersonalBoardLoader();
    private static final String GLOBAL_POSITION =
            "SELECT 1 + COUNT(*) AS position, * FROM scores WHERE score < (SELECT score" +
            " " +
            "FROM scores WHERE uid = ?)";

    private PersonalBoardLoader() {}

    @Override
    public @Nullable BoardPlayer load(final @NotNull UUID key) throws Exception {
        return retrieve(key);
    }

    @Override
    public ListenableFuture<BoardPlayer> reload(final @NotNull UUID key, final @NotNull BoardPlayer oldValue) {
        return Futures.immediateFuture(retrieve(key));
    }

    @Override
    public @Nullable BoardPlayer retrieve(final @NotNull UUID key) {
        try (final DatabaseQuery databaseQuery = new DatabaseQuery(GLOBAL_POSITION)) {
            databaseQuery.setString(key.toString());

            final AtomicReference<BoardPlayer> boardPlayer = new AtomicReference<>();
            databaseQuery.executeQuery(resultSet -> {
                final int islandSlot;
                try {
                    islandSlot = resultSet.getInt("island_slot");
                    final double playerScore = resultSet.getDouble("score");
                    final Score score = Score.of(islandSlot, playerScore);

                    boardPlayer.set(new BoardPlayer(resultSet.getInt("position"), key, score));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            return boardPlayer.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
