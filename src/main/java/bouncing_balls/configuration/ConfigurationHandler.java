package bouncing_balls.configuration;

import java.io.File;

import bouncing_balls.BouncingBalls;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ConfigurationHandler {
	
	public static boolean showUpdateCheck;
	public static final boolean UPDATECHECKER_DEFAULT = true;
	public static final String UPDATECHECKER_NAME = "Enable Bouncing Balls update checker";
	
	public static void syncConfig() {		
		final String CONFIG = BouncingBalls.config.CATEGORY_GENERAL;
		BouncingBalls.config.addCustomCategoryComment(CONFIG, "Bouncing Balls Configuration");
		
		showUpdateCheck = BouncingBalls.config.get(CONFIG, UPDATECHECKER_NAME, UPDATECHECKER_DEFAULT).getBoolean(UPDATECHECKER_DEFAULT);
		if(BouncingBalls.config.hasChanged()){
			BouncingBalls.config.save();
		}
	}
}
