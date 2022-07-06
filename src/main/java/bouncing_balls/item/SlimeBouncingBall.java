package bouncing_balls.item;

import bouncing_balls.BouncingBalls;
import bouncing_balls.api.item.BouncingBall;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class SlimeBouncingBall extends BouncingBall {

	public SlimeBouncingBall() {
		super(new Item.Properties().tab(BouncingBalls.ITEMGROUP), 150, Items.SLIME_BALL);
	}
	
	@Override
	public SoundEvent getBounceSound() {
		return SoundEvents.SLIME_BLOCK_PLACE;
	}
}