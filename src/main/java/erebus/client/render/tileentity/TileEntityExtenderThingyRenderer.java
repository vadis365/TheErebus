package erebus.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.client.model.block.ModelExtenderThingy;
import erebus.lib.EnumWood;
import erebus.tileentity.TileEntityExtenderThingy;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class TileEntityExtenderThingyRenderer extends TileEntitySpecialRenderer {

	private final ModelExtenderThingy ModelExtenderThingy = new ModelExtenderThingy();
	private final RenderBlocks blockRenderer = new RenderBlocks();

	public void renderAModelAt(TileEntityExtenderThingy tile, double x, double y, double z, float f) {
		bindTexture(new ResourceLocation("erebus:textures/special/tiles/extenderThingy.png"));
		int meta = tile.getBlockMetadata();
		switch (meta) {
			case 0:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y - 0.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(180F, 0.0F, 0F, 1F);
				GL11.glRotatef(-180F, 0.0F, 1F, 0F);
				ModelExtenderThingy.render2(tile);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 0.5625F, z + 0.5D);
				GL11.glScaled(1.0D, 0.875D, 1.0D);
				bindTexture(TextureMap.locationBlocksTexture);
				blockRenderer.renderBlockAsItem(ModBlocks.planks, EnumWood.Bamboo.ordinal(), 1.0F);
				GL11.glPopMatrix();
				break;
			case 1:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 1.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(180F, 0.0F, 1F, 0F);
				ModelExtenderThingy.render2(tile);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 0.4375F, z + 0.5D);
				GL11.glScaled(1.0D, 0.875D, 1.0D);
				bindTexture(TextureMap.locationBlocksTexture);
				blockRenderer.renderBlockAsItem(ModBlocks.planks, EnumWood.Bamboo.ordinal(), 1.0F);
				GL11.glPopMatrix();
				break;
			case 2:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 1.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(180F, 0.0F, 1F, 0F);
				ModelExtenderThingy.render(tile);
				GL11.glPopMatrix();
				break;
			case 3:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 1.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(0F, 0.0F, 1F, 0F);
				ModelExtenderThingy.render(tile);
				GL11.glPopMatrix();
				break;
			case 4:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 1.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(90F, 0.0F, 1F, 0F);
				ModelExtenderThingy.render(tile);
				GL11.glPopMatrix();
				break;
			case 5:
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 1.5F, z + 0.5D);
				GL11.glScalef(1F, -1F, -1F);
				GL11.glRotatef(-90F, 0.0F, 1F, 0F);
				ModelExtenderThingy.render(tile);
				GL11.glPopMatrix();
				break;
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float meta) {
		renderAModelAt((TileEntityExtenderThingy) tile, x, y, z, meta);
	}
}