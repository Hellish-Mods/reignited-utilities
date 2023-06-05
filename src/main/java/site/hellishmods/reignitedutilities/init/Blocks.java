package site.hellishmods.reignitedutilities.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.BarrelTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.lib.blocks.CompressedBlock;
import site.hellishmods.reignitedutilities.lib.blocks.GPConsumingBlock;
import site.hellishmods.reignitedutilities.lib.blocks.GPProducingBlock;
import site.hellishmods.reignitedutilities.lib.tileentities.ChunkLoadingWardTile;
import site.hellishmods.reignitedutilities.lib.tileentities.CreativeMillTile;
import site.hellishmods.reignitedutilities.lib.tileentities.DragonEggMillTile;
import site.hellishmods.reignitedutilities.lib.tileentities.FireMillTile;
import site.hellishmods.reignitedutilities.lib.tileentities.LunarPanelMillTile;
import site.hellishmods.reignitedutilities.lib.tileentities.ManualMillTile;
import site.hellishmods.reignitedutilities.lib.tileentities.SolarPanelMillTile;
import site.hellishmods.reignitedutilities.lib.tileentities.WindMillTile;

public class Blocks {
    public static ArrayList<RegistryObject<CompressedBlock>> CompressedBlocks = new ArrayList<>();

    public static final RegistryObject<Block> ANGEL_BLOCK = reignitedutilities.BLOCKS.register("angel_block", () -> new Block(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> POLISHED_STONE = reignitedutilities.BLOCKS.register("polished_stone", () -> new Block(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> STONEBURNT = reignitedutilities.BLOCKS.register("stoneburnt", () -> new Block(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> STONEBURNT_CHISELED = reignitedutilities.BLOCKS.register("stoneburnt_chiseled", () -> new Block(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> TRUCHET = reignitedutilities.BLOCKS.register("truchet", () -> new Block(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE))); // TODO: make the truchet effect
    public static final RegistryObject<Block> STONEBURNT_CROSS = reignitedutilities.BLOCKS.register("stoneburnt_cross", () -> new Block(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> SOLAR_PANEL = reignitedutilities.BLOCKS.register("solar_panel", () -> new GPProducingBlock<SolarPanelMillTile>(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE), TileEntities.solar_panel_mill_entity_type));
    public static final RegistryObject<Block> LUNAR_PANEL = reignitedutilities.BLOCKS.register("lunar_panel", () -> new GPProducingBlock<LunarPanelMillTile>(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE), TileEntities.lunar_panel_mill_entity_type));
    public static final RegistryObject<Block> FIRE_MILL = reignitedutilities.BLOCKS.register("fire_mill", () -> new GPProducingBlock<FireMillTile>(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE), TileEntities.fire_mill_entity_type));
    public static final RegistryObject<Block> WIND_MILL = reignitedutilities.BLOCKS.register("wind_mill", () -> new GPProducingBlock<WindMillTile>(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE), TileEntities.wind_mill_entity_type)); // TODO: replace properties & texture
    public static final RegistryObject<Block> CREATIVE_MILL = reignitedutilities.BLOCKS.register("creative_mill", () -> new GPProducingBlock<CreativeMillTile>(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE), TileEntities.creative_mill_entity_type));
    public static final RegistryObject<Block> MANUAL_MILL = reignitedutilities.BLOCKS.register("manual_mill", () -> new GPProducingBlock<ManualMillTile>(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE), TileEntities.manual_mill_entity_type) {
        @Override
        public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult ray) {
            tile.generating = true;

            return ActionResultType.SUCCESS;
        }
    });
    public static final RegistryObject<Block> DRAGON_EGG_MILL = reignitedutilities.BLOCKS.register("dragon_egg_mill", () -> new GPProducingBlock<DragonEggMillTile>(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE), TileEntities.dragon_egg_mill_entity_type));
    
    public static final RegistryObject<Block> CHUNK_LOADING_WARD = reignitedutilities.BLOCKS.register("chunk_loading_ward", () -> new GPConsumingBlock<ChunkLoadingWardTile>(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE), TileEntities.chunk_loading_ward_entity_type));

    static void initCompressed() {
        HashMap<String, Integer> mats = new HashMap<>();
        mats.put("cobblestone", 8);
        mats.put("dirt", 4);
        mats.put("sand", 2);
        mats.put("gravel", 2);
        mats.put("netherrack", 6);
        
        mats.forEach((mat, compTier) -> {
            IntStream.range(1, compTier+1).forEach(i -> {
                RegistryObject<CompressedBlock> b = reignitedutilities.BLOCKS.register(Integer.toString(i)+"_compressed_"+mat, () -> new CompressedBlock(i, mat));
                CompressedBlocks.add(b);
            });
        });
    }
    public static void init() {
        initCompressed();

        reignitedutilities.BLOCKS.register("slightly_larger_chest", () -> new Block(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.CHEST)) { // TODO: finish and item model providers
            @Override
            public boolean hasTileEntity(BlockState state) {return true;} 
            @Override
            public TileEntity createTileEntity(BlockState state, IBlockReader world) {
                return new BarrelTileEntity();
            }
        });
    }
}
