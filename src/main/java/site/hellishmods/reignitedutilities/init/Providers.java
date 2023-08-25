package site.hellishmods.reignitedutilities.init;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.providers.blocks.AllSideBlockModelProvider;
import site.hellishmods.reignitedutilities.providers.blocks.BlockLootTableProvider;
import site.hellishmods.reignitedutilities.providers.blocks.PanelModelProvider;
import site.hellishmods.reignitedutilities.providers.blocks.SpikeModelProvider;
import site.hellishmods.reignitedutilities.providers.blocks.compressed.CompressedBlockModelProvider;
import site.hellishmods.reignitedutilities.providers.blocks.compressed.CompressedBlockRecipeProvider;
import site.hellishmods.reignitedutilities.providers.items.ReignitedItemModelProvider;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = reignitedutilities.MOD_ID)
public class Providers {
    @SubscribeEvent
    public static void onDataGather(GatherDataEvent e) {
        DataGenerator gen = e.getGenerator();
        ExistingFileHelper exFileHelper = e.getExistingFileHelper();

        gen.addProvider(new ReignitedItemModelProvider(gen, exFileHelper));

        gen.addProvider(new BlockLootTableProvider(gen));
        gen.addProvider(new AllSideBlockModelProvider(gen, exFileHelper));
        gen.addProvider(new PanelModelProvider(gen, exFileHelper));
        gen.addProvider(new SpikeModelProvider(gen, exFileHelper));
        gen.addProvider(new CompressedBlockModelProvider(gen, exFileHelper));
        gen.addProvider(new CompressedBlockRecipeProvider(gen));
    }
}
