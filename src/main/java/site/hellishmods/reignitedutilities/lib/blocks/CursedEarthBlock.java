package site.hellishmods.reignitedutilities.lib.blocks;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.util.Constants;

public class CursedEarthBlock extends Block {
    public static final int MAX_DISTANCE = 15; // TODO: a config option for max distance
    public static final IntegerProperty SPREADING_DISTANCE_LEFT = IntegerProperty.create("spread", 0, MAX_DISTANCE);
    
    public CursedEarthBlock() {
        super(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.DIRT).sound(SoundType.STONE)); // TODO: loot table
    }
    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(SPREADING_DISTANCE_LEFT);
    }
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        return defaultBlockState().setValue(SPREADING_DISTANCE_LEFT, 0);
    }
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) { // TODO: a config option to disable particles?
        for (int i = 0; i < random.ints(5, 20).findFirst().getAsInt(); i++) world.addParticle(ParticleTypes.SMOKE, pos.getX()+random.nextDouble(), pos.getY()+.5, pos.getZ()+random.nextDouble(), 0, .05, 0);
    }
    
    @Override
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!world.isClientSide()) world.getBlockTicks().scheduleTick(pos, this, 1);
    }
    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, java.util.Random random) {
        if (state.getValue(SPREADING_DISTANCE_LEFT)==0) return;

        Arrays.asList(
            pos.offset(1, 0, 0),
            pos.offset(-1, 0, 0),
            pos.offset(0, 0, 1),
            pos.offset(0, 0, -1)
        ).forEach(p -> {
            if (world.getBlockState(p).getBlock()==this) return;

            BlockPos dirtPos = null;
            List<BlockPos> possibleDirtPoses = Arrays.asList(
                p.above(),
                p,
                p.below()
            );
            for (BlockPos d : possibleDirtPoses) {
                if (!isDirtBlock(world.getBlockState(d))) continue;

                dirtPos = d;
                break;
            }
            if (dirtPos==null) return;

            world.setBlock(dirtPos, state.setValue(SPREADING_DISTANCE_LEFT, state.getValue(SPREADING_DISTANCE_LEFT)-1), Constants.BlockFlags.DEFAULT);
        });
        world.setBlock(pos, state.setValue(SPREADING_DISTANCE_LEFT, 0), Constants.BlockFlags.DEFAULT);
    }
    public static boolean isDirtBlock(BlockState block) {
        return block.getBlock().getTags().contains(Tags.Blocks.DIRT.getName());
    }
}
