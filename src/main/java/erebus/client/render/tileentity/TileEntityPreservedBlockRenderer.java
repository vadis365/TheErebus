package erebus.client.render.tileentity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.api.ErebusAPI;
import erebus.preserved.PreservableEntityRegistry.EntityDimensions;
import erebus.tileentity.TileEntityPreservedBlock;

@SideOnly(Side.CLIENT)
public class TileEntityPreservedBlockRenderer extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTickTime) {
		Entity entity = ((TileEntityPreservedBlock) tile).getRenderEntity();
		if (entity == null)
			return;

		renderTrappedEntity(entity, x, y, z, tile.getBlockMetadata());
	}

	public static void renderTrappedEntity(Entity entity, double x, double y, double z, int meta) {
		EntityDimensions dimensions = ErebusAPI.preservableEntityRegistry.getEntityDimensions(entity);
		float xOff = 0.5F;
		float yOff = 0.0625F;
		float zOff = 0.5F;
		float scale = 0.35F;
		if (dimensions != null) {
			xOff = dimensions.getX();
			yOff = dimensions.getY();
			zOff = dimensions.getZ();
			scale = dimensions.getScale();
		}

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y, z + 0.5F);
		switch (meta) {
			case 3:
			case 7:
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				break;
			case 5:
			case 9:
				GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
				break;
			case 4:
			case 8:
				GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
				break;
		}

		GL11.glScalef(scale, scale, scale);
		GL11.glTranslated(0, yOff, 0.2F); // x and z offsets will need changing in the config and re-adding here
		Render renderer = RenderManager.instance.getEntityRenderObject(entity);
		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		renderer.doRender(entity, 0, 0, 0, 0, 0);
		GL11.glPopAttrib();
		
		GL11.glPopMatrix();
	}
}