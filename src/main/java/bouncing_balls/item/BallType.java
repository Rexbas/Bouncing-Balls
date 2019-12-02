package bouncing_balls.item;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public enum BallType {	
	EGG(0.5F, 0.65F, 12F),
	SNOW(0.65F, 0.65F, 14F),
	DYNAMITE(0.7F, 0.7F, 16F),
	SLIME(150, 0.5F, 1.0F, Items.SLIME_BALL, 10F),
	CLAY(150, 0.6F, 0.75F, Items.CLAY_BALL, 10F),
	REDSTONE(150, 0.6F, 0.75F, Items.REDSTONE, 10F),
	GLOWSTONE(150, 0.6F, 0.75F, Items.GLOWSTONE_DUST, 10F),
	GOLD(250, 0.75F, 0.8F, Items.GOLD_INGOT, 10F),
	IRON(300, 0.9F, 0.9F, Items.IRON_INGOT, 10F),
	DIAMOND(400, 1.0F, 1.0F, Items.DIAMOND, 10F),
	OBSIDIAN(500, 1.0F, 0.75F, 	Item.BLOCK_TO_ITEM.get(Blocks.OBSIDIAN), 10F),
	EMERALD(600, 1.25F, 1.5F, Items.EMERALD, 16F),
	NETHER_STAR(2500, 1.75F, 1.5F, Items.NETHER_STAR, 18F),
	QUARTZ(550, 1.0F, 0.8F, Items.QUARTZ, 10F),
	NORMAL(100, 0.5F, 0.65F, Items.SLIME_BALL, 10F);
	
	private int maxDamage;
	private float movingAmount;
	private float motionY;
	private Item repairItem;
	private float fallJumpHeight;
	
	private BallType(int maxDamage, float movingAmount, float motionY, Item repairItem, float fallJumpHeight) {
		this.maxDamage = maxDamage;
		this.movingAmount = movingAmount;
		this.motionY = motionY;
		this.repairItem = repairItem;
		this.fallJumpHeight = fallJumpHeight;
	}
	
	private BallType(float movingAmount, float motionY, float fallJumpHeight) {
		this.maxDamage = 0;
		this.movingAmount = movingAmount;
		this.motionY = motionY;
		this.repairItem = null;
		this.fallJumpHeight = fallJumpHeight;
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
