package site.hellishmods.reignitedutilities.providers.items;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import site.hellishmods.reignitedutilities.reignitedutilities;

public class ToolsTagProvider extends ItemTagsProvider {
    private static final ArrayList<String> TOOL_MATS = Lists.newArrayList(
        "wood",
        "stone",
        "iron",
        "gold",
        "diamond",
        "netherite"
    );
    private static final ArrayList<String> TOOL_TYPES = Lists.newArrayList("sickle");

    public ToolsTagProvider(DataGenerator gen, BlockTagsProvider blockTagsProvider, ExistingFileHelper exFileHelper) {
        super(gen, blockTagsProvider, reignitedutilities.MOD_ID, exFileHelper);
    }

    @Override
    protected void addTags() {
        TOOL_TYPES.forEach(t -> {
            TOOL_MATS.forEach(m -> {
                Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(reignitedutilities.MOD_ID, m+"_"+t));

                tag(ItemTags.createOptional(new ResourceLocation("forge", "tools/"+m))).add(item);
                tag(ItemTags.createOptional(new ResourceLocation("forge", t+"s"))).add(item);
                tag(ItemTags.createOptional(new ResourceLocation("forge", t+"s/"+m))).add(item);

                if (m.equals("gold")) tag(ItemTags.PIGLIN_LOVED).add(item);
            });
        });
    }
}
