package erebus.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.handler.configs.ConfigHandler;
import erebus.world.teleporter.TeleporterHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ErebusPortal extends Block {

	public ErebusPortal() {
		super(Material.portal);
		setLightLevel(1.0F);
		setBlockUnbreakable();
		setBlockName("erebus.portal");
		setStepSound(Block.soundTypeGlass);
		setBlockTextureName("erebus:portal");
	}

	public static boolean obeysPortalRule(World world, int x, int y, int z, boolean actualPortal) {
		// >0 substrate neighbors
		// Each substrate neighbor has solid/substrate on the opposite side
		//
		int neighborPortals = 0;
		int axisFlag = 0;
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			final int atX = x + dir.offsetX;
			final int atY = y + dir.offsetY;
			final int atZ = z + dir.offsetZ;
			Block at = world.getBlock(atX, atY, atZ);
			if (!isSubstrate(at, actualPortal))
				continue;
			final int opX = x - dir.offsetX;
			final int opY = y - dir.offsetY;
			final int opZ = z - dir.offsetZ;
			Block op = world.getBlock(opX, opY, opZ);
			if (!op.isNormalCube() && !isSubstrate(op, actualPortal))
				return false;
			neighborPortals++;
			axisFlag |= 1 << (dir.ordinal() >> 1) /* Creates a mask formatted as: <Up|Down><North|South><East|West> */;
		}
		if (neighborPortals < 1)
			return false;
		if (axisFlag == 0x7)
			return false; // 0b111, meaning there's a nieghbor on 3 sides, therefore not defining a plane
		return true;
	}

	private static boolean isSubstrate(Block block, boolean portalNotLeaf) {
		return portalNotLeaf ? block instanceof ErebusPortal : block instanceof BlockLeaves;
	}

	@Override
	public void onNeighborBlockChange(World w, int x, int y, int z, Block neighbor) {
		if (!obeysPortalRule(w, x, y, z, true))
			w.setBlockToAir(x, y, z);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		if (entity.ridingEntity == null && entity.riddenByEntity == null && entity.timeUntilPortal <= 0) {
			if (entity.dimension == 0)
				TeleporterHandler.transferToErebus(entity);
			else
				TeleporterHandler.transferToOverworld(entity);
			if (entity != null)
				entity.timeUntilPortal = ConfigHandler.INSTANCE.portalCooldown * 20;
		}
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		return world.getBlock(x, y, z) != this;
	}
}