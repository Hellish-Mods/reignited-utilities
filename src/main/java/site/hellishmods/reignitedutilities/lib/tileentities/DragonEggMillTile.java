package site.hellishmods.reignitedutilities.lib.tileentities;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import site.hellishmods.reignitedutilities.init.TileEntities;

public class DragonEggMillTile extends GPProducingTile {
    public DragonEggMillTile() {
        super(TileEntities.dragon_egg_mill_entity_type.get());
    }

    @Override
    public Float getGP() {return 500f;}
    @Override
    public boolean canGenerate(BlockPos pos, World world) {
        return world.getBlockState(pos.above()).getBlock().asItem().getTags().contains(new ResourceLocation("forge", "dragon_eggs"));
    }
}
