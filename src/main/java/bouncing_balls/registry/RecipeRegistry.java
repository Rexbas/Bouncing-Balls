package bouncing_balls.registry;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import bouncing_balls.BouncingBalls;
import bouncing_balls.item.BouncingBall;
import bouncing_balls.item.BallType;

public class RecipeRegistry {

	public static void registerRecipes() {		
		//Normal Balls
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBall.returnByID(i)), new Object[] {
				"SSS",
				"SCS",
				"SSS",
				'C', new ItemStack(Items.dye, 1, i), 'S', Items.slime_ball
			});
		}
		
		//EggBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.eggBouncingBall), new Object[] {
				"EEE",
				"EBE",
				"EEE",
				'B', BouncingBall.returnByID(i), 'E', Items.egg
			});
		}
		
		//SnowBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.snowBouncingBall), new Object[] {
				"SSS",
				"SBS",
				"SSS",
				'B', BouncingBall.returnByID(i), 'S', Items.snowball
			});
		}
				
		//ClayBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.clayBouncingBall), new Object[] {
				"CCC",
				"CBC",
				"CCC",
				'B', BouncingBall.returnByID(i), 'C', Items.clay_ball
			});
		}
		
		//RedstoneBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.redstoneBouncingBall), new Object[] {
				"RRR",
				"RBR",
				"RRR",
				'B', BouncingBall.returnByID(i), 'R', Items.redstone
			});
		}

		//GlowstoneBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.glowstoneBouncingBall), new Object[] {
				"GGG",
				"GBG",
				"GGG",
				'B', BouncingBall.returnByID(i), 'G', Items.glowstone_dust
			});
		}
		
		//GoldenBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.goldenBouncingBall), new Object[] {
				"GGG",
				"GBG",
				"GGG",
				'B', BouncingBall.returnByID(i), 'G', Items.gold_ingot
			});
		}
		
		//IronBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.ironBouncingBall), new Object[] {
				"III",
				"IBI",
				"III",
				'B', BouncingBall.returnByID(i), 'I', Items.iron_ingot
			});
		}
		
		//DiamondBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.diamondBouncingBall), new Object[] {
				"DDD",
				"DBD",
				"DDD",
				'B', BouncingBall.returnByID(i), 'D', Items.diamond
			});
		}
		
		//EmeraldBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.emeraldBouncingBall), new Object[] {
				"EEE",
				"EBE",
				"EEE",
				'B', BouncingBall.returnByID(i), 'E', Items.emerald
			});
		}
	}
}
