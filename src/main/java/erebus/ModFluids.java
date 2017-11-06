package erebus;

import java.lang.reflect.Field;

import javax.annotation.Nonnull;

import erebus.lib.Reference;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModFluids {

	public static final ModFluids INSTANCE = new ModFluids();

	public static Fluid HONEY = new Fluid("honey", new ResourceLocation(Reference.MOD_ID, "fluids/honey_still"), new ResourceLocation(Reference.MOD_ID, "fluids/honey_flow")).setDensity(6000).setViscosity(6000).setUnlocalizedName("honey");
	public static Fluid ANTI_VENOM = new Fluid("anti_venom", new ResourceLocation(Reference.MOD_ID, "fluids/anti_venom_still"), new ResourceLocation(Reference.MOD_ID, "fluids/anti_venom_flow")).setViscosity(2000).setUnlocalizedName("anti_venom");
	public static Fluid BEETLE_JUICE = new Fluid("beetle_juice", new ResourceLocation(Reference.MOD_ID, "fluids/beetle_juice_still"), new ResourceLocation(Reference.MOD_ID, "fluids/beetle_juice_flow")).setUnlocalizedName("beetle_juice");
	public static Fluid MILK = new Fluid("milk", new ResourceLocation(Reference.MOD_ID, "fluids/milk_still"), new ResourceLocation(Reference.MOD_ID, "fluids/milk_flow")).setUnlocalizedName("milk");
	public static Fluid FORMIC_ACID = new Fluid("formic_acid", new ResourceLocation(Reference.MOD_ID, "fluids/formic_acid_still"), new ResourceLocation(Reference.MOD_ID, "fluids/formic_acid_flow")).setUnlocalizedName("formic_acid");

	private ModFluids() {
	}

	public static void init() {
		try {
			for (Field f : ModFluids.class.getDeclaredFields()) {
				Object obj = f.get(null);
				if (obj instanceof Fluid) {
					Fluid fluid = (Fluid) obj;
					if (FluidRegistry.isFluidRegistered(fluid.getName()))
						f.set(null, FluidRegistry.getFluid(fluid.getName()));
					else {
						FluidRegistry.registerFluid(fluid);
						FluidRegistry.addBucketForFluid(fluid);
					}
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Nonnull
    public static ItemStack getFilledBambucket(@Nonnull FluidStack fluidStack){
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
	}
}