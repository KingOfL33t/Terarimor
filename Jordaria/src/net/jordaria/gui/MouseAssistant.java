package net.jordaria.gui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class MouseAssistant {
	public int deltaX;
	public int deltaY;
	
	public void grabMouseCursor()
    {
        Mouse.setGrabbed(true);
        this.deltaX = 0;
        this.deltaY = 0;
    }
	
	public void releaseMouseCursor()
    {
        Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
        Mouse.setGrabbed(false);
    }

    public void mouseXYChange()
    {
        this.deltaX = Mouse.getDX();
        this.deltaY = Mouse.getDY();
    }

}
