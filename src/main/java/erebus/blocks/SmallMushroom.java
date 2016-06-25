package erebus.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockMushroom;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.world.feature.plant.WorldGenGiantMushrooms;
import erebus.world.feature.plant.WorldGenGiantMushrooms.MushroomType;

public class SmallMushroom extends BlockMushroom {

	public SmallMushroom() {
		setHardness(0.0F);
		setSoundType(SoundType.PLANT);
		setCreativeTab(ModTabs.BLOCKS);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item));
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		WorldGenGiantMushrooms genGiantMushrooms = new WorldGenGiantMushrooms();
		int i = 0;
        int j = 0;
        boolean flag = false;

        if (this == ModBlocks.SARCASTIC_CZECH_MUSHROOM) {
	           for (i = 0; i >= -1; --i) {
                   for (j = 0; j >= -1; --j) {
                       if (isTwoByTwoOfType(world, pos, i, j)) {
            			genGiantMushrooms.setMushroomType(MushroomType.SARCASTIC_CZECH);
                           flag = true;
                           break;
                       }
                   }
               }
        }

        else if (this == ModBlocks.GRANDMAS_SHOES_MUSHROOM) {
	           for (i = 0; i >= -1; --i) {
                   for (j = 0; j >= -1; --j) {
                       if (isTwoByTwoOfType(world, pos, i, j)) {
            			genGiantMushrooms.setMushroomType(MushroomType.GRANDMAS_SHOES);
                           flag = true;
                           break;
                       }
                   }
               }
        }

        else if (this == ModBlocks.DUTCH_CAP_MUSHROOM) {
	           for (i = 0; i >= -1; --i) {
                   for (j = 0; j >= -1; --j) {
                       if (isTwoByTwoOfType(world, pos, i, j)) {
            			genGiantMushrooms.setMushroomType(MushroomType.DUTCH_CAP);
                           flag = true;
                           break;
                       }
                   }
               }
        }

        else if (this == ModBlocks.DARK_CAPPED_MUSHROOM)
    			genGiantMushrooms.setMushroomType(MushroomType.DARK_CAPPED);

        else if (this == ModBlocks.KAIZERS_FINGERS_MUSHROOM)
    			genGiantMushrooms.setMushroomType(MushroomType.KAIZERS_FINGERS);

               IBlockState airBlock = Blocks.AIR.getDefaultState();

               if (flag) {
                   world.setBlockState(pos.add(i, 0, j), airBlock, 4);
                   world.setBlockState(pos.add(i + 1, 0, j), airBlock, 4);
                   world.setBlockState(pos.add(i, 0, j + 1), airBlock, 4);
                   world.setBlockState(pos.add(i + 1, 0, j + 1), airBlock, 4);
               }
               else
                   world.setBlockState(pos, airBlock, 4);

               if (!genGiantMushrooms.generate(world, rand, pos.add(i, 0, j))) {
                   if (flag) {
                       world.setBlockState(pos.add(i, 0, j), state, 4);
                       world.setBlockState(pos.add(i + 1, 0, j), state, 4);
                       world.setBlockState(pos.add(i, 0, j + 1), state, 4);
                       world.setBlockState(pos.add(i + 1, 0, j + 1), state, 4);
                   }
                   else
                       world.setBlockState(pos, state, 4);
               }
	}

	private boolean isTwoByTwoOfType(World world, BlockPos pos, int x, int z) {
		return isTypeAt(world, pos.add(x, 0, z)) && isTypeAt(world, pos.add(x + 1, 0, z)) && isTypeAt(world, pos.add(x, 0, z + 1)) && isTypeAt(world, pos.add(x + 1, 0, z + 1));
	}

	public boolean isTypeAt(World worldIn, BlockPos pos) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		return iblockstate.getBlock() == this;
	}
}