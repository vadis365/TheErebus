package erebus.client.render.tileentity;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.tileentity.TileEntitySpawner;

@SideOnly(Side.CLIENT)
public class TileEntitySpawnerRender extends TileEntitySpecialRenderer {

	private double rotation0;
	private double rotation1;

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float rotation) {
		renderTileEntityMobSpawner((TileEntitySpawner) tile, x, y, z, rotation);
	}

	public void renderTileEntityMobSpawner(TileEntitySpawner tile, double x, double y, double z, float rotation) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y, (float) z + 0.5F);

		Entity entity = tile.getEntityToRender();
		rotation1 = rotation0;
		rotation0 = (rotation0 + 1000.0F / (tile.getSpawnDelay() + 200.0F)) % 360.0D;

		if (entity != null) {
			entity.setWorld(tile.worldObj);
			float f1 = 0.4375F;
			GL11.glTranslatef(0.0F, 0.4F, 0.0F);
			GL11.glRotatef((float) (rotation1 + (rotation0 - rotation1) * rotation) * 10.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
			GL11.glTranslatef(0.0F, -0.4F, 0.0F);
			GL11.glScalef(f1, f1, f1);
			entity.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
			RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, rotation);
		}

		GL11.glPopMatrix();
	}
}
