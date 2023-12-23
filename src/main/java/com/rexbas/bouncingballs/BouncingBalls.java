package com.rexbas.bouncingballs;

import com.rexbas.bouncingballs.init.BouncingBallsItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(BouncingBalls.MODID)
public class BouncingBalls {
	public static final String MODID = "bouncingballs";
	
	public BouncingBalls(IEventBus modEventBus) {
		BouncingBallsItems.ITEMS.register(modEventBus);
		BouncingBallsItems.CREATIVE_TABS.register(modEventBus);
	}
}