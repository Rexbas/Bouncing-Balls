package bouncing_balls.item;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import bouncing_balls.BouncingBalls;
import bouncing_balls.common.capabilities.IJumpCapability;
import bouncing_balls.common.capabilities.JumpProvider;
import bouncing_balls.jump.BouncingBallJump;
import bouncing_balls.jump.JumpHandler;
import bouncing_balls.jump.JumpType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;

public class BouncingBall extends Item {
	
	private BallType ballType;
	protected float movingAmount;
	protected double motionY;
	
	public static BouncingBall buildBall(BallType type) {
		return new BouncingBall(type);
	}
	
	public static BouncingBall buildBall() {
		return buildBall(BallType.NORMAL);
	}
	
	public BouncingBall(BallType type) {
		super(new Item.Properties()
				.stacksTo(1)
				.tab(BouncingBalls.ITEMGROUP)
				.defaultDurability(type.getMaxDamage()));
		
		this.ballType = type;
		this.movingAmount = getBallType().getMovingAmount();
		this.motionY = getBallType().getMotionY();
	}
	
	@Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
    	ItemStack stack = player.getItemInHand(hand);
    	
    	if(hand == InteractionHand.OFF_HAND && player.getMainHandItem() != null && player.getMainHandItem().getItem() instanceof BouncingBall) {
	        return new InteractionResultHolder<ItemStack>(InteractionResult.FAIL, stack);
		}
		
		BouncingBall ball = (BouncingBall) stack.getItem();
		
		LazyOptional<IJumpCapability> cap = player.getCapability(JumpProvider.JUMP_CAPABILITY, player.getDirection());
		AtomicReference<InteractionResult> ar = new AtomicReference<>();
		ar.set(InteractionResult.FAIL);
		
		if (!cap.isPresent()) {
	        return new InteractionResultHolder<ItemStack>(InteractionResult.FAIL, stack);
		}
		
		cap.ifPresent(c -> {
			//Normal Balls
			if(ball.getBallType() != BallType.EGG && ball.getBallType() != BallType.SNOW && ball.getBallType() != BallType.DYNAMITE) {
				if(c.canJump(player) && c.check(player)) {
					BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.NORMAL);
					JumpHandler.jump(jump);
					ar.set(InteractionResult.PASS);
					if(ball.getBallType() == BallType.OBSIDIAN) {
						player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600));
					}
					return;
				}
			}
			
			//Throwable Ammo Balls
			if(ball.getBallType() == BallType.EGG) {
				if(c.canJumpInAir(new ItemStack(Items.EGG), player) && c.check(player)) {
					BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.EGG_JUMP);
					JumpHandler.jump(jump);
					ar.set(InteractionResult.PASS);
			        return;
				}
			}
			if(ball.getBallType() == BallType.SNOW) {
				if(c.canJumpInAir(new ItemStack(Items.SNOWBALL), player) && c.check(player)) {
					BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.SNOWBALL_JUMP);
					JumpHandler.jump(jump);
					ar.set(InteractionResult.PASS);
			        return;
				}
			}
			if(ball.getBallType() == BallType.DYNAMITE) {
				if(c.canJumpInAir(new ItemStack(Items.GUNPOWDER), player) && c.check(player)) {
					BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.DYNAMITE_JUMP);
					JumpHandler.jump(jump);
					ar.set(InteractionResult.PASS);
			        return;
				}
			}
		});
		
		return new InteractionResultHolder<ItemStack>(ar.get(), stack);
    }
		
	@Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == getBallType().getRepairItem();
    }
	
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> list, TooltipFlag flag) {
		if(this.getBallType() == BallType.EGG) {
			list.add(Component.translatable("bouncing_balls.egg.tooltip").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAAAAAA))));
		}
		else if(this.getBallType() == BallType.SNOW) {
			list.add(Component.translatable("bouncing_balls.snow.tooltip").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAAAAAA))));
		}
		else if(this.getBallType() == BallType.DYNAMITE) {
			list.add(Component.translatable("bouncing_balls.dynamite.tooltip").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAAAAAA))));
			list.add(Component.translatable("bouncing_balls.dynamite.warning").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAA0000))));
		}
    }
		
	public BallType getBallType() {
		return this.ballType;
	}
	
	public float getMovingAmount() {
		return this.movingAmount;
	}
	
	public double getMotionY() {
		return this.motionY;
	}
	
	public float getFallJumpHeight() {
		return getBallType().getFallJumpHeight();
	}
}