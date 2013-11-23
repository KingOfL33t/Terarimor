package net.jordaria;

/*
 * Settings for use in the game. These eventually will be able to be set, saved and loaded
 */
public class GameSettings {

	protected Jordaria jd;

	//control keybinds
	public KeyBind KEYBIND_MOVE_FORWARD = new KeyBind("key.forward", 17);
	public KeyBind KEYBIND_MOVE_LEFT = new KeyBind("key.left", 30);
	public KeyBind KEYBIND_MOVE_BACKWARD = new KeyBind("key.back", 31);
	public KeyBind KEYBIND_MOVE_RIGHT = new KeyBind("key.right", 32);
	public KeyBind KEYBIND_MOVE_JUMP = new KeyBind("key.jump", 57);
	public KeyBind[] keyBindings;

	//booleans
	public boolean showAdvancedInfo;//for later use in showing biome info (humidity, magic, etc) on screen
	public boolean noclip;

	public int renderDistance;
	public float noclipSpeed;
	public String language = "en_US";

	public GameSettings(Jordaria jordaria)
	{
		this.keyBindings = new KeyBind[] {this.KEYBIND_MOVE_FORWARD, this.KEYBIND_MOVE_BACKWARD, this.KEYBIND_MOVE_JUMP, this.KEYBIND_MOVE_LEFT, this.KEYBIND_MOVE_RIGHT};
		this.jd = jordaria;
		this.loadDefaults();
	}
	private void loadDefaults(){
		this.showAdvancedInfo = false;
		this.noclip = false;
		this.noclipSpeed = 1.0F;
		this.language = "en_US";
		this.renderDistance = 10;
	}

}
