package erebus.entity.projectile;

import erebus.registries.ModBlocks;
import erebus.registries.ModSounds;
import erebus.registries.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class WebSling  extends ThrowableProjectile implements ItemSupplier {
	private static final EntityDataAccessor<Byte> TYPE = SynchedEntityData.defineId(WebSling.class, EntityDataSerializers.BYTE);
	private float damage; // not needed but will leave for now - just in case...

	public WebSling(Level level) {
		super(ModEntities.WEB_SLING.get(), level);
	}

	public WebSling(EntityType<WebSling> type, Level level) {
		super(type, level);
	}
	
	public WebSling(Level level, Entity owner, float damageCaused) {
		super(ModEntities.WEB_SLING.get(), level);
		this.setOwner(owner);
		setXRot(owner.getXRot());
		setYRot(owner.getYRot());
		damage = damageCaused;
	}
	
	public WebSling(double x, double y, double z, Level level) {
		super(ModEntities.WEB_SLING.get(), x, y, z, level);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(TYPE, (byte) 0);
	}

	protected SoundEvent getWebSlingSplatSound() {
		return ModSounds.WEBSLING_SPLAT.get();
	}

	@Override
	protected void onHit(HitResult result) {
		HitResult.Type typeOfHit = result.getType();
		if (!level().isClientSide()) {
			if (typeOfHit == HitResult.Type.ENTITY) {
				EntityHitResult entityhitresult = (EntityHitResult) result;
				BlockPos entityPos = entityhitresult.getEntity().blockPosition();
				if (level().getBlockState(entityPos).isAir()) {
					if (getWebType() == 0)
						level().setBlockAndUpdate(entityPos, webState(getWebType()));
					else if (getWebType() == 1)
						level().setBlockAndUpdate(entityPos, webState(getWebType()));
					else if (getWebType() == 2)
						if (BaseFireBlock.canBePlacedAt(level(), entityPos, Direction.DOWN))
							entityhitresult.getEntity().setRemainingFireTicks(10);
				}
				else
					level().levelEvent(null, 2001, blockPosition(), Block.getId(webState(getWebType())));
			} else {
				if (level().getBlockState(blockPosition()).isAir()) {
					if (getWebType() == 0)
						level().setBlockAndUpdate(blockPosition(), webState(getWebType()));
					else if (getWebType() == 1)
						level().setBlockAndUpdate(blockPosition(), webState(getWebType()));
					else if (getWebType() == 2)
						if (BaseFireBlock.canBePlacedAt(level(), blockPosition(), Direction.DOWN))
							level().setBlockAndUpdate(blockPosition(), webState(getWebType()));
				}
				else
					level().levelEvent(null, 2001, blockPosition(), Block.getId(webState(getWebType())));
			}
			kill();
		}
		if (getWebType() != 2)
			level().playSound(null, blockPosition(), getWebSlingSplatSound(), SoundSource.HOSTILE, 1.0F, 1.0F);
	}
	
	public BlockState webState (byte type) {
		return type == 0 ? Blocks.COBWEB.defaultBlockState() : type == 1 ? ModBlocks.WITHER_WEB.get().defaultBlockState() : Blocks.FIRE.defaultBlockState();
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	public boolean attackEntityFrom(DamageSource source, int par2) {
		return false;
	}

	public void setWebType(byte webType) {
		entityData.set(TYPE, webType);
	}

	public byte getWebType() {
		return entityData.get(TYPE);
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(webState(getWebType()).getBlock());
	}
}