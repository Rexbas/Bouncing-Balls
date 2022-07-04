package bouncing_balls.common.capabilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface IJumpCapability {
	
	boolean canJump(PlayerEntity player);
	boolean canJumpInAir(ItemStack requiredItem, PlayerEntity player);
	int jumpsInAir();
	void setJumpsInAir(int jumpsInAir);
	float fallDistance();
	void setFallDistance(float newFallDistance);
	boolean check(PlayerEntity player);
	int ticksOnGround();
	void setTicksOnGround(int ticks);
}