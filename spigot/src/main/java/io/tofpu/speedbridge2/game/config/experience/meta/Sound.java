package io.tofpu.speedbridge2.game.config.experience.meta;

import static org.immutables.value.Value.Immutable;

import com.cryptomorin.xseries.XSound;
import org.bukkit.entity.Player;

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
