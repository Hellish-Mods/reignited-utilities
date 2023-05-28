package site.hellishmods.reignitedutilities.providers.blocks;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import site.hellishmods.reignitedutilities.reignitedutilities;

public class AllSideBlockModelProvider extends BlockStateProvider {
    public AllSideBlockModelProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, reignitedutilities.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ArrayList<String> allSidedModels = Lists.newArrayList(
            "polished_stone",
            "stoneburnt",
            "stoneburnt_chiseled",
            "stoneburnt_cross",
            "truchet"
        );

        ForgeRegistries.BLOCKS.getValues().stream().filter(b -> b.getRegistryName().getNamespace().equals(reignitedutilities.MOD_ID)).forEach(b -> {
            if(allSidedModels.contains(b.getRegistryName().getPath())) simpleBlock(b);
        });
    }
}
