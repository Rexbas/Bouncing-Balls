package bouncing_balls.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class JumpCapabilityStorage implements Capability.IStorage<IJumpCapability> {

	@Override
	public void readNBT(Capability<IJumpCapability> cap, IJumpCapability instance, Direction side, INBT nbt) {
		
	}

	@Override
	public INBT writeNBT(Capability<IJumpCapability> cap, IJumpCapability instance, Direction side) {
		return null;
	}

}
