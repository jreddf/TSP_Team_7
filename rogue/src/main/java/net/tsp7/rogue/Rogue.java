package net.tsp7.rogue;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tsp7.rogue.Enemy.roguebaseenemy;
import net.tsp7.rogue.block.ModBlocks;
import net.tsp7.rogue.item.ModItemGroups;
import net.tsp7.rogue.item.ModItems;
import net.tsp7.rogue.dimension.modWorldGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rogue implements ModInitializer {
	public static final String MOD_ID = "rogue";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final EntityType<roguebaseenemy> Enemy = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of("entitytesting", "rogue"),
			EntityType.Builder.create(roguebaseenemy::new, SpawnGroup.CREATURE).setDimensions(0.75f, 0.75f).build("rogue")
	);


	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		FabricDefaultAttributeRegistry.register(Enemy, roguebaseenemy.createMobAttributes());

		CustomPortalBuilder.beginPortal()
				.frameBlock(ModBlocks.EXAMPLE_BLOCK)
				.lightWithItem(ModItems.EXAMPLE_ITEM)
				.destDimID(new Identifier(Rogue.MOD_ID, "roguedimension"))
				.tintColor(255,10,127).
				registerPortal();
	}
}