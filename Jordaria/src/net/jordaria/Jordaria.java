package net.jordaria;

import javax.swing.JDialog;
import javax.swing.JLabel;

import net.jordaria.debug.DebugConsole;
import net.jordaria.debug.DebugPanel;
import net.jordaria.entity.EntityLiving;
import net.jordaria.entity.EntityPlayer;
import net.jordaria.entity.NameGenerator;
import net.jordaria.event.DebugMessage;
import net.jordaria.event.Error;
import net.jordaria.event.EventHandler;
import net.jordaria.event.EventManager;
import net.jordaria.event.EventSystemStarted;
import net.jordaria.event.GraphicsSystemStarted;
import net.jordaria.event.Listener;
import net.jordaria.event.ShuttingDown;
import net.jordaria.event.Tick;
import net.jordaria.gui.MainWindow;
import net.jordaria.math.Random;
import net.jordaria.world.World;


public class Jordaria implements Runnable, Listener{

	//VARIABLES
	public boolean running;
	public static Configuration config;
	DebugConsole console;
	public EntityLiving renderViewEntity;
	public EntityPlayer thePlayer;
	EventManager eventManager;
	public FileIO fileIO;
	public GameSettings gameSettings;
	public int displayWidth;
	public int displayHeight;
	public Localization localization;
	public MainWindow mainWindow;
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

			mainWindow = new MainWindow(this);
			
			initEventManager();

			fileIO = new FileIO(this);
			fileIO.createMainDirectories(gameSettings.homeDirectory);
			fileIO.copyFilesToDisk(gameSettings.homeDirectory);

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
			mainWindow.createWindow();
		} catch (Exception e) {
			eventManager.fireEvent(new Error("Error creating Window"));
		}
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
		while (this.running){
			try{
				eventManager.fireEvent(new Tick());
			}
			catch(Exception e){
				break;
			}
		}
		
	}
	
	//GETTERS
	public GameSettings getGameSettings(){
		return this.gameSettings;
	}
	public EventManager getEventManager(){
		return this.eventManager;
	}
	//REGISTERING LISTENERS
	public void registerListeners(){
		if (config.getDebugActive()){
			try {
				eventManager.registerEventListeners(console);
				eventManager.registerEventListeners(this);
				eventManager.registerEventListeners(mainWindow);
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

}
