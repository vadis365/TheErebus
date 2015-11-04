package erebus.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.api.PreservableEntityRegistry;
import erebus.api.PreservableEntityRegistry.EntityDimensions;
import erebus.tileentity.TileEntityPreservedBlock;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

@SideOnly(Side.CLIENT)
public class TileEntityPreservedBlockRenderer extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTickTime) {
		Entity entity = ((TileEntityPreservedBlock) tile).getRenderEntity();
		if (entity == null)
			return;

		renderTrappedEntity(entity, x, y, z);
	}

	private void renderTrappedEntity(Entity entity, double x, double y, double z) {
		EntityDimensions dimensions = PreservableEntityRegistry.getEntityDimensions(entity);
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
		GL11.glTranslated(x + xOff, y + yOff, z + zOff);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		GL11.glScalef(scale, scale, scale);

		entity.setLocationAndAngles(0, 0, 0, 0, 0);
		RenderManager.instance.renderEntityWithPosYaw(entity, 0, 0, 0, 0, 0);

		GL11.glPopMatrix();
	}
}