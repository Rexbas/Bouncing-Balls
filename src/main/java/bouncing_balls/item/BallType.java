package bouncing_balls.item;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

public enum BallType {
	EGG(false, 0.5F, 0.65F, 12F),
	SNOW(false, 0.65F, 0.65F, 14F),
	CLAY(true, 125, 0.6F, 0.75F, Items.clay_ball, 10F),
	REDSTONE(true, 150, 0.6F, 0.75F, Items.redstone, 10F),
	GLOWSTONE(true, 150, 0.6F, 0.75F, Items.glowstone_dust, 10F),
	GOLD(true, 250, 0.75F, 0.8F, Items.gold_ingot, 10F),
	IRON(true, 300, 0.9F, 0.9F, Items.iron_ingot, 10F),
	DIAMOND(true, 400, 1.0F, 1.0F, Items.diamond, 10F),
	EMERALD(true, 500, 1.25F, 1.5F, Items.emerald, 16F),
	NORMAL(true, 100, 0.5F, 0.65F, Items.slime_ball, 10F);
		
	private boolean hasMaxDamage;
	private int maxDamage;
	private float movingAmount;
	private float motionY;
	private Item repairItem;
	private float fallJumpHeight;
	
	private BallType(boolean hasMaxDamage, int maxDamage, float movingAmount, float motionY, Item repairItem, float fallJumpHeight) {
		this.hasMaxDamage = hasMaxDamage;
		this.maxDamage = maxDamage;
		this.movingAmount = movingAmount;
		this.motionY = motionY;
		this.repairItem = repairItem;
		this.fallJumpHeight = fallJumpHeight;
	}
	
	private BallType(boolean hasMaxDamage, float movingAmount, float motionY, float fallJumpHeight) {
		this.hasMaxDamage = hasMaxDamage;
		this.movingAmount = movingAmount;
		this.motionY = motionY;
		this.fallJumpHeight = fallJumpHeight;
	}
	
	public boolean hasMaxDamage() {
		return this.hasMaxDamage;
	}
	
	public int getMaxDamage() {
		return this.maxDamage;
	}
	
	public float getMovingAmount() {
		return this.movingAmount;
	}
	
	public float getMotionY() {
		return this.motionY;
	}
	
	public Item getRepairItem() {
		return this.repairItem;
	}
	
	public float getFallJumpHeight() {
		return this.fallJumpHeight;
	}
}
