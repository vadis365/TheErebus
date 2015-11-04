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
			evt.map.setTextureEntry("erebus:homingBeecon", HomingBeeconTextureHandler.beecon = new TextureHomingBeecon("erebus:homingBeecon"));
			evt.map.setTextureEntry("erebus:deathCompass", HomingBeeconTextureHandler.deathCompasss = new TextureHomingBeecon("erebus:deathCompass"));
		}
	}
}
