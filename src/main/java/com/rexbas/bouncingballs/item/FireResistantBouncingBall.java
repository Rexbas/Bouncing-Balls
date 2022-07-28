package com.rexbas.bouncingballs.item;

import java.util.List;

import com.rexbas.bouncingballs.BouncingBalls;
import com.rexbas.bouncingballs.api.capability.BounceCapability;
import com.rexbas.bouncingballs.api.capability.IBounceCapability;
import com.rexbas.bouncingballs.api.item.BouncingBall;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class FireResistantBouncingBall extends BouncingBall {

	public FireResistantBouncingBall(BouncingBall.Properties ballProperties) {
		super(new Item.Properties().tab(BouncingBalls.TAB), ballProperties);
	}
	
	@Override
	public boolean shouldSitOnBall(LivingEntity entity) {
		IBounceCapability cap = entity.getCapability(BounceCapability.BOUNCE_CAPABILITY).orElse(null);
		if (cap != null) {
			return super.shouldSitOnBall(entity) || entity.isInLava() || cap.getLastFluid() == FluidTags.LAVA;
		}
		return false;
	}
	
	@Override
	public boolean onDamage(LivingEntity entity, DamageSource damageSource, float amount) {
		if (!entity.level.isClientSide()) {
			if (entity.isInLava() && (damageSource == DamageSource.LAVA || damageSource == DamageSource.ON_FIRE)) {
				entity.clearFire();
				return true;
			}
			IBounceCapability cap = entity.getCapability(BounceCapability.BOUNCE_CAPABILITY).orElse(null);
			if (cap != null && (damageSource == DamageSource.IN_FIRE || damageSource == DamageSource.ON_FIRE || damageSource == DamageSource.HOT_FLOOR)) {
				if (cap.getConsecutiveBounces() > 0 || this.shouldSitOnBall(entity)) {
					entity.clearFire();
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.appendHoverText(stack, world, list, flag);
		list.add(new TranslationTextComponent("bouncingballs.hovertext.fireresistant").setStyle(Style.EMPTY.withColor(Color.fromRgb(0xFF9900))));
    }
}