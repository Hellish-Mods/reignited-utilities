package site.hellishmods.reignitedutilities.utils;

import java.util.stream.IntStream;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class FindGround {
    @SuppressWarnings("deprecation")
    public static Vector3d findGround(World world, double x, double z) {
        double y = IntStream.range(0, 256).filter(posy -> (world.getBlockState(new BlockPos(x, posy, z)).isAir() && world.canSeeSky(new BlockPos(x, posy+1, z)))).findFirst().orElse(256);
        return new Vector3d(x, y, z);
    }
}
