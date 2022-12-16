package com.rexbas.bouncingballs;

import com.rexbas.bouncingballs.init.BouncingBallsItems;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BouncingBalls.MODID)
public class BouncingBalls {
	public static final String MODID = "bouncingballs";
	
	public BouncingBalls() {
		BouncingBallsItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}