package com.rexbas.bouncingballs.item;

import java.util.List;

import com.rexbas.bouncingballs.BouncingBalls;
import com.rexbas.bouncingballs.api.item.BouncingBall;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

public class DynamiteBouncingBall extends BouncingBall {

	public DynamiteBouncingBall() {
		super(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties(0.7f, 0.7f, 16f, 0.2f, false, 3, Items.GUNPOWDER)
				.recipeItem(Items.GUNPOWDER)
				.addFluid(FluidTags.WATER));
	}
	
	@Override
	public void bounce(LivingEntity entity, float motionY) {
		super.bounce(entity, motionY);
		entity.level.explode(entity, entity.getX(), entity.getY(), entity.getZ(), 0.75F, false, Explosion.BlockInteraction.BREAK);
	}
	
	@Override
	public void playBounceSound(Level level, LivingEntity entity) {
		level.playSound(null, entity, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1, 1);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(stack, level, list, flag);
		list.add(Component.translatable("bouncingballs.hovertext.dynamite.warning").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAA0000))));
    }
}