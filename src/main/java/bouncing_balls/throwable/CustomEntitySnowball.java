package bouncing_balls.throwable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class CustomEntitySnowball extends EntityThrowable {

    public CustomEntitySnowball(World world) {
        super(world);
    }

    public CustomEntitySnowball(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    public CustomEntitySnowball(World world, double x, double y, double z) {
        super(world, x, y, z);
    }
    
    protected void onImpact(RayTraceResult rtr) {
        if(rtr.entityHit != null) {
            int i = 0;

            if(rtr.entityHit instanceof EntityBlaze) {
                i = 3;
            }

            rtr.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)i);
        }

        for(int j = 0; j < 8; ++j) {
            this.worldObj.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
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
