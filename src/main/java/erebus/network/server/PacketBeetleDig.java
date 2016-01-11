package erebus.network.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.core.helper.Utils;
import erebus.entity.EntityStagBeetle;
import erebus.network.AbstractServerPacket;

public class PacketBeetleDig extends AbstractServerPacket {

	public PacketBeetleDig() {
	}

	@Override
	protected void handle(World world, EntityPlayerMP player) {
		if (player.isRiding() && player.ridingEntity instanceof EntityStagBeetle) {
			EntityStagBeetle beetle = (EntityStagBeetle)player.ridingEntity;
			int direction = MathHelper.floor_double(beetle.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
			int i = MathHelper.floor_double(beetle.boundingBox.minX + 0.25F);
			int j = MathHelper.floor_double(beetle.boundingBox.minY);
			int k = MathHelper.floor_double(beetle.boundingBox.minZ + 0.25F);
			int l = MathHelper.floor_double(beetle.boundingBox.maxX - 0.25F);
			int i1 = MathHelper.floor_double(beetle.boundingBox.maxY + 1);
			int j1 = MathHelper.floor_double(beetle.boundingBox.maxZ - 0.25F);

			if (direction == 0)
				j1 += 1F;

			if (direction == 3)
				l += 0.75F;

			if (direction == 1)
				i -= 1F;

			if (direction == 2)
				k -= 0.75F;

			for (int k1 = i; k1 <= l; ++k1)
				for (int l1 = j; l1 <= i1; ++l1)
					for (int i2 = k; i2 <= j1; ++i2) {
						Block block = world.getBlock(k1, l1, i2);
						if (block != null && block.getBlockHardness(world, k1, l1, i2) <= 10F) {
							Utils.breakBlockWithParticles(world, k1, l1, i2, 0);
							block.dropBlockAsItem(world, k1, l1, i2, world.getBlockMetadata(k1, l1, i2), 0);
						}
					}
			return;
		}
	}

	@Override
	public void write(ByteBuf buffer) {
	}

	@Override
	public void read(ByteBuf buffer) {
	}

}