package bouncing_balls.tab;

import bouncing_balls.BouncingBalls;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TabBouncingBalls extends CreativeTabs {

	public TabBouncingBalls(int id, String name) {
		super(id, name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem() {
		return new ItemStack(BouncingBalls.redBouncingBall);
	}
}
