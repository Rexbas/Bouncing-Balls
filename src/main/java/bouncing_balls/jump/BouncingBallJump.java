package bouncing_balls.jump;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class BouncingBallJump {

	private Player player;
	private ItemStack stack;
	private JumpType type;
	
	public BouncingBallJump(Player player, ItemStack stack, JumpType type) {
		this.player = player;
		this.stack = stack;
		this.type = type;
	}
	
	public Player getPlayer() {
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
		return "BouncingBallJump[" + getPlayer().getName() + ", " + getItemStack().getHoverName() + ", "+ getJumpType().toString() + "]";
	}
}
