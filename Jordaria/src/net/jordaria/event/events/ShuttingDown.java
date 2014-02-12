package net.jordaria.event.events;

import net.jordaria.event.Event;
import net.jordaria.event.HandlerList;

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
