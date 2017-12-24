package erebus.client.fx;

import erebus.lib.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ParticleTextureStitchEvent {

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	 public void onTextureStitchPre(TextureStitchEvent.Pre event) {
	  event.getMap().registerSprite(new ResourceLocation(Reference.MOD_ID + ":particle/particle_sonic"));
	  event.getMap().registerSprite(new ResourceLocation(Reference.MOD_ID + ":entity/wisp"));
	}

}