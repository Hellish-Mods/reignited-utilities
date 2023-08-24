package site.hellishmods.reignitedutilities.lib.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import site.hellishmods.reignitedutilities.lib.tileentities.SlightlyLargerChestTile;

public class SlightlyLargerChestBlock extends OrientableBlock {
    public SlightlyLargerChestBlock() {
        super(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.CHEST));
    }
    
    @Override
    public boolean hasTileEntity(BlockState state) {return true;} 
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SlightlyLargerChestTile();
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        if (!world.isClientSide) {
            NetworkHooks.openGui((ServerPlayerEntity)player, (SlightlyLargerChestTile)world.getBlockEntity(pos), pos);
            world.playSound(null, pos, SoundEvents.CHEST_OPEN, SoundCategory.BLOCKS, 1, 1);
            return ActionResultType.CONSUME;
        }
        return ActionResultType.SUCCESS;
    }
    @Override
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        InventoryHelper.dropContents(world, pos, (SlightlyLargerChestTile)world.getBlockEntity(pos));
    }
}
