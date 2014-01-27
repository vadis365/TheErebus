package erebus.block;

import net.minecraft.block.BlockWeb;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import erebus.entity.EntityBlackWidow;

public class BlockWitherWeb extends BlockWeb {

	public BlockWitherWeb(int id) {
		super(id);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		entity.setInWeb();
		if (entity instanceof EntityLivingBase && !(entity instanceof EntityBlackWidow))
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.wither.id, 5 * 20, 0));
	}
}
