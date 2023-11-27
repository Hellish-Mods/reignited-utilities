package site.hellishmods.reignitedutilities.lib.blocks;

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
        for (int i = 0; i < random.ints(10, 30).findFirst().getAsInt(); i++) world.addParticle(ParticleTypes.SMOKE, pos.getX()+random.nextDouble(), pos.getY()+.5, pos.getZ()+random.nextDouble(), 0, .05, 0);
    }
}
