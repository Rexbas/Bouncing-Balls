package bouncing_balls;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import bouncing_balls.item.BouncingBall;
import bouncing_balls.throwable.CustomEntityEgg;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class BouncingBallsEventHandler {
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		if(event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			World world = player.worldObj;
			float fallDistance = player.getEntityData().getFloat("bouncing_balls_fallDistance");
			
			if(!world.isRemote && player.onGround) {
				player.getEntityData().setInteger("bouncing_balls_jumps", 0);
			}
			
			if(!world.isRemote && player.fallDistance > fallDistance) {
				setFallDistance(player, player.fallDistance);
			}
			
			if(player.getHeldItem() != null && !world.isRemote) {
				if(player.getHeldItem().getItem() instanceof BouncingBall && player.onGround && !player.isInWater()) {
					ItemStack ballStack = player.getHeldItem();
					BouncingBall ballItem = (BouncingBall) player.getHeldItem().getItem();
					if(ballItem.getID() == 23 && fallDistance >= 16) {
						doubleJump(player, ballItem, ballStack, fallDistance);
					}
					else if(ballItem.getID() != 23 && fallDistance >= 10) {
						doubleJump(player, ballItem, ballStack, fallDistance);
					}
				}
				if(player.getHeldItem().getItem() instanceof BouncingBall && player.onGround) {
					setFallDistance(player, 0);
				}
				if(!(player.getHeldItem().getItem() instanceof BouncingBall) && player.onGround) {
					setFallDistance(player, 0);
				}
				if(player.isInWater()) {
					setFallDistance(player, 0);
				}
			}
			if(player.getHeldItem() == null) {
				setFallDistance(player, 0);
			}
		}		
	} 
	
	public void doubleJump(EntityPlayer player, BouncingBall ballItem, ItemStack ballStack, float fallDistance) {
		float yaw = player.rotationYaw;
		float pitch = player.rotationPitch;
		float movingAmount = ballItem.getMovingAmount() - 0.1F;
		double motionX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * movingAmount);
		double motionY = ballItem.getMotionY() + fallDistance / 100; 
		double motionZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * movingAmount);
		
		player.addVelocity(motionX, motionY, motionZ);
		if(player instanceof EntityPlayerMP && !player.worldObj.isRemote) {
			((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(player));
		}
		if(ballItem.getID() == 16) {
			if(player.inventory.hasItem(Items.egg)) {
				player.inventory.consumeInventoryItem(Items.egg);
				player.worldObj.spawnEntityInWorld(new CustomEntityEgg(player.worldObj, player));
			}
		    Random itemRand = new Random();
			player.worldObj.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		}
		else {
		    ballStack.damageItem(1, player);
		    Random rand = new Random();
		    float pitch1 = (float) (rand.nextFloat() * (1.1 - 0.9) + 0.9);
			player.worldObj.playSoundAtEntity(player, BouncingBalls.MODID + ":bouncingball.bounce", 1, pitch1);

		}
	    setFallDistance(player, 0);
	}
	
	public void setFallDistance(EntityPlayer player, float value) {
		player.getEntityData().setFloat("bouncing_balls_fallDistance", value);
	}
		
	@SubscribeEvent
	public void onPlayerDamage(LivingAttackEvent event) {
		if(event.entityLiving instanceof EntityPlayer && event.source == DamageSource.fall) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if(player.getHeldItem() != null) {
				if(player.getHeldItem().getItem() instanceof BouncingBall) {
					event.setCanceled(true);
				}
			}
		}
	}
	
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void onEvent(PlayerTickEvent event) {
		if(!BouncingBalls.haveWarnedVersionOutOfDate && event.player.worldObj.isRemote && !BouncingBalls.updateChecker.isLatestVersion()) {
			BouncingBalls.updateChecker.updateStatus(event.player);
		}
	}
}
