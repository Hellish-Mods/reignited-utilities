package site.hellishmods.reignitedutilities.lib.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.loading.FMLEnvironment;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.init.Blocks;

public class AngelBlockItem extends BlockItem {
    public AngelBlockItem() {
        super(Blocks.ANGEL_BLOCK.get(), new Item.Properties().tab(reignitedutilities.TAB));
    }

    @Override
    public ITextComponent getName(ItemStack i) {
        if (FMLEnvironment.dist!=Dist.CLIENT) return null;
        return Blocks.ANGEL_BLOCK.get().getName();
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (player.pick(4, 0, false).getType()==RayTraceResult.Type.MISS) {
            world.setBlock(new BlockPos(player.pick(2, 0, false).getLocation()), getBlock().defaultBlockState(), Constants.BlockFlags.DEFAULT);
            if (!player.isCreative()) player.getItemInHand(hand).shrink(1);
        }

        return super.use(world, player, hand);
    }
}
