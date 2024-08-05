package com.example;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		LOGGER.info("Loading rust lib");

		RustModLibrary.INSTANCE.load();

		LOGGER.info("Loaded rust lib (rustmods/rustmod)");

		// register item TEST
		// ideally we get the item data from the function
		// then use that to create the item

		//Identifier id = new Identifier("example", "rust_item");
        RustModLibrary.INSTANCE.register_item("rust_item");
        
        //Item item = new Item(new Item.Settings());
        //Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "rust_item"),
	}
}