package bouncing_balls;

import bouncing_balls.api.capability.BounceCapabilityProvider;
import bouncing_balls.api.item.IBouncingBall;
import bouncing_balls.common.capabilities.IJumpCapability;
import bouncing_balls.common.capabilities.JumpProvider;
import bouncing_balls.item.BallType;
import bouncing_balls.item.BouncingBallOld;
import bouncing_balls.jump.BouncingBallJump;
import bouncing_balls.jump.JumpHandler;
import bouncing_balls.jump.JumpType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BouncingBalls.MODID)
public class BouncingBallsEventHandler {
	
	@SubscribeEvent
	public static void attachtCapability(AttachCapabilitiesEvent<Entity> event) {	
		if(event.getObject() instanceof PlayerEntity) {
			event.addCapability(new ResourceLocation(BouncingBalls.MODID, "capability.jump"), new JumpProvider());
			event.addCapability(new ResourceLocation(BouncingBalls.MODID, "capability.bounce"), new BounceCapabilityProvider());
		}
	}
	
	@SubscribeEvent
	public static void tick(PlayerTickEvent event) {
		event.player.getCapability(BounceCapabilityProvider.BOUNCE_CAPABILITY).ifPresent(cap -> {
			if (event.phase == TickEvent.Phase.START) {
				cap.setStartTickOnGround(event.player.isOnGround());
			}
			else if (event.phase == TickEvent.Phase.END) {
				if (cap.getConsecutiveBounces() > 0 && event.player.isOnGround() && cap.getStartTickOnGround()) {
					cap.resetConsecutiveBounces();
				}
			}
		});
	}
	
	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event) {
		if(event.getEntityLiving() instanceof PlayerEntity) {			
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			
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
				
				BouncingBallOld ball = null;
				ItemStack ballStack = null;
				if(player.getMainHandItem() != null && player.getMainHandItem().getItem() instanceof BouncingBallOld &&
						player.getOffhandItem() != null && player.getOffhandItem().getItem() instanceof BouncingBallOld) {
					ball = (BouncingBallOld) player.getMainHandItem().getItem();
					ballStack = player.getMainHandItem(); 
				}
				else if(player.getMainHandItem() != null && player.getMainHandItem().getItem() instanceof BouncingBallOld) {
					ball = (BouncingBallOld) player.getMainHandItem().getItem();
					ballStack = player.getMainHandItem();
				}
				else if(player.getOffhandItem() != null && player.getOffhandItem().getItem() instanceof BouncingBallOld) {
					ball = (BouncingBallOld) player.getOffhandItem().getItem();
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
	public static void onCreativePlayerFall(PlayerFlyableFallEvent event) {
		if (event.getEntity() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntity();
			player.getCapability(BounceCapabilityProvider.BOUNCE_CAPABILITY).ifPresent(cap -> {
				cap.resetConsecutiveBounces();
			});
			// TODO Rebounce does not work and is not needed for creative mode
			if (player.getMainHandItem().getItem() instanceof IBouncingBall) {
				((IBouncingBall) player.getMainHandItem().getItem()).onFall(player, player.getMainHandItem(), event.getDistance());
			}
			else if (player.getOffhandItem().getItem() instanceof IBouncingBall) {
				((IBouncingBall) player.getOffhandItem().getItem()).onFall(player, player.getOffhandItem(), event.getDistance());
			}
			player.hurtMarked = true;
		}
	}
	
	@SubscribeEvent
	public static void onPlayerFall(LivingFallEvent event) {
		if (event.getEntity() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntity();
			player.getCapability(BounceCapabilityProvider.BOUNCE_CAPABILITY).ifPresent(cap -> {
				cap.resetConsecutiveBounces();
			});
			
			float multiplier = 1;
			if (player.getMainHandItem().getItem() instanceof IBouncingBall) {
				multiplier = ((IBouncingBall) player.getMainHandItem().getItem()).onFall(player, player.getMainHandItem(), event.getDistance());
			}
			else if (player.getOffhandItem().getItem() instanceof IBouncingBall) {
				multiplier = ((IBouncingBall) player.getOffhandItem().getItem()).onFall(player, player.getOffhandItem(), event.getDistance());
			}
			event.setDamageMultiplier(multiplier);
			player.hurtMarked = true;
		}
		
		
		if(event.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			if(player.getMainHandItem() != null) {
				if(player.getMainHandItem().getItem() instanceof BouncingBallOld) {
					event.setCanceled(true);
				}
			}
			if(player.getOffhandItem() != null) {
				if(player.getOffhandItem().getItem() instanceof BouncingBallOld) {
					event.setCanceled(true);
				}
			}
		}
	}
}