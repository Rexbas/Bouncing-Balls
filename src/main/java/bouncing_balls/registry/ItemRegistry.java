package bouncing_balls.registry;

import net.minecraft.item.Item;
import bouncing_balls.item.BouncingBall;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegistry {

	public static void registerItems() {		
		for(int i = 0; i <= 23; i++) {
			Item item = BouncingBall.returnByID(i);
			GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
		}
	}
}
