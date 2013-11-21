package net.jordaria.event;

public interface Cancellable {
	/**
	 * * Gets the cancellation state of this event. returns true if this event is cancelled
	 */
	public boolean isCancelled();

	/**
	 * * Sets the cancellation state of this event.
	 * set cancel true if you wish to cancel this event
	 */
	public void setCancelled(boolean cancel);
}
