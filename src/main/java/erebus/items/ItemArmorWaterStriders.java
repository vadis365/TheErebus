package erebus.items;

import erebus.ModItems;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.items.ItemMaterials.EnumType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorWaterStriders extends ItemArmor {

	public ItemArmorWaterStriders(EntityEquipmentSlot slot) {
		super(ModMaterials.ARMOR_REIN_EXOSKELETON, 2, slot);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, EntityEquipmentSlot slot, String type) {
		if (is.getItem() == ModItems.WATER_STRIDERS)
			return "erebus:textures/models/armor/striders_1.png";
		else
			return null;
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.MATERIALS && material.getItemDamage() == EnumType.HYDROFUGE.ordinal();
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		int x = MathHelper.floor(player.posX);
		int y = MathHelper.floor(player.posY - player.getYOffset());
		int z = MathHelper.floor(player.posZ);

		player.posY += -player.motionY;

		if (world.getBlockState(new BlockPos(x, y - 1, z)).getMaterial() == Material.WATER) {
			if (player.motionY < 0.0D && player.getEntityBoundingBox().minY < y) {
				player.motionY = 0.0D;
				player.jumpMovementFactor *= 0.4F;
				player.fallDistance = 0.0F;
				player.onGround = true;
			}
		}
	}
}