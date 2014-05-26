package erebus.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.tileentity.TileEntityErebusAltarEmpty;

public class BlockErebusAltar extends BlockContainer {

	private String message;

	public BlockErebusAltar() {
		super(Material.rock);
		setBlockTextureName("erebus:blockErebusAltarBreak");
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityErebusAltarEmpty();
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) || BlockFence.func_149825_a(world.getBlock(x, y - 1, z));
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		message = "Place Erebus item offerings on this altar. Then activate with The Wand of Animation.";
		if (world.isRemote) {
			Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(message.toString()));
			return true;
		}
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		float f = 0.0625F;
		return AxisAlignedBB.getBoundingBox(i + f, j, k + f, i + 1 - f, j + 1 - f, k + 1 - f);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		double offsetY = 0.9D;
		if (entity instanceof EntityItem)
			if (entity.boundingBox.minY >= y + offsetY) {
				ItemStack is = ((EntityItem) entity).getEntityItem();

				if (is.getItem() == ModItems.erebusMaterials && isValidOffering(is.getItemDamage()))
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
					world.setBlock(x, y, z, ModBlocks.erebusAltarXP, world.getBlockMetadata(x, y, z), 3);
				if (world.isRemote)
					message = "Altar of Experience Summoned.";
				break;

			case 9:
				if (!world.isRemote)
					world.setBlock(x, y, z, ModBlocks.erebusAltarRepair, world.getBlockMetadata(x, y, z), 3);
				if (world.isRemote)
					message = "Altar of Repair Summoned.";
				break;

			case 12:
				if (!world.isRemote)
					world.setBlock(x, y, z, ModBlocks.erebusAltarLightning, world.getBlockMetadata(x, y, z), 3);
				if (world.isRemote)
					message = "Altar of Lightning Summoned.";
				break;

			case 13:
				if (!world.isRemote)
					world.setBlock(x, y, z, ModBlocks.erebusAltarHealing, world.getBlockMetadata(x, y, z), 3);
				if (world.isRemote)
					message = "Altar of Healing Summoned.";
				break;
		}
	}
}