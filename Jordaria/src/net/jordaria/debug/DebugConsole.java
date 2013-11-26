package net.jordaria.debug;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DebugConsole extends WindowAdapter{
	
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
		
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(new JScrollPane(textArea),BorderLayout.CENTER);
		frame.setVisible(true);
	}
}
