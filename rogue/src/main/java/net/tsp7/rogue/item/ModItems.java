package net.tsp7.rogue.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.tsp7.rogue.Rogue;
import net.tsp7.rogue.item.custom.*;

public class ModItems {
    // Basic item, variable name should be all caps, name within register should be all lowercase and not include any spaces
    public static final Item EXAMPLE_ITEM = registerItem("example_item", new Item(new FabricItemSettings()));
    public static final Item LVL1_SWORD = registerItem("level_1_sword", new SwordItem(new Level1Material(), 2, -3.5f, new FabricItemSettings()));
    public static final Item LVL2_SWORD = registerItem("level_2_sword", new SwordItem(new Level2Material(), 2, -3.25f, new FabricItemSettings()));
    public static final Item LVL3_SWORD = registerItem("level_3_sword", new SwordItem(new Level3Material(), 2, -3f, new FabricItemSettings()));
    public static final Item RustyHelmet = registerItem("rustyhelmet", new ArmorItem(new RustyMaterial(), ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item RustyChestplate = registerItem("rustychestplate", new ArmorItem(new RustyMaterial(), ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item RustyLeggings = registerItem("rustyleggings", new ArmorItem(new RustyMaterial(), ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item RustyBoots = registerItem("rustyboots", new ArmorItem(new RustyMaterial(), ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item MythrilHelmet = registerItem("mythrilhelmet", new ArmorItem(new MythrilMaterial(), ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item MythrilChestplate = registerItem("mythrilchestplate", new ArmorItem(new MythrilMaterial(), ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item MythrilLeggings = registerItem("mythrilleggings", new ArmorItem(new MythrilMaterial(), ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item MythrilBoots = registerItem("mythrilboots", new ArmorItem(new MythrilMaterial(), ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item UPGRADE_TOKEN = registerItem("upgrade_token", new Item(new FabricItemSettings().rarity(Rarity.RARE)));


    // Method to help add to a vanilla item group
    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(EXAMPLE_ITEM);
        entries.add(LVL1_SWORD);
        entries.add(LVL2_SWORD);
        entries.add(LVL3_SWORD);
        entries.add(RustyHelmet);
        entries.add(RustyChestplate);
        entries.add(RustyLeggings);
        entries.add(RustyBoots);
        entries.add(MythrilHelmet);
        entries.add(MythrilChestplate);
        entries.add(MythrilLeggings);
        entries.add(MythrilBoots);
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
