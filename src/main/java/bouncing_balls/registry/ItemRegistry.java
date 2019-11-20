package bouncing_balls.registry;

import bouncing_balls.BouncingBalls;
import bouncing_balls.item.BouncingBall;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BouncingBalls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegistry {

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {		
		for(int i = 0; i <= 29; i++) {
			Item item = BouncingBall.returnByID(i);
			event.getRegistry().register(item);
		}
	}
}
