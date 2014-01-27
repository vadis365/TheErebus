package erebus.item;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ErebusMod;
import erebus.ModItems;
import erebus.client.model.armor.ModelArmorGlider;

public class ItemArmorGlider extends ItemArmor {

	public ItemArmorGlider(int id, int armorType) {
		super(id, ErebusMod.armorREINEXOSPECIAL, 2, armorType);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, int layer) {
		return "erebus:textures/models/armor/ModelArmorGlider.png";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase player, ItemStack is, int slot) {
		ModelArmorGlider model = new ModelArmorGlider();
		model.bipedHead.showModel = false;
		model.bipedHeadwear.showModel = false;
		model.bipedBody.showModel = false;
		model.bipedRightArm.showModel = false;
		model.bipedLeftArm.showModel = false;
		model.bipedRightLeg.showModel = false;
		model.bipedLeftLeg.showModel = false;
		if (is.hasTagCompound())
			model.isGliding = is.getTagCompound().getBoolean("isGliding");

		return model;
	}

	@Override
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {
		if (!itemStack.hasTagCompound()) {
			itemStack.stackTagCompound = new NBTTagCompound();
			return;
		} else if (itemStack.getTagCompound().getBoolean("isGliding")) {
			player.fallDistance = 0.0F;
			if (!player.onGround) {
				player.motionX *= 1.0D;
				player.motionZ *= 1.0D;
				player.motionY *= 0.5D;
			}
		}
	}

	@Override
	public void onCreated(ItemStack is, World world, EntityPlayer player) {
		if (!is.hasTagCompound())
			is.setTagCompound(new NBTTagCompound());
		is.stackTagCompound.setBoolean("isGliding", false);
	}

	@ForgeSubscribe
	@SideOnly(Side.CLIENT)
	public void onPlayerRenderPre(RenderPlayerEvent.Pre e) {
		GL11.glPushMatrix();
		EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
		ItemStack chestPlate = player.inventory.armorInventory[2];
		if (chestPlate != null && chestPlate.getItem().itemID == ModItems.armorGlider.itemID) {
			if (!chestPlate.hasTagCompound()) {
				chestPlate.stackTagCompound = new NBTTagCompound();
				return;
			}
			if (chestPlate.getTagCompound().getBoolean("isGliding") && !player.onGround) {
				// Unimplemented for the time being.
				// Method is fixed but rotations need working out!
				// GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onPlayerRenderPost(RenderPlayerEvent.Post e) {
		GL11.glPopMatrix();
	}
}