package net.jordaria;

import java.awt.Canvas;

import javax.swing.JFrame;

import net.jordaria.gui.GuiIngame;

public class Jordaria extends Canvas implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1337968212257060894L;
	public boolean running = true;//A boolean used to show whether or not the game is running -King
	public static Configuration config;
	public Thread thread;
	
	public static void main(String args[]){
		config = new Configuration();
		Jordaria game = new Jordaria();
		JFrame mainFrame = new JFrame();
		
		mainFrame.setSize(config.getWindow_width(), config.getWindow_height());
		mainFrame.setTitle(config.getWindow_title());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		mainFrame.add(game);
		
		game.start();
	}

	public void start(){
		if (this.running)
		{
			return;//if the game is already in progress, dont start it again -King
		}
		this.running = true;
		this.thread = new Thread(this);
		this.thread.start();
		GuiIngame ingameGui = new GuiIngame(this);
	}
	public void stop(){
		if (!this.running){
			return;
		}
	}
	public void run(){
		while (this.running){
		}
	}
}
