package erebus.tileentity;

import java.util.List;

import erebus.Erebus;
import erebus.ModBlocks;
import erebus.ModSounds;
import erebus.entity.EntityAnimatedBlock;
import erebus.entity.EntityUmberGolem;
import erebus.network.client.PacketAltarAnimationTimer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityErebusAltarLightning extends TileEntityErebusAltar implements ITickable {

	public boolean active;
	public int fuzz;
	private int spawnTicks;

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@Override
	public void update() {
		if (!getWorld().isRemote) {
			prevAnimationTicks = animationTicks;
			findEnemyToAttack();
			spawnTicks--;
			if (active) {
				if (animationTicks == 0)
					getWorld().playSound(null, getPos(), ModSounds.ALTAR_CHANGE_STATE, SoundCategory.BLOCKS, 1.0F, 1.3F);
				if (animationTicks <= 24)
					animationTicks++;

			}
			if (!active) {
				if (animationTicks == 25)
					getWorld().playSound(null, getPos(), ModSounds.ALTAR_CHANGE_STATE, SoundCategory.BLOCKS, 1.0F,
							1.3F);
				if (animationTicks >= 1)
					animationTicks--;
				if (animationTicks == 1)
					getWorld().setBlockState(getPos(), ModBlocks.ALTAR_BASE.getDefaultState());
			}

			if (spawnTicks == 0)
				setActive(false);
			if (prevAnimationTicks != animationTicks)
				Erebus.NETWORK_WRAPPER.sendToAll(new PacketAltarAnimationTimer(getPos().getX(), getPos().getY(), getPos().getZ(), animationTicks));
		}

		if (getWorld().isRemote) {
			if (animationTicks >= 1 && animationTicks <= 24)
				flameOn(getWorld(), getPos());
			if (animationTicks == 25)
				if (fuzz < 20) {
					fuzz++;
					if (fuzz >= 20)
						fuzz = 0;
				}
		}
	}

	public void flameOn(World world, BlockPos pos) {
		if (world.isRemote) {
			double d0 = pos.getX() + 0.53125F;
			double d1 = pos.getY() + 1.25F;
			double d2 = pos.getZ() + 0.53125F;
			Erebus.PROXY.spawnCustomParticle("smoke", world, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("flame", world, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("smoke", world, d0, d1, d2 - 0.265625, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("flame", world, d0, d1, d2 - 0.265625, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("smoke", world, d0, d1, d2 + 0.265625, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("flame", world, d0, d1, d2 + 0.265625, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("smoke", world, d0 - 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("flame", world, d0 - 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("smoke", world, d0 + 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("flame", world, d0 + 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("smoke", world, d0, d1 + 0.25, d2, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("flame", world, d0, d1 + 0.25, d2, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("smoke", world, d0, d1 + 0.5, d2, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("flame", world, d0, d1 + 0.5, d2, 0.0D, 0.0D, 0.0D);
		}
	}

	public void setActive(boolean par1) {
		active = par1;
	}

	public void setSpawnTicks(int i) {
		spawnTicks = i;
	}

	@SuppressWarnings("unchecked")
	protected Entity findEnemyToAttack() {
		List<EntityLivingBase> list = getWorld().getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(getPos()).grow(6D, 2D, 6D));
		if (active)
			for (int i = 0; i < list.size(); i++) {
				Entity entity = list.get(i);
				if (entity != null)
					if (entity instanceof EntityLivingBase)
						if (((EntityLivingBase) entity).getCreatureAttribute().equals(EnumCreatureAttribute.ARTHROPOD) && !(entity instanceof EntityAnimatedBlock) && !(entity instanceof EntityUmberGolem)) {
							double a = entity.posX;
							double b = entity.getEntityBoundingBox().minY;
							double c = entity.posZ;
							EntityLightningBolt entitybolt = new EntityLightningBolt(getWorld(), 0D, 0D, 0D, false);
							entitybolt.setLocationAndAngles(a, b, c, 0F, 0F);
							getWorld().addWeatherEffect(entitybolt);
						}
			}
		return null;
	}

	@Override
	protected void writeTileToNBT(NBTTagCompound nbt) {
		nbt.setInteger("animationTicks", animationTicks);
		nbt.setInteger("spawnTicks", spawnTicks);
		nbt.setBoolean("active", active);
	}

	@Override
	protected void readTileFromNBT(NBTTagCompound nbt) {
		animationTicks = nbt.getInteger("animationTicks");
		spawnTicks = nbt.getInteger("spawnTicks");
		active = nbt.getBoolean("active");
	}

}
