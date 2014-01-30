package net.jordaria.text;

/**
 * A string of text with a {@link Color color}.
 * 
 * @author Ches Burks
 *
 */
public class ColoredText {
	String text;
	Color color;
	
	/**
	 * Construct a new ColoredText with the given string.
	 * The text is black by default.
	 * 
	 * @param text The text to use
	 */
	public ColoredText(String text){
		this.text = text;
		this.color = Color.BLACK;
	}
	
	/**
	 * Construct a new ColoredText with the given string and {@link Color color}.
	 * 
	 * @param text The text to use
	 * @param color The color of the text
	 */
	public ColoredText(String text, Color color){
		this.text = text;
		this.color = color;
	}
	
	/**
	 * Returns the text string.
	 * 
	 * @return The text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Sets the text string to the given text.
	 * 
	 * @param text The new text
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Returns the texts color.
	 * 
	 * @return The color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Sets the texts color to the given color.
	 * 
	 * @param color The new color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
}
