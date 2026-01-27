package erebus.entity;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.entity.PartEntity;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class GenericPartEntity<T extends Entity> extends PartEntity<T> {

	private final EntityDimensions size;

	public GenericPartEntity(T parent, float width, float height) {
		super(parent);
		this.size = EntityDimensions.scalable(width, height);
		this.refreshDimensions();
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder builder) {

	}

	@Override
	public boolean hurtServer(@NonNull ServerLevel serverLevel, @NonNull DamageSource damageSource, float damage) {
		return damage > 0;
	}

	@Override
	protected void readAdditionalSaveData(@NonNull ValueInput input) {

	}

	@Override
	protected void addAdditionalSaveData(@NonNull ValueOutput output) {

	}

	@Override
	public boolean isCurrentlyGlowing() {
		return this.getParent().isCurrentlyGlowing();
	}

	@Override
	public boolean isInvisible() {
		return this.getParent().isInvisible();
	}

	@Override
	public boolean isPickable() {
		return true;
	}

	@Override
	public boolean is(@NonNull Entity entity) {
		return this == entity || this.getParent() == entity;
	}

	@Override
	public @Nullable ItemStack getPickResult() {
		return this.getParent().getPickResult();
	}

	@Override
	public boolean shouldBeSaved() {
		return false;
	}

	@Override
	public @NonNull EntityDimensions getDimensions(@NonNull Pose pose) {
		return this.size;
	}

	@Override
	public boolean canUsePortal(boolean allowPassengers) {
		return false;
	}
}
