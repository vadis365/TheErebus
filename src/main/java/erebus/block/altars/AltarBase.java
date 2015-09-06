package erebus.block.altars;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.tileentity.TileEntityErebusAltarEmpty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class AltarBase extends AltarAbstract {

	private String message;

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityErebusAltarEmpty();
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity) {
		double offsetY = 0.9D;
		if (entity instanceof EntityItem)
			if (entity.getBoundingBox().minY >= pos.getY() + offsetY) {
				ItemStack is = ((EntityItem) entity).getEntityItem();

				if (is.getItem() == ModItems.materials && isValidOffering(is.getItemDamage()))
					if (((EntityItem) entity).getAge() > 20) {
						chooseAltar(world, pos, is.getItemDamage());
						entity.setDead();
						world.playSoundEffect(entity.posX, entity.posY, entity.posZ, "erebus:altaroffering", 0.2F, 1.0F);
						world.spawnParticle(EnumParticleTypes.FLAME, entity.posX, entity.posY + 0.5D, entity.posZ, 0.0D, 0.0D, 0.0D);
						world.spawnParticle(EnumParticleTypes.CLOUD, entity.posX, entity.posY + 0.5D, entity.posZ, 0.0D, 0.0D, 0.0D);
						if (world.isRemote)
							Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(message.toString()));
					}
			}
	}

	private boolean isValidOffering(int damage) {
		return damage == 8 || damage == 9 || damage == 12 || damage == 13;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack is) {
		int rot = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		world.setBlockState(pos, getStateFromMeta(rot == 0 ? 2 : rot == 1 ? 5 : rot == 2 ? 3 : 4), 2);
	}

	private void chooseAltar(World world, BlockPos pos, int metadata) {
		switch (metadata) {
			case 8:
				if (!world.isRemote)
					world.setBlockState(pos, ModBlocks.altarXP.getStateFromMeta(8), 3);
				if (world.isRemote)
					message = StatCollector.translateToLocal("altar.experience.message");
				break;

			case 9:
				if (!world.isRemote)
					world.setBlockState(pos, ModBlocks.altarRepair.getStateFromMeta(9), 3);
				if (world.isRemote)
					message = StatCollector.translateToLocal("altar.repair.message");
				break;

			case 12:
				if (!world.isRemote)
					world.setBlockState(pos, ModBlocks.altarLightning.getStateFromMeta(12), 3);
				if (world.isRemote)
					message = StatCollector.translateToLocal("altar.lightning.message");
				break;

			case 13:
				if (!world.isRemote)
					world.setBlockState(pos, ModBlocks.altarHealing.getStateFromMeta(13), 3);
				if (world.isRemote)
					message = StatCollector.translateToLocal("altar.healing.message");
				break;
		}
	}
}