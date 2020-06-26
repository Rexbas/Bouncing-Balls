package bouncing_balls.common.capabilities;

import bouncing_balls.item.BouncingBall;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class JumpCapability implements IJumpCapability {

	private int jumpsInAir = 0;
	private float fallDistance;
	private float manualFallDistance;
	private int ticksOnGround;
	
	public JumpCapability() {}
	
	@Override
	public boolean canJump(PlayerEntity player) {
		if(player.func_233570_aj_() && !player.isInWater() && !player.isInLava()) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canJumpInAir(ItemStack requiredItem, PlayerEntity player) {
		if(!player.isInWater() && !player.isInLava() &&
				player.inventory.hasItemStack(requiredItem) && this.jumpsInAir < 2) {
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
	public boolean check(PlayerEntity player) {
		BouncingBall ball = null;
		if(player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof BouncingBall &&
				player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() instanceof BouncingBall) {
			ball = (BouncingBall) player.getHeldItemMainhand().getItem();
		}
		else if(player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof BouncingBall) {
			ball = (BouncingBall) player.getHeldItemMainhand().getItem();
		}
		else {
			ball = (BouncingBall) player.getHeldItemOffhand().getItem();
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
