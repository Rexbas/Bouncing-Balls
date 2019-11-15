package bouncing_balls.item;

import java.util.Date;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import bouncing_balls.BouncingBalls;
import bouncing_balls.capability.IBB_CAP;
import bouncing_balls.jump.BouncingBallJump;
import bouncing_balls.jump.JumpHandler;
import bouncing_balls.jump.JumpType;
import bouncing_balls.throwable.CustomEntityEgg;
import bouncing_balls.throwable.CustomEntitySnowball;

public class BouncingBall extends Item {
	
	private BallType materialType;
	private int ID;
	protected float movingAmount;
	protected double motionY;
	
	public BouncingBall(BallType type, int id) {
		this.ID = id;
		this.materialType = type;
		this.maxStackSize = 1;
		this.setCreativeTab(BouncingBalls.tabBouncingBalls);
		this.setProperties();
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		float yaw = player.rotationYaw;
		float pitch = player.rotationPitch;
		double motionX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * movingAmount);
		double motionZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * movingAmount);	

		if(!world.isRemote) {
			//Normal Balls
			IBB_CAP capability = player.getCapability(BouncingBalls.BB_CAP, player.func_181012_aH());
			int jumps = capability.jumpsInAir();
			BouncingBall ball = (BouncingBall) stack.getItem();
			if(ball.getID() != 16 && ball.getID() != 17) {
				if(capability.canJump() && capability.check()) {
					/*player.addVelocity(motionX, motionY, motionZ);
					if(player instanceof EntityPlayerMP && !player.worldObj.isRemote) {
						((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(player));
					}
				    stack.damageItem(1, player);
				    Random rand = new Random();
				    float pitch1 = (float) (rand.nextFloat() * (1.1 - 0.9) + 0.9);
					world.playSoundAtEntity(player, BouncingBalls.MODID + ":bouncingball.bounce", 1, pitch1);*/
					BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.NORMAL);
					JumpHandler.jump(jump);
				}
			}
			
			//Throwable Ammo Balls
			if(ball.getID() == 16) {
				if(capability.canJumpInAir(Items.egg) && capability.check()) {
					/*player.addVelocity(motionX, motionY, motionZ);
					if(player instanceof EntityPlayerMP && !player.worldObj.isRemote) {
						((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(player));
					}
					player.inventory.consumeInventoryItem(Items.egg);
					capability.setJumpsInAir(jumps + 1);
					player.inventoryContainer.detectAndSendChanges();
		            world.spawnEntityInWorld(new CustomEntityEgg(world, player));
					world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));*/
					BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.EGG_JUMP);
					JumpHandler.jump(jump);
				}
			}
			
			if(ball.getID() == 17) {
				if(capability.canJumpInAir(Items.snowball) && capability.check()) {
					/*player.addVelocity(motionX, motionY, motionZ);
					if(player instanceof EntityPlayerMP && !player.worldObj.isRemote) {
						((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(player));
					}
					player.inventory.consumeInventoryItem(Items.snowball);
					capability.setJumpsInAir(jumps + 1);
					player.inventoryContainer.detectAndSendChanges();
		            world.spawnEntityInWorld(new CustomEntitySnowball(world, player));
					world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));*/
					BouncingBallJump jump = new BouncingBallJump(player, stack, JumpType.SNOWBALL_JUMP);
					JumpHandler.jump(jump);
				}
			}
		}
		return stack;
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
			tooltip.add(StatCollector.translateToLocal("bouncing_balls.EggBouncingBall.tooltip"));
		}
		else if(this.getMaterialType() == BallType.SNOW) {
			tooltip.add(StatCollector.translateToLocal("bouncing_balls.SnowBouncingBall.tooltip"));
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
			return BouncingBalls.clayBouncingBall;
		case 19:
			return BouncingBalls.redstoneBouncingBall;
		case 20:
			return BouncingBalls.glowstoneBouncingBall;
		case 21:
			return BouncingBalls.goldenBouncingBall;
		case 22:
			return BouncingBalls.ironBouncingBall;
		case 23:
			return BouncingBalls.diamondBouncingBall;
		case 24:
			return BouncingBalls.emeraldBouncingBall;
		default:
			return null;
		}
	}
		
	/*public static void addLight(World world, EntityPlayer player, int light) {
		BlockPos pos = new BlockPos(player.posX, player.posY + 3, player.posZ);
		world.setLightFor(EnumSkyBlock.BLOCK, pos, light);
		world.notifyLightSet(pos);
		world.markBlockRangeForRenderUpdate(pos.getX(), pos.getY(), pos.getZ(), 12, 12, 12);
		world.markBlockForUpdate(pos);
	}*/
}