package net.jordaria;

import org.lwjgl.input.Keyboard;

/*
 * Settings for use in the game. These eventually will be able to be set, saved and loaded
 */
public class GameSettings {

	protected Jordaria jd;

	//control keybinds
	public KeyBind KEYBIND_MOVE_FORWARD = new KeyBind("key.forward", Keyboard.KEY_W);
	public KeyBind KEYBIND_MOVE_LEFT = new KeyBind("key.left", Keyboard.KEY_A);
	public KeyBind KEYBIND_MOVE_BACKWARD = new KeyBind("key.back", Keyboard.KEY_S);
	public KeyBind KEYBIND_MOVE_RIGHT = new KeyBind("key.right", Keyboard.KEY_D);
	public KeyBind KEYBIND_MOVE_JUMP = new KeyBind("key.jump", Keyboard.KEY_SPACE);
	public KeyBind KEYBIND_WIREFRAME = new KeyBind("key.wireframe", Keyboard.KEY_F);
	public KeyBind[] keyBindings;

	//booleans
	public boolean showAdvancedInfo;//for later use in showing biome info (humidity, magic, etc) on screen
	public boolean wireframe;

	public int renderDistance;

	public float mouseSensitivity = 0.5F;

	public GameSettings(Jordaria jordaria)
	{
		this.keyBindings = new KeyBind[] {this.KEYBIND_MOVE_FORWARD, this.KEYBIND_MOVE_BACKWARD, this.KEYBIND_MOVE_JUMP, this.KEYBIND_MOVE_LEFT, this.KEYBIND_MOVE_RIGHT};
		this.jd = jordaria;
		this.loadDefaults();
	}
	private void loadDefaults(){
		this.showAdvancedInfo = false;
		this.renderDistance = 10;
		this.wireframe = false;
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

}
