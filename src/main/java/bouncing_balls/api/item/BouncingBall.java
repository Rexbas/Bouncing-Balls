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
	
	protected Item repairItem;
	
	public BouncingBall(Properties properties, int durability, Item repairItem) {
		super(properties.stacksTo(1).defaultDurability(durability));
		this.repairItem = repairItem;
	}
	
	public BouncingBall(Properties properties) {
		this(properties, 0, Items.AIR);
	}

	@Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
    	ItemStack stack = player.getItemInHand(hand);

    	// TODO pick the main hand first or consuming ball in off hand if already in air (check canBounce)
    	// Always prioritize the main hand. If the main hand contains a ball, don't bounce with the off hand ball
    	if (hand == Hand.OFF_HAND && player.getMainHandItem().getItem() instanceof IBouncingBall) {
    		return new ActionResult<ItemStack>(ActionResultType.FAIL, stack);
    	}
    	
    	if (canBounce(player)) {
    		bounce(player, 0.9f);
    		damageBall(player, stack);
			playBounceSound(world, player);
    		return new ActionResult<ItemStack>(ActionResultType.PASS, stack);
    	}
		return new ActionResult<ItemStack>(ActionResultType.FAIL, stack);
	}
	
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
		return this.repairItem != Items.AIR && repair.getItem() == this.repairItem;
	}
	
	@Override
	public boolean canBounce(Entity entity) {
		IBounceCapability cap = entity.getCapability(BounceCapabilityProvider.BOUNCE_CAPABILITY).orElse(new BounceCapability());
		return cap.getConsecutiveBounces() < 1 && entity.isOnGround() && !entity.isInWater() && !entity.isInLava();
	}
	
	@Override
	public void bounce(Entity entity, float motionY) {
		float movingAmount = 0.5F;
		float yaw = entity.yRot;
		float pitch = entity.xRot;
		double motionX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * movingAmount);
		double motionZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * movingAmount);
		
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
		if (fallDistance > 5) {
			bounce(player, 5f);
			damageBall(player, stack);
			playBounceSound(player.level, player);
			return 0.1f;
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
}