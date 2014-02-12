package net.jordaria.event.events;

import net.jordaria.event.Event;
import net.jordaria.event.HandlerList;

/**
 * Fired when the game has been resumed.
 * 
 * @author Ches Burks
 */
public class GameResumed  extends Event{
	private static final HandlerList handlers = new HandlerList();

	/**
	 * Constructs a new {@link GameResumed}.
	 */
	public GameResumed(){

	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}


	public static HandlerList getHandlerList() {
		return handlers;
	}
}
