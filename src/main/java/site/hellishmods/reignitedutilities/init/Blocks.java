package site.hellishmods.reignitedutilities.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.IntStream;

import com.mojang.authlib.GameProfile;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.BarrelTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.RegistryObject;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.lib.blocks.CompressedBlock;
import site.hellishmods.reignitedutilities.lib.blocks.GPConsumingBlock;
import site.hellishmods.reignitedutilities.lib.blocks.GPProducingBlock;
import site.hellishmods.reignitedutilities.lib.blocks.SpikeBlock;
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

    public static final RegistryObject<Block> SLIGHTLY_LARGER_CHEST = reignitedutilities.BLOCKS.register("slightly_larger_chest", () -> new Block(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.CHEST)) { // TODO: finish and item model providers
            @Override
            public boolean hasTileEntity(BlockState state) {return true;} 
            @Override
            public TileEntity createTileEntity(BlockState state, IBlockReader world) {
                return new BarrelTileEntity(TileEntities.slightly_larger_chest_entity_type.get());
            }
        });

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

        DamageSource spikeDamage = new DamageSource("spikes");
        GameProfile fakeKiller = new GameProfile(UUID.randomUUID(), "killer");
        reignitedutilities.BLOCKS.register("wood_spike", () -> new SpikeBlock(net.minecraft.block.Blocks.OAK_PLANKS, 1, spikeDamage) {
            @Override
            public void	stepOn(World world, BlockPos pos, Entity entity) {
                if (entity instanceof LivingEntity && ((LivingEntity)entity).getHealth()>1) entity.hurt(spikeDamage, 1);
            }
        });
        reignitedutilities.BLOCKS.register("stone_spike", () -> new SpikeBlock(net.minecraft.block.Blocks.COBBLESTONE, 2, spikeDamage));
        reignitedutilities.BLOCKS.register("iron_spike", () -> new SpikeBlock(net.minecraft.block.Blocks.IRON_BLOCK, 4, spikeDamage));
        reignitedutilities.BLOCKS.register("gold_spike", () -> new SpikeBlock(net.minecraft.block.Blocks.GOLD_BLOCK, 2, spikeDamage));
        reignitedutilities.BLOCKS.register("diamond_spike", () -> new SpikeBlock(net.minecraft.block.Blocks.DIAMOND_BLOCK, 8, spikeDamage) {
            @Override
            public void	stepOn(World world, BlockPos pos, Entity entity) {
                if (world instanceof ServerWorld) entity.hurt(DamageSource.playerAttack(new FakePlayer((ServerWorld)world, fakeKiller)), 8);
            }
        });
        reignitedutilities.BLOCKS.register("creative_spike", () -> new SpikeBlock(net.minecraft.block.Blocks.DIAMOND_BLOCK, Integer.MAX_VALUE, spikeDamage) {
            @Override
            public void	stepOn(World world, BlockPos pos, Entity entity) {
                if (entity instanceof LivingEntity) entity.hurt(spikeDamage, ((LivingEntity)entity).getHealth());
            }
        });

    }
}
