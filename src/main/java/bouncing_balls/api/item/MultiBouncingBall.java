package bouncing_balls.api.item;

import bouncing_balls.api.capability.BounceCapability;
import bouncing_balls.api.capability.BounceCapabilityProvider;
import bouncing_balls.api.capability.IBounceCapability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class MultiBouncingBall extends BouncingBall {

	protected ItemStack consumptionItem;
	
	public MultiBouncingBall(Properties properties, Item consumptionItem) {
		super(properties);
		this.consumptionItem = new ItemStack(consumptionItem);
	}
	
	public MultiBouncingBall(Properties properties) {
		this(properties, Items.AIR);
	}
	
	@Override
	public boolean canBounce(Entity entity) {
		// TODO values
		PlayerEntity player = (PlayerEntity) entity;
		IBounceCapability cap = player.getCapability(BounceCapabilityProvider.BOUNCE_CAPABILITY).orElse(new BounceCapability());
		return cap.getConsecutiveBounces() < 3 && !entity.isInWater() && !entity.isInLava() && hasConsumptionItem(player);
	}
	
	/**
	 * Add y-motion to the entity and reduce the consumption item if applicable.
	 * IMPORTANT: If the consumption item is not Items.AIR, check if the inventory
	 * contains the consumption item before calling this function.
	 * 
	 * @param entity  The entity to bounce.
	 * @param motionY The y-motion to add.
	 */
	@Override
	public void bounce(Entity entity, float motionY) {
		super.bounce(entity, motionY);
		PlayerEntity player = (PlayerEntity) entity;
		
		if (consumptionItem.getItem() != Items.AIR) {
			int slot = player.inventory.findSlotMatchingItem(consumptionItem);
			player.inventory.removeItem(slot, 1);
		}
	}
	
	@Override
	public float onFall(PlayerEntity player, ItemStack stack, float fallDistance) {
		// TODO values
		if (fallDistance > 5) {
			float multiplier;
			if (hasConsumptionItem(player)) {
				bounce(player, 2f);
				multiplier = 0.1f;
			}
			else {
				super.bounce(player, 1f);
				multiplier = 0.5f;
			}

			damageBall(player, stack);
			playBounceSound(player.level, player);
			return multiplier;
		}
		return 0f;
	}
	
	protected boolean hasConsumptionItem(PlayerEntity player) {
		return consumptionItem.getItem() == Items.AIR || player.inventory.contains(consumptionItem);
	}
}