package bouncing_balls.item;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public enum BallType {	
	EGG(false, 0.5F, 0.65F, 12F),
	SNOW(false, 0.65F, 0.65F, 14F),
	DYNAMITE(false, 0.7F, 0.7F, 16F),
	SLIME(true, 150, 0.5F, 1.0F, Items.SLIME_BALL, 10F),
	CLAY(true, 150, 0.6F, 0.75F, Items.CLAY_BALL, 10F),
	REDSTONE(true, 150, 0.6F, 0.75F, Items.REDSTONE, 10F),
	GLOWSTONE(true, 150, 0.6F, 0.75F, Items.GLOWSTONE_DUST, 10F),
	GOLD(true, 250, 0.75F, 0.8F, Items.GOLD_INGOT, 10F),
	IRON(true, 300, 0.9F, 0.9F, Items.IRON_INGOT, 10F),
	DIAMOND(true, 400, 1.0F, 1.0F, Items.DIAMOND, 10F),
	OBSIDIAN(true, 500, 1.0F, 0.75F, Item.getItemFromBlock(Blocks.OBSIDIAN), 10F),
	EMERALD(true, 600, 1.25F, 1.5F, Items.EMERALD, 16F),
	NETHER_STAR(true, 2500, 1.75F, 1.5F, Items.NETHER_STAR, 18F),
	NORMAL(true, 100, 0.5F, 0.65F, Items.SLIME_BALL, 10F);
		
	private boolean hasMaxDamage;
	private int maxDamage;
	private float movingAmount;
	private float motionY;
	private Item repairItem;
	private Block repairBlock;
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
