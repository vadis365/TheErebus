package erebus.blocks;

import erebus.ModTabs;
import net.minecraft.block.BlockVine;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockThorns extends BlockVine implements IShearable {

	public BlockThorns() {
		super();
		setHardness(0.2F);
		setCreativeTab(ModTabs.PLANTS);
		setSoundType(SoundType.PLANT);
	}

	@Override
	public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
		if (!worldIn.isRemote && entity instanceof EntityLivingBase)
			entity.attackEntityFrom(DamageSource.CACTUS, 1);
	}

}