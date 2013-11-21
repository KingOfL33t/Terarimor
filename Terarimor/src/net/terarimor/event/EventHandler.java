package net.terarimor.event;

public interface EventHandler {
	/**
	 * The priority of the event
	 * (LOWEST, LOW, NORMAL, HIGH, HIGHEST, MONITOR)
	 */
	EventPriority priority() default EventPriority.NORMAL;
	
	boolean ignoreCancelled() default false;
}
