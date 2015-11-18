package erebus.client.model.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class ModelPortalStaff extends ModelBase {

	public ModelRenderer[] boxes = new ModelRenderer[8];

	public ModelPortalStaff() {
		textureWidth = 64;
		textureHeight = 32;

		boxes[0] = new ModelRenderer(this, 0, 0);
		boxes[0].addBox(-2F, -2F, -2F, 4, 4, 4);
		boxes[0].setRotationPoint(0f, 0f, 0f);
		boxes[1] = new ModelRenderer(this, 0, 8);
		boxes[1].addBox(-0.5F, 0F, -0.5F, 1, 14, 1);
		boxes[1].setRotationPoint(0f, 0f, 0f);
		boxes[2] = new ModelRenderer(this, 16, 4);
		boxes[2].addBox(-1.5F, 2F, -1.5F, 3, 1, 3);
		boxes[2].setRotationPoint(0f, 0f, 0f);
		boxes[3] = new ModelRenderer(this, 4, 20);
		boxes[3].addBox(-1F, 12F, -1F, 2, 1, 2);
		boxes[3].setRotationPoint(0f, 0f, 0f);
		boxes[4] = new ModelRenderer(this, 5, 9);
		boxes[4].addBox(-0.5F, -0.5F, -2.5F, 1, 3, 1);
		boxes[4].setRotationPoint(0f, 0f, 0f);
		boxes[5] = new ModelRenderer(this, 5, 9);
		boxes[5].addBox(-0.5F, -0.5F, 1.5F, 1, 3, 1);
		boxes[5].setRotationPoint(0f, 0f, 0f);
		boxes[6] = new ModelRenderer(this, 5, 9);
		boxes[6].addBox(-2.5F, -0.5F, -0.5F, 1, 3, 1);
		boxes[6].setRotationPoint(0f, 0f, 0f);
		boxes[7] = new ModelRenderer(this, 5, 9);
		boxes[7].addBox(1.5F, -0.5F, -0.5F, 1, 3, 1);
		boxes[7].setRotationPoint(0f, 0f, 0f);
	}

	public void render() {
		for (ModelRenderer box : boxes)
			box.render(0.0625f);
	}
}
