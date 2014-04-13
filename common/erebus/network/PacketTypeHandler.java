package erebus.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.fluids.FluidStack;
import erebus.lib.Reference;
import erebus.network.packet.CustomPacket;
import erebus.network.packet.PacketBeetleRamAttack;
import erebus.network.packet.PacketColossalCratePage;
import erebus.network.packet.PacketGlider;
import erebus.network.packet.PacketGliderPowered;
import erebus.network.packet.PacketJarOHoney;
import erebus.network.packet.PacketParticle;
import erebus.network.packet.PacketSound;
import erebus.network.packet.PacketTeleport;

public enum PacketTypeHandler {
	// @formatter:off
	
	BEETLE_RAM_ATTACK(PacketBeetleRamAttack.class),
	COLOSSAL_CRATE_PAGE(PacketColossalCratePage.class),
	GLIDER(PacketGlider.class),
	GLIDER_POWERED(PacketGliderPowered.class),
	PARTICLE(PacketParticle.class),
	SOUND(PacketSound.class),
	TELEPORT(PacketTeleport.class),
	JAR_O_HONEY(PacketJarOHoney.class);
	
	// @formatter:on

	private Class<? extends CustomPacket> clazz;

	PacketTypeHandler(Class<? extends CustomPacket> clazz) {
		this.clazz = clazz;
	}

	public static CustomPacket buildPacket(byte[] data) {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		int selector = bis.read();
		DataInputStream dis = new DataInputStream(bis);

		CustomPacket packet = null;

		try {
			packet = values()[selector].clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		packet.readPopulate(dis);

		return packet;
	}

	public static Packet populatePacket(CustomPacket customPacket) {
		byte[] data = customPacket.populate();

		Packet250CustomPayload packet250 = new Packet250CustomPayload();
		packet250.channel = Reference.CHANNEL;
		packet250.data = data;
		packet250.length = data.length;

		return packet250;
	}

	public static void writeNBTTagCompound(NBTTagCompound nbt, DataOutput dos) throws IOException {
		if (nbt == null)
			dos.writeShort(-1);
		else {
			byte[] abyte = CompressedStreamTools.compress(nbt);
			dos.writeShort((short) abyte.length);
			dos.write(abyte);
		}
	}

	public static void writeFluidStack(FluidStack fluid, DataOutput dos) throws IOException {
		System.out.println(fluid);
		if (fluid == null)
			dos.writeInt(-1);
		else {
			dos.writeInt(fluid.fluidID);
			dos.writeInt(fluid.amount);
			writeNBTTagCompound(fluid.tag, dos);
		}
	}

	public static FluidStack readFluidStack(DataInputStream data) throws IOException {
		FluidStack fluid = null;
		int id = data.readInt();
		System.out.println(id);
		if (id > 0) {
			int amount = data.readInt();
			fluid = new FluidStack(id, amount);
			fluid.tag = Packet.readNBTTagCompound(data);
		}

		return fluid;
	}
}