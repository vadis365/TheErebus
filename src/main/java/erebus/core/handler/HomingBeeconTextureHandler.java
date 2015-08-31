package erebus.core.handler;

import erebus.client.render.item.TextureHomingBeecon;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HomingBeeconTextureHandler {

	public static TextureAtlasSprite beecon;

	@SubscribeEvent
	public void onItemIconRegister(TextureStitchEvent.Pre evt) {
		if (evt.map.getTextureType() == 1)
			evt.map.setTextureEntry("erebus:homingBeecon", HomingBeeconTextureHandler.beecon = new TextureHomingBeecon());
	}
}
