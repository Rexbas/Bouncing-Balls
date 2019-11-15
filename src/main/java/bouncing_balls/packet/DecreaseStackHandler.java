package bouncing_balls.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class DecreaseStackHandler implements IMessageHandler<DecreaseStackPacket, IMessage> {

	@Override
	public IMessage onMessage(DecreaseStackPacket message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().playerEntity;
		player.inventory.decrStackSize(message.getSlot(), 1);
		return null;
	}
}
