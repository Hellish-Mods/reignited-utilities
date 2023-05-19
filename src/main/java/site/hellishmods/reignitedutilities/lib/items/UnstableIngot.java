package site.hellishmods.reignitedutilities.lib.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.init.Items;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = reignitedutilities.MOD_ID)
public class UnstableIngot extends Item {
    public UnstableIngot() {
        super(new Item.Properties().tab(reignitedutilities.TAB).stacksTo(1));
    }

    static Integer getTicksLeft(ItemStack stack) {
        if(!stack.hasTag() || !stack.getTag().contains("ticksleft")) return null;
        return stack.getTag().getInt("ticksleft");
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
    void setCountdown(ItemStack stack) {
        stack.setTag(new CompoundNBT());
        stack.getTag().putInt("ticksleft", 200);
    }
            
    @Override
    public void onCraftedBy(ItemStack stack, World world, PlayerEntity entity) {
        setCountdown(stack);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (getTicksLeft(stack)==null) {
            if (!((PlayerEntity)entity).isCreative()) setCountdown(stack);
            return;
        }

        if (getTicksLeft(stack)==0) explode(entity, world, stack);
        else stack.getTag().putInt("ticksleft", getTicksLeft(stack)-1);
    }
    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        if(getTicksLeft(stack)!=null) tooltip.add(new TranslationTextComponent(stack.getItem().getDescriptionId()+".tooltip.active", (float) getTicksLeft(stack)/20));
        else tooltip.add(new TranslationTextComponent(stack.getItem().getDescriptionId()+".tooltip.inactive"));
    }

    @SubscribeEvent
    static public void onItemThrown(ItemTossEvent e) {
        ItemEntity item = e.getEntityItem();
        
        if (getTicksLeft(item.getItem())!=null && item.getItem().getItem()==Items.unstableIngot.get()) {
            explode(e.getPlayer().getEntity(), e.getPlayer().getEntity().level, item);
        };
    }
    @SubscribeEvent
    static public void onCraftingTableClose(PlayerContainerEvent.Close e) {
        PlayerEntity player = e.getPlayer();

        for (ItemStack i : player.inventory.items) {
            if (i.getItem()==Items.unstableIngot.get() && i.hasTag() && i.getTag().contains("ticksleft")) explode(player, player.level, i);
        }
    }
}
