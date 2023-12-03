package site.hellishmods.reignitedutilities.lib.loottables;

import java.util.HashMap;
import java.util.stream.Collectors;

import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.ItemLootEntry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.init.Blocks;

public class ReignitedUtilitiesBlockLootTable extends BlockLootTables {
    private static HashMap<RegistryObject<Block>, Block> exceptions = new HashMap<>();
    static {
        exceptions.put(Blocks.CURSED_EARTH, net.minecraft.block.Blocks.DIRT); // TODO: fix
    }

    @Override
    protected void addTables() {
        for (RegistryObject<Block> b : reignitedutilities.BLOCKS.getEntries()) {
            Block block = b.get();
            if (block instanceof CropsBlock) continue;

            if (exceptions.containsKey(b)) {
                add(block, createSelfDropDispatchTable(block, HAS_SILK_TOUCH, applyExplosionCondition(block, ItemLootEntry.lootTableItem(exceptions.get(b)))));
                continue;
            }
            
            dropSelf(block);
        }
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getValues().stream()
            .filter(block -> block.getRegistryName().getNamespace().equals(reignitedutilities.MOD_ID))
            .filter(block -> !(block instanceof CropsBlock))
            .collect(Collectors.toList());
    }
}
