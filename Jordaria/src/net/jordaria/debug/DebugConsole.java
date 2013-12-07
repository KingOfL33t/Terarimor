package net.jordaria.debug;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;

import net.jordaria.Configuration;
import net.jordaria.event.DebugMessage;
import net.jordaria.event.EventHandler;
import net.jordaria.event.EventSystemStarted;
import net.jordaria.event.Listener;

public class DebugConsole extends WindowAdapter implements Listener{

	private String window_title = "Debug Console";
	private int width = 300; 
	private int height = 200;

	private JFrame frame;
	private JTextArea textArea;

	public DebugConsole(){
		frame = new JFrame(window_title);
		frame.setSize(width, height);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(new Color(2, 3, 2));
		textArea.setForeground(new Color(2,200,2));
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(new JScrollPane(textArea),BorderLayout.CENTER);

		frame.setVisible(true);
	}

	//Adds the message to the screen and checks if it needs to remove the top line
	public void appendMessage(String message){
		this.textArea.append(message+"\n");

		if (this.textArea.getLineCount()>=100){
			int end;
			try {
				end = this.textArea.getLineEndOffset(0);
				this.textArea.replaceRange("", 0, end);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
	}

	@EventHandler
	public void onDebugMessage(DebugMessage event){
		this.appendMessage(event.getMessage());
	}

	@EventHandler
	public void onEventSystemStarted(EventSystemStarted event){
		if (Configuration.DEBUG_SHOW_STARTINGSYSTEMS){
			this.appendMessage("Event system started");
		}
	}
}
