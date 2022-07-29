package com.rexbas.bouncingballs.init;

import com.rexbas.bouncingballs.BouncingBalls;
import com.rexbas.bouncingballs.api.item.BouncingBall;
import com.rexbas.bouncingballs.item.DynamiteBouncingBall;
import com.rexbas.bouncingballs.item.EggBouncingBall;
import com.rexbas.bouncingballs.item.EnderBouncingBall;
import com.rexbas.bouncingballs.item.FireResistantBouncingBall;
import com.rexbas.bouncingballs.item.PrismarineBouncingBall;
import com.rexbas.bouncingballs.item.SlimeBouncingBall;
import com.rexbas.bouncingballs.item.SnowBouncingBall;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = BouncingBalls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BouncingBallsItems {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BouncingBalls.MODID);
	
	public static final RegistryObject<Item> WHITE = ITEMS.register("white", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties().recipeItem(Items.WHITE_DYE).addFluid(FluidTags.WATER)));
	public static final RegistryObject<Item> ORANGE = ITEMS.register("orange", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties().recipeItem(Items.ORANGE_DYE).addFluid(FluidTags.WATER)));
	public static final RegistryObject<Item> MAGENTA = ITEMS.register("magenta", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties().recipeItem(Items.MAGENTA_DYE).addFluid(FluidTags.WATER)));
	public static final RegistryObject<Item> LIGHTBLUE = ITEMS.register("lightblue", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties().recipeItem(Items.LIGHT_BLUE_DYE).addFluid(FluidTags.WATER)));
	public static final RegistryObject<Item> YELLOW = ITEMS.register("yellow", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties().recipeItem(Items.YELLOW_DYE).addFluid(FluidTags.WATER)));
	public static final RegistryObject<Item> LIME = ITEMS.register("lime", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties().recipeItem(Items.LIME_DYE).addFluid(FluidTags.WATER)));
	public static final RegistryObject<Item> PINK = ITEMS.register("pink", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties().recipeItem(Items.PINK_DYE).addFluid(FluidTags.WATER)));
	public static final RegistryObject<Item> GRAY = ITEMS.register("gray", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties().recipeItem(Items.GRAY_DYE).addFluid(FluidTags.WATER)));
	public static final RegistryObject<Item> LIGHTGRAY = ITEMS.register("lightgray", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties().recipeItem(Items.LIGHT_GRAY_DYE).addFluid(FluidTags.WATER)));
	public static final RegistryObject<Item> CYAN = ITEMS.register("cyan", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties().recipeItem(Items.CYAN_DYE).addFluid(FluidTags.WATER)));
	public static final RegistryObject<Item> PURPLE = ITEMS.register("purple", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties().recipeItem(Items.PURPLE_DYE).addFluid(FluidTags.WATER)));
	public static final RegistryObject<Item> BLUE = ITEMS.register("blue", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties().recipeItem(Items.BLUE_DYE).addFluid(FluidTags.WATER)));
	public static final RegistryObject<Item> BROWN = ITEMS.register("brown", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties().recipeItem(Items.BROWN_DYE).addFluid(FluidTags.WATER)));
	public static final RegistryObject<Item> GREEN = ITEMS.register("green", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties().recipeItem(Items.GREEN_DYE).addFluid(FluidTags.WATER)));
	public static final RegistryObject<Item> RED = ITEMS.register("red", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties().recipeItem(Items.RED_DYE).addFluid(FluidTags.WATER)));
	public static final RegistryObject<Item> BLACK = ITEMS.register("black", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties().recipeItem(Items.BLACK_DYE).addFluid(FluidTags.WATER)));

	public static final RegistryObject<Item> EGG = ITEMS.register("egg", () -> new EggBouncingBall());
	public static final RegistryObject<Item> SNOW = ITEMS.register("snow", () -> new SnowBouncingBall());
	public static final RegistryObject<Item> DYNAMITE = ITEMS.register("dynamite", () -> new DynamiteBouncingBall());
	public static final RegistryObject<Item> SLIME = ITEMS.register("slime", () -> new SlimeBouncingBall());
	public static final RegistryObject<Item> CLAY = ITEMS.register("clay", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties(150, Items.CLAY_BALL, 0.6f, 0.75f, 10f, 0.4f).recipeItem(Items.CLAY_BALL).addFluid(FluidTags.WATER)));
	public static final RegistryObject<Item> REDSTONE = ITEMS.register("redstone", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties(150, Items.REDSTONE, 0.6f, 0.75f, 10f, 0.4f).recipeItem(Items.REDSTONE).addFluid(FluidTags.WATER)));
	public static final RegistryObject<Item> GLOWSTONE = ITEMS.register("glowstone", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties(150, Items.GLOWSTONE_DUST, 0.6f, 0.75f, 10f, 0.4f).recipeItem(Items.GLOWSTONE_DUST).addFluid(FluidTags.WATER)));
	public static final RegistryObject<Item> COPPER = ITEMS.register("copper", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties(275, Items.COPPER_INGOT, 0.7f, 0.75f, 10f, 0.3f).recipeItem(Items.COPPER_INGOT)));
	public static final RegistryObject<Item> GOLD = ITEMS.register("gold", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties(250, Items.GOLD_INGOT, 0.75f, 0.8f, 10f, 0.3f).recipeItem(Items.GOLD_INGOT)));
	public static final RegistryObject<Item> IRON = ITEMS.register("iron", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties(300, Items.IRON_INGOT, 0.9f, 0.9f, 10f, 0.25f).recipeItem(Items.IRON_INGOT)));
	public static final RegistryObject<Item> DIAMOND = ITEMS.register("diamond", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties(400, Items.DIAMOND, 1.0f, 1.0f, 10f, 0.2f).recipeItem(Items.DIAMOND)));
	public static final RegistryObject<Item> PRISMARINE = ITEMS.register("prismarine", () -> new PrismarineBouncingBall());
	public static final RegistryObject<Item> ENDER = ITEMS.register("ender", () -> new EnderBouncingBall());
	public static final RegistryObject<Item> OBSIDIAN = ITEMS.register("obsidian", () -> new FireResistantBouncingBall(new BouncingBall.Properties(500, Item.BY_BLOCK.get(Blocks.OBSIDIAN), 1f, 0.75f, 10f, 0.4f).recipeItem(Items.OBSIDIAN).addFluid(FluidTags.LAVA)));
	public static final RegistryObject<Item> QUARTZ = ITEMS.register("quartz", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties(550, Items.QUARTZ, 1.0f, 0.8f, 10f, 0.2f).recipeItem(Items.QUARTZ)));
	public static final RegistryObject<Item> EMERALD = ITEMS.register("emerald", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties(600, Items.EMERALD, 1.25f, 1.5f, 16f, 0.1f).recipeItem(Items.EMERALD)));
	public static final RegistryObject<Item> NETHERITE = ITEMS.register("netherite", () -> new FireResistantBouncingBall(new BouncingBall.Properties(1500, Items.NETHERITE_INGOT, 1.75f, 1.5f, 18f, 0.05f).recipeItem(Items.NETHERITE_INGOT).addFluid(FluidTags.WATER).addFluid(FluidTags.LAVA)));
	public static final RegistryObject<Item> NETHERSTAR = ITEMS.register("netherstar", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.TAB), new BouncingBall.Properties(2500, Items.NETHER_STAR, 2f, 1.75f, 20f, 0f).recipeItem(Items.NETHER_STAR).addFluid(FluidTags.WATER)));
}