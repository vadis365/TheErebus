package erebus.blocks;

import java.util.Random;

import erebus.Erebus;
import erebus.ModSounds;
import erebus.ModTabs;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDung extends BlockFalling {

	public BlockDung() {
		super(Material.GROUND);
		setHardness(0.4F);
		setSoundType(ModSounds.DUNG);
		setCreativeTab(ModTabs.BLOCKS);
		setHarvestLevel("shovel", 0);
	}

	@Override
	   public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable) {
		return true;
	}

	@Override
    @SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World world, BlockPos pos, Random rand) {
		double pixel = 0.0625D;
		if(rand.nextInt(3) == 0) {
			for (int l = 0; l < 5; l++) {
				double particleX = pos.getX() + rand.nextFloat();
				double particleY = pos.getY() + rand.nextFloat();
				double particleZ = pos.getZ() + rand.nextFloat();

				if (l == 0 && !world.getBlockState(pos.up()).isOpaqueCube())
					particleY = pos.getY() + 1 + pixel;

				if (l == 1 && !world.getBlockState(pos.down()).isOpaqueCube())
					particleY = pos.getY() - pixel;

				if (l == 2 && !world.getBlockState(pos.add(0, 0, 1)).isOpaqueCube())
					particleZ = pos.getZ() + 1 + pixel;

				if (l == 3 && !world.getBlockState(pos.add(0, 0, -1)).isOpaqueCube())
					particleZ = pos.getZ() - pixel;

				if (l == 4 && !world.getBlockState(pos.add(1, 0, 0)).isOpaqueCube())
					particleX = pos.getX() + 1 + pixel;

				if (l == 5 && !world.getBlockState(pos.add(-1, 0, 0)).isOpaqueCube())
					particleX = pos.getX() - pixel;

				if (particleX < pos.getX() || particleX > pos.getX() + 1 || particleY < pos.getY() || particleY > pos.getY() + 1 || particleZ < pos.getZ() || particleZ > pos.getZ() + 1)
					Erebus.PROXY.spawnCustomParticle("poison", world, particleX, particleY, particleZ, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.DIRT;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getDustColor(IBlockState state) {
		return -8356741;
	}

	@Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
        if (side == EnumFacing.UP)
            return true;
        return false;
    }

	@Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 0;
    }
}
