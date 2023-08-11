package site.hellishmods.reignitedutilities.lib.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TranslationTextComponent;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.utils.DeletableChatMessage;

public class DoomEffect extends Effect {
    int duration;
    DeletableChatMessage prevMsg;

    public DoomEffect(EffectType effectType, int color) {
        super(effectType, color);
    }
    
    @Override
    public boolean isDurationEffectTick(int dur, int amp) {
        duration = dur;
        return true;
    }
    
    @Override
    public void applyEffectTick(LivingEntity entity, int amp) {
        if (duration==1) {
            entity.hurt(new DamageSource("doom"), entity.getHealth());
            prevMsg.delete();
            prevMsg = null;
        }
        else if ((duration+1)%20==0) {
            int seconds = (duration+1)/20;
            if (seconds%10==0 || seconds<=10) {
                if (prevMsg!=null) prevMsg.delete();
                prevMsg = new DeletableChatMessage(new TranslationTextComponent("chat."+reignitedutilities.MOD_ID+".doom", seconds));
            }
        }
    }
}
