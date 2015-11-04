package erebus.block.plants;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import erebus.lib.EnumWood;
import erebus.lib.Reference;
import erebus.world.feature.tree.WorldGenAsperTree;
import erebus.world.feature.tree.WorldGenBaobabTree;
import erebus.world.feature.tree.WorldGenCypressTree;
import erebus.world.feature.tree.WorldGenErebusHugeTree;
import erebus.world.feature.tree.WorldGenEucalyptusTree;
import erebus.world.feature.tree.WorldGenMarshwoodTree;
import erebus.world.feature.tree.WorldGenMossbarkTree;
import erebus.world.feature.tree.WorldGenSapTree;
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

public class BlockSaplingErebus extends BlockSapling {

	@SideOnly(Side.CLIENT)
	private IIcon icon;
	private final EnumWood wood;

	public BlockSaplingErebus(EnumWood wood) {
		this.wood = wood;
		setCreativeTab(ModTabs.plants);
		setStepSound(Block.soundTypeGrass);
		setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
		setBlockName(Reference.MOD_ID + ".sapling." + wood.name());
		setBlockTextureName(Reference.MOD_ID + ":sapling_" + wood.name().toLowerCase());
	}

	@Override
	public String getLocalizedName() {
		return StatCollector.translateToLocal("tile." + Reference.MOD_ID + ".sapling_" + wood.getUnlocalisedName() + ".name");
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

	@Override
	public void func_149853_b(World world, Random rand, int x, int y, int z) {
		growTree(world, x, y, z, rand);
	}

	private void growTree(World world, int x, int y, int z, Random rand) {
		if (!TerrainGen.saplingGrowTree(world, rand, x, y, z))
			return;

		WorldGenerator worldGen = null;

		switch (wood) {
			case Eucalyptus:
				worldGen = new WorldGenEucalyptusTree();
				break;
			case Baobab:
				worldGen = new WorldGenBaobabTree();
				break;
			case Asper:
				worldGen = new WorldGenAsperTree();
				break;
			case Cypress:
				worldGen = new WorldGenCypressTree();
				break;
			case Mahogany:
				worldGen = new WorldGenErebusHugeTree(true, true, wood);
				((WorldGenErebusHugeTree) worldGen).prepare(20 + rand.nextInt(5));
				break;
			case Sap:
				worldGen = new WorldGenSapTree();
				break;
			case Mossbark:
				worldGen = new WorldGenMossbarkTree();
				break;
			case Marshwood:
				worldGen = new WorldGenMarshwoodTree();
				break;
			default:
				break;
		}

		if (worldGen == null)
			return;

		world.setBlockToAir(x, y, z);
		if (!worldGen.generate(world, rand, x, y, z))
			world.setBlock(x, y, z, this);
	}

	public boolean isSameSapling(World world, int x, int y, int z, int meta) {
		return world.getBlock(x, y, z) == this;
	}
}