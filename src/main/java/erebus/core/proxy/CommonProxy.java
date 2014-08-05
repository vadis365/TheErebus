package erebus.core.proxy;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.block.BlockPetrifiedChest;
import erebus.block.silo.TileEntitySiloIntake;
import erebus.block.silo.TileEntitySiloTank;
import erebus.client.gui.*;
import erebus.entity.EntityAnimatedBambooCrate;
import erebus.entity.EntityBlackAnt;
import erebus.inventory.*;
import erebus.tileentity.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class CommonProxy implements IGuiHandler {

	public static final int GUI_ID_BAMBOO_CRATE = 1;
	public static final int GUI_ID_COLOSSAL_CRATE = 2;
	public static final int GUI_ID_PETRIFIED_CRAFT = 3;
	public static final int GUI_ID_UMBER_FURNACE = 4;
	public static final int GUI_ID_PETRIFIED_CHEST = 5;
	public static final int GUI_ID_ANIMATED_BAMBOO_CRATE = 6;
	public static final int GUI_ID_EXTENDER_THINGY = 7;
	public static final int GUI_ID_HONEY_COMB = 9;
	public static final int GUI_ID_ANT_INVENTORY = 10;
	public static final int GUI_ID_SILO_INVENTORY = 11;
	public static final int GUI_ID_COMPOSTER = 12;

	private final int[][] places = new int[][] { { 1, 0, 0 }, { 1, 0, 1 }, { 0, 0, 1 }, { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 }, { 0, 1, 0 }, { 0, 0, 0 } };

	public void registerKeyHandlers() {
		// Unused server side. -- see ClientProxy for implementation
	}

	public void registerRenderInformation() {
		// Unused server side. -- see ClientProxy for implementation
	}

	public void registerTileEntities() {
		GameRegistry.registerTileEntityWithAlternatives(TileEntitySpawner.class, "tile.erebus.customSpawner", "tile.erebus.spiderSpawner", "tile.erebus.waspSpawner");
		registerTileEntity(TileEntityBambooCrate.class, "bambooCrate");
		registerTileEntity(TileEntityUmberFurnace.class, "umberFurnace");
		registerTileEntity(TileEntityErebusAltar.class, "bugZapper");
		registerTileEntity(TileEntityErebusAltarEmpty.class, "altarEmpty");
		registerTileEntity(TileEntityErebusAltarHealing.class, "altarHealing");
		registerTileEntity(TileEntityErebusAltarLightning.class, "altarLighting");
		registerTileEntity(TileEntityErebusAltarRepair.class, "altarRepair");
		registerTileEntity(TileEntityErebusAltarXP.class, "altarXP");
		registerTileEntity(TileEntityGlowingJar.class, "glowingJar");
		registerTileEntity(TileEntityLadder.class, "bambooLadder");
		registerTileEntity(TileEntityBambooBridge.class, "bambooBridge");
		registerTileEntity(TileEntityUmberGolemStatue.class, "umberGolemStatue");
		registerTileEntity(TileEntityPetrifiedWoodChest.class, "petrifiedWoodChest");
		registerTileEntity(TileEntityBones.class, "blockOBones");
		registerTileEntity(TileEntityExtenderThingy.class, "extenderThingy");
		registerTileEntity(TileEntityBambooPole.class, "bambooPole");
		registerTileEntity(TileEntityJarOHoney.class, "jarOHoney");
		registerTileEntity(TileEntityAltar.class, "altar");
		registerTileEntity(TileEntityGlowGem.class, "glowGemBlock");
		registerTileEntity(TileEntityHoneyComb.class, "honeyComb");
		registerTileEntity(TileEntitySiloIntake.class, "siloIntake");
		registerTileEntity(TileEntitySiloTank.class, "siloTank");
		registerTileEntity(TileEntityComposter.class, "composter");
        registerTileEntity(TileEntityPortal.class, "portal");
	}

	private void registerTileEntity(Class<? extends TileEntity> cls, String baseName) {
		GameRegistry.registerTileEntity(cls, "tile.erebus." + baseName);
	}

	public void spawnCustomParticle(String particleName, World world, double x, double y, double z, double vecX, double vecY, double vecZ) {
		// Unused server side. -- see ClientProxy for implementation
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GUI_ID_BAMBOO_CRATE) {
			TileEntity tileentity = world.getTileEntity(x, y, z);
			if (tileentity instanceof TileEntityBambooCrate)
				return new ContainerBambooCrate(player.inventory, (TileEntityBambooCrate) tileentity);
		}

		else if (ID == GUI_ID_COLOSSAL_CRATE) {
			TileEntity tileentity = world.getTileEntity(x, y, z);
			if (tileentity instanceof TileEntityBambooCrate) {
				List<TileEntityBambooCrate> list = new ArrayList<TileEntityBambooCrate>();
				for (int[] place : places) {
					TileEntity tile;
					tile = world.getTileEntity(x + place[0], y + place[1], z + place[2]);
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
			TileEntity tileentity = world.getTileEntity(x, y, z);
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
			return new ContainerExtenderThingy(player.inventory, (TileEntityExtenderThingy) world.getTileEntity(x, y, z));

		else if (ID == GUI_ID_HONEY_COMB) {
			TileEntity tileentity = world.getTileEntity(x, y, z);
			if (tileentity instanceof TileEntityHoneyComb)
				return new ContainerHoneyComb(player.inventory, (TileEntityHoneyComb) tileentity);
		}
		
		else if (ID == GUI_ID_ANT_INVENTORY) {
			Entity entity = world.getEntityByID(x);
			if (entity != null && entity instanceof EntityBlackAnt)
				return new ContainerAntInventory(player.inventory, (EntityBlackAnt) entity);
		}
		
		else if (ID == GUI_ID_SILO_INVENTORY) {
			TileEntity tileentity = world.getTileEntity(x, y, z);
			if (tileentity instanceof TileEntitySiloTank)
				return new ContainerSilo(player.inventory, (TileEntitySiloTank) tileentity);
		}
		
		else if (ID == GUI_ID_COMPOSTER) {
			TileEntity tileentity = world.getTileEntity(x, y, z);
			if (tileentity instanceof TileEntityComposter)
				return new ContainerComposter(player.inventory, (TileEntityComposter) tileentity);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GUI_ID_BAMBOO_CRATE) {
			TileEntity tileentity = world.getTileEntity(x, y, z);
			if (tileentity instanceof TileEntityBambooCrate)
				return new GuiBambooCrate(player.inventory, (TileEntityBambooCrate) tileentity);
		}

		else if (ID == GUI_ID_COLOSSAL_CRATE) {
			TileEntity tileentity = world.getTileEntity(x, y, z);
			if (tileentity instanceof TileEntityBambooCrate) {
				List<TileEntityBambooCrate> list = new ArrayList<TileEntityBambooCrate>();
				for (int[] place : places) {
					TileEntityBambooCrate tilecrate = (TileEntityBambooCrate) world.getTileEntity(x + place[0], y + place[1], z + place[2]);
					list.add(tilecrate);
				}
				return new GuiColossalCrate(player.inventory, list);
			}
		}

		else if (ID == GUI_ID_PETRIFIED_CRAFT)
			return new GuiPetrifiedWorkbench(player.inventory, world, x, y, z);
		else if (ID == GUI_ID_UMBER_FURNACE) {
			TileEntity tileentity = world.getTileEntity(x, y, z);
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
			return new GuiExtenderThingy(player.inventory, (TileEntityExtenderThingy) world.getTileEntity(x, y, z));

		else if (ID == GUI_ID_HONEY_COMB) {
			TileEntity tileentity = world.getTileEntity(x, y, z);
			if (tileentity instanceof TileEntityHoneyComb)
				return new GuiHoneyComb(player.inventory, (TileEntityHoneyComb) tileentity);
		}
		
		else if (ID == GUI_ID_ANT_INVENTORY) {
			Entity entity = world.getEntityByID(x);
			if (entity != null && entity instanceof EntityBlackAnt)
				return new GuiAntInventory(player.inventory, entity);
		}
		
		else if (ID == GUI_ID_SILO_INVENTORY) {
			TileEntity tileentity = world.getTileEntity(x, y, z);
			if (tileentity instanceof TileEntitySiloTank)
				return new GuiSilo(player.inventory, (TileEntitySiloTank) tileentity);
		}
		
		else if (ID == GUI_ID_COMPOSTER) {
			TileEntity tileentity = world.getTileEntity(x, y, z);
			if (tileentity instanceof TileEntityComposter)
				return new GuiComposter(player.inventory, (TileEntityComposter) tileentity);
		}

		return null;
	}
}