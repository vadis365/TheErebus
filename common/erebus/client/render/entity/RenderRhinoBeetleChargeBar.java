package erebus.client.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

import erebus.entity.EntityRhinoBeetle;


public class RenderRhinoBeetleChargeBar extends Gui {
    private Minecraft mc;
    
    public RenderRhinoBeetleChargeBar() {
     mc = Minecraft.getMinecraft();
    }
    @ForgeSubscribe(priority = EventPriority.NORMAL)
    public void onRenderHUD(RenderGameOverlayEvent.Post evt) {
    if (evt.type.equals(RenderGameOverlayEvent.ElementType.HOTBAR)) {
    if (mc.thePlayer != null
    && mc.thePlayer.ridingEntity != null
    && mc.thePlayer.ridingEntity instanceof EntityRhinoBeetle) {
    ScaledResolution scaledRes = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
    int scaledWdt = scaledRes.getScaledWidth();
    int scaledHgt = scaledRes.getScaledHeight();
    EntityRhinoBeetle beetle = ((EntityRhinoBeetle)mc.thePlayer.ridingEntity);
     
    GL11.glColor4f(1F, 1F, 1F, 1F);
     
    mc.renderEngine.bindTexture(new ResourceLocation("erebus:textures/special/tiles/bambooLadder.png"));
     
    renderChargeBar((int) (beetle.rammingCharge *0.9F) , scaledWdt / 2 + 91, scaledHgt - 49);
    }
    }
    }
     
    private void renderChargeBar(int currCond, int posX, int posY) {
    for (int i = 0; i < 10; i++) {
    int var6 = posX - i * 8 - 9;
    drawTexturedModalRect(var6, posY, 0, 9, 9, 9);
    }
     
    boolean addAHalf = false;
     
    if (currCond % 2 != 0 && currCond >= 0)
    addAHalf = true;
     
    for (int j = 0; j < currCond / 2; j++) {
    int var6 = posX - j * 8 - 9;
    drawTexturedModalRect(var6, posY, 9, 9, 9, 9);
    }
     
    if (addAHalf) {
    int var6 = posX - (currCond / 2) * 8 - 9;
    drawTexturedModalRect(var6, posY, 18, 9, 9, 9);
    }
    }
    }  
    
	      
/*
	@ForgeSubscribe(priority = EventPriority.NORMAL)
    public void eventHandler(RenderGameOverlayEvent.Pre event){
		if (event.type.equals(RenderGameOverlayEvent.ElementType.HOTBAR)) {
	       // EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
	    	if(player.isRiding() && player.ridingEntity instanceof EntityRhinoBeetle)
    	    {
	    		//System.out.println("PUT SOME RENDERING CODE HERE " + ((EntityRhinoBeetle)player.ridingEntity).rammingCharge);
	    	} }
	*/ 