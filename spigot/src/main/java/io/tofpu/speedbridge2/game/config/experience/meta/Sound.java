package io.tofpu.speedbridge2.game.config.experience.meta;

import com.cryptomorin.xseries.XSound;
import org.bukkit.entity.Player;

import static org.immutables.value.Value.*;

@Immutable
public interface Sound {
    XSound type();
    float volume();
    float pitch();

    class Builder extends ImmutableSound.Builder {}
    static Builder builder() {
        return new Builder();
    }

    static Sound of(XSound type, float volume, float pitch) {
        return ImmutableSound.of(type, volume, pitch);
    }

    default void play(Player player) {
        type().play(player, volume(), pitch());
    }
}
