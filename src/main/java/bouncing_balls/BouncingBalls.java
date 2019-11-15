package bouncing_balls;

import bouncing_balls.capability.BB_CAPProvider;
import bouncing_balls.capability.BB_CAPStorage;
import bouncing_balls.capability.IBB_CAP;
import bouncing_balls.configuration.ConfigurationHandler;
import bouncing_balls.item.BallType;
import bouncing_balls.item.BouncingBall;
import bouncing_balls.jump.JumpHandler;
import bouncing_balls.packet.DecreaseStackHandler;
import bouncing_balls.packet.DecreaseStackPacket;
import bouncing_balls.registry.ItemRegistry;
import bouncing_balls.tab.TabBouncingBalls;
import bouncing_balls.updatechecker.UpdateChecker;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = BouncingBalls.MODID, name = BouncingBalls.NAME, version = BouncingBalls.MODVERSION, guiFactory = "bouncing_balls.configuration.GuiFactory")
public class BouncingBalls {
	
	/*TODO
	 * Drijven in water
	 * enchantment water ball frost walking ofzo
	 * prismarine
	 * chorus
	 * 3d model
	 * chest
	 */

	public static final String MODID = "bouncing_balls";
	public static final String NAME = "Bouncing Balls";
	public static final String MODVERSION = "1.5.1.1";
	public static final String MCVERSION = "1.12";
	
	public static Configuration config;
		
	@CapabilityInject(IBB_CAP.class)
	public static final Capability<IBB_CAP> BB_CAP = null;
	
	public static BouncingBallsEventHandler bouncingBallsEventHandler = new BouncingBallsEventHandler();

	public static JumpHandler jumpHandler = new JumpHandler();
	
	public static SimpleNetworkWrapper network;
	
	public static CreativeTabs tabBouncingBalls = new TabBouncingBalls(CreativeTabs.getNextID(), "bouncing_balls");
	
	public static SoundEvent sound_bounce = new SoundEvent(new ResourceLocation(BouncingBalls.MODID + ":bouncingball.bounce")).setRegistryName("bouncingball.bounce");
	
	public static Item blackBouncingBall = new BouncingBall(BallType.NORMAL, 0, "blackbouncingball");
	public static Item redBouncingBall = new BouncingBall(BallType.NORMAL, 1, "redbouncingball");
	public static Item darkGreenBouncingBall = new BouncingBall(BallType.NORMAL, 2, "darkgreenbouncingball");
	public static Item brownBouncingBall = new BouncingBall(BallType.NORMAL, 3, "brownbouncingball");
	public static Item blueBouncingBall = new BouncingBall(BallType.NORMAL, 4, "bluebouncingball");
	public static Item purpleBouncingBall = new BouncingBall(BallType.NORMAL, 5, "purplebouncingball");
	public static Item cyanBouncingBall = new BouncingBall(BallType.NORMAL, 6, "cyanbouncingball");
	public static Item lightGrayBouncingBall = new BouncingBall(BallType.NORMAL, 7, "lightgraybouncingball");
	public static Item grayBouncingBall = new BouncingBall(BallType.NORMAL, 8, "graybouncingball");
	public static Item pinkBouncingBall = new BouncingBall(BallType.NORMAL, 9, "pinkbouncingball");
	public static Item greenBouncingBall = new BouncingBall(BallType.NORMAL, 10, "greenbouncingball");
	public static Item yellowBouncingBall = new BouncingBall(BallType.NORMAL, 11, "yellowbouncingball");
	public static Item lightBlueBouncingBall = new BouncingBall(BallType.NORMAL, 12, "lightbluebouncingball");
	public static Item magentaBouncingBall = new BouncingBall(BallType.NORMAL, 13, "magentabouncingball");
	public static Item orangeBouncingBall = new BouncingBall(BallType.NORMAL, 14, "orangebouncingball");
	public static Item whiteBouncingBall = new BouncingBall(BallType.NORMAL, 15, "whitebouncingball");

	public static Item eggBouncingBall = new BouncingBall(BallType.EGG, 16, "eggbouncingball");
	public static Item snowBouncingBall = new BouncingBall(BallType.SNOW, 17, "snowbouncingball");
	public static Item dynamiteBouncingBall = new BouncingBall(BallType.DYNAMITE, 18, "dynamitebouncingball");
	public static Item slimeBouncingBall = new BouncingBall(BallType.SLIME, 19, "slimebouncingball");
	public static Item clayBouncingBall = new BouncingBall(BallType.CLAY , 20, "claybouncingball");
	public static Item redstoneBouncingBall = new BouncingBall(BallType.REDSTONE, 21, "redstonebouncingball");
	public static Item glowstoneBouncingBall = new BouncingBall(BallType.GLOWSTONE, 22, "glowstonebouncingball");
	public static Item goldenBouncingBall = new BouncingBall(BallType.GOLD, 23, "goldenbouncingball");
	public static Item ironBouncingBall = new BouncingBall(BallType.IRON, 24, "ironbouncingball");
	public static Item diamondBouncingBall = new BouncingBall(BallType.DIAMOND, 25, "diamondbouncingball");
	public static Item obsidianBouncingBall = new BouncingBall(BallType.OBSIDIAN, 26, "obsidianbouncingball");
	public static Item emeraldBouncingBall = new BouncingBall(BallType.EMERALD, 27, "emeraldbouncingball");
	public static Item netherStarBouncingBall = new BouncingBall(BallType.NETHER_STAR, 28, "netherstarbouncingball");
	public static Item quartzBouncingBall = new BouncingBall(BallType.QUARTZ, 29, "quartzbouncingball");
	
    @SidedProxy(clientSide="bouncing_balls.ClientProxy", serverSide="bouncing_balls.CommonProxy")
    public static CommonProxy commonProxy;
    public static ClientProxy clientProxy;
    
    @Instance(MODID)
    public static BouncingBalls instance;
	public static UpdateChecker updateChecker;
	public static boolean haveWarnedVersionOutOfDate = false;
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	ForgeRegistries.SOUND_EVENTS.register(sound_bounce);
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event) {
    	commonProxy.registerUpdateChecker();
    }
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
    	config = new Configuration(event.getSuggestedConfigurationFile());
    	ConfigurationHandler.syncConfig();
    	
    	network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
    	network.registerMessage(new DecreaseStackHandler(), DecreaseStackPacket.class, 0, Side.SERVER);

    	MinecraftForge.EVENT_BUS.register(instance);
    	MinecraftForge.EVENT_BUS.register(bouncingBallsEventHandler);
    	    	
    	CapabilityManager.INSTANCE.register(IBB_CAP.class, new BB_CAPStorage(), BB_CAPProvider.class);
    	
    	ItemRegistry.registerItems();
    	
    	commonProxy.registerRenders();
    }
}
