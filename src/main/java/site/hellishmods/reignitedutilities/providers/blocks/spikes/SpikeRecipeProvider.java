package site.hellishmods.reignitedutilities.providers.blocks.spikes;

import java.util.function.Consumer;

import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.TickTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.init.Blocks;

public class SpikeRecipeProvider extends RecipeProvider {
    public SpikeRecipeProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        Blocks.SPIKES.forEach(s -> {
            String spikeMat = s.get().getRegistryName().getPath().split("_")[0];
            if(spikeMat.equals("creative")) return;

            ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(s.get(), 4)
                .pattern(" S ")
                .pattern("SIS")
                .pattern("IBI")
                .define('S', ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft", (spikeMat.equals("wood") || spikeMat.equals("gold") ? spikeMat+"en" : spikeMat)+"_sword")));
                
            switch (spikeMat) {
                case "wood": builder.define('I', ItemTags.createOptional(new ResourceLocation("minecraft", "planks"))); builder.define('B', ItemTags.createOptional(new ResourceLocation("minecraft", "logs"))); break;
                case "stone": builder.define('I', Tags.Items.COBBLESTONE); builder.define('B', ForgeRegistries.ITEMS.getValue(new ResourceLocation(reignitedutilities.MOD_ID, "1_compressed_cobblestone"))); break;
                case "diamond": builder.define('I', Items.DIAMOND); builder.define('B', Items.DIAMOND_BLOCK); break;

                default: builder.define('I', ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft", spikeMat+"_ingot"))); builder.define('B', ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft", spikeMat+"_block"))); break;
            }
            
            builder
                .unlockedBy("tick", new TickTrigger().createInstance(null, EntityPredicate.AndPredicate.ANY, null))
                .save(consumer);
        });
    }
}
