package site.hellishmods.reignitedutilities.lib.loottables;

import java.util.stream.Collectors;

import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import site.hellishmods.reignitedutilities.reignitedutilities;

public class ReignitedUtilitiesBlockLootTable extends BlockLootTables {
    @Override
    protected void addTables() {
        for (RegistryObject<Block> block : reignitedutilities.BLOCKS.getEntries()) {
            dropSelf(block.get());
        }
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(reignitedutilities.MOD_ID)).collect(Collectors.toList());
    }
}
