package com.rexbas.bouncingballs.item;

import com.rexbas.bouncingballs.api.capability.BounceCapability;
import com.rexbas.bouncingballs.api.capability.IBounceCapability;
import com.rexbas.bouncingballs.api.item.BouncingBall;
import com.rexbas.bouncingballs.api.item.IBouncingBall;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class EnderBouncingBall extends BouncingBall {

	public EnderBouncingBall() {
		super(new Item.Properties(), new BouncingBall.Properties(600, Items.ENDER_EYE, 0.5f, 0.65f, 10f, 0.5f)
				.recipeItem(Items.ENDER_EYE)
				.addFluid(FluidTags.WATER));
	}

	@Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (hand == InteractionHand.MAIN_HAND && player.getOffhandItem().getItem() instanceof IBouncingBall) {
			return new InteractionResultHolder<ItemStack>(InteractionResult.FAIL, stack);
		}

		if (!player.level.isClientSide() && canBounce(player)) {
			bounce(player, 0);
			damageBall(player, stack);
			playBounceSound(level, player);
			return new InteractionResultHolder<ItemStack>(InteractionResult.PASS, stack);
		}
		return new InteractionResultHolder<ItemStack>(InteractionResult.FAIL, stack);
	}

	@Override
	public boolean canBounce(LivingEntity entity) {
		IBounceCapability cap = entity.getCapability(BounceCapability.BOUNCE_CAPABILITY).orElse(null);
		if (cap != null) {
			float yaw = entity.getYRot();
			float pitch = entity.getXRot();
			double deltaX = (double) (-Mth.sin(yaw / 180.0F * (float) Math.PI) * Mth.cos(pitch / 180.0F * (float) Math.PI) * 5);
			double deltaZ = (double) (Mth.cos(yaw / 180.0F * (float) Math.PI) * Mth.cos(pitch / 180.0F * (float) Math.PI) * 5);
			BlockPos newPos = entity.blockPosition().offset(new Vec3i((int) Math.round(deltaX), 8, (int) Math.round(deltaZ)));
			return super.canBounce(entity) && entity.level.getBlockState(newPos).isAir() && entity.level.getBlockState(newPos.above()).isAir();
		}
		return false;
	}

	@Override
	public void bounce(LivingEntity entity, float motionY) {
		if (motionY == 0) {
			float yaw = entity.getYRot();
			float pitch = entity.getXRot();
			double forwardMotion = 10;
			double deltaX = (double)(-Mth.sin(yaw / 180.0F * (float)Math.PI) * Mth.cos(pitch / 180.0F * (float)Math.PI) * forwardMotion);
			double deltaZ = (double)(Mth.cos(yaw / 180.0F * (float)Math.PI) * Mth.cos(pitch / 180.0F * (float)Math.PI) * forwardMotion);

			BlockPos newPos = entity.blockPosition().offset(new Vec3i((int) Math.round(deltaX), 8, (int) Math.round(deltaZ)));
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
	public void playBounceSound(Level level, LivingEntity entity) {
		level.playSound(null, entity, SoundEvents.ENDER_PEARL_THROW, SoundSource.PLAYERS, 0.5f, 0.4f / (level.random.nextFloat() * 0.4f + 0.8f));
	}
}