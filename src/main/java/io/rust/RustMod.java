package io.rust;

import net.fabricmc.api.ModInitializer;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RustMod implements ModInitializer {
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
		Pointer itemsListPointer = RustModLibrary.INSTANCE.get_items_list();
		int index = 0;
        while (true) {
            Pointer itemPointer = itemsListPointer.getPointer(index);
            if (itemPointer == Pointer.NULL) {
                break;
            }
            String str = itemPointer.getString(0);
            LOGGER.info("(rustmod) - registering item: " + str);
			// register once i learn java
			RustModLibrary.INSTANCE.register_item(str);
            index++;

        }

		RustModLibrary.INSTANCE.free_items_list(itemsListPointer);
	}
}