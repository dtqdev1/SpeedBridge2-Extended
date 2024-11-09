package io.tofpu.speedbridge2.game.config.item;

import static org.immutables.value.Value.Immutable;

import io.tofpu.speedbridge2.game.config.item.serializer.ItemMetaOptionsSerializer;
import io.tofpu.speedbridge2.game.config.item.serializer.ItemStackSerializer;
import org.bukkit.inventory.ItemStack;
import space.arim.dazzleconf.annote.ConfSerialisers;
import space.arim.dazzleconf.annote.SubSection;

@ConfSerialisers(value = {ItemStackSerializer.class, ItemMetaOptionsSerializer.class})
@Immutable
public interface GameItemConfiguration {
    static Builder builder() {
        return new Builder();
    }

    static GameItemConfiguration of(Item leaveGame, Item resetGame) {
        return ImmutableGameItemConfiguration.of(leaveGame, resetGame);
    }

    @SubSection
    Item leaveGame();

    @SubSection
    Item resetGame();

    @Immutable
    interface Item {
        static Builder builder() {
            return builder();
        }

        static Item of(ItemStack item, int slot) {
            return ImmutableItem.of(item, slot);
        }

        ItemStack item();

        int slot();

        class Builder extends ImmutableItem.Builder {}
    }

    class Builder extends ImmutableGameItemConfiguration.Builder {}
}
