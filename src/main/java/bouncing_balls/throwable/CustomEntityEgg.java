package bouncing_balls.throwable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

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
	protected void onImpact(MovingObjectPosition mop) {
        if(mop.entityHit != null) {
            mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
        }
        
        double d0 = 0.08D;

        for(int k = 0; k < 8; ++k) {
            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
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
}
