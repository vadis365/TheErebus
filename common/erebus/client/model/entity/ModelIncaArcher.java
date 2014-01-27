package erebus.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelIncaArcher extends ModelBase {

	// fields
	ModelRenderer BELT;
	ModelRenderer BELT1;
	ModelRenderer BELT2;
	ModelRenderer CROWN;
	ModelRenderer TURBANT;
	ModelRenderer QUIVER;
	ModelRenderer ARROW;
	ModelRenderer GOLD_PIECE;
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;

	public ModelIncaArcher() {
		textureWidth = 128;
		textureHeight = 60;

		BELT = new ModelRenderer(this, 10, 32);
		BELT.addBox(-4F, 0F, -3F, 8, 1, 6);
		BELT.setRotationPoint(0F, 8F, 0F);
		BELT.setTextureSize(128, 60);
		BELT.mirror = true;
		setRotation(BELT, 0F, 0F, 0F);
		BELT1 = new ModelRenderer(this, 0, 32);
		BELT1.addBox(-2F, 0F, 0F, 4, 8, 0);
		BELT1.setRotationPoint(0F, 8F, -3F);
		BELT1.setTextureSize(128, 60);
		BELT1.mirror = true;
		setRotation(BELT1, 0F, 0F, 0F);
		BELT2 = new ModelRenderer(this, 0, 32);
		BELT2.addBox(-2F, 0F, 0F, 4, 8, 0);
		BELT2.setRotationPoint(0F, 8F, 3F);
		BELT2.setTextureSize(128, 60);
		BELT2.mirror = true;
		setRotation(BELT2, 0F, 0F, 0F);
		CROWN = new ModelRenderer(this, 34, 0);
		CROWN.addBox(-5F, 0F, -5F, 10, 1, 10);
		CROWN.setRotationPoint(0F, -6F, 1F);
		CROWN.setTextureSize(128, 60);
		CROWN.mirror = true;
		setRotation(CROWN, -0.6108652F, 0F, 0F);
		TURBANT = new ModelRenderer(this, 76, 0);
		TURBANT.addBox(-5F, -9F, -7F, 10, 4, 10);
		TURBANT.setRotationPoint(0F, 0F, 0F);
		TURBANT.setTextureSize(128, 60);
		TURBANT.mirror = true;
		setRotation(TURBANT, -0.6108652F, 0F, 0F);
		QUIVER = new ModelRenderer(this, 9, 43);
		QUIVER.addBox(-2F, 0F, 0F, 4, 12, 2);
		QUIVER.setRotationPoint(-6F, 0F, 2F);
		QUIVER.setTextureSize(128, 60);
		QUIVER.mirror = true;
		setRotation(QUIVER, 0F, 0F, -0.6981317F);
		ARROW = new ModelRenderer(this, 0, 43);
		ARROW.addBox(-2F, -4F, 0F, 4, 4, 0);
		ARROW.setRotationPoint(-6F, 0F, 3F);
		ARROW.setTextureSize(128, 60);
		ARROW.mirror = true;
		setRotation(ARROW, 0F, 0F, -0.6981317F);
		GOLD_PIECE = new ModelRenderer(this, 0, 0);
		GOLD_PIECE.addBox(-1F, -10F, -8F, 2, 5, 1);
		GOLD_PIECE.setRotationPoint(0F, 0F, 0F);
		GOLD_PIECE.setTextureSize(128, 60);
		GOLD_PIECE.mirror = true;
		setRotation(GOLD_PIECE, -0.6108652F, 0F, 0F);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, 0F, 0F);
		head.setTextureSize(128, 60);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 16, 16);
		body.addBox(-4F, 0F, -2F, 8, 12, 4);
		body.setRotationPoint(0F, 0F, 0F);
		body.setTextureSize(128, 60);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		rightarm = new ModelRenderer(this, 40, 16);
		rightarm.addBox(-1F, -2F, -1F, 2, 12, 2);
		rightarm.setRotationPoint(-5F, 2F, 0F);
		rightarm.setTextureSize(128, 60);
		rightarm.mirror = true;
		setRotation(rightarm, 0F, 0F, 0F);
		leftarm = new ModelRenderer(this, 40, 16);
		leftarm.addBox(-1F, -2F, -1F, 2, 12, 2);
		leftarm.setRotationPoint(5F, 2F, 0F);
		leftarm.setTextureSize(128, 60);
		leftarm.mirror = true;
		setRotation(leftarm, 0F, 0F, 0F);
		rightleg = new ModelRenderer(this, 0, 16);
		rightleg.addBox(-1F, 0F, -1F, 2, 12, 2);
		rightleg.setRotationPoint(-2F, 12F, 0F);
		rightleg.setTextureSize(128, 60);
		rightleg.mirror = true;
		setRotation(rightleg, 0F, 0F, 0F);
		leftleg = new ModelRenderer(this, 0, 16);
		leftleg.addBox(-1F, 0F, -1F, 2, 12, 2);
		leftleg.setRotationPoint(2F, 12F, 0F);
		leftleg.setTextureSize(128, 60);
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
		TURBANT.render(f5);
		QUIVER.render(f5);
		ARROW.render(f5);
		GOLD_PIECE.render(f5);
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
