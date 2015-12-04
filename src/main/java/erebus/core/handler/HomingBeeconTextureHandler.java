package erebus.core.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.render.item.TextureHomingBeecon;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.client.event.TextureStitchEvent;

@SideOnly(Side.CLIENT)
public class HomingBeeconTextureHandler {

	public static TextureAtlasSprite beecon, deathCompasss;

	@SubscribeEvent
	public void onItemIconRegister(TextureStitchEvent.Pre evt) {
		if (evt.map.getTextureType() == 1) {
			evt.map.setTextureEntry("erebus:homing_beecon", HomingBeeconTextureHandler.beecon = new TextureHomingBeecon("erebus:homing_beecon"));
			evt.map.setTextureEntry("erebus:death_compass", HomingBeeconTextureHandler.deathCompasss = new TextureHomingBeecon("erebus:death_compass"));
		}
	}
}
