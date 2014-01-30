package net.jordaria.event;

/**
 * Fired when an error occurs.
 * 
 * @author Ches Burks
 *
 */
public class Error extends Event{
	private static final HandlerList handlers = new HandlerList();

	String message;

	/**
	 * Constructs a new {@link Error Error} with the 
	 * given message.
	 * 
	 * @param theMessage The message to display
	 */
	public Error(String theMessage){
		this.message = theMessage;
	}

	/**
	 * Returns the message or an empty string if no message was supplied.
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
