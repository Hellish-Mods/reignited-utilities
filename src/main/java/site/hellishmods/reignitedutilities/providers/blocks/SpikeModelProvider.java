package site.hellishmods.reignitedutilities.providers.blocks;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.init.Blocks;

import static site.hellishmods.reignitedutilities.utils.AddBlockModelTransforms.addTransforms;

public class SpikeModelProvider extends BlockStateProvider {
    public SpikeModelProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, reignitedutilities.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        Blocks.SPIKES.forEach(s -> {
            Block spike = s.get();
            String spikeName = spike.getRegistryName().getPath();
            
            ResourceLocation blockModelName;
            if (spikeName.startsWith("wood")) blockModelName = mcLoc("block/stripped_oak_log");
            else if (spikeName.startsWith("stone")) blockModelName = mcLoc("block/cobblestone");
            else if (spikeName.startsWith("creative")) blockModelName = modLoc("block/creative_overlay");
            else blockModelName = mcLoc("block/"+spikeName.split("_")[0]+"_block");

            simpleBlock(spike, addTransforms(models().withExistingParent(spikeName, new ResourceLocation(reignitedutilities.MOD_ID, "block/spike")).texture("texture", blockModelName).ao(false)));
        });
    }
}
