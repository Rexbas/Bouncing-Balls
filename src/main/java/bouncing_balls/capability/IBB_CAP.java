package bouncing_balls.capability;

import net.minecraft.item.ItemStack;

public interface IBB_CAP {
	
	boolean canJump();
	boolean canJumpInAir(ItemStack neededItem);
	int jumpsInAir();
	void setJumpsInAir(int jumpsInAir);
	float fallDistance();
	void setFallDistance(float newFallDistance);
	boolean check();
	int ticksOnGround();
	void setTicksOnGround(int ticks);
}
