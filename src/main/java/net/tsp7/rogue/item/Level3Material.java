package net.tsp7.rogue.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class Level3Material implements ToolMaterial {

    @Override
    public int getDurability() {
        return 9999;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 12.0f;
    }

    @Override
    public float getAttackDamage() {
        return 10.5f;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public int getEnchantability() {
        return 30;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}
