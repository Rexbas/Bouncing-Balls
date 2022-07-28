package com.rexbas.bouncingballs.item;

import com.rexbas.bouncingballs.BouncingBalls;
import com.rexbas.bouncingballs.api.capability.BounceCapability;
import com.rexbas.bouncingballs.api.capability.IBounceCapability;
import com.rexbas.bouncingballs.api.item.BouncingBall;
import com.rexbas.bouncingballs.api.item.IBouncingBall;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;

public class EnderBouncingBall extends BouncingBall {

	public EnderBouncingBall() {
		super(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties(600, Items.ENDER_EYE, 0.5f, 0.65f, 10f, 0.5f)
				.recipeItem(Items.ENDER_EYE)
				.addFluid(FluidTags.WATER));
	}
	
	@Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
    	ItemStack stack = player.getItemInHand(hand);
    	
    	if (hand == Hand.MAIN_HAND && player.getOffhandItem().getItem() instanceof IBouncingBall) {
    		return new ActionResult<ItemStack>(ActionResultType.FAIL, stack);
    	}
    	
    	if (!player.level.isClientSide() && canBounce(player)) {
    		bounce(player, 0);
    		damageBall(player, stack);
			playBounceSound(world, player);
    		return new ActionResult<ItemStack>(ActionResultType.PASS, stack);
    	}
		return new ActionResult<ItemStack>(ActionResultType.FAIL, stack);
	}
	
	@Override
	public boolean canBounce(LivingEntity entity) {
		IBounceCapability cap = entity.getCapability(BounceCapability.BOUNCE_CAPABILITY).orElse(null);
		if (cap != null) {
			float yaw = entity.yRot;
			float pitch = entity.xRot;
			double deltaX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * 5);
			double deltaZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * 5);
			BlockPos newPos = entity.blockPosition().offset(new Vector3i(deltaX, 8, deltaZ));
			return super.canBounce(entity) && entity.level.getBlockState(newPos).isAir() && entity.level.getBlockState(newPos.above()).isAir();
		}
		return false;
	}
	
	@Override
	public void bounce(LivingEntity entity, float motionY) {
		if (motionY == 0) {
			float yaw = entity.yRot;
			float pitch = entity.xRot;
			double forwardMotion = 10;
			double deltaX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * forwardMotion);
			double deltaZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * forwardMotion);
			
			BlockPos newPos = entity.blockPosition().offset(new Vector3i(deltaX, 8, deltaZ));
			entity.moveTo(newPos.getX(), newPos.getY(), newPos.getZ());
				
			entity.getCapability(BounceCapability.BOUNCE_CAPABILITY).ifPresent(cap -> {
				cap.addBounce();
			});
		}
		else {
			super.bounce(entity, motionY);
		}
	}
	
	@Override
	public void playBounceSound(World world, LivingEntity entity) {
		world.playSound(null, entity, SoundEvents.ENDER_PEARL_THROW, SoundCategory.PLAYERS, 0.5f, 0.4f / (world.random.nextFloat() * 0.4f + 0.8f));
	}
}