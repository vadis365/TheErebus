package erebus.block.entity;

import erebus.registries.blocks.ModBlockEntities;
import erebus.registries.data.ModDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidJarBlockEntity extends BlockEntity {
    public static final int MAX_CAPACITY = FluidType.BUCKET_VOLUME * 32;
    public final FluidStacksResourceHandler tank = new FluidStacksResourceHandler(1, MAX_CAPACITY) {
        @Override
        protected void onContentsChanged(int index, FluidStack previous) {
            setChanged();
            needsClientUpdate = true;
        }
    };
    private boolean needsClientUpdate;

    public FluidJarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FLUID_JAR.get(), pos, state);
    }

    public static <T extends BlockEntity> void serverTick(Level world, BlockPos pos, BlockState state, T entity) {
        if (entity instanceof FluidJarBlockEntity jar && jar.needsClientUpdate) {
            jar.needsClientUpdate = false;
            jar.updateBlock();
        }
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveCustomOnly(registries);
    }

    public void updateBlock() {
        getLevel().sendBlockUpdated(worldPosition, getLevel().getBlockState(worldPosition), getLevel().getBlockState(worldPosition), 3);
    }

    @Override
    public void onDataPacket(Connection net, ValueInput input) {
        super.onDataPacket(net, input);
        loadAdditional(input);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        tank.deserialize(input);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        tank.serialize(output);
    }

    public FluidStacksResourceHandler getTank() {
        return this.tank;
    }

    public FluidStacksResourceHandler getTank(@Nullable Direction ignoredDirection) {
        return this.tank;
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter getter) {
        super.applyImplicitComponents(getter);
        FluidResource resource = getter.getOrDefault(ModDataComponents.FLUID, FluidResource.EMPTY);
        if (resource.isEmpty()) return;
        int amount = Math.clamp(getter.getOrDefault(ModDataComponents.FLUID_AMOUNT, FluidType.BUCKET_VOLUME), 0, MAX_CAPACITY);
        try (var transaction = Transaction.openRoot()) {
            if (tank.insert(resource, amount, transaction) == amount) transaction.commit();
        }
    }

    @Override
    protected void collectImplicitComponents(@Nonnull DataComponentMap.Builder builder) {
        super.collectImplicitComponents(builder);
        if (!tank.getResource(0).isEmpty()) {
            builder.set(ModDataComponents.FLUID, tank.getResource(0));
            builder.set(ModDataComponents.FLUID_AMOUNT, tank.getAmountAsInt(0));
        }
    }

}
