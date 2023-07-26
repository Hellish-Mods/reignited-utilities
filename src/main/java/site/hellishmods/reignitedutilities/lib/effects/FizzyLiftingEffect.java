package site.hellishmods.reignitedutilities.lib.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.vector.Vector3d;

public class FizzyLiftingEffect extends Effect {
    public FizzyLiftingEffect(EffectType effectType, int color) {
        super(effectType, color);
    }
    
    @Override
    public boolean isDurationEffectTick(int dur, int amp) {
        return true;
    }
    
    @Override
    public void applyEffectTick(LivingEntity entity, int amp) {
        Vector3d movement = entity.getDeltaMovement();
        entity.setDeltaMovement(movement.x, .04*(entity.isCrouching() ? -1 : 1), movement.z);
    }
}
