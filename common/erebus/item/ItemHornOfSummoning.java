package erebus.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.ErebusMod;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityLocust;

public class ItemHornOfSummoning extends Item {

	public ItemHornOfSummoning(int id) {
		super(id);
		maxStackSize = 1;
		setCreativeTab(ErebusMod.tabErebusGear);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack is) {
		return EnumAction.block;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack is) {
		return 60;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		player.setItemInUse(is, getMaxItemUseDuration(is));
		world.playSoundAtEntity(player, "erebus:hornblow", 1.0F, 1.0F);
		return is;
	}

	@Override
	public ItemStack onEaten(ItemStack is, World world, EntityPlayer player) {
		--is.stackSize;
		summonHoppers(is, world, player);
		return is;
	}

	protected void summonHoppers(ItemStack is, World world, EntityPlayer player) {
		if (!world.isRemote) {
			int x = world.rand.nextInt(10);
			for (int a = -3; a < world.rand.nextInt(6); a++) {
				EntityGrasshopper entityGrasshopper = new EntityGrasshopper(world);
				entityGrasshopper.setPosition(player.posX, player.posY + 3, player.posZ);
				world.spawnEntityInWorld(entityGrasshopper);
			}
			if (x == 0) {
				EntityLocust entityLocust = new EntityLocust(world);
				entityLocust.setPosition(player.posX, player.posY + 3, player.posZ);
				world.spawnEntityInWorld(entityLocust);
			}
		}
	}
}