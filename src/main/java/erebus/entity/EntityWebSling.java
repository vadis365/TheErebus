package erebus.entity;

import erebus.ModBlocks;
import erebus.ModSounds;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityWebSling extends EntityThrowable {
	private static final DataParameter<Byte> TYPE = EntityDataManager.<Byte>createKey(EntityWebSling.class, DataSerializers.BYTE);
	
	public EntityWebSling(World world) {
		super(world);
		setSize(1F, 1F);
	}

	public EntityWebSling(World world, EntityLiving entity) {
		super(world, entity);
	}

	public EntityWebSling(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityWebSling(World world, EntityPlayer player) {
		super(world, player);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(TYPE, new Byte((byte) 0));
	}

	protected SoundEvent getWebSlingSplatSound() {
		return ModSounds.WEBSLING_SPLAT;
	}

	@Override
	protected void onImpact(RayTraceResult mop) {
		byte type = getType();

		if (!getEntityWorld().isRemote) {
			if (mop.entityHit != null) {
				if (Blocks.WEB.canPlaceBlockAt(getEntityWorld(), getPosition()))
					if (type == 0)
						getEntityWorld().setBlockState(getPosition(), Blocks.WEB.getDefaultState());
					else if (type == 1)
						getEntityWorld().setBlockState(getPosition(), ModBlocks.WITHER_WEB.getDefaultState());
				if (Blocks.FIRE.canPlaceBlockAt(getEntityWorld(), getPosition()))
					if (type == 2)
						mop.entityHit.setFire(10);
			} else {
				if (Blocks.WEB.canPlaceBlockAt(getEntityWorld(), getPosition()))
					if (type == 0)
						getEntityWorld().setBlockState(getPosition(), Blocks.WEB.getDefaultState());
					else if (type == 1)
						getEntityWorld().setBlockState(getPosition(), ModBlocks.WITHER_WEB.getDefaultState());
				if (Blocks.FIRE.canPlaceBlockAt(getEntityWorld(), getPosition()))
					if (type == 2)
						getEntityWorld().setBlockState(getPosition(), Blocks.FIRE.getDefaultState());
			}
			if (!getEntityWorld().isRemote)
				setDead();
		}
		if (type != 2)
			getEntityWorld().playSound((EntityPlayer)null, getPosition(), getWebSlingSplatSound(), SoundCategory.HOSTILE, 1.0F, 1.0F);
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	public boolean attackEntityFrom(DamageSource source, int par2) {
		return false;
	}

	public void setType(byte webType) {
		dataManager.set(TYPE, webType);
	}

	public byte getType() {
		return dataManager.get(TYPE);
	}
}