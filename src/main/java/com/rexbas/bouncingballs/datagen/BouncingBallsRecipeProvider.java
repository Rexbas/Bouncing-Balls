package com.rexbas.bouncingballs.datagen;

import java.util.function.Consumer;

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
	      ShapedRecipeBuilder.shaped(BouncingBallsItems.WHITE.get())
	      .define('S', Blocks.SLIME_BLOCK).define('D', Items.WHITE_DYE)
	      .pattern("DDD")
	      .pattern("DSD")
	      .pattern("DDD").unlockedBy("has_slime_ball", has(Items.SLIME_BALL)).save(consumer);
	      
	      ShapedRecipeBuilder.shaped(BouncingBallsItems.RED.get())
	      .define('S', Blocks.SLIME_BLOCK).define('D', Items.RED_DYE)
	      .pattern("DDD")
	      .pattern("DSD")
	      .pattern("DDD").unlockedBy("has_slime_ball", has(Items.SLIME_BALL)).save(consumer);
	}
}