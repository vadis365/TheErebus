package erebus.client.render.tileentity;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.client.model.block.ModelGlowingJar;

@SideOnly(Side.CLIENT)
public class TileEntityGlowingJarRenderer extends TileEntitySpecialRenderer {

	private final ModelGlowingJar glowingJar = new ModelGlowingJar();
	private final RenderItem renderItem;
	private final ItemStack glowThingy = new ItemStack(ModItems.erebusMaterials, 1, 13);

	public TileEntityGlowingJarRenderer() {
		renderItem = new RenderItem() {
			@Override
			public boolean shouldBob() {
				return false;
			}
		};
		renderItem.setRenderManager(RenderManager.instance);
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTickTime) {
		EntityItem ghostEntityItem = new EntityItem(tile.worldObj);
		ghostEntityItem.hoverStart = 0.0F;
		ghostEntityItem.setEntityItemStack(glowThingy);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) (y + 0.25F), (float) z + 0.5F);
		GL11.glScalef(1.5F, 1.5F, 1.5F);
		renderItem.doRenderItem(ghostEntityItem, 0, 0, 0, 0, 0);
		GL11.glPopMatrix();

		bindTexture(new ResourceLocation("erebus:textures/special/tiles/glowingJar.png"));
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glScalef(0.7F, -1F, -0.7F);
		glowingJar.render();
		GL11.glPopMatrix();
	}
}