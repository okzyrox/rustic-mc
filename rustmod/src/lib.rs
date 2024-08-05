use std::ffi::CStr;
use std::os::raw::c_char;
use log::{info, LevelFilter};
use simple_logging;



#[no_mangle]
pub extern "C" fn load() {
    let _logger = simple_logging::log_to_file("rustmod.log", LevelFilter::Info);
    info!("Loading rust mod (rustmod)");
    
    info!("Loading rust mod (rustmod)");
}

#[no_mangle]
pub extern "C" fn register_item(name: *const c_char) {
    let c_str = unsafe { CStr::from_ptr(name) };
    let r_str = c_str.to_str().unwrap();
    info!("Registering item: {}", r_str);
}