package site.hellishmods.reignitedutilities.lib.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public enum SickleTier implements IItemTier {

    IRON(0, 275, 1.2f, 7.5f, 10),
    DIAMOND(0, 1717, 1.3f, 8.5f, 10),
    NETHERITE(0, 2234, 1.4f, 9.5f, 10);


    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;

    SickleTier(int harvestLevel, int maxUses, float efficiency,
               float attackDamage, int enchantability) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
    }

    @Override
    public int getUses() {
        return maxUses;
    }

    @Override
    public float getSpeed() {
        return efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

}