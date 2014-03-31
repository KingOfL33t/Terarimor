package net.jordaria.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
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

	private int tileWidth;
	private int tileHeight;
	private int mapWidth = 0;//how wide the map is in tiles
	private int mapHeight = 0;//how tall the map is in tiles
	private int tilesY = 30;//how many tiles fit in the y direction
	private int tilesX = 30;//how many tiles fit in the x direction
	private Map currentMap;
	private Jordaria jordaria;
	private int cameraX = 0;//how far the map is offset x
	private int cameraY = 0;//how far the map is offset y
	private boolean eventManagerValid = false;
	private TextureManager textureManager;
	private JLabel[][] tiles;

	/**
	 * Constructs a new {@link MapArea} to display a 
	 * map on the gui.
	 */
	public MapArea(TextureManager textureManager){
		this.jordaria = Jordaria.getInstance();
		try {
			EventManager.getInstance().registerEventListeners(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.textureManager = textureManager;
		setupTiles();
		resetCamera();
		addComponentListener(this);

	}

	@EventHandler
	public void onTick(Tick event){
		handleKeyboard();
		repaint();
	}

	private void setupTiles(){
		int y,x;
		this.tileWidth = this.getWidth()/this.tilesX;
		this.tileHeight = this.getHeight()/this.tilesY;

		tiles = new JLabel[tilesY][tilesX];
		
		setLayout(new GridLayout(tilesX, tilesY));

		if (tilesX > 0 && tilesY > 0){
			for (y = 0; y < tilesY; y++){
				for (x = 0; x < tilesX; x++){
					tiles[y][x] = new JLabel();
					tiles[y][x].setLocation(x*tileWidth, y*tileHeight);
					tiles[y][x].setSize(tileWidth, tileHeight);
					tiles[y][x].setVisible(true);
					this.add(tiles[y][x]);
				}
			}
		}
	}
	/**
	 * Handles input from the keyboard
	 */
	public void handleKeyboard(){
		while (jordaria.getGameSettings().KEYBIND_MOVE_FORWARD.isPressed() && this.cameraY > 0)
		{
			moveCamera(0, -1);
		}
		while (jordaria.getGameSettings().KEYBIND_MOVE_BACKWARD.isPressed() && this.cameraY+tileHeight < mapHeight)
		{
			moveCamera(0, 1);
		}
		while (jordaria.getGameSettings().KEYBIND_MOVE_LEFT.isPressed() && this.cameraX > 0)
		{
			moveCamera(-1, 0);
		}
		while (jordaria.getGameSettings().KEYBIND_MOVE_RIGHT.isPressed() && this.cameraX+tileWidth < mapWidth)
		{
			moveCamera(1, 0);
		}
	}
	/**
	 * Repaints the component
	 */
	public void paint(Graphics g) {
		super.paint(g);
		if (eventManagerValid){
			Graphics2D g2d = (Graphics2D) g;
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

			//draw the map

			g2d.setColor(new Color(0,0,0));
			g2d.drawString("Map W:"+mapWidth, 10, 10);
			g2d.drawString("Map H:"+mapHeight, 10, 20);
			g2d.drawString("Tile W:"+tileWidth, 10, 30);
			g2d.drawString("Tile H:"+tileHeight, 10, 40);
			g2d.drawString("Cam X:"+cameraX, 10, 60);
			g2d.drawString("Cam Y:"+cameraY, 10, 70);
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

		updateTileTextures();
	}

	/**
	 * Resets the camera offsets to zero.
	 */
	public void resetCamera(){
		this.cameraX = 0;
		this.cameraY = 0;

		updateTileTextures();
	}

	/**
	 * Determines what texture to use with each tile and 
	 * sets them appropriately.
	 */
	private void updateTileTextures(){
		int x,y;
		BufferedImage img;

		if (currentMap == null){
			return;
		}
		if (tiles == null){
			return;
		}
		if (! (tiles.length > 0)){
			return;
		}
		

		for (y = 0+cameraY; y < tilesY+cameraY; y++){
			for (x = 0+cameraX; x < tilesX+cameraX; x++){
				if (currentMap.containsPoint(x, y)){
					try{
						img = textureManager.getTextureBuffer(currentMap.getTile(x, y).getTileType().getName());
					}
					catch(NullPointerException e){
						img = textureManager.getTextureBuffer("notexture");
					}
					tiles[y][x].setVisible(false);
					//set the icon to a version of the image scaled to fit
					tiles[y][x].setIcon(new ImageIcon(img.getScaledInstance(tileWidth, tileHeight, BufferedImage.SCALE_FAST)));
					tiles[y][x].setSize(tileWidth, tileHeight);
					tiles[y][x].setVisible(true);
				}
				else{
					tiles[y][x].setVisible(false);
				}
			}
		}
	}

	/**
	 * Sets the cameras x position to the supplied pos.
	 * 
	 * @param x The new x position
	 */
	public void setCameraX(int x){
		this.cameraX = x;
		updateTileTextures();
	}

	/**
	 * Sets the cameras y position to the supplied pos.
	 * 
	 * @param y The new y position
	 */
	public void setCameraY(int y){
		this.cameraY = y;
		updateTileTextures();
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
		updateTileTextures();
	}

	/**
	 * Recalculates the scale of the tiles to fit the screen.
	 *
	 */
	public void recalculateScale(){
		this.tileWidth = this.getWidth()/this.tilesX;
		this.tileHeight = this.getHeight()/this.tilesY;
	
		updateTileTextures();
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
