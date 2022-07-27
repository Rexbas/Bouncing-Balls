package com.rexbas.bouncingballs.item;

import com.rexbas.bouncingballs.BouncingBalls;
import com.rexbas.bouncingballs.api.item.BouncingBall;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class SnowBouncingBall extends BouncingBall {

	public SnowBouncingBall() {
		super(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties(0.65f, 0.65f, 14f, 0.3f, false, 3, Items.SNOWBALL)
				.recipeItem(Items.SNOWBALL)
				.addFluid(FluidTags.WATER));
	}
	
	@Override
	public void bounce(LivingEntity entity, float motionY) {
		super.bounce(entity, motionY);
		SnowballEntity snow = new SnowballEntity(entity.level, entity);
		snow.push(0, -1, 0);
		entity.level.addFreshEntity(snow);
	}
	
	@Override
	public void playBounceSound(World world, LivingEntity entity) {
		entity.playSound(SoundEvents.SNOWBALL_THROW, 0.5f, 0.4f / (world.random.nextFloat() * 0.4f + 0.8f));
	}
}