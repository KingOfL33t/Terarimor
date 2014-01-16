package net.jordaria.event;


public class MapChanged extends Event{
	private static final HandlerList handlers = new HandlerList();

	int width;
	int height;

	public MapChanged(int width, int height){
		this.width = width;
		this.height = height;
	}

	public int getWidth(){
		return this.width;
	}
	public int getHeight(){
		return this.height;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
