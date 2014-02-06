package net.jordaria.gui;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;

/**
 * Extends {@link JTextArea} and allows messages to be appended and a limited history.
 * Clearing of early lines is handled with the appending of lines automatically.
 * 
 * @author Ches Burks
 *
 */
public class ScrollingTextArea extends JTextArea{
	
	private static final long serialVersionUID = 5849426766182247126L;

	private int maxHistory = 100;//The maximum lines this area can have
	
	public ScrollingTextArea(){
		super();
		DefaultCaret caret = (DefaultCaret)getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		setEditable(false);
	}
	/**
	 * Sets the maximum number of lines this area can contain.
	 * 
	 * @param lines The maximum number of lines allowed
	 */
	public void setMaxHistory(int lines){
		this.maxHistory = lines;
	}
	
	/**
	 * Returns the maximum number of lines this area can contain. 
	 * 
	 * @return The number of lines allowed
	 */
	public int getMaxHistory(){
		return this.maxHistory;
	}
	
	/**
	 * Adds a message to the bottom of the area and removes lines from 
	 * the top if there are more lines than the history allows.
	 * 
	 * @param message The message to append
	 */
	public void appendMessage(String message){

		this.append(message+"\n");

		while (this.getLineCount()>=this.maxHistory){
			int end;
			try {
				end = this.getLineEndOffset(0);
				this.replaceRange("", 0, end);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
	}
	
}
