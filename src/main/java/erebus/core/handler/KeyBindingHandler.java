package erebus.core.handler;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.entity.EntityRhinoBeetle;
import erebus.network.PacketPipeline;
import erebus.network.server.PacketBeetleRamAttack;
import erebus.network.server.PacketGlider;
import erebus.network.server.PacketGliderPowered;

@SideOnly(Side.CLIENT)
public class KeyBindingHandler {

	public static KeyBinding glide = new KeyBinding("Glide", Keyboard.KEY_G, "category.movement");
	public static KeyBinding poweredGlide = new KeyBinding("Glider Lift", Keyboard.KEY_F, "category.movement");
	public static KeyBinding beetleRam = new KeyBinding("Beetle Ram Attack", Keyboard.KEY_R, "category.movement");

	public KeyBindingHandler() {
		ClientRegistry.registerKeyBinding(glide);
		ClientRegistry.registerKeyBinding(poweredGlide);
		ClientRegistry.registerKeyBinding(beetleRam);
	}

	@SubscribeEvent
	public void onKey(KeyInputEvent e) {
		if (glide.getIsKeyPressed()) { // TODO not sure if getIsKeyPressed or isPressed, test!
			EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
			if (player == null)
				return;

			ItemStack chestPlate = player.inventory.armorInventory[2];
			if (chestPlate != null && chestPlate.getItem() == ModItems.armorGlider || chestPlate != null && chestPlate.getItem() == ModItems.armorGliderPowered) {
				if (!chestPlate.hasTagCompound())
					chestPlate.stackTagCompound = new NBTTagCompound();

				chestPlate.getTagCompound().setBoolean("isGliding", true);
				PacketPipeline.sendToServer(new PacketGlider(true));
			}
		}

		if (poweredGlide.getIsKeyPressed()) {
			EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
			if (player == null)
				return;

			ItemStack chestPlate = player.inventory.armorInventory[2];
			if (chestPlate != null && chestPlate.getItem() == ModItems.armorGliderPowered) {
				if (!chestPlate.hasTagCompound())
					chestPlate.stackTagCompound = new NBTTagCompound();

				chestPlate.getTagCompound().setBoolean("isPowered", true);
				PacketPipeline.sendToServer(new PacketGliderPowered(true));
			}
		}

		if (beetleRam.getIsKeyPressed()) {
			EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
			if (player == null)
				return;

			if (player.isRiding() && player.ridingEntity instanceof EntityRhinoBeetle)
				PacketPipeline.sendToServer(new PacketBeetleRamAttack(true));
		}
	}

	// TODO dave, you're on your own now... detect unpressing key in event above and run these depending on the unpressed key; DON'T just put 'else' in there,
	// otherwise it will run all the time!
	
	// Messed about with this a bit and got frustrated - made it half work.
	// Can't figure out a 'clean' way to do this...
	// Now, I will give up and let some one else fix it! D.

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		if (tickEnd) {
			EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
			if (player == null)
				return;

			ItemStack chestPlate = player.inventory.armorInventory[2];
			if (chestPlate != null && chestPlate.getItem() == ModItems.armorGlider || chestPlate != null && chestPlate.getItem() == ModItems.armorGliderPowered) {
				if (!chestPlate.hasTagCompound())
					chestPlate.stackTagCompound = new NBTTagCompound();

				chestPlate.getTagCompound().setBoolean("isGliding", false);
				PacketPipeline.sendToServer(new PacketGlider(false));
			}

			if (chestPlate != null && chestPlate.getItem() == ModItems.armorGliderPowered) {
				if (!chestPlate.hasTagCompound())
					chestPlate.stackTagCompound = new NBTTagCompound();

				chestPlate.getTagCompound().setBoolean("isPowered", false);
				PacketPipeline.sendToServer(new PacketGliderPowered(false));
			}

			if (player.isRiding() && player.ridingEntity instanceof EntityRhinoBeetle)
				PacketPipeline.sendToServer(new PacketBeetleRamAttack(false));
		}
	}
}