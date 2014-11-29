package erebus.block.altars;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.tileentity.TileEntityErebusAltarEmpty;

public class AltarBase extends AltarAbstract {
	private String message;

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityErebusAltarEmpty();
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		double offsetY = 0.9D;
		if (entity instanceof EntityItem)
			if (entity.boundingBox.minY >= y + offsetY) {
				ItemStack is = ((EntityItem) entity).getEntityItem();

				if (is.getItem() == ModItems.materials && isValidOffering(is.getItemDamage()))
					if (((EntityItem) entity).age > 20) {
						chooseAltar(world, x, y, z, is.getItemDamage());
						entity.setDead();
						world.playSoundEffect(entity.posX, entity.posY, entity.posZ, "erebus:altaroffering", 0.2F, 1.0F);
						world.spawnParticle("flame", entity.posX, entity.posY + 0.5D, entity.posZ, 0.0D, 0.0D, 0.0D);
						world.spawnParticle("cloud", entity.posX, entity.posY + 0.5D, entity.posZ, 0.0D, 0.0D, 0.0D);
						if (world.isRemote)
							Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(message.toString()));
					}
			}
	}

	private boolean isValidOffering(int damage) {
		return damage == 8 || damage == 9 || damage == 12 || damage == 13;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
		int rot = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, rot == 0 ? 2 : rot == 1 ? 5 : rot == 2 ? 3 : 4, 2);
	}

	private void chooseAltar(World world, int x, int y, int z, int damage) {
		switch (damage) {
			case 8:
				if (!world.isRemote)
					world.setBlock(x, y, z, ModBlocks.altarXP, world.getBlockMetadata(x, y, z), 3);
				if (world.isRemote)
					message = "Altar of Experience Summoned.";
				break;

			case 9:
				if (!world.isRemote)
					world.setBlock(x, y, z, ModBlocks.altarRepair, world.getBlockMetadata(x, y, z), 3);
				if (world.isRemote)
					message = "Altar of Repair Summoned.";
				break;

			case 12:
				if (!world.isRemote)
					world.setBlock(x, y, z, ModBlocks.altarLightning, world.getBlockMetadata(x, y, z), 3);
				if (world.isRemote)
					message = "Altar of Lightning Summoned.";
				break;

			case 13:
				if (!world.isRemote)
					world.setBlock(x, y, z, ModBlocks.altarHealing, world.getBlockMetadata(x, y, z), 3);
				if (world.isRemote)
					message = "Altar of Healing Summoned.";
				break;
		}
	}
}