package site.hellishmods.reignitedutilities.lib.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class GreekFireEffect extends Effect {
    public GreekFireEffect(EffectType effectType, int color) {
        super(effectType, color);
    }
    
    @Override
    public boolean isDurationEffectTick(int dur, int amp) {
        return true;
    }
    
    @Override
    public void applyEffectTick(LivingEntity entity, int amp) {
        entity.setSecondsOnFire(1);
    }
}
