package bouncing_balls.itemgroup;

import bouncing_balls.init.BouncingBallsItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupBouncingBalls extends ItemGroup {

	public ItemGroupBouncingBalls(String name) {
		super(name);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(BouncingBallsItems.RED.get());
	}
}
