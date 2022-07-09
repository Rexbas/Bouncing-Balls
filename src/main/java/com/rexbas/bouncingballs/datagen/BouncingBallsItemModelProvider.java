package com.rexbas.bouncingballs.datagen;

import com.rexbas.bouncingballs.BouncingBalls;
import com.rexbas.bouncingballs.BouncingBallsAPI;
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
			this.getBuilder(name).parent(bouncingball).texture("layer0", "item/" + name);
		});
	}
}