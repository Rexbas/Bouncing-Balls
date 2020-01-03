package bouncing_balls.itemgroup;

import bouncing_balls.item.BallList;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupBouncingBalls extends ItemGroup {

	public ItemGroupBouncingBalls(String name) {
		super(name);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(BallList.RED);
	}
}
