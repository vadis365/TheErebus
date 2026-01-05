package erebus.registries;

import erebus.Erebus;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class ModFluids {
	
	public static DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, Erebus.MODID);
	public static DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, Erebus.MODID);

	public static DeferredHolder<FluidType, FluidType> BEETLE_JUICE_TYPE = FLUID_TYPES.register("beetle_juice", () -> new FluidType(FluidType.Properties.create()
			.canConvertToSource(false)
			.canDrown(true)
			.canSwim(true)
			.descriptionId("erebus.beetle_juice")
			.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
			.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL))
		{
		@Override
		public @NotNull ItemStack getBucket(@NotNull FluidStack stack) {
			return new ItemStack(ModItems.BEETLE_JUICE_BUCKET.get());
		}
	});

	public static DeferredHolder<FluidType, FluidType> FORMIC_ACID_TYPE = FLUID_TYPES.register("formic_acid", () -> new FluidType(FluidType.Properties.create()
			.canConvertToSource(false)
			.canDrown(true)
			.canSwim(true)
			.lightLevel(11)
			.canExtinguish(false)
			.descriptionId("erebus.formic_acid")
			.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
			.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL))
		{
			@Override
			public @NotNull ItemStack getBucket(@NotNull FluidStack stack) {
				return new ItemStack(ModItems.FORMIC_ACID_BUCKET.get());
			}
	});

	public static DeferredHolder<FluidType, FluidType> HONEY_TYPE = FLUID_TYPES.register("honey", () -> new FluidType(FluidType.Properties.create()
			.canConvertToSource(false)
			.canDrown(true)
			.canSwim(true)
			.viscosity(6000)
			.canExtinguish(false)
			.descriptionId("erebus.honey")
			.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
			.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL))
		{
			@Override
			public @NotNull ItemStack getBucket(@NotNull FluidStack stack) {
				return new ItemStack(ModItems.HONEY_BUCKET.get());
		}
	});

	public static DeferredHolder<FluidType, FluidType> ANTI_VENOM_TYPE = FLUID_TYPES.register("anti_venom", () -> new FluidType(FluidType.Properties.create()
			.canConvertToSource(false)
			.canDrown(true)
			.canSwim(true)
			.viscosity(2000)
			.descriptionId("erebus.anti_venom")
			.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
			.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL))
		{
			@Override
			public @NotNull ItemStack getBucket(@NotNull FluidStack stack) {
				return new ItemStack(ModItems.ANTI_VENOM_BUCKET.get());
		}
	});

	public static final DeferredHolder<Fluid, BaseFlowingFluid> HONEY_STILL = FLUIDS.register("honey_still", () -> new BaseFlowingFluid.Source(ModFluids.HONEY_PROPERTIES));
	public static final DeferredHolder<Fluid, BaseFlowingFluid> HONEY_FLOW = FLUIDS.register("honey_flowing", () -> new BaseFlowingFluid.Flowing(ModFluids.HONEY_PROPERTIES));
	public static final BaseFlowingFluid.Properties HONEY_PROPERTIES = new BaseFlowingFluid.Properties(() -> HONEY_TYPE.get(), HONEY_STILL, HONEY_FLOW).block(ModBlocks.FLUID_HONEY_BLOCK).bucket(ModItems.HONEY_BUCKET);

	public static final DeferredHolder<Fluid, BaseFlowingFluid> ANTI_VENOM_STILL = FLUIDS.register("anti_venom_still", () -> new BaseFlowingFluid.Source(ModFluids.ANTI_VENOM_PROPERTIES));
	public static final DeferredHolder<Fluid, BaseFlowingFluid> ANTI_VENOM_FLOW = FLUIDS.register("anti_venom_flowing", () -> new BaseFlowingFluid.Flowing(ModFluids.ANTI_VENOM_PROPERTIES));
	public static final BaseFlowingFluid.Properties ANTI_VENOM_PROPERTIES = new BaseFlowingFluid.Properties(() -> ANTI_VENOM_TYPE.get(), ANTI_VENOM_STILL, ANTI_VENOM_FLOW).block(ModBlocks.FLUID_ANTI_VENOM_BLOCK).bucket(ModItems.ANTI_VENOM_BUCKET);

	public static final DeferredHolder<Fluid, BaseFlowingFluid> BEETLE_JUICE_STILL = FLUIDS.register("beetle_juice_still", () -> new BaseFlowingFluid.Source(ModFluids.BEETLE_JUICE_PROPERTIES));
	public static final DeferredHolder<Fluid, BaseFlowingFluid> BEETLE_JUICE_FLOW = FLUIDS.register("beetle_juice_flowing", () -> new BaseFlowingFluid.Flowing(ModFluids.BEETLE_JUICE_PROPERTIES));
	public static final BaseFlowingFluid.Properties BEETLE_JUICE_PROPERTIES = new BaseFlowingFluid.Properties(() -> BEETLE_JUICE_TYPE.get(), BEETLE_JUICE_STILL, BEETLE_JUICE_FLOW).block(ModBlocks.FLUID_BEETLE_JUICE_BLOCK).bucket(ModItems.BEETLE_JUICE_BUCKET);

	public static final DeferredHolder<Fluid, BaseFlowingFluid> FORMIC_ACID_STILL = FLUIDS.register("formic_acid_still", () -> new BaseFlowingFluid.Source(ModFluids.FORMIC_ACID_PROPERTIES));
	public static final DeferredHolder<Fluid, BaseFlowingFluid> FORMIC_ACID_FLOW = FLUIDS.register("formic_acid_flowing", () -> new BaseFlowingFluid.Flowing(ModFluids.FORMIC_ACID_PROPERTIES));
	public static final BaseFlowingFluid.Properties FORMIC_ACID_PROPERTIES = new BaseFlowingFluid.Properties(() -> FORMIC_ACID_TYPE.get(), FORMIC_ACID_STILL, FORMIC_ACID_FLOW).block(ModBlocks.FLUID_FORMIC_ACID_BLOCK).bucket(ModItems.FORMIC_ACID_BUCKET);

	/*	
	@Nonnull
    public static ItemStack getFilledBambucket(@Nonnull FluidStack fluidStack) {
        Fluid fluid = fluidStack.getFluid();
        if (FluidRegistry.getBucketFluids().contains(fluid)){
            ItemStack filledBucket = new ItemStack(ModItems.BAMBUCKET);
            IFluidHandlerItem cap = filledBucket.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
            cap.fill(fluidStack, true);
            return filledBucket;
        }
        return ItemStack.EMPTY;
       
    }
    */
}
