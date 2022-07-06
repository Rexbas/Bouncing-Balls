package bouncing_balls.api.item;

import bouncing_balls.api.capability.BounceCapability;
import bouncing_balls.api.capability.BounceCapabilityProvider;
import bouncing_balls.api.capability.IBounceCapability;
import bouncing_balls.init.BouncingBallsSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BouncingBall extends Item implements IBouncingBall {
	
	protected BouncingBall.Properties properties;

	public BouncingBall(Item.Properties itemProperties, BouncingBall.Properties ballProperties) {
		super(itemProperties.stacksTo(1).defaultDurability(ballProperties.durability));
		this.properties = ballProperties;
	}

	@Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
    	ItemStack stack = player.getItemInHand(hand);

    	// TODO ALWAYS PREFER OFF HAND AND ONLY ALLOW 1 BALL TO BE ACTIVE AND NO SWITCHING
    	// TODO pick the main hand first or consuming ball in off hand if already in air (check canBounce)
    	// Always prioritize the main hand. If the main hand contains a ball, don't bounce with the off hand ball
    	if (hand == Hand.OFF_HAND && player.getMainHandItem().getItem() instanceof IBouncingBall) {
    		return new ActionResult<ItemStack>(ActionResultType.FAIL, stack);
    	}
    	
    	if (canBounce(player)) {
    		bounce(player, properties.upwardMotion);
    		damageBall(player, stack);
			playBounceSound(world, player);
    		return new ActionResult<ItemStack>(ActionResultType.PASS, stack);
    	}
		return new ActionResult<ItemStack>(ActionResultType.FAIL, stack);
	}
	
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
		return this.properties.repairItem != Items.AIR && repair.getItem() == this.properties.repairItem;
	}
	
	@Override
	public boolean canBounce(Entity entity) {
		IBounceCapability cap = entity.getCapability(BounceCapabilityProvider.BOUNCE_CAPABILITY).orElse(new BounceCapability());
		return cap.getConsecutiveBounces() < 1 && entity.isOnGround() && !entity.isInWater() && !entity.isInLava();
	}
	
	@Override
	public void bounce(Entity entity, float motionY) {
		float yaw = entity.yRot;
		float pitch = entity.xRot;
		double motionX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * properties.forwardMotion);
		double motionZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * properties.forwardMotion);
		
		entity.push(motionX, motionY, motionZ);
		
		entity.getCapability(BounceCapabilityProvider.BOUNCE_CAPABILITY).ifPresent(cap -> {
			cap.addBounce();
		});
	}
	
	@Override
	public void damageBall(PlayerEntity player, ItemStack stack) {
		stack.hurtAndBreak(1, player, (p) -> {});
	}
	
	@Override
	public float onFall(PlayerEntity player, ItemStack stack, float fallDistance) {
		if (fallDistance > properties.rebounceHeight) {
			bounce(player, properties.upwardMotion);
			damageBall(player, stack);
			playBounceSound(player.level, player);
			return properties.damageMultiplier;
		}
		return 0f;
	}
	
	public void playBounceSound(World world, Entity player) {
		float pitch = world.random.nextFloat() * (1.1f - 0.9f) + 0.9f;
		player.playSound(getBounceSound(), 1, pitch);
	}
	
	public SoundEvent getBounceSound() {
		return BouncingBallsSounds.BOUNCE.get();
	}
	
	public static class Properties {
		
		protected int durability;
		protected Item repairItem;
		protected float forwardMotion;
		protected float upwardMotion;
		protected float rebounceHeight;
		protected float damageMultiplier;
		
		public Properties(int durability, Item repairItem, float forwardMotion, float upwardMotion, float rebounceHeight, float damageMultiplier) {
			this.durability = durability;
			this.repairItem = repairItem;
			this.forwardMotion = forwardMotion;
			this.upwardMotion = upwardMotion;
			this.rebounceHeight = rebounceHeight;
			this.damageMultiplier = damageMultiplier;
		}
		
		public Properties(float forwardMotion, float upwardMotion, float rebounceHeight, float damageMultiplier) {
			this(0, Items.AIR, forwardMotion, upwardMotion, rebounceHeight, damageMultiplier);
		}
		
		public Properties() {
			this(100, Items.SLIME_BALL, 0.5f, 0.65f, 10f, 0.5f);
		}
	}
}