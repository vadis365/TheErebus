package erebus.network.client;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.entity.EntityClientPlayerMP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.network.AbstractClientPacket;

// @formatter:off
public class C__ extends AbstractClientPacket{
	
	public C__(){}
	
	public C__(Object o){
		
	}
	
	@Override
	public void write(ByteBuf buffer){
		
	}

	@Override
	public void read(ByteBuf buffer){
		
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected void handle(EntityClientPlayerMP player){
		
	}
}
//@formatter:on
