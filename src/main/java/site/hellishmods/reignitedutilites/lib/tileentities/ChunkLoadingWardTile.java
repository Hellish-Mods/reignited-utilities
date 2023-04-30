package site.hellishmods.reignitedutilites.lib.tileentities;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.world.ForgeChunkManager;
import site.hellishmods.reignitedutilites.reignitedutilites;
import site.hellishmods.reignitedutilites.init.TileEntities;

public class ChunkLoadingWardTile extends GPConsumingTile {
    public ChunkLoadingWardTile() {
        super(TileEntities.chunk_loading_ward_entity_type.get());
    }

    @Override
    public void consumeTick(BlockPos pos, World world, String ownerID) {
        ChunkPos chunk = world.getChunkAt(pos).getPos();
        if (world instanceof ServerWorld) ForgeChunkManager.forceChunk((ServerWorld) world, reignitedutilites.MOD_ID, pos, chunk.x, chunk.z, true, true);
    }

    @Override
    public Float getGP() {return 8f;} // TODO: add config for consumption amount
}
