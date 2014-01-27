package erebus.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockThorns extends Block implements IShearable {

	public BlockThorns(int id) {
		super(id, Material.vine);
		setTickRandomly(true);
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public int getRenderType() {
		return 20;
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
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		int var6 = world.getBlockMetadata(x, y, z);
		float var7 = 1.0F;
		float var8 = 1.0F;
		float var9 = 1.0F;
		float var10 = 0.0F;
		float var11 = 0.0F;
		float var12 = 0.0F;
		boolean var13 = var6 > 0;

		if ((var6 & 2) != 0) {
			var10 = Math.max(var10, 0.0625F);
			var7 = 0.0F;
			var8 = 0.0F;
			var11 = 1.0F;
			var9 = 0.0F;
			var12 = 1.0F;
			var13 = true;
		}

		if ((var6 & 8) != 0) {
			var7 = Math.min(var7, 0.9375F);
			var10 = 1.0F;
			var8 = 0.0F;
			var11 = 1.0F;
			var9 = 0.0F;
			var12 = 1.0F;
			var13 = true;
		}

		if ((var6 & 4) != 0) {
			var12 = Math.max(var12, 0.0625F);
			var9 = 0.0F;
			var7 = 0.0F;
			var10 = 1.0F;
			var8 = 0.0F;
			var11 = 1.0F;
			var13 = true;
		}

		if ((var6 & 1) != 0) {
			var9 = Math.min(var9, 0.9375F);
			var12 = 1.0F;
			var7 = 0.0F;
			var10 = 1.0F;
			var8 = 0.0F;
			var11 = 1.0F;
			var13 = true;
		}

		if (!var13 && canBePlacedOn(world.getBlockId(x, y + 1, z))) {
			var8 = Math.min(var8, 0.9375F);
			var11 = 1.0F;
			var7 = 0.0F;
			var10 = 1.0F;
			var9 = 0.0F;
			var12 = 1.0F;
		}

		setBlockBounds(var7, var8, var9, var10, var11, var12);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side) {
		switch (side) {
			case 1:
				return canBePlacedOn(world.getBlockId(x, y + 1, z));
			case 2:
				return canBePlacedOn(world.getBlockId(x, y, z + 1));
			case 3:
				return canBePlacedOn(world.getBlockId(x, y, z - 1));
			case 4:
				return canBePlacedOn(world.getBlockId(x + 1, y, z));
			case 5:
				return canBePlacedOn(world.getBlockId(x - 1, y, z));
			default:
				return false;
		}
	}

	private boolean canBePlacedOn(int blockID) {
		if (blockID == 0)
			return false;
		else {
			Block var2 = Block.blocksList[blockID];
			return var2.renderAsNormalBlock() && var2.blockMaterial.blocksMovement();
		}
	}

	private boolean canVineStay(World world, int x, int y, int z) {
		int var5 = world.getBlockMetadata(x, y, z);
		int var6 = var5;

		if (var5 > 0)
			for (int var7 = 0; var7 <= 3; ++var7) {
				int var8 = 1 << var7;

				if ((var5 & var8) != 0 && !canBePlacedOn(world.getBlockId(x + Direction.offsetX[var7], y, z + Direction.offsetZ[var7])) && (world.getBlockId(x, y + 1, z) != blockID || (world.getBlockMetadata(x, y + 1, z) & var8) == 0))
					var6 &= ~var8;
			}

		if (var6 == 0 && !canBePlacedOn(world.getBlockId(x, y + 1, z)))
			return false;
		else {
			if (var6 != var5)
				world.setBlock(x, y, z, var6);

			return true;
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		if (!world.isRemote && !canVineStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote && world.rand.nextInt(4) == 0) {
			byte b0 = 4;
			int l = 5;
			boolean flag = false;
			int i1;
			int j1;
			int k1;
			label138:

			for (i1 = x - b0; i1 <= x + b0; ++i1)
				for (j1 = z - b0; j1 <= z + b0; ++j1)
					for (k1 = y - 1; k1 <= y + 1; ++k1)
						if (world.getBlockId(i1, k1, j1) == blockID) {
							--l;

							if (l <= 0) {
								flag = true;
								break label138;
							}
						}

			i1 = world.getBlockMetadata(x, y, z);
			j1 = world.rand.nextInt(6);
			// TODO Direction fix
			k1 = /* Direction.rotateLeft[j1] */0;
			int l1;
			int i2;

			if (j1 == 1 && y < 255 && world.isAirBlock(x, y + 1, z)) {
				if (flag)
					return;

				l1 = world.rand.nextInt(16) & i1;

				if (l1 > 0) {
					for (i2 = 0; i2 <= 3; ++i2)
						if (!canBePlacedOn(world.getBlockId(x + Direction.offsetX[i2], y + 1, z + Direction.offsetZ[i2])))
							l1 &= ~(1 << i2);

					if (l1 > 0)
						world.setBlock(x, y + 1, z, blockID, l1, 2);
				}
			} else {
				int j2;

				if (j1 >= 2 && j1 <= 5 && (i1 & 1 << k1) == 0) {
					if (flag)
						return;

					l1 = world.getBlockId(x + Direction.offsetX[k1], y, z + Direction.offsetZ[k1]);

					if (l1 != 0 && Block.blocksList[l1] != null) {
						if (Block.blocksList[l1].blockMaterial.isOpaque() && Block.blocksList[l1].renderAsNormalBlock())
							world.setBlockMetadataWithNotify(x, y, z, i1 | 1 << k1, 2);
					} else {
						i2 = k1 + 1 & 3;
						j2 = k1 + 3 & 3;

						if ((i1 & 1 << i2) != 0 && canBePlacedOn(world.getBlockId(x + Direction.offsetX[k1] + Direction.offsetX[i2], y, z + Direction.offsetZ[k1] + Direction.offsetZ[i2])))
							world.setBlock(x + Direction.offsetX[k1], y, z + Direction.offsetZ[k1], blockID, 1 << i2, 2);
						else if ((i1 & 1 << j2) != 0 && canBePlacedOn(world.getBlockId(x + Direction.offsetX[k1] + Direction.offsetX[j2], y, z + Direction.offsetZ[k1] + Direction.offsetZ[j2])))
							world.setBlock(x + Direction.offsetX[k1], y, z + Direction.offsetZ[k1], blockID, 1 << j2, 2);
						else if ((i1 & 1 << i2) != 0 && world.isAirBlock(x + Direction.offsetX[k1] + Direction.offsetX[i2], y, z + Direction.offsetZ[k1] + Direction.offsetZ[i2]) && canBePlacedOn(world.getBlockId(x + Direction.offsetX[i2], y, z + Direction.offsetZ[i2])))
							world.setBlock(x + Direction.offsetX[k1] + Direction.offsetX[i2], y, z + Direction.offsetZ[k1] + Direction.offsetZ[i2], blockID, 1 << (k1 + 2 & 3), 2);
						else if ((i1 & 1 << j2) != 0 && world.isAirBlock(x + Direction.offsetX[k1] + Direction.offsetX[j2], y, z + Direction.offsetZ[k1] + Direction.offsetZ[j2]) && canBePlacedOn(world.getBlockId(x + Direction.offsetX[j2], y, z + Direction.offsetZ[j2])))
							world.setBlock(x + Direction.offsetX[k1] + Direction.offsetX[j2], y, z + Direction.offsetZ[k1] + Direction.offsetZ[j2], blockID, 1 << (k1 + 2 & 3), 2);
						else if (canBePlacedOn(world.getBlockId(x + Direction.offsetX[k1], y + 1, z + Direction.offsetZ[k1])))
							world.setBlock(x + Direction.offsetX[k1], y, z + Direction.offsetZ[k1], blockID, 0, 2);
					}
				} else if (y > 1) {
					l1 = world.getBlockId(x, y - 1, z);

					if (l1 == 0) {
						i2 = world.rand.nextInt(16) & i1;

						if (i2 > 0)
							world.setBlock(x, y - 1, z, blockID, i2, 2);
					} else if (l1 == blockID) {
						i2 = world.rand.nextInt(16) & i1;
						j2 = world.getBlockMetadata(x, y - 1, z);

						if (j2 != (j2 | i2))
							world.setBlockMetadataWithNotify(x, y - 1, z, j2 | i2, 2);
					}
				}
			}
		}
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int par9) {
		byte var10 = 0;

		switch (side) {
			case 2:
				var10 = 1;
				break;
			case 3:
				var10 = 4;
				break;
			case 4:
				var10 = 8;
				break;
			case 5:
				var10 = 2;
		}

		return var10 != 0 ? var10 : par9;
	}

	@Override
	public int idDropped(int id, Random rand, int fortune) {
		return 0;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
		super.harvestBlock(world, player, x, y, z, meta);
	}

	@Override
	public boolean isShearable(ItemStack item, World world, int x, int y, int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, 0));
		return ret;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		entity.attackEntityFrom(DamageSource.cactus, 1);
	}

	@Override
	public boolean isLadder(World world, int x, int y, int z, EntityLivingBase entity) {
		return true;
	}
}
