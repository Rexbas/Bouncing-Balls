package bouncing_balls;

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
import bouncing_balls.capability.BB_CAPProvider;
import bouncing_balls.capability.IBB_CAP;
import bouncing_balls.configuration.ConfigurationHandler;
import bouncing_balls.item.BouncingBall;
import bouncing_balls.jump.BouncingBallJump;
import bouncing_balls.jump.JumpHandler;
import bouncing_balls.jump.JumpType;

public class BouncingBallsEventHandler {
	
	@SubscribeEvent
	public void attachtCapability(AttachCapabilitiesEvent.Entity event) {		
		if(event.getEntity() instanceof EntityPlayer) {
			event.addCapability(new ResourceLocation(BouncingBalls.MODID + ":BBCAP"), new BB_CAPProvider(event));
		}
	}	
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		if(event.entityLiving instanceof EntityPlayer) {			
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			World world = player.worldObj;
			IBB_CAP capability = player.getCapability(BouncingBalls.BB_CAP, player.func_181012_aH());
			float fallDistance = capability.fallDistance();
			
			if(!world.isRemote) {
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
				
				if(player.getHeldItem() != null) {
					if(player.getHeldItem().getItem() instanceof BouncingBall && capability.canJump()) {
						ItemStack ballStack = player.getHeldItem();
						BouncingBall ballItem = (BouncingBall) player.getHeldItem().getItem();
						if(ballItem.getID() != 16 && ballItem.getID() != 17 && fallDistance >= ballItem.getFallJumpHeight()) {
							BouncingBallJump jump = new BouncingBallJump(player, ballStack, JumpType.FALL_JUMP);
							JumpHandler.jump(jump);
						}
						if(ballItem.getID() == 16 && fallDistance >= ballItem.getFallJumpHeight()) {
							BouncingBallJump jump = new BouncingBallJump(player, ballStack, JumpType.EGG_JUMP);
							JumpHandler.jump(jump);
						}
						if(ballItem.getID() == 17 && fallDistance >= ballItem.getFallJumpHeight()) {
							BouncingBallJump jump = new BouncingBallJump(player, ballStack, JumpType.SNOWBALL_JUMP);
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
	}
	
	@SubscribeEvent
	public void onPlayerFall(LivingFallEvent event) {
		if(event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if(player.getHeldItem() != null) {
				if(player.getHeldItem().getItem() instanceof BouncingBall) {
					event.setCanceled(true);
				}
			}
		}
	}
	
	@SubscribeEvent(priority=EventPriority.HIGHEST, receiveCanceled=true)
	public void onEvent(PlayerTickEvent event) {
		if(!BouncingBalls.haveWarnedVersionOutOfDate && event.player.worldObj.isRemote && !BouncingBalls.updateChecker.isLatestVersion() && ConfigurationHandler.showUpdateCheck) {
			BouncingBalls.updateChecker.updateStatus(event.player);
		}
	}
	
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event){
    	if(event.modID.equals(BouncingBalls.MODID)){
    		ConfigurationHandler.syncConfig();
    	}
    }
}
