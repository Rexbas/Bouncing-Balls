package bouncing_balls.item;

import bouncing_balls.BouncingBalls;
import bouncing_balls.api.item.BouncingBall;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ObsidianBouncingBall extends BouncingBall {

	public ObsidianBouncingBall() {
		super(new Item.Properties().tab(BouncingBalls.ITEMGROUP), new BouncingBall.Properties(500, Item.BY_BLOCK.get(Blocks.OBSIDIAN), 1f, 0.75f, 10f, 0.4f));
	}
	
	@Override
	public void bounce(Entity entity, float motionY) {
		super.bounce(entity, motionY);
		if (entity instanceof LivingEntity) {
			((LivingEntity) entity).addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 600));
		}
	}
}