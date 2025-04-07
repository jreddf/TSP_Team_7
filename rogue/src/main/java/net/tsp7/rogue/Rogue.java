package net.tsp7.rogue;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tsp7.rogue.block.ModBlocks;
import net.tsp7.rogue.block.entity.ModBlockEntities;
import net.tsp7.rogue.entity.ModEntities;
import net.tsp7.rogue.entity.custom.EvilGolemEntity;
import net.tsp7.rogue.entity.custom.EvilVillagerEntity;
import net.tsp7.rogue.entity.custom.EvilWanderingTrader;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.tsp7.rogue.item.ModItemGroups;
import net.tsp7.rogue.item.ModItems;
import net.tsp7.rogue.recipe.ModRecipes;
import net.tsp7.rogue.screen.ModScreenHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rogue implements ModInitializer {
	public static final String MOD_ID = "rogue";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModRecipes.registerRecipes();

		CustomPortalBuilder.beginPortal()
				.frameBlock(Blocks.REINFORCED_DEEPSLATE)
				.lightWithItem(Items.NETHERITE_SWORD)
				.destDimID(new Identifier(Rogue.MOD_ID, "roguedimension"))
				.tintColor(50,200,255).
				registerPortal();
		FabricDefaultAttributeRegistry.register(ModEntities.EVIL_GOLEM, EvilGolemEntity.createEvilGolemAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.EVIL_VILLAGER, EvilVillagerEntity.createEvilVillagerAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.EVIL_TRADER, EvilWanderingTrader.createEvilWanderingTraderAttributes());
	}
}
