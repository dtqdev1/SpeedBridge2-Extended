package io.tofpu.speedbridge2.game.config.experience.meta;

import org.bukkit.entity.Player;

import static org.immutables.value.Value.Immutable;

// todo: add support for specifying a fade in and fade out time
@Immutable
public interface Title {
    static Builder builder() {
        return new Builder();
    }

    static Title of(String title, String subtitle) {
        return ImmutableTitle.of(title, subtitle);
    }

    String title();

    String subtitle();

    default void show(Player player) {
        player.sendTitle(title(), subtitle());
    }

    class Builder extends ImmutableTitle.Builder {}
}
