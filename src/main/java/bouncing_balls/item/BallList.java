package bouncing_balls.item;

import bouncing_balls.BouncingBalls;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = BouncingBalls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(BouncingBalls.MODID)
public class BallList {
	public static final Item BLACK = null;
	public static final Item RED = null;
	public static final Item DARKGREEN = null;
	public static final Item BROWN = null;
	public static final Item BLUE = null;
	public static final Item PURPLE = null;
	public static final Item CYAN = null;
	public static final Item LIGHTGRAY = null;
	public static final Item GRAY = null;
	public static final Item PINK = null;
	public static final Item GREEN = null;
	public static final Item YELLOW = null;
	public static final Item LIGHTBLUE = null;
	public static final Item MAGENTA = null;
	public static final Item ORANGE = null;
	public static final Item WHITE = null;

	public static final Item EGG = null;
	public static final Item SNOW = null;
	public static final Item DYNAMITE = null;
	public static final Item SLIME = null;
	public static final Item CLAY = null;
	public static final Item REDSTONE = null;
	public static final Item GLOWSTONE = null;
	public static final Item GOLD = null;
	public static final Item IRON = null;
	public static final Item DIAMOND = null;
	public static final Item OBSIDIAN = null;
	public static final Item EMERALD = null;
	public static final Item NETHERSTAR = null;
	public static final Item QUARTZ = null;
	
	@SubscribeEvent
	public static void registerItemEvent(final RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				BouncingBall.buildBall("black"),
				BouncingBall.buildBall("red"),
				BouncingBall.buildBall("darkgreen"),
				BouncingBall.buildBall("brown"),
				BouncingBall.buildBall("blue"),
				BouncingBall.buildBall("purple"),
				BouncingBall.buildBall("cyan"),
				BouncingBall.buildBall("lightgray"),
				BouncingBall.buildBall("gray"),
				BouncingBall.buildBall("pink"),
				BouncingBall.buildBall("green"),
				BouncingBall.buildBall("yellow"),
				BouncingBall.buildBall("lightblue"),
				BouncingBall.buildBall("magenta"),
				BouncingBall.buildBall("orange"),
				BouncingBall.buildBall("white"),
				
				BouncingBall.buildBall(BallType.EGG, "egg"),
				BouncingBall.buildBall(BallType.SNOW, "snow"),
				BouncingBall.buildBall(BallType.DYNAMITE, "dynamite"),
				BouncingBall.buildBall(BallType.SLIME, "slime"),
				BouncingBall.buildBall(BallType.CLAY, "clay"),
				BouncingBall.buildBall(BallType.REDSTONE, "redstone"),
				BouncingBall.buildBall(BallType.GLOWSTONE, "glowstone"),
				BouncingBall.buildBall(BallType.GOLD, "gold"),
				BouncingBall.buildBall(BallType.IRON, "iron"),
				BouncingBall.buildBall(BallType.DIAMOND, "diamond"),
				BouncingBall.buildBall(BallType.OBSIDIAN, "obsidian"),
				BouncingBall.buildBall(BallType.EMERALD, "emerald"),
				BouncingBall.buildBall(BallType.NETHER_STAR, "netherstar"),
				BouncingBall.buildBall(BallType.QUARTZ, "quartz")
				);
	}
}
