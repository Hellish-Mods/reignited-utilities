package site.hellishmods.reignitedutilities.lib.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.lib.GPData;

public class GPDisplayItem extends Item {
    public GPDisplayItem() {
        super(new Item.Properties().tab(reignitedutilities.TAB));
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent e) {
        PlayerEntity player = e.getPlayer();
        if (player!=null && e.getItemStack().getItem()==this) e.getToolTip().add(new TranslationTextComponent("item."+reignitedutilities.MOD_ID+".gp_display_item.tooltip", Math.round(GPData.get(e.getPlayer().getStringUUID(), "o")*100f)/100f, Math.round(GPData.get(e.getPlayer().getStringUUID(), "i"))));
    }
}
