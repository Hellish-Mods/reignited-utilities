package site.hellishmods.reignitedutilities.providers.blocks;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.init.Blocks;

import static site.hellishmods.reignitedutilities.utils.AddBlockModelTransforms.addTransforms;

public class PanelModelProvider extends BlockStateProvider {
    public PanelModelProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, reignitedutilities.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ArrayList<RegistryObject<Block>> panels = new ArrayList<>();
        panels.addAll(Arrays.asList(Blocks.SOLAR_PANEL, Blocks.LUNAR_PANEL));

        panels.forEach(p -> {
            simpleBlock(p.get(), addTransforms(models().withExistingParent(p.get().getRegistryName().toString(), new ResourceLocation(reignitedutilities.MOD_ID, "block/panel")).texture("0", modLoc("block/"+p.get().getRegistryName().getPath()))));
        });
    }
}
