package net.jordaria.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.jordaria.exception.EventException;

public class EventManager {

	public void registerEventListeners(Listener listener) throws Exception {
		for (Map.Entry<Class<? extends Event>, Set<EventListener>> entry : createRegisteredListeners(listener).entrySet()) {
			getEventListeners(getRegistrationClass(entry.getKey())).registerAll(entry.getValue());
		}
	}

	private HandlerList getEventListeners(Class<? extends Event> type)
			throws Exception {
		try {
			Method method = getRegistrationClass(type).getDeclaredMethod("getHandlerList");// get the gethandlerlist method
			method.setAccessible(true);// eh, security? we have a try/catch for
			// that :P
			return (HandlerList) method.invoke(null);// get the handler list
			// from the class
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e.getCause());// oops
		}
	}

	private Class<? extends Event> getRegistrationClass(
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
				throw new Exception("Unable to find handler list for event "
						+ eventClass.getName());// they dont exist
			}
		}
	}

	public void fireEvent(Event event) {
		HandlerList handlers = event.getHandlers();
		EventListener[] listeners = handlers.getRegisteredListeners();
		for (EventListener registration : listeners) {
			try {
				registration.callEvent(event);
			} catch (EventException e) {
				;// should probably do something here, but its the event system.
				// soooo if this is broken we are screwed for reporting
				// errors.
				// perhaps implement a log system for these cases
			}
		}
	}

	public Map<Class<? extends Event>, Set<EventListener>> createRegisteredListeners(Listener listener) {

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
