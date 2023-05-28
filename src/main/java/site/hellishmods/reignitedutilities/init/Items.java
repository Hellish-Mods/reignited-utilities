package site.hellishmods.reignitedutilities.init;

import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import site.hellishmods.reignitedutilities.lib.items.SickleTier;
import site.hellishmods.reignitedutilities.lib.items.SunCrystalItem;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.lib.items.GPDisplayItem;
import site.hellishmods.reignitedutilities.lib.items.SickleItem;
import site.hellishmods.reignitedutilities.lib.items.UnstableIngot;

public class Items {
    public static RegistryObject<Item> unstableIngot;
    public static RegistryObject<Item> sunCrystal;

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

        reignitedutilities.ITEMS.register("wood_sickle", () -> new SickleItem(SickleTier.WOOD, 1));
        reignitedutilities.ITEMS.register("stone_sickle", () -> new SickleItem(SickleTier.STONE, 1.1f));
        reignitedutilities.ITEMS.register("iron_sickle", () -> new SickleItem(SickleTier.IRON, 1.1f));
        reignitedutilities.ITEMS.register("gold_sickle", () -> new SickleItem(SickleTier.GOLD, 1.3f));
        reignitedutilities.ITEMS.register("diamond_sickle", () -> new SickleItem(SickleTier.DIAMOND, 1.2f));
        reignitedutilities.ITEMS.register("netherite_sickle", () -> new SickleItem(SickleTier.NETHERITE, 1.3f));

        sunCrystal = reignitedutilities.ITEMS.register("sun_crystal", () -> new SunCrystalItem());

        reignitedutilities.ITEMS.register("redstone_crystal", () -> new GPDisplayItem());
        reignitedutilities.ITEMS.register("redstone_gear", () -> new Item(new Item.Properties().tab(reignitedutilities.TAB)));
    }
}
