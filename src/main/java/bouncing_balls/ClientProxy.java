package bouncing_balls;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import bouncing_balls.item.BouncingBall;
import bouncing_balls.throwable.CustomEntityEgg;
import bouncing_balls.updatechecker.UpdateChecker;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {

	@SideOnly(Side.CLIENT)
	@Override
	public void registerRenders() {	
		RenderingRegistry.registerEntityRenderingHandler(CustomEntityEgg.class, new RenderSnowball(Items.egg));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerUpdateChecker() {
		BouncingBalls.updateChecker = new UpdateChecker();
		Thread updateCheckerThread = new Thread(BouncingBalls.updateChecker, "Bouncing Balls Update Checker");
		updateCheckerThread.start();
	}
}
