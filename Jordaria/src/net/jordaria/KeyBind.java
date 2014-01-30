package net.jordaria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Key bindings which handle state of pressed and time pressed.
 */
public class KeyBind {

	public static List<Object> keybindArray = new ArrayList<Object>();
	public static HashMap<Integer, Object> intHash = new HashMap<Integer, Object>();
	public String keyDescription;
	public int keyCode;

	public boolean isPressed;
	public int timePressed;

	/**
	 * Increases key binding time for bindings that match the given key.
	 * 
	 * @param keyValue The value of the key pressed
	 */
	public static void onTick(int keyValue)
	{
		KeyBind binding = (KeyBind)intHash.get(keyValue);

		if (binding != null)
		{
			++binding.timePressed;
		}
	}

	/**
	 * Sets the state of the given key to the given state.
	 * 
	 * @param keyValue The value of the key pressed
	 * @param pressed True if the key is pressed, false otherwise
	 */
	public static void setKeyBindState(int keyValue, boolean pressed)
	{
		KeyBind binding = (KeyBind)intHash.get(keyValue);

		if (binding != null)
		{
			binding.isPressed = pressed;
		}
	}

	/**
	 * Sets all keys pressed states to false.
	 */
	public static void unPressAllKeys()
	{
		Iterator<Object> iter = keybindArray.iterator();

		while (iter.hasNext())
		{
			KeyBind binding = (KeyBind)iter.next();
			binding.unpressKey();
		}
	}

	/**
	 * Clears out the keybind HashMap.
	 */
	public static void resetKeyBindingHash()
	{
		intHash.clear();
		Iterator<Object> iter = keybindArray.iterator();

		while (iter.hasNext())
		{
			KeyBind binding = (KeyBind)iter.next();
			intHash.put(binding.keyCode, binding);
		}
	}

	/**
	 * Sets up a new keybind for the given key and code.
	 * The code value is found in org.lwjgl.input.Keyboard.
	 * 
	 * @param description A description of the keybinding
	 * @param code The key code for the key
	 */
	public KeyBind(String description, int code)
	{
		this.keyDescription = description;
		this.keyCode = code;
		keybindArray.add(this);
		intHash.put(code, this);
	}

	/**
	 * Returns the pressed state of the key binding.
	 * 
	 * @return True if the key is pressed, false otherwise
	 */
	public boolean isPressed()
	{
		if (this.timePressed == 0)
		{
			return false;
		}
		else
		{
			--this.timePressed;
			return true;
		}
	}

	/**
	 * Sets the key pressed state to false and resets the time pressed to zero.
	 */
	private void unpressKey()
	{
		this.timePressed = 0;
		this.isPressed = false;
	}
}
