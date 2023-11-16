package com.rexbas.bouncingballs.datagen;

import com.rexbas.bouncingballs.BouncingBalls;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod.EventBusSubscriber(modid = BouncingBalls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		PackOutput output = event.getGenerator().getPackOutput();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();

        gen.addProvider(event.includeClient(), new BouncingBallsItemModelProvider(output, fileHelper));
        gen.addProvider(event.includeServer(), new BouncingBallsRecipeProvider(output, event.getLookupProvider()));
	}
}