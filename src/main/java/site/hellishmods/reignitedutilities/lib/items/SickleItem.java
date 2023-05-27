package site.hellishmods.reignitedutilities.lib.items;

import java.util.stream.IntStream;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import site.hellishmods.reignitedutilities.reignitedutilities;

public class SickleItem extends SwordItem {
    int radius;

    public SickleItem(SickleTier tier, float damageSpeed) {
        super(tier, 0, damageSpeed-4, new Item.Properties().tab(reignitedutilities.TAB));
        this.radius = (tier.diameter-1)/2;
    }

    @Override
    public boolean mineBlock(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity player) {
        IntStream.range(pos.getX()-radius, pos.getX()+radius).forEach(x -> {
            IntStream.range(pos.getY()-radius, pos.getY()+radius).forEach(y -> {
                IntStream.range(pos.getZ()-radius, pos.getZ()+radius).forEach(z -> {
                    BlockPos selectedBlockPos = new BlockPos(x, y, z);
                    if(world.getBlockState(selectedBlockPos).getMaterial().isReplaceable()) world.setBlock(selectedBlockPos, Blocks.AIR.defaultBlockState(), Constants.BlockFlags.DEFAULT);
                });
            });
        });

        return super.mineBlock(stack, world, state, pos, player);
    }
}
