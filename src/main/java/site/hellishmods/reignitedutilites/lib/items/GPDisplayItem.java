package site.hellishmods.reignitedutilites.lib.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import site.hellishmods.reignitedutilites.reignitedutilites;
import site.hellishmods.reignitedutilites.lib.GPData;

public class GPDisplayItem extends Item {
    public PlayerEntity owner;
    public GPDisplayItem() {
        super(new Item.Properties().tab(reignitedutilites.TAB));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity && world instanceof ServerWorld && entity!=owner) owner = (PlayerEntity)entity;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        if (owner!=null) tooltip.add(new TranslationTextComponent("item."+reignitedutilites.MOD_ID+".redstone_crystal.tooltip", GPData.get(owner, "o"), GPData.get(owner, "i")));
    }
}
