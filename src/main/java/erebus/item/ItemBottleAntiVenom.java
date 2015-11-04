package erebus.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemBottleAntiVenom extends ItemBucketMilk {

	public ItemBottleAntiVenom() {
		setMaxStackSize(1);
		setCreativeTab(ModTabs.specials);
		setContainerItem(Items.glass_bottle);
	}

	@Override
	@SuppressWarnings("unchecked")
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		List<Potion> toRemove = new ArrayList<Potion>();
		for (PotionEffect effect : (Collection<PotionEffect>) player.getActivePotionEffects()) {
			Potion potion = Potion.potionTypes[effect.getPotionID()];
			if (potion.isBadEffect() && effect.getAmplifier() <= 0)
				toRemove.add(potion);
		}
		for (Potion potion : toRemove)
			player.removePotionEffect(potion.getId());

		if (!player.capabilities.isCreativeMode) {
			stack.stackSize--;
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