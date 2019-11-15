package bouncing_balls.jump;

import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum JumpType {
	NORMAL,
	FALL_JUMP,
	EGG_JUMP(new ItemStack(Items.EGG), "CustomEntityEgg"),
	SNOWBALL_JUMP(new ItemStack(Items.SNOWBALL), "CustomEntitySnowball"),
	DYNAMITE_JUMP(new ItemStack(Items.GUNPOWDER));
	
	private ItemStack neededItem;
	private String throwable;
	
	private JumpType() {}
	
	private JumpType(ItemStack neededItem, String throwable) {
		this.neededItem = neededItem;
		this.throwable = throwable;
	}
	
	private JumpType(ItemStack neededItem) {
		this.neededItem = neededItem;
	}
	
	public ItemStack getNeededItem() {
		return this.neededItem;
	}
	
	public String getEntityThrowable() {
		return this.throwable;
	}
}
