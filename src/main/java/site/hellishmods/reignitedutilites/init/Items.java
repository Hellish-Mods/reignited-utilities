package site.hellishmods.reignitedutilites.init;

import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import site.hellishmods.reignitedutilites.reignitedutilites;
import site.hellishmods.reignitedutilites.lib.items.GPDisplayItem;
import site.hellishmods.reignitedutilites.lib.items.UnstableIngot;

public class Items {
    public static RegistryObject<Item> unstableIngot;

    public static void init() {
        unstableIngot = reignitedutilites.ITEMS.register("unstable_ingot", () -> new UnstableIngot());
        reignitedutilites.ITEMS.register("stable_nugget", () -> new Item(new Item.Properties().tab(reignitedutilites.TAB)));
        reignitedutilites.ITEMS.register("stable_ingot", () -> new Item(new Item.Properties().tab(reignitedutilites.TAB)));

        reignitedutilites.ITEMS.register("glasscutter", () -> new Item(new Item.Properties().tab(reignitedutilites.TAB).stacksTo(1).defaultDurability(250)) {
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
        reignitedutilites.ITEMS.register("endershard", () -> new Item(new Item.Properties().tab(reignitedutilites.TAB).stacksTo(8)));

        reignitedutilites.ITEMS.register("redstone_crystal", () -> new GPDisplayItem());
        reignitedutilites.ITEMS.register("redstone_gear", () -> new Item(new Item.Properties().tab(reignitedutilites.TAB)));
    }
}
