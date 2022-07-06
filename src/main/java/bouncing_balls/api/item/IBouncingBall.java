package bouncing_balls.api.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface IBouncingBall {
	// TODO check Entity type
	// TODO check PlayerENtity cast or (something else cast?!)
	public boolean canBounce(Entity entity);
	public void bounce(Entity entity, float motionY);
	public void damageBall(PlayerEntity player, ItemStack stack);
	public float onFall(PlayerEntity player, ItemStack stack, float fallDistance); // TODO Entity?
}