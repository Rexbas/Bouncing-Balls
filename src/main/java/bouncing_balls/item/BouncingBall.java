package bouncing_balls.item;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import bouncing_balls.BouncingBalls;
import bouncing_balls.capability.IJumpCapability;
import bouncing_balls.capability.JumpProvider;
import bouncing_balls.jump.BouncingBallJump;
import bouncing_balls.jump.JumpHandler;
import bouncing_balls.jump.JumpType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class BouncingBall extends Item {
	
	private BallType materialType;
	private int ID;
	protected float movingAmount;
	protected double motionY;
	
	public BouncingBall(BallType type, int id, String name) {
		super(new Item.Properties()
				.maxStackSize(1)
				.group(BouncingBalls.itemGroup)
				.defaultMaxDamage(type.getMaxDamage()));
		this.ID = id;
		this.materialType = type;
		this.movingAmount = getMaterialType().getMovingAmount();
		this.motionY = getMaterialType().getMotionY();
		this.setRegistryName(new ResourceLocation(BouncingBalls.MODID, name));
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
    	ItemStack stack = player.getHeldItem(hand);
    	
    	if(hand == EnumHand.OFF_HAND && player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof BouncingBall) {
	        return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
		}
		
		BouncingBall ball = (BouncingBall) stack.getItem();
		
		LazyOptional<IJumpCapability> cap = player.getCapability(JumpProvider.JUMP_CAPABILITY, player.getHorizontalFacing());
		AtomicReference<EnumActionResult> ar = new AtomicReference<>();
		
		if (!cap.isPresent()) {
	        return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
		}
		
		cap.ifPresent(c -> {
			//Normal Balls
			if(ball.getID() != 16 && ball.getID() != 17 && ball.getID() != 18) {
				if(c.canJump(player) && c.check(player)) {
					BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.NORMAL);
					JumpHandler.jump(jump);
					if(ball.getID() == 26) {
						player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 600, 0, false, false));
					}
					ar.set(EnumActionResult.SUCCESS);
					return;
				}
			}
			
			//Throwable Ammo Balls
			if(ball.getID() == 16) {
				if(c.canJumpInAir(new ItemStack(Items.EGG), player) && c.check(player)) {
					BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.EGG_JUMP);
					JumpHandler.jump(jump);
			        ar.set(EnumActionResult.SUCCESS);
			        return;
				}
			}
			if(ball.getID() == 17) {
				if(c.canJumpInAir(new ItemStack(Items.SNOWBALL), player) && c.check(player)) {
					BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.SNOWBALL_JUMP);
					JumpHandler.jump(jump);
			        ar.set(EnumActionResult.SUCCESS);
			        return;
				}
			}
			if(ball.getID() == 18) {
				if(c.canJumpInAir(new ItemStack(Items.GUNPOWDER), player) && c.check(player)) {
					BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.DYNAMITE_JUMP);
					JumpHandler.jump(jump);
			        ar.set(EnumActionResult.SUCCESS);
			        return;
				}
			}
		});
		
		return new ActionResult<ItemStack>(ar.get(), stack);
    }
		
	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == getMaterialType().getRepairItem();
    }
	
	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		if(this.getMaterialType() == BallType.EGG) {
			list.add(new TextComponentString("\u00A77").appendSibling(new TextComponentTranslation("bouncing_balls.egg.tooltip")));
		}
		else if(this.getMaterialType() == BallType.SNOW) {
			list.add(new TextComponentString("\u00A77").appendSibling(new TextComponentTranslation("bouncing_balls.snow.tooltip")));
		}
		else if(this.getMaterialType() == BallType.DYNAMITE) {
			list.add(new TextComponentString("\u00A77").appendSibling(new TextComponentTranslation("bouncing_balls.dynamite.tooltip")));
			list.add(new TextComponentString("\u00A74").appendSibling(new TextComponentTranslation("bouncing_balls.dynamite.warning")));
		}
    }
		
	public BallType getMaterialType() {
		return this.materialType;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public float getMovingAmount() {
		return this.movingAmount;
	}
	
	public double getMotionY() {
		return this.motionY;
	}
	
	public float getFallJumpHeight() {
		return getMaterialType().getFallJumpHeight();
	}
	
	public static Item returnByID(int id) {
		switch(id) {
		// Normal Bouncing Balls
		case 0:
			return BouncingBalls.blackBouncingBall;
		case 1:
			return BouncingBalls.redBouncingBall;
		case 2:
			return BouncingBalls.darkGreenBouncingBall;
		case 3:
			return BouncingBalls.brownBouncingBall;
		case 4:
			return BouncingBalls.blueBouncingBall;
		case 5:
			return BouncingBalls.purpleBouncingBall;
		case 6:
			return BouncingBalls.cyanBouncingBall;
		case 7:
			return BouncingBalls.lightGrayBouncingBall;
		case 8:
			return BouncingBalls.grayBouncingBall;
		case 9:
			return BouncingBalls.pinkBouncingBall;
		case 10:
			return BouncingBalls.greenBouncingBall;
		case 11:
			return BouncingBalls.yellowBouncingBall;
		case 12:
			return BouncingBalls.lightBlueBouncingBall;
		case 13:
			return BouncingBalls.magentaBouncingBall;
		case 14:
			return BouncingBalls.orangeBouncingBall;
		case 15:
			return BouncingBalls.whiteBouncingBall;
		// Special Bouncing Balls
		case 16:
			return BouncingBalls.eggBouncingBall;
		case 17:
			return BouncingBalls.snowBouncingBall;
		case 18:
			return BouncingBalls.dynamiteBouncingBall;
		case 19:
			return BouncingBalls.slimeBouncingBall;
		case 20:
			return BouncingBalls.clayBouncingBall;
		case 21:
			return BouncingBalls.redstoneBouncingBall;
		case 22:
			return BouncingBalls.glowstoneBouncingBall;
		case 23:
			return BouncingBalls.goldenBouncingBall;
		case 24:
			return BouncingBalls.ironBouncingBall;
		case 25:
			return BouncingBalls.diamondBouncingBall;
		case 26:
			return BouncingBalls.obsidianBouncingBall;
		case 27:
			return BouncingBalls.emeraldBouncingBall;
		case 28:
			return BouncingBalls.netherStarBouncingBall;
		case 29:
			return BouncingBalls.quartzBouncingBall;
		default:
			return null;
		}
	}
}