package erebus.client.model.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelTarantula extends ModelBase {

	ModelRenderer Body;
	ModelRenderer RearEnd;
	ModelRenderer Leg1;
	ModelRenderer LegE1;
	ModelRenderer Leg2;
	ModelRenderer LegE2;
	ModelRenderer Leg3;
	ModelRenderer LegE3;
	ModelRenderer Leg4;
	ModelRenderer LegE4;
	ModelRenderer Leg1F;
	ModelRenderer LegEF1;
	ModelRenderer Leg2F;
	ModelRenderer LegEF2;
	ModelRenderer Leg3F;
	ModelRenderer LegEF3;
	ModelRenderer Leg4F;
	ModelRenderer LegEF4;
	ModelRenderer Head;
	ModelRenderer Right_Fang;
	ModelRenderer Left_Fang;
	ModelRenderer Right_Pincerthing;
	ModelRenderer Left_Pincerthing;
	ModelRenderer Left_Spinneret;
	ModelRenderer Right_Spinneret;

	public ModelTarantula() {
		this(0.0F);
	}

	public ModelTarantula(float size) {
		textureWidth = 128;
		textureHeight = 64;

		Body = new ModelRenderer(this, 63, 6);
		Body.addBox(-7F, -6F, -7F, 14, 8, 13, size);
		Body.setRotationPoint(0F, 21F, 0F);
		RearEnd = new ModelRenderer(this, 38, 31);
		RearEnd.addBox(-10F, -6F, -2F, 20, 10, 23, size);
		RearEnd.setRotationPoint(0F, 19F, 8F);
		Leg1 = new ModelRenderer(this, 30, 0);
		Leg1.addBox(0F, -1F, -1F, 7, 2, 2, size);
		Leg1.setRotationPoint(5F, 21F, -7F);
		LegE1 = new ModelRenderer(this, 0, 0);
		LegE1.addBox(0F, -1F, -1F, 13, 2, 2, size);
		LegE1.setRotationPoint(7F, 0F, 0F);
		Leg2 = new ModelRenderer(this, 30, 0);
		Leg2.addBox(0F, -1F, -1F, 7, 2, 2, size);
		Leg2.setRotationPoint(7F, 21F, -3F);
		LegE2 = new ModelRenderer(this, 0, 0);
		LegE2.addBox(0F, -1F, -1F, 13, 2, 2, size);
		LegE2.setRotationPoint(7F, 0F, 0F);
		Leg3 = new ModelRenderer(this, 30, 0);
		Leg3.addBox(0F, -1F, -1F, 7, 2, 2, size);
		Leg3.setRotationPoint(7F, 21F, 1F);
		LegE3 = new ModelRenderer(this, 0, 0);
		LegE3.addBox(0F, -1F, -1F, 13, 2, 2, size);
		LegE3.setRotationPoint(7F, 0F, 0F);
		Leg4 = new ModelRenderer(this, 30, 0);
		Leg4.addBox(0F, -1F, -1F, 7, 2, 2, size);
		Leg4.setRotationPoint(7F, 21F, 5F);
		LegE4 = new ModelRenderer(this, 0, 0);
		LegE4.addBox(0F, -1F, -1F, 13, 2, 2, size);
		LegE4.setRotationPoint(7F, 0F, 0F);
		Leg1F = new ModelRenderer(this, 30, 4);
		Leg1F.addBox(-7F, -1F, -1F, 7, 2, 2, size);
		Leg1F.setRotationPoint(-5F, 21F, -7F);
		LegEF1 = new ModelRenderer(this, 0, 4);
		LegEF1.addBox(-12F, -1F, -1F, 13, 2, 2, size);
		LegEF1.setRotationPoint(-8F, 0F, 0F);
		Leg2F = new ModelRenderer(this, 30, 4);
		Leg2F.addBox(-7F, -1F, -1F, 7, 2, 2, size);
		Leg2F.setRotationPoint(-7F, 21F, -3F);
		LegEF2 = new ModelRenderer(this, 0, 4);
		LegEF2.addBox(-13F, -1F, -1F, 13, 2, 2, size);
		LegEF2.setRotationPoint(-7F, 0F, 0F);
		Leg3F = new ModelRenderer(this, 30, 4);
		Leg3F.addBox(-7F, -1F, -1F, 7, 2, 2, size);
		Leg3F.setRotationPoint(-7F, 21F, 1F);
		LegEF3 = new ModelRenderer(this, 0, 4);
		LegEF3.addBox(-13F, -1F, -1F, 13, 2, 2, size);
		LegEF3.setRotationPoint(-7F, 0F, 0F);
		Leg4F = new ModelRenderer(this, 30, 4);
		Leg4F.addBox(-7F, -1F, -1F, 7, 2, 2, size);
		Leg4F.setRotationPoint(-7F, 21F, 5F);
		LegEF4 = new ModelRenderer(this, 0, 4);
		LegEF4.addBox(-13F, -1F, -1F, 13, 2, 2, size);
		LegEF4.setRotationPoint(-7F, 0F, 0F);
		Head = new ModelRenderer(this, 0, 48);
		Head.addBox(-5.5F, -3F, -2F, 11, 6, 3, size);
		Head.setRotationPoint(0F, 20F, -8F);
		Right_Fang = new ModelRenderer(this, 0, 58);
		Right_Fang.addBox(-1F, 0F, -2F, 2, 4, 2, size);
		Right_Fang.setRotationPoint(1F, -1F, -2F);
		Left_Fang = new ModelRenderer(this, 0, 58);
		Left_Fang.addBox(-1F, 0F, -2F, 2, 4, 2, size);
		Left_Fang.setRotationPoint(-1F, -1F, -2F);
		Right_Pincerthing = new ModelRenderer(this, 10, 57);
		Right_Pincerthing.addBox(-1F, -1F, -5F, 2, 2, 5, size);
		Right_Pincerthing.setRotationPoint(3F, 0F, -2F);
		Left_Pincerthing = new ModelRenderer(this, 10, 57);
		Left_Pincerthing.addBox(-1F, -1F, -5F, 2, 2, 5, size);
		Left_Pincerthing.setRotationPoint(-3F, 0F, -2F);
		Left_Spinneret = new ModelRenderer(this, 24, 57);
		Left_Spinneret.addBox(-1F, -1F, 0F, 2, 2, 5, size);
		Left_Spinneret.setRotationPoint(-2F, 18F, 29F);
		Right_Spinneret = new ModelRenderer(this, 24, 57);
		Right_Spinneret.addBox(-1F, -1F, 0F, 2, 2, 5, size);
		Right_Spinneret.setRotationPoint(2F, 18F, 29F);
		Head.addChild(Left_Pincerthing);
		Head.addChild(Right_Pincerthing);
		Head.addChild(Left_Fang);
		Head.addChild(Right_Fang);
		Leg1.addChild(LegE1);
		Leg2.addChild(LegE2);
		Leg3.addChild(LegE3);
		Leg4.addChild(LegE4);
		Leg1F.addChild(LegEF1);
		Leg2F.addChild(LegEF2);
		Leg3F.addChild(LegEF3);
		Leg4F.addChild(LegEF4);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Body.render(f5);
		RearEnd.render(f5);
		Leg1.render(f5);
		Leg2.render(f5);
		Leg3.render(f5);
		Leg4.render(f5);
		Leg1F.render(f5);
		Leg2F.render(f5);
		Leg3F.render(f5);
		Leg4F.render(f5);
		Head.render(f5);
		Left_Spinneret.render(f5);
		Right_Spinneret.render(f5);
	}

	// Normal Legs
	private final float Pair1Z = 1.4F;
	private final float Pair2Z = 1F;
	private final float Pair3Z = 1.2F;
	private final float Pair4Z = 1F;

	private final float Pair1Y = 0.8F;
	private final float Pair2Y = 0.5F;
	private final float Pair3Y = 0.3F;
	private final float Pair4Y = 0.6F;

	// Extension Legs
	private final float PairE1Z = 0.3F;
	private final float PairE2Z = 1.3F;
	private final float PairE3Z = 1.8F;
	private final float PairE4Z = 1.2F;

	private final float PairE1Y = 1.4F;
	private final float PairE2Y = 0.5F;
	private final float PairE3Y = 0.3F;
	private final float PairE4Y = 0.6F;

	private final float legSpeed = 1.1F;

	@Override
	public void setRotationAngles(float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
		super.setRotationAngles(limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel, null);

		Head.rotateAngleY = rotationYaw / (180F / (float) Math.PI);
		Head.rotateAngleX = rotationPitch / (180F / (float) Math.PI);

		float varY = MathHelper.sin(limbSwing * legSpeed) * 1.4F * prevLimbSwing;
		float varZ = Math.abs(MathHelper.cos(limbSwing * legSpeed)) * 0.8F * prevLimbSwing;

		float varEY = 0;
		float varEZ = 0;

		Leg1.rotateAngleZ = -Pair1Z + varZ;
		Leg2.rotateAngleZ = -Pair2Z + varZ;
		Leg3.rotateAngleZ = -Pair3Z + varZ;
		Leg4.rotateAngleZ = -Pair4Z + varZ;

		Leg1F.rotateAngleZ = Pair1Z - varZ;
		Leg2F.rotateAngleZ = Pair2Z - varZ;
		Leg3F.rotateAngleZ = Pair3Z - varZ;
		Leg4F.rotateAngleZ = Pair4Z - varZ;

		Leg1.rotateAngleY = Pair1Y + varY;
		Leg2.rotateAngleY = Pair2Y - varY;
		Leg3.rotateAngleY = -Pair3Y + varY;
		Leg4.rotateAngleY = -Pair4Y - varY;

		Leg1F.rotateAngleY = -Pair1Y + varY;
		Leg2F.rotateAngleY = -Pair2Y - varY;
		Leg3F.rotateAngleY = Pair3Y + varY;
		Leg4F.rotateAngleY = Pair4Y - varY;

		LegE1.rotateAngleZ = PairE1Z + varEZ;
		LegE2.rotateAngleZ = PairE2Z + varEZ;
		LegE3.rotateAngleZ = PairE3Z + varEZ;
		LegE4.rotateAngleZ = PairE4Z + varEZ;

		LegEF1.rotateAngleZ = -PairE1Z + varEZ;
		LegEF2.rotateAngleZ = -PairE2Z + varEZ;
		LegEF3.rotateAngleZ = -PairE3Z + varEZ;
		LegEF4.rotateAngleZ = -PairE4Z + varEZ;

		LegE1.rotateAngleY = PairE1Y + varEY;
		LegE2.rotateAngleY = PairE2Y + varEY;
		LegE3.rotateAngleY = -PairE3Y + varEY;
		LegE4.rotateAngleY = -PairE4Y + varEY;

		LegEF1.rotateAngleY = -PairE1Y + varEY;
		LegEF2.rotateAngleY = -PairE2Y + varEY;
		LegEF3.rotateAngleY = PairE3Y + varEY;
		LegEF4.rotateAngleY = PairE4Y + varEY;
	}
}
