package site.hellishmods.reignitedutilities.lib;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class BlockStateWithPosition {
    public BlockState state;
    public BlockPos pos;
    public World world;

    public BlockStateWithPosition(BlockState state, BlockPos pos, World world) {
        this.state = state;
        this.pos = pos;
        this.world = world;
    }
    public BlockStateWithPosition offset(int x, int y, int z) {
        BlockPos newPos = pos.offset(x, y, z);
        return new BlockStateWithPosition(world.getBlockState(newPos), newPos, world);
    }
    public void set() {
        world.setBlock(pos, state, Constants.BlockFlags.DEFAULT);
    }
}
