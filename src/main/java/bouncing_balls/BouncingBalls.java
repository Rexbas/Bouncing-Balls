package bouncing_balls;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
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
import net.minecraftforge.fml.common.registry.EntityRegistry;
import bouncing_balls.capability.BB_CAPProvider;
import bouncing_balls.capability.BB_CAPStorage;
import bouncing_balls.capability.IBB_CAP;
import bouncing_balls.configuration.ConfigurationHandler;
import bouncing_balls.item.BallType;
import bouncing_balls.item.BouncingBall;
import bouncing_balls.jump.JumpHandler;
import bouncing_balls.registry.ItemRegistry;
import bouncing_balls.registry.RecipeRegistry;
import bouncing_balls.tab.TabBouncingBalls;
import bouncing_balls.throwable.CustomEntityEgg;
import bouncing_balls.throwable.CustomEntitySnowball;
import bouncing_balls.updatechecker.UpdateChecker;

@Mod(modid = BouncingBalls.MODID, name = BouncingBalls.NAME, version = BouncingBalls.MODVERSION, guiFactory = "bouncing_balls.configuration.GuiFactory")
public class BouncingBalls {

	public static final String MODID = "bouncing_balls";
	public static final String NAME = "Bouncing Balls";
	public static final String MODVERSION = "1.2.1";
	
	public static Configuration config;
		
	@CapabilityInject(IBB_CAP.class)
	public static final Capability<IBB_CAP> BB_CAP = null;
	
	public static BouncingBallsEventHandler bouncingBallsEventHandler = new BouncingBallsEventHandler();

	public static JumpHandler jumpHandler = new JumpHandler();
	
	public static CreativeTabs tabBouncingBalls = new TabBouncingBalls(CreativeTabs.getNextID(), "BouncingBalls");
		
	public static Item blackBouncingBall = new BouncingBall(BallType.NORMAL, 0).setUnlocalizedName("BlackBouncingBall");
	public static Item redBouncingBall = new BouncingBall(BallType.NORMAL, 1).setUnlocalizedName("RedBouncingBall");
	public static Item darkGreenBouncingBall = new BouncingBall(BallType.NORMAL, 2).setUnlocalizedName("DarkGreenBouncingBall");
	public static Item brownBouncingBall = new BouncingBall(BallType.NORMAL, 3).setUnlocalizedName("BrownBouncingBall");
	public static Item blueBouncingBall = new BouncingBall(BallType.NORMAL, 4).setUnlocalizedName("BlueBouncingBall");
	public static Item purpleBouncingBall = new BouncingBall(BallType.NORMAL, 5).setUnlocalizedName("PurpleBouncingBall");
	public static Item cyanBouncingBall = new BouncingBall(BallType.NORMAL, 6).setUnlocalizedName("CyanBouncingBall");
	public static Item lightGrayBouncingBall = new BouncingBall(BallType.NORMAL, 7).setUnlocalizedName("LightGrayBouncingBall");
	public static Item grayBouncingBall = new BouncingBall(BallType.NORMAL, 8).setUnlocalizedName("GrayBouncingBall");
	public static Item pinkBouncingBall = new BouncingBall(BallType.NORMAL, 9).setUnlocalizedName("PinkBouncingBall");
	public static Item greenBouncingBall = new BouncingBall(BallType.NORMAL, 10).setUnlocalizedName("GreenBouncingBall");
	public static Item yellowBouncingBall = new BouncingBall(BallType.NORMAL, 11).setUnlocalizedName("YellowBouncingBall");
	public static Item lightBlueBouncingBall = new BouncingBall(BallType.NORMAL, 12).setUnlocalizedName("LightBlueBouncingBall");
	public static Item magentaBouncingBall = new BouncingBall(BallType.NORMAL, 13).setUnlocalizedName("MagentaBouncingBall");
	public static Item orangeBouncingBall = new BouncingBall(BallType.NORMAL, 14).setUnlocalizedName("OrangeBouncingBall");
	public static Item whiteBouncingBall = new BouncingBall(BallType.NORMAL, 15).setUnlocalizedName("WhiteBouncingBall");

	public static Item eggBouncingBall = new BouncingBall(BallType.EGG, 16).setUnlocalizedName("EggBouncingBall");
	public static Item snowBouncingBall = new BouncingBall(BallType.SNOW, 17).setUnlocalizedName("SnowBouncingBall");
	public static Item clayBouncingBall = new BouncingBall(BallType.CLAY , 18).setUnlocalizedName("ClayBouncingBall");
	public static Item redstoneBouncingBall = new BouncingBall(BallType.REDSTONE, 19).setUnlocalizedName("RedstoneBouncingBall");
	public static Item glowstoneBouncingBall = new BouncingBall(BallType.GLOWSTONE, 20).setUnlocalizedName("GlowstoneBouncingBall");
	public static Item goldenBouncingBall = new BouncingBall(BallType.GOLD, 21).setUnlocalizedName("GoldenBouncingBall");
	public static Item ironBouncingBall = new BouncingBall(BallType.IRON, 22).setUnlocalizedName("IronBouncingBall");
	public static Item diamondBouncingBall = new BouncingBall(BallType.DIAMOND, 23).setUnlocalizedName("DiamondBouncingBall");
	public static Item emeraldBouncingBall = new BouncingBall(BallType.EMERALD, 24).setUnlocalizedName("EmeraldBouncingBall");
	
    @SidedProxy(clientSide="bouncing_balls.ClientProxy", serverSide="bouncing_balls.CommonProxy")
    public static CommonProxy commonProxy;
    public static ClientProxy clientProxy;
    
    @Instance(MODID)
    public static BouncingBalls instance;
	public static UpdateChecker updateChecker;
	public static boolean haveWarnedVersionOutOfDate = false;
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event) {
    	commonProxy.registerRenders();
    	commonProxy.registerUpdateChecker();
    }
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
    	config = new Configuration(event.getSuggestedConfigurationFile());
    	ConfigurationHandler.syncConfig();

    	MinecraftForge.EVENT_BUS.register(instance);
    	MinecraftForge.EVENT_BUS.register(bouncingBallsEventHandler);
    	    	
    	CapabilityManager.INSTANCE.register(IBB_CAP.class, new BB_CAPStorage(), BB_CAPProvider.class);
    	
    	ItemRegistry.registerItems();
        EntityRegistry.registerModEntity(CustomEntityEgg.class, "customEntityEgg", 0, this.MODID, 64, 10, true);
        EntityRegistry.registerModEntity(CustomEntitySnowball.class, "customEntitySnowball", 1, this.MODID, 64, 10, true);
    	RecipeRegistry.registerRecipes();
    }
}
