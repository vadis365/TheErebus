package erebus.item;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.entity.EntityUmberGolemDungeonTypes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemDungeonIdols extends Item {

	public ItemDungeonIdols() {
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(ModTabs.items);
		setUnlocalizedName("erebus.idols");
	}

	public static ItemStack createStack(IDOL idol) {
		return createStack(idol, 1);
	}

	public static ItemStack createStack(IDOL idol, int size) {
		return new ItemStack(ModItems.idols, size, idol.ordinal());
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < IDOL.values().length; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + stack.getItemDamage();
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (is.getItemDamage() >= 4) {
			if (!world.isRemote) {
				int x = pos.getX(), y = pos.getY(), z = pos.getZ();
				byte spawn = (byte) (is.getItemDamage() - 4);
				EntityUmberGolemDungeonTypes entityUmberGolem = new EntityUmberGolemDungeonTypes(world);
				entityUmberGolem.setType(spawn);
				entityUmberGolem.setHealth(entityUmberGolem.getMaxHealth()); // hack because of stupid attributes setting
				entityUmberGolem.setPosition(x + 0.5D, y + 1, z + 0.5D);
				world.spawnEntityInWorld(entityUmberGolem);
			}
			if (!player.capabilities.isCreativeMode)
				--is.stackSize;
			return true;
		}
		return false;
	}

	public enum IDOL {
		Mud,
		Iron,
		Gold,
		Jade,
		MudUmbergolem,
		IronUmbergolem,
		GoldUmbergolem,
		JadeUmbergolem
	}
}