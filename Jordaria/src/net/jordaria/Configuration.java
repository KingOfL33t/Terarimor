package net.jordaria;

/*The settings and configurations for various parts of the program
 * This is so changes across the entire game can be made easily,
 * such as changing the number of blocks in a chunk,
 * without having to redo large chunks of code
 */
public class Configuration {
	//GUI settings
	public static int window_height = 480;
	public static int window_width = 640;
	public static int display_bitsPerPixel = 32;
	public static String window_title = "Jordaria!";
	
	//Chunk settings
	public static int CHUNK_SIZE = 16;
	
	//debug settings
	public static boolean debugActive = true;

	//getters for non-static versions of the variables
	public int getWindow_height(){
		return window_height;
	}
	public int getWindow_width(){
		return window_width;
	}
	public String getWindow_title(){
		return window_title;
	}
	public int getDisplay_bitsPerPixel() {
		return display_bitsPerPixel;
	}
	public int getCHUNK_SIZE() {
		return CHUNK_SIZE;
	}
	public boolean getDebugActive(){
		return debugActive;
	}
	
}
