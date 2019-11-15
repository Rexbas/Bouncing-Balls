package bouncing_balls.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import bouncing_balls.BouncingBalls;
import bouncing_balls.item.BouncingBall;

public class BB_CAPProvider implements ICapabilityProvider, IBB_CAP {

	private EntityPlayer player;
	private AttachCapabilitiesEvent.Entity event;
	private int jumpsInAir = 0;
	private float fallDistance;
	private float manualFallDistance;
	private int ticksOnGround;
	
	public BB_CAPProvider(AttachCapabilitiesEvent.Entity event) {
		this.player = (EntityPlayer) event.getEntity();
		this.event = event;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == BouncingBalls.BB_CAP) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(BouncingBalls.BB_CAP != null && capability == BouncingBalls.BB_CAP) {
			return (T) this;
		}
		else {
			return null;
		}
	}
	
	@Override
	public boolean canJump() {
		if(player.onGround && !player.isInWater() && !player.isInLava()) {
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public boolean canJumpInAir(ItemStack neededItem) {
		if(!player.isInWater() && !player.isInLava() &&
				player.inventory.hasItemStack(neededItem) && this.jumpsInAir < 2) {
			return true;
		}
		else {
			return false;
		}
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
	public boolean check() {
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