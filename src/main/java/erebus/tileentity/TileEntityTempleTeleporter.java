package erebus.tileentity;

import erebus.ModBlocks;
import erebus.ModSounds;
import erebus.blocks.BlockTempleBrickUnbreaking;
import erebus.blocks.BlockTempleTeleporter;
import erebus.blocks.BlockTempleTeleporter.EnumTeleporterType;
import erebus.core.helper.Utils;
import erebus.world.feature.structure.AntlionMazeDungeon;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityTempleTeleporter extends TileEntity implements ITickable {
	private int targetX, targetY, targetZ;
	private boolean bossSpawn = true;
	private boolean playOpenSound = true;

	@Override
	public void update() {
		if (!getWorld().isRemote && getWorld().getBlockState(getPos()).getValue(BlockTempleTeleporter.TYPE).ordinal() < 4) {
			int activeCount = 0;
			for(int offX = -1; offX < 2; ++offX)
				for(int offZ = -1; offZ < 2; ++offZ) {
					if (getPos().add(offX, 0, offZ) != getPos())
						if (getWorld().getBlockState(getPos().add(offX, 0, offZ)).getBlock() instanceof BlockTempleBrickUnbreaking)
							if (getWorld().getBlockState(getPos().add(offX, 0, offZ)).getValue(BlockTempleBrickUnbreaking.TYPE).ordinal() >= 6 && getWorld().getBlockState(getPos().add(offX, 0, offZ)).getValue(BlockTempleBrickUnbreaking.TYPE).ordinal()  <= 9)
								activeCount++;
				}
			if(activeCount == 8)
				setAnimationMeta();
		}
		if (!getWorld().isRemote) {
			int activeCount2 = 0;
			for (int offX = -1; offX < 2; ++offX)
				for (int offZ = -1; offZ < 2; ++offZ) {
					if (getPos().add(offX, 0, offZ) != getPos())
						if (getWorld().getBlockState(getPos().add(offX, 0, offZ)) .getBlock() instanceof BlockTempleBrickUnbreaking)
							if (getWorld().getBlockState(getPos().add(offX, 0, offZ)) .getValue(BlockTempleBrickUnbreaking.TYPE).ordinal() == 10)
								activeCount2++;
				}
			if (activeCount2 == 8)
				setDestoyForcefield();
		}
	}

	private void setDestoyForcefield() {
		IBlockState state = getWorld().getBlockState(getPos());
		EnumTeleporterType type = state.getValue(BlockTempleTeleporter.TYPE);
		int meta = type.ordinal();
		if (meta == 0 && playOpenSound) {
			getWorld().playSound(null, getPos(), ModSounds.ALTAR_CHANGE_STATE, SoundCategory.BLOCKS, 1.0F, 1.3F);
			playOpenSound = false;
		}
		if (getWorld().getWorldTime() % 5 == 0 && meta < 4)
			getWorld().setBlockState(getPos(), ModBlocks.TEMPLE_TELEPORTER.getDefaultState().withProperty(BlockTempleTeleporter.TYPE, EnumTeleporterType.values()[meta + 1]), 3);
		if (meta == 3 && bossSpawn) {
			AntlionMazeDungeon.breakForceField(getWorld(), getPos().getX() - 16, getPos().getY() + 1, getPos().getZ() - 27);
			ItemStack nightVisionPotion = new ItemStack(Items.POTIONITEM, 1, 0);
			PotionUtils.addPotionToItemStack(nightVisionPotion, PotionTypes.NIGHT_VISION);
			ItemStack fireResitancePotion = new ItemStack(Items.POTIONITEM, 1, 0);
			PotionUtils.addPotionToItemStack(fireResitancePotion, PotionTypes.FIRE_RESISTANCE);
			Utils.dropStackNoRandom(getWorld(), getPos().up(2), nightVisionPotion);
			Utils.dropStackNoRandom(getWorld(), getPos().up(2), fireResitancePotion);
			bossSpawn = false;
		}
	}

	public void setAnimationMeta() {
		IBlockState state = getWorld().getBlockState(getPos());
		EnumTeleporterType type = state.getValue(BlockTempleTeleporter.TYPE);
		int meta = type.ordinal();
		if (meta == 0 && playOpenSound) {
			getWorld().playSound(null, getPos(), ModSounds.ALTAR_CHANGE_STATE, SoundCategory.BLOCKS, 1.0F, 1.3F);
			playOpenSound = false;
		}
		if (getWorld().getWorldTime() % 5 == 0 && meta < 4)
			getWorld().setBlockState(getPos(), ModBlocks.TEMPLE_TELEPORTER.getDefaultState().withProperty(BlockTempleTeleporter.TYPE, EnumTeleporterType.values()[meta + 1]), 3);
		}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
	return oldState.getBlock() != newState.getBlock();
	}

    public void markForUpdate() {
        IBlockState state = this.getWorld().getBlockState(this.getPos());
        if(state != null)
        	getWorld().notifyBlockUpdate(getPos(), state, state, 2);
    }

	public void setTargetDestination(int x, int y, int z) {
		targetX = x;
		targetY = y;
		targetZ = z;
		markForUpdate();
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
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("targetX", getTargetX());
		nbt.setInteger("targetY", getTargetY());
		nbt.setInteger("targetZ", getTargetZ());
		return nbt;
	}

	@Override
    public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = new NBTTagCompound();
        return writeToNBT(nbt);
    }

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new SPacketUpdateTileEntity(pos, 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		readFromNBT(packet.getNbtCompound());
	}
}