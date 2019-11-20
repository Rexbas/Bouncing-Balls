package bouncing_balls.registry;

import bouncing_balls.BouncingBalls;
import bouncing_balls.item.BouncingBall;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeRegistry {

	public static void registerRecipes() {		
		//Normal Balls
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBall.returnByID(i)), new Object[] {
				"SSS",
				"SCS",
				"SSS",
				'C', new ItemStack(Items.DYE, 1, i), 'S', Items.SLIME_BALL
			});
		}
		
		//EggBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.eggBouncingBall), new Object[] {
				"EEE",
				"EBE",
				"EEE",
				'B', BouncingBall.returnByID(i), 'E', Items.EGG
			});
		}
		
		//SnowBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.snowBouncingBall), new Object[] {
				"SSS",
				"SBS",
				"SSS",
				'B', BouncingBall.returnByID(i), 'S', Items.SNOWBALL
			});
		}
		
		//DynamiteBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.dynamiteBouncingBall), new Object[] {
				"GGG",
				"GBG",
				"GGG",
				'B', BouncingBall.returnByID(i), 'G', Items.GUNPOWDER
			});
		}
		
		//SlimeBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.slimeBouncingBall), new Object[] {
				"SSS",
				"SBS",
				"SSS",
				'B', BouncingBall.returnByID(i), 'S', Items.SLIME_BALL
			});
		}
				
		//ClayBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.clayBouncingBall), new Object[] {
				"CCC",
				"CBC",
				"CCC",
				'B', BouncingBall.returnByID(i), 'C', Items.CLAY_BALL
			});
		}
		
		//RedstoneBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.redstoneBouncingBall), new Object[] {
				"RRR",
				"RBR",
				"RRR",
				'B', BouncingBall.returnByID(i), 'R', Items.REDSTONE
			});
		}

		//GlowstoneBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.glowstoneBouncingBall), new Object[] {
				"GGG",
				"GBG",
				"GGG",
				'B', BouncingBall.returnByID(i), 'G', Items.GLOWSTONE_DUST
			});
		}
		
		//GoldenBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.goldenBouncingBall), new Object[] {
				"GGG",
				"GBG",
				"GGG",
				'B', BouncingBall.returnByID(i), 'G', Items.GOLD_INGOT
			});
		}
		
		//IronBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.ironBouncingBall), new Object[] {
				"III",
				"IBI",
				"III",
				'B', BouncingBall.returnByID(i), 'I', Items.IRON_INGOT
			});
		}
		
		//DiamondBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.diamondBouncingBall), new Object[] {
				"DDD",
				"DBD",
				"DDD",
				'B', BouncingBall.returnByID(i), 'D', Items.DIAMOND
			});
		}
		
		//ObsidianBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.obsidianBouncingBall), new Object[] {
				"OOO",
				"OBO",
				"OOO",
				'B', BouncingBall.returnByID(i), 'O', Item.getItemFromBlock(Blocks.OBSIDIAN)
			});
		}
		
		//EmeraldBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.emeraldBouncingBall), new Object[] {
				"EEE",
				"EBE",
				"EEE",
				'B', BouncingBall.returnByID(i), 'E', Items.EMERALD
			});
		}
		
		//NetherStarBouncingBall
		GameRegistry.addRecipe(new ItemStack(BouncingBalls.netherStarBouncingBall), new Object[] {
			"SSS",
			"SNS",
			"SSS",
			'S', Items.SLIME_BALL, 'N', Items.NETHER_STAR
		});
		
		//QuartzBouncingBall
		for(int i = 0; i <= 15; i++) {
			GameRegistry.addRecipe(new ItemStack(BouncingBalls.quartzBouncingBall), new Object[] {
				"QQQ",
				"QBQ",
				"QQQ",
				'B', BouncingBall.returnByID(i), 'Q', Items.QUARTZ
			});
		}
	}
}
