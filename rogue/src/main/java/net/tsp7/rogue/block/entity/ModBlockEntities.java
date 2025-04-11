package net.tsp7.rogue.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tsp7.rogue.Rogue;
import net.tsp7.rogue.block.ModBlocks;


public class ModBlockEntities {
    public static final BlockEntityType<UpgradeTableBlockEntity> UPGRADE_TABLE_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Rogue.MOD_ID, "upgrade_table_be"),
                    FabricBlockEntityTypeBuilder.create(UpgradeTableBlockEntity::new,
                            ModBlocks.UPGRADE_TABLE).build());


    public static void registerBlockEntities() {
        Rogue.LOGGER.info("Registering Block Entities for " + Rogue.MOD_ID);
    }
}

