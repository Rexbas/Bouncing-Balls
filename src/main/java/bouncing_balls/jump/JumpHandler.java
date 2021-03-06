package bouncing_balls.jump;

import java.util.Random;

import bouncing_balls.BouncingBalls;
import bouncing_balls.common.capabilities.IJumpCapability;
import bouncing_balls.common.capabilities.JumpProvider;
import bouncing_balls.item.BallType;
import bouncing_balls.item.BouncingBall;
import bouncing_balls.network.BouncingBallsPacketHandler;
import bouncing_balls.network.packets.DecreaseItemStackPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.EggEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class JumpHandler {
	
	public static void jump(BouncingBallJump jump) {
		PlayerEntity player = jump.getPlayer();
		ItemStack stack = jump.getItemStack();
		BouncingBall ball = (BouncingBall) stack.getItem();
		World world = player.world;
		
		LazyOptional<IJumpCapability> cap = player.getCapability(JumpProvider.JUMP_CAPABILITY, player.getHorizontalFacing());
		cap.ifPresent(c -> {
			int jumps = c.jumpsInAir();
			float fallDistance = c.fallDistance();
			
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
			
			if (jump.getJumpType() != JumpType.NORMAL && jump.getJumpType() != JumpType.FALL_JUMP) {
				if(player.inventory.hasItemStack(jump.getJumpType().getRequiredItem()) && world.isRemote) {
					int slot = player.inventory.getSlotFor(jump.getJumpType().getRequiredItem());
					BouncingBallsPacketHandler.INSTANCE.sendToServer(new DecreaseItemStackPacket(slot));
				}
				c.setJumpsInAir(jumps + 1);
				
				switch (jump.getJumpType()) {
				case EGG_JUMP:
			        if (!world.isRemote) world.addEntity(new EggEntity(world, player));
		            Random r0 = new Random();
					player.playSound(SoundEvents.ENTITY_EGG_THROW, 0.5F, 0.4F / (r0.nextFloat() * 0.4F + 0.8F));
					break;
				case SNOWBALL_JUMP:        
			        if (!world.isRemote) world.addEntity(new SnowballEntity(world, player));
		            Random r1 = new Random();
					player.playSound(SoundEvents.ENTITY_SNOWBALL_THROW, 0.5F, 0.4F / (r1.nextFloat() * 0.4F + 0.8F));
					break;
				case DYNAMITE_JUMP:
					player.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1, 1);
					if (!world.isRemote) world.createExplosion(player, player.prevPosX, player.prevPosY, player.prevPosZ, 0.75F, false, Explosion.Mode.BREAK);
					break;
				default:
					break;
				}
			}
			else {
				stack.damageItem(1, player, (p) -> {});
			    Random rand = new Random();
			    float pitch1 = (float) (rand.nextFloat() * (1.1 - 0.9) + 0.9);
			    if(ball.getBallType() == BallType.SLIME) {
					player.playSound(SoundEvents.BLOCK_SLIME_BLOCK_FALL, 1, pitch1);
			    }
			    else {
					player.playSound(BouncingBalls.BOUNCE, 1, pitch1);
			    }
			}
		});		
	}
}

