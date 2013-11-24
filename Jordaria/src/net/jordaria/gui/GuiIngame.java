package net.jordaria.gui;

import org.lwjgl.opengl.GL11;

import net.jordaria.Jordaria;

public class GuiIngame extends Gui{

	private final Jordaria jordaria;

	public GuiIngame(Jordaria jd){
		jordaria = jd;
	}

	public void RenderCube(){
		if (jordaria.gameSettings.wireframe){
			GL11.glBegin(GL11.GL_LINE_LOOP);
		}
		else{
			GL11.glBegin(GL11.GL_QUADS);
		}
		GL11.glColor3f(1.0f,1.0f,0.0f);
		GL11.glVertex3f( 1.0f, 1.0f,-1.0f);
		GL11.glVertex3f(-1.0f, 1.0f,-1.0f);        
		GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
		GL11.glVertex3f( 1.0f, 1.0f, 1.0f);
		GL11.glColor3f(1.0f,0.5f,0.0f);
		GL11.glVertex3f( 1.0f,-1.0f, 1.0f);
		GL11.glVertex3f(-1.0f,-1.0f, 1.0f);
		GL11.glVertex3f(-1.0f,-1.0f,-1.0f);
		GL11.glVertex3f( 1.0f,-1.0f,-1.0f);
		GL11.glColor3f(1.0f,0.0f,0.0f);
		GL11.glVertex3f( 1.0f, 1.0f, 1.0f);
		GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
		GL11.glVertex3f(-1.0f,-1.0f, 1.0f);
		GL11.glVertex3f( 1.0f,-1.0f, 1.0f);
		GL11.glColor3f(1.0f,1.0f,0.0f);
		GL11.glVertex3f( 1.0f,-1.0f,-1.0f);
		GL11.glVertex3f(-1.0f,-1.0f,-1.0f);
		GL11.glVertex3f(-1.0f, 1.0f,-1.0f);
		GL11.glVertex3f( 1.0f, 1.0f,-1.0f);
		GL11.glColor3f(0.0f,0.0f,1.0f);
		GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
		GL11.glVertex3f(-1.0f, 1.0f,-1.0f);
		GL11.glVertex3f(-1.0f,-1.0f,-1.0f);
		GL11.glVertex3f(-1.0f,-1.0f, 1.0f);
		GL11.glColor3f(1.0f,0.0f,1.0f);
		GL11.glVertex3f( 1.0f, 1.0f,-1.0f);
		GL11.glVertex3f( 1.0f, 1.0f, 1.0f);
		GL11.glVertex3f( 1.0f,-1.0f, 1.0f);
		GL11.glVertex3f( 1.0f,-1.0f,-1.0f);
		GL11.glEnd();
	}


}
