package erebus.network.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.network.AbstractClientPacket;

public class PacketAntiVenom extends AbstractClientPacket {

	private int duration;

	public PacketAntiVenom() {
	}

	public PacketAntiVenom(int duration) {
		this.duration = duration;
	}

	@Override
	public void write(ByteBuf buffer) {
		buffer.writeInt(duration);
	}

	@Override
	public void read(ByteBuf buffer) {
		duration = buffer.readInt();
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected void handle(World world, EntityClientPlayerMP player) {
		player.getEntityData().setInteger("antivenomDuration", duration);
	}
}