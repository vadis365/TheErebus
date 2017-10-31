package erebus.core.capabilities.player;

import erebus.core.capabilities.base.ISerializableCapability;

public interface IPlayerDeathLocationCapability extends ISerializableCapability {

	/**
	 * Sets Death Dimension
	 * @param dimension
	 */
	public void setGraveDimension(int dimension);

	/**
	 * Sets Death Dimension's String Name
	 * @param dimension
	 */
	public void setGraveDimensionName(String dimensionName);

	/**
	 * Sets Death X Location
	 * @param locationX
	 */
	public void setGraveLocationX(int locationX);

	/**
	 * Sets Death Z Location
	 * @param locationZ
	 */
	public void setGraveLocationZ(int locationZ);

	/**
	 * Returns Death Dimension
	 * @return
	 */
	public int getGraveDimension();

	/**
	 * Returns Death Dimension's String Name
	 * @return
	 */
	public String getGraveDimensionName();

	/**
	 * Returns Death X Location
	 * @return
	 */
	public int getGraveLocationX();

	/**
	 * Returns Death Z Location
	 * @return
	 */
	public int getGraveLocationZ();
}
