package com.example;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface RustModLibrary extends Library {
    RustModLibrary INSTANCE = Native.load("rustmods/rustmod", RustModLibrary.class);
    // core funcs
    void load();
    void register_item(String name);
}
