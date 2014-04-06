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
import erebus.entity.EntityRhinoBeetle;
import erebus.network.PacketHandler;

public class KeyBindingHandler extends KeyHandler {
	public static KeyBinding glide = new KeyBinding("Glide", Keyboard.KEY_G);
	public static KeyBinding poweredGlide = new KeyBinding("Glider Lift", Keyboard.KEY_F);
	public static KeyBinding beetleRam = new KeyBinding("Beetle Ram Attack", Keyboard.KEY_R);
	public static KeyBinding[] arrayOfKeys = new KeyBinding[] { glide, beetleRam, poweredGlide };
	public static boolean[] areRepeating = new boolean[] { false, false, false };

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
		
		if (kb.keyCode == poweredGlide.keyCode) {
			EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
			if (player == null)
				return;

			ItemStack chestPlate = player.inventory.armorInventory[2];
			if (chestPlate != null && chestPlate.getItem() == ModItems.armorGlider) {
				if (!chestPlate.hasTagCompound())
					chestPlate.stackTagCompound = new NBTTagCompound();

				chestPlate.getTagCompound().setBoolean("isPowered", true);
				PacketDispatcher.sendPacketToServer(PacketHandler.buildPacket(6, true));
			}
		}
		
		if (kb.keyCode == beetleRam.keyCode) {
			EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
			if (player == null)
				return;

			if (player.isRiding() && player.ridingEntity instanceof EntityRhinoBeetle) {
			PacketDispatcher.sendPacketToServer(PacketHandler.buildPacket(5, true));
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
			
			if (chestPlate != null && chestPlate.getItem() == ModItems.armorGlider) {
				if (!chestPlate.hasTagCompound())
					chestPlate.stackTagCompound = new NBTTagCompound();

				chestPlate.getTagCompound().setBoolean("isPowered", false);
				PacketDispatcher.sendPacketToServer(PacketHandler.buildPacket(6, false));
			}
			
			if (player.isRiding() && player.ridingEntity instanceof EntityRhinoBeetle) {
			PacketDispatcher.sendPacketToServer(PacketHandler.buildPacket(5, false));
			}	
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}
}