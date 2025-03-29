package erebus.entity.projectile;

import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThrownBlockAsItem  extends ThrowableProjectile implements ItemSupplier {
	private static final EntityDataAccessor<BlockState> TYPE = SynchedEntityData.defineId(ThrownBlockAsItem.class, EntityDataSerializers.BLOCK_STATE);
	private float damage; // not needed but will leave for now - just in case...
	private SoundEvent placedSound;
	public ThrownBlockAsItem(Level level) {
		super(ModEntities.THROWN_BLOCK_AS_ITEM.get(), level);
	}

	public ThrownBlockAsItem(EntityType<ThrownBlockAsItem> type, Level level) {
		super(type, level);
	}
	
	public ThrownBlockAsItem(Level level, Entity owner, BlockState state, float damageCaused, SoundEvent placedSoundIn) {
		super(ModEntities.THROWN_BLOCK_AS_ITEM.get(), level);
		this.setOwner(owner);
		setXRot(owner.getXRot());
		setYRot(owner.getYRot());
		setBlockType(state);
		damage = damageCaused;
		placedSound = placedSoundIn;
	}
	
	public ThrownBlockAsItem(double x, double y, double z, Level level) {
		super(ModEntities.THROWN_BLOCK_AS_ITEM.get(), x, y, z, level);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(TYPE, Blocks.STONE.defaultBlockState());
	}

	protected SoundEvent getPlacedSound() {
		return placedSound != null ? placedSound : getBlockType().getSoundType(level(), blockPosition(), null).getPlaceSound();
	}

	@Override
	protected void onHit(HitResult result) {
		HitResult.Type typeOfHit = result.getType();
		if (!level().isClientSide()) {
			if (typeOfHit == HitResult.Type.ENTITY) {
				EntityHitResult entityhitresult = (EntityHitResult) result;
				BlockPos entityPos = entityhitresult.getEntity().blockPosition();
				if (level().getBlockState(entityPos).isAir())
					level().setBlockAndUpdate(entityPos, getBlockType());
				else
					level().levelEvent(null, 2001, blockPosition(), Block.getId(getBlockType()));
			} else {
				if (level().getBlockState(blockPosition()).isAir())
					level().setBlockAndUpdate(blockPosition(), getBlockType());
				else
					level().levelEvent(null, 2001, blockPosition(), Block.getId(getBlockType()));
			}
			kill();
			
			level().playSound(null, blockPosition(), getPlacedSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
		}
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	public boolean attackEntityFrom(DamageSource source, int par2) {
		return false;
	}

	public void setBlockType(BlockState state ) {
		entityData.set(TYPE, state);
	}

	public BlockState getBlockType() {
		return entityData.get(TYPE);
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(getBlockType().getBlock());
	}
}