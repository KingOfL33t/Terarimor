package net.jordaria.event;

/**
 * Fired when a message is sent to the console.
 * 
 * @author Ches Burks
 */
public class DebugMessage extends Event{

	private static final HandlerList handlers = new HandlerList();

	String message;

	/**
	 * Constructs a new {@link DebugMessage DebugMessage} with 
	 * the given message.
	 * 
	 * @param theMessage The message to use
	 */
	public DebugMessage(String theMessage){
		this();
		this.message = theMessage;
	}
	
	/**
	 * Constructs a new empty {@link DebugMessage DebugMessage}.
	 */
	public DebugMessage(){

	}

	/**
	 * Returns the message contained.
	 * 
	 * @return The message
	 */
	public String getMessage(){
		if (this.message == null){
			return "";
		}
		else{
			return this.message;
		}
	}
	
	/**
	 * Returns the {@link HandlerList handler list}.
	 * 
	 * @return The handler list.
	 * 
	 */
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}


	/**
	 * Returns the {@link HandlerList handler list}.
	 * 
	 * @return The handler list.
	 * 
	 */
	public static HandlerList getHandlerList() {
		return handlers;
	}

}
