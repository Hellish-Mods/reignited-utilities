package site.hellishmods.reignitedutilities.lib.items;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.init.Blocks;
import site.hellishmods.reignitedutilities.lib.BlockStateWithPosition;
import site.hellishmods.reignitedutilities.lib.blocks.CursedEarthBlock;

public class DropOfEvilItem extends Item {
    private static ArrayDeque<BlockStateWithPosition> spreaders = new ArrayDeque<>();

    public DropOfEvilItem() {
        super(new Item.Properties().tab(reignitedutilities.TAB));
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) { // 15 block default, 1 block y diff
        ItemStack heldItem = player.getItemInHand(hand);
        if (world.isClientSide()) return ActionResult.fail(heldItem);

        RayTraceResult result = player.pick(5, 0, false);
        if (!(result instanceof BlockRayTraceResult)) return ActionResult.fail(heldItem);

        BlockPos pos = ((BlockRayTraceResult)result).getBlockPos();
        if (!isDirtBlock(world.getBlockState(pos))) return ActionResult.fail(heldItem);

        if (!player.isCreative()) heldItem.shrink(1);

        BlockState state = Blocks.CURSED_EARTH.get().defaultBlockState().setValue(CursedEarthBlock.SPREADING_DISTANCE_LEFT, CursedEarthBlock.MAX_DISTANCE);
        world.setBlock(pos, state, Constants.BlockFlags.DEFAULT);
        spreaders.add(new BlockStateWithPosition(state, pos, world));

        return ActionResult.success(heldItem);
    }
    @SubscribeEvent
    public void spreadCursedEarthServerTick(ServerTickEvent e) { // TODO: break anim
        ArrayDeque<BlockStateWithPosition> spreadersClone = spreaders.clone();
        while(!spreadersClone.isEmpty()) {
            BlockStateWithPosition spreader = spreadersClone.remove();
            spreaders.remove();

            spreader.world.destroyBlock(spreader.pos, false);
            spreader.set();

            Integer spreadDistance = spreader.state.getValue(CursedEarthBlock.SPREADING_DISTANCE_LEFT);
            if(spreadDistance==0) continue;

            Arrays.asList(
                spreader.offset(1, 0, 0),
                spreader.offset(-1, 0, 0),
                spreader.offset(0, 0, 1),
                spreader.offset(0, 0, -1)
            ).forEach(s -> {
                if (s.state.getBlock()==Blocks.CURSED_EARTH.get()) return;

                BlockPos pos = null;
                List<BlockStateWithPosition> possiblePoses = Arrays.asList(
                    s.offset(0, 1, 0),
                    s,
                    s.offset(0, -1, 0)
                );
                for (BlockStateWithPosition p : possiblePoses) {
                    if (!isDirtBlock(p.state)) continue;

                    pos = p.pos;
                    break;
                }
                if (pos==null) return;

                spreaders.add(new BlockStateWithPosition(spreader.state.setValue(CursedEarthBlock.SPREADING_DISTANCE_LEFT, spreadDistance-1), pos, s.world));
            });
        }
    }
    private boolean isDirtBlock(BlockState block) {
        return block.getBlock().getTags().contains(Tags.Blocks.DIRT.getName());
    }
}