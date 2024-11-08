package io.tofpu.speedbridge2.util.toberemoved.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public interface ItemMetaConfiguration {
    String displayName();
    List<String> lore();

    default ItemStack apply(ItemStack item) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName());
        itemMeta.setLore(lore());
        item.setItemMeta(itemMeta);
        return item;
    }
}
