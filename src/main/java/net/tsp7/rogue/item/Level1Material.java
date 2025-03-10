package net.tsp7.rogue.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class Level1Material implements ToolMaterial {
    @Override
    public int getDurability() {
        return 99;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 4.0f;
    }

    @Override
    public float getAttackDamage() {
        return 3.5f;
    }

    @Override
    public int getMiningLevel() {
        return 1;
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}
