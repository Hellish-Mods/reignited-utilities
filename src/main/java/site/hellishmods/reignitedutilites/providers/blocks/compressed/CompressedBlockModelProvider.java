package site.hellishmods.reignitedutilites.providers.blocks.compressed;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import site.hellishmods.reignitedutilites.reignitedutilites;
import site.hellishmods.reignitedutilites.init.Blocks;

public class CompressedBlockModelProvider extends BlockStateProvider {
    public CompressedBlockModelProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, reignitedutilites.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        Blocks.CompressedBlocks.forEach(b -> {
            simpleBlock(b.get()); // TODO: big b textures
        });
    }
}
