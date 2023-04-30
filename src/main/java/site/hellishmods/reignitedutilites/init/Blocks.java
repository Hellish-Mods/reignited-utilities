package site.hellishmods.reignitedutilites.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import site.hellishmods.reignitedutilites.reignitedutilites;
import site.hellishmods.reignitedutilites.lib.blocks.CompressedBlock;
import site.hellishmods.reignitedutilites.lib.blocks.GPConsumingBlock;
import site.hellishmods.reignitedutilites.lib.blocks.GPProducingBlock;
import site.hellishmods.reignitedutilites.lib.tileentities.ChunkLoadingWardTile;
import site.hellishmods.reignitedutilites.lib.tileentities.CreativeMillTile;
import site.hellishmods.reignitedutilites.lib.tileentities.DragonEggMillTile;
import site.hellishmods.reignitedutilites.lib.tileentities.FireMillTile;
import site.hellishmods.reignitedutilites.lib.tileentities.WindMillTile;

public class Blocks {
    public static ArrayList<RegistryObject<CompressedBlock>> CompressedBlocks = new ArrayList<>();

    public static final RegistryObject<Block> WIND_MILL = reignitedutilites.BLOCKS.register("wind_mill", () -> new GPProducingBlock<WindMillTile>(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE), TileEntities.wind_mill_entity_type)); // TODO: replace properties & texture
    public static final RegistryObject<Block> FIRE_MILL = reignitedutilites.BLOCKS.register("fire_mill", () -> new GPProducingBlock<FireMillTile>(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE), TileEntities.fire_mill_entity_type));
    public static final RegistryObject<Block> CREATIVE_MILL = reignitedutilites.BLOCKS.register("creative_mill", () -> new GPProducingBlock<CreativeMillTile>(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE), TileEntities.creative_mill_entity_type));
    public static final RegistryObject<Block> DRAGON_EGG_MILL = reignitedutilites.BLOCKS.register("dragon_egg_mill", () -> new GPProducingBlock<DragonEggMillTile>(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE), TileEntities.dragon_egg_mill_entity_type));
    
    public static final RegistryObject<Block> CHUNK_LOADING_WARD = reignitedutilites.BLOCKS.register("chunk_loading_ward", () -> new GPConsumingBlock<ChunkLoadingWardTile>(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE), TileEntities.chunk_loading_ward_entity_type));

    static void initCompressed() {
        HashMap<String, Integer> mats = new HashMap<>();
        mats.put("cobblestone", 8);
        mats.put("dirt", 4);
        mats.put("sand", 2);
        mats.put("gravel", 2);
        mats.put("netherrack", 6);
        
        mats.forEach((mat, compTier) -> {
            IntStream.range(1, compTier+1).forEach(i -> {
                RegistryObject<CompressedBlock> b = reignitedutilites.BLOCKS.register(Integer.toString(i)+"_compressed_"+mat, () -> new CompressedBlock(i, mat));
                CompressedBlocks.add(b);
            });
        });
    }
    public static void init() {
        initCompressed();
    }
}
