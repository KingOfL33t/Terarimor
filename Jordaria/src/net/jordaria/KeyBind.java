package net.jordaria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class KeyBind {

	public static List<Object> keybindArray = new ArrayList<Object>();
	public static HashMap<Integer, Object> intHash = new HashMap<Integer, Object>();
	public String keyDescription;
	public int keyCode;

	public boolean isPressed;
	public int timePressed;

	public static void onTick(int keyValue)
	{
		KeyBind binding = (KeyBind)intHash.get(keyValue);

		if (binding != null)
		{
			++binding.timePressed;
		}
	}

	public static void setKeyBindState(int keyValue, boolean pressed)
	{
		KeyBind binding = (KeyBind)intHash.get(keyValue);

		if (binding != null)
		{
			binding.isPressed = pressed;
		}
	}

	public static void unPressAllKeys()
	{
		Iterator<Object> iter = keybindArray.iterator();

		while (iter.hasNext())
		{
			KeyBind binding = (KeyBind)iter.next();
			binding.unpressKey();
		}
	}

	public static void resetKeyBindingArrayAndHash()
	{
		intHash.clear();
		Iterator<Object> iter = keybindArray.iterator();

		while (iter.hasNext())
		{
			KeyBind binding = (KeyBind)iter.next();
			intHash.put(binding.keyCode, binding);
		}
	}

	public KeyBind(String description, int code)
	{
		this.keyDescription = description;
		this.keyCode = code;
		keybindArray.add(this);
		intHash.put(code, this);
	}

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

	private void unpressKey()
	{
		this.timePressed = 0;
		this.isPressed = false;
	}
}
