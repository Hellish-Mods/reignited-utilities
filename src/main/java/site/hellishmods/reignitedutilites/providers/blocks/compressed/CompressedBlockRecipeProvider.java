package site.hellishmods.reignitedutilites.providers.blocks.compressed;

import java.util.function.Consumer;

import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.TickTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import site.hellishmods.reignitedutilites.reignitedutilites;
import site.hellishmods.reignitedutilites.init.Blocks;
import site.hellishmods.reignitedutilites.lib.blocks.CompressedBlock;

public class CompressedBlockRecipeProvider extends RecipeProvider {
    public CompressedBlockRecipeProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        Blocks.CompressedBlocks.forEach(obj -> {
            CompressedBlock b = obj.get();
            ShapedRecipeBuilder
                .shaped(b)
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', (b.compTier>1 ? ForgeRegistries.ITEMS.getValue(new ResourceLocation(reignitedutilites.MOD_ID, Integer.toString(b.compTier-1)+"_compressed_"+b.mat)) : ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft", b.mat))))
                .unlockedBy("tick", new TickTrigger().createInstance(null, EntityPredicate.AndPredicate.ANY, null))
                .save(consumer);
        });
    }
}
