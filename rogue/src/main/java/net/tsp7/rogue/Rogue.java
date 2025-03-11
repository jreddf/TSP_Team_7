package net.tsp7.rogue;

import net.fabricmc.api.ModInitializer;

import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.util.Identifier;
import net.tsp7.rogue.block.ModBlocks;
import net.tsp7.rogue.item.ModItemGroups;
import net.tsp7.rogue.item.ModItems;
import net.tsp7.rogue.dimension.modWorldGen;
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
		CustomPortalBuilder.beginPortal()
				.frameBlock(ModBlocks.EXAMPLE_BLOCK)
				.lightWithItem(ModItems.EXAMPLE_ITEM)
				.destDimID(new Identifier(Rogue.MOD_ID, "roguedimension"))
				.tintColor(255,10,127).
				registerPortal();
	}
}