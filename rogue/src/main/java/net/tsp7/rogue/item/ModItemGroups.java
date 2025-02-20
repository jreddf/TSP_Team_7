package net.tsp7.rogue.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.tsp7.rogue.Rogue;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItemGroups {
    public static final ItemGroup ITEMS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Rogue.MOD_ID, "example_item"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.example_tab"))
            .icon(() -> new ItemStack(ModItems.EXAMPLE_ITEM)).entries((displayContext, entries) -> {
                entries.add(ModItems.EXAMPLE_ITEM);
    }).build());

    public static void registerItemGroups() {
        Rogue.LOGGER.info("Registering Item Groups for " + Rogue.MOD_ID);
    }
}
