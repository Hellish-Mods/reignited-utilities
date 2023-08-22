package site.hellishmods.reignitedutilities.lib.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import site.hellishmods.reignitedutilities.reignitedutilities;

public class UnstableIngotItem extends Item {
    public UnstableIngotItem() {
        super(new Item.Properties().tab(reignitedutilities.TAB).stacksTo(1));
        MinecraftForge.EVENT_BUS.register(this);
    }

    static Integer getticksLeft(ItemStack stack) {
        if(!stack.hasTag() || !stack.getTag().contains("ticksLeft")) return null;
        return stack.getTag().getInt("ticksLeft");
    }
    static void explode(Entity entity, World world) {
        BlockPos pos = entity.blockPosition();
        world.explode(null, pos.getX(), pos.getY(), pos.getZ(), 6, true, Explosion.Mode.NONE);
    }
    static void explode(Entity entity, World world, ItemStack item) {
        item.setCount(0);
        explode(entity, world);
    }
    static void explode(Entity entity, World world, ItemEntity item) {
        item.kill();
        explode(entity, world);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (getticksLeft(stack)==null) return;

        if (getticksLeft(stack)==0) explode(entity, world, stack);
        else stack.getTag().putInt("ticksLeft", getticksLeft(stack)-1);
    }
    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        if(getticksLeft(stack)!=null) tooltip.add(new TranslationTextComponent(stack.getItem().getDescriptionId()+".tooltip.active", (float) getticksLeft(stack)/20));
        else tooltip.add(new TranslationTextComponent(stack.getItem().getDescriptionId()+".tooltip.inactive"));
    }

    @SubscribeEvent
    public void onItemThrown(ItemTossEvent e) {
        ItemEntity item = e.getEntityItem();
        
        if (item.getItem().getItem()==this && getticksLeft(item.getItem())!=null) {
            explode(e.getPlayer().getEntity(), e.getPlayer().getEntity().level, item);
        };
    }
    @SubscribeEvent
    public void onCraftingTableClose(PlayerContainerEvent.Close e) {
        PlayerEntity player = e.getPlayer();

        for (ItemStack i : player.inventory.items) {
            if (i.getItem()==this && i.hasTag() && i.getTag().contains("ticksLeft")) explode(player, player.level, i);
        }
    }
}
