package net.tsp7.rogue.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.tsp7.rogue.Rogue;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tsp7.rogue.block.ModBlocks;


public class ModItemGroups {
    // Adds items into custom creative mode tab. Do not modify unless adding/removing an item
    public static final ItemGroup ITEMS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Rogue.MOD_ID, "example_tab"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.example_tab"))
            .icon(() -> new ItemStack(ModItems.EXAMPLE_ITEM)).entries((displayContext, entries) -> {

                // Actual entries in the tab, the order they are listed is the order they appear.
                entries.add(ModItems.EXAMPLE_ITEM);
                entries.add(ModItems.LVL1_SWORD);
                entries.add(ModItems.LVL2_SWORD);
                entries.add(ModItems.LVL3_SWORD);
                entries.add(ModBlocks.EXAMPLE_BLOCK);
                entries.add(ModBlocks.PINE_LOG_BLOCK);
    }).build());

    // Helper log
    public static void registerItemGroups() {
        Rogue.LOGGER.info("Registering Item Groups for " + Rogue.MOD_ID);
    }
}
