package bouncing_balls.updatechecker;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import bouncing_balls.BouncingBalls;

public class UpdateChecker implements Runnable {
	
	private static boolean isLatestVersion = false;
	private static String latestVersion = null;
	private static String currentVersion = BouncingBalls.MODVERSION;
	private static String minecraftVersion = "1.7.10";

	@Override
	public void run() {
		//Get Newest Version
		try {
			String[] mcVersionArray = minecraftVersion.split(Pattern.quote("."));
			
			URL url = new URL("https://raw.githubusercontent.com/Rexbas/Bouncing-Balls/master/" +
			mcVersionArray[0] + "." + mcVersionArray[1] + "%20Version.txt");
			
			Scanner s = new Scanner(url.openStream());
			latestVersion = s.next();
			s.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		//Print to Console
		if(latestVersion == null) {
			System.out.println("Could Not Check For Updates!");
			System.out.println("Your Mod Version = " + currentVersion);
		}
		else {
			System.out.println("Latest Mod Version = " + latestVersion);
			System.out.println("Your Mod Version = " + currentVersion);
			isLatestVersion = BouncingBalls.MODVERSION.equals(latestVersion);
		}
	}
	
	//Print InGame
	public void updateStatus(EntityPlayer player) {
		char c = 167;
		String p = Character.toString((char)c); //p = §
		if(latestVersion == null) {
			//versionWarningChatComponent = new ChatComponentText("Could not check for update!");
			//Not important for user
		}
		else {
			ChatComponentText versionWarningChatComponent = null;
			ClickEvent versionCheckChatClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, "http://www.planetminecraft.com/mod/bouncing-balls/");
			ChatStyle clickableChatStyle = new ChatStyle().setChatClickEvent(versionCheckChatClickEvent);
			versionWarningChatComponent = new ChatComponentText(p + "c[" + p + "bBouncing Balls" + p + "c]" + p + "aNew Version Available, Click To Download!");
			versionWarningChatComponent.setChatStyle(clickableChatStyle);
			player.addChatMessage(versionWarningChatComponent);
		}
		BouncingBalls.haveWarnedVersionOutOfDate = true;
	}
	
	public boolean isLatestVersion() {
		return isLatestVersion;
	}
}
