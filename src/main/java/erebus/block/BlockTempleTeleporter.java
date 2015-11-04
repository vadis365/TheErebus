package erebus.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityTempleTeleporter;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockTempleTeleporter extends BlockContainer {

	public static final String[] iconPaths = new String[] { "templeTeleport1", "templeTeleport2", "templeTeleport3", "templeTeleport4", "templeTeleport5", "templeTeleport6", "templeTeleportNE", "templeTeleportNW", "templeTeleportSE", "templeTeleportSW", "templeBrick" };

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public BlockTempleTeleporter() {
		super(Material.rock);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.templeTeleporter");
		setBlockUnbreakable();
		setResistance(6000000.0F);
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icons = new IIcon[iconPaths.length];
		int i = 0;
		for (String path : iconPaths)
			icons[i++] = reg.registerIcon("erebus:" + path);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icons[Math.max(0, Math.min(icons.length - 1, side == 1 ? meta : 10))];
	}

	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		if (entity.isSneaking())
			return;

		int meta = world.getBlockMetadata(x, y, z);
		TileEntityTempleTeleporter tile = Utils.getTileEntity(world, x, y, z, TileEntityTempleTeleporter.class);
		if (!world.isRemote)
			if (meta >= 4 && meta <= 9)
				if (entity instanceof EntityLivingBase && tile != null) {
					((EntityLivingBase) entity).setPositionAndUpdate(tile.getTargetX() + 0.5D, tile.getTargetY() + 1D, tile.getTargetZ() + 0.5D);
					entity.worldObj.playSoundEffect(x, y, z, "mob.endermen.portal", 1.0F, 1.0F);
				}
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return null;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new TileEntityTempleTeleporter();
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		double pixel = 0.0625D;
		int meta = world.getBlockMetadata(x, y, z);
		for (int l = 0; l < 6; ++l) {
			double particleX = x + rand.nextFloat();
			double particleY = y + rand.nextFloat();
			double particleZ = z + rand.nextFloat();

			if (l == 0 && !world.getBlock(x, y + 1, z).isOpaqueCube())
				particleY = y + 1 + pixel;

			if (l == 1 && !world.getBlock(x, y - 1, z).isOpaqueCube())
				particleY = y + 0 - pixel;

			if (l == 2 && !world.getBlock(x, y, z + 1).isOpaqueCube())
				particleZ = z + 1 + pixel;

			if (l == 3 && !world.getBlock(x, y, z - 1).isOpaqueCube())
				particleZ = z + 0 - pixel;

			if (l == 4 && !world.getBlock(x + 1, y, z).isOpaqueCube())
				particleX = x + 1 + pixel;

			if (l == 5 && !world.getBlock(x - 1, y, z).isOpaqueCube())
				particleX = x + 0 - pixel;

			if (particleX < x || particleX > x + 1 || particleY < 0.0D || particleY > y + 1 || particleZ < z || particleZ > z + 1) {
				if (meta >= 1 && meta <= 9 && meta != 5)
					Erebus.proxy.spawnCustomParticle("portal", world, particleX, particleY, particleZ, 0D, 0D, 0D);
				if (meta == 5)
					Erebus.proxy.spawnCustomParticle("repellent", world, particleX, particleY, particleZ, 0D, 0D, 0D);
			}
		}
	}

}