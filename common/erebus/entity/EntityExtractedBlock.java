package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import erebus.core.helper.Utils;

public class EntityExtractedBlock extends EntityFlying implements IEntityAdditionalSpawnData {
	
	public int blockID, blockMeta;
	public double targetX, targetY, targetZ;
	EntityLivingBase entityToAttack;
	
	public EntityExtractedBlock(World world) {
		super(world);
		setSize(1.0F, 1.0F);
		setBlock(Block.stone.blockID, 0);
		experienceValue = 0;
	}
	
	public void setBlock(int blockID, int blockMeta) {
		this.blockID = blockID;
		this.blockMeta = blockMeta;
	}
	
	public void setHeading(double targetX, double targetY, double targetZ) {
		this.targetX = targetX;
		this.targetY = targetY;
		this.targetZ = targetZ;
	}
	
	@Override
	public boolean isAIEnabled() {
		return true;
	}
	
	@Override
	public void onUpdate() {
		if (!worldObj.isRemote) {
			if(posX !=targetX ||posZ !=targetZ) {
				motionX += ((targetX - posX) * 0.5D - motionX) * 0.10000000149011612D;
				motionY += ((targetY - posY) * 0.5D - motionY) * 0.10000000149011612D;
				motionZ += ((targetZ - posZ) * 0.5D - motionZ) * 0.10000000149011612D;
			}
			
			if(posX ==targetX && posZ ==targetZ)
				if(posY>=targetY) {
					motionY += 0.75D;
				}
			
			if(posY <=targetY) {
				if(onGround) {
					setDead();
					worldObj.setBlock((int) posX,(int) posY,(int) posZ, this.blockID, this.blockMeta, 3);
				}
			}
		}		
		super.onUpdate();
	}
	
	@Override
	protected void collideWithEntity(Entity entity) {
		setDead();
		if (entity instanceof EntityPlayer) {
			if(!worldObj.isRemote) {
				if(((EntityPlayer)entity).inventory.addItemStackToInventory(new ItemStack((Item.itemsList[blockID].itemID), 1 ,blockMeta)));
				else {
					Utils.dropStack(worldObj, (int) posX, (int) posY, (int) posZ, (new ItemStack((Item.itemsList[blockID].itemID), 1 ,blockMeta)));
				}	
			}
		}
		else if(!worldObj.isRemote) {
			worldObj.setBlock((int) posX,(int) posY,(int) posZ, this.blockID, this.blockMeta, 3);
		}
	super.collideWithEntity(entity);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound data) {
		super.writeEntityToNBT(data);
		data.setInteger("blockID", blockID);
		data.setInteger("blockMeta", blockMeta);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound data) {
		super.readEntityFromNBT(data);
		blockID = data.getInteger("blockID");
		blockMeta = data.getInteger("blockMeta");
	}

	@Override
	public void writeSpawnData(ByteArrayDataOutput data) {
		data.writeInt(blockID);
		data.writeInt(blockMeta);
	}

	@Override
	public void readSpawnData(ByteArrayDataInput data) {
		blockID = data.readInt();
		blockMeta = data.readInt();
	}
}
