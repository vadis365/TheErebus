package erebus.blocks;

import erebus.Erebus;
import erebus.ModTabs;
import erebus.world.feature.plant.WorldGenBamboo;
import erebus.world.feature.tree.*;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockSaplingErebus extends BlockSapling {

	private final EnumWood wood;

	public BlockSaplingErebus(EnumWood wood) {
		this.wood = wood;
		setSoundType(SoundType.PLANT);
		setCreativeTab(ModTabs.BLOCKS);
		Erebus.proxy.setCustomStateMap(this, new StateMap.Builder().ignore(new IProperty[] { TYPE }).build());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, NonNullList<ItemStack> list) {
		list.add(new ItemStack(item));
	}

	@Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.isRemote) {
            super.updateTick(world, pos, state, rand);

            if (world.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
            	if(this != EnumWood.BAMBOO.getSapling())
            		grow(world, pos, state, rand);
            	else
            		world.setBlockState(pos, EnumWood.BAMBOO.getLog().getDefaultState(), 3);
            }
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
			case BAMBOO:
				worldGen = new WorldGenBamboo(true, false);
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
}