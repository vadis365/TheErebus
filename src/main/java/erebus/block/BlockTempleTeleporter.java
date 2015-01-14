package erebus.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityTempleTeleporter;

public class BlockTempleTeleporter extends BlockContainer {
	
	private IIcon jadeTop, exoTop, magmaCreamTop, magmaEyeTop, stringTop, closedTop, openTop1, openTop2, openTop3, activeSendTop, activeReturnTop;
	
	public BlockTempleTeleporter() {
		super(Material.rock);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.templeTeleporter");
		setBlockUnbreakable();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon("erebus:templeBrick");
		jadeTop = reg.registerIcon("erebus:templeTeleportJade");
		exoTop = reg.registerIcon("erebus:templeTeleportExo");
		magmaCreamTop = reg.registerIcon("erebus:templeTeleportCream");
		magmaEyeTop = reg.registerIcon("erebus:templeTeleportEye");
		stringTop = reg.registerIcon("erebus:templeTeleportString");
		closedTop = reg.registerIcon("erebus:templeTeleportClosed");
		openTop1 = reg.registerIcon("erebus:templeTeleportOpen1");
		openTop2 = reg.registerIcon("erebus:templeTeleportOpen2");
		openTop3 = reg.registerIcon("erebus:templeTeleportOpen3");
		activeSendTop = reg.registerIcon("erebus:templeTeleportSend");
		activeReturnTop = reg.registerIcon("erebus:templeTeleportReturn");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (side == 1)
			switch(meta) {
			case 0:
				return jadeTop;
			case 1:
				return exoTop;
			case 2:
				return magmaCreamTop;
			case 3:
				return magmaEyeTop;
			case 4:
				return stringTop;
			case 5:
				return closedTop;
			case 6:
				return openTop1;
			case 7:
				return openTop2;
			case 8:
				return openTop3;
			case 9:
				return activeSendTop;
			case 10:
				return activeReturnTop;
			default:
				return blockIcon;
		}
			return blockIcon;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		TileEntityTempleTeleporter tile = Utils.getTileEntity(world, x, y, z, TileEntityTempleTeleporter.class);

		if (player.isSneaking())
			return false;

		if (player.getCurrentEquippedItem() != null) {
			int meta = world.getBlockMetadata(x, y, z);
			if(meta <= 8)
				world.setBlockMetadataWithNotify(x, y, z, meta + 1, 3);
			return true;
		}
		return true;
	}
	
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		if (entity.isSneaking())
			return;
		
		int meta = world.getBlockMetadata(x, y, z);
		TileEntityTempleTeleporter tile = Utils.getTileEntity(world, x, y, z, TileEntityTempleTeleporter.class);
		if(!world.isRemote)
			if (meta >= 9 && meta <= 10)
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
		Random random = world.rand;
		double d0 = 0.0625D;
		int meta = world.getBlockMetadata(x, y, z);
		for (int l = 0; l < 6; ++l) {
			double particleX = x + random.nextFloat();
			double particleY = y + random.nextFloat();
			double particleZ = z + random.nextFloat();

			if (l == 0 && !world.getBlock(x, y + 1, z).isOpaqueCube())
				particleY = y + 1 + d0;

			if (l == 1 && !world.getBlock(x, y - 1, z).isOpaqueCube())
				particleY = y + 0 - d0;

			if (l == 2 && !world.getBlock(x, y, z + 1).isOpaqueCube())
				particleZ = z + 1 + d0;

			if (l == 3 && !world.getBlock(x, y, z - 1).isOpaqueCube())
				particleZ = z + 0 - d0;

			if (l == 4 && !world.getBlock(x + 1, y, z).isOpaqueCube())
				particleX = x + 1 + d0;

			if (l == 5 && !world.getBlock(x - 1, y, z).isOpaqueCube())
				particleX = x + 0 - d0;

			if (particleX < x || particleX > x + 1 || particleY < 0.0D || particleY > y + 1 || particleZ < z || particleZ > z + 1) {
				if (meta >= 6 && meta <= 9)
					Erebus.proxy.spawnCustomParticle("portal", world, particleX, particleY, particleZ, 0D, 0D, 0D);
				if (meta == 10)
					Erebus.proxy.spawnCustomParticle("repellent", world, particleX, particleY, particleZ, 0D, 0D, 0D);
			}
		}
	}
}