package bouncing_balls;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import bouncing_balls.item.BouncingBall;
import bouncing_balls.throwable.CustomEntityEgg;
import bouncing_balls.throwable.CustomEntitySnowball;
import bouncing_balls.updatechecker.UpdateChecker;

public class ClientProxy extends CommonProxy {

	@SideOnly(Side.CLIENT)
	@Override
	public void registerRenders() {	
		for(int i = 0; i <= 28; i++) {
			registerItem(BouncingBall.returnByID(i));
		}
		RenderingRegistry.registerEntityRenderingHandler(CustomEntityEgg.class, new RenderSnowball(Minecraft.getMinecraft().getRenderManager(), Items.EGG, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(CustomEntitySnowball.class, new RenderSnowball(Minecraft.getMinecraft().getRenderManager(), Items.SNOWBALL, Minecraft.getMinecraft().getRenderItem()));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerUpdateChecker() {
		BouncingBalls.updateChecker = new UpdateChecker();
		Thread updateCheckerThread = new Thread(BouncingBalls.updateChecker, "Bouncing Balls Update Checker");
		updateCheckerThread.start();
	}
	
	public void registerItem(Item i) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(i, 0, new ModelResourceLocation(BouncingBalls.MODID + ":" + i.getUnlocalizedName().substring(5), "inventory"));
	}
}
