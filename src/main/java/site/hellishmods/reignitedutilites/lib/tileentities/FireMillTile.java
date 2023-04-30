package site.hellishmods.reignitedutilites.lib.tileentities;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import site.hellishmods.reignitedutilites.init.TileEntities;

public class FireMillTile extends GPProducingTile {
    public FireMillTile() {
        super(TileEntities.fire_mill_entity_type.get());
    }

    @Override
    public Float getGP() {return 4f;}
    @Override
    public boolean canGenerate(BlockPos pos, World world) {
        return world.getBlockState(pos.below()).getBlock().equals(Blocks.FIRE);
    }
}
