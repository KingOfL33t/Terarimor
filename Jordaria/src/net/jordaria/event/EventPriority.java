package net.jordaria.event;

public enum EventPriority {
	LOWEST(0),
	LOW(1),
	NORMAL(2),
	HIGH(3),
	HIGHEST(4),
	MONITOR(5);

	private final int priority;
	private EventPriority(int priority){
		this.priority = priority;
	}
	private int getPriority(){
		return priority;
	}
}
