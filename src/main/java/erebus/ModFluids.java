package erebus;

import java.lang.reflect.Field;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids {

	public static final ModFluids INSTANCE = new ModFluids();

	public static Fluid honey = new Fluid("honey").setDensity(6000).setViscosity(6000).setUnlocalizedName("honey");
	public static Fluid antiVenom = new Fluid("antiVenom").setViscosity(2000).setUnlocalizedName("antiVenom");
	public static Fluid beetleJuice = new Fluid("beetleJuice").setUnlocalizedName("beetleJuice");
	public static Fluid milk = new Fluid("milk").setUnlocalizedName("milk");

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
					else
						FluidRegistry.registerFluid(fluid);
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		FluidContainerRegistry.registerFluidContainer(FluidRegistry.WATER, new ItemStack(ModItems.bambucketWater), new ItemStack(ModItems.bambucket));
		FluidContainerRegistry.registerFluidContainer(honey, new ItemStack(ModItems.bambucketHoney), new ItemStack(ModItems.bambucket));
		FluidContainerRegistry.registerFluidContainer(antiVenom, new ItemStack(ModItems.bambucketAntiVenom), new ItemStack(ModItems.bambucket));
		FluidContainerRegistry.registerFluidContainer(beetleJuice, new ItemStack(ModItems.bambucketBeetleJuice), new ItemStack(ModItems.bambucket));

		FluidContainerRegistry.registerFluidContainer(honey, new ItemStack(ModItems.bucketHoney), new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(beetleJuice, new ItemStack(ModItems.bucketBeetleJuice), new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(antiVenom, new ItemStack(ModItems.bucketAntiVenom), new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(milk, new ItemStack(Items.milk_bucket), new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(antiVenom, new ItemStack(ModItems.bottleAntiVenom), new ItemStack(Items.glass_bottle));
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void postStitch(TextureStitchEvent.Pre event) {
		if (event.map.getTextureType() == 0) {
			honey.setIcons(ModBlocks.honeyBlock.getBlockTextureFromSide(0), ModBlocks.honeyBlock.getBlockTextureFromSide(1));
			antiVenom.setIcons(event.map.registerIcon("erebus:antiVenom"), event.map.registerIcon("erebus:antiVenomFlow"));
			beetleJuice.setIcons(event.map.registerIcon("erebus:beetleJuice"), event.map.registerIcon("erebus:beetleJuiceFlow"));
		}
	}
}