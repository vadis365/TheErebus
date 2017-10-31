package erebus.network.client;

import java.io.IOException;

import erebus.core.capabilities.base.EntityCapability;
import erebus.core.capabilities.base.EntityCapabilityHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageSyncEntityCapabilities implements IMessage, IMessageHandler<MessageSyncEntityCapabilities, MessageSyncEntityCapabilities> {
	private ResourceLocation capability;
	private NBTTagCompound nbt;
	private int entityID;

	public MessageSyncEntityCapabilities() { }

	public MessageSyncEntityCapabilities(EntityCapability<?, ?, ?> entityCapability) {
		this.capability = entityCapability.getID();
		this.entityID = entityCapability.getEntity().getEntityId();
		this.nbt = new NBTTagCompound();
		entityCapability.writeTrackingDataToNBT(this.nbt);
	}

	private Entity getEntity(World world) {
		return world.getEntityByID(this.entityID);
	}

	@Override
	public MessageSyncEntityCapabilities onMessage(final MessageSyncEntityCapabilities message, MessageContext ctx) {
		if(ctx.side == Side.CLIENT) {
			Minecraft mc = FMLClientHandler.instance().getClient();
			mc.addScheduledTask(new Runnable() {
				public void run() {
					handleMessage(message);
				}
			});
		}
		return null;
	}

	@SideOnly(Side.CLIENT)
	private void handleMessage(MessageSyncEntityCapabilities message) {
		World world = Minecraft.getMinecraft().world;
		Entity entity = world.getEntityByID(message.entityID);
		if(entity != null) {
			EntityCapability<?, ?, Entity> capability = EntityCapabilityHandler.getCapability(message.capability, entity);
			if(capability != null) {
				capability.readTrackingDataFromNBT(message.nbt);
			}
		}
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		PacketBuffer buf = new PacketBuffer(buffer);
		this.capability = new ResourceLocation(buf.readString(128));
		this.entityID = buf.readInt();
		try {
			this.nbt = buf.readCompoundTag();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		PacketBuffer buf = new PacketBuffer(buffer);
		buf.writeString(this.capability.toString());
		buf.writeInt(this.entityID);
		buf.writeCompoundTag(this.nbt);
	}
}
