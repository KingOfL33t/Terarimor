package net.jordaria.gui;

import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import net.jordaria.Jordaria;

public class GuiScreen extends Gui{
	/** The width of the screen object. */
    public int width;

    /** The height of the screen object. */
    public int height;

    public Jordaria jd;
    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
    	
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
    	
    }

    /**
     * Returns a string stored in the system clipboard.
     */
    public static String getClipboardString()
    {
        try
        {
            Transferable var0 = Toolkit.getDefaultToolkit().getSystemClipboard().getContents((Object)null);

            if (var0 != null && var0.isDataFlavorSupported(DataFlavor.stringFlavor))
            {
                return (String)var0.getTransferData(DataFlavor.stringFlavor);
            }
        }
        catch (Exception var1)
        {
            ;
        }

        return "";
    }

    /**
     * store a string in the system clipboard
     */
    public static void setClipboardString(String par0Str)
    {
        try
        {
            StringSelection var1 = new StringSelection(par0Str);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(var1, (ClipboardOwner)null);
        }
        catch (Exception var2)
        {
            ;
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int par1, int par2, int par3)
    {
    	
    }

    /**
     * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
     * mouseMove, which==0 or which==1 is mouseUp
     */
    protected void mouseMovedOrUp(int par1, int par2, int par3)
    {
        
    }

    /**
     * Called when a mouse button is pressed and the mouse is moved around. Parameters are : mouseX, mouseY,
     * lastButtonClicked & timeSinceMouseClick.
     */
    protected void mouseClickMove(int par1, int par2, int par3, long par4) {}


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

    public void confirmClicked(boolean par1, int par2) {}

    public static boolean isShiftKeyDown()
    {
        return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
    }

}
