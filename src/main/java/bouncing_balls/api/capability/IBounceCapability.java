package bouncing_balls.api.capability;

public interface IBounceCapability {
	public void addBounce();
	public void resetConsecutiveBounces();
	public int getConsecutiveBounces();
	public void setStartTickOnGround(boolean onGround);
	public boolean getStartTickOnGround();
}