package erebus.core.proxy;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.block.BlockPetrifiedChest;
import erebus.client.gui.GuiBambooCrate;
import erebus.client.gui.GuiColossalCrate;
import erebus.client.gui.GuiExtenderThingy;
import erebus.client.gui.GuiPetrifiedChest;
import erebus.client.gui.GuiPetrifiedWorkbench;
import erebus.client.gui.GuiUmberFurnace;
import erebus.entity.EntityAnimatedBambooCrate;
import erebus.inventory.ContainerBambooCrate;
import erebus.inventory.ContainerColossalCrate;
import erebus.inventory.ContainerExtenderThingy;
import erebus.inventory.ContainerPetrifiedCraftingTable;
import erebus.inventory.ContainerPetrifiedWoodChest;
import erebus.inventory.ContainerUmberFurnace;
import erebus.tileentity.TileEntityBambooBridge;
import erebus.tileentity.TileEntityBambooCrate;
import erebus.tileentity.TileEntityBambooPole;
import erebus.tileentity.TileEntityBones;
import erebus.tileentity.TileEntityErebusAltar;
import erebus.tileentity.TileEntityErebusAltarEmpty;
import erebus.tileentity.TileEntityErebusAltarHealing;
import erebus.tileentity.TileEntityErebusAltarLightning;
import erebus.tileentity.TileEntityErebusAltarRepair;
import erebus.tileentity.TileEntityErebusAltarXP;
import erebus.tileentity.TileEntityExtenderThingy;
import erebus.tileentity.TileEntityGlowingJar;
import erebus.tileentity.TileEntityLadder;
import erebus.tileentity.TileEntityPetrifiedWoodChest;
import erebus.tileentity.TileEntitySpawner;
import erebus.tileentity.TileEntityUmberFurnace;
import erebus.tileentity.TileEntityUmberGolemStatue;

public class CommonProxy implements IGuiHandler {

	public static final int GUI_ID_BAMBOO_CRATE = 1;
	public static final int GUI_ID_COLOSSAL_CRATE = 2;
	public static final int GUI_ID_PETRIFIED_CRAFT = 3;
	public static final int GUI_ID_UMBER_FURNACE = 4;
	public static final int GUI_ID_PETRIFIED_CHEST = 5;
	public static final int GUI_ID_ANIMATED_BAMBOO_CRATE = 6;
	public static final int GUI_ID_EXTENDER_THINGY = 7;
	public final int bambooCropRenderID = RenderingRegistry.getNextAvailableRenderId();
	public final int hollowLogRenderID = RenderingRegistry.getNextAvailableRenderId();

	private final int[][] places = new int[][] { { 1, 0, 0 }, { 1, 0, 1 }, { 0, 0, 1 }, { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 }, { 0, 1, 0 }, { 0, 0, 0 } };

	public void registerKeyHandlers() {
		// Unused server side. -- see ClientProxy for implementation
	}

	public void registerRenderInformation() {
		// Unused server side. -- see ClientProxy for implementation
	}

	public void registerTileEntities() {
		GameRegistry.registerTileEntityWithAlternatives(TileEntitySpawner.class, "Custom Spawner (Erebus)", "Spider Spawner (Erebus)", "Wasp Spawner (Erebus)");
		GameRegistry.registerTileEntity(TileEntityBambooCrate.class, "Bamboo Crate (Erebus)");
		GameRegistry.registerTileEntity(TileEntityUmberFurnace.class, "Umber Furnace (Erebus)");
		GameRegistry.registerTileEntity(TileEntityErebusAltar.class, "Tile Entity Bug Zapper (Erebus)");
		GameRegistry.registerTileEntity(TileEntityErebusAltarEmpty.class, "Altar - Empty (Erebus)");
		GameRegistry.registerTileEntity(TileEntityErebusAltarHealing.class, "Altar - Healing (Erebus)");
		GameRegistry.registerTileEntity(TileEntityErebusAltarLightning.class, "Altar - Lightning (Erebus)");
		GameRegistry.registerTileEntity(TileEntityErebusAltarRepair.class, "Altar - Repair (Erebus)");
		GameRegistry.registerTileEntity(TileEntityErebusAltarXP.class, "Altar - XP (Erebus)");
		GameRegistry.registerTileEntity(TileEntityGlowingJar.class, "Glowing Jar (Erebus)");
		GameRegistry.registerTileEntity(TileEntityLadder.class, "Bamboo Ladder (Erebus)");
		GameRegistry.registerTileEntity(TileEntityBambooBridge.class, "Bamboo Bridge (Erebus)");
		GameRegistry.registerTileEntity(TileEntityUmberGolemStatue.class, "Umber Golem Statue (Erebus)");
		GameRegistry.registerTileEntity(TileEntityPetrifiedWoodChest.class, "Petrified Wood Chest (Erebus)");
		GameRegistry.registerTileEntity(TileEntityBones.class, "Block o' Bones (Erebus)");
		GameRegistry.registerTileEntity(TileEntityExtenderThingy.class, "erebus.extenderThingy");
		GameRegistry.registerTileEntity(TileEntityBambooPole.class, "erebus.bambooPole");
	}

	public void handleParticlePacket(INetworkManager manager, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput data) {
		// Unused server side. -- see ClientProxy for implementation
	}

	public void spawnCustomParticle(String particleName, World world, double x, double y, double z, double vecX, double vecY, double vecZ) {
		// Unused server side. -- see ClientProxy for implementation
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GUI_ID_BAMBOO_CRATE) {
			TileEntity tileentity = world.getBlockTileEntity(x, y, z);
			if (tileentity instanceof TileEntityBambooCrate)
				return new ContainerBambooCrate(player.inventory, (TileEntityBambooCrate) tileentity);
		}

		else if (ID == GUI_ID_COLOSSAL_CRATE) {
			TileEntity tileentity = world.getBlockTileEntity(x, y, z);
			if (tileentity instanceof TileEntityBambooCrate) {
				List<TileEntityBambooCrate> list = new ArrayList<TileEntityBambooCrate>();
				for (int[] place : places) {
					TileEntity tile;
					tile = world.getBlockTileEntity(x + place[0], y + place[1], z + place[2]);
					if (tile != null && tile instanceof TileEntityBambooCrate) {
						TileEntityBambooCrate tilecrate = (TileEntityBambooCrate) tile;
						list.add(tilecrate);
					} else
						return null;
				}
				return new ContainerColossalCrate(player.inventory, list);
			}
		}

		else if (ID == GUI_ID_PETRIFIED_CRAFT)
			return new ContainerPetrifiedCraftingTable(player.inventory, world, x, y, z);

		else if (ID == GUI_ID_UMBER_FURNACE) {
			TileEntity tileentity = world.getBlockTileEntity(x, y, z);
			if (tileentity instanceof TileEntityUmberFurnace)
				return new ContainerUmberFurnace(player.inventory, (TileEntityUmberFurnace) tileentity);
		}

		else if (ID == GUI_ID_PETRIFIED_CHEST) {
			IInventory inventory = BlockPetrifiedChest.getInventory(world, x, y, z);
			return new ContainerPetrifiedWoodChest(player.inventory, inventory);
		}

		else if (ID == GUI_ID_ANIMATED_BAMBOO_CRATE) {
			Entity entity = world.getEntityByID(x);
			if (entity != null && entity instanceof EntityAnimatedBambooCrate)
				return new ContainerBambooCrate(player.inventory, ((EntityAnimatedBambooCrate) entity).getTile());
		}

		else if (ID == GUI_ID_EXTENDER_THINGY)
			return new ContainerExtenderThingy(player.inventory, (TileEntityExtenderThingy) world.getBlockTileEntity(x, y, z));

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GUI_ID_BAMBOO_CRATE) {
			TileEntity tileentity = world.getBlockTileEntity(x, y, z);
			if (tileentity instanceof TileEntityBambooCrate)
				return new GuiBambooCrate(player.inventory, (TileEntityBambooCrate) tileentity);
		}

		else if (ID == GUI_ID_COLOSSAL_CRATE) {
			TileEntity tileentity = world.getBlockTileEntity(x, y, z);
			if (tileentity instanceof TileEntityBambooCrate) {
				List<TileEntityBambooCrate> list = new ArrayList<TileEntityBambooCrate>();
				for (int[] place : places) {
					TileEntityBambooCrate tilecrate = (TileEntityBambooCrate) world.getBlockTileEntity(x + place[0], y + place[1], z + place[2]);
					list.add(tilecrate);
				}
				return new GuiColossalCrate(player.inventory, list);
			}
		}

		else if (ID == GUI_ID_PETRIFIED_CRAFT)
			return new GuiPetrifiedWorkbench(player.inventory, world, x, y, z);
		else if (ID == GUI_ID_UMBER_FURNACE) {
			TileEntity tileentity = world.getBlockTileEntity(x, y, z);
			if (tileentity instanceof TileEntityUmberFurnace)
				return new GuiUmberFurnace(player.inventory, (TileEntityUmberFurnace) tileentity);
		}

		else if (ID == GUI_ID_PETRIFIED_CHEST) {
			IInventory inventory = BlockPetrifiedChest.getInventory(world, x, y, z);
			return new GuiPetrifiedChest(player.inventory, inventory);
		}

		else if (ID == GUI_ID_ANIMATED_BAMBOO_CRATE) {
			Entity entity = world.getEntityByID(x);
			if (entity != null && entity instanceof EntityAnimatedBambooCrate)
				return new GuiBambooCrate(player.inventory, ((EntityAnimatedBambooCrate) entity).getTile());
		}

		else if (ID == GUI_ID_EXTENDER_THINGY)
			return new GuiExtenderThingy(player.inventory, (TileEntityExtenderThingy) world.getBlockTileEntity(x, y, z));

		return null;
	}

}