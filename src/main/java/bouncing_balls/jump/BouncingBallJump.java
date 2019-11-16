package bouncing_balls.jump;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class BouncingBallJump {

	private EntityPlayer player;
	private ItemStack stack;
	private JumpType type;
	
	public BouncingBallJump(EntityPlayer player, ItemStack stack, JumpType type) {
		this.player = player;
		this.stack = stack;
		this.type = type;
	}
	
	public EntityPlayer getPlayer() {
		return this.player;
	}
	
	public ItemStack getItemStack() {
		return this.stack;
	}
	
	public JumpType getJumpType() {
		return this.type;
	}
	
	@Override
	public String toString() {
		return "BouncingBallJump[" + getPlayer().getName() + ", " + getItemStack().getDisplayName() + ", "+ getJumpType().toString() + "]";
	}
}
