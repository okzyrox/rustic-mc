package io.rust;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface RustModLibrary extends Library {
    RustModLibrary INSTANCE = Native.load("rmods/rustmod", RustModLibrary.class);
    // core funcs
    void load();
    void register_item(String name);

    Pointer get_items_list();
    void free_items_list(Pointer items);
}
