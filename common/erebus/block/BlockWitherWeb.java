package erebus.block;

import net.minecraft.block.BlockWeb;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.helper.Utils;
import erebus.entity.EntityBlackWidow;

public class BlockWitherWeb extends BlockWeb {

	public BlockWitherWeb(int id) {
		super(id);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		super.onEntityCollidedWithBlock(world, x, y, z, entity);
		if (entity instanceof EntityLivingBase && !(entity instanceof EntityBlackWidow))
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.wither.id, 5 * 20, 0));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		return Utils.getColour(0, 0, 0);
	}
}