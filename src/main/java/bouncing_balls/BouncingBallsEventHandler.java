package bouncing_balls;

import bouncing_balls.capability.BB_CAPProvider;
import bouncing_balls.capability.IBB_CAP;
import bouncing_balls.item.BouncingBall;
import bouncing_balls.jump.BouncingBallJump;
import bouncing_balls.jump.JumpHandler;
import bouncing_balls.jump.JumpType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class BouncingBallsEventHandler {
	
	@SubscribeEvent
	public void attachtCapability(AttachCapabilitiesEvent.Entity event) {		
		if(event.getEntity() instanceof EntityPlayer) {
			event.addCapability(new ResourceLocation(BouncingBalls.MODID + ":BBCAP"), new BB_CAPProvider(event));
		}
	}	
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		if(event.getEntityLiving() instanceof EntityPlayer) {			
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			World world = player.world;
			IBB_CAP capability = player.getCapability(BouncingBalls.BB_CAP, player.getHorizontalFacing());
			float fallDistance = capability.fallDistance();
			
			int ticks = capability.ticksOnGround();
			
			if(player.onGround && ticks < 50) {
				capability.setTicksOnGround(ticks + 1);
			}
			if(!player.onGround && ticks != 0) {
				capability.setTicksOnGround(0);
			}
			if(player.onGround) {
				capability.setJumpsInAir(0);
			}
			if(player.fallDistance > fallDistance) {
				capability.setFallDistance(player.fallDistance);
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
				if(capability.canJump()) {
					if(ball.getID() != 16 && ball.getID() != 17 && ball.getID() != 18 && fallDistance >= ball.getFallJumpHeight()) {
						BouncingBallJump jump = new BouncingBallJump(player, ballStack, JumpType.FALL_JUMP);
						JumpHandler.jump(jump);
					}
					if(ball.getID() == 16 && fallDistance >= ball.getFallJumpHeight()) {
						BouncingBallJump jump = new BouncingBallJump(player, ballStack, JumpType.EGG_JUMP);
						JumpHandler.jump(jump);
					}
					if(ball.getID() == 17 && fallDistance >= ball.getFallJumpHeight()) {
						BouncingBallJump jump = new BouncingBallJump(player, ballStack, JumpType.SNOWBALL_JUMP);
						JumpHandler.jump(jump);
					}
					if(ball.getID() == 18 && fallDistance >= ball.getFallJumpHeight()) {
						BouncingBallJump jump = new BouncingBallJump(player, ballStack, JumpType.DYNAMITE_JUMP);
						JumpHandler.jump(jump);
					}
				}
			}
			if(player.onGround) {
				capability.setFallDistance(0);
			}
			if(player.isInWater() || player.isInLava()) {
				capability.setFallDistance(0);
			}
		}	
	}		
	
	
	@SubscribeEvent
	public void onPlayerFall(LivingFallEvent event) {
		if(event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
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
