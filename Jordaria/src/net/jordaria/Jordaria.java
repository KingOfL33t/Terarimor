package net.jordaria;

import java.nio.FloatBuffer;

import net.jordaria.entity.Entity;
import net.jordaria.entity.EntityLiving;
import net.jordaria.entity.EntityPlayer;
import net.jordaria.entity.NameGenerator;
import net.jordaria.entity.PlayerMoveHelper;
import net.jordaria.gui.GuiIngame;
import net.jordaria.gui.GuiScreen;
import net.jordaria.gui.MouseAssistant;
import net.jordaria.world.Chunk;
import net.jordaria.world.World;

import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.glu.GLU;


public class Jordaria implements Runnable{

	public boolean running;//A boolean used to show whether or not the game is running -King
	public static Configuration config;
	public GameSettings gameSettings;
	public Thread thread;

	private boolean isPaused;


	public GuiIngame ingameGui;
	public GuiScreen currentScreen;
	DisplayMode displayMode;
	int VBOColorHandle;
	int VBOVertexHandle;
	public int displayWidth;
	public int displayHeight;
	private long systemTime;

	public MouseAssistant mouseHelper;

	//whether or not the actual game has the focus or a menu does
	public boolean inGameHasFocus;

	public World theWorld;
	public EntityLiving renderViewEntity;
	public EntityPlayer thePlayer;

	public static void main(String args[]){
		config = new Configuration();

		Jordaria game = new Jordaria();
		game.start();
	}

	public void start(){
		if (this.running)
		{
			return;//if the game is already in progress, dont start it again -King
		}
		this.running = true;
		try{
			gameSettings = new GameSettings(this);
			ingameGui= new GuiIngame(this);
			createWindow();
			InitGL();
			theWorld = new World("Test");
			NameGenerator namegen = new NameGenerator();
			thePlayer = new EntityPlayer(theWorld, namegen.getRandomName());
			run();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	public void run(){

		while (this.running && !Display.isCloseRequested()){

			try{
				Render();
				Display.update();
				Display.sync(60);

			}
			catch(Exception e){
				;
			}
		}
		Display.destroy();
	}

	public void createWindow() throws Exception{
		Display.setFullscreen(false);
		DisplayMode d[] = Display.getAvailableDisplayModes();
		for (int i = 0; i < d.length; i++){
			if (d[i].getWidth() == config.getWindow_width()
					&& d[i].getHeight() == config.getWindow_height() 
					&& d[i].getBitsPerPixel() == config.getDisplay_bitsPerPixel()){
				displayMode = d[i];
				break;
			}
		}
		Display.setDisplayMode(displayMode);
		Display.setTitle(config.getWindow_title());
		Display.create();
	}

	private void InitGL(){
		GL11.glEnable(GL11.GL_TEXTURE_2D);//enable mapping textures to faces or quads
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
		GL11.glShadeModel(GL11.GL_SMOOTH);//makes surfaces prettier :3
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);//Sets the background color
		GL11.glClearDepth(1.0);//the depth used when the depth buffer is cleared
		GL11.glEnable(GL11.GL_DEPTH_TEST);//allows depth testing, makes rendering more efficient
		GL11.glDepthFunc(GL11.GL_LEQUAL);//what kind of depth testing to use

		GL11.glMatrixMode(GL11.GL_PROJECTION);//modify pixel projection matrix
		GL11.glLoadIdentity();

		//set up the camera
		GLU.gluPerspective(45.0f, (float)displayMode.getWidth()/ (float)displayMode.getHeight(), 0.1f, 10000.0f);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);//modify the orientation and location matrix
		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
	}

	private void Render(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();

		GL11.glTranslatef(-3f,0.0f,-20f);             
		GL11.glRotatef(45f,0.4f,1.0f,0.1f);               
		GL11.glColor3f(0.5f,0.5f,1.0f);  
		for (int x = 0; x<3; x++){
			for (int y = 0; y<3; y++){
				for (int z = 0; z<3; z++){
					ingameGui.RenderCube();
					GL11.glTranslatef(0.0f, 0.0f, 2f);
				}
				GL11.glTranslatef(0.0f, 2f, -6f);
			}
			GL11.glTranslatef(2f, -6f, 0.0f);
		}

	}

	private void CreateVBO() {
		VBOColorHandle=GL15.glGenBuffers();
		VBOVertexHandle=GL15.glGenBuffers();
		FloatBuffer VertexPositionData = BufferUtils.createFloatBuffer(4*3);
		VertexPositionData.put(new float[]{0.0f, 0.0f, 0.0f, 0f, 1f, 0f,1.0f, 1.0f, 0.0f,1.0f, 0f, 0f});
		VertexPositionData.flip();
		FloatBuffer VertexColorData = BufferUtils.createFloatBuffer(4*3);
		VertexColorData.put(new float[]{1,1,1,1,1,1,1,1,1,0,1,1});
		VertexColorData.flip();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,VBOVertexHandle);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, VertexPositionData, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,VBOColorHandle);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, VertexColorData, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,0);
	}
	private void DrawVBO() {
		GL11.glPushMatrix();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,VBOVertexHandle);
		GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0L);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,VBOColorHandle);
		GL11.glColorPointer(3, GL11.GL_FLOAT, 0, 0L);
		GL11.glDrawArrays(GL11.GL_LINE_LOOP, 0, 4);
		GL11.glPopMatrix();
	}
	public void runTick(){
		int mouseButton;
		while (Mouse.next())
		{
			mouseButton = Mouse.getEventButton();

			KeyBind.setKeyBindState(mouseButton - 100, Mouse.getEventButtonState());

			if (Mouse.getEventButtonState())
			{
				KeyBind.onTick(mouseButton - 100);
			}

			if (this.currentScreen == null)
			{
				if (!this.inGameHasFocus && Mouse.getEventButtonState())
				{
					this.setIngameFocus();
				}
			}
			else if (this.currentScreen != null)
			{
				this.currentScreen.handleMouseInput();
			}
		}


		while (Keyboard.next())
		{
			KeyBind.setKeyBindState(Keyboard.getEventKey(), Keyboard.getEventKeyState());

			if (Keyboard.getEventKeyState())
			{
				KeyBind.onTick(Keyboard.getEventKey());
			}
		}

		if (!this.isPaused)
		{
			this.theWorld.updateEntities();
		}

	}

	public void setIngameFocus()
	{
		if (Display.isActive())
		{
			if (!this.inGameHasFocus)
			{
				this.inGameHasFocus = true;
				this.mouseHelper.grabMouseCursor();
				this.displayGuiScreen((GuiScreen)null);
			}
		}
	}

	/**
	 * Resets the player keystate, disables the ingame focus, and ungrabs the mouse cursor.
	 */
	public void setIngameNotInFocus()
	{
		if (this.inGameHasFocus)
		{
			KeyBind.unPressAllKeys();
			this.inGameHasFocus = false;
			this.mouseHelper.releaseMouseCursor();
		}
	}

	private void resize(int newX, int newY)
	{
		this.displayWidth = newX <= 0 ? 1 : newX;
		this.displayHeight = newY <= 0 ? 1 : newY;

		if (this.currentScreen != null)
		{
			this.currentScreen.setContainerAndResolution(this, this.displayWidth, this.displayHeight);
		}
	}

	public void displayGuiScreen(GuiScreen par1GuiScreen)
	{
		if (this.currentScreen != null)
		{
			this.currentScreen.onGuiClosed();
		}

		this.currentScreen = (GuiScreen)par1GuiScreen;

		if (par1GuiScreen != null)
		{
			this.setIngameNotInFocus();
			((GuiScreen)par1GuiScreen).setContainerAndResolution(this, this.displayWidth, this.displayHeight);
		}
		else
		{
			this.setIngameFocus();
		}
	}

	public void loadWorld()
	{

		this.renderViewEntity = null;

		this.thePlayer.preparePlayerToSpawn();
		this.theWorld.spawnEntityInWorld(this.thePlayer);
		this.thePlayer.movementInput = new PlayerMoveHelper(this.gameSettings);
		this.renderViewEntity = this.thePlayer;


		System.gc();
		this.systemTime = 0L;
	}

	public static long getSystemTime()
	{
		return Sys.getTime() * 1000L / Sys.getTimerResolution();
	}

}
