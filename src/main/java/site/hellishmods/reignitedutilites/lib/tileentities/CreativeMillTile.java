package site.hellishmods.reignitedutilites.lib.tileentities;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import site.hellishmods.reignitedutilites.init.TileEntities;

public class CreativeMillTile extends GPProducingTile {
    public CreativeMillTile() {
        super(TileEntities.creative_mill_entity_type.get());
    }

    @Override
    public Float getGP() {return 10000f;} // TODO: config options for generation amount
    @Override
    public boolean canGenerate(BlockPos pos, World world) {return true;}
}
