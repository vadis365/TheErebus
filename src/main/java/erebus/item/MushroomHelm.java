package erebus.item;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModMaterials;
import erebus.client.model.armor.ModelMushroomHelm;

public class MushroomHelm extends ItemArmor {

	public MushroomHelm(int armorType) {
		super(ModMaterials.armorREINEXOSPECIAL, 2, armorType);
		setMaxDamage(40);
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == Item.getItemFromBlock(ModBlocks.mushroomCap0)
				|| material.getItem() == Item.getItemFromBlock(ModBlocks.mushroomCap1)
				|| material.getItem() == Item.getItemFromBlock(ModBlocks.mushroomCap2)
				|| material.getItem() == Item.getItemFromBlock(ModBlocks.mushroomCap3)
				|| material.getItem() == Item.getItemFromBlock(ModBlocks.mushroomCap4)
				|| material.getItem() == Item.getItemFromBlock(Blocks.red_mushroom_block)
				|| material.getItem() == Item.getItemFromBlock(Blocks.brown_mushroom_block);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type) {
		return "erebus:textures/models/armor/mushroomHelm.png";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase player, ItemStack is, int slot) {
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
		if(player.getFoodStats().needFood() && stack.getItemDamage() != 40) {
			player.getFoodStats().addStats(1, 0.2F);
			stack.damageItem(1, player);
		}
	}

}

