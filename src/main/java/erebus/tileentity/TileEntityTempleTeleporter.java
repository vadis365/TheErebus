package erebus.tileentity;

import erebus.core.helper.Utils;
import erebus.world.feature.structure.AntlionMazeDungeon;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTempleTeleporter extends TileEntity {
	private int targetX, targetY, targetZ;
	private boolean bossSpawn = true;
	private boolean playOpenSound = true;

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote && worldObj.getBlockMetadata(xCoord, yCoord, zCoord) <= 4) {
			if (worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord - 1) >= 6 && worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord - 1) <= 9)
				if (worldObj.getBlockMetadata(xCoord, yCoord, zCoord - 1) >= 6 && worldObj.getBlockMetadata(xCoord, yCoord, zCoord - 1) <= 9)
					if (worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord - 1) >= 6 && worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord - 1) <= 9)
						if (worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord) >= 6 && worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord) <= 9)
							if (worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord + 1) >= 6 && worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord + 1) <= 9)
								if (worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord) >= 6 && worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord) <= 9)
									if (worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord + 1) >= 6 && worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord + 1) <= 9)
										if (worldObj.getBlockMetadata(xCoord, yCoord, zCoord + 1) >= 6 && worldObj.getBlockMetadata(xCoord, yCoord, zCoord + 1) <= 9)
											setAnimationMeta();

			if (worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord - 1) == 10 && worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord - 1) == 10)
				if (worldObj.getBlockMetadata(xCoord, yCoord, zCoord - 1) == 10 && worldObj.getBlockMetadata(xCoord, yCoord, zCoord - 1) == 10)
					if (worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord - 1) == 10 && worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord - 1) == 10)
						if (worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord) == 10 && worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord) == 10)
							if (worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord + 1) == 10 && worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord + 1) == 10)
								if (worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord) == 10 && worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord) == 10)
									if (worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord + 1) == 10 && worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord + 1) == 10)
										if (worldObj.getBlockMetadata(xCoord, yCoord, zCoord + 1) == 10 && worldObj.getBlockMetadata(xCoord, yCoord, zCoord + 1) == 10)
											setDestoyForcefield();

		}
	}

	private void setDestoyForcefield() {
		int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		if (meta == 0 && playOpenSound) {
			worldObj.playSoundEffect(xCoord, yCoord, zCoord, "erebus:altarchangestate", 1.0F, 1.3F);
			playOpenSound = false;
		}
		if (worldObj.getWorldTime() % 5 == 0 && meta < 4)
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta + 1, 3);
		if (meta == 3 && bossSpawn) {
			AntlionMazeDungeon.breakForceField(worldObj, xCoord - 16, yCoord + 1, zCoord - 27);
			Utils.dropStackNoRandom(worldObj, xCoord, yCoord + 2, zCoord, new ItemStack(Items.potionitem, 1, 8227));
			Utils.dropStackNoRandom(worldObj, xCoord, yCoord + 2, zCoord, new ItemStack(Items.potionitem, 1, 8198));
			bossSpawn = false;
		}
	}

	public void setAnimationMeta() {
		int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		if (meta == 0 && playOpenSound) {
			worldObj.playSoundEffect(xCoord, yCoord, zCoord, "erebus:altarchangestate", 1.0F, 1.3F);
			playOpenSound = false;
		}
		if (worldObj.getWorldTime() % 5 == 0 && meta < 4)
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta + 1, 3);
	}

	public void setTargetDestination(int x, int y, int z) {
		targetX = x;
		targetY = y;
		targetZ = z;
	}

	public int getTargetX() {
		return targetX;
	}

	public int getTargetY() {
		return targetY;
	}

	public int getTargetZ() {
		return targetZ;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		setTargetDestination(nbt.getInteger("targetX"), nbt.getInteger("targetY"), nbt.getInteger("targetZ"));
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("targetX", getTargetX());
		nbt.setInteger("targetY", getTargetY());
		nbt.setInteger("targetZ", getTargetZ());
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		readFromNBT(packet.func_148857_g());
	}
}