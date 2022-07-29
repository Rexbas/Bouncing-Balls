<<<<<<<< HEAD:src/main/java/bouncing_balls/datagen/DataGenerators.java
package bouncing_balls.datagen;

import bouncing_balls.BouncingBalls;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BouncingBalls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();

        gen.addProvider(event.includeClient(), new BouncingBallsItemModelProvider(gen, fileHelper));
	}
========
package com.rexbas.bouncingballs.datagen;

import com.rexbas.bouncingballs.BouncingBalls;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = BouncingBalls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();

        gen.addProvider(new BouncingBallsItemModelProvider(gen, fileHelper));
        gen.addProvider(new BouncingBallsRecipeProvider(gen));
	}
>>>>>>>> origin/1.18:src/main/java/com/rexbas/bouncingballs/datagen/DataGenerators.java
}