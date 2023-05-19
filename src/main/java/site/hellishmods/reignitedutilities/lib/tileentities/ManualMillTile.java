package site.hellishmods.reignitedutilities.lib.tileentities;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import site.hellishmods.reignitedutilities.init.TileEntities;

public class ManualMillTile extends GPProducingTile {
    public boolean generating;

    public ManualMillTile() {
        super(TileEntities.manual_mill_entity_type.get());
    }

    @Override
    public Float getGP() {return 15f;}
    @Override
    public boolean canGenerate(BlockPos pos, World world) {return generating;}
    @Override
    public void tick() {
        // TODO: finish
        // generating = false;
    }
}
