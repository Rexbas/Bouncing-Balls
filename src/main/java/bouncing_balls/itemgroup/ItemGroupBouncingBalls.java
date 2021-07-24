package bouncing_balls.itemgroup;

import bouncing_balls.init.BouncingBallsItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ItemGroupBouncingBalls extends CreativeModeTab {

	public ItemGroupBouncingBalls(String name) {
		super(name);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(BouncingBallsItems.RED.get());
	}
}
