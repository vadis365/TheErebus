package erebus.block;

import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityJarOHoney;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

public class JarOHoney extends BlockGlowingJar {

	public JarOHoney() {
		super();
		setLightLevel(0);
		setHardness(0.5F);
		setBlockName("erebus.jarOHoney");
		setBlockTextureName("erebus:glassAmber");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityJarOHoney();
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		TileEntityJarOHoney tile = Utils.getTileEntity(world, x, y, z, TileEntityJarOHoney.class);
		if (tile != null)
			if (player instanceof EntityPlayer)
				tile.setOwner(((EntityPlayer) player).getCommandSenderName());
			else
				tile.setOwner("Boo Boo");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		TileEntityJarOHoney tile = Utils.getTileEntity(world, x, y, z, TileEntityJarOHoney.class);
		if (tile == null)
			return false;

		ItemStack stack = player.getCurrentEquippedItem();
		if (FluidContainerRegistry.isFilledContainer(stack)) {
			FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(stack);
			if (fluidStack.isFluidEqual(tile.getHoney()))
				if (fluidStack.amount + tile.getHoney().amount > TileEntityJarOHoney.HONEY_MAX_AMOUNT)
					return true;
				else {
					tile.addHoney(fluidStack.amount);
					ItemStack container = stack.getItem().getContainerItem(stack);
					stack.stackSize--;
					if (stack.stackSize <= 0)
						player.setCurrentItemOrArmor(0, container);
					else if (!player.inventory.addItemStackToInventory(container))
						Utils.dropStack(world, x, y, z, container);

					return true;
				}
		} else if (FluidContainerRegistry.isEmptyContainer(stack)) {
			ItemStack filledContainer = FluidContainerRegistry.fillFluidContainer(tile.getHoney(), stack);
			if (filledContainer != null) {
				stack.stackSize--;
				if (stack.stackSize <= 0)
					player.setCurrentItemOrArmor(0, filledContainer);
				else if (!player.inventory.addItemStackToInventory(filledContainer))
					Utils.dropStack(world, x, y, z, filledContainer);

				tile.drainHoney(FluidContainerRegistry.getFluidForFilledItem(filledContainer).amount);
				return true;
			}
		} else {
			player.getFoodStats().addStats(1, 0.8F);
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 100, 1));
			tile.drainHoney(100);
		}
		return false;
	}
}