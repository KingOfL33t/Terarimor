package net.jordaria.event.events;

import net.jordaria.event.Event;
import net.jordaria.event.HandlerList;

/**
 * Fired when the Event system has been started and is now running.
 * 
 * @author Ches Burks
 */
public class EventSystemStarted extends Event{
	private static final HandlerList handlers = new HandlerList();

	/**
	 * Constructs a new {@link EventSystemStarted}.
	 */
	public EventSystemStarted(){

	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}


	public static HandlerList getHandlerList() {
		return handlers;
	}
}
