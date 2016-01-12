package erebus.network.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.core.helper.Utils;
import erebus.entity.EntityStagBeetle;
import erebus.network.AbstractServerPacket;

public class PacketBeetleDig extends AbstractServerPacket {
	private int blockX, blockY, blockZ, blockHitSide;

	public PacketBeetleDig() {
	}

	public PacketBeetleDig(int blockX, int blockY, int blockZ, int blockHitSide) {
		this.blockX = blockX;
		this.blockY = blockY;
		this.blockZ = blockZ;
		this.blockHitSide = blockHitSide;
	}

	@Override
	protected void handle(World world, EntityPlayerMP player) {
		if (player.isRiding() && player.ridingEntity instanceof EntityStagBeetle) {
			EntityStagBeetle beetle = (EntityStagBeetle) player.ridingEntity;
			int direction = MathHelper.floor_double(beetle.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

			if (!world.isAirBlock(blockX, blockY, blockZ)) {
				AxisAlignedBB blockBox = AxisAlignedBB.getBoundingBox(blockX + 0.5D, blockY + 0.5D, blockZ + 0.5D, blockX + 0.5D, blockY + 0.5D, blockZ + 0.5D);
				beetle.getDataWatcher().updateObject(29, (byte)1);
				int i = MathHelper.floor_double(blockBox.minX);
				int j = MathHelper.floor_double(blockBox.minY);
				int k = MathHelper.floor_double(blockBox.minZ);
				int l = MathHelper.floor_double(blockBox.maxX);
				int i1 = MathHelper.floor_double(blockBox.maxY);
				int j1 = MathHelper.floor_double(blockBox.maxZ);

				if(blockY + 0.5D > player.posY + 2)
					beetle.getDataWatcher().updateObject(28, (byte)2);
				else if(blockY + 0.5D < player.posY)
					beetle.getDataWatcher().updateObject(28, (byte)0);
				else
					beetle.getDataWatcher().updateObject(28, (byte)1);

				if(blockHitSide != 0 && blockHitSide != 1) {
						if (direction == 0 || direction == 2) {
							i -= 1;
							l += 1;
							j -= 1;
							i1 +=1;
						}
						if (direction == 1 || direction == 3) {
							k -= 1;
							j1 += 1;
							j -= 1;
							i1 +=1;
						}
				}
				else {
					k -= 1;
					j1 += 1;
					i -= 1;
					l += 1;
				}
				for (int k1 = i; k1 <= l; ++k1)
					for (int l1 = j; l1 <= i1; ++l1)
						for (int i2 = k; i2 <= j1; ++i2) {
							Block block = world.getBlock(k1, l1, i2);
							if (block != null && block.getBlockHardness(world, k1, l1, i2) <= 10F) {
								Utils.breakBlockWithParticles(world, k1, l1, i2, 0);
								block.dropBlockAsItem(world, k1, l1, i2, world.getBlockMetadata(k1, l1, i2), 0);
							}
						}
				}
			return;
		}
	}

	@Override
	public void write(ByteBuf buffer) {
		buffer.writeInt(blockX);
		buffer.writeInt(blockY);
		buffer.writeInt(blockZ);
		buffer.writeInt(blockHitSide);
	}

	@Override
	public void read(ByteBuf buffer) {
		blockX = buffer.readInt();
		blockY = buffer.readInt();
		blockZ = buffer.readInt();
		blockHitSide = buffer.readInt();
	}

}