package erebus.api.animationmagic;

import net.minecraft.item.ItemStack;

public interface IEnergyStorage {
	/**
	 * Max amount of energy of the specified type this item can store
	 *
	 * @param stack
	 * @param type
	 * @return
	 */
	public int getMaxStorage(ItemStack stack, EnergyType type);

	/**
	 * Current amount of energy of the specified type this item currently holds
	 *
	 * @param stack
	 * @param type
	 * @return
	 */
	public int getCurrentStorage(ItemStack stack, EnergyType type);

	/**
	 * Adds a certain amount of energy of the specified type to this item's
	 * internal storage
	 *
	 * @param stack
	 * @param type
	 * @param amount
	 * @return amount of energy that was added
	 */
	public int addEnergy(ItemStack stack, EnergyType type, int amount);

	/**
	 * Removes a certain amount of energy of the specified type of this item's
	 * internal storage
	 *
	 * @param stack
	 * @param type
	 * @param amount
	 * @return
	 */
	public int extractEnergy(ItemStack stack, EnergyType type, int amount);

	/**
	 * Returns true if this item can store the specified type
	 *
	 * @param stack
	 * @param type
	 * @return
	 */
	public boolean canStore(ItemStack stack, EnergyType type);
}