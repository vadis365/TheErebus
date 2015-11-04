package erebus.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import erebus.entity.EntityExtractedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPane;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;

public class ItemExtractor extends Item {

	public ItemExtractor() {
		setMaxStackSize(1);
		setMaxDamage(128);
		setCreativeTab(ModTabs.gears);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
		list.add(StatCollector.translateToLocal("tooltip.erebus.extractor"));
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
		if (hasTag(stack) && stack.getTagCompound().getInteger("coolDown") <= 0)
			getBlockInfo(world, player, stack);
		if (hasTag(stack)) {
			Block block = Block.getBlockById(stack.getTagCompound().getInteger("blockID"));
			if (block != null)
				player.setItemInUse(stack, getMaxItemUseDuration(stack));
		}
		return stack;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int count) {
		resetStats(stack);
	}

	public void getBlockInfo(World world, EntityPlayer player, ItemStack stack) {
		if (!world.isRemote && hasTag(stack)) {
			Vec3 vec3 = player.getLookVec().normalize();
			double targetX = player.posX;
			double targetY = player.posY + player.getEyeHeight() - 0.10000000149011612D;
			double targetZ = player.posZ;

			int range = 0;
			while (world.isAirBlock(MathHelper.floor_double(targetX), MathHelper.floor_double(targetY), MathHelper.floor_double(targetZ)) && range <= 15) { // range of 16
				range++;
				targetX += vec3.xCoord;
				targetY += vec3.yCoord;
				targetZ += vec3.zCoord;
				if (!world.isAirBlock(MathHelper.floor_double(targetX), MathHelper.floor_double(targetY), MathHelper.floor_double(targetZ))) {
					stack.getTagCompound().setInteger("targetX", MathHelper.floor_double(targetX));
					stack.getTagCompound().setInteger("targetY", MathHelper.floor_double(targetY));
					stack.getTagCompound().setInteger("targetZ", MathHelper.floor_double(targetZ));
					stack.getTagCompound().setInteger("blockID", Block.getIdFromBlock(world.getBlock(MathHelper.floor_double(targetX), MathHelper.floor_double(targetY), MathHelper.floor_double(targetZ))));
					stack.getTagCompound().setInteger("blockMeta", world.getBlockMetadata(MathHelper.floor_double(targetX), MathHelper.floor_double(targetY), MathHelper.floor_double(targetZ)));
					Block block = Block.getBlockById(stack.getTagCompound().getInteger("blockID"));
					if (block != null)
						stack.getTagCompound().setFloat("blockHardness", block.getBlockHardness(world, MathHelper.floor_double(targetX), MathHelper.floor_double(targetY), MathHelper.floor_double(targetZ)));
				}
			}
		}
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote && hasTag(stack)) {
			Block block = Block.getBlockById(stack.getTagCompound().getInteger("blockID"));
			if (block != null && canExtract(block))
				extractBlock(stack, world, player);
		}
		resetStats(stack);
		stack.damageItem(1, player);
		return stack;
	}

	protected void extractBlock(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			EntityExtractedBlock entityExtractedBlock;
			entityExtractedBlock = new EntityExtractedBlock(world);
			int targetX = stack.getTagCompound().getInteger("targetX");
			int targetY = stack.getTagCompound().getInteger("targetY");
			int targetZ = stack.getTagCompound().getInteger("targetZ");

			world.setBlockToAir(targetX, targetY, targetZ);
			entityExtractedBlock.setLocationAndAngles(targetX + 0.5D, targetY, targetZ + 0.5D, 0.0F, 0.0F);
			entityExtractedBlock.setBlock(Block.getBlockById(stack.getTagCompound().getInteger("blockID")), stack.getTagCompound().getInteger("blockMeta"));
			entityExtractedBlock.setHeading(player.posX, player.posY, player.posZ);
			world.spawnEntityInWorld(entityExtractedBlock);
		}
	}

	private boolean canExtract(Block block) {
		return !(block instanceof BlockContainer) && !(block instanceof BlockFluidBase) && !(block instanceof BlockPane) && block != Block.getBlockFromName("bedrock") && block.getBlockBoundsMaxX() - block.getBlockBoundsMinX() >= 0.7F && block.getBlockBoundsMaxZ() - block.getBlockBoundsMinZ() >= 0.7F && block.getBlockBoundsMaxY() - block.getBlockBoundsMinY() >= 0.7F;
	}

	public void resetStats(ItemStack stack) {
		stack.getTagCompound().setInteger("blockID", 0);
		stack.getTagCompound().setInteger("coolDown", 20);
	}

	private boolean hasTag(ItemStack stack) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			return false;
		}
		return true;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int aNumber, boolean aBoolean) {
		if (hasTag(stack) && stack.getTagCompound().getInteger("coolDown") >= 0)
			stack.getTagCompound().setInteger("coolDown", stack.getTagCompound().getInteger("coolDown") - 1);
	}
}
