package erebus.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.client.model.armor.ModelMushroomHelm;

public class ItemHelmMushroom extends ItemArmor {

	public ItemHelmMushroom(EntityEquipmentSlot slot) {
		super(ModMaterials.ARMOR_REIN_EXOSKELETON, 2, slot);
		setMaxDamage(40);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return false;
		//material.getItem() == Item.getItemFromBlock(ModBlocks.bigBulbCappedMushroom) || material.getItem() == Item.getItemFromBlock(ModBlocks.bigGreenMushroom) || material.getItem() == Item.getItemFromBlock(ModBlocks.bigBundleMushroom) || material.getItem() == Item.getItemFromBlock(ModBlocks.bigKaiserfingerMushroom) || material.getItem() == Item.getItemFromBlock(ModBlocks.bigDutchCapMushroom) || material.getItem() == Item.getItemFromBlock(Blocks.red_mushroom_block) || material.getItem() == Item.getItemFromBlock(Blocks.brown_mushroom_block);
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
