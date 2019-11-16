package bouncing_balls.item;

import java.util.List;

import bouncing_balls.BouncingBalls;
import bouncing_balls.capability.IBB_CAP;
import bouncing_balls.jump.BouncingBallJump;
import bouncing_balls.jump.JumpHandler;
import bouncing_balls.jump.JumpType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BouncingBall extends Item {
	
	private BallType materialType;
	private int ID;
	protected float movingAmount;
	protected double motionY;
	
	public BouncingBall(BallType type, int id, String name) {
		this.ID = id;
		this.materialType = type;
		this.maxStackSize = 1;
		this.setCreativeTab(BouncingBalls.tabBouncingBalls);
		this.setProperties();
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {    	
		if(hand == EnumHand.OFF_HAND && player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof BouncingBall) {
	        return new ActionResult(EnumActionResult.FAIL, stack);
		}
		
		BouncingBall ball = (BouncingBall) stack.getItem();
		
		//Normal Balls
		IBB_CAP capability = player.getCapability(BouncingBalls.BB_CAP, player.getHorizontalFacing());
		int jumps = capability.jumpsInAir();
		if(ball.getID() != 16 && ball.getID() != 17 && ball.getID() != 18) {
			if(capability.canJump() && capability.check()) {
				BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.NORMAL);
				JumpHandler.jump(jump);
				if(ball.getID() == 26) {
					player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 600, 0, false, false));
				}
		        return new ActionResult(EnumActionResult.SUCCESS, stack);
			}
		}
		
		//Throwable Ammo Balls
		if(ball.getID() == 16) {
			if(capability.canJumpInAir(new ItemStack(Items.EGG)) && capability.check()) {
				BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.EGG_JUMP);
				JumpHandler.jump(jump);
		        return new ActionResult(EnumActionResult.SUCCESS, stack);
			}
		}
		if(ball.getID() == 17) {
			if(capability.canJumpInAir(new ItemStack(Items.SNOWBALL)) && capability.check()) {
				BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.SNOWBALL_JUMP);
				JumpHandler.jump(jump);
		        return new ActionResult(EnumActionResult.SUCCESS, stack);
			}
		}
		if(ball.getID() == 18) {
			if(capability.canJumpInAir(new ItemStack(Items.GUNPOWDER)) && capability.check()) {
				BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.DYNAMITE_JUMP);
				JumpHandler.jump(jump);
		        return new ActionResult(EnumActionResult.SUCCESS, stack);
			}
		}
        return new ActionResult(EnumActionResult.FAIL, stack);
    }
		
	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		if(repair.getItem() == getMaterialType().getRepairItem()) {
			return true;
		}
		else {
			return false;
		}
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		if(this.getMaterialType() == BallType.EGG) {
			tooltip.add(I18n.translateToLocal("bouncing_balls.eggbouncingball.tooltip"));
		}
		else if(this.getMaterialType() == BallType.SNOW) {
			tooltip.add(I18n.translateToLocal("bouncing_balls.snowbouncingball.tooltip"));
		}
		else if(this.getMaterialType() == BallType.DYNAMITE) {
			tooltip.add(I18n.translateToLocal("bouncing_balls.dynamitebouncingball.tooltip"));
			tooltip.add(I18n.translateToLocal("bouncing_balls.dynamitebouncingball.warning"));
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
	
	public void setProperties() {
		if(getMaterialType().hasMaxDamage()) {
			this.setMaxDamage(getMaterialType().getMaxDamage());
		}
		this.movingAmount = getMaterialType().getMovingAmount();
		this.motionY = getMaterialType().getMotionY();
	}
	
	public float getFallJumpHeight() {
		return getMaterialType().getFallJumpHeight();
	}
	
	public static Item returnByID(int id) {
		switch(id) {
		//Normal Bouncing Balls
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
		//Abnormal Bouncing Balls
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