package erebus.items;

import erebus.ModItems;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.items.ItemMaterials.EnumType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemLeggingsSprint extends ItemArmor {

	public static final byte maxTier = 9;

	private int armtick;
	private String texture;

	public ItemLeggingsSprint() {
		super(ModMaterials.ARMOR_REIN_EXOSKELETON, 2, EntityEquipmentSlot.LEGS);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.MATERIALS && material.getItemDamage() == EnumType.BIO_VELOCITY.ordinal();
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean showAdvancedInfo) {
		//list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocalFormatted("tooltip.erebus.sprintleggingstier", 1 + (!stack.hasTagCompound() ? 0 : stack.stackTagCompound.getByte("upgradeTier"))));
	}

	@Override
	public String getArmorTexture(ItemStack is, Entity entity, EntityEquipmentSlot slot, String type) {
		if (is.getItem() == ModItems.SPRINT_LEGGINGS) {
			if (armtick >= 0 && armtick <= 20)
				texture = "erebus:textures/models/armor/centipede_0.png";
			if (armtick > 20 && armtick <= 40)
				texture = "erebus:textures/models/armor/centipede_1.png";
			if (armtick > 40 && armtick <= 60)
				texture = "erebus:textures/models/armor/centipede_2.png";
		}
		return texture;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack is) {
		if (player.isSprinting() && player.onGround) {
			byte tier = (byte) (3 + (is.getTagCompound() == null ? 0 : is.getTagCompound().getByte("upgradeTier")));
			player.motionX *= 1D + tier * .0425D;
			player.motionZ *= 1D + tier * .0425D;
		}
		armtick++;
		if (armtick > 60 || player.isSprinting())
			armtick = 0;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item id, CreativeTabs tab, NonNullList list) {
		list.add(new ItemStack(id, 1, 0));

		ItemStack is = new ItemStack(id, 1, 0);
		if (!is.hasTagCompound())
			is.setTagCompound(new NBTTagCompound());
		is.getTagCompound().setByte("upgradeTier", (byte) 9);
		list.add(is);
	}
}