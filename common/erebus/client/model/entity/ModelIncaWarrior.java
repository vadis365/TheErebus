package erebus.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelIncaWarrior extends ModelBase {

	// fields
	ModelRenderer BELT;
	ModelRenderer BELT1;
	ModelRenderer BELT2;
	ModelRenderer CROWN;
	ModelRenderer FEATHER1;
	ModelRenderer FEATHER2;
	ModelRenderer FEATHER3;
	ModelRenderer FEATHER4;
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;

	public ModelIncaWarrior() {
		textureWidth = 75;
		textureHeight = 45;

		BELT = new ModelRenderer(this, 10, 32);
		BELT.addBox(-4F, 0F, -3F, 8, 1, 6);
		BELT.setRotationPoint(0F, 8F, 0F);
		BELT.setTextureSize(75, 45);
		BELT.mirror = true;
		setRotation(BELT, 0F, 0F, 0F);
		BELT1 = new ModelRenderer(this, 0, 32);
		BELT1.addBox(-2F, 0F, 0F, 4, 8, 0);
		BELT1.setRotationPoint(0F, 8F, -3F);
		BELT1.setTextureSize(75, 45);
		BELT1.mirror = true;
		setRotation(BELT1, 0F, 0F, 0F);
		BELT2 = new ModelRenderer(this, 0, 32);
		BELT2.addBox(-2F, 0F, 0F, 4, 8, 0);
		BELT2.setRotationPoint(0F, 8F, 3F);
		BELT2.setTextureSize(75, 45);
		BELT2.mirror = true;
		setRotation(BELT2, 0F, 0F, 0F);
		CROWN = new ModelRenderer(this, 34, 0);
		CROWN.addBox(-5F, 0F, -5F, 10, 1, 10);
		CROWN.setRotationPoint(0F, -6F, 1F);
		CROWN.setTextureSize(75, 45);
		CROWN.mirror = true;
		setRotation(CROWN, -0.6108652F, 0F, 0F);
		FEATHER1 = new ModelRenderer(this, 71, 0);
		FEATHER1.addBox(4F, -11F, -5F, 0, 6, 1);
		FEATHER1.setRotationPoint(0F, 0F, 0F);
		FEATHER1.setTextureSize(75, 45);
		FEATHER1.mirror = true;
		setRotation(FEATHER1, -0.6108652F, 0F, 0F);
		FEATHER2 = new ModelRenderer(this, 71, 0);
		FEATHER2.addBox(-4F, -11F, 0F, 0, 6, 1);
		FEATHER2.setRotationPoint(0F, 0F, 0F);
		FEATHER2.setTextureSize(75, 45);
		FEATHER2.mirror = true;
		setRotation(FEATHER2, -0.6108652F, 0F, 0F);
		FEATHER3 = new ModelRenderer(this, 35, 0);
		FEATHER3.addBox(-1F, -11F, -7F, 2, 6, 0);
		FEATHER3.setRotationPoint(0F, 0F, 0F);
		FEATHER3.setTextureSize(75, 45);
		FEATHER3.mirror = true;
		setRotation(FEATHER3, -0.6108652F, 0F, 0F);
		FEATHER4 = new ModelRenderer(this, 71, 0);
		FEATHER4.addBox(-4F, -11F, -5F, 0, 6, 1);
		FEATHER4.setRotationPoint(0F, 0F, 0F);
		FEATHER4.setTextureSize(75, 45);
		FEATHER4.mirror = true;
		setRotation(FEATHER4, -0.6108652F, 0F, 0F);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, 0F, 0F);
		head.setTextureSize(75, 45);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 16, 16);
		body.addBox(-4F, 0F, -2F, 8, 12, 4);
		body.setRotationPoint(0F, 0F, 0F);
		body.setTextureSize(75, 45);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		rightarm = new ModelRenderer(this, 40, 16);
		rightarm.addBox(-1F, -2F, -1F, 2, 12, 2);
		rightarm.setRotationPoint(-5F, 2F, 0F);
		rightarm.setTextureSize(75, 45);
		rightarm.mirror = true;
		setRotation(rightarm, 0F, 0F, 0F);
		leftarm = new ModelRenderer(this, 40, 16);
		leftarm.addBox(-1F, -2F, -1F, 2, 12, 2);
		leftarm.setRotationPoint(5F, 2F, 0F);
		leftarm.setTextureSize(75, 45);
		leftarm.mirror = true;
		setRotation(leftarm, 0F, 0F, 0F);
		rightleg = new ModelRenderer(this, 0, 16);
		rightleg.addBox(-1F, 0F, -1F, 2, 12, 2);
		rightleg.setRotationPoint(-2F, 12F, 0F);
		rightleg.setTextureSize(75, 45);
		rightleg.mirror = true;
		setRotation(rightleg, 0F, 0F, 0F);
		leftleg = new ModelRenderer(this, 0, 16);
		leftleg.addBox(-1F, 0F, -1F, 2, 12, 2);
		leftleg.setRotationPoint(2F, 12F, 0F);
		leftleg.setTextureSize(75, 45);
		leftleg.mirror = true;
		setRotation(leftleg, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		BELT.render(f5);
		BELT1.render(f5);
		BELT2.render(f5);
		CROWN.render(f5);
		FEATHER1.render(f5);
		FEATHER2.render(f5);
		FEATHER3.render(f5);
		FEATHER4.render(f5);
		head.render(f5);
		body.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
