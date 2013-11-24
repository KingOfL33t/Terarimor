package net.jordaria.entity;

import net.jordaria.GameSettings;

public class PlayerMoveHelper {
	private GameSettings gameSettings;
	
	 //The speed at which the player is moving forward. Negative numbers will move backwards.
	public float moveStrafe;
	
    public float moveForward;
    public boolean jump;
    public boolean sneak;

    public PlayerMoveHelper(GameSettings gameSettings)
    {
        this.gameSettings = gameSettings;
    }

    public void updatePlayerMoveState()
    {
        this.moveStrafe = 0.0F;
        this.moveForward = 0.0F;

        if (this.gameSettings.KEYBIND_MOVE_FORWARD.isPressed())
        {
            ++this.moveForward;
        }

        if (this.gameSettings.KEYBIND_MOVE_BACKWARD.isPressed())
        {
            --this.moveForward;
        }

        if (this.gameSettings.KEYBIND_MOVE_LEFT.isPressed())
        {
            ++this.moveStrafe;
        }

        if (this.gameSettings.KEYBIND_MOVE_RIGHT.isPressed())
        {
            --this.moveStrafe;
        }

        this.jump = this.gameSettings.KEYBIND_MOVE_JUMP.isPressed();
    }

}
