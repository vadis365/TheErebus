package erebus.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPane;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import erebus.entity.EntityExtractedBlock;

public class ItemBlockExtractor extends Item {
	private final Minecraft mc = Minecraft.getMinecraft();
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
		return 40;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		player.setItemInUse(is, getMaxItemUseDuration(is));
		return is;
	}

	@Override
	public ItemStack onEaten(ItemStack is, World world, EntityPlayer player) {
		
		//world.playSoundAtEntity(player, "erebus:someSoundHere", 1.0F, 1.0F);
		extractBlock(is, world, player);
		is.damageItem(1, player);
		return is;
	}

	protected void extractBlock(ItemStack is, World world, EntityPlayer player) {
		MovingObjectPosition objectMouseOver = mc.thePlayer.rayTrace(16,1);// Distance is 16 atm;
			if(objectMouseOver != null && objectMouseOver.typeOfHit == EnumMovingObjectType.TILE) {
		        int blockID = world.getBlockId(objectMouseOver.blockX, objectMouseOver.blockY, objectMouseOver.blockZ);
		        int blockMeta = world.getBlockMetadata(objectMouseOver.blockX, objectMouseOver.blockY, objectMouseOver.blockZ);
		        Block block = Block.blocksList[blockID];
		        if (!world.isRemote && block != null && canExtract(block)) {
		        		EntityExtractedBlock entityExtractedBlock;
		        		entityExtractedBlock = new EntityExtractedBlock(world);
		        		world.setBlock(objectMouseOver.blockX,objectMouseOver.blockY,objectMouseOver.blockZ, 0);
						entityExtractedBlock.setLocationAndAngles((double) objectMouseOver.blockX + 0.5F, objectMouseOver.blockY, (double) objectMouseOver.blockZ + 0.5F, 0.0F, 0.0F);
						entityExtractedBlock.setBlock(blockID, blockMeta);
						entityExtractedBlock.setHeading(player.posX, player.posY, player.posZ);
						world.spawnEntityInWorld(entityExtractedBlock);
			}
		}
	}
	
	private boolean canExtract(Block block) {
		return  !(block instanceof BlockContainer) &&!(block instanceof BlockPane) && block.blockHardness >= 0 && block.getBlockBoundsMaxX() - block.getBlockBoundsMinX() >= 0.7F && block.getBlockBoundsMaxZ() - block.getBlockBoundsMinZ() >= 0.7F && block.getBlockBoundsMaxY() - block.getBlockBoundsMinY() >= 0.7F;
	}
}