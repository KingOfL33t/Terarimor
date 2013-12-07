package net.jordaria.event;

public class Error extends Event{
	private static final HandlerList handlers = new HandlerList();

	String message;

	public Error(String theMessage){
		this.message = theMessage;
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
