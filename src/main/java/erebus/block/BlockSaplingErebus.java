package erebus.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.terraingen.TerrainGen;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.lib.EnumWood;
import erebus.lib.Reference;
import erebus.world.feature.tree.WorldGenAcaciaTree;
import erebus.world.feature.tree.WorldGenAsperTree;
import erebus.world.feature.tree.WorldGenErebusHugeTree;
import erebus.world.feature.tree.WorldGenErebusTrees;
import erebus.world.feature.tree.WorldGenEucalyptusTree;
import erebus.world.feature.tree.WorldGenMossbarkTree;

public class BlockSaplingErebus extends BlockSapling {

	@SideOnly(Side.CLIENT)
	private IIcon icon;
	private final EnumWood wood;

	public BlockSaplingErebus(EnumWood wood) {
		this.wood = wood;
		setStepSound(Block.soundTypeGrass);
		setCreativeTab(ModTabs.blocks);
		setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
		setBlockName(Reference.MOD_ID + ".sapling." + wood.name());
		setBlockTextureName(Reference.MOD_ID + ":sapling_" + wood.name().toLowerCase());
	}

	@Override
	public String getLocalizedName() {
		return String.format(StatCollector.translateToLocal("tile." + Reference.MOD_ID + ".sapling.name"), wood.getTranslatedName());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon(getTextureName());
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		Block soil = world.getBlock(x, y - 1, z);
		return soil != null && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote) {
			super.updateTick(world, x, y, z, rand);

			if (rand.nextInt(13 - (world.getBlockLightValue(x, y + 1, z) >> 1)) == 0)
				growTree(world, x, y, z, rand);
		}
	}

	@Override
	public void func_149879_c(World world, int x, int y, int z, Random rand) {
	}

	@Override
	public void func_149878_d(World world, int x, int y, int z, Random rand) {
		growTree(world, x, y, z, rand);
	}

	public void growTree(World world, int x, int y, int z, Random rand) {
		if (!TerrainGen.saplingGrowTree(world, rand, x, y, z))
			return;

		WorldGenerator worldGen = null;
		int var8 = 0;
		int var9 = 0;
		boolean var10 = false;

		switch (wood) {
			case Eucalyptus:
				worldGen = new WorldGenEucalyptusTree();
				break;
			case Acacia:
				worldGen = new WorldGenAcaciaTree();
				break;
			case Asper:
				worldGen = new WorldGenAsperTree();
				break;
			case Cypress:
				break;
			case Mahogany:
				for (var8 = 0; var8 >= -1; --var8) {
					for (var9 = 0; var9 >= -1; --var9)
						if (isSameSapling(world, x + var8, y, z + var9, 0) && isSameSapling(world, x + var8 + 1, y, z + var9, 0) && isSameSapling(world, x + var8, y, z + var9 + 1, 0) && isSameSapling(world, x + var8 + 1, y, z + var9 + 1, 0)) {
							worldGen = new WorldGenErebusHugeTree(true, BlockLeavesErebus.dataMahoganyDecay, true, EnumWood.Mahogany.getLog(), ModBlocks.leavesErebus);
							((WorldGenErebusHugeTree) worldGen).prepare(20 + rand.nextInt(5));
							var10 = true;
							break;
						}

					if (worldGen != null)
						break;
				}
				break;
			case Mossbark:
				worldGen = new WorldGenMossbarkTree();
				break;
			default:
				break;
		}

		if (worldGen == null) {
			var9 = 0;
			var8 = 0;
			worldGen = new WorldGenErebusTrees(true, 5, BlockLeavesErebus.dataMahoganyDecay, false, EnumWood.Mahogany.getLog(), ModBlocks.leavesErebus, ModBlocks.thorns);
		}

		if (var10) {
			world.setBlockToAir(x + var8, y, z + var9);
			world.setBlockToAir(x + var8 + 1, y, z + var9);
			world.setBlockToAir(x + var8, y, z + var9 + 1);
			world.setBlockToAir(x + var8 + 1, y, z + var9 + 1);
		} else
			world.setBlockToAir(x, y, z);

		if (!worldGen.generate(world, rand, x + var8, y, z + var9))
			if (var10) {
				world.setBlock(x + var8, y, z + var9, this, 0, 3);
				world.setBlock(x + var8 + 1, y, z + var9, this, 0, 3);
				world.setBlock(x + var8, y, z + var9 + 1, this, 0, 3);
				world.setBlock(x + var8 + 1, y, z + var9 + 1, this, 0, 3);
			} else
				world.setBlock(x, y, z, this, 0, 3);
	}

	public boolean isSameSapling(World world, int x, int y, int z, int meta) {
		return super.func_149880_a(world, x, y, z, meta);
	}
}