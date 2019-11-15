package bouncing_balls.registry;

import bouncing_balls.item.BouncingBall;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistry {

	public static void registerItems() {		
		for(int i = 0; i <= 29; i++) {
			Item item = BouncingBall.returnByID(i);
			GameRegistry.register(item);
		}
	}
}
