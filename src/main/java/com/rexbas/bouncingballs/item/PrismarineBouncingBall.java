package com.rexbas.bouncingballs.item;

import java.util.List;

import com.rexbas.bouncingballs.BouncingBalls;
import com.rexbas.bouncingballs.api.capability.BounceCapabilityProvider;
import com.rexbas.bouncingballs.api.capability.IBounceCapability;
import com.rexbas.bouncingballs.api.item.BouncingBall;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class PrismarineBouncingBall extends BouncingBall {

	public PrismarineBouncingBall() {
		super(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties(500, Items.PRISMARINE_SHARD, 0.5f, 0.65f, 10f, 0.5f)
				.recipeItem(Items.PRISMARINE_SHARD)
				.addFluid(FluidTags.WATER));
	}
	
	@Override
	public void bounce(LivingEntity entity, float motionY) {
		float yaw = entity.yRot;
		float pitch = entity.xRot;
		double motionX = 0;
		double motionZ = 0;
		
		IBounceCapability cap = entity.getCapability(BounceCapabilityProvider.BOUNCE_CAPABILITY).orElse(null);
		if (cap != null) {
			if (cap.getTicksInFluid() > 0 && cap.getLastFluid() == FluidTags.WATER) {
				motionX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * properties.forwardMotion * 3);
				motionY *= 2.5;
				motionZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * properties.forwardMotion * 3);
			} 
		} else {
			motionX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * properties.forwardMotion);
			motionZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * properties.forwardMotion);
		}
		
		entity.push(motionX, motionY, motionZ);
		
		entity.getCapability(BounceCapabilityProvider.BOUNCE_CAPABILITY).ifPresent(capability -> {
			capability.addBounce();
		});
	}
	
	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.appendHoverText(stack, world, list, flag);
		list.add(new TranslationTextComponent("bouncingballs.hovertext.prismarine").setStyle(Style.EMPTY.withColor(Color.fromRgb(0x0099FF))));
    }
}