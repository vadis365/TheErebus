package erebus.core.handler;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import erebus.ModItems;
import erebus.core.teleport.TeleportClient;

public class ClientTickHandler implements ITickHandler {

	public static Minecraft mc = FMLClientHandler.instance().getClient();
	public boolean keyStates[] = new boolean[256];

	private static PotionEffect nightVisionEffect = new PotionEffect(Potion.nightVision.id, 300, 1, true);

	public boolean checkKey(int i) {
		if (Minecraft.getMinecraft().currentScreen != null)
			return false;
		if (Keyboard.isKeyDown(i) != keyStates[i])
			return keyStates[i] = !keyStates[i];
		else
			return false;
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if (type.equals(EnumSet.of(TickType.RENDER))) {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			if (player != null && player.inventory.armorInventory[3] != null && player.inventory.armorInventory[3].getItem() == ModItems.compoundGoggles)
				player.addPotionEffect(nightVisionEffect);
			if (player != null && player.inventory.armorInventory[3] != null && player.inventory.armorInventory[3].getItem() == ModItems.reinCompoundGoggles)
				player.addPotionEffect(nightVisionEffect);
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if (type.equals(EnumSet.of(TickType.RENDER))) {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			if (player != null) {
				PotionEffect eff = player.getActivePotionEffect(Potion.nightVision);
				if (eff != null && eff.getAmplifier() == 1)
					player.removePotionEffectClient(Potion.nightVision.id);
			}

			float ticks = ((Float) tickData[0]).floatValue();

			float time = TeleportClient.prevTimeInPortal + (TeleportClient.timeInPortal - TeleportClient.prevTimeInPortal) * ticks;

			if (!(time > 0F && TeleportClient.mc.currentScreen == null) && TeleportClient.mc.thePlayer != null) {
				PotionEffect eff = TeleportClient.mc.thePlayer.getActivePotionEffect(Potion.confusion);
				if (eff != null && eff.getAmplifier() == 69)
					TeleportClient.mc.thePlayer.removePotionEffect(Potion.confusion.id);
			}
		} else if (type.equals(EnumSet.of(TickType.PLAYER)))
			TeleportClient.onTick((EntityPlayer) tickData[0]);
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER, TickType.PLAYER);
	}

	@Override
	public String getLabel() {
		return "Erebus_ClientTickHandler";
	}

}