package net.tsp7.rogue;

import net.fabricmc.api.ModInitializer;

import net.tsp7.rogue.block.ModBlocks;
import net.tsp7.rogue.item.ModItemGroups;
import net.tsp7.rogue.item.ModItems;
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
	}
}