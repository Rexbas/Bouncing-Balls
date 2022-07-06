package bouncing_balls.item;

import bouncing_balls.BouncingBalls;
import bouncing_balls.api.item.BouncingBall;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ObsidianBouncingBall extends BouncingBall {

	public ObsidianBouncingBall() {
		super(new Item.Properties().tab(BouncingBalls.ITEMGROUP), 150, Items.SLIME_BALL);
	}
	
	@Override
	public void bounce(Entity entity, float motionY) {
		super.bounce(entity, motionY);
		if (entity instanceof LivingEntity) {
			((LivingEntity) entity).addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 600));
		}
	}
}