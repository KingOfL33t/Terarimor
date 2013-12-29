package net.jordaria;

import javax.swing.JDialog;
import javax.swing.JLabel;

import net.jordaria.debug.DebugConsole;
import net.jordaria.debug.DebugPanel;
import net.jordaria.entity.Direction;
import net.jordaria.entity.EntityLiving;
import net.jordaria.entity.EntityPlayer;
import net.jordaria.entity.NameGenerator;
import net.jordaria.event.DebugMessage;
import net.jordaria.event.EntityMoveRequest;
import net.jordaria.event.Error;
import net.jordaria.event.EventHandler;
import net.jordaria.event.EventManager;
import net.jordaria.event.EventSystemStarted;
import net.jordaria.event.GraphicsSystemStarted;
import net.jordaria.event.Listener;
import net.jordaria.event.ShuttingDown;
import net.jordaria.math.Random;
import net.jordaria.world.World;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;


public class Jordaria implements Runnable, Listener{

	//VARIABLES

	public boolean running;//A boolean used to show whether or not the game is running -King
	public boolean inGameHasFocus;//whether or not the actual gameplay has the focus
	public static Configuration config;
	DebugConsole console;
	DisplayMode displayMode;
	public EntityLiving renderViewEntity;
	public EntityPlayer thePlayer;
	EventManager eventManager;
	public FileIO fileIO;
	public GameSettings gameSettings;
	public int displayWidth;
	public int displayHeight;
	public Localization localization;
	public Random rand;
	public Thread thread;
	public World theWorld;

	//MAIN THREAD
	public static void main(String args[]){
		config = new Configuration();
		Jordaria game = new Jordaria();

		game.tryConsoleInit();
		game.setup();
		game.start();
	}

	//SETUP
	public void tryConsoleInit(){
		if (config.getDebugActive()){
			console = new DebugConsole();
		}
	}
	public void setup(){
		try{
			rand = new Random();
			rand.initializeGenerator((int)(Math.random()*1337));
			gameSettings = new GameSettings(this);

			initEventManager();

			fileIO = new FileIO(this);
			fileIO.createMainDirectories(gameSettings.homeDirectory);

			localization = new Localization(this);
			localization.loadLanguage();

			initGraphics();

			theWorld = new World("Test");
			eventManager.fireEvent(new DebugMessage("World ("+theWorld.worldName+") created!"));

			NameGenerator namegen = new NameGenerator();
			thePlayer = new EntityPlayer(theWorld, namegen.getCharacterNameNordic());

			eventManager.fireEvent(new DebugMessage("Player ("+thePlayer.getUsername()+") created!"));

			if (config.getDebugActive()){
				DebugPanel panel = new DebugPanel();
				panel.setJordariaVar(this);
				eventManager.registerEventListeners(panel);
			}
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

	//RUNNING
	public void start(){
		if (this.running)
		{
			return;
		}
		this.running = true;
		try{

			run();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	public void shutdown(){
		this.running = false;
		this.console = null;
		this.theWorld = null;
		this.thePlayer = null;
		this.renderViewEntity = null;
		this.eventManager = null;
		garbageCollect();
		System.exit(0);
	}

	public void garbageCollect(){
		System.gc();
	}
	public void run(){
		while (this.running && !Display.isCloseRequested()){
			try{
				handleKeyboard();
				Display.update();
				Display.sync(60);
			}
			catch(Exception e){
				break;
			}
		}
		eventManager.fireEvent(new ShuttingDown());
		Display.destroy();
	}

	//REGISTERING LISTENERS
	public void registerListeners(){
		if (config.getDebugActive()){
			try {
				eventManager.registerEventListeners(console);
				eventManager.registerEventListeners(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@EventHandler
	public void onError(Error error){
		JDialog errorDialog = new JDialog();
		errorDialog.setTitle("Error!");
		errorDialog.setSize(200, 250);
		errorDialog.add(new JLabel(error.getMessage()));
		errorDialog.setVisible(true);
	}
	
	@EventHandler
	public void onShutdown(ShuttingDown shutdown){
		shutdown();
	}

	//INPUT
	public void handleKeyboard(){
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
				eventManager.fireEvent(new EntityMoveRequest(thePlayer, thePlayer.direction.getRelativeDirection(new Direction(180))));
				if (config.getDebugActive() && config.getDEBUG_SHOW_KEYPRESSES())
					eventManager.fireEvent(new DebugMessage("Key pressed! ("+gameSettings.KEYBIND_MOVE_BACKWARD.keyDescription+")"));
			}
			while (this.gameSettings.KEYBIND_MOVE_LEFT.isPressed())
			{
				eventManager.fireEvent(new EntityMoveRequest(thePlayer, thePlayer.direction.getRelativeDirection(new Direction(90))));
				if (config.getDebugActive() && config.getDEBUG_SHOW_KEYPRESSES())
					eventManager.fireEvent(new DebugMessage("Key pressed! ("+gameSettings.KEYBIND_MOVE_LEFT.keyDescription+")"));
			}
			while (this.gameSettings.KEYBIND_MOVE_RIGHT.isPressed())
			{
				eventManager.fireEvent(new EntityMoveRequest(thePlayer, thePlayer.direction.getRelativeDirection(new Direction(-90))));
				if (config.getDebugActive() && config.getDEBUG_SHOW_KEYPRESSES())
					eventManager.fireEvent(new DebugMessage("Key pressed! ("+gameSettings.KEYBIND_MOVE_RIGHT.keyDescription+")"));
			}
			while (this.gameSettings.KEYBIND_WIREFRAME.isPressed())
			{
				this.gameSettings.toggleWireframe();
				if (config.getDebugActive() && config.getDEBUG_SHOW_KEYPRESSES()){
					eventManager.fireEvent(new DebugMessage("Key pressed! ("+gameSettings.KEYBIND_WIREFRAME.keyDescription+")"));

				}
			}

		}
	}

	//GRAPHICS
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

}
