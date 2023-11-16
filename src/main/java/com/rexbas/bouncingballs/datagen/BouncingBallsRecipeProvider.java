package com.rexbas.bouncingballs.datagen;

import com.rexbas.bouncingballs.api.item.BouncingBall;
import com.rexbas.bouncingballs.init.BouncingBallsItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class BouncingBallsRecipeProvider extends RecipeProvider {

	public BouncingBallsRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider);
	}
	
	@Override
	protected void buildRecipes(RecipeOutput recipeOutput) {
		BouncingBallsItems.ITEMS.getEntries().forEach((ball) -> {			
			if (ball.get() instanceof BouncingBall && ((BouncingBall) ball.get()).getRecipeItem() != Items.AIR) {
				ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, ball.get())
				.define('S', Blocks.SLIME_BLOCK).define('D', ((BouncingBall) ball.get()).getRecipeItem())
				.pattern("DDD")
				.pattern("DSD")
				.pattern("DDD").group("Bouncing Balls").unlockedBy("has_slime_block", has(Blocks.SLIME_BLOCK)).save(recipeOutput);
			}
		});
	}
}