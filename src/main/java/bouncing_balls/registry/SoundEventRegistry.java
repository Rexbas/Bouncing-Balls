package bouncing_balls.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import bouncing_balls.BouncingBalls;

public class SoundEventRegistry {

	public static SoundEvent registerSoundEvent(String soundName) {
		ResourceLocation soundID = new ResourceLocation(BouncingBalls.MODID, soundName);
		return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
	}
}
