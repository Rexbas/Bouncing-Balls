package bouncing_balls.init;

import bouncing_balls.BouncingBalls;
import bouncing_balls.item.BallType;
import bouncing_balls.item.BouncingBall;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BouncingBalls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BouncingBallsItems {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BouncingBalls.MODID);
		
	public static final RegistryObject<Item> BLACK = registerBall("black");
	public static final RegistryObject<Item> RED = registerBall("red");
	public static final RegistryObject<Item> DARKGREEN = registerBall("darkgreen");
	public static final RegistryObject<Item> BROWN = registerBall("brown");
	public static final RegistryObject<Item> BLUE = registerBall("blue");
	public static final RegistryObject<Item> PURPLE = registerBall("purple");
	public static final RegistryObject<Item> CYAN = registerBall("cyan");
	public static final RegistryObject<Item> LIGHTGRAY = registerBall("lightgray");
	public static final RegistryObject<Item> GRAY = registerBall("gray");
	public static final RegistryObject<Item> PINK = registerBall("pink");
	public static final RegistryObject<Item> GREEN = registerBall("green");
	public static final RegistryObject<Item> YELLOW = registerBall("yellow");
	public static final RegistryObject<Item> LIGHTBLUE = registerBall("lightblue");
	public static final RegistryObject<Item> MAGENTA = registerBall("magenta");
	public static final RegistryObject<Item> ORANGE = registerBall("orange");
	public static final RegistryObject<Item> WHITE = registerBall("white");

	public static final RegistryObject<Item> EGG = registerBall(BallType.EGG, "egg");
	public static final RegistryObject<Item> SNOW = registerBall(BallType.SNOW, "snow");
	public static final RegistryObject<Item> DYNAMITE = registerBall(BallType.DYNAMITE, "dynamite");
	public static final RegistryObject<Item> SLIME = registerBall(BallType.SLIME, "slime");
	public static final RegistryObject<Item> CLAY = registerBall(BallType.CLAY, "clay");
	public static final RegistryObject<Item> REDSTONE = registerBall(BallType.REDSTONE, "redstone");
	public static final RegistryObject<Item> GLOWSTONE = registerBall(BallType.GLOWSTONE, "glowstone");
	public static final RegistryObject<Item> GOLD = registerBall(BallType.GOLD, "gold");
	public static final RegistryObject<Item> IRON = registerBall(BallType.IRON, "iron");
	public static final RegistryObject<Item> DIAMOND = registerBall(BallType.DIAMOND, "diamond");
	public static final RegistryObject<Item> OBSIDIAN = registerBall(BallType.OBSIDIAN, "obsidian");
	public static final RegistryObject<Item> EMERALD = registerBall(BallType.EMERALD, "emerald");
	public static final RegistryObject<Item> NETHERSTAR = registerBall(BallType.NETHER_STAR, "netherstar");
	public static final RegistryObject<Item> QUARTZ = registerBall(BallType.QUARTZ, "quartz");
	
	private static RegistryObject<Item> registerBall (BallType type, String name) {
		return ITEMS.register(name, () -> BouncingBall.buildBall(type));
	}
	
	private static RegistryObject<Item> registerBall (String name) {
		return registerBall(BallType.NORMAL, name);
	}
}