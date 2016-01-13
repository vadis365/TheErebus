package erebus.network.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.network.AbstractClientPacket;

public class PacketArmchairClientMessages extends AbstractClientPacket {

	private byte messageIndex;

	public PacketArmchairClientMessages() {
	}

	public PacketArmchairClientMessages(byte messageIndex) {
		this.messageIndex = messageIndex;
	}

	@Override
	public void write(ByteBuf buffer) {
		buffer.writeByte(messageIndex);
	}

	@Override
	public void read(ByteBuf buffer) {
		messageIndex = buffer.readByte();
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected void handle(World world, EntityClientPlayerMP player) {
		switch (messageIndex) {
		case 0:
			player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("armchair.unableToSit")));
			break;
		case 1:
			player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("armchair.spawnSet")));
			break;
		case 2:
			player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("armchair.obstructed")));
			break;
		case 3:
			player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("armchair.missing")));
			break;
		case 4:
			player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("armchair.unstable")));
			break;
		}
	}
}