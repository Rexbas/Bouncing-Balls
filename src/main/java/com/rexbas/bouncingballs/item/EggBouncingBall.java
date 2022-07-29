package com.rexbas.bouncingballs.item;

import com.rexbas.bouncingballs.BouncingBalls;
import com.rexbas.bouncingballs.api.item.BouncingBall;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.EggEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class EggBouncingBall extends BouncingBall {

	public EggBouncingBall() {
		super(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties(0.5f, 0.65f, 12f, 0.3f, false, 3, Items.EGG)
				.recipeItem(Items.EGG)
				.addFluid(FluidTags.WATER));
	}
	
	@Override
	public void bounce(LivingEntity entity, float motionY) {
		super.bounce(entity, motionY);
        EggEntity egg = new EggEntity(entity.level, entity);
        egg.push(0, -1, 0);
		entity.level.addFreshEntity(egg);
	}
	
	@Override
	public void playBounceSound(World world, LivingEntity entity) {
		world.playSound(null, entity, SoundEvents.EGG_THROW, SoundCategory.PLAYERS, 0.5f, 0.4f / (world.random.nextFloat() * 0.4f + 0.8f));
	}
}