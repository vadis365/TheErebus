package erebus.tileentity;

import java.util.List;

import erebus.ModBlocks;
import erebus.ModSounds;
import erebus.blocks.BlockTempleBrickUnbreaking;
import erebus.blocks.BlockTempleTeleporter;
import erebus.blocks.BlockTempleTeleporter.EnumTeleporterType;
import erebus.core.helper.Utils;
import erebus.world.feature.structure.AntlionMazeDungeon;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityTempleTeleporter extends TileEntity implements ITickable {
	private int targetX, targetY, targetZ;
	private boolean bossSpawn = true;
	private boolean playOpenSound = true;

	@Override
	public void update() {

		if(getWorld().isRemote)
			return;

		if (getWorld().getBlockState(getPos()).getValue(BlockTempleTeleporter.TYPE).ordinal() < 4) {
			int activeCount = 0;
			int activeCountLast = 0;
			for(int offX = -1; offX < 2; ++offX)
				for(int offZ = -1; offZ < 2; ++offZ) {
					if (getPos().add(offX, 0, offZ) != getPos())
						if (getWorld().getBlockState(getPos().add(offX, 0, offZ)).getBlock() instanceof BlockTempleBrickUnbreaking)
							if (getWorld().getBlockState(getPos().add(offX, 0, offZ)).getValue(BlockTempleBrickUnbreaking.TYPE).ordinal() >= 6 && getWorld().getBlockState(getPos().add(offX, 0, offZ)).getValue(BlockTempleBrickUnbreaking.TYPE).ordinal()  <= 9)
								activeCount++;
							else if (getWorld().getBlockState(getPos().add(offX, 0, offZ)).getValue(BlockTempleBrickUnbreaking.TYPE).ordinal() == 10)
								activeCountLast++;
				}
			if(activeCount == 8)
				setAnimationMeta();
			if (activeCountLast == 8)
				setDestoyForcefield();
		}

		if (getWorld().getTotalWorldTime() % 5 == 0 && getWorld().getBlockState(getPos()).getBlock() != null)
			if (isActiveTeleporter())
				activateBlock();
	}

	public boolean isActiveTeleporter() {
		int type = getWorld().getBlockState(getPos()).getValue(BlockTempleTeleporter.TYPE).ordinal();
		return type >= 4 && type <= 9;
	}
	
	@SuppressWarnings("unchecked")
	protected Entity activateBlock() {
		List<EntityLivingBase> list = getWorld().getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(getPos().up()).grow(0D, 0.25D, 0D));
		for (int i = 0; i < list.size(); i++) {
			Entity entity = list.get(i);
			if (entity != null) {
				if (entity instanceof EntityLivingBase) {
					if (entity.isSneaking())
						return null;
					((EntityLivingBase) entity).setPositionAndUpdate(getTargetX() + 0.5D, getTargetY() + 1D, getTargetZ() + 0.5D);
					world.playSound(null, getPos(), SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.BLOCKS, 1.0F, 1.0F);
				}
			}
		}
		return null;
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
    	if (this != null && !getWorld().isRemote) {
			final IBlockState state = getWorld().getBlockState(getPos());
			getWorld().notifyBlockUpdate(getPos(), state, state, 8);
			markDirty();
    	}
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
		return new SPacketUpdateTileEntity(getPos(), 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		super.onDataPacket(net, packet);
		readFromNBT(packet.getNbtCompound());
		markForUpdate();
		return;
	}
}