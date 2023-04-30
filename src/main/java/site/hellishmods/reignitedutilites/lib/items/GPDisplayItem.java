package site.hellishmods.reignitedutilites.lib.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import site.hellishmods.reignitedutilites.reignitedutilites;
import site.hellishmods.reignitedutilites.lib.GPData;

public class GPDisplayItem extends Item {
    public GPDisplayItem() {
        super(new Item.Properties().tab(reignitedutilites.TAB));
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent e) {
        PlayerEntity player = e.getPlayer();
        if (player!=null && e.getItemStack().getItem()==this) e.getToolTip().add(new TranslationTextComponent("item."+reignitedutilites.MOD_ID+".gp_display_item.tooltip", Math.round(GPData.get(e.getPlayer().getStringUUID(), "o")*100f)/100f, Math.round(GPData.get(e.getPlayer().getStringUUID(), "i"))));
    }
}
