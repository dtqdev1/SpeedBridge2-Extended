package io.tofpu.speedbridge2.util.toberemoved.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.SubSection;

public interface ItemStackConfiguration {
    Material material();
    @ConfDefault.DefaultInteger(1)
    int amount();
    short durability();

    @SubSection
    ItemMetaConfiguration meta();

    default ItemStack create() {
        ItemStack item = new ItemStack(material(), amount());
        item.setDurability(durability());
        return meta().apply(item);
    }
}
