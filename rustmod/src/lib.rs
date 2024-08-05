use std::ffi::{CString, CStr};
use std::os::raw::c_char;
use std::ptr;

use log::{info, LevelFilter};
use simple_logging;



#[no_mangle]
pub extern "C" fn load() {
    let _logger = simple_logging::log_to_file("rustmod.log", LevelFilter::Info);
    info!("Loading rust mod (rustmod)");
    // do stuff here idfk
    info!("Loaded rust mod (rustmod)");
}

#[no_mangle]
pub extern "C" fn register_item(name: *const c_char) {
    // the plan is that the java code calls a function like this
    // which then returns a list of things like items and their data
    // which is then used to create the items on the fly
    // might be difficult but we'll see
    let c_str = unsafe { CStr::from_ptr(name) };
    let r_str = c_str.to_str().unwrap();
    info!("Registering item: {}", r_str);
}

#[no_mangle]
pub extern "C" fn get_items_list() -> *const *const c_char {
    let strings = vec![
        CString::new("rust_item").unwrap(),
        CString::new("rust_item2").unwrap()
    ];
    let mut c_strings: Vec<*const c_char> = strings.iter()
        .map(|s| s.as_ptr())
        .collect();
    
    c_strings.push(ptr::null());

    let c_strings_ptr = c_strings.as_ptr();
    std::mem::forget(c_strings);

    c_strings_ptr
}

pub extern "C" fn free_items_list(items: *const *const c_char) {
    info!("Freeing items list");
    if items.is_null() {
        return;
    }

    unsafe {
        let mut i = 0;
        while !(*items.add(i)).is_null() {
            CString::from_raw(*items.add(i) as *mut c_char);
            i += 1;
        }

        Vec::from_raw_parts(items as *mut *const c_char, i + 1, i + 1);
    }
}