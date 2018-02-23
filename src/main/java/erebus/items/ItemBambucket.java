package erebus.items;

import javax.annotation.Nullable;

import erebus.ModItems;
import erebus.ModTabs;
import erebus.core.helper.FluidHandlerUniversalBucket;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBotFlyLarva;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemBambucket extends UniversalBucket {
	
	private final ItemStack empty = new ItemStack(this);
	
	public ItemBambucket() {
		super(Fluid.BUCKET_VOLUME, new ItemStack(ModItems.BAMBUCKET), true);
		setMaxStackSize(16);
		setCreativeTab(ModTabs.ITEMS);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
		FluidStack fluidStack = getFluid(stack);
		if(fluidStack != null)
			return false;
		ItemStack newStack = player.getHeldItem(hand).splitStack(1);

		IFluidHandlerItem cap = newStack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
		if (target instanceof EntityCow && !target.isChild()) {
			cap.fill(new FluidStack(FluidRegistry.getFluid("milk"), Fluid.BUCKET_VOLUME), true);
			player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
            if (!player.inventory.addItemStackToInventory(newStack))
                player.dropItem(newStack, false);
			return true;
		}
		if (target instanceof EntityBeetle) {
			cap.fill(new FluidStack(FluidRegistry.getFluid("beetle_juice"), Fluid.BUCKET_VOLUME), true);
			player.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
            if (!player.inventory.addItemStackToInventory(newStack)) {
                player.dropItem(newStack, false);
                System.out.println("Dropped Stack is: " + newStack.getItem());
            }
            else
            	 System.out.println("Inventory Stack is: " + newStack.getItem());
			return true;
		}
		return false;
	}

	@Override
	public void getSubItems(@Nullable final CreativeTabs tab, final NonNullList<ItemStack> subItems) {
		if (!this.isInCreativeTab(tab)) return;

		subItems.add(empty);

		for (final Fluid fluid : FluidRegistry.getRegisteredFluids().values()) {
			// Add all fluids that the bucket can be filled with
			final FluidStack fs = new FluidStack(fluid, getCapacity());
			final ItemStack stack = new ItemStack(this);
			final IFluidHandlerItem fluidHandler = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
			if (fluidHandler != null && fluidHandler.fill(fs, true) == fs.amount) {
				final ItemStack filled = fluidHandler.getContainer();
				subItems.add(filled);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getItemStackDisplayName(final ItemStack stack) {
		final FluidStack fluidStack = getFluid(stack);
		final String unlocalisedName = this.getUnlocalizedNameInefficiently(stack);

		if (fluidStack == null)
			return I18n.translateToLocal(unlocalisedName + ".name").trim();

		final String fluidUnlocalisedName = unlocalisedName + ".filled." + fluidStack.getFluid().getName() + ".name";
		if (I18n.canTranslate(fluidUnlocalisedName))
			return I18n.translateToLocal(fluidUnlocalisedName);

		return I18n.translateToLocalFormatted(unlocalisedName + ".filled.name", fluidStack.getLocalizedName());
	}

	@Override
    public int getMaxItemUseDuration(ItemStack stack) {
		return isDrinkableFluid(stack) ? 32 : 0;
    }

	@Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return isDrinkableFluid(stack) ? EnumAction.DRINK : EnumAction.NONE;
    }

	public boolean isDrinkableFluid(ItemStack stack) {
		FluidStack fluidStack = getFluid(stack);
		if(fluidStack != null)
			if(fluidStack.getFluid() == FluidRegistry.getFluid("beetle_juice") || fluidStack.getFluid() == FluidRegistry.getFluid("anti_venom") || fluidStack.getFluid() == FluidRegistry.getFluid("milk"))
			return true;
		return false;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(final World world, final EntityPlayer player, final EnumHand hand) {
		final ItemStack heldItem = player.getHeldItem(hand);
		final FluidStack fluidStack = getFluid(heldItem);
		if(world.isRemote)
			return new ActionResult<>(EnumActionResult.PASS, heldItem);
		if (isDrinkableFluid(heldItem)) {
			player.setActiveHand(hand);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, heldItem);
		}
		
		final RayTraceResult target = this.rayTrace(world, player, true);
		if (target.typeOfHit == RayTraceResult.Type.MISS)
			return new ActionResult<>(EnumActionResult.PASS, heldItem);

		final BlockPos pos = target.getBlockPos();	

		if (fluidStack != null) {
			world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
			return super.onItemRightClick(world, player, hand);
		}

		if (target.typeOfHit != RayTraceResult.Type.MISS || target == null || target.typeOfHit != RayTraceResult.Type.BLOCK)
			return new ActionResult<>(EnumActionResult.PASS, heldItem);
		

		final ItemStack singleBucket = heldItem.copy();
		singleBucket.setCount(1);

		final FluidActionResult filledResult = FluidUtil.tryPickUpFluid(singleBucket, player, world, pos, target.sideHit);
		if (filledResult.isSuccess()) {
            world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
			final ItemStack filledBucket = filledResult.result;
			if (player.capabilities.isCreativeMode)
				return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);
			heldItem.shrink(1);
			if (heldItem.isEmpty())
				return new ActionResult<>(EnumActionResult.SUCCESS, filledBucket);
			ItemHandlerHelper.giveItemToPlayer(player, filledBucket);
			return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);
		}
		return new ActionResult<>(EnumActionResult.PASS, heldItem);
	}

	@Override
	public ItemStack getEmpty() {
		return empty;
	}

	@Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entityLiving) {
		FluidStack fluidStack = getFluid(stack);
		ItemStack newStack = stack.splitStack(1);
		if (fluidStack.getFluid() == FluidRegistry.getFluid("beetle_juice") || fluidStack.getFluid() == FluidRegistry.getFluid("milk")) {
			if (!world.isRemote)
				entityLiving.curePotionEffects(stack);

			if (entityLiving.isBeingRidden() && entityLiving.getLowestRidingEntity() instanceof EntityBotFlyLarva)
				if (((EntityBotFlyLarva) entityLiving.getLowestRidingEntity()).getParasiteCount() > 0)
					((EntityBotFlyLarva) entityLiving.getLowestRidingEntity()).setABitDead();
		}

		if (fluidStack.getFluid() == FluidRegistry.getFluid("anti_venom")) {
			if (entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entityLiving;
				if(!player.getEntityData().hasKey("anti_venom_duration") || player.getEntityData().getInteger("anti_venom_duration") < 180)
					player.getEntityData().setInteger("anti_venom_duration", 180);
			}
		}

		if (entityLiving instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP) entityLiving;
			CriteriaTriggers.CONSUME_ITEM.trigger(player, stack);
			player.addStat(StatList.getObjectUseStats(this));
		}

		IFluidHandlerItem cap = newStack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
		cap.drain(new FluidStack(getFluid(newStack), Fluid.BUCKET_VOLUME), true);
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
		  if (!player.inventory.addItemStackToInventory(newStack))
              player.dropItem(newStack, false);
		}
		return stack.getCount() == 0 && hasContainerItem(stack) ? getContainerItem(stack) : stack;
    }

	@Nullable
	@Override
	public FluidStack getFluid(final ItemStack container) {
		return FluidUtil.getFluidContained(container);
	}

	@Override
	public ICapabilityProvider initCapabilities(final ItemStack stack, final NBTTagCompound nbt) {
		return new FluidHandlerUniversalBucket(stack, getCapacity());
	}
}