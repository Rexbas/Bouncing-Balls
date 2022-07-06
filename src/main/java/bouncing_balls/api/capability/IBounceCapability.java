package bouncing_balls.api.capability;

public interface IBounceCapability {
	public void addBounce();
	public void resetConsecutiveBounces();
	public int getConsecutiveBounces();
}