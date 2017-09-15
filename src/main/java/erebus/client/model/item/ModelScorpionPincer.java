package erebus.client.model.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelScorpionPincer extends ModelBase {

	ModelRenderer ClawR4;
	ModelRenderer ClawR5Top;
	ModelRenderer ClawR5Bot;

	public ModelScorpionPincer() {
		textureWidth = 16;
		textureHeight = 16;

		ClawR4 = new ModelRenderer(this, 0, 6);
		ClawR4.addBox(-2F, 0F, -2F, 4, 6, 4);
		ClawR4.setRotationPoint(0F, 0F, 0F);
		setRotation(ClawR4, 0F, 0F, 0F);
		ClawR5Top = new ModelRenderer(this, 11, 0);
		ClawR5Top.addBox(-3F, 5F, 0.5F, 1, 4, 1);
		ClawR5Top.setRotationPoint(0F, 0F, 0F);
		setRotation(ClawR5Top, 0F, 0F, -0.3490659F);
		ClawR5Bot = new ModelRenderer(this, 0, 0);
		ClawR5Bot.addBox(-3F, 5F, -1.5F, 1, 4, 1);
		ClawR5Bot.setRotationPoint(0F, 0F, 0F);
		setRotation(ClawR5Bot, 0F, 0F, -0.3490659F);
	}

	public void render(float unitPixel) {
		ClawR4.render(unitPixel);
		ClawR5Top.render(unitPixel);
		ClawR5Bot.render(unitPixel);
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
