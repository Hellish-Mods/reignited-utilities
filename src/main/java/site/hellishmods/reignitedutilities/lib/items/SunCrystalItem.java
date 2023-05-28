package site.hellishmods.reignitedutilities.lib.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import site.hellishmods.reignitedutilities.reignitedutilities;

public class SunCrystalItem extends Item {
    public SunCrystalItem() {
        super(new Item.Properties().tab(reignitedutilities.TAB).stacksTo(1).defaultDurability(250));
    }

    @Override
    public void onCraftedBy(ItemStack stack, World world, PlayerEntity entity) {
        stack.setDamageValue(stack.getMaxDamage());
    }

    private class SunCrystalItemEntity extends ItemEntity {
        ItemStack stack;
        World world;

        public SunCrystalItemEntity(World world, Entity location, ItemStack stack) {
            super(world, location.position().x, location.position().y, location.position().z, stack);

            this.setPickUpDelay(10);
            lerpMotion(location.getDeltaMovement().x, location.getDeltaMovement().y, location.getDeltaMovement().z);

            this.stack = stack;
            this.world = world;
        }

        @Override
        public void tick() {
            super.tick();
            if(world.isDay() && !world.isRaining() && world.canSeeSky(new BlockPos(position()))) stack.setDamageValue(stack.getDamageValue()-1);
        }
    }
    
    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }
    @Override
    public Entity createEntity(World world, Entity location, ItemStack stack) {
        return new SunCrystalItemEntity(world, location, stack);
    }
}
