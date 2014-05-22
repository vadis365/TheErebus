package erebus.network;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.relauncher.Side;

public abstract class AbstractServerPacket extends AbstractPacket{
	@Override
	public void handle(Side side, EntityPlayer player){
		if (side == Side.SERVER)handle((EntityPlayerMP)player);
		else throw new UnsupportedOperationException("Tried to handle server packet on client side!");
	}
	
	protected abstract void handle(EntityPlayerMP player);
}
