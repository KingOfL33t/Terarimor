package net.jordaria.event;

/**
 * Fired when the program is shutting down.
 * 
 * @author Ches Burks
 */
public class ShuttingDown extends Event{

	private static final HandlerList handlers = new HandlerList();

	/**
	 * Constructs a new {@link ShuttingDown}.
	 */
	public ShuttingDown(){

	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}


	public static HandlerList getHandlerList() {
		return handlers;
	}
}
