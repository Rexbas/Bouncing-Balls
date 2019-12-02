package bouncing_balls;

import bouncing_balls.capability.IJumpCapability;
import bouncing_balls.capability.JumpProvider;
import bouncing_balls.item.BallType;
import bouncing_balls.item.BouncingBall;
import bouncing_balls.jump.BouncingBallJump;
import bouncing_balls.jump.JumpHandler;
import bouncing_balls.jump.JumpType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BouncingBallsEventHandler {
	
	@SubscribeEvent
	public void attachtCapability (AttachCapabilitiesEvent<Entity> event) {	
		if(event.getObject() instanceof PlayerEntity) {
			event.addCapability(new ResourceLocation(BouncingBalls.MODID, "capability.jump"), new JumpProvider());
		}
	}
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		if(event.getEntityLiving() instanceof PlayerEntity) {			
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			
			LazyOptional<IJumpCapability> cap = player.getCapability(JumpProvider.JUMP_CAPABILITY, player.getHorizontalFacing());
			cap.ifPresent(c -> {
				float fallDistance = c.fallDistance();
				
				int ticks = c.ticksOnGround();
				
				if(player.onGround && ticks < 50) {
					c.setTicksOnGround(ticks + 1);
				}
				if(!player.onGround && ticks != 0) {
					c.setTicksOnGround(0);
				}
				if(player.onGround) {
					c.setJumpsInAir(0);
				}
				if(player.fallDistance > fallDistance) {
					c.setFallDistance(player.fallDistance);
				}
				
				BouncingBall ball = null;
				ItemStack ballStack = null;
				if(player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof BouncingBall &&
						player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() instanceof BouncingBall) {
					ball = (BouncingBall) player.getHeldItemMainhand().getItem();
					ballStack = player.getHeldItemMainhand(); 
				}
				else if(player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof BouncingBall) {
					ball = (BouncingBall) player.getHeldItemMainhand().getItem();
					ballStack = player.getHeldItemMainhand();
				}
				else if(player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() instanceof BouncingBall) {
					ball = (BouncingBall) player.getHeldItemOffhand().getItem();
					ballStack = player.getHeldItemOffhand();
				}
					
				if(ball != null && ballStack != null) {	
					if(c.canJump(player)) {
						if(ball.getBallType() != BallType.EGG && ball.getBallType() != BallType.SNOW && ball.getBallType() != BallType.DYNAMITE && fallDistance >= ball.getFallJumpHeight()) {
							BouncingBallJump jump = new BouncingBallJump(player, ballStack, JumpType.FALL_JUMP);
							JumpHandler.jump(jump);
						}
						if(ball.getBallType() == BallType.EGG && fallDistance >= ball.getFallJumpHeight()) {
							BouncingBallJump jump = new BouncingBallJump(player, ballStack, JumpType.EGG_JUMP);
							JumpHandler.jump(jump);
						}
						if(ball.getBallType() == BallType.SNOW && fallDistance >= ball.getFallJumpHeight()) {
							BouncingBallJump jump = new BouncingBallJump(player, ballStack, JumpType.SNOWBALL_JUMP);
							JumpHandler.jump(jump);
						}
						if(ball.getBallType() == BallType.DYNAMITE && fallDistance >= ball.getFallJumpHeight()) {
							BouncingBallJump jump = new BouncingBallJump(player, ballStack, JumpType.DYNAMITE_JUMP);
							JumpHandler.jump(jump);
						}
					}
				}
				if(player.onGround) {
					c.setFallDistance(0);
				}
				if(player.isInWater() || player.isInLava()) {
					c.setFallDistance(0);
				}
			});
		}	
	}		
	
	
	@SubscribeEvent
	public void onPlayerFall(LivingFallEvent event) {
		if(event.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			if(player.getHeldItemMainhand() != null) {
				if(player.getHeldItemMainhand().getItem() instanceof BouncingBall) {
					event.setCanceled(true);
				}
			}
			if(player.getHeldItemOffhand() != null) {
				if(player.getHeldItemOffhand().getItem() instanceof BouncingBall) {
					event.setCanceled(true);
				}
			}
		}
	}
}
