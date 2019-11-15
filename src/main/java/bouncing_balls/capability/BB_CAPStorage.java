package bouncing_balls.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class BB_CAPStorage implements IStorage<IBB_CAP> {

	@Override
	public NBTBase writeNBT(Capability<IBB_CAP> capability, IBB_CAP instance, EnumFacing side) {
		return null;
	}

	@Override
	public void readNBT(Capability<IBB_CAP> capability, IBB_CAP instance, EnumFacing side, NBTBase nbt) {

	}
}
