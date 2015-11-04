package erebus.item;

import erebus.ModTabs;
import erebus.entity.EntityWorkerBee;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHornOfSummoning extends Item {

	public ItemHornOfSummoning() {
		setMaxStackSize(1);
		setCreativeTab(ModTabs.specials);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack is) {
		return EnumAction.block;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack is) {
		return 30;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		player.setItemInUse(is, getMaxItemUseDuration(is));
		world.playSoundAtEntity(player, "erebus:hornblow", 1.0F, 2.0F);
		return is;
	}

	@Override
	public ItemStack onEaten(ItemStack is, World world, EntityPlayer player) {
		--is.stackSize;
		summonBees(is, world, player);
		return is;
	}

	protected void summonBees(ItemStack is, World world, EntityPlayer player) {
		if (!world.isRemote) {
			for (int a = -3; a < world.rand.nextInt(6); a++) {
				EntityWorkerBee bee = new EntityWorkerBee(world);
				bee.setPosition(player.posX, player.posY + 3, player.posZ);
				world.spawnEntityInWorld(bee);
			}
		}
	}
}