package erebus.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPane;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import erebus.entity.EntityExtractedBlock;

public class ItemBlockExtractor extends Item {

	public Block block;
	public int blockID;
	public int blockMeta;
	public double targetX, targetY, targetZ;
	public float blockHardness;
	private boolean seekBlock;

	public ItemBlockExtractor(int id) {
		super(id);
		maxStackSize = 1;
		setMaxDamage(128);
		seekBlock=true;
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
		if(seekBlock)
			getBlockInfo(world, player);
		
		player.setItemInUse(is, getMaxItemUseDuration(is));
		return is;
	}

	public void getBlockInfo(World world, EntityPlayer player) {
		float range = 16.0F; // range of 16
		Vec3 vec3 = player.getLookVec().normalize();
		targetX = player.posX;
		targetY = player.posY + player.getEyeHeight() - 0.10000000149011612D;
		targetZ = player.posZ;
		
		while (Math.abs(targetX) < (Math.abs(player.posX) + Math.abs(vec3.xCoord * range)) && seekBlock) {
			targetX += vec3.xCoord;
			targetY += vec3.yCoord;
			targetZ += vec3.zCoord;
			if (!world.isAirBlock((int) targetX, (int) targetY, (int) targetZ)) {
				seekForBlock(false);
				blockID = world.getBlockId((int)targetX, (int)targetY, (int)targetZ);
				blockMeta = world.getBlockMetadata((int)targetX, (int)targetY, (int)targetZ);
				block = Block.blocksList[blockID];
				if (block != null)
					blockHardness = block.getBlockHardness(world, (int)targetX, (int)targetY, (int)targetZ);
			}
		}
	}
	
	private void seekForBlock(boolean state) {
		seekBlock = state;	
	}

	@Override
	public ItemStack onEaten(ItemStack is, World world, EntityPlayer player) {
		// world.playSoundAtEntity(player, "erebus:someSoundHere", 1.0F, 1.0F);
		if (block != null && canExtract(block))
			extractBlock(is, world, player);
		is.damageItem(1, player);
		return is;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack is, World world, EntityPlayer player, int count) {
		block = null;
		seekForBlock(true);
	}

	protected void extractBlock(ItemStack is, World world, EntityPlayer player) {
		if (!world.isRemote && block != null && canExtract(block)) {
			EntityExtractedBlock entityExtractedBlock;
			entityExtractedBlock = new EntityExtractedBlock(world);
			world.setBlock((int)targetX, (int)targetY, (int)targetZ, 0);
			entityExtractedBlock.setLocationAndAngles(targetX, targetY, targetZ, 0.0F, 0.0F);
			entityExtractedBlock.setBlock(blockID, blockMeta);
			entityExtractedBlock.setHeading(player.posX, player.posY, player.posZ);
			world.spawnEntityInWorld(entityExtractedBlock);
		}
		seekForBlock(true);
	}

	private boolean canExtract(Block block) {
		return !(block instanceof BlockContainer) && !(block instanceof BlockPane) && block.blockHardness >= 0 && block.getBlockBoundsMaxX() - block.getBlockBoundsMinX() >= 0.7F && block.getBlockBoundsMaxZ() - block.getBlockBoundsMinZ() >= 0.7F && block.getBlockBoundsMaxY() - block.getBlockBoundsMinY() >= 0.7F;
	}
}