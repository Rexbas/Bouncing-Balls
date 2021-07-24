package bouncing_balls.jump;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public enum JumpType {
	NORMAL,
	FALL_JUMP,
	EGG_JUMP(new ItemStack(Items.EGG)),
	SNOWBALL_JUMP(new ItemStack(Items.SNOWBALL)),
	DYNAMITE_JUMP(new ItemStack(Items.GUNPOWDER));
	
	private ItemStack requiredItem;
	
	private JumpType() {}
	
	private JumpType(ItemStack requiredItem) {
		this.requiredItem = requiredItem;
	}
	
	public ItemStack getRequiredItem() {
		return this.requiredItem;
	}
}
