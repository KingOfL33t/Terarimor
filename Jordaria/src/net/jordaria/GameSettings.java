package net.jordaria;

import org.lwjgl.input.Keyboard;

/*
 * Settings for use in the game. These eventually will be able to be set, saved and loaded
 */
public class GameSettings {

	protected Jordaria jd;

	//control keybinds
	public KeyBind KEYBIND_MOVE_FORWARD = new KeyBind("Forward", Keyboard.KEY_W);
	public KeyBind KEYBIND_MOVE_LEFT = new KeyBind("Left", Keyboard.KEY_A);
	public KeyBind KEYBIND_MOVE_BACKWARD = new KeyBind("Back", Keyboard.KEY_S);
	public KeyBind KEYBIND_MOVE_RIGHT = new KeyBind("Right", Keyboard.KEY_D);
	public KeyBind[] keyBindings;

	//floats
	public boolean showAdvancedInfo;//for later use in showing biome info (humidity, magic, etc) on screen
	public boolean wireframe;

	public int renderDistance;
	public int maxDungeonSize;

	public float mouseSensitivity = 0.5F;

	public String OS;
	public String homeDirectory;

	public GameSettings(Jordaria jordaria)
	{
		this.keyBindings = new KeyBind[] {this.KEYBIND_MOVE_FORWARD, this.KEYBIND_MOVE_BACKWARD, this.KEYBIND_MOVE_LEFT, this.KEYBIND_MOVE_RIGHT};
		this.jd = jordaria;
		this.loadDefaults();
	}
	private void loadDefaults(){
		this.obtainOS();
		this.setHomeDir();
		this.showAdvancedInfo = false;
		this.renderDistance = 10;
		this.wireframe = false;
		this.maxDungeonSize = 100;
	}
	public KeyBind[] getKeyBindings() {
		return keyBindings;
	}
	public boolean isShowAdvancedInfo() {
		return showAdvancedInfo;
	}

	public boolean isWireframe() {
		return wireframe;
	}
	public boolean toggleWireframe(){//toggles wireframe and returns the new value
		wireframe = !wireframe;
		return wireframe;
	}
	public int getRenderDistance() {
		return renderDistance;
	}
	public int getMaxDungeonSize(){
		return this.maxDungeonSize;
	}
	public String getHomeDirectory(){
		return this.homeDirectory;
	}
	public float getMouseSensitivity() {
		return mouseSensitivity;
	}
	public String getOS() {
		return OS;
	}
	private void obtainOS(){
		String osName = System.getProperty("os.name").toLowerCase();
		String toSet = "UNKNOWN";

		if (osName.contains("win")){
			toSet = "WINDOWS";
		}
		else if (osName.contains("mac")){
			toSet = "MAC";
		}
		else if (osName.contains("linux") || osName.contains("unix")){
			toSet = "LINUX";
		}
		else{
			toSet = "UNKNOWN";
		}
		this.OS = toSet;
	}
	private void setHomeDir(){
		String theDir = "";
		if (OS == "WINDOWS"){
			theDir = System.getenv("APPDATA");
		}
		else if (OS == "MAC"){
			theDir = System.getProperty("user.home") + "/Library/Application Support";
		}
		else if (OS == "LINUX"){
			theDir = System.getProperty("user.home");
		}
		else{
			theDir = System.getProperty("user.dir");
		}
		this.homeDirectory = theDir;
	}


}
