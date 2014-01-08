package net.jordaria.event;


public class Tick extends Event{
	private static final HandlerList handlers = new HandlerList();

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
