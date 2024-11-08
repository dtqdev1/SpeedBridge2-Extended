package io.tofpu.speedbridge2.game.config.item.serializer;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.Decomposer;
import space.arim.dazzleconf.serialiser.FlexibleType;
import space.arim.dazzleconf.serialiser.ValueSerialiser;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class ItemStackSerializer implements ValueSerialiser<ItemStack> {

    protected static final String MATERIAL = "material";
    protected static final String AMOUNT = "amount";
    protected static final String DURABILITY = "durability";
    protected static final String META = "meta";

    @Override
    public Class<ItemStack> getTargetClass() {
        return ItemStack.class;
    }

    @Override
    public ItemStack deserialise(FlexibleType flexibleType) throws BadValueException {
        Map<String, Object> map = flexibleType.getMap((flexibleKey, flexibleValue) -> new AbstractMap.SimpleEntry<>(flexibleKey.getString(), flexibleKey.getObject(Object.class)));
        Material material = Material.valueOf(map.get(MATERIAL).toString());
        int amount = Integer.parseInt(map.get(AMOUNT).toString());
        short durability = Short.parseShort(map.get(DURABILITY).toString());
        ItemStack itemStack = new ItemStack(material, amount, durability);
        if (map.containsKey(META)) {
            ItemMetaOptions itemMetaOptions = flexibleType.getObject(ItemMetaOptions.class);
            itemStack = itemMetaOptions.apply(itemStack);
        }
        return itemStack;
    }

    @Override
    public Object serialise(ItemStack value, Decomposer decomposer) {
        Map<String, Object> map = new HashMap<>();
        map.put(MATERIAL, value.getType().name());
        map.put(AMOUNT, value.getAmount());
        map.put(DURABILITY, value.getDurability());
        if (value.hasItemMeta()) {
            map.put(META, decomposer.decompose(ItemMetaOptions.class, new ItemMetaOptions(value.getItemMeta())));
        }
        return map;
    }
}
