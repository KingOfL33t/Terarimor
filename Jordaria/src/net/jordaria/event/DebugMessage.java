package net.jordaria.event;

public class DebugMessage extends Event{

	private static final HandlerList handlers = new HandlerList();

	String message;

	public DebugMessage(String theMessage){
		this();
		this.message = theMessage;
	}
	public DebugMessage(){

	}

	public String getMessage(){
		if (this.message == null){
			return "";
		}
		else{
			return this.message;
		}
	}
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}


	public static HandlerList getHandlerList() {
		return handlers;
	}

}
