package bouncing_balls.network.packets;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class DecreaseItemStackPacket {
	private int slot;
	
	public DecreaseItemStackPacket(int slot) {
		this.slot = slot;
	}
	
	public int getSlot() {
		return this.slot;
	}
	
	public static void encode(DecreaseItemStackPacket msg, FriendlyByteBuf buf) {
		buf.writeInt(msg.slot);
	}
	
	
	public static DecreaseItemStackPacket decode(FriendlyByteBuf buf) {
		return new DecreaseItemStackPacket(buf.readInt());
	}
	
	public static void handle(DecreaseItemStackPacket msg, Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			ServerPlayer player = context.get().getSender();
			player.getInventory().removeItem(msg.getSlot(), 1);
		});
		context.get().setPacketHandled(true);
	}
}