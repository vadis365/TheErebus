package erebus.core.handler;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.entity.EntityRhinoBeetle;
import erebus.lib.Reference;
import erebus.network.PacketPipeline;
import erebus.network.server.PacketBeetleRamAttack;
import erebus.network.server.PacketGlider;
import erebus.network.server.PacketGliderPowered;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

@SideOnly(Side.CLIENT)
public class KeyBindingHandler {

	public static KeyBinding glide = new KeyBinding("Glide", Keyboard.KEY_G, Reference.MOD_NAME);
	public static KeyBinding poweredGlide = new KeyBinding("Glider Lift", Keyboard.KEY_F, Reference.MOD_NAME);
	public static KeyBinding beetleRam = new KeyBinding("Beetle Ram Attack", Keyboard.KEY_R, Reference.MOD_NAME);

	public KeyBindingHandler() {
		ClientRegistry.registerKeyBinding(glide);
		ClientRegistry.registerKeyBinding(poweredGlide);
		ClientRegistry.registerKeyBinding(beetleRam);
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
	}
}