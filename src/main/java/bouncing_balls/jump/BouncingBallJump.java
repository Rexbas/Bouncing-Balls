package bouncing_balls.jump;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class BouncingBallJump {

	private PlayerEntity player;
	private ItemStack stack;
	private JumpType type;
	
	public BouncingBallJump(PlayerEntity player, ItemStack stack, JumpType type) {
		this.player = player;
		this.stack = stack;
		this.type = type;
	}
	
	public PlayerEntity getPlayer() {
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
