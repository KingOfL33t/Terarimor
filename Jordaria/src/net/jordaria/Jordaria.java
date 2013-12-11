package net.jordaria;

import java.util.HashMap;

import net.jordaria.debug.DebugConsole;
import net.jordaria.entity.Direction;
import net.jordaria.entity.EntityLiving;
import net.jordaria.entity.EntityPlayer;
import net.jordaria.entity.NameGenerator;
import net.jordaria.event.DebugMessage;
import net.jordaria.event.EntityMoveRequest;
import net.jordaria.event.Error;
import net.jordaria.event.EventManager;
import net.jordaria.event.EventSystemStarted;
import net.jordaria.event.GraphicsSystemStarted;
import net.jordaria.math.Random;
import net.jordaria.world.World;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.input.Keyboard;


public class Jordaria implements Runnable{

	public boolean running;//A boolean used to show whether or not the game is running -King
	public static Configuration config;
	public GameSettings gameSettings;
	public Thread thread;

	DisplayMode displayMode;

	public int displayWidth;
	public int displayHeight;

	DebugConsole console;

	EventManager eventManager;
	
	public FileIO fileIO;
	
	public Localization localization;

	//whether or not the actual game has the focus or a menu does
	public boolean inGameHasFocus;

	public World theWorld;
	public EntityLiving renderViewEntity;
	public EntityPlayer thePlayer;

	public static void main(String args[]){
		config = new Configuration();
		Jordaria game = new Jordaria();

		game.tryConsoleInit();
		game.start();
	}
	public void tryConsoleInit(){
		if (config.getDebugActive()){
			console = new DebugConsole();
		}
	}
	public void start(){
		if (this.running)
		{
			return;//if the game is already in progress, dont start it again -King
		}
		this.running = true;
		try{
			gameSettings = new GameSettings(this);
			
			initEventManager();
			
			fileIO = new FileIO(this);
			fileIO.createMainDirectories(gameSettings.homeDirectory);
			
			localization = new Localization(this);
			localization.loadLanguage();
			
			initGraphics();

			theWorld = new World("Test");
			if (config.getDebugActive())
				eventManager.fireEvent(new DebugMessage("World ("+theWorld.worldName+") created!"));

			NameGenerator namegen = new NameGenerator();
			thePlayer = new EntityPlayer(theWorld, namegen.getRandomName());
				eventManager.fireEvent(new DebugMessage("Player ("+thePlayer.getUsername()+") created!"));
				
			run();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	private void initEventManager(){
		eventManager = new EventManager();
		registerListeners();
		eventManager.fireEvent(new EventSystemStarted());
	}
	private void initGraphics(){
		try {
			createWindow();
		} catch (Exception e) {
			eventManager.fireEvent(new Error("Error creating Window"));
		}
		InitGL();
		eventManager.fireEvent(new GraphicsSystemStarted());
	}
	public void run(){
		while (this.running && !Display.isCloseRequested()){

			try{
				while (Keyboard.next()){
					KeyBind.setKeyBindState(Keyboard.getEventKey(), Keyboard.getEventKeyState());
					if (Keyboard.getEventKeyState())
					{
						KeyBind.onTick(Keyboard.getEventKey());
					}

					while (this.gameSettings.KEYBIND_MOVE_FORWARD.isPressed())
					{
						eventManager.fireEvent(new EntityMoveRequest(thePlayer, thePlayer.direction));
						if (config.getDebugActive() && config.getDEBUG_SHOW_KEYPRESSES())
							eventManager.fireEvent(new DebugMessage("Key pressed! ("+gameSettings.KEYBIND_MOVE_FORWARD.keyDescription+")"));
					}
					while (this.gameSettings.KEYBIND_MOVE_BACKWARD.isPressed())
					{
						eventManager.fireEvent(new EntityMoveRequest(thePlayer, thePlayer.direction.getRelativeDirection(new Direction(180, 0, 0))));
						if (config.getDebugActive() && config.getDEBUG_SHOW_KEYPRESSES())
							eventManager.fireEvent(new DebugMessage("Key pressed! ("+gameSettings.KEYBIND_MOVE_BACKWARD.keyDescription+")"));
					}
					while (this.gameSettings.KEYBIND_MOVE_LEFT.isPressed())
					{
						eventManager.fireEvent(new EntityMoveRequest(thePlayer, thePlayer.direction.getRelativeDirection(new Direction(90, 0, 0))));
						if (config.getDebugActive() && config.getDEBUG_SHOW_KEYPRESSES())
							eventManager.fireEvent(new DebugMessage("Key pressed! ("+gameSettings.KEYBIND_MOVE_LEFT.keyDescription+")"));
					}
					while (this.gameSettings.KEYBIND_MOVE_RIGHT.isPressed())
					{
						eventManager.fireEvent(new EntityMoveRequest(thePlayer, thePlayer.direction.getRelativeDirection(new Direction(-90, 0, 0))));
						if (config.getDebugActive() && config.getDEBUG_SHOW_KEYPRESSES())
							eventManager.fireEvent(new DebugMessage("Key pressed! ("+gameSettings.KEYBIND_MOVE_RIGHT.keyDescription+")"));
					}
					while (this.gameSettings.KEYBIND_MOVE_JUMP.isPressed())
					{
						eventManager.fireEvent(new EntityMoveRequest(thePlayer, thePlayer.direction.getRelativeDirection(new Direction(0, 90, 0))));
						if (config.getDebugActive() && config.getDEBUG_SHOW_KEYPRESSES())
							eventManager.fireEvent(new DebugMessage("Key pressed! ("+gameSettings.KEYBIND_MOVE_JUMP.keyDescription+")"));
					}
					while (this.gameSettings.KEYBIND_WIREFRAME.isPressed())
					{
						this.gameSettings.toggleWireframe();
						if (config.getDebugActive() && config.getDEBUG_SHOW_KEYPRESSES()){
							eventManager.fireEvent(new DebugMessage("Key pressed! ("+gameSettings.KEYBIND_WIREFRAME.keyDescription+")"));

						}
					}

				}
				Display.update();
				Display.sync(60);
			}
			catch(Exception e){
				break;
			}
		}
		shutdown();
		Display.destroy();
	}

	public void shutdown(){
		this.running = false;
		this.console = null;
		this.theWorld = null;
		this.thePlayer = null;
		this.renderViewEntity = null;
		this.eventManager = null;
		System.gc();
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

	//register all the listeners here
	public void registerListeners(){
		if (config.getDebugActive()){
			try {
				eventManager.registerEventListeners(console);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
