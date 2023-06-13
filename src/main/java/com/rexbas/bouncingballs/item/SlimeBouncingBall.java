package com.rexbas.bouncingballs.item;

import com.rexbas.bouncingballs.api.item.BouncingBall;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class SlimeBouncingBall extends BouncingBall {

	public SlimeBouncingBall() {
		super(new Item.Properties(), new BouncingBall.Properties(150, Items.SLIME_BALL, 0.5f, 1.0f, 10f, 0.3f)
				.recipeItem(Items.SLIME_BALL)
				.addFluid(FluidTags.WATER));
	}
	
	@Override
	public SoundEvent getBounceSound() {
		return SoundEvents.SLIME_BLOCK_PLACE;
	}
}