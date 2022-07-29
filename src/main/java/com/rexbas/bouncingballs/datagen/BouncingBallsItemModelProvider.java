package com.rexbas.bouncingballs.datagen;

import com.rexbas.bouncingballs.BouncingBalls;
import com.rexbas.bouncingballs.api.BouncingBallsAPI;
import com.rexbas.bouncingballs.init.BouncingBallsItems;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BouncingBallsItemModelProvider extends ItemModelProvider {

	private ModelFile bouncingball;
	
	public BouncingBallsItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, BouncingBalls.MODID, existingFileHelper);
		bouncingball = this.getBuilder(BouncingBallsAPI.MODID + ":item/bouncingball");
	}

	@Override
	protected void registerModels() {
		BouncingBallsItems.ITEMS.getEntries().forEach((ball) -> {
			String name = ball.getId().getPath();
			if (name != "dynamite") {
				this.getBuilder(name).parent(bouncingball)
				.texture("north", "item/" + name)
				.texture("east", "item/" + name)
				.texture("south", "item/" + name)
				.texture("west", "item/" + name)
				.texture("up", "item/" + name)
				.texture("down", "item/" + name)
				.texture("handle", "item/" + name + "_handle");
			}
		});
	}
}