package com.rexbas.bouncingballs.item;

import com.rexbas.bouncingballs.BouncingBalls;
import com.rexbas.bouncingballs.api.item.BouncingBall;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class SlimeBouncingBall extends BouncingBall {

	public SlimeBouncingBall() {
		super(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties(150, Items.SLIME_BALL, 0.5f, 1.0f, 10f, 0.3f));
	}
	
	@Override
	public SoundEvent getBounceSound() {
		return SoundEvents.SLIME_BLOCK_PLACE;
	}
}