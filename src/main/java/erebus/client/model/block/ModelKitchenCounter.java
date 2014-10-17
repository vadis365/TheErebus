package erebus.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelKitchenCounter extends ModelBase {
	ModelRenderer Base;
	ModelRenderer Top;
	ModelRenderer BottomBlender;
	ModelRenderer BlenderMiddle;
	ModelRenderer Lid1;
	ModelRenderer HoneyJar;
	ModelRenderer HoneyJarLiquid;
	ModelRenderer MilkJar;
	ModelRenderer AntivenomJar;
	ModelRenderer BeetleJuiceJar;
	ModelRenderer MilkJarLiquid;
	ModelRenderer BeetleJuiceLiquid;
	ModelRenderer AntivenomLiquid;
	ModelRenderer HandleBottom;
	ModelRenderer HandleTop;
	ModelRenderer handleside;
	ModelRenderer HoneyLid;
	ModelRenderer MilkLid;
	ModelRenderer AntiVenomLid;
	ModelRenderer BeetleJuiceLid;

	public ModelKitchenCounter() {
		textureWidth = 256;
		textureHeight = 128;

		Base = new ModelRenderer(this, 0, 0);
		Base.addBox(0F, 0F, 0F, 16, 15, 16);
		Base.setRotationPoint(-8F, 9F, -8F);
		setRotation(Base, 0F, 0F, 0F);
		Top = new ModelRenderer(this, 66, 0);
		Top.addBox(0F, 0F, 0F, 18, 1, 18);
		Top.setRotationPoint(-9F, 8F, -9F);
		setRotation(Top, 0F, 0F, 0F);
		BottomBlender = new ModelRenderer(this, 43, 116);
		BottomBlender.addBox(0F, 0F, 0F, 6, 3, 6);
		BottomBlender.setRotationPoint(-3F, 5F, -3F);
		setRotation(BottomBlender, 0F, 0F, 0F);
		BlenderMiddle = new ModelRenderer(this, 43, 100);
		BlenderMiddle.addBox(0F, 0F, 0F, 4, 7, 4);
		BlenderMiddle.setRotationPoint(-2F, -2F, -2F);
		setRotation(BlenderMiddle, 0F, 0F, 0F);
		Lid1 = new ModelRenderer(this, 40, 87);
		Lid1.addBox(0F, -3F, 0F, 6, 1, 6);
		Lid1.setRotationPoint(-3F, 0F, -3F);
		setRotation(Lid1, 0F, 0F, 0F);
		HoneyJar = new ModelRenderer(this, 87, 71);
		HoneyJar.addBox(0F, 0F, 0F, 3, 4, 3);
		HoneyJar.setRotationPoint(-1F, 4F, 6F);
		setRotation(HoneyJar, 0F, 0.5235988F, 0F);
		HoneyJarLiquid = new ModelRenderer(this, 108, 60);
		HoneyJarLiquid.addBox(0F, 0F, 0F, 1, 4, 1);
		HoneyJarLiquid.setRotationPoint(0.4F, 4F, 6.35F);
		setRotation(HoneyJarLiquid, 0F, 0.5235988F, 0F);
		MilkJar = new ModelRenderer(this, 87, 71);
		MilkJar.addBox(0F, 0F, 0F, 3, 4, 3);
		MilkJar.setRotationPoint(3F, 4F, 4F);
		setRotation(MilkJar, 0F, 0.7853982F, 0F);
		AntivenomJar = new ModelRenderer(this, 87, 71);
		AntivenomJar.addBox(0F, 0F, 0F, 3, 4, 3);
		AntivenomJar.setRotationPoint(1F, 4F, -8F);
		setRotation(AntivenomJar, 0F, -0.5235988F, 0F);
		BeetleJuiceJar = new ModelRenderer(this, 87, 71);
		BeetleJuiceJar.addBox(0F, 0F, 0F, 3, 4, 3);
		BeetleJuiceJar.setRotationPoint(3F, 4F, -4F);
		setRotation(BeetleJuiceJar, 0F, 0.7853982F, 0F);
		MilkJarLiquid = new ModelRenderer(this, 108, 50);
		MilkJarLiquid.addBox(0F, 0F, 0F, 1, 4, 1);
		MilkJarLiquid.setRotationPoint(4.5F, 8F, 4F);
		setRotation(MilkJarLiquid, 0F, 0.7853982F, 0F);
		BeetleJuiceLiquid = new ModelRenderer(this, 108, 62);
		BeetleJuiceLiquid.addBox(0F, 0F, 0F, 1, 4, 1);
		BeetleJuiceLiquid.setRotationPoint(4.5F, 4F, -4F);
		setRotation(BeetleJuiceLiquid, 0F, 0.7853982F, 0F);
		AntivenomLiquid = new ModelRenderer(this, 108, 50);
		AntivenomLiquid.addBox(0F, 0F, 0F, 1, 4, 1);
		AntivenomLiquid.setRotationPoint(1.4F, 8F, -6.6F);
		setRotation(AntivenomLiquid, 0F, -0.5235988F, 0F);
		HandleBottom = new ModelRenderer(this, 0, 72);
		HandleBottom.addBox(0F, 0F, 0F, 2, 1, 1);
		HandleBottom.setRotationPoint(-1F, 3F, -3F);
		setRotation(HandleBottom, 0F, 0F, 0F);
		HandleTop = new ModelRenderer(this, 0, 72);
		HandleTop.addBox(0F, 0F, 0F, 2, 1, 1);
		HandleTop.setRotationPoint(-1F, -1F, -3F);
		setRotation(HandleTop, 0F, 0F, 0F);
		handleside = new ModelRenderer(this, 0, 61);
		handleside.addBox(0F, 0F, 0F, 2, 3, 1);
		handleside.setRotationPoint(-1F, 0F, -4F);
		setRotation(handleside, 0F, 0F, 0F);
		HoneyLid = new ModelRenderer(this, 30, 40);
		HoneyLid.addBox(0F, 0F, 0F, 2, 1, 2);
		HoneyLid.setRotationPoint(-0.3F, 3F, 6.3F);
		setRotation(HoneyLid, 0F, 0.5235988F, 0F);
		MilkLid = new ModelRenderer(this, 30, 40);
		MilkLid.addBox(0F, 0F, 0F, 2, 1, 2);
		MilkLid.setRotationPoint(3.7F, 3F, 4F);
		setRotation(MilkLid, 0F, 0.7853982F, 0F);
		AntiVenomLid = new ModelRenderer(this, 30, 40);
		AntiVenomLid.addBox(0F, 0F, 0F, 2, 1, 2);
		AntiVenomLid.setRotationPoint(1.3F, 3F, -7.3F);
		setRotation(AntiVenomLid, 0F, -0.5235988F, 0F);
		BeetleJuiceLid = new ModelRenderer(this, 30, 40);
		BeetleJuiceLid.addBox(0F, 0F, 0F, 2, 1, 2);
		BeetleJuiceLid.setRotationPoint(3.7F, 3F, -4F);
		setRotation(BeetleJuiceLid, 0F, 0.7853982F, 0F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void renderAll() {
		Base.render(0.0625F);
		Top.render(0.0625F);
		BottomBlender.render(0.0625F);
		BlenderMiddle.render(0.0625F);
		Lid1.render(0.0625F);
		HoneyJar.render(0.0625F);
		HoneyJarLiquid.render(0.0625F);
		MilkJar.render(0.0625F);
		AntivenomJar.render(0.0625F);
		BeetleJuiceJar.render(0.0625F);
		MilkJarLiquid.render(0.0625F);
		BeetleJuiceLiquid.render(0.0625F);
		AntivenomLiquid.render(0.0625F);
		HandleBottom.render(0.0625F);
		handleside.render(0.0625F);
		HoneyLid.render(0.0625F);
		MilkLid.render(0.0625F);
		AntiVenomLid.render(0.0625F);
		BeetleJuiceLid.render(0.0625F);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
	}
}
