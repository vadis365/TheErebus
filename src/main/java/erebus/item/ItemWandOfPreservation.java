package erebus.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.entity.EntityPreservedBlock;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWandOfPreservation extends Item {

	public ItemWandOfPreservation() {
		setMaxDamage(256);
		setMaxStackSize(1);
		setCreativeTab(ModTabs.specials);
		setUnlocalizedName("erebus.wand_of_preservation");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (player.capabilities.isCreativeMode || consumeBullet(player)) {
			stack.damageItem(1, player);
			world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote)
				world.spawnEntityInWorld(new EntityPreservedBlock(world, player));
		}
		return stack;
	}

	private boolean consumeBullet(EntityPlayer player) {
		for (int i = 0; i < player.inventory.mainInventory.length; i++) {
			ItemStack stack = player.inventory.mainInventory[i];
			if (stack != null && stack.getItem() == ModItems.materials && stack.getItemDamage() == ItemMaterials.DATA.amberStar.ordinal()) {
				if (--stack.stackSize <= 0)
					player.inventory.mainInventory[i] = null;
				player.inventory.markDirty();
				return true;
			}
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
	}
}