package erebus.network.server;

import erebus.entity.EntityRhinoBeetle;
import erebus.network.AbstractServerPacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class PacketBeetleRamAttack extends AbstractServerPacket {

	private boolean ramming;

	public PacketBeetleRamAttack() {
	}

	public PacketBeetleRamAttack(boolean ramming) {
		this.ramming = ramming;
	}

	@Override
	protected void handle(World world, EntityPlayerMP player) {
		if (player.isRiding() && player.ridingEntity instanceof EntityRhinoBeetle) {
			((EntityRhinoBeetle) player.ridingEntity).setRamAttack(ramming);
			return;
		}
	}

	@Override
	public void write(ByteBuf buffer) {
		buffer.writeBoolean(ramming);
	}

	@Override
	public void read(ByteBuf buffer) {
		ramming = buffer.readBoolean();
	}
}