package erebus.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPane;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import erebus.entity.EntityExtractedBlock;

public class ItemBlockExtractor extends Item {

	public ItemBlockExtractor(int id) {
		super(id);
		maxStackSize = 1;
		setMaxDamage(128);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.bow;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		float x = hasTag(stack) ? stack.getTagCompound().getFloat("blockHardness") * 30 : 0;
		if (x >= 150)
			x = 150;
		return (int) x;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		getBlockInfo(world, player, stack);
		player.setItemInUse(stack, getMaxItemUseDuration(stack));
		return stack;
	}

	public void getBlockInfo(World world, EntityPlayer player, ItemStack stack) {
		if (!world.isRemote && hasTag(stack)) {
			Vec3 vec3 = player.getLookVec().normalize();
			double targetX = player.posX;
			double targetY = player.posY + player.getEyeHeight() - 0.10000000149011612D;
			double targetZ = player.posZ;

			while (world.isAirBlock((int) targetX, (int) targetY, (int) targetZ) && stack.getTagCompound().getInteger("range") <= 15) { // range of 16
				stack.getTagCompound().setInteger("range", stack.getTagCompound().getInteger("range") + 1);
				targetX += vec3.xCoord;
				targetY += vec3.yCoord;
				targetZ += vec3.zCoord;
				if (!world.isAirBlock((int) targetX, (int) targetY, (int) targetZ)) {
					System.out.println("found Block" + stack.getTagCompound().getInteger("range"));

					stack.getTagCompound().setInteger("blockID", world.getBlockId((int) targetX, (int) targetY, (int) targetZ));
					stack.getTagCompound().setInteger("blockMeta", world.getBlockMetadata((int) targetX, (int) targetY, (int) targetZ));
					Block block = Block.blocksList[stack.getTagCompound().getInteger("blockID")];
					if (block != null) {
						stack.getTagCompound().setFloat("blockHardness", block.getBlockHardness(world, (int) targetX, (int) targetY, (int) targetZ));
						stack.getTagCompound().setInteger("targetX", (int) targetX);
						stack.getTagCompound().setInteger("targetY", (int) targetY);
						stack.getTagCompound().setInteger("targetZ", (int) targetZ);
					}
				}
			}
		}
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote && hasTag(stack)) {
			Block block = Block.blocksList[stack.getTagCompound().getInteger("blockID")];
			if (block != null && canExtract(block))
				extractBlock(stack, world, player);
		}
		stack.damageItem(1, player);
		return stack;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int count) {
		resetStats(stack);
	}

	protected void extractBlock(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			EntityExtractedBlock entityExtractedBlock;
			entityExtractedBlock = new EntityExtractedBlock(world);
			int targetX = stack.getTagCompound().getInteger("targetX");
			int targetY = stack.getTagCompound().getInteger("targetY");
			int targetZ = stack.getTagCompound().getInteger("targetZ");

			world.setBlock(targetX, targetY, targetZ, 0);
			entityExtractedBlock.setLocationAndAngles(targetX + 0.5D, targetY, targetZ + 0.5D, 0.0F, 0.0F);
			entityExtractedBlock.setBlock(stack.getTagCompound().getInteger("blockID"), stack.getTagCompound().getInteger("blockMeta"));
			entityExtractedBlock.setHeading(player.posX, player.posY, player.posZ);
			world.spawnEntityInWorld(entityExtractedBlock);
			resetStats(stack);
		}
	}

	public void resetStats(ItemStack stack) {
		stack.getTagCompound().setInteger("range", 0);
	}

	private boolean canExtract(Block block) {
		return !(block instanceof BlockContainer) && !(block instanceof BlockPane) && block.blockHardness >= 0 && block.getBlockBoundsMaxX() - block.getBlockBoundsMinX() >= 0.7F && block.getBlockBoundsMaxZ() - block.getBlockBoundsMinZ() >= 0.7F && block.getBlockBoundsMaxY() - block.getBlockBoundsMinY() >= 0.7F;
	}

	private boolean hasTag(ItemStack stack) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			return false;
		}
		return true;
	}
}