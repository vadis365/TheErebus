package erebus.items;

import java.util.List;

import javax.annotation.Nullable;

import erebus.ModItems;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
		return material.getItem() == ModItems.MATERIALS && material.getItemDamage() == EnumErebusMaterialsType.BIO_VELOCITY.ordinal();
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
		list.add(TextFormatting.GRAY + new TextComponentTranslation("tooltip.erebus.sprintleggingstier").getFormattedText() + " " + 1 + (!stack.hasTagCompound() ? 0 : stack.getTagCompound().getByte("upgradeTier")));
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
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (tab == ModTabs.GEAR) {
			list.add(new ItemStack(this, 1, 0));

			ItemStack is = new ItemStack(this, 1, 0);
			if (!is.hasTagCompound())
				is.setTagCompound(new NBTTagCompound());
			is.getTagCompound().setByte("upgradeTier", (byte) 9);
			list.add(is);
		}
	}
}