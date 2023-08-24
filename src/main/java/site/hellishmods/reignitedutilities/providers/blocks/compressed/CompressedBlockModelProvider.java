package site.hellishmods.reignitedutilities.providers.blocks.compressed;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.init.Blocks;
import site.hellishmods.reignitedutilities.lib.blocks.CompressedBlock;

public class CompressedBlockModelProvider extends BlockStateProvider {
    public CompressedBlockModelProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, reignitedutilities.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        Blocks.CompressedBlocks.forEach(obj -> {
            CompressedBlock b = obj.get();

            simpleBlock(b, models().withExistingParent(b.getRegistryName().toString(), new ResourceLocation(reignitedutilities.MOD_ID, "block/multi_layer_block"))
                .texture("layer0", mcLoc("block/"+b.mat))
                .texture("layer1", modLoc("block/compressed_overlays/"+Integer.toString(b.compTier)))
            );
        });
    }
}
