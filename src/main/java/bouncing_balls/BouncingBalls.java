package bouncing_balls;

import bouncing_balls.capability.IJumpCapability;
import bouncing_balls.capability.JumpCapability;
import bouncing_balls.capability.JumpCapabilityStorage;
import bouncing_balls.item.BallType;
import bouncing_balls.item.BouncingBall;
import bouncing_balls.itemgroup.BouncingBallsItemGroup;
import bouncing_balls.jump.JumpHandler;
import bouncing_balls.network.BouncingBallsPacketHandler;
import net.minecraft.item.Item;
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
	public static final String MODNAME = "Bouncing Balls";
		
	public static BouncingBallsEventHandler bouncingBallsEventHandler = new BouncingBallsEventHandler();

	public static JumpHandler jumpHandler = new JumpHandler();
		
	public static ItemGroup itemGroup = new BouncingBallsItemGroup(MODID);
	
	public static SoundEvent sound_bounce = new SoundEvent(new ResourceLocation(BouncingBalls.MODID, "bounce"));
	
	public static Item blackBouncingBall = new BouncingBall(BallType.NORMAL, 0, "black");
	public static Item redBouncingBall = new BouncingBall(BallType.NORMAL, 1, "red");
	public static Item darkGreenBouncingBall = new BouncingBall(BallType.NORMAL, 2, "darkgreen");
	public static Item brownBouncingBall = new BouncingBall(BallType.NORMAL, 3, "brown");
	public static Item blueBouncingBall = new BouncingBall(BallType.NORMAL, 4, "blue");
	public static Item purpleBouncingBall = new BouncingBall(BallType.NORMAL, 5, "purple");
	public static Item cyanBouncingBall = new BouncingBall(BallType.NORMAL, 6, "cyan");
	public static Item lightGrayBouncingBall = new BouncingBall(BallType.NORMAL, 7, "lightgray");
	public static Item grayBouncingBall = new BouncingBall(BallType.NORMAL, 8, "gray");
	public static Item pinkBouncingBall = new BouncingBall(BallType.NORMAL, 9, "pink");
	public static Item greenBouncingBall = new BouncingBall(BallType.NORMAL, 10, "green");
	public static Item yellowBouncingBall = new BouncingBall(BallType.NORMAL, 11, "yellow");
	public static Item lightBlueBouncingBall = new BouncingBall(BallType.NORMAL, 12, "lightblue");
	public static Item magentaBouncingBall = new BouncingBall(BallType.NORMAL, 13, "magenta");
	public static Item orangeBouncingBall = new BouncingBall(BallType.NORMAL, 14, "orange");
	public static Item whiteBouncingBall = new BouncingBall(BallType.NORMAL, 15, "white");

	public static Item eggBouncingBall = new BouncingBall(BallType.EGG, 16, "egg");
	public static Item snowBouncingBall = new BouncingBall(BallType.SNOW, 17, "snow");
	public static Item dynamiteBouncingBall = new BouncingBall(BallType.DYNAMITE, 18, "dynamite");
	public static Item slimeBouncingBall = new BouncingBall(BallType.SLIME, 19, "slime");
	public static Item clayBouncingBall = new BouncingBall(BallType.CLAY , 20, "clay");
	public static Item redstoneBouncingBall = new BouncingBall(BallType.REDSTONE, 21, "redstone");
	public static Item glowstoneBouncingBall = new BouncingBall(BallType.GLOWSTONE, 22, "glowstone");
	public static Item goldenBouncingBall = new BouncingBall(BallType.GOLD, 23, "gold");
	public static Item ironBouncingBall = new BouncingBall(BallType.IRON, 24, "iron");
	public static Item diamondBouncingBall = new BouncingBall(BallType.DIAMOND, 25, "diamond");
	public static Item obsidianBouncingBall = new BouncingBall(BallType.OBSIDIAN, 26, "obsidian");
	public static Item emeraldBouncingBall = new BouncingBall(BallType.EMERALD, 27, "emerald");
	public static Item netherStarBouncingBall = new BouncingBall(BallType.NETHER_STAR, 28, "netherstar");
	public static Item quartzBouncingBall = new BouncingBall(BallType.QUARTZ, 29, "quartz");
	
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

    	MinecraftForge.EVENT_BUS.register(instance);
    	MinecraftForge.EVENT_BUS.register(bouncingBallsEventHandler);
    	
    	CapabilityManager.INSTANCE.register(IJumpCapability.class, new JumpCapabilityStorage(), JumpCapability::new);
    }
}
