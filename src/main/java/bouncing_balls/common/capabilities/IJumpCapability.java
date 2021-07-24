package bouncing_balls.common.capabilities;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface IJumpCapability {
	
	boolean canJump(Player player);
	boolean canJumpInAir(ItemStack requiredItem, Player player);
	int jumpsInAir();
	void setJumpsInAir(int jumpsInAir);
	float fallDistance();
	void setFallDistance(float newFallDistance);
	boolean check(Player player);
	int ticksOnGround();
	void setTicksOnGround(int ticks);
}
