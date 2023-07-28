package site.hellishmods.reignitedutilities.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import site.hellishmods.reignitedutilities.reignitedutilities;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = reignitedutilities.MOD_ID)
public class Potions {
    public static RegistryObject<Potion> doom;
    public static RegistryObject<Potion> gravity;
    public static RegistryObject<Potion> gravityLong;
    public static RegistryObject<Potion> secondChance;
    public static RegistryObject<Potion> greekFire;
    public static RegistryObject<Potion> oily;
    public static RegistryObject<Potion> fizzyLifting;
    public static RegistryObject<Potion> relapse;
    public static RegistryObject<Potion> love;
    public static RegistryObject<Potion> purging;

    public static ArrayList<RegistryObject<Potion>> potionsWithEffects = new ArrayList<>();

    private static ItemStack toItemstack(Potion p) {
        return PotionUtils.setPotion(Items.POTION.getDefaultInstance(), p);
    }

    public static void init() {
        doom = reignitedutilities.POTIONS.register("doom_potion", () -> new Potion(new EffectInstance(Effects.doom.get(), 60*20)));
        gravity = reignitedutilities.POTIONS.register("gravity_potion", () -> new Potion(new EffectInstance(Effects.gravity.get(), 60*20)));
        gravityLong = reignitedutilities.POTIONS.register("long_gravity_potion", () -> new Potion(new EffectInstance(Effects.gravity.get(), 8*60*20)));
        secondChance = reignitedutilities.POTIONS.register("second_chance_potion", () -> new Potion(new EffectInstance(Effects.secondChance.get(), 2*60*20)));
        greekFire = reignitedutilities.POTIONS.register("greek_fire_potion", () -> new Potion(new EffectInstance(Effects.greekFire.get(), 2*60*20)));
        oily = reignitedutilities.POTIONS.register("oily_potion", () -> new Potion());
        fizzyLifting = reignitedutilities.POTIONS.register("fizzy_lifting_potion", () -> new Potion(new EffectInstance(Effects.fizzyLifting.get(), 30*20)));
        relapse = reignitedutilities.POTIONS.register("relapse_potion", () -> new Potion(new EffectInstance(Effects.relapse.get(), 8*60*20)));
        love = reignitedutilities.POTIONS.register("love_potion", () -> new Potion(new EffectInstance(Effects.love.get())));
        purging = reignitedutilities.POTIONS.register("purging_potion", () -> new Potion(new EffectInstance(Effects.purging.get())));

        potionsWithEffects.addAll(Arrays.asList(
            doom, gravity, gravityLong, secondChance, greekFire, fizzyLifting, relapse, love, purging
        ));
    }

    @SubscribeEvent
    public static void initRecipes(WorldEvent.Load e) {
        BrewingRecipeRegistry.addRecipe(Ingredient.of(toItemstack(net.minecraft.potion.Potions.AWKWARD)), Ingredient.of(Items.OBSIDIAN.getDefaultInstance()), toItemstack(gravity.get()));
        BrewingRecipeRegistry.addRecipe(Ingredient.of(toItemstack(gravity.get())), Ingredient.of(Items.REDSTONE.getDefaultInstance()), toItemstack(gravityLong.get()));
        BrewingRecipeRegistry.addRecipe(Ingredient.of(toItemstack(net.minecraft.potion.Potions.STRONG_HEALING)), Ingredient.of(Items.ENCHANTED_GOLDEN_APPLE.getDefaultInstance()), toItemstack(secondChance.get()));
        BrewingRecipeRegistry.addRecipe(Ingredient.of(toItemstack(oily.get())), Ingredient.of(Items.LAVA_BUCKET.getDefaultInstance()), toItemstack(greekFire.get()));
        BrewingRecipeRegistry.addRecipe(Ingredient.of(toItemstack(net.minecraft.potion.Potions.AWKWARD)), Ingredient.of(Items.BEETROOT.getDefaultInstance()), toItemstack(oily.get()));
        BrewingRecipeRegistry.addRecipe(Ingredient.of(toItemstack(net.minecraft.potion.Potions.LEAPING)), Ingredient.of(Items.SUGAR.getDefaultInstance()), toItemstack(fizzyLifting.get()));
        BrewingRecipeRegistry.addRecipe(Ingredient.of(toItemstack(net.minecraft.potion.Potions.AWKWARD)), Ingredient.of(Items.JACK_O_LANTERN.getDefaultInstance()), toItemstack(relapse.get()));
        BrewingRecipeRegistry.addRecipe(Ingredient.of(toItemstack(net.minecraft.potion.Potions.AWKWARD)), Ingredient.of(ItemTags.getAllTags().getTag(new ResourceLocation(reignitedutilities.MOD_ID, "love_potion_flowers"))), toItemstack(love.get()));
        BrewingRecipeRegistry.addRecipe(Ingredient.of(toItemstack(net.minecraft.potion.Potions.AWKWARD)), Ingredient.of(Items.ROTTEN_FLESH.getDefaultInstance()), toItemstack(purging.get()));
    }
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void initTooltips(ItemTooltipEvent e) {
        Potion potion = PotionUtils.getPotion(e.getItemStack());
        if (potion!=net.minecraft.potion.Potions.EMPTY && potionsWithEffects.stream().map(p -> p.get()).collect(Collectors.toList()).contains(potion)) {
            e.getToolTip().add(new TranslationTextComponent("potion_tooltip."+reignitedutilities.MOD_ID+"."+potion.getEffects().get(0).getEffect().getRegistryName().getPath()).withStyle(TextFormatting.GRAY));
        }
    }
}
