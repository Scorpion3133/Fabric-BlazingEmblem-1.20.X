package net.scorpion.blazingemblem;

import net.fabricmc.api.ModInitializer;

import net.scorpion.blazingemblem.item.ModItemGroups;
import net.scorpion.blazingemblem.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlazingEmblem implements ModInitializer {
	public static final String MOD_ID = "blazingemblem";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
	}
}