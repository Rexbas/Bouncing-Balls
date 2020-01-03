package bouncing_balls;

import bouncing_balls.common.capabilities.IJumpCapability;
import bouncing_balls.common.capabilities.JumpCapability;
import bouncing_balls.common.capabilities.JumpCapabilityStorage;
import bouncing_balls.itemgroup.ItemGroupBouncingBalls;
import bouncing_balls.network.BouncingBallsPacketHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(BouncingBalls.MODID)
public class BouncingBalls {
	public static final String MODID = "bouncing_balls";
	public static final ItemGroup ITEMGROUP = new ItemGroupBouncingBalls(MODID);
	public static SoundEvent BOUNCE = new SoundEvent(new ResourceLocation(BouncingBalls.MODID, "bounce"));
	
	public BouncingBalls() {		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
	}
	
	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
    	ForgeRegistries.SOUND_EVENTS.register(BOUNCE);
	}
	
    public void setup(final FMLCommonSetupEvent event) {
    	BouncingBallsPacketHandler.register();
    	CapabilityManager.INSTANCE.register(IJumpCapability.class, new JumpCapabilityStorage(), JumpCapability::new);
    }
}
