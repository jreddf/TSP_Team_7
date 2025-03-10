package net.tsp7.rogue.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class Level2Material implements ToolMaterial {
    @Override
    public int getDurability() {
        return 999;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 8.0f;
    }

    @Override
    public float getAttackDamage() {
        return 6.5f;
    }

    @Override
    public int getMiningLevel() {
        return 2;
    }

    @Override
    public int getEnchantability() {
        return 20;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}
