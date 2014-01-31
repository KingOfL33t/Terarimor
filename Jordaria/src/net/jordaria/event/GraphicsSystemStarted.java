package net.jordaria.event;

/**
 * Fired when the graphics system has been initialized and is now running.
 * 
 * @author Ches Burks
 */
public class GraphicsSystemStarted extends Event{
	private static final HandlerList handlers = new HandlerList();

	/**
	 * Constructs a new {@link GraphicsSystemStarted}.
	 */
	public GraphicsSystemStarted(){

	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}


	public static HandlerList getHandlerList() {
		return handlers;
	}
}
