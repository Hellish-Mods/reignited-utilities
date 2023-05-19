package site.hellishmods.reignitedutilities.lib.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.RegistryObject;
import site.hellishmods.reignitedutilities.lib.tileentities.GPTile;

public class GPBlock<T extends GPTile> extends Block {
    protected T tile;
    protected RegistryObject<TileEntityType<T>> tileType;

    public GPBlock(Properties properties, RegistryObject<TileEntityType<T>> tileType) {
        super(properties);
        this.tileType = tileType;
    }

    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity player, ItemStack stack) {
        if (world instanceof ServerWorld) tile.getTileData().putString("owner", player.getStringUUID());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {return true;}
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        tile = tileType.get().create();
        return tile;
    }
}
