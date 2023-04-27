package site.hellishmods.reignitedutilites.lib.tileentities;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import site.hellishmods.reignitedutilites.lib.GPData;

abstract public class GPProducingTile extends TileEntity implements ITickableTileEntity {
    protected boolean active;
    int tick;
    
    public GPProducingTile(TileEntityType<?> tile) {
        super(tile);
    }

    protected String getOwnerId() {
        return getTileData().getString("owner");
    }
    protected void activate() {
        if (active) return;

        GPData.set(getOwnerId(), GPData.get(getOwnerId(), "o")+getGPOutput(), "o");
        active = true;
    }
    protected void deactivate() {
        if (!active) return;

        GPData.set(getOwnerId(), GPData.get(getOwnerId(), "o")-getGPOutput(), "o");
        active = false;
    }

    abstract public int getGPOutput();
    abstract public boolean canGenerate(BlockPos pos, World world);
    
    @Override
    public void setRemoved() {
        deactivate();
        super.setRemoved();
    }

    @Override
    public void tick() {
        tick++;

        if (!getTileData().contains("owner")) return;

        if (canGenerate(getBlockPos(), getLevel())) activate();
        else deactivate();
    }
}
