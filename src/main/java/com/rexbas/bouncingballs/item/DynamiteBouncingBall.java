package com.rexbas.bouncingballs.item;

import java.util.List;

import com.rexbas.bouncingballs.BouncingBalls;
import com.rexbas.bouncingballs.api.item.BouncingBall;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class DynamiteBouncingBall extends BouncingBall {

	public DynamiteBouncingBall() {
		super(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties(0.7f, 0.7f, 16f, 0.2f, false, 3, Items.GUNPOWDER)
				.recipeItem(Items.GUNPOWDER)
				.addFluid(FluidTags.WATER));
	}
	
	@Override
	public void bounce(LivingEntity entity, float motionY) {
		super.bounce(entity, motionY);
		entity.level.explode(entity, entity.getX(), entity.getY(), entity.getZ(), 0.75F, false, Explosion.Mode.BREAK);
	}
	
	@Override
	public void playBounceSound(World world, LivingEntity entity) {
		world.playSound(null, entity, SoundEvents.GENERIC_EXPLODE, SoundCategory.PLAYERS, 1, 1);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.appendHoverText(stack, world, list, flag);
		list.add(new TranslationTextComponent("bouncingballs.hovertext.dynamite.warning").setStyle(Style.EMPTY.withColor(Color.fromRgb(0xAA0000))));
    }
}