package erebus.items;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.client.model.armor.ModelArmorGlider;
import erebus.client.model.armor.ModelArmorPowered;
import erebus.items.ItemMaterials.EnumType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class ItemArmorGlider extends ItemArmor {
	public ItemArmorGlider() {
		super(ModMaterials.ARMOR_REIN_EXOSKELETON, 2, EntityEquipmentSlot.CHEST);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
		/*if (canFly()) {
			list.add(I18n.translateToLocal("tooltip.erebus.poweredGlider"));
			list.add(I18n.translateToLocal("tooltip.erebus.gliderPoweredKey") + ": " + Keyboard.getKeyName(KeyBindingHandler.poweredGlide.getKeyCode()));
		}
		list.add(I18n.translateToLocal("tooltip.erebus.gliderGlideKey") + ": " + Keyboard.getKeyName(KeyBindingHandler.glide.getKeyCode()));*/
	}

	@Override
	public boolean hasColor(ItemStack stack) {
		return stack.hasTagCompound() && (stack.getTagCompound().hasKey("display", Constants.NBT.TAG_COMPOUND) && stack.getTagCompound().getCompoundTag("display").hasKey("color", Constants.NBT.TAG_INT));
	}

	@Override
	public int getColor(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();

		if (nbt == null)
			return 0xFFFFFF;
		else {
			NBTTagCompound displayNBT = nbt.getCompoundTag("display");
			return displayNBT == null ? 0xFFFFFF : displayNBT.hasKey("color", 3) ? displayNBT.getInteger("color") : 0xFFFFFF;
		}
	}

	@Override
	public void removeColor(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();

		if (nbt != null) {
			NBTTagCompound displayNBT = nbt.getCompoundTag("display");

			if (displayNBT.hasKey("color"))
				displayNBT.removeTag("color");
		}
	}

	@Override
	public void setColor(ItemStack stack, int color) {
		NBTTagCompound nbt = stack.getTagCompound();

		if (nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}

		NBTTagCompound displayNBT = nbt.getCompoundTag("display");

		if (!nbt.hasKey("display", 10))
			nbt.setTag("display", displayNBT);

		displayNBT.setInteger("color", color);
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.MATERIALS && material.getItemDamage() == EnumType.GLIDER_WING.ordinal();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, EntityEquipmentSlot armorSlot, String type) {
		return "erebus:textures/models/armor/glider.png";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase player, ItemStack stack, EntityEquipmentSlot armorSlot, ModelBiped modelBiped) {
		if (true/*canFly()*/) {
			ModelArmorPowered model = new ModelArmorPowered();
			model.bipedHead.showModel = false;
			model.bipedHeadwear.showModel = false;
			model.bipedBody.showModel = false;
			model.bipedRightArm.showModel = false;
			model.bipedLeftArm.showModel = false;
			model.bipedRightLeg.showModel = false;
			model.bipedLeftLeg.showModel = false;

			if (stack.hasTagCompound()) {
				model.isGliding = stack.getTagCompound().getBoolean("isGliding");
				model.isPowered = stack.getTagCompound().getBoolean("isPowered");
			}

			return model;
		} else {
			ModelArmorGlider model = new ModelArmorGlider();

			model.bipedHead.showModel = false;
			model.bipedHeadwear.showModel = false;
			model.bipedBody.showModel = false;
			model.bipedRightArm.showModel = false;
			model.bipedLeftArm.showModel = false;
			model.bipedRightLeg.showModel = false;
			model.bipedLeftLeg.showModel = false;

			if (stack.hasTagCompound())
				model.isGliding = stack.getTagCompound().getBoolean("isGliding");

			return model;
		}
	}

//	@Override
//	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
//		if (world.isRemote) {
//			if (!stack.hasTagCompound())
//				stack.getTagCompound() = new NBTTagCompound();
//
//			if (stack.getTagCompound().getBoolean("isGliding") && (!KeyBindingHandler.glide.getIsKeyPressed() || entity.onGround)) {
//				stack.getTagCompound().setBoolean("isGliding", false);
//				PacketPipeline.sendToServer(new PacketGlider(false));
//			}
//
//			if (canFly())
//				if (stack.getTagCompound().getBoolean("isPowered") && (!KeyBindingHandler.poweredGlide.getIsKeyPressed() || entity.onGround)) {
//					stack.getTagCompound().setBoolean("isPowered", false);
//					PacketPipeline.sendToServer(new PacketGliderPowered(false));
//				}
//		}
//	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		onUpdate(stack, world, player, 0, false);

		player.fallDistance = 0.0F;

		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			return;
		}
		NBTTagCompound nbt = stack.getTagCompound();

		if (nbt.getBoolean("isGliding"))
			if (!player.onGround) {
				player.motionX *= 1.05D;
				player.motionZ *= 1.05D;
				player.motionY *= 0.5D;
			}

		if (nbt.getBoolean("isPowered") && /*canFly() &&*/ hasGemOrIsCreative(player))
			if (!player.onGround) {
				player.motionX *= 1.05D;
				player.motionZ *= 1.05D;
				player.motionY += 0.1D;

				if (!player.capabilities.isCreativeMode) {
					nbt.setInteger("fuelTicks", nbt.getInteger("fuelTicks") + 1);
					if (nbt.getInteger("fuelTicks") >= 80) {
						nbt.setInteger("fuelTicks", 0);
						player.inventory.deleteStack(new ItemStack(ModBlocks.MUD));
					}
				}
			}
	}

	private boolean hasGemOrIsCreative(EntityPlayer player) {
		return player.capabilities.isCreativeMode || player.inventory.hasItemStack(new ItemStack(ModBlocks.MUD));
	}

	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setBoolean("isGliding", false);
		stack.getTagCompound().setBoolean("isPowered", false);
		stack.getTagCompound().setInteger("fuelTicks", 0);

	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onPlayerRenderPre(RenderPlayerEvent.Pre event) {
		GL11.glPushMatrix();

		EntityPlayer player = event.getEntityPlayer();
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();

		double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * event.getPartialRenderTick();
		double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * event.getPartialRenderTick();
		double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * event.getPartialRenderTick();
		d0 = d0 - renderManager.viewerPosX;
		d1 = d1 - renderManager.viewerPosY;
		d2 = d2 - renderManager.viewerPosZ;
		GL11.glTranslated(d0, d1, d2);

		ItemStack chestPlate = player.inventory.armorInventory.get(2);
		if (chestPlate.getItem() instanceof ItemArmorGlider && chestPlate.hasTagCompound())
			if (chestPlate.getTagCompound().getBoolean("isGliding") && !player.onGround || chestPlate.getTagCompound().getBoolean("isPowered") && !player.onGround) {
				float yaw = player.rotationYaw;
				float x = (float) Math.cos(Math.PI * yaw / 180F);
				float y = (float) Math.sin(Math.PI * yaw / 180F);
				GL11.glRotatef(60.0F, x, 0.0F, y);
				player.limbSwingAmount = 0.1F;
			}

		GL11.glTranslated(-d0, -d1, -d2);
	}

	/*public boolean canFly() {
		return this == ModItems.GLIDER_CHESTPLATE_POWERED;
	}*/

	private boolean hasTag(ItemStack stack) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			return false;
		}
		return true;
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onPlayerRenderPost(RenderPlayerEvent.Post event) {
		GL11.glPopMatrix();
	}
}
