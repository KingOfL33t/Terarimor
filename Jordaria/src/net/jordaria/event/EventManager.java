package net.jordaria.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Manages events and listeners.
 */
public class EventManager {
	
	private static EventManager instance;
	
	/**
	 * Creates a new EventManager if one does not already exist, 
	 * and then returns the manager. There is only one instance at 
	 * any time.
	 * 
	 * @return The instance of the EventManager
	 */
	public static synchronized EventManager getInstance(){
		if (instance == null){
			instance = new EventManager();
		}
		return instance;
	}

	/**
	 * Registers event listeners in the supplied listener.
	 * 
	 * @param listener The listener to register
	 * @throws Exception If there is an error registering
	 */
	public synchronized void registerEventListeners(Listener listener) throws Exception {
		for (Map.Entry<Class<? extends Event>, Set<EventListener>> entry : createRegisteredListeners(listener).entrySet()) {
			getEventListeners(getRegistrationClass(entry.getKey())).registerAll(entry.getValue());
		}
	}

	/**
	 * Returns a {@link HandlerList} for a give event type
	 * 
	 * @param type The type of event to find handlers for
	 * @throws Exception If an exception occurred
	 */
	private synchronized HandlerList getEventListeners(Class<? extends Event> type)
			throws Exception {
		try {
			Method method = getRegistrationClass(type).getDeclaredMethod("getHandlerList");
			method.setAccessible(true);
			return (HandlerList) method.invoke(null);//get the handler list from the class
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Gets the registration class for the {@link Event}.
	 * 
	 * @param eventClass The class to find handlers for
	 * @throws Exception If a handler list cannot be found
	 */
	private synchronized Class<? extends Event> getRegistrationClass(
			Class<? extends Event> eventClass) throws Exception {
		try {
			eventClass.getDeclaredMethod("getHandlerList");
			return eventClass;
		} catch (NoSuchMethodException e) {
			if (eventClass.getSuperclass() != null
					&& !eventClass.getSuperclass().equals(Event.class)
					&& Event.class.isAssignableFrom(eventClass.getSuperclass())) {

				return getRegistrationClass(eventClass.getSuperclass()
						.asSubclass(Event.class));
			} else {
				throw new Exception("Unable to find handler list for event "+ eventClass.getName());
			}
		}
	}

	/**
	 * Sends the {@link Event event} to all of its listeners.
	 * 
	 * @param event The event to fire
	 */
	public void fireEvent(Event event) {
		HandlerList handlers = event.getHandlers();
		EventListener[] listeners = handlers.getRegisteredListeners();
		for (EventListener registration : listeners) {
			try {
				registration.callEvent(event);
			} catch (EventException e) {
				;// should probably do something here, but its the event system.
				// so if this is broken we are screwed for reporting errors
				// perhaps implement a log system for these cases
			}
		}
	}

	/**
	 * Creates {@link EventListener EventListeners} for a given {@link Listener listener}.
	 * 
	 * @param listener The listener to create EventListenrs for
	 * @return A map of events to a set of EventListeners belonging to it
	 */
	public synchronized Map<Class<? extends Event>, Set<EventListener>> createRegisteredListeners(Listener listener) {

		Map<Class<? extends Event>, Set<EventListener>> toReturn = new HashMap<Class<? extends Event>, Set<EventListener>>();
		Set<Method> methods;
		try {
			Method[] publicMethods = listener.getClass().getMethods();
			methods = new HashSet<Method>(publicMethods.length, Float.MAX_VALUE);
			for (Method method : publicMethods) {
				methods.add(method);
			}
			for (Method method : listener.getClass().getDeclaredMethods()) {
				methods.add(method);
			}
		} catch (NoClassDefFoundError e) {
			return toReturn;
		}
		for (final Method method : methods) {
			final EventHandler handlerAnnotation = method.getAnnotation(EventHandler.class);
			if (handlerAnnotation == null)
				continue;
			final Class<?> checkClass;
			if (method.getParameterTypes().length != 1
					|| !Event.class.isAssignableFrom(checkClass = method.getParameterTypes()[0])) {
				continue;
			}
			final Class<? extends Event> eventClass = checkClass.asSubclass(Event.class);
			method.setAccessible(true);
			Set<EventListener> eventSet = toReturn.get(eventClass);
			if (eventSet == null) {
				eventSet = new HashSet<EventListener>();
				toReturn.put(eventClass, eventSet);
			}
			EventExecutor executor = new EventExecutor() {
				public void execute(Listener listener, Event event)
						throws EventException {
					try {
						if (!eventClass.isAssignableFrom(event.getClass())) {
							return;
						}
						method.invoke(listener, event);
					}

					catch (Throwable t) {
						throw new EventException(t);
					}
				}
			};

			eventSet.add(new EventListener(listener, executor, handlerAnnotation.priority()));

		}
		return toReturn;
	}
}
