package site.hellishmods.reignitedutilities.init;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.RegistryObject;
import site.hellishmods.reignitedutilities.reignitedutilities;

public class Potions {
    public static RegistryObject<Potion> gravity;
    public static RegistryObject<Potion> gravityLong;
    public static RegistryObject<Potion> greekFire;
    public static RegistryObject<Potion> oily;
    public static RegistryObject<Potion> fizzyLifting;
    public static RegistryObject<Potion> purging;

    private static ItemStack toItemstack(Potion p) {
        return PotionUtils.setPotion(Items.POTION.getDefaultInstance(), p);
    }

    public static void init() {
        reignitedutilities.POTIONS.register("doom_potion", () -> new Potion(new EffectInstance(Effects.doom.get(), 60*20)));
        gravity = reignitedutilities.POTIONS.register("gravity_potion", () -> new Potion(new EffectInstance(Effects.gravity.get(), 60*20)));
        gravityLong = reignitedutilities.POTIONS.register("long_gravity_potion", () -> new Potion(new EffectInstance(Effects.gravity.get(), 8*60*20)));
        greekFire = reignitedutilities.POTIONS.register("greek_fire_potion", () -> new Potion(new EffectInstance(Effects.greekFire.get(), 2*60*20)));
        oily = reignitedutilities.POTIONS.register("oily_potion", () -> new Potion());
        fizzyLifting = reignitedutilities.POTIONS.register("fizzy_lifting_potion", () -> new Potion(new EffectInstance(Effects.fizzyLifting.get(), 30*20)));
        purging = reignitedutilities.POTIONS.register("purging_potion", () -> new Potion(new EffectInstance(Effects.purging.get())));
    }
    public static void initRecipes() {
        BrewingRecipeRegistry.addRecipe(Ingredient.of(toItemstack(net.minecraft.potion.Potions.AWKWARD)), Ingredient.of(Items.OBSIDIAN.getDefaultInstance()), toItemstack(gravity.get()));
        BrewingRecipeRegistry.addRecipe(Ingredient.of(toItemstack(gravity.get())), Ingredient.of(Items.REDSTONE.getDefaultInstance()), toItemstack(gravityLong.get()));
        BrewingRecipeRegistry.addRecipe(Ingredient.of(toItemstack(oily.get())), Ingredient.of(Items.LAVA_BUCKET.getDefaultInstance()), toItemstack(greekFire.get()));
        BrewingRecipeRegistry.addRecipe(Ingredient.of(toItemstack(net.minecraft.potion.Potions.AWKWARD)), Ingredient.of(Items.BEETROOT.getDefaultInstance()), toItemstack(oily.get()));
        BrewingRecipeRegistry.addRecipe(Ingredient.of(toItemstack(net.minecraft.potion.Potions.LEAPING)), Ingredient.of(Items.SUGAR.getDefaultInstance()), toItemstack(fizzyLifting.get()));
        BrewingRecipeRegistry.addRecipe(Ingredient.of(toItemstack(net.minecraft.potion.Potions.AWKWARD)), Ingredient.of(Items.ROTTEN_FLESH.getDefaultInstance()), toItemstack(purging.get()));
    }
}
