package com.example;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface RustModLibrary extends Library {
    RustModLibrary INSTANCE = Native.load("rustmod", RustLibrary.class);
    // add the funcs
    void hello_world();
}
