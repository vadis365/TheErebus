package erebus.core.handler;

import org.lwjgl.input.Keyboard;

import erebus.Erebus;
import erebus.ModItems;
import erebus.entity.EntityRhinoBeetle;
import erebus.entity.EntityStagBeetle;
import erebus.lib.Reference;
import erebus.network.server.PacketBeetleDig;
import erebus.network.server.PacketBeetleRamAttack;
import erebus.network.server.PacketGlider;
import erebus.network.server.PacketGliderPowered;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
	public static KeyBinding POWERED_GLIDE = new KeyBinding("key.erebus.poweredglide", Keyboard.KEY_F, Reference.MOD_NAME);
	public static KeyBinding BEETLE_RAM = new KeyBinding("key.erebus.beetleram", Keyboard.KEY_R, Reference.MOD_NAME);
	public static KeyBinding BEETLE_MINE = new KeyBinding("key.erebus.beetlemine", Keyboard.KEY_LMENU, Reference.MOD_NAME);

	public KeyBindingHandler() {
		ClientRegistry.registerKeyBinding(GLIDE);
		ClientRegistry.registerKeyBinding(POWERED_GLIDE);
		ClientRegistry.registerKeyBinding(BEETLE_RAM);
		ClientRegistry.registerKeyBinding(BEETLE_MINE);
	}

	@SubscribeEvent
	public void onKey(KeyInputEvent e) {

		if (GLIDE.isPressed()) {
			EntityPlayer player = FMLClientHandler.instance().getClient().player;
			if (player == null)
				return;

			ItemStack chestPlate = player.inventory.armorInventory.get(2);
			if (!chestPlate.isEmpty() && chestPlate.getItem() == ModItems.GLIDER_CHESTPLATE || !chestPlate.isEmpty() && chestPlate.getItem() == ModItems.GLIDER_CHESTPLATE_POWERED) {
				if (!chestPlate.hasTagCompound())
					chestPlate.setTagCompound(new NBTTagCompound());

				chestPlate.getTagCompound().setBoolean("isGliding", true);
				if(player.onGround) {
					player.jump();
					player.onGround = false;
				}
				Erebus.NETWORK_WRAPPER.sendToServer(new PacketGlider(true));
			}
		}

		if (POWERED_GLIDE.isPressed()) {
			EntityPlayer player = FMLClientHandler.instance().getClient().player;
			if (player == null)
				return;

			ItemStack chestPlate = player.inventory.armorInventory.get(2);
			if (!chestPlate.isEmpty() && chestPlate.getItem() == ModItems.GLIDER_CHESTPLATE_POWERED) {
				if (!chestPlate.hasTagCompound())
					chestPlate.setTagCompound(new NBTTagCompound());

				chestPlate.getTagCompound().setBoolean("isPowered", true);
				if(player.onGround) {
					player.jump();
					player.onGround = false;
				}
				Erebus.NETWORK_WRAPPER.sendToServer(new PacketGliderPowered(true));
			}
		}

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
