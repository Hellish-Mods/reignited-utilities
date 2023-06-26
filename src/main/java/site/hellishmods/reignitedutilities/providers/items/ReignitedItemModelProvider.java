package site.hellishmods.reignitedutilities.providers.items;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import site.hellishmods.reignitedutilities.reignitedutilities;

public class ReignitedItemModelProvider extends ItemModelProvider {
    public ReignitedItemModelProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, reignitedutilities.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerModels() {
        ArrayList<String> exceptions = Lists.newArrayList(
            "unstable_ingot",
            "sun_crystal",
            "biome_marker"
        );
        

        ForgeRegistries.ITEMS.getValues().stream().filter(item -> item.getRegistryName().getNamespace().equals(reignitedutilities.MOD_ID)).forEach(i -> {
            String name = i.getRegistryName().getPath();
            
            if (exceptions.contains(name)) return;

            if (i instanceof BlockItem) getBuilder(name).parent(new ModelFile.UncheckedModelFile(modLoc("block/"+name)));
            else getBuilder(name).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", modLoc("item/"+name));
        });
    }
}
