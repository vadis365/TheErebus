package erebus.blocks;

import erebus.ModTabs;
import erebus.entity.EntityBlackWidow;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockWitherWeb extends BlockWeb {

	public BlockWitherWeb() {
		setHardness(4.0F);
		setCreativeTab(ModTabs.BLOCKS);
		setHarvestLevel("shears", 1);
		Items.SHEARS.setHarvestLevel("shears", 1);
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
		super.onEntityCollidedWithBlock(worldIn, pos, state, entity);
		if (entity instanceof EntityLivingBase && !(entity instanceof EntityBlackWidow))
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WITHER, 5 * 20, 0));
	}
}