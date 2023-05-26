package site.hellishmods.reignitedutilities.providers.blocks.compressed;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.init.Blocks;

public class CompressedBlockModelProvider extends BlockStateProvider {
    public CompressedBlockModelProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, reignitedutilities.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        Blocks.CompressedBlocks.forEach(b -> {
            simpleBlock(b.get()); // TODO: big b textures
        });
    }
}
