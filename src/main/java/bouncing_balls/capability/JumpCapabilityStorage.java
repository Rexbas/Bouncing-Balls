package bouncing_balls.capability;

import net.minecraft.nbt.INBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class JumpCapabilityStorage implements Capability.IStorage<IJumpCapability> {

	@Override
	public void readNBT(Capability<IJumpCapability> cap, IJumpCapability instance, EnumFacing side, INBTBase nbt) {
		
	}

	@Override
	public INBTBase writeNBT(Capability<IJumpCapability> cap, IJumpCapability instance, EnumFacing side) {
		return null;
	}
}
