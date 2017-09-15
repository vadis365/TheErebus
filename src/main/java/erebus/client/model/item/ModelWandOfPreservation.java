package erebus.client.model.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class ModelWandOfPreservation extends ModelBase {

	ModelRenderer topMid;
	ModelRenderer topMain;
	ModelRenderer topBase;
	ModelRenderer shaft1;
	ModelRenderer shaft2;
	ModelRenderer pommel1;
	ModelRenderer pommel2;

	public ModelWandOfPreservation() {
		textureWidth = 64;
		textureHeight = 32;

		topMid = new ModelRenderer(this, 21, 0);
		topMid.addBox(-3F, 1F, -3F, 6, 6, 6);
		topMid.setRotationPoint(0F, 0F, 0F);
		setRotation(topMid, 0F, 0F, 0F);
		topMain = new ModelRenderer(this, 0, 0);
		topMain.addBox(-2.5F, 2F, -2.5F, 5, 6, 5);
		topMain.setRotationPoint(0F, 0F, 0F);
		setRotation(topMain, 0F, 0.7853982F, 0F);
		topBase = new ModelRenderer(this, 46, 5);
		topBase.addBox(-1.5F, 8F, -1.5F, 3, 1, 3);
		topBase.setRotationPoint(0F, 0F, 0F);
		setRotation(topBase, 0F, 0F, 0F);
		shaft1 = new ModelRenderer(this, 0, 14);
		shaft1.addBox(-1F, 9F, -1F, 2, 11, 2);
		shaft1.setRotationPoint(0F, 0F, 0F);
		setRotation(shaft1, 0F, 0F, 0F);
		shaft2 = new ModelRenderer(this, 9, 14);
		shaft2.addBox(-1F, 9F, -1F, 2, 11, 2);
		shaft2.setRotationPoint(0F, 0F, 0F);
		setRotation(shaft2, 0F, 0.7853982F, 0F);
		pommel1 = new ModelRenderer(this, 21, 13);
		pommel1.addBox(-1.5F, 21F, -1.5F, 3, 3, 3);
		pommel1.setRotationPoint(0F, 0F, 0F);
		setRotation(pommel1, 0F, 0F, 0F);
		pommel2 = new ModelRenderer(this, 21, 20);
		pommel2.addBox(-1.5F, 20F, -1.5F, 3, 3, 3);
		pommel2.setRotationPoint(0F, 0F, 0F);
		setRotation(pommel2, 0F, 0.7853982F, 0F);
	}

	public void render(float unitPixel) {
		topMid.render(unitPixel);
		topMain.render(unitPixel);
		topBase.render(unitPixel);
		shaft1.render(unitPixel);
		shaft2.render(unitPixel);
		pommel1.render(unitPixel);
		pommel2.render(unitPixel);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
