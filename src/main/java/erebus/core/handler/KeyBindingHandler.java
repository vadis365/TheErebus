package erebus.core.handler;

import org.lwjgl.input.Keyboard;

import erebus.Erebus;
import erebus.entity.EntityRhinoBeetle;
import erebus.entity.EntityStagBeetle;
import erebus.lib.Reference;
import erebus.network.server.PacketBeetleDig;
import erebus.network.server.PacketBeetleRamAttack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyBindingHandler {

	public static KeyBinding GLIDE = new KeyBinding("key.erebus.glide", Keyboard.KEY_G, Reference.MOD_NAME);
	public static KeyBinding POWERED_GLIDE = new KeyBinding("key.erebus.poweredGlide", Keyboard.KEY_F, Reference.MOD_NAME);
	public static KeyBinding BEETLE_RAM = new KeyBinding("key.erebus.beetleRam", Keyboard.KEY_R, Reference.MOD_NAME);
	public static KeyBinding BEETLE_MINE = new KeyBinding("key.erebus.beetleMine", Keyboard.KEY_LMENU, Reference.MOD_NAME);

	public KeyBindingHandler() {
		ClientRegistry.registerKeyBinding(GLIDE);
		ClientRegistry.registerKeyBinding(POWERED_GLIDE);
		ClientRegistry.registerKeyBinding(BEETLE_RAM);
		ClientRegistry.registerKeyBinding(BEETLE_MINE);
	}

	@SubscribeEvent
	public void onKey(KeyInputEvent e) {
/*
		if (glide.isPressed()) {
			EntityPlayer player = FMLClientHandler.instance().getClient().player;
			if (player == null || player.onGround)
				return;

			ItemStack chestPlate = player.inventory.armorInventory[2];
			if (chestPlate != null && chestPlate.getItem() == ModItems.armorGlider || chestPlate != null && chestPlate.getItem() == ModItems.armorGliderPowered) {
				if (!chestPlate.hasTagCompound())
					chestPlate.getTagCompound() = new NBTTagCompound();

				chestPlate.getTagCompound().setBoolean("isGliding", true);
				PacketPipeline.sendToServer(new PacketGlider(true));
			}
		}

		if (poweredGlide.isPressed()) {
			EntityPlayer player = FMLClientHandler.instance().getClient().player;
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
*/
		if (BEETLE_RAM.isPressed()) {
			EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
			if (player == null)
				return;

			if (player.isRiding() && player.getRidingEntity() instanceof EntityRhinoBeetle)
				Erebus.NETWORK_WRAPPER.sendToServer(new PacketBeetleRamAttack(true));
		}

		if (BEETLE_MINE.isPressed()) {
			EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
			if (player == null)
				return;

			if (player.isRiding() && player.getRidingEntity() instanceof EntityStagBeetle) {
				RayTraceResult mop = Minecraft.getMinecraft().getRenderViewEntity().rayTrace(5, 1.0F);
				if(mop != null && mop.typeOfHit == RayTraceResult.Type.BLOCK) {
					Erebus.NETWORK_WRAPPER.sendToServer(new PacketBeetleDig(mop.getBlockPos(), mop.sideHit));
				}
			}
		}
	}
}
