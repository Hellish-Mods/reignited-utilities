package site.hellishmods.reignitedutilites.lib.tileentities;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import site.hellishmods.reignitedutilites.init.TileEntities;

public class DragonEggMillTile extends GPProducingTile {
    public DragonEggMillTile() {
        super(TileEntities.dragon_egg_mill_entity_type.get());
    }

    @Override
    public int getGPOutput() {return 500;}
    @Override
    public boolean canGenerate(BlockPos pos, World world) {
        return world.getBlockState(pos.above()).getBlock().equals(Blocks.DRAGON_EGG); // TODO: config option for custom dragon eggs
    }
}
