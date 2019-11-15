package bouncing_balls.throwable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CustomEntityEgg extends EntityThrowable {

    public CustomEntityEgg(World world) {
        super(world);
    }

    public CustomEntityEgg(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    public CustomEntityEgg(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

	@Override
	protected void onImpact(RayTraceResult rtr) {
        if(rtr.entityHit != null) {
        	rtr.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
        }
        
        double d0 = 0.08D;

        for(int k = 0; k < 8; ++k) {
            this.worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, new int[] {Item.getIdFromItem(Items.EGG)});
        }

        if(!this.worldObj.isRemote) {
            this.setDead();
        }
	}
	
	@Override
    public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy) {
		this.motionX = 0;
		this.motionY = -1;
		this.motionZ = 0;
	}
	
	@Override
    protected float getGravityVelocity() {
        return 0.75F;
    }
}
