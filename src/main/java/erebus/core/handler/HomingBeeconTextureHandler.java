package erebus.core.handler;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.client.event.TextureStitchEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.render.item.TextureHomingBeecon;

@SideOnly(Side.CLIENT)
public class HomingBeeconTextureHandler
{

	public static TextureAtlasSprite beecon;

	@SubscribeEvent
	public void onItemIconRegister(TextureStitchEvent.Pre evt)
	{
		if (evt.map.getTextureType() == 1)
		{
			evt.map.setTextureEntry("erebus:homingBeecon", HomingBeeconTextureHandler.beecon = new TextureHomingBeecon());
		}
	}
}
