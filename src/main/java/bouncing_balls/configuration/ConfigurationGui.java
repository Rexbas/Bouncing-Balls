package bouncing_balls.configuration;

import bouncing_balls.BouncingBalls;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ConfigurationGui extends GuiConfig {

	public ConfigurationGui(GuiScreen screen) {
		super(screen, new ConfigElement(
				BouncingBalls.config.getCategory(Configuration.CATEGORY_GENERAL))
				.getChildElements(), BouncingBalls.MODID, false, true, GuiConfig
				.getAbridgedConfigPath(BouncingBalls.config.toString()));
	}
}
