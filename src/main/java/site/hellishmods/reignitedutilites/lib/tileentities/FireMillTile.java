package site.hellishmods.reignitedutilites.lib.tileentities;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import site.hellishmods.reignitedutilites.init.TileEntities;

public class FireMillTile extends GPProducingTile { // TODO: test
    public FireMillTile() {
        super(TileEntities.dragon_egg_mill_entity_type.get());
    }

    @Override
    public int getGPOutput() {return 4;}
    @Override
    public boolean canGenerate(BlockPos pos, World world) {
        return world.getBlockState(pos.below()).getBlock().equals(Blocks.FIRE);
    }
}
