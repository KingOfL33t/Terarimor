package net.jordaria.event.events;

import net.jordaria.event.Event;
import net.jordaria.event.HandlerList;

/**
 * Fired when the sound system has been initialized and is now working.
 * 
 * @author Ches Burks
 */
public class SoundSystemStarted extends Event{
	private static final HandlerList handlers = new HandlerList();

	/**
	 * Constructs a new {@link SoundSystemStarted}.
	 */
	public SoundSystemStarted(){

	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}


	public static HandlerList getHandlerList() {
		return handlers;
	}
}