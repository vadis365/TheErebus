package erebus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ErebusMod;
import erebus.ModBlocks;
import erebus.core.handler.ConfigurationHandler;
import erebus.core.teleport.TeleportClient;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;

public class BlockPortalErebus extends BlockBreakable {

	public BlockPortalErebus(int id) {
		super(id, "erebus:portalErebus", Material.portal, false);
		setTickRandomly(true);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (ConfigurationHandler.spawnPortalMobs && world.difficultySetting > 0 && world.provider.isSurfaceWorld() && rand.nextInt(100) < 3D + world.difficultySetting * 0.5D) {
			int yy;
			for (yy = y; !world.doesBlockHaveSolidTopSurface(x, yy, z) && yy > 0; --yy);

			if (yy > 0 && !world.isBlockNormalCube(x, yy + 1, z)) {
				EntityLiving entity = rand.nextInt(2) == 0 ? new EntityBeetle(world) : new EntityBeetleLarva(world);
				entity.setLocationAndAngles(x + 0.5D, yy + 1.1D, z + 0.5D, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
				entity.rotationYawHead = entity.renderYawOffset = entity.rotationYaw;
				entity.onSpawnWithEgg(null);
				world.spawnEntityInWorld(entity);
				entity.playLivingSound();
				entity.timeUntilPortal = entity.getPortalCooldown();
			}
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.SERVER) {
			if (entity.ridingEntity == null && entity.riddenByEntity == null && entity instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) entity;
				ErebusMod.teleportHandler.getPlayer(player.username).setInPortal();
			}
		} else if (side == Side.CLIENT && entity instanceof EntityPlayer) {
			TeleportClient.setInPortal();
			((EntityPlayer) entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 80, 69));
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		if (world.getBlockId(x - 1, y, z) != blockID && world.getBlockId(x + 1, y, z) != blockID) {
			float f = 0.125F;
			float f2 = 0.5F;
			setBlockBounds(0.5F - f, 0.0F, 0.5F - f2, 0.5F + f, 1.0F, 0.5F + f2);
		} else {
			float f1 = 0.5F;
			float f3 = 0.125F;
			setBlockBounds(0.5F - f1, 0.0F, 0.5F - f3, 0.5F + f1, 1.0F, 0.5F + f3);
		}
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		byte b0 = 0;
		byte b1 = 1;

		if (world.getBlockId(x - 1, y, z) == blockID || world.getBlockId(x + 1, y, z) == blockID) {
			b0 = 1;
			b1 = 0;
		}

		int i1;

		for (i1 = y; world.getBlockId(x, i1 - 1, z) == blockID; --i1) {
		}

		if (world.getBlockId(x, i1 - 1, z) != Block.stoneBrick.blockID)
			world.setBlockToAir(x, y, z);
		else {
			int j1;

			for (j1 = 1; j1 < 4 && world.getBlockId(x, i1 + j1, z) == blockID; ++j1) {
			}

			if (j1 == 3 && world.getBlockId(x, i1 + j1, z) == Block.stoneBrick.blockID) {
				boolean flag = world.getBlockId(x - 1, y, z) == blockID || world.getBlockId(x + 1, y, z) == blockID;
				boolean flag1 = world.getBlockId(x, y, z - 1) == blockID || world.getBlockId(x, y, z + 1) == blockID;

				if (flag && flag1)
					world.setBlockToAir(x, y, z);
				else if ((world.getBlockId(x + b0, y, z + b1) != Block.stoneBrick.blockID || world.getBlockId(x - b0, y, z - b1) != blockID) && (world.getBlockId(x - b0, y, z - b1) != Block.stoneBrick.blockID || world.getBlockId(x + b0, y, z + b1) != blockID))
					world.setBlockToAir(x, y, z);
			} else
				world.setBlockToAir(x, y, z);
		}
	}

	public boolean tryToCreatePortal(World world, int x, int y, int z) {
		byte b0 = 0;
		byte b1 = 0;

		if (world.getBlockId(x - 1, y, z) == Block.stoneBrick.blockID || world.getBlockId(x + 1, y, z) == Block.stoneBrick.blockID)
			b0 = 1;

		if (world.getBlockId(x, y, z - 1) == Block.stoneBrick.blockID || world.getBlockId(x, y, z + 1) == Block.stoneBrick.blockID)
			b1 = 1;

		if (b0 == b1)
			return false;
		else {
			if (world.getBlockId(x - b0, y, z - b1) == 0) {
				x -= b0;
				z -= b1;
			}

			int l;
			int i1;

			for (l = -1; l <= 2; ++l)
				for (i1 = -1; i1 <= 3; ++i1) {
					boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;

					if (l != -1 && l != 2 || i1 != -1 && i1 != 3) {
						int j1 = world.getBlockId(x + b0 * l, y + i1, z + b1 * l);

						if (flag) {
							if (j1 != Block.stoneBrick.blockID)
								return false;
						} else if (j1 != 0 && j1 != Block.fire.blockID)
							return false;
					}
				}

			for (l = 0; l < 2; ++l)
				for (i1 = 0; i1 < 3; ++i1)
					world.setBlock(x + b0 * l, y + i1, z + b1 * l, ModBlocks.portalErebus.blockID, 0, 2);

			return true;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		if (world.getBlockId(x, y, z) == blockID)
			return false;
		else {
			boolean flag = world.getBlockId(x - 1, y, z) == blockID && world.getBlockId(x - 2, y, z) != blockID;
			boolean flag1 = world.getBlockId(x + 1, y, z) == blockID && world.getBlockId(x + 2, y, z) != blockID;
			boolean flag2 = world.getBlockId(x, y, z - 1) == blockID && world.getBlockId(x, y, z - 2) != blockID;
			boolean flag3 = world.getBlockId(x, y, z + 1) == blockID && world.getBlockId(x, y, z + 2) != blockID;
			boolean flag4 = flag || flag1;
			boolean flag5 = flag2 || flag3;
			return !flag4 || side != 4 ? !flag4 || side != 5 ? !flag5 || side != 2 ? flag5 && side == 3 : true : true : true;
		}
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
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		if (rand.nextInt(100) == 0) {
			// TODO world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D,
			// (double)z + 0.5D, "portalambiance", 0.5F, rand.nextFloat() *
			// 0.4F + 0.8F);
		}
		for (int l = 0; l < 4; l++) {
			double d = x + rand.nextFloat();
			double d1 = y + rand.nextFloat();
			double d2 = z + rand.nextFloat();
			double d3 = 0.0D;
			double d4 = 0.0D;
			double d5 = 0.0D;
			int i1 = rand.nextInt(2) * 2 - 1;
			d3 = (rand.nextFloat() - 0.5D) * 0.5D;
			d4 = (rand.nextFloat() - 0.5D) * 0.5D;
			d5 = (rand.nextFloat() - 0.5D) * 0.5D;
			if (world.getBlockId(x - 1, y, z) != blockID && world.getBlockId(x + 1, y, z) != blockID) {
				d = x + 0.5D + 0.25D * i1;
				d3 = rand.nextFloat() * 2.0F * i1;
			} else {
				d2 = z + 0.5D + 0.25D * i1;
				d5 = rand.nextFloat() * 2.0F * i1;
			}
			world.spawnParticle("smoke", d, d1, d2, d3 / 4D, d4 / 4D, d5 / 4D);
		}
	}

}
