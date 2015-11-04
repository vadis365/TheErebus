package erebus.block;

import java.util.ArrayList;
import java.util.HashSet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.tileentity.TileEntityGaeanKeystone;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class GaeanKeystone extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon icons[];

	public GaeanKeystone() {
		super(Material.rock);
		setHardness(3.0f);
		setCreativeTab(ModTabs.blocks);
		setBlockName("erebus.gaeanKeystone");
		setBlockBounds(0, 0, 0, 1, 0.8125F, 1);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		icons = new IIcon[3];
		icons[0] = reg.registerIcon("erebus:keystone_top");
		icons[1] = reg.registerIcon("erebus:keystone_sides");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? icons[0] : side == 0 ? Blocks.stonebrick.getIcon(0, 1) : icons[1];
	}

	public static boolean isGemActive(int metadata) {
		return metadata > 0;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		ItemStack held = player.getCurrentEquippedItem();
		if (world.getBlockMetadata(x, y, z) > 0) {
			if (held != null)
				return true;
			breakPortal(world, x, y, z);
			world.setBlockMetadataWithNotify(x, y, z, 0, 3);
			player.setCurrentItemOrArmor(0, new ItemStack(ModItems.portalActivator));
			return true;
		}
		if (held == null || held.getItem() != ModItems.portalActivator)
			return false;
		if (makePortal(world, x, y, z)) {
			world.setBlockMetadataWithNotify(x, y, z, 1, 3);
			player.setCurrentItemOrArmor(0, null);
		} else
			world.setBlockMetadataWithNotify(x, y, z, 0, 3);
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityGaeanKeystone();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		super.breakBlock(world, x, y, z, block, meta);
		if (meta > 0) {
			breakPortal(world, x, y, z);
			world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, new ItemStack(ModItems.portalActivator)));
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
			return w.getBlock(x, y, z) == b;
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
			w.setBlockToAir(x, y, z);
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
			return w.getBlock(x, y, z) instanceof BlockLeaves;
		}

		boolean validLeafPortal() {
			return ErebusPortal.obeysPortalRule(w, x, y, z, false);
		}

		PCoord[] neighbors() {
			PCoord[] ret = new PCoord[6];
			int i = 0;
			for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
				ret[i++] = add(dir.offsetX, dir.offsetY, dir.offsetZ);
			return ret;
		}

		public void ensureFloored() {
			Block b = w.getBlock(x, y, z);
			if (b.isReplaceable(w, x, y, z))
				setBlock(ModBlocks.umberstone);
		}

		public void setBlock(Block b) {
			w.setBlock(x, y, z, b);
		}

		public void setBlockNoNotify(Block b) {
			w.setBlock(x, y, z, b, 0, 2);
		}

		public boolean isAir() {
			return w.isAirBlock(x, y, z);
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
				if (!at.is(ModBlocks.portal))
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
						if (pc.is(ModBlocks.portal))
							frontier.add(pc);
					}
				}
				for (PCoord pc : found)
					pc.setBlockNoNotify(Blocks.air);
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
			at.setBlockNoNotify(ModBlocks.portal);
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
					at.w.setBlockToAir(at.x, y, at.z);
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
				Block block;
				int md = 0;
				if (b == L)
					block = Blocks.air;
				else {
					md = w.rand.nextBoolean() ? 5 /* smoothUmbertile */ : 6 /* smoothUmbertiles */;
					block = ModBlocks.umberstone;
				}
				w.setBlock(start.x + dx, start.y + dy, start.z + dz, block, md, 3);
			}
			dx++;
		}

		keystone.setBlock(ModBlocks.gaeanKeystone);
		keystone.w.getTileEntity(keystone.x, keystone.y, keystone.z);
	}

}