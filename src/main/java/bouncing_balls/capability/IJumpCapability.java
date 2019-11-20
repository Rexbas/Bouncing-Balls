package bouncing_balls.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IJumpCapability {
	
	boolean canJump(EntityPlayer player);
	boolean canJumpInAir(ItemStack requiredItem, EntityPlayer player);
	int jumpsInAir();
	void setJumpsInAir(int jumpsInAir);
	float fallDistance();
	void setFallDistance(float newFallDistance);
	boolean check(EntityPlayer player);
	int ticksOnGround();
	void setTicksOnGround(int ticks);
}
