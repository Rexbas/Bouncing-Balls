package bouncing_balls.common.capabilities;

import bouncing_balls.item.BouncingBall;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class JumpCapability implements IJumpCapability {

	private int jumpsInAir = 0;
	private float fallDistance;
	private float manualFallDistance;
	private int ticksOnGround;
	
	public JumpCapability() {}
	
	@Override
	public boolean canJump(Player player) {
		if(player.isOnGround() && !player.isInWater() && !player.isInLava()) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canJumpInAir(ItemStack requiredItem, Player player) {
		if(!player.isInWater() && !player.isInLava() &&
				player.getInventory().contains(requiredItem) && this.jumpsInAir < 2) {
			return true;
		}
		return false;
	}
	
	@Override
	public int jumpsInAir() {
		return this.jumpsInAir;
	}
	
	@Override
	public void setJumpsInAir(int jumpsInAir) {
		this.jumpsInAir = jumpsInAir;
	}
	
	@Override
	public float fallDistance() {
		return fallDistance;
	}
	
	@Override
	public void setFallDistance(float newFallDistance) {
		this.fallDistance = newFallDistance;
		if(newFallDistance >= 10) manualFallDistance = newFallDistance;
	}

	@Override
	public boolean check(Player player) {
		BouncingBall ball = null;
		if(player.getMainHandItem() != null && player.getMainHandItem().getItem() instanceof BouncingBall &&
				player.getOffhandItem() != null && player.getOffhandItem().getItem() instanceof BouncingBall) {
			ball = (BouncingBall) player.getMainHandItem().getItem();
		}
		else if(player.getMainHandItem() != null && player.getMainHandItem().getItem() instanceof BouncingBall) {
			ball = (BouncingBall) player.getMainHandItem().getItem();
		}
		else {
			ball = (BouncingBall) player.getOffhandItem().getItem();
		}
		
		float height = ball.getFallJumpHeight();
		if(this.ticksOnGround > 2 || manualFallDistance < height) {
			manualFallDistance = 0;
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int ticksOnGround() {
		return this.ticksOnGround;
	}

	@Override
	public void setTicksOnGround(int ticks) {
		this.ticksOnGround = ticks;
	}
}
