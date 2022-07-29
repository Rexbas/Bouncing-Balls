package com.rexbas.bouncingballs.item;

import java.util.List;

import com.rexbas.bouncingballs.BouncingBalls;
import com.rexbas.bouncingballs.api.capability.BounceCapability;
import com.rexbas.bouncingballs.api.capability.IBounceCapability;
import com.rexbas.bouncingballs.api.item.BouncingBall;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class PrismarineBouncingBall extends BouncingBall {

	public PrismarineBouncingBall() {
		super(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties(500, Items.PRISMARINE_SHARD, 0.5f, 0.65f, 10f, 0.5f)
				.recipeItem(Items.PRISMARINE_SHARD)
				.addFluid(FluidTags.WATER));
	}
	
	@Override
	public void bounce(LivingEntity entity, float motionY) {
		float yaw = entity.getYRot();
		float pitch = entity.getXRot();
		double motionX = 0;
		double motionZ = 0;
		
		IBounceCapability cap = entity.getCapability(BounceCapability.BOUNCE_CAPABILITY).orElse(null);
		if (cap != null) {
			if (cap.getTicksInFluid() > 0 && cap.getLastFluid() == FluidTags.WATER) {
				motionX = (double)(-Mth.sin(yaw / 180.0F * (float)Math.PI) * Mth.cos(pitch / 180.0F * (float)Math.PI) * properties.forwardMotion * 3);
				motionY *= 2.5;
				motionZ = (double)(Mth.cos(yaw / 180.0F * (float)Math.PI) * Mth.cos(pitch / 180.0F * (float)Math.PI) * properties.forwardMotion * 3);
			} 
		} else {
			motionX = (double)(-Mth.sin(yaw / 180.0F * (float)Math.PI) * Mth.cos(pitch / 180.0F * (float)Math.PI) * properties.forwardMotion);
			motionZ = (double)(Mth.cos(yaw / 180.0F * (float)Math.PI) * Mth.cos(pitch / 180.0F * (float)Math.PI) * properties.forwardMotion);
		}
		
		entity.push(motionX, motionY, motionZ);
		entity.hurtMarked = true;
		
		entity.getCapability(BounceCapability.BOUNCE_CAPABILITY).ifPresent(capability -> {
			capability.addBounce();
		});
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(stack, level, list, flag);
		list.add(new TranslatableComponent("bouncingballs.hovertext.prismarine").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0099FF))));
    }
}