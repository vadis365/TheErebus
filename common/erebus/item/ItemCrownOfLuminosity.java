package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModMaterials;
import erebus.item.ItemErebusMaterial.DATA;

public class ItemCrownOfLuminosity extends ItemArmor {

	public ItemCrownOfLuminosity(int id, int armorType) {
		super(id, ModMaterials.armorEXOSKELETON, 2, armorType);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type) {
		return "erebus:textures/models/armor/lightCrown.png";
	}
	
	@Override
	public void onCreated(ItemStack is, World world, EntityPlayer player) {
		hasTag(is);
		is.stackTagCompound.setBoolean("isWorn", false);
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.itemID == ModItems.erebusMaterials.itemID && material.getItemDamage() == DATA.plateExo.ordinal();
	}

	@Override
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack is) {
		if (hasTag(is) && !is.getTagCompound().getBoolean("isWorn"))
			is.stackTagCompound.setBoolean("isWorn", true);
		if (hasTag(is) && is.getTagCompound().getBoolean("isWorn")) {
			world.setLightValue(EnumSkyBlock.Block, (int)player.posX, (int)player.posY, (int)player.posZ, 9);
		for (int i = -1; i < 2; i++)
			for (int j = -1; j < 2; j++)
				for (int k = -1; k < 2; k++)
					if ((int)player.posX + i != (int)player.prevPosX || (int)player.prevPosY  + j != (int)player.posY || (int)player.prevPosZ  + k != (int)player.posZ || player.isDead)
						world.updateLightByType(EnumSkyBlock.Block, (int)player.posX + i, (int)player.posY + j, (int)player.posZ + k);
		}
	}

	@Override
    public void onUpdate(ItemStack is, World world, Entity entity, int id, boolean map) {
		EntityPlayer player = (EntityPlayer) entity;
    	ItemStack slot = player.inventory.armorInventory[3];
    	if (slot == null && hasTag(is) && is.getTagCompound().getBoolean("isWorn") || slot != null && slot.itemID != itemID && hasTag(is) && is.getTagCompound().getBoolean("isWorn")) {
			world.updateLightByType(EnumSkyBlock.Block, (int)player.prevPosX, (int)player.prevPosY, (int)player.prevPosZ);
			world.updateLightByType(EnumSkyBlock.Block, MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY), MathHelper.floor_double(player.posZ));
    		is.stackTagCompound.setBoolean("isWorn", false);
    	}
    }
	
	private boolean hasTag(ItemStack is) {
		if (!is.hasTagCompound()) {
			is.setTagCompound(new NBTTagCompound());
			return false;
		}
		return true;
	}

}