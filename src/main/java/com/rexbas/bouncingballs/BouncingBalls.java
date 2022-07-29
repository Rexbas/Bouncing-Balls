package com.rexbas.bouncingballs;

import com.rexbas.bouncingballs.init.BouncingBallsItems;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BouncingBalls.MODID)
public class BouncingBalls {
	public static final String MODID = "bouncingballs";
	
	public static final ItemGroup TAB = new ItemGroup(MODID) {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(BouncingBallsItems.RED.get());
		}
	};
	
	public BouncingBalls() {
		BouncingBallsItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}