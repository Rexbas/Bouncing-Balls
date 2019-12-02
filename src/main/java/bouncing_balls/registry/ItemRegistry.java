package bouncing_balls.registry;

import bouncing_balls.BouncingBalls;
import bouncing_balls.BouncingBallsItems;
import bouncing_balls.item.BallType;
import bouncing_balls.item.BouncingBall;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BouncingBalls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegistry {
	
	@SubscribeEvent
	public static void registerItemEvent(final RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				BouncingBallsItems.blackBouncingBall = BouncingBall.buildBall("black"),
				BouncingBallsItems.redBouncingBall = BouncingBall.buildBall("red"),
				BouncingBallsItems.darkgreenBouncingBall = BouncingBall.buildBall("darkgreen"),
				BouncingBallsItems.brownBouncingBall = BouncingBall.buildBall("brown"),
				BouncingBallsItems.blueBouncingBall = BouncingBall.buildBall("blue"),
				BouncingBallsItems.purpleBouncingBall = BouncingBall.buildBall("purple"),
				BouncingBallsItems.cyanBouncingBall = BouncingBall.buildBall("cyan"),
				BouncingBallsItems.lightgrayBouncingBall = BouncingBall.buildBall("lightgray"),
				BouncingBallsItems.grayBouncingBall = BouncingBall.buildBall("gray"),
				BouncingBallsItems.pinkBouncingBall = BouncingBall.buildBall("pink"),
				BouncingBallsItems.greenBouncingBall = BouncingBall.buildBall("green"),
				BouncingBallsItems.yellowBouncingBall = BouncingBall.buildBall("yellow"),
				BouncingBallsItems.lightblueBouncingBall = BouncingBall.buildBall("lightblue"),
				BouncingBallsItems.magentaBouncingBall = BouncingBall.buildBall("magenta"),
				BouncingBallsItems.orangeBouncingBall = BouncingBall.buildBall("orange"),
				BouncingBallsItems.whiteBouncingBall = BouncingBall.buildBall("white"),
				
				BouncingBallsItems.eggBouncingBall = BouncingBall.buildBall(BallType.EGG, "egg"),
				BouncingBallsItems.snowBouncingBall = BouncingBall.buildBall(BallType.SNOW, "snow"),
				BouncingBallsItems.dynamiteBouncingBall = BouncingBall.buildBall(BallType.DYNAMITE, "dynamite"),
				BouncingBallsItems.slimeBouncingBall = BouncingBall.buildBall(BallType.SLIME, "slime"),
				BouncingBallsItems.clayBouncingBall = BouncingBall.buildBall(BallType.CLAY, "clay"),
				BouncingBallsItems.redstoneBouncingBall = BouncingBall.buildBall(BallType.REDSTONE, "redstone"),
				BouncingBallsItems.glowstoneBouncingBall = BouncingBall.buildBall(BallType.GLOWSTONE, "glowstone"),
				BouncingBallsItems.goldenBouncingBall = BouncingBall.buildBall(BallType.GOLD, "gold"),
				BouncingBallsItems.ironBouncingBall = BouncingBall.buildBall(BallType.IRON, "iron"),
				BouncingBallsItems.diamondBouncingBall = BouncingBall.buildBall(BallType.DIAMOND, "diamond"),
				BouncingBallsItems.obsidianBouncingBall = BouncingBall.buildBall(BallType.OBSIDIAN, "obsidian"),
				BouncingBallsItems.emeraldBouncingBall = BouncingBall.buildBall(BallType.EMERALD, "emerald"),
				BouncingBallsItems.netherstarBouncingBall = BouncingBall.buildBall(BallType.NETHER_STAR, "netherstar"),
				BouncingBallsItems.quartzBouncingBall = BouncingBall.buildBall(BallType.QUARTZ, "quartz")
				);
	}
}
