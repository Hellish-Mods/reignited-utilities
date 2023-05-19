package site.hellishmods.reignitedutilities.lib.tileentities;

import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

abstract public class GPProducingTile extends GPTile { 
    public GPProducingTile(TileEntityType<?> tile) {
        super(tile);
        mode = "o";
    }

    abstract public boolean canGenerate(BlockPos pos, World world);
    
    @Override
    public void tick() {
        if (!getTileData().contains("owner")) return;

        if (canGenerate(getBlockPos(), getLevel())) activate();
        else deactivate();
    }
}
