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
import erebus.ModItems;
import erebus.ModMaterials;
import erebus.client.model.armor.ModelArmorGlider;
import erebus.item.ItemErebusMaterial.DATA;

public class ItemArmorGlider extends ItemArmor {

	public ItemArmorGlider(int id, int armorType) {
		super(id, ModMaterials.armorREINEXOSPECIAL, 2, armorType);
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.itemID == ModItems.erebusMaterials.itemID && material.getItemDamage() == DATA.gliderWing.ordinal();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, int layer) {
		return "erebus:textures/models/armor/glider.png";
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
		if (is.hasTagCompound())
			model.isPowered = is.getTagCompound().getBoolean("isPowered");

		return model;
	}

	@Override
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {
		if (!itemStack.hasTagCompound()) {
			itemStack.stackTagCompound = new NBTTagCompound();
			return;
			}
		
		if (itemStack.getTagCompound().getBoolean("isGliding")) {
			player.fallDistance = 0.0F;
			if (!player.onGround) {
				player.motionX *= 1.05D;
				player.motionZ *= 1.05D;
				player.motionY *= 0.5D;
			}
		}
		
		if (itemStack.getTagCompound().getBoolean("isPowered")) {
			player.fallDistance = 0.0F;
			if (!player.onGround) {
				player.motionX *= 1.05D;
				player.motionZ *= 1.05D;
				player.motionY += 0.1D;
			}
		}
	}

	@Override
	public void onCreated(ItemStack is, World world, EntityPlayer player) {
		if (!is.hasTagCompound())
			is.setTagCompound(new NBTTagCompound());
		is.stackTagCompound.setBoolean("isGliding", false);
		is.stackTagCompound.setBoolean("isPowered", false);
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
			if (chestPlate.getTagCompound().getBoolean("isGliding") && !player.onGround || chestPlate.getTagCompound().getBoolean("isPowered") && !player.onGround) {
				// Unimplemented for the time being.
				// Method is fixed but rotations need working out!
			     int yaw = (int)player.rotationYaw;
		           if (yaw<0)
		              yaw+=360;
		           
		           yaw+=22;
		           yaw%=360;

		           int facing = yaw/45;   //  360degrees divided by 45 == 8 zones
		        
		           float x = 0;
		           float y = 0;
		           switch (facing) {
		           case 0:
		        	   x=1;
		        	   y=0;
		        	   break;
		           case 1:
		        	   x=1;
		        	   y=1;
		        	   break;
		           case 2:
		        	   x=0;
		        	   y=1;
		        	   break;
		           case 3:
		        	   x=-1;
		        	   y=1;
		        	   break;
		           case 4:
		        	   x=-1;
		        	   y=0;
		        	   break;
		           case 5:
		        	   x=-1;
		        	   y=-1;
		        	   break;
		           case 6:
		        	   x=0;
		        	   y=-1;
		        	   break;
		           case 7:
		        	   x=1;
		        	   y=-1;
		        	   break;	 
		         }
		         GL11.glRotatef(60.0F, x, 0.0F, y);
		         player.limbSwingAmount=0.001F;
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onPlayerRenderPost(RenderPlayerEvent.Post e) {
		GL11.glPopMatrix();
	}
}