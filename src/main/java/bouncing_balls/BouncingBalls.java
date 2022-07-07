package bouncing_balls;

import bouncing_balls.api.capability.BounceCapability;
import bouncing_balls.api.capability.IBounceCapability;
import bouncing_balls.init.BouncingBallsItems;
import bouncing_balls.init.BouncingBallsSounds;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BouncingBalls.MODID)
public class BouncingBalls {
	public static final String MODID = "bouncing_balls";
	
	public static final ItemGroup ITEMGROUP = new ItemGroup(MODID) {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(BouncingBallsItems.RED.get());
		}
	};
	
	public BouncingBalls() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		BouncingBallsItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BouncingBallsSounds.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
    public void setup(final FMLCommonSetupEvent event) {    	
    	CapabilityManager.INSTANCE.register(IBounceCapability.class, new IStorage<IBounceCapability>() {

			@Override
			public INBT writeNBT(Capability<IBounceCapability> capability, IBounceCapability instance, Direction side) {
				return null;
			}

			@Override
			public void readNBT(Capability<IBounceCapability> capability, IBounceCapability instance, Direction side, INBT nbt) {}
			
		}, BounceCapability::new);
	}
}