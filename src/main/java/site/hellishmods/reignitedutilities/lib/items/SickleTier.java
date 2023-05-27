package site.hellishmods.reignitedutilities.lib.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public enum SickleTier implements IItemTier {

    WOOD(95, 1f, 5.5f, 3),
    STONE(186, 1.1f, 6.5f, 5),
    IRON(275, 1f, 7.5f, 7),
    GOLD(68, 1.1f, 5.5f, 3),
    DIAMOND(1717, 1.3f, 8.5f, 9),
    NETHERITE(2234, 1.4f, 9.5f, 11);

    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;

    public final int diameter;

    SickleTier(int maxUses, float efficiency, float attackDamage, int diameter) {
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;

        this.diameter = diameter;
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
        return 0;
    }

    @Override
    public int getEnchantmentValue() {
        return 10;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

}