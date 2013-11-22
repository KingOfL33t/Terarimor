package net.jordaria;

//The settings and configurations for various parts of the program
public class Configuration {
	public static int window_height = 480;
	public static int window_width = 640;
	public static String window_title = "Jordaria!";
	public static int default_display_height = 480;
	public static int default_display_width = 640;
	public static int default_display_bitsPerPixel = 32;
	
	public int getWindow_height(){
		return window_height;
	}
	public int getWindow_width(){
		return window_width;
	}
	public String getWindow_title(){
		return window_title;
	}
	public int getDefault_display_height() {
		return default_display_height;
	}
	public int getDefault_display_width() {
		return default_display_width;
	}
	public int getDefault_display_bitsPerPixel() {
		return default_display_bitsPerPixel;
	}
	
}
