package site.hellishmods.reignitedutilities.lib.blocks;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import site.hellishmods.reignitedutilities.lib.tileentities.GPConsumingTile;

public class GPConsumingBlock<T extends GPConsumingTile> extends GPBlock<T> {
    public GPConsumingBlock(Properties properties, RegistryObject<TileEntityType<T>> tileType) {
        super(properties, tileType);
    }
}