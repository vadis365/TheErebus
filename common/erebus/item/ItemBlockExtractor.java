package erebus.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPane;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import erebus.entity.EntityExtractedBlock;

public class ItemBlockExtractor extends Item {

	public Block block;
	public int blockID;
	public int blockMeta;
	public int objectX, objectY, objectZ;
	public float blockHardness;

	public ItemBlockExtractor(int id) {
		super(id);
		maxStackSize = 1;
		setMaxDamage(128);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack is) {
		return EnumAction.bow;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack is) {
		float x = blockHardness * 30;
		if (x >= 150)
			x = 150;
		return (int) x;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		getBlockInfo(world, player);
		player.setItemInUse(is, getMaxItemUseDuration(is));
		return is;
	}

	public void getBlockInfo(World world, EntityPlayer player) {
		MovingObjectPosition objectMouseOver = player.rayTrace(16, 1.0F);// That method has a SideOnly(Side.CLIENT) annotation
		if (objectMouseOver != null && objectMouseOver.typeOfHit == EnumMovingObjectType.TILE) {
			objectX = objectMouseOver.blockX;
			objectY = objectMouseOver.blockY;
			objectZ = objectMouseOver.blockZ;
			blockID = world.getBlockId(objectX, objectY, objectZ);
			blockMeta = world.getBlockMetadata(objectX, objectY, objectZ);
			block = Block.blocksList[blockID];
			if (block != null)
				blockHardness = block.getBlockHardness(world, objectX, objectY, objectZ);
		}
	}

	@Override
	public ItemStack onEaten(ItemStack is, World world, EntityPlayer player) {
		// world.playSoundAtEntity(player, "erebus:someSoundHere", 1.0F, 1.0F);
		if (block != null)
			extractBlock(is, world, player);
		is.damageItem(1, player);
		return is;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack is, World world, EntityPlayer player, int count) {
		block = null;
	}

	protected void extractBlock(ItemStack is, World world, EntityPlayer player) {
		if (!world.isRemote && block != null && canExtract(block)) {
			EntityExtractedBlock entityExtractedBlock;
			entityExtractedBlock = new EntityExtractedBlock(world);
			world.setBlock(objectX, objectY, objectZ, 0);
			entityExtractedBlock.setLocationAndAngles((double) objectX + 0.5F, objectY, (double) objectZ + 0.5F, 0.0F, 0.0F);
			entityExtractedBlock.setBlock(blockID, blockMeta);
			entityExtractedBlock.setHeading(player.posX, player.posY, player.posZ);
			world.spawnEntityInWorld(entityExtractedBlock);
		}
	}

	private boolean canExtract(Block block) {
		return !(block instanceof BlockContainer) && !(block instanceof BlockPane) && block.blockHardness >= 0 && block.getBlockBoundsMaxX() - block.getBlockBoundsMinX() >= 0.7F && block.getBlockBoundsMaxZ() - block.getBlockBoundsMinZ() >= 0.7F && block.getBlockBoundsMaxY() - block.getBlockBoundsMinY() >= 0.7F;
	}
}