package erebus.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.item.ItemMaterials.DATA;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemArmorWaterStriders extends ItemArmor {

	public ItemArmorWaterStriders(ArmorMaterial material, int k) {
		super(material, 2, k);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type) {
		if (is.getItem() == ModItems.waterStriders)
			return "erebus:textures/models/armor/striders1.png";
		else
			return null;
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.materials && material.getItemDamage() == DATA.ELASTIC_FIBRE.ordinal();
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		int x = MathHelper.floor_double(player.posX);
		int y = MathHelper.floor_double(player.posY - player.getYOffset());
		int z = MathHelper.floor_double(player.posZ);

		player.posY += -player.motionY;

		if (world.getBlock(x, y - 1, z).getMaterial() == Material.water) {
			if (player.motionY < 0.0D && player.boundingBox.minY < y) {
				player.motionY = 0.0D;
				player.jumpMovementFactor *= 0.4F;
				player.fallDistance = 0.0F;
				player.onGround = true;
			}
		}
	}
}