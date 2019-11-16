package bouncing_balls.jump;

import java.util.Random;

import bouncing_balls.BouncingBalls;
import bouncing_balls.capability.IBB_CAP;
import bouncing_balls.item.BouncingBall;
import bouncing_balls.packet.DecreaseStackPacket;
import bouncing_balls.throwable.CustomEntityEgg;
import bouncing_balls.throwable.CustomEntitySnowball;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class JumpHandler {
	
	public static void jump(BouncingBallJump jump) {
		EntityPlayer player = jump.getPlayer();
		ItemStack stack = jump.getItemStack();
		BouncingBall ball = (BouncingBall) stack.getItem();
		World world = player.worldObj;
		
		IBB_CAP capability = player.getCapability(BouncingBalls.BB_CAP, player.getHorizontalFacing());
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
		
		if(jump.getJumpType() == JumpType.EGG_JUMP || jump.getJumpType() == JumpType.SNOWBALL_JUMP) {
			if(player.inventory.hasItemStack(jump.getJumpType().getNeededItem()) && world.isRemote) {
				int slot = player.inventory.getSlotFor(jump.getJumpType().getNeededItem());
				BouncingBalls.network.sendToServer(new DecreaseStackPacket(slot));
			}			
			capability.setJumpsInAir(jumps + 1);
            if(jump.getJumpType().getEntityThrowable() == "CustomEntityEgg") {
	            world.spawnEntityInWorld(new CustomEntityEgg(world, player));
            }
            else {
	            world.spawnEntityInWorld(new CustomEntitySnowball(world, player));
            }
            Random itemRand = new Random();
			player.playSound(SoundEvents.ENTITY_ARROW_SHOOT, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		}
		else if(jump.getJumpType() == JumpType.DYNAMITE_JUMP) {
			if(player.inventory.hasItemStack(jump.getJumpType().getNeededItem()) && world.isRemote) {
				int slot = player.inventory.getSlotFor(jump.getJumpType().getNeededItem());
				BouncingBalls.network.sendToServer(new DecreaseStackPacket(slot));
			}
			capability.setJumpsInAir(jumps + 1);
			player.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1, 1);
			if(!world.isRemote) world.createExplosion(player, player.posX, player.posY, player.posZ, 0.75F, true);
		}
		else {
			stack.damageItem(1, player);
		    Random rand = new Random();
		    float pitch1 = (float) (rand.nextFloat() * (1.1 - 0.9) + 0.9);
		    if(ball.getID() == 19) {
				player.playSound(SoundEvents.BLOCK_SLIME_PLACE, 1, pitch1);
		    }
		    else {
				player.playSound(BouncingBalls.sound_bounce, 1, pitch1);
		    }
		}
	}
    
    private static boolean stackEqualExact(ItemStack stack1, ItemStack stack2) {
        return stack1.getItem() == stack2.getItem() && (!stack1.getHasSubtypes() || stack1.getMetadata() == stack2.getMetadata()) && ItemStack.areItemStackTagsEqual(stack1, stack2);
    }
}

