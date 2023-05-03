package site.hellishmods.reignitedutilites.init;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import site.hellishmods.reignitedutilites.reignitedutilites;
import site.hellishmods.reignitedutilites.providers.blocks.BlockLootTableProvider;
import site.hellishmods.reignitedutilites.providers.blocks.compressed.CompressedBlockModelProvider;
import site.hellishmods.reignitedutilites.providers.blocks.compressed.CompressedBlockRecipeProvider;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = reignitedutilites.MOD_ID)
public class Providers {
    @SubscribeEvent
    public static void onDataGather(GatherDataEvent e) {
        DataGenerator gen = e.getGenerator();
        ExistingFileHelper exFileHelper = e.getExistingFileHelper();

        gen.addProvider(new BlockLootTableProvider(gen));

        gen.addProvider(new CompressedBlockRecipeProvider(gen));
        // TODO: reenable with bee textures
        // gen.addProvider(new CompressedBlockModelProvider(gen, exFileHelper));
    }
}
