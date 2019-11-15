
package bouncing_balls;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import bouncing_balls.item.BouncingBall;
import bouncing_balls.item.EnumType;
import bouncing_balls.registry.ItemRegistry;
import bouncing_balls.registry.RecipeRegistry;
import bouncing_balls.tab.TabBouncingBalls;
import bouncing_balls.throwable.CustomEntityEgg;
import bouncing_balls.updatechecker.UpdateChecker;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;

@Mod(modid = BouncingBalls.MODID, name = BouncingBalls.NAME, version = BouncingBalls.MODVERSION)
public class BouncingBalls {

	public static final String MODID = "bouncing_balls";
	public static final String NAME = "Bouncing Balls";
	public static final String MODVERSION = "1.1";

	public static BouncingBallsEventHandler bouncingBallsEventHandler = new BouncingBallsEventHandler();
	
	public static CreativeTabs tabBouncingBalls = new TabBouncingBalls(CreativeTabs.getNextID(), "BouncingBalls");
	
	public static Item blackBouncingBall = new BouncingBall(EnumType.NORMAL, 0).setUnlocalizedName("BlackBouncingBall").setTextureName(BouncingBalls.MODID + ":BlackBouncingBall");
	public static Item redBouncingBall = new BouncingBall(EnumType.NORMAL, 1).setUnlocalizedName("RedBouncingBall").setTextureName(BouncingBalls.MODID + ":RedBouncingBall");
	public static Item darkGreenBouncingBall = new BouncingBall(EnumType.NORMAL, 2).setUnlocalizedName("DarkGreenBouncingBall").setTextureName(BouncingBalls.MODID + ":DarkGreenBouncingBall");
	public static Item brownBouncingBall = new BouncingBall(EnumType.NORMAL, 3).setUnlocalizedName("BrownBouncingBall").setTextureName(BouncingBalls.MODID + ":BrownBouncingBall");
	public static Item blueBouncingBall = new BouncingBall(EnumType.NORMAL, 4).setUnlocalizedName("BlueBouncingBall").setTextureName(BouncingBalls.MODID + ":BlueBouncingBall");
	public static Item purpleBouncingBall = new BouncingBall(EnumType.NORMAL, 5).setUnlocalizedName("PurpleBouncingBall").setTextureName(BouncingBalls.MODID + ":PurpleBouncingBall");
	public static Item cyanBouncingBall = new BouncingBall(EnumType.NORMAL, 6).setUnlocalizedName("CyanBouncingBall").setTextureName(BouncingBalls.MODID + ":CyanBouncingBall");
	public static Item lightGrayBouncingBall = new BouncingBall(EnumType.NORMAL, 7).setUnlocalizedName("LightGrayBouncingBall").setTextureName(BouncingBalls.MODID + ":LightGrayBouncingBall");
	public static Item grayBouncingBall = new BouncingBall(EnumType.NORMAL, 8).setUnlocalizedName("GrayBouncingBall").setTextureName(BouncingBalls.MODID + ":GrayBouncingBall");
	public static Item pinkBouncingBall = new BouncingBall(EnumType.NORMAL, 9).setUnlocalizedName("PinkBouncingBall").setTextureName(BouncingBalls.MODID + ":PinkBouncingBall");
	public static Item greenBouncingBall = new BouncingBall(EnumType.NORMAL, 10).setUnlocalizedName("GreenBouncingBall").setTextureName(BouncingBalls.MODID + ":GreenBouncingBall");
	public static Item yellowBouncingBall = new BouncingBall(EnumType.NORMAL, 11).setUnlocalizedName("YellowBouncingBall").setTextureName(BouncingBalls.MODID + ":YellowBouncingBall");
	public static Item lightBlueBouncingBall = new BouncingBall(EnumType.NORMAL, 12).setUnlocalizedName("LightBlueBouncingBall").setTextureName(BouncingBalls.MODID + ":LightBlueBouncingBall");
	public static Item magentaBouncingBall = new BouncingBall(EnumType.NORMAL, 13).setUnlocalizedName("MagentaBouncingBall").setTextureName(BouncingBalls.MODID + ":MagentaBouncingBall");
	public static Item orangeBouncingBall = new BouncingBall(EnumType.NORMAL, 14).setUnlocalizedName("OrangeBouncingBall").setTextureName(BouncingBalls.MODID + ":OrangeBouncingBall");
	public static Item whiteBouncingBall = new BouncingBall(EnumType.NORMAL, 15).setUnlocalizedName("WhiteBouncingBall").setTextureName(BouncingBalls.MODID + ":WhiteBouncingBall");

	public static Item eggBouncingBall = new BouncingBall(EnumType.EGG, 16).setUnlocalizedName("EggBouncingBall").setTextureName(BouncingBalls.MODID + ":EggBouncingBall");
	public static Item clayBouncingBall = new BouncingBall(EnumType.CLAY , 17).setUnlocalizedName("ClayBouncingBall").setTextureName(BouncingBalls.MODID + ":ClayBouncingBall");
	public static Item redstoneBouncingBall = new BouncingBall(EnumType.REDSTONE, 18).setUnlocalizedName("RedstoneBouncingBall").setTextureName(BouncingBalls.MODID + ":RedstoneBouncingBall");
	public static Item glowstoneBouncingBall = new BouncingBall(EnumType.GLOWSTONE, 19).setUnlocalizedName("GlowstoneBouncingBall").setTextureName(BouncingBalls.MODID + ":GlowstoneBouncingBall");
	public static Item goldenBouncingBall = new BouncingBall(EnumType.GOLD, 20).setUnlocalizedName("GoldenBouncingBall").setTextureName(BouncingBalls.MODID + ":GoldenBouncingBall");
	public static Item ironBouncingBall = new BouncingBall(EnumType.IRON, 21).setUnlocalizedName("IronBouncingBall").setTextureName(BouncingBalls.MODID + ":IronBouncingBall");
	public static Item diamondBouncingBall = new BouncingBall(EnumType.DIAMOND, 22).setUnlocalizedName("DiamondBouncingBall").setTextureName(BouncingBalls.MODID + ":DiamondBouncingBall");
	public static Item emeraldBouncingBall = new BouncingBall(EnumType.EMERALD, 23).setUnlocalizedName("EmeraldBouncingBall").setTextureName(BouncingBalls.MODID + ":EmeraldBouncingBall");
	
    @SidedProxy(clientSide="bouncing_balls.ClientProxy", serverSide="bouncing_balls.CommonProxy")
    public static CommonProxy commonProxy;
    public static ClientProxy clientProxy;
    
    @Instance(MODID)
    public static BouncingBalls instance;
	public static UpdateChecker updateChecker;
	public static boolean haveWarnedVersionOutOfDate = false;
    
    @EventHandler
    public void init(FMLInitializationEvent e) {
    
    }
    
    @EventHandler
    public void load(FMLInitializationEvent e) {
    	commonProxy.registerRenders();
    	commonProxy.registerUpdateChecker();
    }
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent e) {
    	MinecraftForge.EVENT_BUS.register(instance);
    	MinecraftForge.EVENT_BUS.register(bouncingBallsEventHandler);
    	FMLCommonHandler.instance().bus().register(bouncingBallsEventHandler);
    	ItemRegistry.registerItems();
        EntityRegistry.registerModEntity(CustomEntityEgg.class, "customEntityEgg", 0, this.MODID, 64, 10, true);
    	RecipeRegistry.registerRecipes();
    }
}
