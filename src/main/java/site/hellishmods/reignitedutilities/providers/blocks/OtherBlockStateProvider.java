package site.hellishmods.reignitedutilities.providers.blocks;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.client.model.generators.ModelFile.ExistingModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ExistingFileHelper.ResourceType;
import net.minecraftforge.registries.ForgeRegistries;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.lib.blocks.OrientableBlock;

public class OtherBlockStateProvider extends BlockStateProvider {
    private static final ArrayList<String> ALL_SIDED_MODELS = Lists.newArrayList(
        "polished_stone",
        "stoneburnt",
        "stoneburnt_chiseled",
        "stoneburnt_cross",
        "truchet", 
        "angel_block",
        "overworld_portal"
    );
    
    ExistingFileHelper exFileHelper;
    public OtherBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, reignitedutilities.MOD_ID, exFileHelper);
        this.exFileHelper = exFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        ForgeRegistries.BLOCKS.getValues().stream().filter(b -> b.getRegistryName().getNamespace().equals(reignitedutilities.MOD_ID)).forEach(b -> {
            if (ALL_SIDED_MODELS.contains(b.getRegistryName().getPath())) {
                simpleBlock(b);
                return;
            }

            ExistingModelFile model = new ModelFile.ExistingModelFile(new ResourceLocation(b.getRegistryName().getNamespace(), ModelProvider.BLOCK_FOLDER+"/"+b.getRegistryName().getPath()), exFileHelper);
            if (!exFileHelper.exists(model.getUncheckedLocation(), new ResourceType(ResourcePackType.CLIENT_RESOURCES, ".json", "models"))) return;

            if (b instanceof OrientableBlock) {
                horizontalBlock(b, model);
                return;
            }

            getVariantBuilder(b).forAllStates(state -> ConfiguredModel.builder().modelFile(model).build());
        });
    }
    
}
