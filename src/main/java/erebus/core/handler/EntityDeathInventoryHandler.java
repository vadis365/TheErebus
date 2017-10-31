package erebus.core.handler;

import java.util.List;

import erebus.ModBlocks;
import erebus.blocks.BlockBones;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityBones;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityDeathInventoryHandler {
	@SuppressWarnings("unchecked")
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onPlayerDrops(PlayerDropsEvent event) {

		final List<EntityItem> drops = event.getDrops();
		if (drops.isEmpty()) return;

		World world = event.getEntityLiving().world;
		if (world.isRemote)
			return;

		if (event.getEntityLiving() instanceof EntityPlayer && !world.getGameRules().getBoolean("keepInventory")) {
			final EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			//ErebusExtendedPlayerProperties playerProps = ErebusExtendedPlayerProperties.get(player);
			BlockPos pos = player.getPosition().up();
			BlockPos posBones = player.getPosition();
			EnumFacing playerFacing = player.getHorizontalFacing();

			for (EnumFacing offset : EnumFacing.VALUES)
				if (world.getBlockState(pos.offset(offset)).getBlock().isReplaceable(world, pos.offset(offset))) {
					posBones = pos.offset(offset);
					break;
				}

			world.setBlockState(posBones, ModBlocks.BLOCK_OF_BONES.getDefaultState().withProperty(BlockBones.FACING, playerFacing), 3);
		//	playerProps.setDimension(player.world.provider.getDimension());
		//	playerProps.setXLocation(x);
		//	playerProps.setZLocation(z);
		//	NBTTagCompound playerData = new NBTTagCompound();
		//	playerProps.saveNBTData(playerData);
			TileEntityBones tile = Utils.getTileEntity(world, new BlockPos(posBones), TileEntityBones.class);
			if (tile != null) {
				int index = 0;
				for (int i = 0; i < drops.size(); i++) {
					if (index >= 86 || index >= drops.size())
						break;
					EntityItem entityitem = drops.get(index++);
					if (entityitem != null) {
						ItemStack stack = entityitem.getItem();
						if (stack != null) {
							tile.setInventorySlotContents(i, stack.copy());
							entityitem.setDead();
						}
					}
				}
				tile.setOwner("R.I.P. " + player.getCommandSenderEntity().getName());
				event.setCanceled(true);
			}
		}
	}
}