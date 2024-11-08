package io.tofpu.speedbridge2.game.config.experience.meta;

import com.cryptomorin.xseries.XSound;
import org.bukkit.entity.Player;

import static org.immutables.value.Value.Immutable;

@Immutable
public interface Sound {
    static Builder builder() {
        return new Builder();
    }

    static Sound of(XSound type, float volume, float pitch) {
        return ImmutableSound.of(type, volume, pitch);
    }

    XSound type();

    float volume();

    float pitch();

    default void play(Player player) {
        type().play(player, volume(), pitch());
    }

    class Builder extends ImmutableSound.Builder {}
}
