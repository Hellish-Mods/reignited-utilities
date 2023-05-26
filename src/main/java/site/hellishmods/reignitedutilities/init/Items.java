package site.hellishmods.reignitedutilities.init;

import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import site.hellishmods.reignitedutilities.lib.items.SickleTier;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.lib.items.GPDisplayItem;
import site.hellishmods.reignitedutilities.lib.items.UnstableIngot;

public class Items {
    public static RegistryObject<Item> unstableIngot;

    public static void init() {
        unstableIngot = reignitedutilities.ITEMS.register("unstable_ingot", () -> new UnstableIngot());
        reignitedutilities.ITEMS.register("stable_nugget", () -> new Item(new Item.Properties().tab(reignitedutilities.TAB)));
        reignitedutilities.ITEMS.register("stable_ingot", () -> new Item(new Item.Properties().tab(reignitedutilities.TAB)));

        reignitedutilities.ITEMS.register("glasscutter", () -> new Item(new Item.Properties().tab(reignitedutilities.TAB).stacksTo(1).defaultDurability(250)) {
            @Override
            public ItemStack getContainerItem(ItemStack stack) {
                ItemStack newItem = stack.copy();
                if (newItem.hurt(1, new Random(), null)) return ItemStack.EMPTY;
                else return newItem;
            }
            @Override
            public boolean hasContainerItem(ItemStack stack)
            {
                return true;
            }
        });
        reignitedutilities.ITEMS.register("endershard", () -> new Item(new Item.Properties().tab(reignitedutilities.TAB).stacksTo(8)));

        reignitedutilities.ITEMS.register("wood_sickle", () -> new SwordItem(SickleTier.IRON, 0, 0f, new Item.Properties().tab(reignitedutilities.TAB)));
        reignitedutilities.ITEMS.register("stone_sickle", () -> new SwordItem(SickleTier.IRON, 0, 0f, new Item.Properties().tab(reignitedutilities.TAB)));
        reignitedutilities.ITEMS.register("iron_sickle", () -> new SwordItem(SickleTier.IRON, 0, 0f, new Item.Properties().tab(reignitedutilities.TAB)));
        reignitedutilities.ITEMS.register("gold_sickle", () -> new SwordItem(SickleTier.IRON, 0, 0f, new Item.Properties().tab(reignitedutilities.TAB)));
        reignitedutilities.ITEMS.register("diamond_sickle", () -> new SwordItem(SickleTier.DIAMOND, 0, 0f, new Item.Properties().tab(reignitedutilities.TAB)));
        reignitedutilities.ITEMS.register("netherite_sickle", () -> new SwordItem(SickleTier.NETHERITE, 0, 0f, new Item.Properties().tab(reignitedutilities.TAB)));

        reignitedutilities.ITEMS.register("redstone_crystal", () -> new GPDisplayItem());
        reignitedutilities.ITEMS.register("redstone_gear", () -> new Item(new Item.Properties().tab(reignitedutilities.TAB)));
    }
}
