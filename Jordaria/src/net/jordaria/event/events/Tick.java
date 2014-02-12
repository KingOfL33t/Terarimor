package net.jordaria.event.events;

import net.jordaria.event.Event;
import net.jordaria.event.HandlerList;

/**
 * Fired to tick subsystems.
 * 
 * @author Ches Burks
 *
 */
public class Tick extends Event{
	private static final HandlerList handlers = new HandlerList();

	/**
	 * Constructs a new {@link Tick}.
	 */
	public Tick(){

	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}


	public static HandlerList getHandlerList() {
		return handlers;
	}
}
