package io.tofpu.speedbridge2.game.config.item;

import io.tofpu.speedbridge2.game.config.item.serializer.ItemMetaOptionsSerializer;
import io.tofpu.speedbridge2.game.config.item.serializer.ItemStackSerializer;
import org.bukkit.inventory.ItemStack;
import org.immutables.value.Value;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfSerialisers;
import space.arim.dazzleconf.annote.SubSection;

import static org.immutables.value.Value.*;

@ConfSerialisers(value = {
        ItemStackSerializer.class,
        ItemMetaOptionsSerializer.class
})
@Immutable
public interface GameItemConfiguration {
    @SubSection
    Item leaveGame();
    @SubSection
    Item resetGame();

    class Builder extends ImmutableGameItemConfiguration.Builder {}
    static Builder builder() {
        return new Builder();
    }

    static GameItemConfiguration of(Item leaveGame, Item resetGame) {
        return ImmutableGameItemConfiguration.of(leaveGame, resetGame);
    }

    @Immutable
    interface Item {
        ItemStack item();
        int slot();

        class Builder extends ImmutableItem.Builder {}
        static Builder builder() {
            return builder();
        }

        static Item of(ItemStack item, int slot) {
            return ImmutableItem.of(item, slot);
        }
    }
}
