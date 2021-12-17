package bouncing_balls;

import bouncing_balls.init.BouncingBallsItems;
import bouncing_balls.init.BouncingBallsSounds;
import bouncing_balls.itemgroup.ItemGroupBouncingBalls;
import bouncing_balls.network.BouncingBallsPacketHandler;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BouncingBalls.MODID)
public class BouncingBalls {
	public static final String MODID = "bouncing_balls";
	public static final CreativeModeTab ITEMGROUP = new ItemGroupBouncingBalls(MODID);
	
	public BouncingBalls() {		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		
		BouncingBallsItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BouncingBallsSounds.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
    public void setup(final FMLCommonSetupEvent event) {
    	BouncingBallsPacketHandler.register();
    }
}