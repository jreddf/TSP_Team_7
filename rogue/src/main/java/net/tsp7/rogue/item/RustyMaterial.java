package net.tsp7.rogue.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class RustyMaterial implements ArmorMaterial {

    @Override
    public int getDurability(ArmorItem.Type type) {
        return switch(type) {
            case HELMET -> 95;
            case CHESTPLATE -> 150;
            case LEGGINGS -> 130;
            case BOOTS -> 105;
        };
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return switch(type) {
            case CHESTPLATE -> 3;
            case LEGGINGS -> 2;
            case BOOTS, HELMET -> 1;
            default -> 0;
        };
    }

    @Override
    public int getEnchantability() {
        return 1;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    @Override
    public String getName() {
        return "rusty";
    }

    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
