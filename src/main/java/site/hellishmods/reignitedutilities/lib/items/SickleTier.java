package site.hellishmods.reignitedutilities.lib.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public enum SickleTier implements IItemTier {

    WOOD(95, 1f, 5.5f),
    STONE(186, 1.1f, 6.5f),
    IRON(275, 1f, 7.5f),
    GOLD(68, 1.1f, 5.5f),
    DIAMOND(1717, 1.3f, 8.5f),
    NETHERITE(2234, 1.4f, 9.5f);

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;

    SickleTier(int maxUses, float efficiency, float attackDamage) {
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;

        this.harvestLevel = 0;
        this.enchantability = 10;
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