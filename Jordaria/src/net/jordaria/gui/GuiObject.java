package net.jordaria.gui;

import java.util.ArrayList;
import java.util.LinkedList;

import net.jordaria.event.EventManager;
import net.jordaria.event.Listener;

/**
 * An object in the gui. These have children and can be rendered. 
 * All have a reference to the main event system and their parents/children.
 * 
 * @author Ches Burks
 *
 */
public class GuiObject implements Listener{

	private ArrayList<GuiObject> children;
	private GuiObject parent;
	private boolean hasParent = false;
	private EventManager eventManager;
	
	/**
	 * Constructs a new {@link GuiObject}.
	 */
	public GuiObject(){
		
	}
	
	/**
	 * Constructs a new {@link GuiObject} and sets the parent to the 
	 * given object.
	 * 
	 * @param parent The parent object
	 */
	public GuiObject(GuiObject parent){
		this();
		setParent(parent);
	}
	/**
	 * Performs initialization of the gui object. 
	 * This should be overwritten.
	 */
	public void init(){
		
	}
	
	public void setEventManager(EventManager eventManager){
		this.eventManager = eventManager;
	}
	
	/**
	 * Initializes the child object with the event manager, calls its 
	 * init method, and registers it as a listener with the event manager.
	 * It is then added to the children list.
	 * 
	 * @param child The child object to add
	 */
	public void addChild(GuiObject child){
		child.setEventManager(eventManager);
		child.init();
		try {
			eventManager.registerEventListeners(child);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.children.add(child);
	}
	
	/**
	 * Sets the parent reference to the provided object.
	 * HasParent is set to true.
	 * 
	 * @param parent The new parent object
	 */
	public void setParent(GuiObject parent){
		this.parent = parent;
		this.hasParent = true;
	}
	
	/**
	 * Sets the parent reference to null and hasParent to false.
	 */
	public void removeParent(){
		this.parent = null;
		this.hasParent = false;
	}
	
	/**
	 * Recursively finds all of the children of this object and returns them 
	 * in an {@link ArrayList}. getChildren() is called for the objects children as 
	 * well.
	 * 
	 * @return all of this objects children
	 */
	public ArrayList<GuiObject> getChildren(){
		LinkedList<GuiObject> open = new LinkedList<GuiObject>();
		LinkedList<GuiObject> closed = new LinkedList<GuiObject>();
		open.addAll(children);
		while(!open.isEmpty()){
			open.addAll(open.getFirst().getChildren());
			closed.add(open.removeFirst());
		}
		ArrayList<GuiObject> output = new ArrayList<GuiObject>();
		output.addAll(closed);
		return output;
	}
	
	/**
	 * Returns true if this has a parent object, false otherwise.
	 * 
	 * @return true if this has a parent object, false otherwise.
	 */
	public boolean hasParent(){
		return this.hasParent;
	}
	
	/**
	 * Returns the parent object. This can be null.
	 * 
	 * @return the parent object
	 */
	public GuiObject getParent(){
		return this.parent;
	}
	
}
