package net.jordaria.debug;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.jordaria.event.DebugMessageEvent;
import net.jordaria.event.EventHandler;
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
		
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(new JScrollPane(textArea),BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
	
	@EventHandler
	public void onDebugMessage(DebugMessageEvent event){
		this.textArea.append(event.getMessage()+"\n");
	}
}
