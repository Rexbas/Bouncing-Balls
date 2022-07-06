package bouncing_balls.api.capability;

public class BounceCapability implements IBounceCapability {
	
	private int consecutiveBounces;
	
	public BounceCapability() {
		this.consecutiveBounces = 0;
	}
	
	public void addBounce() {
		this.consecutiveBounces += 1;
	}
	
	public void resetConsecutiveBounces() {
		this.consecutiveBounces = 0;
	}
	
	public int getConsecutiveBounces() {
		return this.consecutiveBounces;
	}
}