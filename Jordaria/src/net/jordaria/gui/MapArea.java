package net.jordaria.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import net.jordaria.Jordaria;
import net.jordaria.event.EventHandler;
import net.jordaria.event.EventManager;
import net.jordaria.event.Listener;
import net.jordaria.event.events.MapChanged;
import net.jordaria.event.events.Tick;
import net.jordaria.world.Map;

/**
 * Displays a map and allows for movement.
 * 
 * @author Ches Burks
 *
 */
public class MapArea extends JPanel implements Listener, ComponentListener{

	private static final long serialVersionUID = -6525651076156323158L;

	private int tileWidth = 20;//how many tiles fit horizontally
	private int tileHeight = 20;//how many tiles fit vertically
	private int defaultTileWidth = 20;
	private int defaultTileHeight = 20;
	private int mapWidth = 0;//how wide the map is
	private int mapHeight = 0;//how tall the map is
	private int scale = 1;//how large a tile should be drawn on the map area
	private Map currentMap;
	private EventManager eventManager;
	private Jordaria jordaria;
	private int cameraX = 0;//how far the map is offset x
	private int cameraY = 0;//how far the map is offset y
	private boolean eventManagerValid = false;
	private TexturePaint texturePaint;
	private TextureManager textureManager;

	/**
	 * Constructs a new {@link MapArea} with the given 
	 * {@link Jordaria} reference.
	 * 
	 * @param jordaria The reference to the main program
	 */
	public MapArea(Jordaria jordaria){
		this.jordaria = jordaria;
		resetCamera();
		addComponentListener(this);
		textureManager = new TextureManager();
	}

	/**
	 * Sets the event manager to jordarias. This is called after the 
	 * Event system is set up to prevent null references
	 */
	public void setEventManager(){
		this.eventManager = jordaria.getEventManager();
		this.eventManagerValid = true;
	}

	@EventHandler
	public void onTick(Tick event){
		repaint();
		handleKeyboard();
	}

	/**
	 * Handles input from the keyboard
	 */
	public void handleKeyboard(){
		while (jordaria.getGameSettings().KEYBIND_MOVE_FORWARD.isPressed() && this.cameraY > 0)
		{
			this.cameraY-=1;
		}
		while (jordaria.getGameSettings().KEYBIND_MOVE_BACKWARD.isPressed() && this.cameraY+tileHeight < mapHeight)
		{
			this.cameraY+=1;
		}
		while (jordaria.getGameSettings().KEYBIND_MOVE_LEFT.isPressed() && this.cameraX > 0)
		{
			this.cameraX-=1;
		}
		while (jordaria.getGameSettings().KEYBIND_MOVE_RIGHT.isPressed() && this.cameraX+tileWidth < mapWidth)
		{
			this.cameraX+=1;
		}

	}
	/**
	 * Repaints the component
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		if (eventManagerValid){
			if(currentMap == null){
				System.out.println("Null map");
				return;
			}
			if (mapWidth<=0){
				System.out.println("width <= 0");
				return;//the map is not valid, so dont try to repaint the component
			}
			if ( mapHeight<=0){
				System.out.println("height <=0");
				return;
			}


			int x,y;

			for (y = 0+cameraY; y < tileHeight+cameraY; y++){
				for (x = 0+cameraX; x < tileWidth+cameraX; x++){
					try{
						BufferedImage img = textureManager.getTextureBuffer(currentMap.getTile(x, y).getTileType().getName());
						texturePaint = new TexturePaint(img, new Rectangle(0, 0, scale, scale));
						g2d.setPaint(texturePaint);
						g2d.fillRect(x*scale-cameraX*scale, y*scale-cameraY*scale, scale, scale);
					}
					catch(NullPointerException e){

						g.setColor(new Color(.8f, .21f, .43f));
						g.fillRect(x*scale-cameraX*scale, y*scale-cameraY*scale, scale, scale);
					}
				}
			}
		}
	}

	/**
	 * Resets this map to the new one and resets the camera.
	 * 
	 * @param event The event fired
	 */
	@EventHandler
	public void onMapChange(MapChanged event){
		currentMap = event.getMap();
		this.mapHeight = currentMap.getHeight();
		this.mapWidth = currentMap.getWidth();

		/*
		 * If the maps are smaller than the default size, 
		 * then the tile width should be set to the maps size
		 * to prevent invalid indexes
		 */
		if (this.defaultTileHeight > this.mapHeight){
			this.tileHeight = this.mapHeight;
		}
		else{
			this.tileHeight = this.defaultTileHeight;
		}
		if (this.defaultTileWidth > this.mapWidth){
			this.tileWidth = this.mapWidth;
		}
		else{
			this.tileWidth = this.defaultTileWidth;
		}

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

	/**
	 * Recalculates the scale of the tiles to fit the screen.
	 * 
	 */
	public void recalculateScale(){
		if (this.getWidth()>this.getHeight()){
			//WIDE so limited by the height
			scale = getHeight()/tileHeight;

		}
		else{
			//TALL so limited by width
			scale = getWidth()/tileWidth;
		}
	}

	@Override
	public void componentHidden(ComponentEvent e) {}

	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentResized(ComponentEvent e) {
		recalculateScale();
	}

	@Override
	public void componentShown(ComponentEvent e) {}

}
