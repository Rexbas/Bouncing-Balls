package com.rexbas.bouncingballs.item;

import java.util.List;

import com.rexbas.bouncingballs.BouncingBalls;
import com.rexbas.bouncingballs.api.item.BouncingBall;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.EggEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class EggBouncingBall extends BouncingBall {

	public EggBouncingBall() {
		super(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties(0.5f, 0.65f, 12f, 0.3f, false, 3, Items.EGG));
	}
	
	@Override
	public void bounce(LivingEntity entity, float motionY) {
		super.bounce(entity, motionY);
        EggEntity egg = new EggEntity(entity.level, entity);
        egg.push(0, -1, 0);
		entity.level.addFreshEntity(egg);
	}
	
	@Override
	public void playBounceSound(World world, Entity entity) {
		entity.playSound(SoundEvents.EGG_THROW, 0.5f, 0.4f / (world.random.nextFloat() * 0.4f + 0.8f));
	}
	
	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		list.add(new TranslationTextComponent("bouncing_balls.egg.tooltip").setStyle(Style.EMPTY.withColor(Color.fromRgb(0xAAAAAA))));
	}
}