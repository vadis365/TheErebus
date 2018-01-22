package erebus.items;

import java.util.List;

import javax.annotation.Nullable;

import erebus.ModBlocks;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.client.model.armor.ModelMushroomHelm;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHelmMushroom extends ItemArmor {

	public ItemHelmMushroom(EntityEquipmentSlot slot) {
		super(ModMaterials.ARMOR_REIN_EXOSKELETON, 2, slot);
		setMaxDamage(40);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == Item.getItemFromBlock(ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK) || material.getItem() == Item.getItemFromBlock(ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK) || material.getItem() == Item.getItemFromBlock(ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK) || material.getItem() == Item.getItemFromBlock(ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK) || material.getItem() == Item.getItemFromBlock(ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK) || material.getItem() == Item.getItemFromBlock(Blocks.RED_MUSHROOM_BLOCK) || material.getItem() == Item.getItemFromBlock(Blocks.BROWN_MUSHROOM_BLOCK);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
		list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.mushhelm").getFormattedText());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, EntityEquipmentSlot slot, String type) {
		return "erebus:textures/models/armor/mushroom_helm.png";
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase player, ItemStack is, EntityEquipmentSlot armorSlot, ModelBiped modelBiped) {
		ModelMushroomHelm model = new ModelMushroomHelm();
		model.bipedHead.showModel = false;
		model.bipedHeadwear.showModel = false;
		model.bipedBody.showModel = false;
		model.bipedRightArm.showModel = false;
		model.bipedLeftArm.showModel = false;
		model.bipedRightLeg.showModel = false;
		model.bipedLeftLeg.showModel = false;

		return model;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if (player.getFoodStats().needFood() && stack.getItemDamage() != 40) {
			player.getFoodStats().addStats(1, 0.2F);
			stack.damageItem(1, player);
		}
	}

}
