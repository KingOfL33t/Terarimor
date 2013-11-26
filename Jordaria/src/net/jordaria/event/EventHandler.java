package net.jordaria.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** An annotation to mark methods as being event handler methods */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
	/**
	 * The priority of the event (LOWEST, LOW, NORMAL, HIGH, HIGHEST, MONITOR)
	 */
	EventPriority priority() default EventPriority.NORMAL;
}
