package bouncing_balls.network;

import bouncing_balls.BouncingBalls;
import bouncing_balls.network.packets.DecreaseItemStackPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class BouncingBallsPacketHandler {

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(BouncingBalls.MODID, "main"),
			() -> PROTOCOL_VERSION, 
			PROTOCOL_VERSION::equals, 
			PROTOCOL_VERSION::equals
		);
	
	public static void register() {
		INSTANCE.registerMessage(0, DecreaseItemStackPacket.class, DecreaseItemStackPacket::encode, DecreaseItemStackPacket::decode, DecreaseItemStackPacket::handle);
	}
}