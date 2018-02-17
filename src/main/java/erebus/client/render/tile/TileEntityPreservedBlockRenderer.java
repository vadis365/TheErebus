package erebus.client.render.tile;

import erebus.api.ErebusAPI;
import erebus.preserved.PreservableEntityRegistry.EntityDimensions;
import erebus.tileentity.TileEntityPreservedBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityPreservedBlockRenderer extends TileEntitySpecialRenderer<TileEntityPreservedBlock> {

	@Override
	public void render(TileEntityPreservedBlock tile, double x, double y, double z, float partialTick, int destroyStage, float alpha) {
		IBlockState state = tile.getWorld().getBlockState(tile.getPos());
		if(tile == null || !tile.hasWorld())
			return;
		Entity entity = tile.getRenderEntity();
		if (entity == null)
			return;
		renderTrappedEntity(entity, x, y, z, 90F * -tile.rotation);
	}
	
	public static void renderTrappedEntity(Entity entity, double x, double y, double z, float rotation) {
		EntityDimensions dimensions = ErebusAPI.preservableEntityRegistry.getEntityDimensions(entity);
		float xOff = 0;
		float yOff = 0;
		float zOff = 0;
		float scale = 1;
		if (dimensions != null) {
			xOff = dimensions.getX();
			yOff = dimensions.getY();
			zOff = dimensions.getZ();
			scale = dimensions.getScale();
		}
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5F, y, z + 0.5F);
		GlStateManager.rotate(180F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(xOff, yOff, zOff);
		GlStateManager.scale(scale, scale, scale);
		Render renderer = Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(entity);
		if (entity instanceof EntityItem) {
			GlStateManager.translate(0.25F, -0.25F, -0.125F);
			GlStateManager.scale(scale * 0.5F, scale* 0.5F, scale* 0.5F);
			((EntityItem) entity).hoverStart = 0;
		}
		renderer.doRender(entity, 0, 0, 0, 0, 0);
		GlStateManager.popMatrix();
	}
}