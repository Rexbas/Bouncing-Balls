package com.rexbas.bouncingballs;

import com.rexbas.bouncingballs.init.BouncingBallsItems;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BouncingBalls.MODID)
public class BouncingBalls {
	public static final String MODID = "bouncingballs";
	
	public BouncingBalls() {
		BouncingBallsItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BouncingBallsItems.CREATIVE_TABS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}