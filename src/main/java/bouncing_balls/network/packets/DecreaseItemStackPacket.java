package bouncing_balls.network.packets;

import java.util.function.Supplier;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class DecreaseItemStackPacket {
	private int slot;
	
	public DecreaseItemStackPacket(int slot) {
		this.slot = slot;
	}
	
	public int getSlot() {
		return this.slot;
	}
	
	public static void encode(DecreaseItemStackPacket msg, PacketBuffer buf) {
		buf.writeInt(msg.slot);
	}
	
	
	public static DecreaseItemStackPacket decode(PacketBuffer buf) {
		return new DecreaseItemStackPacket(buf.readInt());
	}
	
	public static void handle(DecreaseItemStackPacket msg, Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			EntityPlayerMP player = context.get().getSender();
			player.inventory.decrStackSize(msg.getSlot(), 1);
		});
		context.get().setPacketHandled(true);
	}
}
