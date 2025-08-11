package net.gorb.supermod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.gorb.supermod.block.ModBLocks;
import net.gorb.supermod.block.entity.ModBlockEntities;
import net.gorb.supermod.entity.ModEntities;
import net.gorb.supermod.entity.custom.BowlingBallEntity;
import net.gorb.supermod.entity.custom.EmreSahinEntity;
import net.gorb.supermod.entity.custom.PinEntity;
import net.gorb.supermod.item.ModItemGroups;
import net.gorb.supermod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SuperMod implements ModInitializer {
	public static final String MOD_ID = "supermod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBLocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
		ModEntities.registerModEntities();
		ModBlockEntities.registerBlockEntities();

		FabricDefaultAttributeRegistry.register(ModEntities.EMRE_SAHIN_ENTITY, EmreSahinEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.BOWLING_BALL_ENTITY, BowlingBallEntity.createAttributes()); // <-- ADD THIS LINE
		FabricDefaultAttributeRegistry.register(ModEntities.PIN_ENTITY, PinEntity.createPinAttributes());
	}
}