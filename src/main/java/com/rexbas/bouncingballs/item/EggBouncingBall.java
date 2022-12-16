package com.rexbas.bouncingballs.item;

import com.rexbas.bouncingballs.api.item.BouncingBall;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class EggBouncingBall extends BouncingBall {

	public EggBouncingBall() {
		super(new Item.Properties(), new BouncingBall.Properties(0.5f, 0.65f, 12f, 0.3f, false, 3, Items.EGG)
				.recipeItem(Items.EGG)
				.addFluid(FluidTags.WATER));
	}
	
	@Override
	public void bounce(LivingEntity entity, float motionY) {
		super.bounce(entity, motionY);
		ThrownEgg egg = new ThrownEgg(entity.level, entity);
        egg.push(0, -1, 0);
		entity.level.addFreshEntity(egg);
	}
	
	@Override
	public void playBounceSound(Level level, LivingEntity entity) {
		level.playSound(null, entity, SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5f, 0.4f / (level.random.nextFloat() * 0.4f + 0.8f));
	}
}