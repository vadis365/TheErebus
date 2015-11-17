package erebus.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.client.model.armor.ModelArmorGlider;
import erebus.client.model.armor.ModelArmorPowered;
import erebus.core.handler.KeyBindingHandler;
import erebus.item.ItemMaterials.DATA;
import erebus.network.PacketPipeline;
import erebus.network.server.PacketGlider;
import erebus.network.server.PacketGliderPowered;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.util.Constants;

public class ItemArmorGlider extends ItemArmor {

	public ItemArmorGlider() {
		super(ModMaterials.armorREINEXOSPECIAL, 2, 1);
		setCreativeTab(ModTabs.gears);
	}

	@Override
	public boolean hasColor(ItemStack stack) {
		return !stack.hasTagCompound() ? false : !stack.getTagCompound().hasKey("display", Constants.NBT.TAG_COMPOUND) ? false : stack.getTagCompound().getCompoundTag("display").hasKey("color", Constants.NBT.TAG_INT);
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
	public void func_82813_b(ItemStack stack, int colour) {
		NBTTagCompound nbt = stack.getTagCompound();

		if (nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}

		NBTTagCompound displayNBT = nbt.getCompoundTag("display");

		if (!nbt.hasKey("display", 10))
			nbt.setTag("display", displayNBT);

		displayNBT.setInteger("color", colour);
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.materials && material.getItemDamage() == DATA.gliderWing.ordinal();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type) {
		return "erebus:textures/models/armor/glider.png";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase player, ItemStack stack, int slot) {
		if (canFly()) {
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

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
		if (world.isRemote) {
			if (!stack.hasTagCompound())
				stack.stackTagCompound = new NBTTagCompound();

			if (stack.getTagCompound().getBoolean("isGliding") && (!KeyBindingHandler.glide.getIsKeyPressed() || entity.onGround)) {
				stack.getTagCompound().setBoolean("isGliding", false);
				PacketPipeline.sendToServer(new PacketGlider(false));
			}

			if (canFly())
				if (stack.getTagCompound().getBoolean("isPowered") && (!KeyBindingHandler.poweredGlide.getIsKeyPressed() || entity.onGround)) {
					stack.getTagCompound().setBoolean("isPowered", false);
					PacketPipeline.sendToServer(new PacketGliderPowered(false));
				}
		}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		onUpdate(stack, world, player, 0, false);

		player.fallDistance = 0.0F;

		if (!stack.hasTagCompound()) {
			stack.stackTagCompound = new NBTTagCompound();
			return;
		}
		NBTTagCompound nbt = stack.getTagCompound();

		if (nbt.getBoolean("isGliding"))
			if (!player.onGround) {
				player.motionX *= 1.05D;
				player.motionZ *= 1.05D;
				player.motionY *= 0.5D;
			}

		if (nbt.getBoolean("isPowered") && canFly() && hasGemOrIsCreative(player))
			if (!player.onGround) {
				player.motionX *= 1.05D;
				player.motionZ *= 1.05D;
				player.motionY += 0.1D;

				if (!player.capabilities.isCreativeMode) {
					nbt.setInteger("fuelTicks", nbt.getInteger("fuelTicks") + 1);
					if (nbt.getInteger("fuelTicks") >= 80) {
						nbt.setInteger("fuelTicks", 0);
						player.inventory.consumeInventoryItem(Item.getItemFromBlock(ModBlocks.redGem));
					}
				}
			}
	}

	private boolean hasGemOrIsCreative(EntityPlayer player) {
		return player.capabilities.isCreativeMode || player.inventory.hasItem(Item.getItemFromBlock(ModBlocks.redGem));
	}

	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		stack.stackTagCompound.setBoolean("isGliding", false);
		stack.stackTagCompound.setBoolean("isPowered", false);
		stack.stackTagCompound.setInteger("fuelTicks", 0);

	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onPlayerRenderPre(RenderPlayerEvent.Pre event) {
		GL11.glPushMatrix();

		EntityPlayer player = event.entityPlayer;

		double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * event.partialRenderTick;
		double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * event.partialRenderTick;
		double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * event.partialRenderTick;
		d0 = d0 - RenderManager.renderPosX;
		d1 = d1 - RenderManager.renderPosY;
		d2 = d2 - RenderManager.renderPosZ;
		GL11.glTranslated(d0, d1, d2);

		ItemStack chestPlate = player.inventory.armorInventory[2];
		if (chestPlate != null && chestPlate.getItem() instanceof ItemArmorGlider && chestPlate.hasTagCompound())
			if (chestPlate.getTagCompound().getBoolean("isGliding") && !player.onGround || chestPlate.getTagCompound().getBoolean("isPowered") && !player.onGround) {
				float yaw = player.rotationYaw;
				float x = (float) Math.cos(Math.PI * yaw / 180F);
				float y = (float) Math.sin(Math.PI * yaw / 180F);
				GL11.glRotatef(60.0F, x, 0.0F, y);
				player.limbSwingAmount = 0.1F;
			}

		GL11.glTranslated(-d0, -d1, -d2);
	}

	public boolean canFly() {
		return this == ModItems.armorGliderPowered;
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onPlayerRenderPost(RenderPlayerEvent.Post event) {
		GL11.glPopMatrix();
	}
}