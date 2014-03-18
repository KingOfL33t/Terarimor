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
	
	//position and size
	private int widthPixels;//the absolute width in pixels
	private float widthPercentage;//the percentage of the parent's width this takes up
	
	private int heightPixels;//the absolute height in pixels
	private float heightPercentage;//percentage of the parent's height
	
	private int positionXPixels;//the actual x position in pixels
	private float positionXPercentage;//relative x position (% of the way across parent)
	
	private int positionYPixels;//the actual y position in pixels
	private float positionYPercentage;//relative y position (% of the way down parent)
	
	private int zIndex;//the depth of the object. objects collide with those of the same depth
	
	//if the object should resize its width automatically. Set to false if width is set.
	private boolean autoResizeWidth = true;
	//if the object should resize its height automatically. Set to false if height is set.
	private boolean autoResizeHeight = true;
	
	/**
	 * Constructs a new {@link GuiObject}.
	 */
	public GuiObject(){
		init();
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

	/**
	 * Returns the percentage of the parent's width that this object 
	 * takes up. The percentage is a float with 0.0 as 0% and 1.0 as 100%.
	 * @return what percent of the parent's width this takes up
	 */
	public float getWidthPercentage() {
		return widthPercentage;
	}

	/** 
	 * Sets the percentage of the parent's width that this object 
	 * should take up. The percentage is a float with 0.0 as 0% and 1.0 as 100%.
	 * <br>
	 * <i>For example:</i> setting the percentage to 0.5f would make an object half as 
	 * wide as the parent
	 * 
	 * @param widthPercentage what percent of the parent's width to take up
	 */
	public void setWidthPercentage(float widthPercentage) {
		this.widthPercentage = widthPercentage;
	}

	/**
	 * Returns the percentage of the parent's height that this object 
	 * takes up. The percentage is a float with 0.0 as 0% and 1.0 as 100%.
	 * 
	 * @return the heightPercentage
	 */
	public float getHeightPercentage() {
		return heightPercentage;
	}

	/**
	 * Sets the percentage of the parent's height that this object 
	 * should take up. The percentage is a float with 0.0 as 0% and 1.0 as 100%.
	 * <br>
	 * <i>For example:</i> setting the percentage to 0.5f would make an object half as 
	 * tall as the parent
	 * 
	 * @param heightPercentage what percent of the parent's height to take up
	 */
	public void setHeightPercentage(float heightPercentage) {
		this.heightPercentage = heightPercentage;
	}

	/**
	 * Returns how far the left edge of object is from the parent's left edge. 
	 * The percentage is a float with a value of 0.0 meaning the left edges are 
	 * touching, and 1.0 meaning that the left edge is touching the parent's right edge.
	 * 
	 * @return how far from the left edge of the parent the object is located
	 */
	public float getPositionXPercentage() {
		return positionXPercentage;
	}

	/**
	 * Sets how far the left edge of object is from the parent's left edge. 
	 * The percentage is a float with a value of 0.0 meaning the left edges are 
	 * touching, and 1.0 meaning that the left edge is touching the parent's right edge.
	 * 
	 * @param positionXPercentage how far from the edge to set the object
	 */
	public void setPositionXPercentage(float positionXPercentage) {
		this.positionXPercentage = positionXPercentage;
	}

	/**
	 * Returns how far the top edge of object is from the parent's top edge. 
	 * The percentage is a float with a value of 0.0 meaning the top edges are 
	 * touching, and 1.0 meaning that the top edge is touching the parent's bottom edge.
	 * 
	 * @return the positionYPercentage
	 */
	public float getPositionYPercentage() {
		return positionYPercentage;
	}

	/**
	 * Sets how far the top edge of object is from the parent's top edge. 
	 * The percentage is a float with a value of 0.0 meaning the top edges are 
	 * touching, and 1.0 meaning that the top edge is touching the parent's bottom edge.
	 * 
	 * @param positionYPercentage the positionYPercentage to set
	 */
	public void setPositionYPercentage(float positionYPercentage) {
		this.positionYPercentage = positionYPercentage;
	}

	/**
	 * Returns what layer the object is on.
	 * Objects on higher layers cover lower layers.
	 * @return the z index
	 */
	public int getzIndex() {
		return zIndex;
	}

	/**
	 * Sets what layer the object is on.
	 * Objects on higher layers cover lower layers.
	 * 
	 * @param zIndex the new index value
	 */
	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	/**
	 * Returns true if the object will resize it's width to fit the parent automatically, 
	 * and false if it is a set percentage of the parent.
	 * 
	 * @return true if the object resizes its width automatically
	 */
	public boolean getAutoResizesWidth() {
		return autoResizeWidth;
	}

	/**
	 * Sets the value of autoResizeWidth to the given value. If it is set to 
	 * false, the object will remain a fixed percent of the parents width. If set to 
	 * true, the object will reisze to fit on its own.
	 * 
	 * @param autoResizesWidth if the object should auto-resize
	 */
	public void setAutoResizesWidth(boolean autoResizesWidth) {
		this.autoResizeWidth = autoResizesWidth;
	}

	/**
	 * Returns true if the object will resize it's height to fit the parent automatically, 
	 * and false if it is a set percentage of the parent.
	 * 
	 * @return true if the object resizes its height automatically
	 */
	public boolean getAutoResizesHeight() {
		return autoResizeHeight;
	}

	/**
	 * Sets the value of autoResizeHeight to the given value. If it is set to 
	 * false, the object will remain a fixed percent of the parents height. If set to 
	 * true, the object will reisze to fit on its own.
	 * 
	 * @param autoResizesHeight if the object should auto-resize
	 */
	public void setAutoResizesHeight(boolean autoResizesHeight) {
		this.autoResizeHeight = autoResizesHeight;
	}

	/**
	 * Returns a reference to the event manager.
	 * 
	 * @return the eventManager
	 */
	public EventManager getEventManager() {
		return eventManager;
	}

	/**
	 * Returns the width in pixels.
	 * 
	 * @return the width
	 */
	public int getWidthPixels() {
		return widthPixels;
	}

	/**
	 * Returns the height in pixels.
	 * 
	 * @return the height
	 */
	public int getHeightPixels() {
		return heightPixels;
	}

	/**
	 * Returns the actual x position in pixels.
	 * 
	 * @return the x position
	 */
	public int getPositionXPixels() {
		return positionXPixels;
	}

	/**
	 * Returns the actual y position in pixels.
	 * 
	 * @return the y position
	 */
	public int getPositionYPixels() {
		return positionYPixels;
	}	
	
}
