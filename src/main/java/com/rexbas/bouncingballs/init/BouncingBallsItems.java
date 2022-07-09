package com.rexbas.bouncingballs.init;

import com.rexbas.bouncingballs.BouncingBalls;
import com.rexbas.bouncingballs.api.item.BouncingBall;
import com.rexbas.bouncingballs.item.DynamiteBouncingBall;
import com.rexbas.bouncingballs.item.EggBouncingBall;
import com.rexbas.bouncingballs.item.ObsidianBouncingBall;
import com.rexbas.bouncingballs.item.SlimeBouncingBall;
import com.rexbas.bouncingballs.item.SnowBouncingBall;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BouncingBalls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BouncingBallsItems {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BouncingBalls.MODID);
	
	public static final RegistryObject<Item> BLACK = ITEMS.register("black", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties()));
	public static final RegistryObject<Item> RED = ITEMS.register("red", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties()));
	public static final RegistryObject<Item> DARKGREEN = ITEMS.register("darkgreen", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties()));
	public static final RegistryObject<Item> BROWN = ITEMS.register("brown", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties()));
	public static final RegistryObject<Item> BLUE = ITEMS.register("blue", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties()));
	public static final RegistryObject<Item> PURPLE = ITEMS.register("purple", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties()));
	public static final RegistryObject<Item> CYAN = ITEMS.register("cyan", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties()));
	public static final RegistryObject<Item> LIGHTGRAY = ITEMS.register("lightgray", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties()));
	public static final RegistryObject<Item> GRAY = ITEMS.register("gray", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties()));
	public static final RegistryObject<Item> PINK = ITEMS.register("pink", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties()));
	public static final RegistryObject<Item> GREEN = ITEMS.register("green", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties()));
	public static final RegistryObject<Item> YELLOW = ITEMS.register("yellow", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties()));
	public static final RegistryObject<Item> LIGHTBLUE = ITEMS.register("lightblue", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties()));
	public static final RegistryObject<Item> MAGENTA = ITEMS.register("magenta", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties()));
	public static final RegistryObject<Item> ORANGE = ITEMS.register("orange", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties()));
	public static final RegistryObject<Item> WHITE = ITEMS.register("white", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties()));

	public static final RegistryObject<Item> EGG = ITEMS.register("egg", () -> new EggBouncingBall());
	public static final RegistryObject<Item> SNOW = ITEMS.register("snow", () -> new SnowBouncingBall());
	public static final RegistryObject<Item> DYNAMITE = ITEMS.register("dynamite", () -> new DynamiteBouncingBall());
	public static final RegistryObject<Item> SLIME = ITEMS.register("slime", () -> new SlimeBouncingBall());
	public static final RegistryObject<Item> CLAY = ITEMS.register("clay", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties(150, Items.CLAY_BALL, 0.6f, 0.75f, 10f, 0.4f)));
	public static final RegistryObject<Item> REDSTONE = ITEMS.register("redstone", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties(150, Items.REDSTONE, 0.6f, 0.75f, 10f, 0.4f)));
	public static final RegistryObject<Item> GLOWSTONE = ITEMS.register("glowstone", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties(150, Items.GLOWSTONE_DUST, 0.6f, 0.75f, 10f, 0.4f)));
	public static final RegistryObject<Item> GOLD = ITEMS.register("gold", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties(250, Items.GOLD_INGOT, 0.75f, 0.8f, 10f, 0.3f)));
	public static final RegistryObject<Item> IRON = ITEMS.register("iron", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties(300, Items.IRON_INGOT, 0.9f, 0.9f, 10f, 0.25f)));
	public static final RegistryObject<Item> DIAMOND = ITEMS.register("diamond", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties(400, Items.DIAMOND, 1.0f, 1.0f, 10f, 0.2f)));
	public static final RegistryObject<Item> OBSIDIAN = ITEMS.register("obsidian", () -> new ObsidianBouncingBall());
	public static final RegistryObject<Item> QUARTZ = ITEMS.register("quartz", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties(550, Items.QUARTZ, 1.0f, 0.8f, 10f, 0.2f)));
	public static final RegistryObject<Item> EMERALD = ITEMS.register("emerald", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties(600, Items.EMERALD, 1.25f, 1.5f, 16f, 0.1f)));
	public static final RegistryObject<Item> NETHERSTAR = ITEMS.register("netherstar", () -> new BouncingBall(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties(2500, Items.NETHER_STAR, 1.75f, 1.5f, 18f, 0f)));
}