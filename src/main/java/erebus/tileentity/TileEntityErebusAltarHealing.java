package erebus.tileentity;

import java.util.List;

import erebus.Erebus;
import erebus.ModBlocks;
import erebus.ModSounds;
import erebus.network.client.PacketAltarAnimationTimer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityErebusAltarHealing extends TileEntityErebusAltar implements ITickable {

	public boolean active;
	private int spawnTicks ;

	@Override
	public void update() {
		if (!getWorld().isRemote) {
			prevAnimationTicks = animationTicks;
			findEnemyToAttack();
			spawnTicks--;
			if (active) {
				if (animationTicks == 0)
					getWorld().playSound(null, pos, ModSounds.ALTAR_CHANGE_STATE, SoundCategory.BLOCKS, 1.0F, 1.3F);
				if (animationTicks <= 24)
					animationTicks++;
			}
			if (!active) {
				if (animationTicks == 25)
					getWorld().playSound(null, pos, ModSounds.ALTAR_CHANGE_STATE, SoundCategory.BLOCKS, 1.0F, 1.3F);
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
			if (animationTicks == 6)
				bigLove(getWorld(), getPos());
		}
	}

	public void bigLove(World world, BlockPos pos) {
		if (world.isRemote) {
			double d0 = pos.getX() + 0.53125F;
			double d1 = pos.getY() + 1.25F;
			double d2 = pos.getZ() + 0.53125F;
			Erebus.PROXY.spawnCustomParticle("heart", world, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("heart", world, d0, d1, d2 - 0.265625, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("heart", world, d0, d1, d2 + 0.265625, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("heart", world, d0 - 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("heart", world, d0 + 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("heart", world, d0, d1 + 0.25, d2, 0.0D, 0.0D, 0.0D);
			Erebus.PROXY.spawnCustomParticle("heart", world, d0, d1 + 0.5, d2, 0.0D, 0.0D, 0.0D);
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
		List<EntityLivingBase> list = getWorld().getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(getPos()).grow(4D, 2D, 4D));
		if (active)
			for (int i = 0; i < list.size(); i++) {
				Entity entity = list.get(i);
				if (entity != null)
					if (entity instanceof EntityPlayer)
						((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1 * 20, 0));
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
