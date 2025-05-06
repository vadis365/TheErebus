package erebus.registries;

import erebus.Erebus;
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

public class ModFluids {
	
	public static DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, Erebus.MODID);
	public static DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, Erebus.MODID);

//	public static Fluid HONEY = new Fluid("honey", new ResourceLocation(Erebus.MODID, "fluids/honey_still"), new ResourceLocation(Erebus.MODID, "fluids/honey_flow")).setDensity(6000).setViscosity(6000).setUnlocalizedName("honey");
//	public static Fluid ANTI_VENOM = new Fluid("anti_venom", new ResourceLocation(Erebus.MODID, "fluids/anti_venom_still"), new ResourceLocation(Erebus.MODID, "fluids/anti_venom_flow")).setViscosity(2000).setUnlocalizedName("anti_venom");
//	public static Fluid BEETLE_JUICE = new Fluid("beetle_juice", new ResourceLocation(Erebus.MODID, "fluids/beetle_juice_still"), new ResourceLocation(Erebus.MODID, "fluids/beetle_juice_flow")).setUnlocalizedName("beetle_juice");
//	public static Fluid MILK = new Fluid("milk", new ResourceLocation(Erebus.MODID, "fluids/milk_still"), new ResourceLocation(Erebus.MODID, "fluids/milk_flow")).setUnlocalizedName("milk");
//	public static Fluid FORMIC_ACID = new Fluid("formic_acid", new ResourceLocation(Erebus.MODID, "fluids/formic_acid_still"), new ResourceLocation(Erebus.MODID, "fluids/formic_acid_flow")).setUnlocalizedName("formic_acid");

	public static DeferredHolder<FluidType, FluidType> BEETLE_JUICE_TYPE = FLUID_TYPES.register("beetle_juice", () -> new FluidType(FluidType.Properties.create()
			.canConvertToSource(false)
			.canDrown(false)
			.canSwim(true)
			.descriptionId("erebus.beetle_juice")
			.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
			.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL))
	{
		@Override
		public ItemStack getBucket(FluidStack stack) {
			return new ItemStack(ModItems.BEETLE_JUICE_BUCKET.get());
		}
	});

	public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> BEETLE_JUICE_STILL = FLUIDS.register("beetle_juice_still", () -> new BaseFlowingFluid.Source(ModFluids.BEETLE_JUICE_PROPERTIES));
	public static final DeferredHolder<Fluid, BaseFlowingFluid.Flowing> BEETLE_JUICE_FLOW = FLUIDS.register("beetle_juice_flowing", () -> new BaseFlowingFluid.Flowing(ModFluids.BEETLE_JUICE_PROPERTIES));
	public static final BaseFlowingFluid.Properties BEETLE_JUICE_PROPERTIES = new BaseFlowingFluid.Properties(() -> BEETLE_JUICE_TYPE.get(), () -> BEETLE_JUICE_STILL.get(), () -> BEETLE_JUICE_FLOW.get()).bucket(() -> ModItems.BEETLE_JUICE_BUCKET.get());
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

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onTextureStitchPre(TextureStitchEvent.Pre event) {
		event.getMap().registerSprite(HONEY.getStill());
		event.getMap().registerSprite(HONEY.getFlowing());
		event.getMap().registerSprite(ANTI_VENOM.getStill());
		event.getMap().registerSprite(ANTI_VENOM.getFlowing());
		event.getMap().registerSprite(BEETLE_JUICE.getStill());
		event.getMap().registerSprite(BEETLE_JUICE.getFlowing());
		event.getMap().registerSprite(FORMIC_ACID.getStill());
		event.getMap().registerSprite(FORMIC_ACID.getFlowing());
		event.getMap().registerSprite(MILK.getStill());
		event.getMap().registerSprite(MILK.getFlowing());
	} */
}