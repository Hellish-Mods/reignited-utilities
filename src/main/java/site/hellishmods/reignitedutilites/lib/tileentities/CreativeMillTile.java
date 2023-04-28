package site.hellishmods.reignitedutilites.lib.tileentities;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import site.hellishmods.reignitedutilites.init.TileEntities;

public class CreativeMillTile extends GPProducingTile {
    public CreativeMillTile() {
        super(TileEntities.creative_mill_entity_type.get());
    }

    @Override
    public int getGPOutput() {return 10000;}
    @Override
    public boolean canGenerate(BlockPos pos, World world) {return true;}
}
