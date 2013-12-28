package net.jordaria.event;

/*
 * The sound system has been initialized and is now working
 */
public class SoundSystemStarted extends Event{
	private static final HandlerList handlers = new HandlerList();

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