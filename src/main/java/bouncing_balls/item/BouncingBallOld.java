package bouncing_balls.item;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import bouncing_balls.BouncingBalls;
import bouncing_balls.common.capabilities.IJumpCapability;
import bouncing_balls.common.capabilities.JumpProvider;
import bouncing_balls.jump.BouncingBallJump;
import bouncing_balls.jump.JumpHandler;
import bouncing_balls.jump.JumpType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class BouncingBallOld extends Item {
	
	private BallType ballType;
	protected float movingAmount;
	protected double motionY;
	
	public static BouncingBallOld buildBall(BallType type) {
		return new BouncingBallOld(type);
	}
	
	public static BouncingBallOld buildBall() {
		return buildBall(BallType.NORMAL);
	}
	
	public BouncingBallOld(BallType type) {
		super(new Item.Properties()
				.stacksTo(1)
				.tab(BouncingBalls.ITEMGROUP)
				.defaultDurability(type.getMaxDamage()));
		
		this.ballType = type;
		this.movingAmount = getBallType().getMovingAmount();
		this.motionY = getBallType().getMotionY();
	}
	
	@Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
    	ItemStack stack = player.getItemInHand(hand);
    	
    	if(hand == Hand.OFF_HAND && player.getMainHandItem() != null && player.getMainHandItem().getItem() instanceof BouncingBallOld) {
	        return new ActionResult<ItemStack>(ActionResultType.FAIL, stack);
		}
		
		BouncingBallOld ball = (BouncingBallOld) stack.getItem();
		
		LazyOptional<IJumpCapability> cap = player.getCapability(JumpProvider.JUMP_CAPABILITY, player.getDirection());
		AtomicReference<ActionResultType> ar = new AtomicReference<>();
		ar.set(ActionResultType.FAIL);
		
		if (!cap.isPresent()) {
	        return new ActionResult<ItemStack>(ActionResultType.FAIL, stack);
		}
		
		cap.ifPresent(c -> {
			//Normal Balls
			if(ball.getBallType() != BallType.EGG && ball.getBallType() != BallType.SNOW && ball.getBallType() != BallType.DYNAMITE) {
				if(c.canJump(player)) {
					BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.NORMAL);
					JumpHandler.jump(jump);
					ar.set(ActionResultType.PASS);
					if(ball.getBallType() == BallType.OBSIDIAN) {
						player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 600));
					}
					return;
				}
			}
			
			//Throwable Ammo Balls
			if(ball.getBallType() == BallType.EGG) {
				if(c.canJumpInAir(new ItemStack(Items.EGG), player)) {
					BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.EGG_JUMP);
					JumpHandler.jump(jump);
					ar.set(ActionResultType.PASS);
			        return;
				}
			}
			if(ball.getBallType() == BallType.SNOW) {
				if(c.canJumpInAir(new ItemStack(Items.SNOWBALL), player)) {
					BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.SNOWBALL_JUMP);
					JumpHandler.jump(jump);
					ar.set(ActionResultType.PASS);
			        return;
				}
			}
			if(ball.getBallType() == BallType.DYNAMITE) {
				if(c.canJumpInAir(new ItemStack(Items.GUNPOWDER), player)) {
					BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.DYNAMITE_JUMP);
					JumpHandler.jump(jump);
					ar.set(ActionResultType.PASS);
			        return;
				}
			}
		});
		
		return new ActionResult<ItemStack>(ar.get(), stack);
    }
		
	@Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == getBallType().getRepairItem();
    }
	
	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		if(this.getBallType() == BallType.EGG) {
			list.add(new TranslationTextComponent("bouncing_balls.egg.tooltip").setStyle(Style.EMPTY.withColor(Color.fromRgb(0xAAAAAA))));
		}
		else if(this.getBallType() == BallType.SNOW) {
			list.add(new TranslationTextComponent("bouncing_balls.snow.tooltip").setStyle(Style.EMPTY.withColor(Color.fromRgb(0xAAAAAA))));
		}
		else if(this.getBallType() == BallType.DYNAMITE) {
			list.add(new TranslationTextComponent("bouncing_balls.dynamite.tooltip").setStyle(Style.EMPTY.withColor(Color.fromRgb(0xAAAAAA))));
			list.add(new TranslationTextComponent("bouncing_balls.dynamite.warning").setStyle(Style.EMPTY.withColor(Color.fromRgb(0xAA0000))));
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