package erebus.network.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
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
			for (int i = -2; i <= 2; i++)
				for (int j = 0; j <= 3; j++)
					for (int k = -2; k <= 2; k++) {
						Block blockToMine = world.getBlock((int)(beetle.posX - 1F) + i, (int) beetle.posY + j, (int)(beetle.posZ) + k);
						Utils.breakBlockWithParticles(world, (int)(beetle.posX - 1F) + i, (int) beetle.posY + j, (int)(beetle.posZ) + k, 0);
						blockToMine.dropBlockAsItem(world, (int)(beetle.posX - 1F) + i, (int) beetle.posY + j, (int)(beetle.posZ) + k, world.getBlockMetadata((int)(beetle.posX - 1F) + i, (int) beetle.posY + j, (int)(beetle.posZ) + k), 0);
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