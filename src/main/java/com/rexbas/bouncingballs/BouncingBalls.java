package com.rexbas.bouncingballs;

import com.rexbas.bouncingballs.init.BouncingBallsItems;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BouncingBalls.MODID)
public class BouncingBalls {
	public static final String MODID = "bouncingballs";
	
	public static final CreativeModeTab TAB = new CreativeModeTab(MODID) {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(BouncingBallsItems.RED.get());
		}
	};
	
	public BouncingBalls() {
		BouncingBallsItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}