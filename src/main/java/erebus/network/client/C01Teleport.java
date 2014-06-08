package erebus.network.client;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.teleport.TeleportClient;
import erebus.network.AbstractClientPacket;

public class C01Teleport extends AbstractClientPacket {

	public C01Teleport() {
	}

	@Override
	public void write(ByteBuf buffer) {
	}

	@Override
	public void read(ByteBuf buffer) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected void handle(World world, EntityClientPlayerMP player) {
		TeleportClient.timeInPortal = 0;
		TeleportClient.prevTimeInPortal = 0;
		TeleportClient.inPortal = false;
		TeleportClient.timeUntilPortal = 20;

		/*
		if (something) {
			Entry<String, URL> entry = AmbientMusicManager.getInstance().getEntry("feint_sleepless.ogg");
			if (entry != null)
				AmbientMusicManager.getInstance().play(entry);
		}
		 */
	}
}