package erebus.client.model.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelWarHammer extends ModelBase {

	ModelRenderer head;
	ModelRenderer head2;
	ModelRenderer handle;
	ModelRenderer counterWeight;

	public ModelWarHammer() {
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-2F, -4F, -4.5F, 4, 4, 9);
		head.setRotationPoint(0F, 9F, 0F);
		setRotation(head, 0F, 0F, 0F);
		head2 = new ModelRenderer(this, 0, 14);
		head2.addBox(-1F, 0F, -1F, 2, 1, 2);
		head2.setRotationPoint(0F, 9F, 0F);
		setRotation(head2, 0F, 0F, 0F);
		handle = new ModelRenderer(this, 27, 0);
		handle.addBox(-0.5F, 1F, -0.5F, 1, 12, 1);
		handle.setRotationPoint(0F, 9F, 0F);
		setRotation(handle, 0F, 0F, 0F);
		counterWeight = new ModelRenderer(this, 0, 18);
		counterWeight.addBox(-1F, 13F, -1F, 2, 2, 2);
		counterWeight.setRotationPoint(0F, 9F, 0F);
		setRotation(counterWeight, 0F, 0F, 0F);
	}

	public void render() {
		head.render(0.0625F);
		head2.render(0.0625F);
		handle.render(0.0625F);
		counterWeight.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par6Entity) {
	}

}
