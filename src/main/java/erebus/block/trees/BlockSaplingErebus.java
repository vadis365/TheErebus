package erebus.block.trees;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockSapling;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import erebus.ModTabs;
import erebus.lib.EnumWood;
import erebus.world.feature.tree.WorldGenAsperTree;
import erebus.world.feature.tree.WorldGenBalsamTree;
import erebus.world.feature.tree.WorldGenBaobabTree;
import erebus.world.feature.tree.WorldGenCypressTree;
import erebus.world.feature.tree.WorldGenErebusHugeTree;
import erebus.world.feature.tree.WorldGenEucalyptusTree;
import erebus.world.feature.tree.WorldGenMarshwoodTree;
import erebus.world.feature.tree.WorldGenMossbarkTree;

public class BlockSaplingErebus extends BlockSapling {

	private final EnumWood wood;

	public BlockSaplingErebus(EnumWood wood) {
		this.wood = wood;
		setCreativeTab(ModTabs.blocks);
		setStepSound(SoundType.PLANT);
		setRegistryName("sapling_" + wood.name().toLowerCase());
		setUnlocalizedName(getRegistryName().toString());
	}

	@Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            super.updateTick(worldIn, pos, state, rand);

            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
               this.grow(worldIn, pos, state, rand); 
        }
    }

	@Override
	public void generateTree(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!TerrainGen.saplingGrowTree(world, rand, pos))
			return;

		WorldGenerator worldGen = null;

		switch (wood) {
			case EUCALYPTUS:
				worldGen = new WorldGenEucalyptusTree();
				break;
			case BAOBAB:
				worldGen = new WorldGenBaobabTree();
				break;
			case ASPER:
				worldGen = new WorldGenAsperTree();
				break;
			case CYPRESS:
				worldGen = new WorldGenCypressTree();
				break;
			case MAHOGANY:
				worldGen = new WorldGenErebusHugeTree(true, true, wood);
				((WorldGenErebusHugeTree) worldGen).prepare(20 + rand.nextInt(5));
				break;
			case BALSAM:
				worldGen = new WorldGenBalsamTree();
				break;
			case MOSSBARK:
				worldGen = new WorldGenMossbarkTree();
				break;
			case MARSHWOOD:
				worldGen = new WorldGenMarshwoodTree();
				break;
			default:
				break;
		}

		if (worldGen == null)
			return;

		world.setBlockToAir(pos);
		if (!worldGen.generate(world, rand, pos))
			world.setBlockState(pos, (IBlockState) this);
	}

	public boolean isSameSapling(World world, BlockPos pos, int meta) {
		return world.getBlockState(pos) == this;
	}
	
	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		list.add(new ItemStack(itemIn, 1, 0));
	}
}