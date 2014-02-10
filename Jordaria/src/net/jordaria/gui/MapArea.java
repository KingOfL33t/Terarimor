package net.jordaria.gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import net.jordaria.Jordaria;
import net.jordaria.event.EventHandler;
import net.jordaria.event.EventManager;
import net.jordaria.event.Listener;
import net.jordaria.event.MapChanged;
import net.jordaria.world.Map;

/**
 * Displays a map and allows for movement.
 * 
 * @author Ches Burks
 *
 */
public class MapArea extends JPanel implements Listener{

	private static final long serialVersionUID = -6525651076156323158L;

	private int tileWidth = 20;//how many tiles fit horizontally
	private int tileHeight = 20;//how many tiles fit vertically
	private int mapWidth = 0;
	private int mapHeight = 0;
	private Map currentMap;
	private EventManager eventManager;
	private Jordaria jordaria;
	private int cameraX = 0;//how far the map is offset x
	private int cameraY = 0;//how far the map is offset y
	
	/**
	 * Constructs a new {@link MapArea} with the given 
	 * {@link Jordaria} reference.
	 * 
	 * @param jordaria The reference to the main program
	 */
	public MapArea(Jordaria jordaria){
		this.jordaria = jordaria;
		this.eventManager = jordaria.getEventManager();
		resetCamera();
	}
	
	protected void paintComponent(Graphics g) {
		if (currentMap == null || mapWidth<=0 || mapHeight<=0){
			return;//the map is not valid, so dont try to repaint the component
		}
		
	}
	@EventHandler
	public void onMapChange(MapChanged event){
		currentMap = event.getMap();
		this.mapHeight = currentMap.getHeight();
		this.mapWidth = currentMap.getWidth();
		resetCamera();
	}
	/**
	 * Moves the camera by the given amount.
	 * 
	 * @param x X offset
	 * @param y Y offset
	 */
	public void moveCamera(int x, int y){
		this.cameraX+=x;
		this.cameraY+=y;
	}

	/**
	 * Resets the camera offsets to zero.
	 */
	public void resetCamera(){
		this.cameraX = 0;
		this.cameraY = 0;
	}

	/**
	 * Sets the cameras x position to the supplied pos.
	 * 
	 * @param x The new x position
	 */
	public void setCameraX(int x){
		this.cameraX = x;
	}

	/**
	 * Sets the cameras y position to the supplied pos.
	 * 
	 * @param y The new y position
	 */
	public void setCameraY(int y){
		this.cameraY = y;
	}

	/**
	 * Sets the cameras position to the supplied position.
	 * 
	 * @param x The new x position
	 * @param y The new y position
	 */
	public void setCameraPos(int x, int y){
		setCameraX(x);
		setCameraY(y);
	}

}
