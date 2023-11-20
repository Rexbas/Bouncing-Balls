package com.rexbas.bouncingballs.init;

import com.rexbas.bouncingballs.BouncingBalls;
import com.rexbas.bouncingballs.api.item.BouncingBall;
import com.rexbas.bouncingballs.item.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BouncingBallsItems {

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BouncingBalls.MODID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BouncingBalls.MODID);

	public static final DeferredItem<BouncingBall> WHITE = ITEMS.register("white", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties().recipeItem(Items.WHITE_DYE).addFluid(FluidTags.WATER)));
	public static final DeferredItem<BouncingBall> ORANGE = ITEMS.register("orange", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties().recipeItem(Items.ORANGE_DYE).addFluid(FluidTags.WATER)));
	public static final DeferredItem<BouncingBall> MAGENTA = ITEMS.register("magenta", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties().recipeItem(Items.MAGENTA_DYE).addFluid(FluidTags.WATER)));
	public static final DeferredItem<BouncingBall> LIGHTBLUE = ITEMS.register("lightblue", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties().recipeItem(Items.LIGHT_BLUE_DYE).addFluid(FluidTags.WATER)));
	public static final DeferredItem<BouncingBall> YELLOW = ITEMS.register("yellow", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties().recipeItem(Items.YELLOW_DYE).addFluid(FluidTags.WATER)));
	public static final DeferredItem<BouncingBall> LIME = ITEMS.register("lime", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties().recipeItem(Items.LIME_DYE).addFluid(FluidTags.WATER)));
	public static final DeferredItem<BouncingBall> PINK = ITEMS.register("pink", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties().recipeItem(Items.PINK_DYE).addFluid(FluidTags.WATER)));
	public static final DeferredItem<BouncingBall> GRAY = ITEMS.register("gray", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties().recipeItem(Items.GRAY_DYE).addFluid(FluidTags.WATER)));
	public static final DeferredItem<BouncingBall> LIGHTGRAY = ITEMS.register("lightgray", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties().recipeItem(Items.LIGHT_GRAY_DYE).addFluid(FluidTags.WATER)));
	public static final DeferredItem<BouncingBall> CYAN = ITEMS.register("cyan", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties().recipeItem(Items.CYAN_DYE).addFluid(FluidTags.WATER)));
	public static final DeferredItem<BouncingBall> PURPLE = ITEMS.register("purple", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties().recipeItem(Items.PURPLE_DYE).addFluid(FluidTags.WATER)));
	public static final DeferredItem<BouncingBall> BLUE = ITEMS.register("blue", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties().recipeItem(Items.BLUE_DYE).addFluid(FluidTags.WATER)));
	public static final DeferredItem<BouncingBall> BROWN = ITEMS.register("brown", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties().recipeItem(Items.BROWN_DYE).addFluid(FluidTags.WATER)));
	public static final DeferredItem<BouncingBall> GREEN = ITEMS.register("green", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties().recipeItem(Items.GREEN_DYE).addFluid(FluidTags.WATER)));
	public static final DeferredItem<BouncingBall> RED = ITEMS.register("red", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties().recipeItem(Items.RED_DYE).addFluid(FluidTags.WATER)));
	public static final DeferredItem<BouncingBall> BLACK = ITEMS.register("black", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties().recipeItem(Items.BLACK_DYE).addFluid(FluidTags.WATER)));

	public static final DeferredItem<EggBouncingBall> EGG = ITEMS.register("egg", EggBouncingBall::new);
	public static final DeferredItem<SnowBouncingBall> SNOW = ITEMS.register("snow", SnowBouncingBall::new);
	public static final DeferredItem<DynamiteBouncingBall> DYNAMITE = ITEMS.register("dynamite", DynamiteBouncingBall::new);
	public static final DeferredItem<SlimeBouncingBall> SLIME = ITEMS.register("slime", SlimeBouncingBall::new);
	public static final DeferredItem<BouncingBall> CLAY = ITEMS.register("clay", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties(150, Items.CLAY_BALL, 0.6f, 0.75f, 10f, 0.4f).recipeItem(Items.CLAY_BALL).addFluid(FluidTags.WATER)));
	public static final DeferredItem<BouncingBall> REDSTONE = ITEMS.register("redstone", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties(150, Items.REDSTONE, 0.6f, 0.75f, 10f, 0.4f).recipeItem(Items.REDSTONE).addFluid(FluidTags.WATER)));
	public static final DeferredItem<BouncingBall> GLOWSTONE = ITEMS.register("glowstone", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties(150, Items.GLOWSTONE_DUST, 0.6f, 0.75f, 10f, 0.4f).recipeItem(Items.GLOWSTONE_DUST).addFluid(FluidTags.WATER)));
	public static final DeferredItem<BouncingBall> COPPER = ITEMS.register("copper", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties(275, Items.COPPER_INGOT, 0.7f, 0.75f, 10f, 0.3f).recipeItem(Items.COPPER_INGOT)));
	public static final DeferredItem<BouncingBall> GOLD = ITEMS.register("gold", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties(250, Items.GOLD_INGOT, 0.75f, 0.8f, 10f, 0.3f).recipeItem(Items.GOLD_INGOT)));
	public static final DeferredItem<BouncingBall> IRON = ITEMS.register("iron", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties(300, Items.IRON_INGOT, 0.9f, 0.9f, 10f, 0.25f).recipeItem(Items.IRON_INGOT)));
	public static final DeferredItem<BouncingBall> DIAMOND = ITEMS.register("diamond", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties(400, Items.DIAMOND, 1.0f, 1.0f, 10f, 0.2f).recipeItem(Items.DIAMOND)));
	public static final DeferredItem<PrismarineBouncingBall> PRISMARINE = ITEMS.register("prismarine", PrismarineBouncingBall::new);
	public static final DeferredItem<EnderBouncingBall> ENDER = ITEMS.register("ender", EnderBouncingBall::new);
	public static final DeferredItem<FireResistantBouncingBall> OBSIDIAN = ITEMS.register("obsidian", () -> new FireResistantBouncingBall(new BouncingBall.Properties(500, Item.BY_BLOCK.get(Blocks.OBSIDIAN), 1f, 0.75f, 10f, 0.4f).recipeItem(Items.OBSIDIAN).addFluid(FluidTags.LAVA)));
	public static final DeferredItem<BouncingBall> QUARTZ = ITEMS.register("quartz", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties(550, Items.QUARTZ, 1.0f, 0.8f, 10f, 0.2f).recipeItem(Items.QUARTZ)));
	public static final DeferredItem<BouncingBall> EMERALD = ITEMS.register("emerald", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties(600, Items.EMERALD, 1.25f, 1.5f, 16f, 0.1f).recipeItem(Items.EMERALD)));
	public static final DeferredItem<FireResistantBouncingBall> NETHERITE = ITEMS.register("netherite", () -> new FireResistantBouncingBall(new BouncingBall.Properties(1500, Items.NETHERITE_INGOT, 1.75f, 1.5f, 18f, 0.05f).recipeItem(Items.NETHERITE_INGOT).addFluid(FluidTags.WATER).addFluid(FluidTags.LAVA)));
	public static final DeferredItem<BouncingBall> NETHERSTAR = ITEMS.register("netherstar", () -> new BouncingBall(new Item.Properties(), new BouncingBall.Properties(2500, Items.NETHER_STAR, 2f, 1.75f, 20f, 0f).recipeItem(Items.NETHER_STAR).addFluid(FluidTags.WATER)));


	public static Supplier<CreativeModeTab> TAB = CREATIVE_TABS.register("bouncingballs_tab",
			() -> CreativeModeTab.builder()
					.title(Component.translatable("item_group." + BouncingBalls.MODID))
					.icon(() -> new ItemStack(RED.get()))
					.displayItems((params, output) -> {
						output.accept(WHITE.get());
						output.accept(ORANGE.get());
						output.accept(MAGENTA.get());
						output.accept(LIGHTBLUE.get());
						output.accept(YELLOW.get());
						output.accept(LIME.get());
						output.accept(PINK.get());
						output.accept(GRAY.get());
						output.accept(LIGHTGRAY.get());
						output.accept(CYAN.get());
						output.accept(PURPLE.get());
						output.accept(BLUE.get());
						output.accept(BROWN.get());
						output.accept(GREEN.get());
						output.accept(RED.get());
						output.accept(BLACK.get());

						output.accept(EGG.get());
						output.accept(SNOW.get());
						output.accept(DYNAMITE.get());
						output.accept(SLIME.get());
						output.accept(CLAY.get());
						output.accept(REDSTONE.get());
						output.accept(GLOWSTONE.get());
						output.accept(COPPER.get());
						output.accept(GOLD.get());
						output.accept(IRON.get());
						output.accept(DIAMOND.get());
						output.accept(PRISMARINE.get());
						output.accept(ENDER.get());
						output.accept(OBSIDIAN.get());
						output.accept(QUARTZ.get());
						output.accept(EMERALD.get());
						output.accept(NETHERITE.get());
						output.accept(NETHERSTAR.get());
					})
					.build()
	);
}