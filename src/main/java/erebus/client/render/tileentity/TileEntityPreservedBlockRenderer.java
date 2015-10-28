package erebus.client.render.tileentity;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.tileentity.TileEntityPreservedBlock;

@SideOnly(Side.CLIENT)
public class TileEntityPreservedBlockRenderer extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTickTime) {
		NBTTagCompound nbt = ((TileEntityPreservedBlock) tile).getEntityNBT();
		if (nbt == null)
			return;

		Entity entity = EntityList.createEntityFromNBT(nbt, tile.getWorldObj());
		renderTrappedEntity(entity, x, y, z);
	}

	private void renderTrappedEntity(Entity entity, double x, double y, double z) {
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y + 0.125, z + 0.5);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);

		GL11.glScaled(0.5, 0.5, 0.5);

		entity.setLocationAndAngles(0, 0, 0, 0, 0);
		RenderManager.instance.renderEntityWithPosYaw(entity, 0, 0, 0, 0, 0);

		GL11.glPopMatrix();
	}
}