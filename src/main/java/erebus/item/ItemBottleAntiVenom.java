package erebus.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;

public class ItemBottleAntiVenom extends ItemBucketMilk {

	public ItemBottleAntiVenom() {
		setMaxStackSize(1);
		setCreativeTab(ModTabs.specials);
		setContainerItem(Items.glass_bottle);
		setUnlocalizedName("erebus.bottleAntiVenom");
	}

	@Override
	@SuppressWarnings("unchecked")
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		if (!player.capabilities.isCreativeMode) {
			stack.stackSize--;
			if(!player.getEntityData().hasKey("antivenomDuration") || player.getEntityData().getInteger("antivenomDuration") < 180) {
				int currentDuration = player.getEntityData().getInteger("antivenomDuration");
				player.getEntityData().setInteger("antivenomDuration", currentDuration <= 120 ? currentDuration + 60 : 180);
			}
			if (stack.stackSize <= 0)
				return new ItemStack(Items.glass_bottle);
			else
				player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
		}
		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return Items.potionitem.getIconFromDamage(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass) {
		return Items.potionitem.getIconFromDamageForRenderPass(meta, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass) {
		return pass > 0 ? 0xFFFFFF : 0x5D995D;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
	}
}