package net.jordaria;

/**The settings and configurations for various parts of the program
 * This is so changes across the entire game can be made quickly and easily
 * with minimal recompiling. 
 * 
 * @author Ches Burks
 */
public class Configuration {
	//GUI settings
	public static int window_height = 480;
	public static int window_width = 640;
	public static int display_bitsPerPixel = 32;
	public static String window_title = "Jordaria!";
	
	//Map settings
	public static int tile_size = 64;
	
	//debug settings
	public static boolean debugActive = true;
	public static boolean DEBUG_SHOW_ENTITYMOVE = false;//movements of entities (will be a lot of events)
	public static boolean DEBUG_SHOW_KEYPRESSES = false;//keys pressed in the gui
	public static boolean DEBUG_SHOW_STARTINGSYSTEMS = true;//game systems starting such as physics

	//getters for non-static versions of the variables
	/**
	 * Returns the window height in pixels.
	 * 
	 * @return The window height in pixels
	 */
	public int getWindow_height(){
		return window_height;
	}
	
	/**
	 * Returns the window width in pixels.
	 * 
	 * @return The window height in pixels
	 */
	public int getWindow_width(){
		return window_width;
	}
	
	/**
	 * Returns the window title for the GUI.
	 * 
	 * @return The window title
	 */
	public String getWindow_title(){
		return window_title;
	}
	
	/**
	 * Returns the display bits per pixel for OpenGL.
	 * 
	 * @return The display bits per pixel for OpenGL
	 */
	public int getDisplay_bitsPerPixel() {
		return display_bitsPerPixel;
	}
	
	/**
	 * Returns the size of a tile.
	 * 
	 * @return The size of a tile
	 */
	public int getTile_Size(){
		return tile_size;
	}
	
	/**
	 * Returns true if debug is active, otherwise false.
	 * 
	 * @return True if debug is active, otherwise false.
	 */
	public boolean getDebugActive(){
		return debugActive;
	}
	
	/**
	 * Returns true if the debug window should show 
	 * entityMove events, otherwise false.
	 * 
	 * @return True if the debug window should show entityMove events, otherwise false
	 */
	public boolean getDEBUG_SHOW_ENTITYMOVE() {
		return DEBUG_SHOW_ENTITYMOVE;
	}
	
	/**
	 * Returns true if the debug window should show 
	 * key presses, otherwise false.
	 * 
	 * @return True if the debug window should show key presses, otherwise false
	 */
	public boolean getDEBUG_SHOW_KEYPRESSES() {
		return DEBUG_SHOW_KEYPRESSES;
	}
	
	/**
	 * Returns true if the debug window should show 
	 * subsystems starting, otherwise false.
	 * 
	 * @return True if the debug window should show subsystems starting, otherwise false
	 */
	public boolean getDEBUG_SHOW_STARTINGSYSTEMS() {
		return DEBUG_SHOW_STARTINGSYSTEMS;
	}
	
}
