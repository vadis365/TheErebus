package erebus.client.model.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelWoodlouseBall extends ModelBase {

	ModelRenderer UpDown;
	ModelRenderer ForeAft;
	ModelRenderer Main;

	public ModelWoodlouseBall() {
		textureWidth = 64;
		textureHeight = 64;

		UpDown = new ModelRenderer(this, 0, 0);
		UpDown.addBox(-3.5F, -1.5F, -3.5F, 7, 13, 7);
		UpDown.setRotationPoint(0F, 13F, 0F);
		setRotation(UpDown, 0F, 0F, 0F);
		ForeAft = new ModelRenderer(this, 0, 42);
		ForeAft.addBox(-3.5F, 1.5F, -6.5F, 7, 7, 13);
		ForeAft.setRotationPoint(0F, 13F, 0F);
		setRotation(ForeAft, 0F, 0F, 0F);
		Main = new ModelRenderer(this, 0, 21);
		Main.addBox(-5F, 0F, -5F, 10, 10, 10);
		Main.setRotationPoint(0F, 13F, 0F);
		setRotation(Main, 0F, 0F, 0F);

	}

	public void render(float unitPixel) {
		UpDown.render(unitPixel);
		ForeAft.render(unitPixel);
		Main.render(unitPixel);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
	}
}