package erebus.client.model.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.tileentity.TileEntityGlowGem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class ModelGlowGem extends ModelBase {
	ModelRenderer TopRight;
	ModelRenderer RightEdge;
	ModelRenderer BottomRight;
	ModelRenderer BottomEdge;
	ModelRenderer BottomLeft;
	ModelRenderer LeftEdge;
	ModelRenderer TopLeft;
	ModelRenderer TopEdge;
	ModelRenderer Layer2Horiz;
	ModelRenderer Layer2Vert;
	ModelRenderer Layer3Vert;
	ModelRenderer Layer3Horiz;

	public ModelGlowGem() {
		textureWidth = 64;
		textureHeight = 32;

		TopRight = new ModelRenderer(this, 18, 0);
		TopRight.addBox(-5F, 0F, 3F, 1, 1, 1);
		TopRight.setRotationPoint(0F, 23F, 0F);
		RightEdge = new ModelRenderer(this, 3, 2);
		RightEdge.addBox(-6F, 0F, -3F, 1, 1, 6);
		RightEdge.setRotationPoint(0F, 23F, 0F);
		BottomRight = new ModelRenderer(this, 18, 9);
		BottomRight.addBox(-5F, 0F, -4F, 1, 1, 1);
		BottomRight.setRotationPoint(0F, 23F, 0F);
		BottomEdge = new ModelRenderer(this, 23, 9);
		BottomEdge.addBox(-4F, 0F, -5F, 8, 1, 1);
		BottomEdge.setRotationPoint(0F, 23F, 0F);
		BottomLeft = new ModelRenderer(this, 42, 9);
		BottomLeft.addBox(4F, 0F, -4F, 1, 1, 1);
		BottomLeft.setRotationPoint(0F, 23F, 0F);
		LeftEdge = new ModelRenderer(this, 47, 2);
		LeftEdge.addBox(5F, 0F, -3F, 1, 1, 6);
		LeftEdge.setRotationPoint(0F, 23F, 0F);
		TopLeft = new ModelRenderer(this, 42, 0);
		TopLeft.addBox(4F, 0F, 3F, 1, 1, 1);
		TopLeft.setRotationPoint(0F, 23F, 0F);
		TopEdge = new ModelRenderer(this, 23, 0);
		TopEdge.addBox(-4F, 0F, 4F, 8, 1, 1);
		TopEdge.setRotationPoint(0F, 23F, 0F);
		Layer2Horiz = new ModelRenderer(this, 0, 17);
		Layer2Horiz.addBox(-5F, -1F, -3F, 10, 1, 6);
		Layer2Horiz.setRotationPoint(0F, 23F, 0F);
		Layer2Vert = new ModelRenderer(this, 32, 15);
		Layer2Vert.addBox(-4F, -1F, -4F, 8, 1, 8);
		Layer2Vert.setRotationPoint(0F, 23F, 0F);
		Layer3Vert = new ModelRenderer(this, 25, 25);
		Layer3Vert.addBox(-3F, -2F, -3F, 6, 1, 6);
		Layer3Vert.setRotationPoint(0F, 23F, 0F);
		Layer3Horiz = new ModelRenderer(this, 0, 27);
		Layer3Horiz.addBox(-4F, -2F, -2F, 8, 1, 4);
		Layer3Horiz.setRotationPoint(0F, 23F, 0F);
	}

	@SideOnly(Side.CLIENT)
	public void render(TileEntityGlowGem tile) {
		TopRight.render(0.0625F);
		RightEdge.render(0.0625F);
		BottomRight.render(0.0625F);
		BottomEdge.render(0.0625F);
		BottomLeft.render(0.0625F);
		LeftEdge.render(0.0625F);
		TopLeft.render(0.0625F);
		TopEdge.render(0.0625F);
		Layer2Horiz.render(0.0625F);
		Layer2Vert.render(0.0625F);
		Layer3Vert.render(0.0625F);
		Layer3Horiz.render(0.0625F);
	}

}
