package com.rexbas.bouncingballs.item;

import com.rexbas.bouncingballs.BouncingBalls;
import com.rexbas.bouncingballs.api.capability.BounceCapabilityProvider;
import com.rexbas.bouncingballs.api.capability.IBounceCapability;
import com.rexbas.bouncingballs.api.item.BouncingBall;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ForgeMod;

public class ObsidianBouncingBall extends BouncingBall {

	public ObsidianBouncingBall() {
		super(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties(500, Item.BY_BLOCK.get(Blocks.OBSIDIAN), 1f, 0.75f, 10f, 0.4f).addFluid(FluidTags.WATER).addFluid(FluidTags.LAVA));
	}
	
	@Override
	public boolean shouldSitOnBall(LivingEntity entity) {
		IBounceCapability cap = entity.getCapability(BounceCapabilityProvider.BOUNCE_CAPABILITY).orElse(null);
		if (cap != null) {
			return super.shouldSitOnBall(entity) || entity.isInLava() || cap.getLastFluid() == FluidTags.LAVA;
		}
		return false;
	}
	
	@Override
	public boolean onDamage(LivingEntity entity, DamageSource damageSource, float amount) {
		if (entity.isInLava() && damageSource == DamageSource.LAVA || damageSource == DamageSource.ON_FIRE) {
			entity.clearFire();
			return true;
		}
		return false;
	}
	
	@Override
	public void inFluid(LivingEntity entity, ITag<Fluid> fluid) {
		if (properties.fluidList.contains(fluid)) {
			
			entity.getCapability(BounceCapabilityProvider.BOUNCE_CAPABILITY).ifPresent(cap -> {
				cap.setLastFluid(fluid);
			});
			
			double d = 0.05 * entity.getFluidHeight(fluid) + 0.0075;
			
			if (entity.getDeltaMovement().y() < 0) {
				d += -0.6 * entity.getDeltaMovement().y();	
			}
			
			if (fluid == FluidTags.LAVA) {
				d *= 1.5;
			}
			
			entity.setDeltaMovement(entity.getDeltaMovement().add(0.0D, entity.getAttribute(ForgeMod.SWIM_SPEED.get()).getValue() * d, 0.0D));
		}
	}
}