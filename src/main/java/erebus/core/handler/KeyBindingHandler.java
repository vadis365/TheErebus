package erebus.core.handler;

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
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

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
			if (player == null)
				return;

			ItemStack chestPlate = player.inventory.armorInventory[2];
			if (chestPlate != null && chestPlate.getItem() == ModItems.armorGlider || chestPlate != null && chestPlate.getItem() == ModItems.armorGliderPowered) {
				if (!chestPlate.hasTagCompound())
					chestPlate.setTagCompound(new NBTTagCompound());

				chestPlate.getTagCompound().setBoolean("isGliding", true);
				PacketPipeline.sendToServer(new PacketGlider(true));
			}
		}

		if (poweredGlide.isPressed()) {
			EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
			if (player == null)
				return;

			ItemStack chestPlate = player.inventory.armorInventory[2];
			if (chestPlate != null && chestPlate.getItem() == ModItems.armorGliderPowered) {
				if (!chestPlate.hasTagCompound())
					chestPlate.setTagCompound(new NBTTagCompound());

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