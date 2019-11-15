package bouncing_balls;

import bouncing_balls.item.BouncingBall;
import bouncing_balls.updatechecker.UpdateChecker;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {

	@SideOnly(Side.CLIENT)
	@Override
	public void registerRenders() {	
		for(int i = 0; i <= 29; i++) {
			registerItem(BouncingBall.returnByID(i));
		}		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerUpdateChecker() {
		BouncingBalls.updateChecker = new UpdateChecker();
		Thread updateCheckerThread = new Thread(BouncingBalls.updateChecker, "Bouncing Balls Update Checker");
		updateCheckerThread.start();
	}
	
	public void registerItem(Item i) {
		ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(i.getRegistryName(), "inventory"));
	}
}
