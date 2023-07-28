package site.hellishmods.reignitedutilities.lib.effects;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class LoveEffect extends Effect {
    public LoveEffect(EffectType effectType, int color) {
        super(effectType, color);
    }
    
    @Override
    public boolean isInstantenous() {return true;}
    
    @Override
    public void applyInstantenousEffect(@Nullable Entity dontCare, @Nullable Entity player, LivingEntity entity, int amp, double health) {
        if (!(entity instanceof AnimalEntity) || !(player instanceof PlayerEntity)) return;
        AnimalEntity animal = (AnimalEntity)entity;
        
        if (!animal.isBaby() && !animal.isInLove()) animal.setInLove((PlayerEntity)player);
    }
}
