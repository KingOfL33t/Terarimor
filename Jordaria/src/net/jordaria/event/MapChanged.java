package net.jordaria.event;

/**
 * Fired when the map has changed.
 * 
 * @author Ches Burks
 *
 */
public class MapChanged extends Event{
	private static final HandlerList handlers = new HandlerList();

	int width;
	int height;

	/**
	 * Constructs a new {@link MapChanged} with the given 
	 * width and height of the new map.
	 * 
	 * @param width The new width
	 * @param height The new height
	 */
	public MapChanged(int width, int height){
		this.width = width;
		this.height = height;
	}

	/**
	 * Returns the new maps width.
	 * 
	 * @return The width
	 */
	public int getWidth(){
		return this.width;
	}
	
	/**
	 * Returns the new maps height.
	 * 
	 * @return The height
	 */
	public int getHeight(){
		return this.height;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
