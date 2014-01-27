package erebus.core.handler;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import erebus.ModItems;
import erebus.network.PacketHandler;

public class KeyBindingHandler extends KeyHandler {
	public static KeyBinding glide = new KeyBinding("Glide", Keyboard.KEY_LMENU);
	public static KeyBinding[] arrayOfKeys = new KeyBinding[] { glide };
	public static boolean[] areRepeating = new boolean[] { false };

	public KeyBindingHandler() {
		super(arrayOfKeys, areRepeating);
	}

	@Override
	public String getLabel() {
		return "Erebus KeyBindings";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		if (tickEnd)
			if (kb.keyCode == glide.keyCode) {
				EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
				if (player == null)
					return;

				ItemStack chestPlate = player.inventory.armorInventory[2];
				if (chestPlate != null && chestPlate.getItem() == ModItems.armorGlider) {
					if (!chestPlate.hasTagCompound())
						chestPlate.stackTagCompound = new NBTTagCompound();

					chestPlate.getTagCompound().setBoolean("isGliding", true);
					PacketDispatcher.sendPacketToServer(PacketHandler.buildPacket(4, true));
				}
			}
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		if (tickEnd) {
			EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
			if (player == null)
				return;

			ItemStack chestPlate = player.inventory.armorInventory[2];
			if (chestPlate != null && chestPlate.getItem() == ModItems.armorGlider) {
				if (!chestPlate.hasTagCompound())
					chestPlate.stackTagCompound = new NBTTagCompound();

				chestPlate.getTagCompound().setBoolean("isGliding", false);
				PacketDispatcher.sendPacketToServer(PacketHandler.buildPacket(4, false));
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}
}