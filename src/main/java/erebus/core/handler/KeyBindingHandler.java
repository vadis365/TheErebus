package erebus.core.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.entity.EntityRhinoBeetle;
import erebus.entity.EntityStagBeetle;
import erebus.lib.Reference;
import erebus.network.PacketPipeline;
import erebus.network.server.PacketBeetleDig;
import erebus.network.server.PacketBeetleRamAttack;
import erebus.network.server.PacketGlider;
import erebus.network.server.PacketGliderPowered;

@SideOnly(Side.CLIENT)
public class KeyBindingHandler {

	public static KeyBinding glide = new KeyBinding("key.erebus.glide", Keyboard.KEY_G, Reference.MOD_NAME);
	public static KeyBinding poweredGlide = new KeyBinding("key.erebus.poweredGlide", Keyboard.KEY_F, Reference.MOD_NAME);
	public static KeyBinding beetleRam = new KeyBinding("key.erebus.beetleRam", Keyboard.KEY_R, Reference.MOD_NAME);
	public static KeyBinding beetleMine = new KeyBinding("key.erebus.beetleMine", Keyboard.KEY_LMENU, Reference.MOD_NAME);

	public KeyBindingHandler() {
		ClientRegistry.registerKeyBinding(glide);
		ClientRegistry.registerKeyBinding(poweredGlide);
		ClientRegistry.registerKeyBinding(beetleRam);
		ClientRegistry.registerKeyBinding(beetleMine);
	}

	@SubscribeEvent
	public void onKey(KeyInputEvent e) {
		if (glide.isPressed()) {
			EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
			if (player == null || player.onGround)
				return;

			ItemStack chestPlate = player.inventory.armorInventory[2];
			if (chestPlate != null && chestPlate.getItem() == ModItems.armorGlider || chestPlate != null && chestPlate.getItem() == ModItems.armorGliderPowered) {
				if (!chestPlate.hasTagCompound())
					chestPlate.stackTagCompound = new NBTTagCompound();

				chestPlate.getTagCompound().setBoolean("isGliding", true);
				PacketPipeline.sendToServer(new PacketGlider(true));
			}
		}

		if (poweredGlide.isPressed()) {
			EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
			if (player == null || player.onGround)
				return;

			ItemStack chestPlate = player.inventory.armorInventory[2];
			if (chestPlate != null && chestPlate.getItem() == ModItems.armorGliderPowered) {
				if (!chestPlate.hasTagCompound())
					chestPlate.stackTagCompound = new NBTTagCompound();

				chestPlate.getTagCompound().setBoolean("isPowered", true);
				PacketPipeline.sendToServer(new PacketGliderPowered(true));
			}
		}

		if (beetleRam.isPressed()) {
			EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
			if (player == null)
				return;

			if (player.isRiding() && player.ridingEntity instanceof EntityRhinoBeetle)
				PacketPipeline.sendToServer(new PacketBeetleRamAttack(true));
		}

		if (beetleMine.isPressed()) {
			EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
			if (player == null)
				return;

			if (player.isRiding() && player.ridingEntity instanceof EntityStagBeetle) {
				MovingObjectPosition mop = Minecraft.getMinecraft().renderViewEntity.rayTrace(5, 1.0F);
				if(mop != null && mop.typeOfHit == MovingObjectType.BLOCK) {
					PacketPipeline.sendToServer(new PacketBeetleDig(mop.blockX, mop.blockY, mop.blockZ, mop.sideHit));
				}
			}
		}
	}
}
