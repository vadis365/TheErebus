package erebus.entity;

public interface IErebusAnimationExtras {

	/**
	 * Added to entity to facilitate some custom animations
	 *
	 * @return true to allow some custom animation. false to deny it.
	 */
	public abstract boolean hasAnimation();

	/**
	 * Added to entity to facilitate some custom animations
	 *
	 * @return true to start animation ticking. false to deny it.
	 */
	public abstract boolean setShouldTickAnimation(boolean performAnimationIn);
	
	/**
	 * Added to entity to facilitate some custom animations
	 *
	 * @return getter for animation on/off status.
	 */
	public abstract boolean getShouldTickAnimation();
	
	
	/**
	 * Added to entity to set custom animation tick
	 *
	 * @return sets the tick counter to an int amount.
	 */
	public abstract void setAnimationTick(int animationTickIn);

	/**
	 * Added to entity to get custom animation tick
	 *
	 * @return gets the tick counter as an int amount.
	 */
	public abstract int getAnimationTick();

	/**
	 * Added to entity to set previous custom animation tick
	 *
	 * @return sets the previous tick counter to an int amount.
	 */

	public abstract void setAnimationTickPrev(int animationTickPrevIn);

	/**
	 * Added to entity to get previous custom animation tick
	 *
	 * @return gets the previous tick counter as an int amount.
	 */
	public abstract int getAnimationTickPrev();
	
	/**
	 * Added to entity to reset custom animation tick & previous custom animation tick
	 *
	 * @return helper method so all animation and prev ticks can be reset.
	 */
	public abstract void resetAllAnimationTicks();

}
