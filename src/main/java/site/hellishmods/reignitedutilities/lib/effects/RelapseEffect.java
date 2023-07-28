package site.hellishmods.reignitedutilities.lib.effects;

import java.util.Random;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.PotionEvent.PotionRemoveEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RelapseEffect extends Effect {
    boolean dontTrigger = false;
    public RelapseEffect(EffectType effectType, int color) {
        super(effectType, color);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onEffectRemove(PotionRemoveEvent e) {
       if (dontTrigger || !e.getEntityLiving().hasEffect(this) || (e.getPotion()!=this && (e.getPotion().isBeneficial() || e.getPotionEffect().getDuration()<=10*20) || new Random().nextInt(6)==0)) return;

        e.setCanceled(true);
        if (e.getPotion()==this) return;

        dontTrigger = true;
        e.getEntityLiving().removeEffect(e.getPotion());
        dontTrigger = false;
        
        e.getEntityLiving().addEffect(new EffectInstance(e.getPotion(), e.getPotionEffect().getDuration()-10*20, e.getPotionEffect().getAmplifier()-(e.getPotionEffect().getAmplifier()>0 && new Random().nextInt(2)==0 ? 1 : 0)));
    }
}
