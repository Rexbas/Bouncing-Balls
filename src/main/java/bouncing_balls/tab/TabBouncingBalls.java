package bouncing_balls.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import bouncing_balls.BouncingBalls;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TabBouncingBalls extends CreativeTabs {

	public TabBouncingBalls(int id, String name) {
		super(id, name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return BouncingBalls.redBouncingBall;
	}
}
