package erebus.network.client;
import erebus.Erebus;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketParticle implements IMessage, IMessageHandler<PacketParticle, IMessage> {

	public static enum ParticleType {
		BEETLE_LARVA_SQUISH,
		SPRAY_CAN,
		CRUSHROOM_BLAM,
		TARANTULA_BLAM,
		BOSS_DEATH,
		ANTLION_BLAM,
		ANTLION_RUMBLE,
		HAMMER_BLAM,
		GAS_VENT_SWAMP,
		GAS_VENT_VOLCANIC,
		SPORE_JET;

		static final ParticleType[] values = values();
	}

	public byte particleType;
	public float posX;
	public float posY;
	public float posZ;

	public PacketParticle() {
	}

	public PacketParticle(ParticleType particleType, float posX, float posY, float posZ) {
		this.particleType = (byte) particleType.ordinal();
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		buffer.writeByte(particleType);
		buffer.writeFloat(posX).writeFloat(posY).writeFloat(posZ);
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		particleType = buffer.readByte();
		posX = buffer.readFloat();
		posY = buffer.readFloat();
		posZ = buffer.readFloat();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IMessage onMessage(PacketParticle message, MessageContext ctx) {
		World world = FMLClientHandler.instance().getWorldClient();
		if (world == null)
			return null;

		if (world.isRemote) {
			if (message.particleType < 0 || message.particleType >= ParticleType.values.length)
				return null;

			switch (ParticleType.values[message.particleType]) {
				case BEETLE_LARVA_SQUISH:
					//for (int count = 0; count <= 200; ++count)
					//	eff.addEffect(new EntityBreakingFX(player.worldObj, message.posX + (player.worldObj.rand.nextDouble() - 0.5D) * message.width, message.posY + player.worldObj.rand.nextDouble() * message.height - message.yOffset, message.posZ + (player.worldObj.rand.nextDouble() - 0.5D) * message.width, Items.slime_ball));
					break;
				case CRUSHROOM_BLAM:
					//for (int a = 0; a < 360; a += 4) {
					//	double ang = a * Math.PI / 180D;
					//	eff.addEffect(new EntityRepellentFX(player.worldObj, message.posX + -MathHelper.sin((float) ang) * 3, message.posY + 0.1D, message.posZ + MathHelper.cos((float) ang) * 3, 0, 0, 0));
					//}
					break;
				case TARANTULA_BLAM:
					//for (int a = 0; a < 360; a += 4) {
					//	double ang = a * Math.PI / 180D;
					//	eff.addEffect(new EntityCloudFX(player.worldObj, message.posX + -MathHelper.sin((float) ang) * 3, message.posY, message.posZ + MathHelper.cos((float) ang) * 3, -MathHelper.sin((float) ang) * 0.5, 0.1D, MathHelper.cos((float) ang) * 0.5));
					//}
					break;
				case ANTLION_BLAM:
				//	for (int a = 0; a < 360; a += 4) {
					//	double ang = a * Math.PI / 180D;
					//	for (int count = 0; count <= 20; ++count)
					//		eff.addEffect(new EntityDiggingFX(player.worldObj, message.posX + -MathHelper.sin((float) ang) * 3.5D, message.posY + 0.5D, message.posZ + MathHelper.cos((float) ang) * 3.5D, -MathHelper.sin((float) ang) * 0.8, 0.0D, MathHelper.cos((float) ang) * 0.8, ModBlocks.ghostSand, 0));
					//	eff.addEffect(new EntityCloudFX(player.worldObj, message.posX + -MathHelper.sin((float) ang) * 4.5, message.posY, message.posZ + MathHelper.cos((float) ang) * 4.5, -MathHelper.sin((float) ang) * 1, 0.1D, MathHelper.cos((float) ang) * 1));
					//}
					break;
				case BOSS_DEATH:
					//float f = (message.worldObj.rand.nextFloat() - 0.5F) * 8.0F;
					//float f1 = (message.worldObj.rand.nextFloat() - 0.5F) * 4.0F;
					//float f2 = (message.worldObj.rand.nextFloat() - 0.5F) * 8.0F;
					//eff.addEffect(new EntityHugeExplodeFX(player.worldObj, message.posX + f, message.posY + 2.0D + f1, message.posZ + f2, 0.0D, 0.0D, 0.0D));
					break;

				case ANTLION_RUMBLE:
					//for (int a = 0; a < 360; a += 4) {
					//	double ang = a * Math.PI / 180D;
					//	eff.addEffect(new EntityDiggingFX(player.worldObj, message.posX + -MathHelper.sin((float) ang) * 3.5D, message.posY + 0.125D, message.posZ + MathHelper.cos((float) ang) * 3.5D, -MathHelper.sin((float) ang) * 0.8, 0.3D, MathHelper.cos((float) ang) * 0.8, ModBlocks.ghostSand, 0));
					//}
					break;
				case HAMMER_BLAM:
					//for (int a = 0; a < 360; a += 4) {
					//	double ang = a * Math.PI / 180D;
					//	for (int count = 0; count <= 4; ++count)
					//		eff.addEffect(new EntityFireworkSparkFX(player.worldObj, message.posX + -MathHelper.sin((float) ang) * 1 * count * 0.5, message.posY - 1, message.posZ + MathHelper.cos((float) ang) * 1 * count * 0.5, -MathHelper.sin((float) ang) * 0.5, 0.01D, MathHelper.cos((float) ang) * 0.5, eff));
					//	eff.addEffect(new EntityCloudFX(player.worldObj, message.posX + -MathHelper.sin((float) ang) * 2, message.posY - 1, message.posZ + MathHelper.cos((float) ang) * 2, -MathHelper.sin((float) ang) * 0.5, 0.01D, MathHelper.cos((float) ang) * 0.5));
					//}
					break;
				case GAS_VENT_SWAMP:
					for (double yy = message.posY; yy < message.posY + 2D; yy += 0.5D) {
						double d0 = message.posX - 0.075F;
						double d1 = yy;
						double d2 = message.posZ - 0.075F;
						double d3 = message.posX + 0.075F;
						double d4 = message.posZ + 0.075F;
						double d5 = message.posX;
						double d6 = yy + 0.25F;
						double d7 = message.posZ;
						Erebus.PROXY.spawnCustomParticle("swampflame", world, d0, d1, d2, 0.0D, 0.05D, 0.0D);
						Erebus.PROXY.spawnCustomParticle("swampflame", world, d0, d1, d4, 0.0D, 0.05D, 0.0D);
						Erebus.PROXY.spawnCustomParticle("swampflame", world, d3, d1, d2, 0.0D, 0.05D, 0.0D);
						Erebus.PROXY.spawnCustomParticle("swampflame", world, d3, d1, d4, 0.0D, 0.05D, 0.0D);
						Erebus.PROXY.spawnCustomParticle("swampflame", world, d5, d6, d7, 0.0D, 0.05D, 0.0D);
					}
					break;
				case GAS_VENT_VOLCANIC:
					/*for (double yy = message.posY; yy < message.posY + 2D; yy += 0.5D) {
						double d0 = message.posX - 0.075F;
						double d1 = yy;
						double d2 = message.posZ - 0.075F;
						double d3 = message.posX + 0.075F;
						double d4 = message.posZ + 0.075F;
						double d5 = message.posX;
						double d6 = yy + 0.25F;
						double d7 = message.posZ;
						Erebus.proxy.spawnCustomParticle("flame", world, d0, d1, d2, 0.0D, 0.05D, 0.0D);
						Erebus.proxy.spawnCustomParticle("flame", world, d0, d1, d4, 0.0D, 0.05D, 0.0D);
						Erebus.proxy.spawnCustomParticle("flame", world, d3, d1, d2, 0.0D, 0.05D, 0.0D);
						Erebus.proxy.spawnCustomParticle("flame", world, d3, d1, d4, 0.0D, 0.05D, 0.0D);
						Erebus.proxy.spawnCustomParticle("flame", world, d5, d6, d7, 0.0D, 0.05D, 0.0D);
					}*/
					break;
				case SPORE_JET:
					/*for (double yy = message.posY; yy < message.posY + 2D; yy += 0.5D) {
						double d0 = message.posX - 0.075F;
						double d1 = yy;
						double d2 = message.posZ - 0.075F;
						double d3 = message.posX + 0.075F;
						double d4 = message.posZ + 0.075F;
						double d5 = message.posX;
						double d6 = yy + 0.25F;
						double d7 = message.posZ;
						Erebus.proxy.spawnCustomParticle("spell", world, d0, d1, d2, 0.0D, 0.05D, 0.0D);
						Erebus.proxy.spawnCustomParticle("spell", world, d0, d1, d4, 0.0D, 0.05D, 0.0D);
						Erebus.proxy.spawnCustomParticle("spell", world, d3, d1, d2, 0.0D, 0.05D, 0.0D);
						Erebus.proxy.spawnCustomParticle("spell", world, d3, d1, d4, 0.0D, 0.05D, 0.0D);
						Erebus.proxy.spawnCustomParticle("spell", world, d5, d6, d7, 0.0D, 0.05D, 0.0D);
					}*/
					break;
				default:
			}
		}
		return null;
		}
	
}