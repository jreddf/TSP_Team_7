package net.tsp7.rogue.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tsp7.rogue.Rogue;

public class ModItems {
    // Basic item, variable name should be all caps, name within register should be all lowercase and not include any spaces
    public static final Item EXAMPLE_ITEM = registerItem("example_item", new Item(new FabricItemSettings()));

    // Method to help add to a vanilla item group
    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(EXAMPLE_ITEM);
    }

    // Helper method for creating new items. Do not modify
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Rogue.MOD_ID, name), item);
    }

    // If you want to add items to a vanilla group, create a method similar to the one above
    public static void registerModItems() {
        Rogue.LOGGER.info("Registering items for " + Rogue.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
