package net.tsp7.rogue.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tsp7.rogue.Rogue;

public class ModBlocks {
    // This is the code that creates the block, use this to create your own blocks.
    // You can either copy settings from existing vanilla blocks, or modify them manually.
    // If you copy and want to modify some values make sure the copy comes first
    public static final Block EXAMPLE_BLOCK = registerBlock("example_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));


    // Everything below are helper methods for creating new blocks. Do not modify
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Rogue.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Rogue.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Rogue.LOGGER.info("Registering ModBlocks for " + Rogue.MOD_ID);
    }

    public static final Block PINE_LOG_BLOCK= registerBlock("pine_log_block",
       new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));

    public static final Block PINE_PLANKS_BLOCK= registerBlock("pine_planks_block",
       new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    
    public static final Block CRACKED_RED_STONE_BRICK = registerBlock("cracked_red_stone_bricks",
       new PillarBlock(FabricBlockSettings.copyOf(Blocks.CRACKED_STONE_BRICKS)));
}

