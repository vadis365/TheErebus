package erebus.core.handler;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.ModBlocks;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityBones;

public class EntityDeathInventoryHandler {

	private static final List<OffsetPos> offsets = new LinkedList<OffsetPos>();

	static {
		offsets.add(new OffsetPos(0, 0, 0));
		offsets.add(new OffsetPos(0, 1, 0));
		offsets.add(new OffsetPos(0, -1, 0));

		offsets.add(new OffsetPos(1, 0, 0));
		offsets.add(new OffsetPos(1, 0, 1));
		offsets.add(new OffsetPos(-1, 0, 1));
		offsets.add(new OffsetPos(-1, 0, -1));

		offsets.add(new OffsetPos(1, 1, 0));
		offsets.add(new OffsetPos(1, 1, 1));
		offsets.add(new OffsetPos(-1, 1, 1));
		offsets.add(new OffsetPos(-1, 1, -1));

		offsets.add(new OffsetPos(1, -1, 0));
		offsets.add(new OffsetPos(1, -1, 1));
		offsets.add(new OffsetPos(-1, -1, 1));
		offsets.add(new OffsetPos(-1, -1, -1));
	}

	@SuppressWarnings("unchecked")
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onPlayerDrops(PlayerDropsEvent event) {

		final List<EntityItem> drops = event.drops;
		if (drops.isEmpty()) return;

		World world = event.entityLiving.worldObj;
		if (world.isRemote)
			return;

		if (event.entityLiving instanceof EntityPlayer && !world.getGameRules().getGameRuleBooleanValue("keepInventory")) {
			final EntityPlayer player = (EntityPlayer) event.entityLiving;
			ErebusExtendedPlayerProperties playerProps = ErebusExtendedPlayerProperties.get(player);
			int x = MathHelper.floor_double(player.posX);
			int y = MathHelper.floor_double(player.posY - 1);
			int z = MathHelper.floor_double(player.posZ);
			int playerFacing = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
			byte directionMeta = 0;

			for (OffsetPos offset : offsets)
				if (world.getBlock(x + offset.x, y + offset.y, z + offset.z).isReplaceable(world, x + offset.x, y + offset.y, z + offset.z)) {
					x += offset.x;
					y += offset.y;
					z += offset.z;
					break;
				}

			if (playerFacing == 0)
				directionMeta = 2;
			if (playerFacing == 1)
				directionMeta = 5;
			if (playerFacing == 2)
				directionMeta = 3;
			if (playerFacing == 3)
				directionMeta = 4;
			world.setBlock(x, y, z, ModBlocks.bones, directionMeta, 3);
			playerProps.setDimension(player.worldObj.provider.getDimensionName());
			playerProps.setXLocation(x);
			playerProps.setZLocation(z);
			NBTTagCompound playerData = new NBTTagCompound();
			playerProps.saveNBTData(playerData);
			TileEntityBones tile = Utils.getTileEntity(world, x, y, z, TileEntityBones.class);
			if (tile != null) {
				int index = 0;
				for (int i = 0; i < drops.size(); i++) {
					if (index >= 86 || index >= drops.size())
						break;
					EntityItem entityitem = drops.get(index++);
					if (entityitem != null) {
						ItemStack stack = entityitem.getEntityItem();
						if (stack != null) {
							tile.setInventorySlotContents(i, stack.copy());
							entityitem.setDead();
						}
					}
				}
				tile.setOwner("R.I.P. " + player.getCommandSenderName());
			}
		}
	}

	static class OffsetPos {
		final int x, y, z;

		OffsetPos(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}