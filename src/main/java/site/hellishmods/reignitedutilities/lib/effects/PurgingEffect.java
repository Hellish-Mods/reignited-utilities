package site.hellishmods.reignitedutilities.lib.effects;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class PurgingEffect extends Effect {
    public PurgingEffect(EffectType effectType, int color) {
        super(effectType, color);
    }
    
    @Override
    public boolean isInstantenous() {return true;}
    
    @Override
    public void applyInstantenousEffect(@Nullable Entity entity, @Nullable Entity anotherEntityLmao, LivingEntity player, int amp, double health) {
        player.removeAllEffects();
    }
}
