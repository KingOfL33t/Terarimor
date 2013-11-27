package net.jordaria.event;

public class DebugMessageEvent extends Event{

	private static final HandlerList handlers = new HandlerList();
	
	String message;
	
	public DebugMessageEvent(String theMessage){
		this();
		this.message = theMessage;
	}
	public DebugMessageEvent(){
		
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
