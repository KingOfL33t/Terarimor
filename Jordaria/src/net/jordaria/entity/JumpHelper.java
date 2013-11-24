package net.jordaria.entity;

public class JumpHelper {
	private EntityLiving entity;
    private boolean isJumping;

    public JumpHelper(EntityLiving livingEntity)
    {
        this.entity = livingEntity;
    }

    public void setJumping()
    {
        this.isJumping = true;
    }

    public void doJump()
    {
        this.entity.setJumping(this.isJumping);
        this.isJumping = false;
    }
}
