package bouncing_balls.registry;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import bouncing_balls.item.BouncingBall;

public class ItemRegistry {

	public static void registerItems() {		
		for(int i = 0; i <= 28; i++) {
			Item item = BouncingBall.returnByID(i);
			GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
		}
	}
}
