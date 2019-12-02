package bouncing_balls;

import bouncing_balls.capability.IJumpCapability;
import bouncing_balls.capability.JumpCapability;
import bouncing_balls.capability.JumpCapabilityStorage;
import bouncing_balls.itemgroup.BouncingBallsItemGroup;
import bouncing_balls.jump.JumpHandler;
import bouncing_balls.network.BouncingBallsPacketHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(BouncingBalls.MODID)
public class BouncingBalls {
    public static BouncingBalls instance;

	public static final String MODID = "bouncing_balls";
		
	public static BouncingBallsEventHandler bouncingBallsEventHandler = new BouncingBallsEventHandler();

	public static JumpHandler jumpHandler = new JumpHandler();
		
	public static ItemGroup itemGroup = new BouncingBallsItemGroup(MODID);
	
	public static SoundEvent sound_bounce = new SoundEvent(new ResourceLocation(BouncingBalls.MODID, "bounce"));
	
	public BouncingBalls() {
		instance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		
    	MinecraftForge.EVENT_BUS.register(instance);
    	MinecraftForge.EVENT_BUS.register(bouncingBallsEventHandler);
	}
	
	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
    	ForgeRegistries.SOUND_EVENTS.register(sound_bounce);
	}
	
    public void setup(final FMLCommonSetupEvent event) {
    	BouncingBallsPacketHandler.register();
    	
    	CapabilityManager.INSTANCE.register(IJumpCapability.class, new JumpCapabilityStorage(), JumpCapability::new);
    }
}
