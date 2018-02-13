package erebus.blocks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.tileentity.TileEntityGaeanKeystone;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class BlockGaeanKeystone extends Block implements ITileEntityProvider {
	public static final PropertyBool ACTIVE = PropertyBool.create("active");

	public BlockGaeanKeystone() {
		super(Material.ROCK);
		setHardness(3.0f);
		setCreativeTab(ModTabs.BLOCKS);
		setDefaultState(blockState.getBaseState().withProperty(ACTIVE, false));
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	   public int damageDropped(IBlockState state) {
		return 0;
	}

	public static boolean isGemActive(int metadata) {
		return metadata > 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { ACTIVE });
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = getDefaultState();
		switch (meta) {
		case 0:
			state = state.withProperty(ACTIVE, Boolean.valueOf(false));
			break;
		case 1:
			state = state.withProperty(ACTIVE, Boolean.valueOf(true));
			break;
		default:
			state = state.withProperty(ACTIVE, Boolean.valueOf(false));
		}
		return state;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = 0;
		if (((Boolean) state.getValue(ACTIVE)).booleanValue())
			meta = meta | 1;
		return meta;
	}

	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		ItemStack stack = player.getHeldItemMainhand();
		if (((Boolean) state.getValue(ACTIVE)).booleanValue()) {
			if (!stack.isEmpty())
				return true;
			breakPortal(world, pos.getX(), pos.getY(), pos.getZ());
			world.setBlockState(pos, getDefaultState().withProperty(ACTIVE, Boolean.valueOf(false)), 3);
			player.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ModItems.PORTAL_ACTIVATOR));
			return true;
		}
		if (stack.isEmpty() || stack.getItem() != ModItems.PORTAL_ACTIVATOR)
			return false;
		if (makePortal(world, pos.getX(), pos.getY(), pos.getZ())) {
			world.setBlockState(pos, getDefaultState().withProperty(ACTIVE, Boolean.valueOf(true)), 3);
			player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
		} else
			world.setBlockState(pos, getDefaultState().withProperty(ACTIVE, Boolean.valueOf(false)), 3);
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityGaeanKeystone();
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		super.breakBlock(world, pos, state);
		if (((Boolean) state.getValue(ACTIVE)).booleanValue()) {
			breakPortal(world, pos.getX(), pos.getY(), pos.getZ());
			world.spawnEntity(new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(ModItems.PORTAL_ACTIVATOR)));
		}
	}

	static class PCoord {
		final World w;
		final int x, y, z;

		PCoord(World w, int x, int y, int z) {
			this.w = w;
			this.x = x;
			this.y = y;
			this.z = z;
		}

		PCoord add(int dx, int dy, int dz) {
			return new PCoord(w, x + dx, y + dy, z + dz);
		}

		boolean is(Block b) {
			return w.getBlockState(new BlockPos(x, y, z)).getBlock() == b;
		}

		static void iterateCube(PCoord min, PCoord max, ICoordFunc func) {
			for (int x = min.x; x <= max.x; x++)
				for (int z = min.z; z <= max.z; z++)
					for (int y = min.y; y <= max.y; y++)
						if (func.visit(new PCoord(min.w, x, y, z)))
							return;
		}

		interface ICoordFunc {
			boolean visit(PCoord at);
		}

		public void setAir() {
			w.setBlockToAir(new BlockPos(x, y, z));
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof PCoord))
				return false;
			PCoord pCoord = (PCoord) o;

			if (x != pCoord.x)
				return false;
			if (y != pCoord.y)
				return false;
			if (z != pCoord.z)
				return false;
			return !(w != null ? !w.equals(pCoord.w) : pCoord.w != null);

		}

		@Override
		public int hashCode() {
			int result = x;
			result = 31 * result + y;
			result = 31 * result + z;
			return result;
		}

		boolean isLeaf() {
			return w.getBlockState(new BlockPos(x, y, z)).getBlock() instanceof BlockLeaves;
		}

		boolean validLeafPortal() {
			return ErebusPortal.obeysPortalRule(w, x, y, z, false);
		}

		PCoord[] neighbors() {
			PCoord[] ret = new PCoord[6];
			int i = 0;
			for (EnumFacing dir : EnumFacing.VALUES)
				ret[i++] = add(dir.getFrontOffsetX(), dir.getFrontOffsetY(), dir.getFrontOffsetZ());
			return ret;
		}

		public void ensureFloored() {
			Block b = w.getBlockState(new BlockPos(x, y, z)).getBlock();
			if (b.isReplaceable(w, new BlockPos(x, y, z)))
				setBlock(ModBlocks.UMBERSTONE.getDefaultState());
		}

		public void setBlock(IBlockState iBlockState) {
			w.setBlockState(new BlockPos(x, y, z), iBlockState);
		}

		public void setBlockNoNotify(IBlockState iBlockState) {
			w.setBlockState(new BlockPos(x, y, z), iBlockState, 2);
		}

		public boolean isAir() {
			return w.isAirBlock(new BlockPos(x, y, z));
		}
	}

	static int LEAF_SEARCH = 8;
	static int MAX_PORTAL_SIZE = 81;

	void breakPortal(World w, int x, int y, int z) {
		PCoord here = new PCoord(w, x, y, z);
		PCoord min = here.add(-LEAF_SEARCH, -LEAF_SEARCH, -LEAF_SEARCH);
		PCoord max = here.add(+LEAF_SEARCH, +LEAF_SEARCH, +LEAF_SEARCH);

		PCoord.iterateCube(min, max, new PCoord.ICoordFunc() {
			@Override
			public boolean visit(PCoord at) {
				if (!at.is(ModBlocks.PORTAL))
					return false;
				HashSet<PCoord> found = new HashSet<PCoord>();
				ArrayList<PCoord> frontier = new ArrayList<PCoord>();
				frontier.add(at);
				while (!frontier.isEmpty()) {
					PCoord f = frontier.remove(frontier.size() - 1);
					found.add(f);
					for (PCoord pc : f.neighbors()) {
						if (found.contains(pc))
							continue;
						if (pc.is(ModBlocks.PORTAL))
							frontier.add(pc);
					}
				}
				for (PCoord pc : found)
					pc.setBlockNoNotify(Blocks.AIR.getDefaultState());
				return true;
			}
		});
	}

	boolean makePortal(World w, int x, int y, int z) {
		PCoord keystone = new PCoord(w, x, y, z);
		final HashSet<PCoord> badLeaves = new HashSet<PCoord>();
		final HashSet<PCoord> contig = new HashSet<PCoord>();
		PCoord min = keystone.add(-LEAF_SEARCH, -LEAF_SEARCH, -LEAF_SEARCH);
		PCoord max = keystone.add(+LEAF_SEARCH, +LEAF_SEARCH, +LEAF_SEARCH);

		PCoord.iterateCube(min, max, new PCoord.ICoordFunc() {
			@Override
			public boolean visit(PCoord at) {
				if (!at.isLeaf())
					return false;
				if (badLeaves.contains(at))
					return false;
				if (visitLeaves(at, contig, badLeaves) && contig.size() < MAX_PORTAL_SIZE)
					return true;
				badLeaves.addAll(contig);
				contig.clear();
				return false;
			}
		});
		if (contig.isEmpty())
			return false;
		for (PCoord at : contig)
			at.setBlockNoNotify(ModBlocks.PORTAL.getDefaultState());
		return true;
	}

	boolean visitLeaves(PCoord start, HashSet<PCoord> contig, HashSet<PCoord> invalidLeaves) {
		ArrayList<PCoord> frontier = new ArrayList<PCoord>();
		frontier.add(start);
		while (!frontier.isEmpty()) {
			PCoord at = frontier.remove(frontier.size() - 1); // Removing from end to skip array move
			for (PCoord n : at.neighbors()) {
				if (!n.isLeaf())
					continue;
				if (invalidLeaves.contains(n))
					return false;
				if (contig.add(n)) {
					if (!n.validLeafPortal()) {
						invalidLeaves.addAll(frontier);
						return false;
					}
					frontier.add(n);
				}

			}
		}
		return true;
	}

	static final byte F = 1, L = 2, END = -1;
	static final byte[] portalFrame = new byte[] { 0, F, F, F, 0, END, F, L, L, L, F, END, F, L, L, L, F, END, F, L, L, L, F, END, 0, F, F, F, 0, END, };

	public void buildDestinationPortal(World w, int x, int y, int z) {
		PCoord keystone = new PCoord(w, x, y, z);
		while (keystone.isAir() && keystone.y > 0)
			keystone = keystone.add(0, -1, 0);
		keystone = keystone.add(0, 1, 0);
		final int C = 5;
		int r = 5 / 2;
		PCoord floorMin = keystone.add(-r, -1, -r);
		PCoord floorMax = keystone.add(+r, -1, +r);
		PCoord.iterateCube(floorMin, floorMax, new PCoord.ICoordFunc() {
			@Override
			public boolean visit(PCoord at) {
				at.ensureFloored();
				int yMin = at.y + 1;
				int yMax = at.y + C;
				for (int y = yMin; y < yMax; y++)
					at.w.setBlockToAir(new BlockPos(at.x, y, at.z));
				return false;
			}
		});

			PCoord start = floorMin.add(0, 1, 0);
			int dx = 0, dy = 0, dz = 0;
			for (byte b : portalFrame) {
				if (b == END) {
					dy++;
					dx = 0;
					continue;
				} else if (b == F || b == L) {
					IBlockState block;
					int md = 0;
					if (b == L)
						if (w.getDifficulty() != EnumDifficulty.HARD)
							block = Blocks.LEAVES.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false);
						else
							block = Blocks.AIR.getDefaultState();
					else {
						md = w.rand.nextBoolean() ? 5 /* smoothUmbertile */: 6 /* smoothUmbertiles */;
						block = ModBlocks.UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, BlockUmberstone.EnumType.values()[md]);
					}
					w.setBlockState(new BlockPos(start.x + dx, start.y + dy, start.z + dz), block, 3);
				}
				dx++;
			}

			keystone.setBlock(ModBlocks.GAEAN_KEYSTONE.getDefaultState());
			keystone.w.getTileEntity(new BlockPos(keystone.x, keystone.y, keystone.z));
	}
}