package net.tsp7.rogue.item.custom;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class MythrilMaterial implements ArmorMaterial {
    @Override
    public int getDurability(ArmorItem.Type type) {
        return switch(type) {
            case HELMET -> 2700;
            case CHESTPLATE -> 4000;
            case LEGGINGS -> 3500;
            case BOOTS -> 3000;
        };
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return switch(type) {
            case HELMET -> 5;
            case CHESTPLATE -> 12;
            case LEGGINGS -> 8;
            case BOOTS -> 6;
        };
    }

    @Override
    public int getEnchantability() {
        return 100;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    @Override
    public String getName() {
        return "mythril";
    }

    @Override
    public float getToughness() {
        return 10.0f;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.9f;
    }
}
