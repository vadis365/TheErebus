package erebus.core.proxy;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import erebus.block.BlockPetrifiedChest;
import erebus.block.silo.TileEntitySiloTank;
import erebus.entity.EntityAnimatedBambooCrate;
import erebus.entity.EntityBlackAnt;
import erebus.inventory.ContainerAnimatedBambooCrate;
import erebus.inventory.ContainerAntInventory;
import erebus.inventory.ContainerBambooCrate;
import erebus.inventory.ContainerColossalCrate;
import erebus.inventory.ContainerComposter;
import erebus.inventory.ContainerExtenderThingy;
import erebus.inventory.ContainerHoneyComb;
import erebus.inventory.ContainerPetrifiedCraftingTable;
import erebus.inventory.ContainerPetrifiedWoodChest;
import erebus.inventory.ContainerSilo;
import erebus.inventory.ContainerSmoothieMaker;
import erebus.inventory.ContainerUmberFurnace;
import erebus.tileentity.TileEntityAntlionEgg;
import erebus.tileentity.TileEntityBambooBridge;
import erebus.tileentity.TileEntityBambooCrate;
import erebus.tileentity.TileEntityBambooPole;
import erebus.tileentity.TileEntityBones;
import erebus.tileentity.TileEntityComposter;
import erebus.tileentity.TileEntityErebusAltar;
import erebus.tileentity.TileEntityErebusAltarEmpty;
import erebus.tileentity.TileEntityErebusAltarHealing;
import erebus.tileentity.TileEntityErebusAltarLightning;
import erebus.tileentity.TileEntityErebusAltarRepair;
import erebus.tileentity.TileEntityErebusAltarXP;
import erebus.tileentity.TileEntityExtenderThingy;
import erebus.tileentity.TileEntityGaeanKeystone;
import erebus.tileentity.TileEntityGlowGem;
import erebus.tileentity.TileEntityGlowingJar;
import erebus.tileentity.TileEntityHoneyComb;
import erebus.tileentity.TileEntityJarOHoney;
import erebus.tileentity.TileEntityLadder;
import erebus.tileentity.TileEntityOfferingAltar;
import erebus.tileentity.TileEntityPetrifiedWoodChest;
import erebus.tileentity.TileEntityPreservedBlock;
import erebus.tileentity.TileEntitySmoothieMaker;
import erebus.tileentity.TileEntityTarantulaEgg;
import erebus.tileentity.TileEntityTempleTeleporter;
import erebus.tileentity.TileEntityUmberFurnace;
import erebus.tileentity.TileEntityUmberGolemStatue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler {

	public static enum GuiID {
		BAMBOO_CRATE,
		COLOSSAL_CRATE,
		PETRIFIED_CRAFT,
		UMBER_FURNACE,
		PETRIFIED_CHEST,
		ANIMATED_BAMBOO_CRATE,
		EXTENDER_THINGY,
		HONEY_COMB,
		ANT_INVENTORY,
		SILO_INVENTORY,
		COMPOSTER,
		SMOOTHIE_MAKER;
	}

	public final int[][] places = new int[][] { { 1, 0, 0 }, { 1, 0, 1 }, { 0, 0, 1 }, { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 }, { 0, 1, 0 }, { 0, 0, 0 } };

	public void registerKeyHandlers() {
		// Unused server side. -- see ClientProxy for implementation
	}

	public void registerRenderInformation() {
		// Unused server side. -- see ClientProxy for implementation
	}

	public void registerTileEntities() {
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
		registerTileEntity(TileEntityGlowGem.class, "glowGemBlock");
		registerTileEntity(TileEntityHoneyComb.class, "honeyComb");
		registerTileEntity(TileEntitySiloTank.class, "siloTank");
		registerTileEntity(TileEntityComposter.class, "composter");
		registerTileEntity(TileEntityGaeanKeystone.class, "gaeanKeystone");
		registerTileEntity(TileEntityOfferingAltar.class, "offeringAltar");
		registerTileEntity(TileEntityTarantulaEgg.class, "tarantulaEgg");
		registerTileEntity(TileEntitySmoothieMaker.class, "smoothieMaker");
		registerTileEntity(TileEntityTempleTeleporter.class, "templeTeleporter");
		registerTileEntity(TileEntityAntlionEgg.class, "antlionEgg");
		registerTileEntity(TileEntityPreservedBlock.class, "preservedBlock");
	}

	private void registerTileEntity(Class<? extends TileEntity> cls, String baseName) {
		GameRegistry.registerTileEntity(cls, "tile.erebus." + baseName);
	}

	public void spawnCustomParticle(String particleName, World world, double x, double y, double z, double vecX, double vecY, double vecZ) {
		// Unused server side. -- see ClientProxy for implementation
	}

	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		GuiID guiID = GuiID.values()[ID];
		TileEntity tile = world.getTileEntity(x, y, z);
		Entity entity = world.getEntityByID(x);

		switch (guiID) {
			case ANIMATED_BAMBOO_CRATE:
				if (entity != null && entity instanceof EntityAnimatedBambooCrate)
					return new ContainerAnimatedBambooCrate(player.inventory, (EntityAnimatedBambooCrate) entity);
			case ANT_INVENTORY:
				if (entity != null && entity instanceof EntityBlackAnt)
					return new ContainerAntInventory(player.inventory, (EntityBlackAnt) entity);
			case BAMBOO_CRATE:
				return new ContainerBambooCrate(player.inventory, (TileEntityBambooCrate) tile);
			case COLOSSAL_CRATE:
				List<TileEntityBambooCrate> list = new ArrayList<TileEntityBambooCrate>();
				for (int[] place : places) {
					TileEntity te = world.getTileEntity(x + place[0], y + place[1], z + place[2]);
					if (te != null && te instanceof TileEntityBambooCrate)
						list.add((TileEntityBambooCrate) te);
					else
						return null;
				}
				return new ContainerColossalCrate(player.inventory, list);
			case COMPOSTER:
				return new ContainerComposter(player.inventory, (TileEntityComposter) tile);
			case EXTENDER_THINGY:
				return new ContainerExtenderThingy(player.inventory, (TileEntityExtenderThingy) world.getTileEntity(x, y, z));
			case HONEY_COMB:
				return new ContainerHoneyComb(player.inventory, (TileEntityHoneyComb) tile);
			case PETRIFIED_CHEST:
				IInventory inventory = BlockPetrifiedChest.getInventory(world, x, y, z);
				return new ContainerPetrifiedWoodChest(player.inventory, inventory);
			case PETRIFIED_CRAFT:
				return new ContainerPetrifiedCraftingTable(player.inventory, world, x, y, z);
			case SILO_INVENTORY:
				return new ContainerSilo(player.inventory, (TileEntitySiloTank) tile);
			case SMOOTHIE_MAKER:
				return new ContainerSmoothieMaker(player.inventory, (TileEntitySmoothieMaker) tile);
			case UMBER_FURNACE:
				return new ContainerUmberFurnace(player.inventory, (TileEntityUmberFurnace) tile);
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}
}