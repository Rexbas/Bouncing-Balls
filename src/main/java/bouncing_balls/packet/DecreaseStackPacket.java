package bouncing_balls.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class DecreaseStackPacket implements IMessage {
	
	private int slot;
	
	public DecreaseStackPacket() {}
	
	public DecreaseStackPacket(int slot) {
		this.slot = slot;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.slot = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(slot);
	}
	
	public int getSlot() {
		return this.slot;
	}
}
