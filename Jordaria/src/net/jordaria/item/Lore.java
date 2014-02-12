package net.jordaria.item;

import net.jordaria.text.Color;
import net.jordaria.text.ColoredText;

/**
 * Three lines of {@link ColoredText colored text} which 
 * are given to items.
 * 
 * @author Ches Burks
 *
 */
public class Lore {
	public ColoredText line1;
	public ColoredText line2;
	public ColoredText line3;
	
	/**
	 * Constructs a new {@link Lore} with three blank lines.
	 */
	public Lore(){
		this.line1 = new ColoredText("");
		this.line2 = new ColoredText("");
		this.line3 = new ColoredText("");
	}
	/**
	 * Constructs a new {@link Lore} with one custom line.
	 * The line is the default color.
	 * The second and third lines are blank.
	 * 
	 * @param line1 The text for the first line
	 */
	public Lore(String line1){
		this.line1 = new ColoredText(line1);
		this.line2 = new ColoredText("");
		this.line3 = new ColoredText("");
	}
	/**
	 * Constructs a new {@link Lore} with one custom line and 
	 * custom line color. The second and third lines are blank.
	 * 
	 * @param line1 The text for the first line
	 * @param line1color The color for the first line
	 */
	public Lore(String line1, Color line1color){
		this.line1 = new ColoredText(line1, line1color);
		this.line2 = new ColoredText("");
		this.line3 = new ColoredText("");
	}
	/**
	 * Constructs a new {@link Lore} with two custom lines.
	 * Both lines are the default color. The third line is blank.
	 * 
	 * @param line1 The text for the first line
	 * @param line2 The text for the second line
	 */
	public Lore(String line1, String line2){
		this.line1 = new ColoredText(line1);
		this.line2 = new ColoredText(line2);
		this.line3 = new ColoredText("");
	}
	/**
	 * Constructs a new {@link Lore} with two custom lines and 
	 * custom line colors. The third line is blank.
	 * 
	 * @param line1 The text for the first line
	 * @param line1color The color for the first line
	 * @param line2 The text for the second line
	 * @param line2color The color for the second line
	 */
	public Lore(String line1, Color line1color, String line2, Color line2color){
		this.line1 = new ColoredText(line1, line1color);
		this.line2 = new ColoredText(line2, line2color);
		this.line3 = new ColoredText("");
	}
	/**
	 * Constructs a new {@link Lore} with three custom lines.
	 * All three lines are the default color.
	 * 
	 * @param line1 The text for the first line
	 * @param line2 The text for the second line
	 * @param line3 The text for the third line
	 */
	public Lore(String line1, String line2, String line3){
		this.line1 = new ColoredText(line1);
		this.line2 = new ColoredText(line2);
		this.line3 = new ColoredText(line3);
	}
	/**
	 * Constructs a new {@link Lore} with three custom lines and 
	 * custom line colors.
	 * 
	 * @param line1 The text for the first line
	 * @param line1color The color for the first line
	 * @param line2 The text for the second line
	 * @param line2color The color for the second line
	 * @param line3 The text for the third line
	 * @param line3color The color for the third line
	 */
	public Lore(String line1, Color line1color, String line2, Color line2color, String line3, Color line3color){
		this.line1 = new ColoredText(line1, line1color);
		this.line2 = new ColoredText(line2, line2color);
		this.line3 = new ColoredText(line3, line3color);
	}
	
	/**
	 * Returns the {@link ColoredText first line}.
	 * 
	 * @return The first line
	 */
	public ColoredText getLine1(){
		return line1;
	}
	/**
	 * Returns the {@link ColoredText second line}.
	 * 
	 * @return The second line
	 */
	public ColoredText getLine2(){
		return line2;
	}
	/**
	 * Returns the {@link ColoredText first line}.
	 * 
	 * @return The first line
	 */
	public ColoredText getLine3(){
		return line3;
	}
	
	/**
	 * Returns the line with the given 
	 * index. The index of the first line is 1, second is 2, and so on. 
	 * If the line requested is not a valid line, a blank {@link ColoredText} 
	 * will be returned.
	 * 
	 * @return The line requested
	 */
	public ColoredText getLine(int index){
		switch(index){
		case 1 : return getLine1();
		case 2 : return getLine2();
		case 3 : return getLine3();
		default : return new ColoredText("");
		}
	}
	
	/**
	 * Returns how many lines contain text.
	 * 
	 * @return The number of non-empty lines
	 */
	public int getLinesUsed(){
		int count = 0;
		if (!this.line1.getText().isEmpty()){
			count++;
		}
		if (!this.line2.getText().isEmpty()){
			count++;
		}
		if (!this.line3.getText().isEmpty()){
			count++;
		}
		return count;
	}
}
