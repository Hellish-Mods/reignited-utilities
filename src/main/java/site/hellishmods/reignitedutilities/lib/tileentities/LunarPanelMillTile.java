package site.hellishmods.reignitedutilities.lib.tileentities;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import site.hellishmods.reignitedutilities.init.TileEntities;

public class LunarPanelMillTile extends GPProducingTile {
    public LunarPanelMillTile() {
        super(TileEntities.lunar_panel_mill_entity_type.get());
    }

    @Override
    public Float getGP() {return 1.25f;}
    @Override
    public boolean canGenerate(BlockPos pos, World world) {
        return world.canSeeSky(new BlockPos(pos.getX(), pos.getY()+1, pos.getZ())) && !world.isDay();
    }
}
