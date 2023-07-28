package site.hellishmods.reignitedutilities.lib.effects;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.FoodStats;

public class PurgingEffect extends Effect {
    public PurgingEffect(EffectType effectType, int color) {
        super(effectType, color);
    }
    
    @Override
    public boolean isInstantenous() {return true;}
    
    @Override
    public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity anotherSourceLmao, LivingEntity receiver, int amp, double health) {
        receiver.removeAllEffects();
        
        if (!(receiver instanceof PlayerEntity)) return;
        FoodStats food = ((PlayerEntity)receiver).getFoodData();

        food.setSaturation(0);
        if (receiver==source) food.setFoodLevel(0);
        else food.setFoodLevel(food.getFoodLevel()/2);
    }
}
