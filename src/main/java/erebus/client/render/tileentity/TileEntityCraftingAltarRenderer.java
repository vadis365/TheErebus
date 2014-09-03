package erebus.client.render.tileentity;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelAltar;
import erebus.client.model.block.ModelStone4;
import erebus.tileentity.TileEntityCraftingAltar;

@SideOnly(Side.CLIENT)
public class TileEntityCraftingAltarRenderer extends TileEntitySpecialRenderer
{
	private final ModelAltar model = new ModelAltar();
	private final ModelStone4 stone4 = new ModelStone4();

	private final ResourceLocation ACTIVE = new ResourceLocation("erebus:textures/special/tiles/craftingAltarActive.png");
	private final ResourceLocation NORMAL = new ResourceLocation("erebus:textures/special/tiles/craftingAltar.png");
	private final ResourceLocation GLOW = new ResourceLocation("erebus:textures/special/tiles/craftingAltarGlow.png");

	private final RenderItem renderItem;

	public TileEntityCraftingAltarRenderer()
	{
		renderItem = new RenderItem()
		{
			@Override
			public boolean shouldBob()
			{
				return true;
			}
		};
		renderItem.setRenderManager(RenderManager.instance);
	}

	public void renderAModelAt(TileEntityCraftingAltar tile, double x, double y, double z, float partialTick)
	{
		if (tile.blockMetadata == 1)
		{
			bindTexture(ACTIVE);
		} else
		{
			bindTexture(NORMAL);
		}

		renderMainModel(x, y, z);
		renderStones(x, y, z, tile.rotation);

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.75F, (float) y + 0.75F, (float) z + 0.5F);
		renderItems(tile);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
		GL11.glDepthMask(true);
		char c0 = 61680;
		int j = c0 % 65536;
		int k = c0 / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j / 1.0F, k / 1.0F);
		bindTexture(GLOW);
		renderMainModel(x, y, z);
		renderStones(x, y, z, tile.rotation);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glPopMatrix();
	}

	private void renderItems(TileEntityCraftingAltar tile)
	{
		float angle = tile.time;
		if (tile.getStackInSlot(9) == null)
		{
			int count = 0;
			for (int i = 1; i < 9; i++)
			{
				EntityItem item = tile.getItemForRendering(i);
				if (item != null)
				{
					count++;
				}
			}

			if (tile.getItemForRendering(0) != null)
			{
				GL11.glTranslatef(0, -0.25F, 0);
				renderCentreItem(tile.getItemForRendering(0), 0);
			}

			GL11.glTranslated(-0.25, 0.5, 0);
			for (int i = 1; i < 9; i++)
			{
				EntityItem item = tile.getItemForRendering(i);
				if (item != null)
				{
					GL11.glPushMatrix();
					GL11.glRotated(360F / count * (i + 1) + tile.getWorldObj().getWorldTime(), 0, 1, 0);
					GL11.glTranslated(Math.cos(Math.toRadians(angle)), 0, 0);
					renderItem.doRender(item, 0, 0, 0, 0, 0);
					GL11.glPopMatrix();
				}
			}
		} else
		{
			GL11.glRotated(90, 1, 0, 0);
			GL11.glScaled(1.5, 1.5, 1.5);
			GL11.glTranslatef(0.05F, -0.56F, -0.15F);
			renderCentreItem(tile.getItemForRendering(9), 0);
		}
	}

	private void renderCentreItem(EntityItem item, long time)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.25F, 0.25F, 0);
		GL11.glRotatef(time, 0, 1, 0);
		GL11.glScaled(1.5, 1.5, 1.5);
		renderItem.doRender(item, 0, 0, 0, 0, 0);
		GL11.glPopMatrix();
	}

	private void renderMainModel(double x, double y, double z)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 1.5F, z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		model.renderAll();
		GL11.glPopMatrix();
	}

	private void renderStones(double x, double y, double z, float rotation)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 1.5F, z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
		stone4.renderAll();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick)
	{
		renderAModelAt((TileEntityCraftingAltar) tile, x, y, z, partialTick);
	}
}