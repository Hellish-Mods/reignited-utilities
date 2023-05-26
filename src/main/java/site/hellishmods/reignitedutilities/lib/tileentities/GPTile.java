package site.hellishmods.reignitedutilities.lib.tileentities;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import site.hellishmods.reignitedutilities.lib.GPData;

abstract public class GPTile extends TileEntity implements ITickableTileEntity {
    protected boolean active;
    protected String mode;
    
    public GPTile(TileEntityType<?> tile) {
        super(tile);
    }

    protected String getOwnerId() {
        return getTileData().getString("owner");
    }
    protected void activate() {
        if (active) return;

        GPData.set(getOwnerId(), GPData.get(getOwnerId(), mode)+getGP(), mode);
        active = true;
    }
    protected void deactivate() {
        if (!active) return;

        GPData.set(getOwnerId(), GPData.get(getOwnerId(), mode)-getGP(), mode);
        active = false;
    }

    abstract public Float getGP();
    
    @Override
    public void setRemoved() {
        deactivate();
        super.setRemoved();
    }
}
