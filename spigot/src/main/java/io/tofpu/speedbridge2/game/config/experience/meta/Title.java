package io.tofpu.speedbridge2.game.config.experience.meta;

import org.bukkit.entity.Player;

import static org.immutables.value.Value.*;

// todo: add support for specifying a fade in and fade out time
@Immutable
public interface Title {
    String title();
    String subtitle();

    class Builder extends ImmutableTitle.Builder {}
    static Builder builder() {
        return new Builder();
    }

    static Title of(String title, String subtitle) {
        return ImmutableTitle.of(title, subtitle);
    }

    default void show(Player player) {
        player.sendTitle(title(), subtitle());
    }
}
