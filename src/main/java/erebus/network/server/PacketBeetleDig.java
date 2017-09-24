package erebus.network.server;

import erebus.core.helper.Utils;
import erebus.entity.EntityStagBeetle;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketBeetleDig implements IMessage, IMessageHandler<PacketBeetleDig, IMessage> {

	private int blockX, blockY, blockZ, blockSide;

	public PacketBeetleDig() {
	}

	public PacketBeetleDig(BlockPos blockPos, EnumFacing sideHit) {
		this.blockX = blockPos.getX();
		this.blockY = blockPos.getY();
		this.blockZ = blockPos.getZ();
		this.blockSide = sideHit.getIndex();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(blockX);
		buf.writeInt(blockY);
		buf.writeInt(blockZ);
		buf.writeInt(blockSide);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		blockX = buf.readInt();
		blockY = buf.readInt();
		blockZ = buf.readInt();
		blockSide = buf.readInt();
	}
	
	@Override
	public IMessage onMessage(PacketBeetleDig message, MessageContext ctx) {

		final EntityPlayerMP player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(new Runnable() {
			public void run() {
				if (player.isRiding() && player.getRidingEntity() instanceof EntityStagBeetle) {
					EntityStagBeetle beetle = (EntityStagBeetle) player.getRidingEntity();
					int direction = MathHelper.floor(beetle.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

					if (!player.getEntityWorld().isAirBlock(new BlockPos(message.blockX, message.blockY, message.blockZ))) {
						AxisAlignedBB blockBox = new AxisAlignedBB(message.blockX + 0.5D, message.blockY + 0.5D, message.blockZ + 0.5D, message.blockX + 0.5D, message.blockY + 0.5D, message.blockZ + 0.5D);
						beetle.setAction((byte)1);
						int i = MathHelper.floor(blockBox.minX);
						int j = MathHelper.floor(blockBox.minY);
						int k = MathHelper.floor(blockBox.minZ);
						int l = MathHelper.floor(blockBox.maxX);
						int i1 = MathHelper.floor(blockBox.maxY);
						int j1 = MathHelper.floor(blockBox.maxZ);

						if(message.blockY + 0.5D > player.posY + 2)
							beetle.setHeadPos((byte)2);
						else if(message.blockY + 0.5D < player.posY)
							beetle.setHeadPos((byte)0);
						else
							beetle.setHeadPos((byte)1);

						if(message.blockSide != 0 && message.blockSide != 1) {
								if (direction == 0 || direction == 2) {
									i -= 1;
									l += 1;
									j -= 1;
									i1 +=1;
								}
								if (direction == 1 || direction == 3) {
									k -= 1;
									j1 += 1;
									j -= 1;
									i1 +=1;
								}
						}
						else {
							k -= 1;
							j1 += 1;
							i -= 1;
							l += 1;
						}
						for (int k1 = i; k1 <= l; ++k1)
							for (int l1 = j; l1 <= i1; ++l1)
								for (int i2 = k; i2 <= j1; ++i2) {
									BlockPos pos = new BlockPos(k1, l1, i2);
									IBlockState state = player.getEntityWorld().getBlockState(pos);
									if (state.getBlock() != null && state.getBlockHardness(player.getEntityWorld(), pos) <= 10F) {
										//player.getEntityWorld().playEvent(null, 2001, pos, Block.getIdFromBlock(state.getBlock()));
										Utils.breakBlockWithParticles(player.getEntityWorld(), pos);
										state.getBlock().dropBlockAsItem(player.getEntityWorld(), pos, state, 0);
									}
								}
						}
					return;
				}
			}
		});

		return null;
	}
}