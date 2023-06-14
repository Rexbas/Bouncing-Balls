package com.rexbas.bouncingballs.item;

import com.rexbas.bouncingballs.api.capability.BounceCapability;
import com.rexbas.bouncingballs.api.capability.IBounceCapability;
import com.rexbas.bouncingballs.api.item.BouncingBall;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class FireResistantBouncingBall extends BouncingBall {

	public FireResistantBouncingBall(BouncingBall.Properties ballProperties) {
		super(new Item.Properties(), ballProperties);
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
		if (!entity.level().isClientSide()) {
			if (entity.isInLava() && (damageSource.is(DamageTypes.LAVA) || damageSource.is(DamageTypes.ON_FIRE))) {
				entity.clearFire();
				return true;
			}
			IBounceCapability cap = entity.getCapability(BounceCapability.BOUNCE_CAPABILITY).orElse(null);
			if (cap != null && (damageSource.is(DamageTypes.IN_FIRE) || damageSource.is(DamageTypes.ON_FIRE) || damageSource.is(DamageTypes.HOT_FLOOR))) {
				if (cap.getConsecutiveBounces() > 0 || this.shouldSitOnBall(entity)) {
					entity.clearFire();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(stack, level, list, flag);
		list.add(Component.translatable("bouncingballs.hovertext.fireresistant").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFF9900))));
    }
}