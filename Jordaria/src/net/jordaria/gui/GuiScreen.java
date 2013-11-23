package net.jordaria.gui;

import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import net.jordaria.Jordaria;

public class GuiScreen extends Gui{
	/** The width of the screen object. */
    public int width;

    /** The height of the screen object. */
    public int height;

    public Jordaria jd;


    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char charTyped, int key)
    {
    	
    }

    /**
     * Returns a string stored in the system clipboard.
     */
    public static String getClipboardString()
    {
        try
        {
            Transferable trans = Toolkit.getDefaultToolkit().getSystemClipboard().getContents((Object)null);

            if (trans != null && trans.isDataFlavorSupported(DataFlavor.stringFlavor))
            {
                return (String)trans.getTransferData(DataFlavor.stringFlavor);
            }
        }
        catch (Exception e)
        {
            ;
        }

        return "";
    }

    /**
     * store a string in the system clipboard
     */
    public static void setClipboardString(String newString)
    {
        try
        {
            StringSelection selection = new StringSelection(newString);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, (ClipboardOwner)null);
        }
        catch (Exception e)
        {
            ;
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int mouseX, int mouseY)
    {
    	
    }



    /**
     * Called when a mouse button is pressed and the mouse is moved around. Parameters are : mouseX, mouseY,
     * lastButtonClicked & timeSinceMouseClick.
     */
    protected void mouseClickMove(int mouseX, int mouseY, int lastButtonClicked, long timeSinceMouseClick) {}


    /**
     * Causes the screen to lay out its subcomponents again. This is the equivalent of the Java call
     * Container.validate()
     */
    public void setContainerAndResolution(Jordaria jordaria, int width, int height)
    {
        this.jd = jordaria;
        this.width = width;
        this.height = height;
        this.initGui();
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui() {}

    /**
     * Delegates mouse and keyboard input.
     */
    public void handleInput()
    {
        while (Mouse.next())
        {
            this.handleMouseInput();
        }

        while (Keyboard.next())
        {
            this.handleKeyboardInput();
        }
    }

    /**
     * Handles mouse input.
     */
    public void handleMouseInput()
    {
        
    }

    /**
     * Handles keyboard input.
     */
    public void handleKeyboardInput()
    {
        if (Keyboard.getEventKeyState())
        {
            int eventKey = Keyboard.getEventKey();
            char eventKeyCharacter = Keyboard.getEventCharacter();

            this.keyTyped(eventKeyCharacter, eventKey);
        }
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen() {}

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed() {}


    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return true;
    }


    public static boolean isShiftKeyDown()
    {
        return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
    }

}
