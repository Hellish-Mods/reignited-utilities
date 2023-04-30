package site.hellishmods.reignitedutilites.lib.tileentities;

import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import site.hellishmods.reignitedutilites.lib.GPData;

abstract public class GPConsumingTile extends GPTile {
    public GPConsumingTile(TileEntityType<?> tile) {
        super(tile);
        mode = "i";
    }

    abstract public void consumeTick(BlockPos pos, World world, String ownerID);

    @Override
    public void tick() {
        if (!getTileData().contains("owner")) return;

        activate();
        if (getGP()<GPData.get(getOwnerId(), "o")) consumeTick(getBlockPos(), getLevel(), getOwnerId());
    }
}
