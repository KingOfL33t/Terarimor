package net.jordaria;


/**
 * Settings for use in the game. 
 * 
 * @author Ches Burks
 */
public class GameSettings {

	protected Jordaria jd;

	//control keybinds
	public KeyBind KEYBIND_MOVE_FORWARD = new KeyBind("Forward", AsciiValue.W_UPPERCASE.value());
	public KeyBind KEYBIND_MOVE_LEFT = new KeyBind("Left", AsciiValue.A_UPPERCASE.value());
	public KeyBind KEYBIND_MOVE_BACKWARD = new KeyBind("Back", AsciiValue.S_UPPERCASE.value());
	public KeyBind KEYBIND_MOVE_RIGHT = new KeyBind("Right", AsciiValue.D_UPPERCASE.value());
	public KeyBind[] keyBindings;

	public int maxDungeonSize;

	public String OS;
	public String homeDirectory;

	/**
	 * Constructs a new GameSettings and loads default settings.
	 * 
	 * @param jordaria A reference to the main program
	 */
	public GameSettings(Jordaria jordaria)
	{
		this.keyBindings = new KeyBind[] {this.KEYBIND_MOVE_FORWARD, this.KEYBIND_MOVE_BACKWARD, this.KEYBIND_MOVE_LEFT, this.KEYBIND_MOVE_RIGHT};
		this.jd = jordaria;
		this.loadDefaults();
	}
	
	/**
	 * Loads default settings and obtains system info.
	 */
	private void loadDefaults(){
		this.obtainOS();
		this.setHomeDir();
		this.maxDungeonSize = 100;
	}
	
	/**
	 * Returns the key binding array.
	 * 
	 * @return The keybinding array
	 */
	public KeyBind[] getKeyBindings() {
		return keyBindings;
	}

	/**
	 * Returns the maximum size of a dungeon.
	 * 
	 * @return The max size of a dungeon.
	 */
	public int getMaxDungeonSize(){
		return this.maxDungeonSize;
	}
	
	/**
	 * Returns the path of the directory for application data.
	 * 
	 * @return The path of the directory for app data
	 */
	public String getHomeDirectory(){
		return this.homeDirectory;
	}
	
	/**
	 * Returns the OS name.
	 * Possible OS names are:
	 * <ul>
	 * <li>WINDOWS</li>
	 * <li>MAC</li>
	 * <li>LINUX</li>
	 * <li>UNKNOWN</li>
	 * </ul>
	 * 
	 * @return The name of the OS
	 */
	public String getOS() {
		return OS;
	}
	
	/**
	 * Determines what operating system is running and sets the OS string.
	 */
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
	
	/**
	 * Determines the application data based on the OS type.
	 * Sets the home dir string to the folder path.
	 */
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
