package com.rexbas.bouncingballs.datagen;

import com.rexbas.bouncingballs.BouncingBalls;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BouncingBalls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		PackOutput output = event.getGenerator().getPackOutput();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();

        gen.addProvider(event.includeClient(), new BouncingBallsItemModelProvider(output, fileHelper));
        gen.addProvider(event.includeServer(), new BouncingBallsRecipeProvider(output));
	}
}