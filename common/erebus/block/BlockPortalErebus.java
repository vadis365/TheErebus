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
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModBlocks;
import erebus.core.handler.ConfigHandler;
import erebus.core.teleport.TeleportClient;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;

public class BlockPortalErebus extends BlockBreakable {

	public static final int[][] types = new int[][] { new int[0], { 3, 1 }, { 2, 0 } };

	public BlockPortalErebus(int id) {
		super(id, "erebus:portalErebus", Material.portal, false);
		setTickRandomly(true);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (ConfigHandler.spawnPortalMobs && world.difficultySetting > 0 && world.provider.isSurfaceWorld() && rand.nextInt(100) < 3D + world.difficultySetting * 0.5D) {
			int yy;
			for (yy = y; !world.doesBlockHaveSolidTopSurface(x, yy, z) && yy > 0; --yy)
				;

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
				Erebus.teleportHandler.getPlayer(player.username).setInPortal();
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
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourID) {
		int meta = getMetadata(world.getBlockMetadata(x, y, z));
		Size size = new Size(world, x, y, z, 1);
		Size size1 = new Size(world, x, y, z, 2);

		if (meta == 1 && (!size.isValidSize() || size.field_150864_e < size.width * size.height))
			world.setBlockToAir(x, y, z);
		else if (meta == 2 && (!size1.isValidSize() || size1.field_150864_e < size1.width * size1.height))
			world.setBlockToAir(x, y, z);
		else if (meta == 0 && !size.isValidSize() && !size1.isValidSize())
			world.setBlockToAir(x, y, z);
	}

	public boolean tryToCreatePortal(World world, int x, int y, int z) {
		Size size = new Size(world, x, y, z, 1);
		Size size1 = new Size(world, x, y, z, 2);

		if (size.isValidSize() && size.field_150864_e == 0) {
			size.makePortal();
			return true;
		} else if (size1.isValidSize() && size1.field_150864_e == 0) {
			size1.makePortal();
			return true;
		} else
			return false;
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

	public static class Size {

		private final World world;
		private final int type, field_150866_c, field_150863_d;
		private int field_150864_e = 0;
		private ChunkCoordinates porition;
		private int height;
		private int width;

		public Size(World world, int x, int y, int z, int type) {
			this.world = world;
			this.type = type;
			field_150863_d = types[type][0];
			field_150866_c = types[type][1];

			for (int i1 = y; y > i1 - 21 && y > 0 && isBlockRepleaceable(world.getBlockId(x, y - 1, z)); --y)
				;

			int j1 = getSize(x, y, z, field_150863_d) - 1;

			if (j1 >= 0) {
				porition = new ChunkCoordinates(x + j1 * Direction.offsetX[field_150863_d], y, z + j1 * Direction.offsetZ[field_150863_d]);
				width = getSize(porition.posX, porition.posY, porition.posZ, field_150866_c);

				if (width < 2 || width > 21) {
					porition = null;
					width = 0;
				}
			}

			if (porition != null)
				height = func_150858_a();
		}

		protected int getSize(int x, int y, int z, int type) {
			int j1 = Direction.offsetX[type];
			int k1 = Direction.offsetZ[type];
			int i1;
			int block;

			for (i1 = 0; i1 < 22; ++i1) {
				block = world.getBlockId(x + j1 * i1, y, z + k1 * i1);

				if (!isBlockRepleaceable(block))
					break;

				int block1 = world.getBlockId(x + j1 * i1, y - 1, z + k1 * i1);

				if (block1 != Block.stoneBrick.blockID)
					break;
			}

			block = world.getBlockId(x + j1 * i1, y, z + k1 * i1);
			return block == Block.stoneBrick.blockID ? i1 : 0;
		}

		protected int func_150858_a() {
			int i;
			int j;
			int k;
			int l;
			label56:

			for (height = 0; height < 21; height++) {
				i = porition.posY + height;

				for (j = 0; j < width; j++) {
					k = porition.posX + j * Direction.offsetX[types[type][1]];
					l = porition.posZ + j * Direction.offsetZ[types[type][1]];
					int block = world.getBlockId(k, i, l);

					if (!isBlockRepleaceable(block))
						break label56;

					if (block == ModBlocks.portalErebus.blockID)
						field_150864_e++;

					if (j == 0) {
						block = world.getBlockId(k + Direction.offsetX[types[type][0]], i, l + Direction.offsetZ[types[type][0]]);

						if (block != Block.stoneBrick.blockID)
							break label56;
					} else if (j == width - 1) {
						block = world.getBlockId(k + Direction.offsetX[types[type][1]], i, l + Direction.offsetZ[types[type][1]]);

						if (block != Block.stoneBrick.blockID)
							break label56;
					}
				}
			}

			for (i = 0; i < width; ++i) {
				j = porition.posX + i * Direction.offsetX[types[type][1]];
				k = porition.posY + height;
				l = porition.posZ + i * Direction.offsetZ[types[type][1]];

				if (world.getBlockId(j, k, l) != Block.stoneBrick.blockID) {
					height = 0;
					break;
				}
			}

			if (height <= 21 && height >= 3)
				return height;
			else {
				porition = null;
				width = 0;
				height = 0;
				return 0;
			}
		}

		protected boolean isBlockRepleaceable(int block) {
			return Block.blocksList[block] == null || Block.blocksList[block].blockMaterial == Material.air || block == Block.fire.blockID || block == ModBlocks.portalErebus.blockID;
		}

		public boolean isValidSize() {
			return porition != null && width >= 2 && width <= 21 && height >= 3 && height <= 21;
		}

		public void makePortal() {
			for (int i = 0; i < width; i++) {
				int j = porition.posX + Direction.offsetX[field_150866_c] * i;
				int k = porition.posZ + Direction.offsetZ[field_150866_c] * i;

				for (int l = 0; l < height; l++) {
					int i1 = porition.posY + l;
					world.setBlock(j, i1, k, ModBlocks.portalErebus.blockID, type, 2);
				}
			}
		}
	}

	public static int getMetadata(int meta) {
		return meta & 3;
	}
}