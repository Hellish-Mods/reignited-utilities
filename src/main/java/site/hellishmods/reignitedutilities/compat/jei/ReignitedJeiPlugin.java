package site.hellishmods.reignitedutilities.compat.jei;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.RegistryObject;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.init.Items;

@JeiPlugin
public class ReignitedJeiPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(reignitedutilities.MOD_ID, "jei_plugin");
    }

    private void registerInfo(IRecipeRegistration r) {
        ArrayList<RegistryObject<Item>> itemsWithDesc = Lists.newArrayList(
            Items.sunCrystal
        );
        itemsWithDesc.forEach(i -> {
            r.addIngredientInfo(i.get().getDefaultInstance(), VanillaTypes.ITEM, new TranslationTextComponent(i.get().getDescriptionId()+".description"));
        });
    }
    
    @Override
    public void registerRecipes(IRecipeRegistration r) {
        registerInfo(r);
    }
    @Override
    public void registerItemSubtypes(ISubtypeRegistration r) {
        r.registerSubtypeInterpreter(Items.sunCrystal.get(), (stack, context) -> {
            return (stack.getDamageValue()==0 || stack.getDamageValue()==stack.getMaxDamage()) ? Integer.toString(stack.getDamageValue()) : "";
        });
    }
    @Override
    public void onRuntimeAvailable(IJeiRuntime r) {
        ItemStack emptySunCrystal = new ItemStack(Items.sunCrystal.get());
        emptySunCrystal.setDamageValue(emptySunCrystal.getMaxDamage());
        r.getIngredientManager().addIngredientsAtRuntime(VanillaTypes.ITEM, Lists.newArrayList(emptySunCrystal));
	}
}
