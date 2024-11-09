package io.tofpu.speedbridge2.game.config.arena;

import static org.immutables.value.Value.Immutable;

import space.arim.dazzleconf.annote.ConfComments;

@Immutable
public interface GameArenaConfiguration {
    @ConfComments("The width gap between the arenas")
    int gap();

    static GameArenaConfiguration of(int gap) {
        return ImmutableGameArenaConfiguration.of(gap);
    }

    static Builder newBuilder() {
        return new Builder();
    }

    class Builder extends ImmutableGameArenaConfiguration.Builder {}
}
