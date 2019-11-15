package bouncing_balls.jump;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import bouncing_balls.BouncingBalls;
import bouncing_balls.capability.IBB_CAP;
import bouncing_balls.item.BouncingBall;
import bouncing_balls.throwable.CustomEntityEgg;
import bouncing_balls.throwable.CustomEntitySnowball;

public class JumpHandler {
	
	public static void jump(BouncingBallJump jump) {
		EntityPlayer player = jump.getPlayer();
		ItemStack stack = jump.getItemStack();
		BouncingBall ball = (BouncingBall) stack.getItem();
		World world = player.worldObj;
		
		if(!world.isRemote) {
			IBB_CAP capability = player.getCapability(BouncingBalls.BB_CAP, player.func_181012_aH());
			int jumps = capability.jumpsInAir();
			float fallDistance = capability.fallDistance();
			
			float movingAmount;
			double motionY;
			if(jump.getJumpType() == JumpType.NORMAL || jump.getJumpType() == JumpType.EGG_JUMP || jump.getJumpType() == JumpType.SNOWBALL_JUMP) {
				movingAmount = ball.getMovingAmount();
				motionY = ball.getMotionY();
			}
			else {
				movingAmount = ball.getMovingAmount() - 0.1F;
				motionY = ball.getMotionY() + fallDistance / 100;
			}
			
			float yaw = player.rotationYaw;
			float pitch = player.rotationPitch;
			double motionX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * movingAmount);
			double motionZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * movingAmount);
			
			player.addVelocity(motionX, motionY, motionZ);
			if(player instanceof EntityPlayerMP && !player.worldObj.isRemote) {
				((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(player));
			}
			
			if(jump.getJumpType() == JumpType.EGG_JUMP || jump.getJumpType() == JumpType.SNOWBALL_JUMP) {
				player.inventory.consumeInventoryItem(jump.getJumpType().getNeededItem());
				capability.setJumpsInAir(jumps + 1);
				player.inventoryContainer.detectAndSendChanges();
	            if(jump.getJumpType().getEntityThrowable() == "CustomEntityEgg") {
		            world.spawnEntityInWorld(new CustomEntityEgg(world, player));
	            }
	            else {
		            world.spawnEntityInWorld(new CustomEntitySnowball(world, player));
	            }
	            Random itemRand = new Random();
				world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			}
			else {
				stack.damageItem(1, player);
			    Random rand = new Random();
			    float pitch1 = (float) (rand.nextFloat() * (1.1 - 0.9) + 0.9);
				world.playSoundAtEntity(player, BouncingBalls.MODID + ":bouncingball.bounce", 1, pitch1);
			}
		}		
	}
}
