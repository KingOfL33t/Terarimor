package net.jordaria.text;

public enum Color {
	RED(255,0,0),
	LIME(0,255,0),
	BLUE(0,0,255),
	MAGENTA(255,0,255),
	YELLOW(255,255,0),
	CYAN(0,255,255),
	MAROON(128,0,0),
	SALMON(250,128,114),
	ORANGE(255,165,0),
	OLIVE(128,128,0),
	GREEN(0,128,128),
	TEAL(0,128,128),
	PURPLE(128,0,128),
	PINK(255,20,147),
	BROWN(139,69,19),
	BLACK(0,0,0),
	DARK_GREY(128,128,128),
	LIGHT_GRAY(211,211,211),
	WHITE(255,255,255);
	
	private int r;
	private int g;
	private int b;
	
	private Color(int red, int green, int blue){
		this.r = red;
		this.g = green;
		this.b = blue;
	}
}
