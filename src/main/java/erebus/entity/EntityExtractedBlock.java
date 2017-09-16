package erebus.entity;

import erebus.core.helper.Utils;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityExtractedBlock extends EntityFlying implements IEntityAdditionalSpawnData {

	public Block blockID;
	public int blockMeta;
	public double targetX, targetY, targetZ;

	public EntityExtractedBlock(World world) {
		super(world);
		setSize(1.0F, 1.0F);
		setBlock(Blocks.STONE, 0);
		experienceValue = 0;
	}

	public void setBlock(Block blockID, int blockMeta) {
		this.blockID = blockID;
		this.blockMeta = blockMeta;
	}

	public void setHeading(double targetX, double targetY, double targetZ) {
		this.targetX = targetX;
		this.targetY = targetY;
		this.targetZ = targetZ;
	}

	@Override
	public void onUpdate() {
		if (!getEntityWorld().isRemote) {
			if (posX != targetX || posZ != targetZ) {
				motionX += ((targetX - posX) * 0.5D - motionX) * 0.10000000149011612D;
				motionY += ((targetY - posY) * 0.5D - motionY) * 0.10000000149011612D;
				motionZ += ((targetZ - posZ) * 0.5D - motionZ) * 0.10000000149011612D;
			}

			if (posX == targetX && posZ == targetZ)
				if (posY >= targetY)
					motionY += 0.75D;

			if (posY <= targetY)
				if (onGround) {
					setDead();
					getEntityWorld().setBlockState(getPosition(), blockID.getStateFromMeta(blockMeta), 3);
				}
		}
		super.onUpdate();
	}

	@Override
	protected void collideWithEntity(Entity entity) {
		setDead();
		if (entity instanceof EntityPlayer) {
			if (!getEntityWorld().isRemote)
				if (((EntityPlayer) entity).inventory.addItemStackToInventory(new ItemStack(blockID, 1, blockMeta)))
					;
				else
					Utils.dropStack(getEntityWorld(), getPosition(), new ItemStack(blockID, 1, blockMeta));
		} else if (!getEntityWorld().isRemote)
			getEntityWorld().setBlockState(getPosition(), blockID.getStateFromMeta(blockMeta), 3);
		super.collideWithEntity(entity);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound data) {
		super.writeEntityToNBT(data);
		data.setInteger("blockID", Block.getIdFromBlock(blockID));
		data.setInteger("blockMeta", blockMeta);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound data) {
		super.readEntityFromNBT(data);
		blockID = Block.getBlockById(data.getInteger("blockID"));
		blockMeta = data.getInteger("blockMeta");
	}

	@Override
	public void writeSpawnData(ByteBuf data) {
		data.writeInt(Block.getIdFromBlock(blockID));
		data.writeInt(blockMeta);
	}

	@Override
	public void readSpawnData(ByteBuf data) {
		blockID = Block.getBlockById(data.readInt());
		blockMeta = data.readInt();
	}
}