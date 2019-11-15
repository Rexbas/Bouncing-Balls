package bouncing_balls.jump;

import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public enum JumpType {
	NORMAL,
	FALL_JUMP,
	EGG_JUMP(Items.egg, "CustomEntityEgg"),
	SNOWBALL_JUMP(Items.snowball, "CustomEntitySnowball");
	
	private Item neededItem;
	private String throwable;
	
	private JumpType() {}
	
	private JumpType(Item neededItem, String throwable) {
		this.neededItem = neededItem;
		this.throwable = throwable;
	}
	
	public Item getNeededItem() {
		return this.neededItem;
	}
	
	public String getEntityThrowable() {
		return this.throwable;
	}
}
