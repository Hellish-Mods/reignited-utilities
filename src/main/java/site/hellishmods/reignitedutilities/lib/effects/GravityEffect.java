package site.hellishmods.reignitedutilities.lib.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.vector.Vector3d;

public class GravityEffect extends Effect {
    public GravityEffect(EffectType effectType, int color) {
        super(effectType, color);
    }
    
    @Override
    public boolean isDurationEffectTick(int dur, int amp) {
        return true;
    }
    
    @Override
    public void applyEffectTick(LivingEntity entity, int amp) {
        Vector3d movement = entity.getDeltaMovement();
        entity.setDeltaMovement(movement.x, movement.y-.1, movement.z);
    }
}
