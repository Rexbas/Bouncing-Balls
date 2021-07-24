package bouncing_balls;

import bouncing_balls.common.capabilities.IJumpCapability;
import bouncing_balls.common.capabilities.JumpProvider;
import bouncing_balls.item.BallType;
import bouncing_balls.item.BouncingBall;
import bouncing_balls.jump.BouncingBallJump;
import bouncing_balls.jump.JumpHandler;
import bouncing_balls.jump.JumpType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BouncingBalls.MODID)
public class BouncingBallsEventHandler {
	
	@SubscribeEvent
	public static void attachtCapability (AttachCapabilitiesEvent<Entity> event) {	
		if(event.getObject() instanceof Player) {
			event.addCapability(new ResourceLocation(BouncingBalls.MODID, "capability.jump"), new JumpProvider());
		}
	}
	
	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event) {
		if(event.getEntityLiving() instanceof Player) {			
			Player player = (Player) event.getEntityLiving();
			
			LazyOptional<IJumpCapability> cap = player.getCapability(JumpProvider.JUMP_CAPABILITY, player.getDirection());
			cap.ifPresent(c -> {
				float fallDistance = c.fallDistance();
				
				int ticks = c.ticksOnGround();
				
				if(player.isOnGround() && ticks < 50) {
					c.setTicksOnGround(ticks + 1);
				}
				if(!player.isOnGround() && ticks != 0) {
					c.setTicksOnGround(0);
				}
				if(player.isOnGround()) {
					c.setJumpsInAir(0);
				}
				if(player.fallDistance > fallDistance) {
					c.setFallDistance(player.fallDistance);
				}
				
				BouncingBall ball = null;
				ItemStack ballStack = null;
				if(player.getMainHandItem() != null && player.getMainHandItem().getItem() instanceof BouncingBall &&
						player.getOffhandItem() != null && player.getOffhandItem().getItem() instanceof BouncingBall) {
					ball = (BouncingBall) player.getMainHandItem().getItem();
					ballStack = player.getMainHandItem(); 
				}
				else if(player.getMainHandItem() != null && player.getMainHandItem().getItem() instanceof BouncingBall) {
					ball = (BouncingBall) player.getMainHandItem().getItem();
					ballStack = player.getMainHandItem();
				}
				else if(player.getOffhandItem() != null && player.getOffhandItem().getItem() instanceof BouncingBall) {
					ball = (BouncingBall) player.getOffhandItem().getItem();
					ballStack = player.getOffhandItem();
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
				if(player.isOnGround()) {
					c.setFallDistance(0);
				}
				if(player.isInWater() || player.isInLava()) {
					c.setFallDistance(0);
				}
			});
		}	
	}		
	
	
	@SubscribeEvent
	public static void onPlayerFall(LivingFallEvent event) {
		if(event.getEntityLiving() instanceof Player) {
			Player player = (Player) event.getEntityLiving();
			if(player.getMainHandItem() != null) {
				if(player.getMainHandItem().getItem() instanceof BouncingBall) {
					event.setCanceled(true);
				}
			}
			if(player.getOffhandItem() != null) {
				if(player.getOffhandItem().getItem() instanceof BouncingBall) {
					event.setCanceled(true);
				}
			}
		}
	}
}
