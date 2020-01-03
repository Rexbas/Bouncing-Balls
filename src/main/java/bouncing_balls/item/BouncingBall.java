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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class BouncingBall extends Item {
	
	private BallType ballType;
	protected float movingAmount;
	protected double motionY;
	
	public static BouncingBall buildBall(BallType type, String name) {
		return new BouncingBall(type, name);
	}
	
	public static BouncingBall buildBall(String name) {
		return buildBall(BallType.NORMAL, name);
	}
	
	public BouncingBall(BallType type, String name) {
		super(new Item.Properties()
				.maxStackSize(1)
				.group(BouncingBalls.ITEMGROUP)
				.defaultMaxDamage(type.getMaxDamage()));
		
		this.ballType = type;
		this.movingAmount = getBallType().getMovingAmount();
		this.motionY = getBallType().getMotionY();
		this.setRegistryName(new ResourceLocation(BouncingBalls.MODID, name));
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    	ItemStack stack = player.getHeldItem(hand);
    	
    	if(hand == Hand.OFF_HAND && player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof BouncingBall) {
	        return new ActionResult<ItemStack>(ActionResultType.FAIL, stack);
		}
		
		BouncingBall ball = (BouncingBall) stack.getItem();
		
		LazyOptional<IJumpCapability> cap = player.getCapability(JumpProvider.JUMP_CAPABILITY, player.getHorizontalFacing());
		AtomicReference<ActionResultType> ar = new AtomicReference<>();
		ar.set(ActionResultType.FAIL);
		
		if (!cap.isPresent()) {
	        return new ActionResult<ItemStack>(ActionResultType.FAIL, stack);
		}
		
		cap.ifPresent(c -> {
			//Normal Balls
			if(ball.getBallType() != BallType.EGG && ball.getBallType() != BallType.SNOW && ball.getBallType() != BallType.DYNAMITE) {
				if(c.canJump(player) && c.check(player)) {
					BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.NORMAL);
					JumpHandler.jump(jump);
					ar.set(ActionResultType.SUCCESS);
					if(ball.getBallType() == BallType.OBSIDIAN) {
						player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 600));
					}
					return;
				}
			}
			
			//Throwable Ammo Balls
			if(ball.getBallType() == BallType.EGG) {
				if(c.canJumpInAir(new ItemStack(Items.EGG), player) && c.check(player)) {
					BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.EGG_JUMP);
					JumpHandler.jump(jump);
					ar.set(ActionResultType.SUCCESS);
			        return;
				}
			}
			if(ball.getBallType() == BallType.SNOW) {
				if(c.canJumpInAir(new ItemStack(Items.SNOWBALL), player) && c.check(player)) {
					BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.SNOWBALL_JUMP);
					JumpHandler.jump(jump);
					ar.set(ActionResultType.SUCCESS);
			        return;
				}
			}
			if(ball.getBallType() == BallType.DYNAMITE) {
				if(c.canJumpInAir(new ItemStack(Items.GUNPOWDER), player) && c.check(player)) {
					BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.DYNAMITE_JUMP);
					JumpHandler.jump(jump);
					ar.set(ActionResultType.SUCCESS);
			        return;
				}
			}
		});
		
		return new ActionResult<ItemStack>(ar.get(), stack);
    }
		
	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == getBallType().getRepairItem();
    }
	
	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		if(this.getBallType() == BallType.EGG) {
			list.add(new StringTextComponent("\u00A77").appendSibling(new TranslationTextComponent("bouncing_balls.egg.tooltip")));
		}
		else if(this.getBallType() == BallType.SNOW) {
			list.add(new StringTextComponent("\u00A77").appendSibling(new TranslationTextComponent("bouncing_balls.snow.tooltip")));
		}
		else if(this.getBallType() == BallType.DYNAMITE) {
			list.add(new StringTextComponent("\u00A77").appendSibling(new TranslationTextComponent("bouncing_balls.dynamite.tooltip")));
			list.add(new StringTextComponent("\u00A74").appendSibling(new TranslationTextComponent("bouncing_balls.dynamite.warning")));
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