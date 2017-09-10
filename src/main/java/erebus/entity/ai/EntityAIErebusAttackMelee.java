package erebus.entity.ai;

import erebus.entity.EntityAntlionMiniBoss;
import erebus.entity.EntityPrayingMantis;
import erebus.entity.EntityVelvetWorm;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAIErebusAttackMelee extends EntityAIAttackMelee {

	public EntityAIErebusAttackMelee(EntityCreature entityIn, double speedIn, boolean useLongMemory) {
		super(entityIn, speedIn, useLongMemory);
	}

	@Override
	protected double getAttackReachSqr(EntityLivingBase attackTarget) {
		return (double) (4.0F + attackTarget.width);
	}
}