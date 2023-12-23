package com.rexbas.bouncingballs.item;

import com.rexbas.bouncingballs.api.BouncingBallsAPI;
import com.rexbas.bouncingballs.api.attachment.BounceData;
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
		BounceData bounceData = entity.getData(BouncingBallsAPI.AttachmentTypes.BOUNCE_DATA);
		return super.shouldSitOnBall(entity) || entity.isInLava() || bounceData.getLastFluid() == FluidTags.LAVA;
	}

	@Override
	public boolean onDamage(LivingEntity entity, DamageSource damageSource, float amount) {
		if (!entity.level().isClientSide()) {
			if (entity.isInLava() && (damageSource.is(DamageTypes.LAVA) || damageSource.is(DamageTypes.ON_FIRE))) {
				entity.clearFire();
				return true;
			}
			BounceData bounceData = entity.getData(BouncingBallsAPI.AttachmentTypes.BOUNCE_DATA);
			if (damageSource.is(DamageTypes.IN_FIRE) || damageSource.is(DamageTypes.ON_FIRE) || damageSource.is(DamageTypes.HOT_FLOOR)) {
				if (bounceData.getConsecutiveBounces() > 0 || this.shouldSitOnBall(entity)) {
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