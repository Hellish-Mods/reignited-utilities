package site.hellishmods.reignitedutilities.providers.blocks;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelBuilder.Perspective;
import net.minecraftforge.common.data.ExistingFileHelper;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.init.Blocks;

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

            simpleBlock(spike, models().withExistingParent(spikeName, new ResourceLocation(reignitedutilities.MOD_ID, "block/spike")).texture("texture", blockModelName).ao(false).transforms()
                .transform(Perspective.GUI).rotation(30, 225, 0).translation(0, 0, 0).scale(.625f, .625f, .625f).end()
                .transform(Perspective.GROUND).rotation(0, 0, 0).translation(0, 3, 0).scale(.25f, .25f, .25f).end()
                .transform(Perspective.FIXED).rotation(0, 0, 0).translation(0, 0, 0).scale(.5f, .5f, .5f).end()
                .transform(Perspective.THIRDPERSON_RIGHT).rotation(75, 45, 0).translation(0, 2.5f, 0).scale(.375f, .375f, .375f).end()
                .transform(Perspective.FIRSTPERSON_RIGHT).rotation(0, 45, 0).translation(0, 0, 0).scale(.4f, .4f, .4f).end()
                .transform(Perspective.FIRSTPERSON_LEFT).rotation(0, 225, 0).translation(0, 0, 0).scale(.4f, .4f, .4f).end()
            .end());
        });
    }
}
