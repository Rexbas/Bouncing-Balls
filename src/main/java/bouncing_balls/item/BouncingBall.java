package bouncing_balls.item;

import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.Language;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import bouncing_balls.BouncingBalls;
import bouncing_balls.throwable.CustomEntityEgg;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BouncingBall extends Item {
	
	private EnumType materialType;
	private int ID;
	protected float movingAmount;
	protected double motionY;
	
	public BouncingBall(EnumType type, int id) {
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
		
		//Normal Balls
		BouncingBall ball = (BouncingBall) stack.getItem();
		if(ball.getID() != 16) {
			if(player.onGround && !player.isInWater()) {
				player.addVelocity(motionX, motionY, motionZ);
				if(player instanceof EntityPlayerMP && !player.worldObj.isRemote) {
					((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(player));
				}
			    stack.damageItem(1, player);
			    Random rand = new Random();
			    float pitch1 = (float) (rand.nextFloat() * (1.1 - 0.9) + 0.9);
				world.playSoundAtEntity(player, BouncingBalls.MODID + ":bouncingball.bounce", 1, pitch1);
			}
		}
		
		//Throwable Ammo Balls
		if(ball.getID() == 16) {
			int jumps = player.getEntityData().getInteger("bouncing_balls_jumps");
			if(!player.isInWater() && player.inventory.hasItem(Items.egg) && jumps < 2) {
				player.addVelocity(motionX, motionY, motionZ);
				if(player instanceof EntityPlayerMP && !player.worldObj.isRemote) {
					((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(player));
				}
				player.inventory.consumeInventoryItem(Items.egg);
				player.getEntityData().setInteger("bouncing_balls_jumps", jumps + 1);
				player.inventoryContainer.detectAndSendChanges();
	            world.spawnEntityInWorld(new CustomEntityEgg(world, player));
				world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			}
		}
		return stack;
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		Language lang = Minecraft.getMinecraft().getLanguageManager().getCurrentLanguage();
		if(this.getMaterialType() == EnumType.EGG) {
			tooltip.add(StatCollector.translateToLocal("bouncing_balls.EggBouncingBall.tooltip"));
		}
    }
		
	public EnumType getMaterialType() {
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
		switch(getMaterialType()) {
		case EGG:
			this.movingAmount = 0.5F;
			this.motionY = 0.6;
			break;
		case CLAY:
			this.setMaxDamage(125);
			this.movingAmount = 0.6F;
			this.motionY = 0.75;
			break;
		case REDSTONE:
			this.setMaxDamage(150);
			this.movingAmount = 0.6F;
			this.motionY = 0.75;
			break;
		case GLOWSTONE:
			this.setMaxDamage(150);
			this.movingAmount = 0.6F;
			this.motionY = 0.75;
			break;
		case GOLD:
			this.setMaxDamage(250);
			this.movingAmount = 0.75F;
			this.motionY = 0.8;
			break;
		case IRON:
			this.setMaxDamage(300);
			this.movingAmount = 0.9F;
			this.motionY = 0.9;
			break;
		case DIAMOND:
			this.setMaxDamage(400);
			this.movingAmount = 1.0F;
			this.motionY = 1.0;
			break;
		case EMERALD:
			this.setMaxDamage(500);
			this.movingAmount = 1.25F;
			this.motionY = 1.5;
			break;
		case NORMAL:
			this.setMaxDamage(100);
			this.movingAmount = 0.5F;
			this.motionY = 0.6;
			break;
		default:
			this.setMaxDamage(100);
			this.movingAmount = 0.5F;
			this.motionY = 0.6;
		}
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
			return BouncingBalls.clayBouncingBall;
		case 18:
			return BouncingBalls.redstoneBouncingBall;
		case 19:
			return BouncingBalls.glowstoneBouncingBall;
		case 20:
			return BouncingBalls.goldenBouncingBall;
		case 21:
			return BouncingBalls.ironBouncingBall;
		case 22:
			return BouncingBalls.diamondBouncingBall;
		case 23:
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