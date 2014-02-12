package net.jordaria.event.events;

import net.jordaria.event.Event;
import net.jordaria.event.HandlerList;

/**
 * Fired when the game has been paused.
 * 
 * @author Ches Burks
 */
public class GamePaused extends Event{
	private static final HandlerList handlers = new HandlerList();

	/**
	 * Constructs a new {@link GamePaused}.
	 */
	public GamePaused(){

	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}


	public static HandlerList getHandlerList() {
		return handlers;
	}
}
