package net.tsp7.rogue.recipe;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tsp7.rogue.Rogue;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Rogue.MOD_ID, UpgradeTableRecipe.Serializer.ID),
                UpgradeTableRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(Rogue.MOD_ID, UpgradeTableRecipe.Type.ID),
                UpgradeTableRecipe.Type.INSTANCE);
    }
}
