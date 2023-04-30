package site.hellishmods.reignitedutilites.lib.tileentities;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import site.hellishmods.reignitedutilites.init.TileEntities;
import site.hellishmods.reignitedutilites.lib.GPData;

public class WindMillTile extends GPProducingTile { // TODO: fix duplication when reentering a world
    World world;

    public WindMillTile() {
        super(TileEntities.wind_mill_entity_type.get());
    }

    @Override
    public Float getGP() {
        return 1f;
    }
    @Override
    public boolean canGenerate(BlockPos pos, World world) {
        this.world = world;
        return (
            world.getBlockState(pos.north()).getMaterial().isReplaceable() &&
            world.getBlockState(pos.south()).getMaterial().isReplaceable()
        );
    }

    @Override
    public void deactivate() {
        if (active && getTileData().contains("lastgen")) GPData.set(getOwnerId(), (float)(GPData.get(getOwnerId(), "o")-getTileData().getFloat("lastgen")), "o");

        super.deactivate();
    }
    @Override
    public void tick() {
        super.tick();

        if (active) {
            if (getTileData().contains("lastgen")) GPData.set(getOwnerId(), (float)(GPData.get(getOwnerId(), "o")-getTileData().getFloat("lastgen")), "o");
            
            getTileData().putFloat("lastgen", ((float)Math.cos(world.getGameTime()/10f)*.75f+.25f) + (world.isRaining() ? 1 : 0));
            GPData.set(getOwnerId(), (float)(GPData.get(getOwnerId(), "o")+getTileData().getFloat("lastgen")), "o");
        }
    }
}
