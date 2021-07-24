package bouncing_balls.init;

import bouncing_balls.BouncingBalls;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BouncingBalls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BouncingBallsSounds {
	
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BouncingBalls.MODID);
	
	public static final RegistryObject<SoundEvent> BOUNCE = SOUNDS.register("bounce",
			() -> new SoundEvent(new ResourceLocation(BouncingBalls.MODID, "bounce")));
}