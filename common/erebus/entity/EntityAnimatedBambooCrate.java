package erebus.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.ErebusMod;
import erebus.ModItems;
import erebus.core.proxy.CommonProxy;
import erebus.tileentity.TileEntityBambooCrate;

public class EntityAnimatedBambooCrate extends EntityAnimatedChest {

	public EntityAnimatedBambooCrate(World world) {
		super(world);

	}

	@Override
	public boolean interact(EntityPlayer player) {
		if (worldObj.isRemote)
			return true;
		ItemStack is = player.inventory.getCurrentItem();
		if (is != null && is.itemID == ModItems.wandOfAnimation.itemID) {
			setDead();
			worldObj.playSoundEffect(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), "erebus:altaroffering", 0.2F, 1.0F);
			worldObj.setBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), blockID, blockMeta, 3);
			TileEntityBambooCrate chest = (TileEntityBambooCrate) worldObj.getBlockTileEntity(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
			for (int i = 0; i < 27; i++)
				chest.setInventorySlotContents(i, inventory[i]);
			return true;
		} else if (is == null) {
			player.openGui(ErebusMod.instance, CommonProxy.GUI_ID_ANIMATED_BAMBOO_CRATE, player.worldObj, entityId, 0, 0);
			return true;
		} else
			return false;
	}

	public TileEntityBambooCrate getTile() {
		TileEntityBambooCrate crate = new TileEntityBambooCrate();
		for (int i = 0; i < 27; i++)
			crate.setInventorySlotContents(i, inventory[i]);
		return crate;
	}
}