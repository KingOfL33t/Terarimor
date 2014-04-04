package net.jordaria;

import javax.swing.JDialog;
import javax.swing.JLabel;

import net.jordaria.debug.DebugConsole;
import net.jordaria.entity.EntityPlayer;
import net.jordaria.event.EventHandler;
import net.jordaria.event.EventManager;
import net.jordaria.event.Listener;
import net.jordaria.event.events.DebugMessage;
import net.jordaria.event.events.Error;
import net.jordaria.event.events.EventSystemStarted;
import net.jordaria.event.events.GraphicsSystemStarted;
import net.jordaria.event.events.ShuttingDown;
import net.jordaria.gui.SwingMainWindow;
import net.jordaria.math.NameGenerator;
import net.jordaria.math.Random;
import net.jordaria.world.Map;
import net.jordaria.world.World;

/**
 * The entry point of the program, containing subsystems and data.
 * 
 * @author Ches Burks
 */
public class Jordaria implements Runnable, Listener{
	private static Jordaria instance;

	//VARIABLES
	private boolean running;//Whether or not the game is active
	public static Configuration config;
	@SuppressWarnings("unused")
	private DebugConsole console;//reference to prevent GC
	private EntityPlayer thePlayer;
	private EventManager eventManager;
	@SuppressWarnings("unused")
	private FileIO fileIO;//reference to prevent GC
	private GameSettings gameSettings;
	//public MainWindow mainWindow;
	private Random rand;//The random number generator for the main program
	private World theWorld;//The current world
	@SuppressWarnings("unused")
	private SwingMainWindow swingMainWindow;//reference to prevent GC
	private Heart heart;

	/**
	 * The main method. Creates and starts a new game.
	 * 
	 * @param args Arguments for the program
	 */
	public static void main(String args[]){
		config = new Configuration();
		instance = new Jordaria();

		instance.tryConsoleInit();
		instance.setup();
		instance.start();
	}

	/**
	 * Returns the current instance of Jordaria. There 
	 * is only one at any time, and it is one of the first 
	 * things created.
	 * 
	 * @return a reference to the Jordaria program currently running
	 */
	public static synchronized Jordaria getInstance(){
		return instance;
	}

	/**
	 * Starts the console if debug mode is active.
	 */
	public synchronized void tryConsoleInit(){
		if (config.getDebugActive()){
			console = new DebugConsole();
		}
	}

	/**
	 * Sets up subsystems and prepares them for starting the game.
	 */
	public synchronized void setup(){
		try{
			initEventManager();

			rand = new Random();
			rand.initializeGenerator((int)(Math.random()*1337));
			gameSettings = new GameSettings();
			
			fileIO = new FileIO();
			//fileIO.createMainDirectories(gameSettings.homeDirectory);

			swingMainWindow = new SwingMainWindow();

			initGraphics();

			theWorld = new World("Test");
			eventManager.fireEvent(new DebugMessage("World ("+theWorld.worldName+") created!"));

			NameGenerator namegen = new NameGenerator();
			thePlayer = new EntityPlayer(theWorld, namegen.getCharacterNameNordic());
			eventManager.fireEvent(new DebugMessage("Player ("+thePlayer.getUsername()+") created!"));

			heart = new Heart();

			try {
				eventManager.registerEventListeners(this);
			} catch (Exception e) {
				e.printStackTrace();
			}

			//Create a small test map
			Map map = new Map(50, 50);
			theWorld.getWorldGenerator().fillWithTown(map);
			theWorld.setCurrentMap(map);

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Starts the event manager and registers listeners.
	 * Fires an {@link EventSystemStarted EventSystemStarted} event on completion.
	 * 
	 */
	private synchronized void initEventManager(){
		eventManager = EventManager.getInstance();//likely creates the manager
		eventManager.fireEvent(new EventSystemStarted());
	}

	/**
	 * Initializes graphics subsystem and creates the main window.
	 * Fires an {@link GraphicsSystemStarted GraphicsSystemStarted} event on completion.
	 */
	private synchronized void initGraphics(){
		try {
			//mainWindow.createWindow();
		} catch (Exception e) {
			e.printStackTrace();
			eventManager.fireEvent(new Error("Error creating Window"));
		}
		eventManager.fireEvent(new GraphicsSystemStarted());
	}

	/**
	 * Starts the game if it is not running, otherwise does nothing.
	 */
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

	/**
	 * Clears references, garbage collects, 
	 * and calls System.exit(0).
	 */
	public void shutdown(){
		this.running = false;
		this.console = null;
		this.theWorld = null;
		this.thePlayer = null;
		this.eventManager = null;
		garbageCollect();
		System.exit(0);
	}

	/**
	 * Runs the system garbage collector.
	 * The same thing as calling System.gc();
	 */
	public void garbageCollect(){
		System.gc();
	}

	/**
	 * Fires Tick events while the game is running.
	 */
	public void run(){
		heart.start();
	}

	/**
	 * Returns the game settings for the current instance.
	 * 
	 * @return Game Settings for the current instance
	 */
	public GameSettings getGameSettings(){
		return this.gameSettings;
	}

	/**
	 * Returns the {@link Random Random number generator} for the game.
	 * 
	 * @return Random number generator for the main program
	 */
	public Random getRandomGenerator(){
		return this.rand;
	}

	/**
	 * Returns the current world for the game.
	 * @return Current world for the game
	 */
	public World getWorld(){
		return this.theWorld;
	}

	/**
	 * Show an error dialog detailing the error message that was fired.
	 * 
	 * @param error The Error event that was fired
	 */
	@EventHandler
	public void onError(Error error){
		JDialog errorDialog = new JDialog();
		errorDialog.setTitle("Error!");
		errorDialog.setSize(200, 250);
		errorDialog.add(new JLabel(error.getMessage()));
		errorDialog.setVisible(true);
	}

	/**
	 * Calls the {@link #shutdown() shutdown} method.
	 * 
	 * @param shutdown The shutdown event that was fired
	 */
	@EventHandler
	public void onShutdown(ShuttingDown shutdown){
		shutdown();
	}

}
