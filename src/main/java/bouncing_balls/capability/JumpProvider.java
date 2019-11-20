package bouncing_balls.capability;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class JumpProvider implements ICapabilityProvider {
	@CapabilityInject(IJumpCapability.class)
	public static final Capability<IJumpCapability> JUMP_CAPABILITY = null;
	
	private LazyOptional<IJumpCapability> instance = LazyOptional.of(JUMP_CAPABILITY::getDefaultInstance);
			
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, EnumFacing side) {
		return cap == JUMP_CAPABILITY ? instance.cast() : LazyOptional.empty();
	}
}