package net.jordaria.gui;

import net.jordaria.Jordaria;
import net.jordaria.KeyBind;
import net.jordaria.entity.Direction;
import net.jordaria.event.DebugMessage;
import net.jordaria.event.EntityMoveRequest;
import net.jordaria.event.EventHandler;
import net.jordaria.event.Listener;
import net.jordaria.event.ShuttingDown;
import net.jordaria.event.Tick;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class MainWindow implements Listener{
	Jordaria jd;
	DisplayMode displayMode;
	int size = 10;
	float r = 0;
	float g = 0;
	float b = 0;

	public MainWindow(Jordaria jordaria){
		this.jd = jordaria;
	}
	public void createWindow() throws Exception{
		Display.setFullscreen(false);
		DisplayMode d[] = Display.getAvailableDisplayModes();
		for (int i = 0; i < d.length; i++){
			if (d[i].getWidth() == Jordaria.config.getWindow_width()
					&& d[i].getHeight() == Jordaria.config.getWindow_height() 
					&& d[i].getBitsPerPixel() == Jordaria.config.getDisplay_bitsPerPixel()){
				displayMode = d[i];
				break;
			}
		}
		Display.setDisplayMode(displayMode);
		Display.setTitle(Jordaria.config.getWindow_title());
		Display.create();
		this.InitGL();
	}
	private void fillRect(float posX, float posY, float width, float height, float r, float g, float b){
		GL11.glBegin(GL11.GL_TRIANGLES);

		// top left
		GL11.glColor3f(r, g, b);
		GL11.glVertex2f(posX, posY);

		// top right
		GL11.glColor3f(r, g, b);
		GL11.glVertex2f(posX+width, posY);

		// bottom right
		GL11.glColor3f(r, g, b);
		GL11.glVertex2f(posX+width, posY+height);

		// bottom right
		GL11.glColor3f(r, g, b);
		GL11.glVertex2f(posX+width, posY+height);

		//bottom left
		GL11.glColor3f(r, g, b);
		GL11.glVertex2f(posX, posY+height);

		//top left
		GL11.glColor3f(r, g, b);
		GL11.glVertex2f(posX, posY);

		GL11.glEnd();
	}
	private void fillTriangleTop(float posX, float posY, float width, float height, float r, float g, float b){
		GL11.glBegin(GL11.GL_TRIANGLES);

		// top left
		GL11.glColor3f(r, g, b);
		GL11.glVertex2f(posX, posY);

		// top right
		GL11.glColor3f(r, g, b);
		GL11.glVertex2f(posX+width, posY);

		// bottom right
		GL11.glColor3f(r, g, b);
		GL11.glVertex2f(posX+width, posY+height);

		GL11.glEnd();
	}
	private void fillTriangleBottom(float posX, float posY, float width, float height, float r, float g, float b){
		GL11.glBegin(GL11.GL_TRIANGLES);

		// bottom right
		GL11.glColor3f(r, g, b);
		GL11.glVertex2f(posX+width, posY+height);

		//bottom left
		GL11.glColor3f(r, g, b);
		GL11.glVertex2f(posX, posY+height);

		//top left
		GL11.glColor3f(r, g, b);
		GL11.glVertex2f(posX, posY);

		GL11.glEnd();
	}
	public void handleKeyboard(){
		while (Keyboard.next()){
			KeyBind.setKeyBindState(Keyboard.getEventKey(), Keyboard.getEventKeyState());
			if (Keyboard.getEventKeyState())
			{
				KeyBind.onTick(Keyboard.getEventKey());
			}
			while (jd.getGameSettings().KEYBIND_MOVE_FORWARD.isPressed())
			{
				jd.getEventManager().fireEvent(new EntityMoveRequest(jd.thePlayer, jd.thePlayer.direction));
				if (Jordaria.config.getDebugActive() && Jordaria.config.getDEBUG_SHOW_KEYPRESSES())
					jd.getEventManager().fireEvent(new DebugMessage("Key pressed! ("+jd.getGameSettings().KEYBIND_MOVE_FORWARD.keyDescription+")"));
			}
			while (jd.getGameSettings().KEYBIND_MOVE_BACKWARD.isPressed())
			{
				jd.getEventManager().fireEvent(new EntityMoveRequest(jd.thePlayer, jd.thePlayer.direction.getRelativeDirection(new Direction(180))));
				if (Jordaria.config.getDebugActive() && Jordaria.config.getDEBUG_SHOW_KEYPRESSES())
					jd.getEventManager().fireEvent(new DebugMessage("Key pressed! ("+jd.getGameSettings().KEYBIND_MOVE_BACKWARD.keyDescription+")"));
			}
			while (jd.getGameSettings().KEYBIND_MOVE_LEFT.isPressed())
			{
				jd.getEventManager().fireEvent(new EntityMoveRequest(jd.thePlayer, jd.thePlayer.direction.getRelativeDirection(new Direction(90))));
				if (Jordaria.config.getDebugActive() && Jordaria.config.getDEBUG_SHOW_KEYPRESSES())
					jd.getEventManager().fireEvent(new DebugMessage("Key pressed! ("+jd.getGameSettings().KEYBIND_MOVE_LEFT.keyDescription+")"));
			}
			while (jd.getGameSettings().KEYBIND_MOVE_RIGHT.isPressed())
			{
				jd.getEventManager().fireEvent(new EntityMoveRequest(jd.thePlayer, jd.thePlayer.direction.getRelativeDirection(new Direction(-90))));
				if (Jordaria.config.getDebugActive() && Jordaria.config.getDEBUG_SHOW_KEYPRESSES())
					jd.getEventManager().fireEvent(new DebugMessage("Key pressed! ("+jd.getGameSettings().KEYBIND_MOVE_RIGHT.keyDescription+")"));
			}
			while (jd.getGameSettings().KEYBIND_WIREFRAME.isPressed())
			{
				jd.getGameSettings().toggleWireframe();
				if (Jordaria.config.getDebugActive() && Jordaria.config.getDEBUG_SHOW_KEYPRESSES()){
					jd.getEventManager().fireEvent(new DebugMessage("Key pressed! ("+jd.getGameSettings().KEYBIND_WIREFRAME.keyDescription+")"));

				}
			}

		}
	}

	private void InitGL(){
		GL11.glEnable(GL11.GL_TEXTURE_2D);//enable mapping textures to faces or quads
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
		GL11.glShadeModel(GL11.GL_SMOOTH);//makes surfaces prettier :3
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);//Sets the background color
		GL11.glClearDepth(1.0);//the depth used when the depth buffer is cleared
		GL11.glEnable(GL11.GL_DEPTH_TEST);//allows depth testing, makes rendering more efficient
		GL11.glDepthFunc(GL11.GL_LEQUAL);//what kind of depth testing to use

		GL11.glMatrixMode(GL11.GL_PROJECTION);//modify pixel projection matrix
		GL11.glLoadIdentity();

		//set up the camera
		//this is a 3d camera
		//GLU.gluPerspective(45.0f, (float)displayMode.getWidth()/ (float)displayMode.getHeight(), 0.1f, 10000.0f);
		//this is an orthographic camera
		GL11.glOrtho(0, size, 0, size, -1, 1);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);//modify the orientation and location matrix
		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);

		Display.sync(60);
	}
	@EventHandler
	public void onShutdown(ShuttingDown shutdown){
		Display.destroy();
	}
	@EventHandler
	public void onTick(Tick event){
		this.tick();
	}
	public void tick(){
		this.handleKeyboard();
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		int x,y;
		for (x=0; x<size; x++){
			for (y=0; y<size; y++){
				r = jd.getRandom().nextFloat();
				g = jd.getRandom().nextFloat();
				b = jd.getRandom().nextFloat();
				fillRect(x, y, 1.0f, 1.0f, r, g, b);
			}
		}

		Display.update();

		if (Display.isCloseRequested()){
			jd.getEventManager().fireEvent(new ShuttingDown());
		}
	}

}
