package erebus.items;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import erebus.Erebus;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.client.model.armor.ModelArmorGlider;
import erebus.client.model.armor.ModelArmorPowered;
import erebus.core.handler.KeyBindingHandler;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import erebus.network.server.PacketGlider;
import erebus.network.server.PacketGliderPowered;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorGlider extends ItemArmor {
	public ItemArmorGlider() {
		super(ModMaterials.ARMOR_REIN_EXOSKELETON, 2, EntityEquipmentSlot.CHEST);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
		if (canFly()) {
			list.add(new TextComponentTranslation("tooltip.erebus.poweredGlider").getFormattedText());
			list.add(new TextComponentTranslation("tooltip.erebus.gliderPoweredKey").getFormattedText() + ": " + Keyboard.getKeyName(KeyBindingHandler.POWERED_GLIDE.getKeyCode()));
		}
		list.add(new TextComponentTranslation("tooltip.erebus.gliderGlideKey").getFormattedText() + ": " + Keyboard.getKeyName(KeyBindingHandler.GLIDE.getKeyCode()));
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
	public void setColor(ItemStack stack, int colour) {
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
		return material.getItem() == ModItems.MATERIALS && material.getItemDamage() == EnumErebusMaterialsType.GLIDER_WING.ordinal();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, EntityEquipmentSlot armorSlot, String type) {
		return "erebus:textures/models/armor/glider.png";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase player, ItemStack stack, EntityEquipmentSlot armorSlot, ModelBiped modelBiped) {
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
				stack.setTagCompound(new NBTTagCompound());

			if (stack.getTagCompound().getBoolean("isGliding") && (!KeyBindingHandler.GLIDE.isKeyDown() || entity.onGround)) {
				stack.getTagCompound().setBoolean("isGliding", false);
				Erebus.NETWORK_WRAPPER.sendToServer(new PacketGlider(false));
			}

			if (canFly())
				if (stack.getTagCompound().getBoolean("isPowered") && (!KeyBindingHandler.POWERED_GLIDE.isKeyDown() || entity.onGround)) {
					stack.getTagCompound().setBoolean("isPowered", false);
					Erebus.NETWORK_WRAPPER.sendToServer(new PacketGliderPowered(false));
				}
		}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		onUpdate(stack, world, player, 0, false);

		player.fallDistance = 0.0F;

		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			return;
		}
		NBTTagCompound nbt = stack.getTagCompound();
		ItemStack fuel = findFuel(player);

		if (nbt.getBoolean("isGliding"))
			if (!player.onGround) {
				player.motionX *= 1.05D;
				player.motionZ *= 1.05D;
				player.motionY *= 0.5D;
			}

		if (nbt.getBoolean("isPowered") && canFly() && hasFuelOrIsCreative(player))
			if (!player.onGround) {
				player.motionX *= 1.05D;
				player.motionZ *= 1.05D;
				player.motionY += 0.1D;

				if (!player.capabilities.isCreativeMode) {
					nbt.setInteger("fuelTicks", nbt.getInteger("fuelTicks") + 1);
					if (nbt.getInteger("fuelTicks") >= 80) {
						nbt.setInteger("fuelTicks", 0);
						fuel.shrink(1);
						if (fuel.getCount() == 0)
							player.inventory.deleteStack(fuel);
					}
				}
			}
	}

	private boolean hasFuelOrIsCreative(EntityPlayer player) {
		return player.capabilities.isCreativeMode || !findFuel(player).isEmpty();
	}
	
	private ItemStack findFuel(EntityPlayer player) {
		for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
			ItemStack itemstack = player.inventory.getStackInSlot(i);
			if (isGem(itemstack))
				return itemstack;
		}
		return ItemStack.EMPTY;
	}

	protected boolean isGem(@Nullable ItemStack stack) {
		return !stack.isEmpty() && stack.getItem() == Item.getItemFromBlock(ModBlocks.RED_GEM);
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
		GlStateManager.pushMatrix();
		EntityPlayer player = event.getEntityPlayer();
		ItemStack chestPlate = player.inventory.armorInventory.get(2);
		if (!chestPlate.isEmpty() && chestPlate.getItem() instanceof ItemArmorGlider && chestPlate.hasTagCompound())
			if (chestPlate.getTagCompound().getBoolean("isGliding") && !player.onGround || chestPlate.getTagCompound().getBoolean("isPowered") && !player.onGround) {
				float yaw = player.rotationYaw;
				float x1 = (float) Math.cos(Math.PI * yaw / 180F);
				float y1 = (float) Math.sin(Math.PI * yaw / 180F);
				GlStateManager.rotate(60.0F, x1, 0.0F, y1);
				player.limbSwingAmount = 0.1F;
			}
	}

	public boolean canFly() {
		return this == ModItems.GLIDER_CHESTPLATE_POWERED;
	}

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
		GlStateManager.popMatrix();
	}
}
