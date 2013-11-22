package net.jordaria;

//The settings and configurations for various parts of the program
public class Configuration {
	public static int window_height = 480;
	public static int window_width = 640;
	public static int display_bitsPerPixel = 32;
	
	public static String window_title = "Jordaria!";

	
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
	
}
