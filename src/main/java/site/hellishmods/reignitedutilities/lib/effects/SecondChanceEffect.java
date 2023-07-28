package site.hellishmods.reignitedutilities.lib.effects;

import java.util.UUID;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import site.hellishmods.reignitedutilities.reignitedutilities;

public class SecondChanceEffect extends Effect {
    public SecondChanceEffect(EffectType effectType, int color) {
        super(effectType, color);
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public void onDeath(LivingDeathEvent e) {
        LivingEntity entity = e.getEntityLiving();

        if (entity.getTags().contains("secondChance")) {
            entity.getTags().remove("secondChance");
            return;
        }
        if (!entity.hasEffect(this)) return;
        e.setCanceled(true);

        entity.setHealth(entity.getMaxHealth());
        entity.addEffect(new EffectInstance(Effects.WEAKNESS, 10*20));
        entity.sendMessage(new TranslationTextComponent("chat."+reignitedutilities.MOD_ID+".second_chance"), UUID.randomUUID()); // TODO: fix???????

        EffectInstance oldEff = entity.getEffect(this);
        if (oldEff.getAmplifier()>0) {
            EffectInstance newEff = new EffectInstance(this, oldEff.getDuration(), oldEff.getAmplifier()-1);
            entity.removeEffect(this);
            entity.addEffect(newEff);
        }
        else {
            entity.removeEffect(this);
            entity.addTag("secondChance");
        }
    }
}
