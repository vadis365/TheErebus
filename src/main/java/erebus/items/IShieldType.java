package erebus.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public interface IShieldType {

	public ItemStack getShieldItem();

	public ItemStack getRepairItem();

	public int getDurability();

	public void addInformation(ItemStack stack, World world, List<String> information, ITooltipFlag flag);

	public void onUpdate(ItemErebusShield shield, ItemStack stack, World world, Entity entity, int slot, boolean selected);

	public SoundEvent getHitSound(ItemErebusShield shield, ItemStack stack);

	public SoundEvent getBreakSound(ItemErebusShield shield, ItemStack stack);

}
