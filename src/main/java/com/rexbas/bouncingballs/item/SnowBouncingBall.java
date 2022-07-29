package com.rexbas.bouncingballs.item;

import com.rexbas.bouncingballs.BouncingBalls;
import com.rexbas.bouncingballs.api.item.BouncingBall;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class SnowBouncingBall extends BouncingBall {

	public SnowBouncingBall() {
		super(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties(0.65f, 0.65f, 14f, 0.3f, false, 3, Items.SNOWBALL)
				.recipeItem(Items.SNOWBALL)
				.addFluid(FluidTags.WATER));
	}
	
	@Override
	public void bounce(LivingEntity entity, float motionY) {
		super.bounce(entity, motionY);
		Snowball snow = new Snowball(entity.level, entity);
		snow.push(0, -1, 0);
		entity.level.addFreshEntity(snow);
	}
	
	@Override
	public void playBounceSound(Level level, LivingEntity entity) {
		level.playSound(null, entity, SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS, 0.5f, 0.4f / (level.random.nextFloat() * 0.4f + 0.8f));
	}
}