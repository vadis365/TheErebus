package erebus.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import erebus.entity.EntityRhinoBeetle;
import erebus.network.PacketTypeHandler;

public class PacketBeetleRamAttack extends CustomPacket {

	private boolean ramming;

	public PacketBeetleRamAttack() {
		super(PacketTypeHandler.BEETLE_RAM_ATTACK);
	}

	public PacketBeetleRamAttack(boolean ramming) {
		this();
		this.ramming = ramming;
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		ramming = data.readBoolean();
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeBoolean(ramming);
	}

	@Override
	public void execute(World world, EntityPlayer player) {
		if (player.isRiding() && player.ridingEntity instanceof EntityRhinoBeetle) {
			((EntityRhinoBeetle) player.ridingEntity).setRamAttack(ramming);
			return;
		}
	}
}