package com.rexbas.bouncingballs.datagen;

import java.util.function.Consumer;

import com.rexbas.bouncingballs.api.item.BouncingBall;
import com.rexbas.bouncingballs.init.BouncingBallsItems;

import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;

public class BouncingBallsRecipeProvider extends RecipeProvider {

	public BouncingBallsRecipeProvider(DataGenerator generator) {
		super(generator);		
	}
	
	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
		BouncingBallsItems.ITEMS.getEntries().forEach((ball) -> {			
			if (ball.get() instanceof BouncingBall && ((BouncingBall) ball.get()).getRecipeItem() != Items.AIR) {
				ShapedRecipeBuilder.shaped(ball.get())
				.define('S', Blocks.SLIME_BLOCK).define('D', ((BouncingBall) ball.get()).getRecipeItem())
				.pattern("DDD")
				.pattern("DSD")
				.pattern("DDD").group("Bouncing Balls").unlockedBy("has_slime_block", has(Blocks.SLIME_BLOCK)).save(consumer);
			}
		});
	}
}