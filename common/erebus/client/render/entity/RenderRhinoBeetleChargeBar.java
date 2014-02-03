package erebus.client.render.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.client.FMLClientHandler;
import erebus.entity.EntityRhinoBeetle;


public class RenderRhinoBeetleChargeBar
{
    private EntityRhinoBeetle entity;

	@ForgeSubscribe(priority = EventPriority.NORMAL)
    public void eventHandler(RenderGameOverlayEvent.Post event){
	        EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
	    	if(player.isRiding() && player.ridingEntity instanceof EntityRhinoBeetle)
    	    {
	    		System.out.println("PUT SOME RENDERING CODE HERE " + ((EntityRhinoBeetle)player.ridingEntity).rammingCharge);
	    	}
	    	}
	       }
