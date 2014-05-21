package erebus.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import erebus.network.PacketTypeHandler;

public class PacketParticle extends CustomPacket {

	public static final byte BEETLE_LARVA_SQUISH = 0;

	private byte particleType;
	private int entityID;

	public PacketParticle() {
		super(PacketTypeHandler.PARTICLE);
	}

	public PacketParticle(byte particleType, int entityID) {
		this();
		this.particleType = particleType;
		this.entityID = entityID;
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		particleType = data.readByte();
		entityID = data.readInt();
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeByte(particleType);
		dos.writeInt(entityID);
	}

	@Override
	public void execute(World world, EntityPlayer player) {
		EffectRenderer eff = Minecraft.getMinecraft().effectRenderer;

		switch (particleType) {
			case BEETLE_LARVA_SQUISH:
				EntityLivingBase e = (EntityLivingBase) player.worldObj.getEntityByID(entityID);
				for (int countparticles = 0; countparticles <= 200; ++countparticles)
					eff.addEffect(new EntityBreakingFX(player.worldObj, e.posX + (e.getRNG().nextDouble() - 0.5D) * e.width, e.posY + e.getRNG().nextDouble() * e.height - e.yOffset, e.posZ + (e.getRNG().nextDouble() - 0.5D) * e.width, Item.slimeBall));
				break;
			default:
				break;
		}
	}
}