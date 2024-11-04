package site.hellishmods.reignitedutilities.lib.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.world.BlockEvent.CropGrowEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RedOrchidBlock extends CropsBlock {
    public RedOrchidBlock() {
        super(AbstractBlock.Properties.copy(Blocks.GRASS).sound(SoundType.STONE));
        MinecraftForge.EVENT_BUS.register(this);
    }
    @Override
    protected IItemProvider getBaseSeedId() {
        return asItem();
    }

    private BlockPos getBlockBelow(BlockPos pos) {
        return pos.offset(0, -1, 0);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader reader, BlockPos pos) {
        return state.is(Blocks.REDSTONE_ORE);
    }

    @SubscribeEvent
    public void onCropGrow(CropGrowEvent.Post event) {
        if (!event.getState().getBlock().equals(this) || !isMaxAge(event.getState())) return;
        RedstoneOreBlock.interact(event.getWorld().getBlockState(getBlockBelow(event.getPos())), (World)event.getWorld(), getBlockBelow(event.getPos()));
    }

    @SubscribeEvent
    public void onBoneMeal(BonemealEvent event) { // TODO: fix
        if (!event.getBlock().getBlock().equals(this) || getAge(event.getBlock())<getMaxAge()-1) return;
        RedstoneOreBlock.interact(event.getWorld().getBlockState(getBlockBelow(event.getPos())), event.getWorld(), getBlockBelow(event.getPos()));
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos posBelow, boolean isMoving) {
        super.neighborChanged(state, world, pos, block, posBelow, isMoving);
        if (!posBelow.equals(pos.offset(0, -1, 0)) || !isMaxAge(state)) return;

        RedstoneOreBlock.interact(world.getBlockState(posBelow), world, posBelow);
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos) {
        return PlantType.get("none");
    }
}
