package erebus.client.model.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityBlackAnt;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelBlackAnt extends ModelBase {
	ModelRenderer Thx;
	ModelRenderer ThxTop;
	ModelRenderer ThxS;
	ModelRenderer Thx2Ab;
	ModelRenderer Ab;
	ModelRenderer AbF;
	ModelRenderer AbSide;
	ModelRenderer AbTop;
	ModelRenderer AbBack;
	ModelRenderer Neck;
	ModelRenderer Head1;
	ModelRenderer Head2;
	ModelRenderer RMandible1;
	ModelRenderer RMandible2;
	ModelRenderer LMandible1;
	ModelRenderer LMandible2;
	ModelRenderer Eyes;
	ModelRenderer AntLS;
	ModelRenderer AntLE;
	ModelRenderer AntRS;
	ModelRenderer AntRE;
	ModelRenderer LBL1;
	ModelRenderer LBL2;
	ModelRenderer LBL3;
	ModelRenderer LBL4;
	ModelRenderer LML1;
	ModelRenderer LML2;
	ModelRenderer LML3;
	ModelRenderer LML4;
	ModelRenderer LFL1;
	ModelRenderer LFL2;
	ModelRenderer LFL3;
	ModelRenderer LFL4;
	ModelRenderer RFL1;
	ModelRenderer RFL2;
	ModelRenderer RFL3;
	ModelRenderer RFL4;
	ModelRenderer RML1;
	ModelRenderer RML2;
	ModelRenderer RML3;
	ModelRenderer RML4;
	ModelRenderer RBL1;
	ModelRenderer RBL2;
	ModelRenderer RBL3;
	ModelRenderer RBL4;
	ModelRenderer LeftPack;
	ModelRenderer StrapPack;
	ModelRenderer RightPack;
	ModelRenderer RightShears;
	ModelRenderer LeftShears;
	ModelRenderer HatTop;
	ModelRenderer HatBrimF;
	ModelRenderer HatBrimL;
	ModelRenderer HatBrimMain;
	ModelRenderer HatBrimR;
	ModelRenderer MachineThorax;
	ModelRenderer ConduitR;
	ModelRenderer ConduitL;
	ModelRenderer SprayL;
	ModelRenderer SpayR;
	ModelRenderer SprayLConduit;
	ModelRenderer SprayRConduit;

	public ModelBlackAnt() {
		textureWidth = 64;
		textureHeight = 128;
		Thx = new ModelRenderer(this, 14, 13);
		Thx.addBox(-3.5F, -3.5F, 0F, 7, 7, 9);
		Thx.setRotationPoint(0F, 17F, -8F);
		setRotation(Thx, 0F, 0F, 0F);
		ThxTop = new ModelRenderer(this, 21, 30);
		ThxTop.addBox(-2.5F, -4.5F, 1F, 5, 1, 7);
		ThxTop.setRotationPoint(0F, 17F, -8F);
		setRotation(ThxTop, 0F, 0F, 0F);
		ThxS = new ModelRenderer(this, 15, 39);
		ThxS.addBox(-4.5F, -2.5F, 1F, 9, 5, 7);
		ThxS.setRotationPoint(0F, 17F, -8F);
		setRotation(ThxS, 0F, 0F, 0F);
		Thx2Ab = new ModelRenderer(this, 39, 55);
		Thx2Ab.addBox(-1.5F, -1.5F, 0F, 3, 3, 1);
		Thx2Ab.setRotationPoint(0F, 17F, 1F);
		setRotation(Thx2Ab, 0F, 0F, 0F);
		Ab = new ModelRenderer(this, 9, 100);
		Ab.addBox(-5.5F, -4.5F, 0F, 11, 9, 12);
		Ab.setRotationPoint(0F, 17F, 3F);
		setRotation(Ab, 0F, 0F, 0F);
		AbF = new ModelRenderer(this, 0, 62);
		AbF.addBox(-3.5F, -3.5F, -1F, 7, 7, 1);
		AbF.setRotationPoint(0F, 17F, 3F);
		setRotation(AbF, 0F, 0F, 0F);
		AbSide = new ModelRenderer(this, 19, 63);
		AbSide.addBox(-6.5F, -2.5F, 2F, 13, 5, 8);
		AbSide.setRotationPoint(0F, 17F, 3F);
		setRotation(AbSide, 0F, 0F, 0F);
		AbTop = new ModelRenderer(this, 12, 80);
		AbTop.addBox(-4F, -5.5F, 2F, 8, 11, 8);
		AbTop.setRotationPoint(0F, 17F, 3F);
		setRotation(AbTop, 0F, 0F, 0F);
		AbBack = new ModelRenderer(this, 22, 122);
		AbBack.addBox(-3.5F, -2.5F, 12F, 7, 5, 1);
		AbBack.setRotationPoint(0F, 17F, 3F);
		setRotation(AbBack, 0F, 0F, 0F);
		Neck = new ModelRenderer(this, 0, 12);
		Neck.addBox(-1.5F, -1.5F, -2F, 3, 3, 3);
		Neck.setRotationPoint(0F, 17F, -9F);
		setRotation(Neck, 0F, 0F, 0F);
		Head1 = new ModelRenderer(this, 21, 0);
		Head1.addBox(-2F, 0.5F, -5F, 4, 5, 6);
		Head1.setRotationPoint(0F, 17F, -9F);
		setRotation(Head1, -0.6981317F, 0F, 0F);
		Head2 = new ModelRenderer(this, 0, 0);
		Head2.addBox(-3F, -0.5F, -4F, 6, 7, 4);
		setRotation(Head2, 0F, 0F, 0F);
		RMandible1 = new ModelRenderer(this, 52, 0);
		RMandible1.addBox(-3F, 5.5F, -3F, 1, 3, 1);
		setRotation(RMandible1, 0F, 0F, 0F);
		RMandible2 = new ModelRenderer(this, 52, 9);
		RMandible2.addBox(-2F, 5.5F, -3F, 1, 4, 1);
		setRotation(RMandible2, 0F, 0F, 0F);
		LMandible1 = new ModelRenderer(this, 47, 0);
		LMandible1.addBox(2F, 5.5F, -3F, 1, 3, 1);
		setRotation(LMandible1, 0F, 0F, 0F);
		LMandible2 = new ModelRenderer(this, 47, 9);
		LMandible2.addBox(1F, 5.5F, -3F, 1, 4, 1);
		setRotation(LMandible2, 0F, 0F, 0F);
		Eyes = new ModelRenderer(this, 0, 35);
		Eyes.addBox(-4F, 1.5F, -3F, 8, 2, 2);
		setRotation(Eyes, 0F, 0F, 0F);
		AntLS = new ModelRenderer(this, 42, 6);
		AntLS.addBox(3F, 4.5F, -3F, 3, 1, 1);
		setRotation(AntLS, 0F, 0F, 0.1745329F);
		AntLE = new ModelRenderer(this, 42, 0);
		AntLE.addBox(6F, 5.5F, -3F, 1, 4, 1);
		setRotation(AntLE, 0F, 0F, 0.1745329F);
		AntRS = new ModelRenderer(this, 53, 6);
		AntRS.addBox(-6F, 4.5F, -3F, 3, 1, 1);
		setRotation(AntRS, 0F, 0F, -0.1745329F);
		AntRE = new ModelRenderer(this, 57, 0);
		AntRE.addBox(-7F, 5.5F, -3F, 1, 4, 1);
		setRotation(AntRE, 0F, 0F, -0.1745329F);
		LBL1 = new ModelRenderer(this, 0, 95);
		LBL1.addBox(-4F, -1F, -1F, 4, 2, 2);
		LBL1.setRotationPoint(4F, 17F, 0F);
		setRotation(LBL1, 0F, 2.443461F, -0.3490659F);
		LBL2 = new ModelRenderer(this, 0, 88);
		LBL2.addBox(-5F, 0F, -1F, 2, 4, 2);
		setRotation(LBL2, 0F, 0F, 0.3490659F);
		LBL3 = new ModelRenderer(this, 0, 82);
		LBL3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(LBL3, 0F, 0F, 0.6981317F);
		LBL4 = new ModelRenderer(this, 0, 76);
		LBL4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
		setRotation(LBL4, 0F, 0F, 0.8726646F);
		LML1 = new ModelRenderer(this, 0, 95);
		LML1.addBox(-4F, -1F, -1F, 4, 2, 2);
		LML1.setRotationPoint(4F, 17F, -3F);
		setRotation(LML1, 0F, 3.141593F, -0.3490659F);
		LML2 = new ModelRenderer(this, 0, 88);
		LML2.addBox(-5F, 0F, -1F, 2, 4, 2);
		setRotation(LML2, 0F, 0F, 0.3490659F);
		LML3 = new ModelRenderer(this, 0, 82);
		LML3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(LML3, 0F, 0F, 0.6981317F);
		LML4 = new ModelRenderer(this, 0, 76);
		LML4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
		setRotation(LML4, 0F, 0F, 0.8726646F);
		LFL1 = new ModelRenderer(this, 0, 95);
		LFL1.addBox(-4F, -1F, -1F, 4, 2, 2);
		LFL1.setRotationPoint(4F, 17F, -6F);
		setRotation(LFL1, 0F, -2.443461F, -0.3490659F);
		LFL2 = new ModelRenderer(this, 0, 88);
		LFL2.addBox(-5F, 0F, -1F, 2, 4, 2);
		setRotation(LFL2, 0F, 0F, 0.3490659F);
		LFL3 = new ModelRenderer(this, 0, 82);
		LFL3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(LFL3, 0F, 0F, 0.6981317F);
		LFL4 = new ModelRenderer(this, 0, 76);
		LFL4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
		setRotation(LFL4, 0F, 0F, 0.8726646F);
		RFL1 = new ModelRenderer(this, 0, 95);
		RFL1.addBox(-4F, -1F, -1F, 4, 2, 2);
		RFL1.setRotationPoint(-4F, 17F, -6F);
		setRotation(RFL1, 0F, -0.6981317F, 0.3490659F);
		RFL2 = new ModelRenderer(this, 0, 88);
		RFL2.addBox(-5F, 0F, -1F, 2, 4, 2);
		setRotation(RFL2, 0F, 0F, 0.3490659F);
		RFL3 = new ModelRenderer(this, 0, 82);
		RFL3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(RFL3, 0F, 0F, 0.6981317F);
		RFL4 = new ModelRenderer(this, 0, 76);
		RFL4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
		setRotation(RFL4, 0F, 0F, 0.8726646F);
		RML1 = new ModelRenderer(this, 0, 95);
		RML1.addBox(-4F, -1F, -1F, 4, 2, 2);
		RML1.setRotationPoint(-4F, 17F, -3F);
		setRotation(RML1, 0F, 0F, 0.3490659F);
		RML2 = new ModelRenderer(this, 0, 88);
		RML2.addBox(-5F, 0F, -1F, 2, 4, 2);
		setRotation(RML2, 0F, 0F, 0.3490659F);
		RML3 = new ModelRenderer(this, 0, 82);
		RML3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(RML3, 0F, 0F, 0.6981317F);
		RML4 = new ModelRenderer(this, 0, 76);
		RML4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
		setRotation(RML4, 0F, 0F, 0.8726646F);
		RBL1 = new ModelRenderer(this, 0, 95);
		RBL1.addBox(-4F, -1F, -1F, 4, 2, 2);
		RBL1.setRotationPoint(-4F, 17F, 0F);
		setRotation(RBL1, 0F, 0.6981317F, 0.3490659F);
		RBL2 = new ModelRenderer(this, 0, 88);
		RBL2.addBox(-5F, 0F, -1F, 2, 4, 2);
		setRotation(RBL2, 0F, 0F, 0.3490659F);
		RBL3 = new ModelRenderer(this, 0, 82);
		RBL3.addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1);
		setRotation(RBL3, 0F, 0F, 0.6981317F);
		RBL4 = new ModelRenderer(this, 0, 76);
		RBL4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
		setRotation(RBL4, 0F, 0F, 0.8726646F);
		LeftPack = new ModelRenderer(this, 47, 15);
		LeftPack.addBox(4F, 0.5F, -2.5F, 3, 6, 5);
		LeftPack.setRotationPoint(0F, 11F, 8F);
		setRotation(LeftPack, 0F, 0F, -0.2443461F);
		StrapPack = new ModelRenderer(this, 42, 52);
		StrapPack.addBox(-5F, -0.5F, -0.5F, 10, 1, 1);
		StrapPack.setRotationPoint(0F, 11F, 8F);
		setRotation(StrapPack, 0F, 0F, 0F);
		RightPack = new ModelRenderer(this, 47, 27);
		RightPack.addBox(-7F, 0.5F, -2.5F, 3, 6, 5);
		RightPack.setRotationPoint(0F, 11F, 8F);
		setRotation(RightPack, 0F, 0F, 0.2617994F);
		RightShears = new ModelRenderer(this, 48, 55);
		RightShears.addBox(-3.5F, -3F, -10.5F, 3, 2, 5);
		RightShears.setRotationPoint(0F, 17F, -10F);
		setRotation(RightShears, 0.8726646F, 0F, 0F);
		LeftShears = new ModelRenderer(this, 48, 40);
		LeftShears.addBox(0.5F, -3F, -10.5F, 3, 2, 5);
		LeftShears.setRotationPoint(0F, 17F, -10F);
		setRotation(LeftShears, 0.8726646F, 0F, 0F);
		HatTop = new ModelRenderer(this, 44, 77);
		HatTop.addBox(-2.5F, -4F, -5F, 5, 2, 5);
		HatTop.setRotationPoint(0F, 17F, -9F);
		setRotation(HatTop, -0.4363323F, 0F, 0F);
		HatBrimF = new ModelRenderer(this, 0, 40);
		HatBrimF.addBox(-3F, -2F, -8F, 6, 1, 1);
		HatBrimF.setRotationPoint(0F, 17F, -9F);
		setRotation(HatBrimF, -0.4363323F, 0F, 0F);
		HatBrimL = new ModelRenderer(this, 0, 27);
		HatBrimL.addBox(4F, -2F, -6F, 1, 1, 6);
		HatBrimL.setRotationPoint(0F, 17F, -9F);
		setRotation(HatBrimL, -0.4363323F, 0F, 0F);
		HatBrimMain = new ModelRenderer(this, 0, 52);
		HatBrimMain.addBox(-4F, -2F, -7F, 8, 1, 8);
		HatBrimMain.setRotationPoint(0F, 17F, -9F);
		setRotation(HatBrimMain, -0.4363323F, 0F, 0F);
		HatBrimR = new ModelRenderer(this, 0, 43);
		HatBrimR.addBox(-5F, -2F, -6F, 1, 1, 6);
		HatBrimR.setRotationPoint(0F, 17F, -9F);
		setRotation(HatBrimR, -0.4363323F, 0F, 0F);
		MachineThorax = new ModelRenderer(this, 44, 85);
		MachineThorax.addBox(-2.5F, -1.5F, -14F, 5, 3, 5);
		MachineThorax.setRotationPoint(0F, 11F, 8F);
		setRotation(MachineThorax, 0F, 0F, 0F);
		ConduitR = new ModelRenderer(this, 18, 77);
		ConduitR.addBox(-1F, -0.5F, -5.5F, 9, 1, 1);
		ConduitR.setRotationPoint(0F, 11F, 8F);
		setRotation(ConduitR, 0F, 1.047198F, 0F);
		ConduitL = new ModelRenderer(this, 18, 77);
		ConduitL.addBox(-8F, -0.5F, -5.5F, 9, 1, 1);
		ConduitL.setRotationPoint(0F, 11F, 8F);
		setRotation(ConduitL, 0F, -1.064651F, 0F);
		SprayL = new ModelRenderer(this, 0, 19);
		SprayL.addBox(11F, 3.5F, -1F, 2, 2, 2);
		SprayL.setRotationPoint(0F, 11F, 8F);
		setRotation(SprayL, 0F, 0F, 0F);
		SpayR = new ModelRenderer(this, 0, 19);
		SpayR.addBox(-13F, 3.5F, -1F, 2, 2, 2);
		SpayR.setRotationPoint(0F, 11F, 8F);
		setRotation(SpayR, 0F, 0F, 0F);
		SprayLConduit = new ModelRenderer(this, 0, 24);
		SprayLConduit.addBox(8F, 3.5F, -0.5F, 3, 1, 1);
		SprayLConduit.setRotationPoint(0F, 11F, 8F);
		setRotation(SprayLConduit, 0F, 0F, 0F);
		SprayRConduit = new ModelRenderer(this, 0, 24);
		SprayRConduit.addBox(-11F, 3.5F, -0.5F, 3, 1, 1);
		SprayRConduit.setRotationPoint(0F, 11F, 8F);
		setRotation(SprayRConduit, 0F, 0F, 0F);

		RFL1.addChild(RFL2);
		RFL1.addChild(RFL3);
		RFL1.addChild(RFL4);

		RML1.addChild(RML2);
		RML1.addChild(RML3);
		RML1.addChild(RML4);

		RBL1.addChild(RBL2);
		RBL1.addChild(RBL3);
		RBL1.addChild(RBL4);

		LFL1.addChild(LFL2);
		LFL1.addChild(LFL3);
		LFL1.addChild(LFL4);

		LML1.addChild(LML2);
		LML1.addChild(LML3);
		LML1.addChild(LML4);

		LBL1.addChild(LBL2);
		LBL1.addChild(LBL3);
		LBL1.addChild(LBL4);

		Head1.addChild(Head2);
		Head1.addChild(RMandible1);
		Head1.addChild(RMandible2);
		Head1.addChild(LMandible1);
		Head1.addChild(LMandible2);
		Head1.addChild(Eyes);
		Head1.addChild(AntLS);
		Head1.addChild(AntLE);
		Head1.addChild(AntRS);
		Head1.addChild(AntRE);

	}

	@Override
	public void render(Entity entity, float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.render(entity, limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel);
		setRotationAngles(limbSwing, prevLimbSwing, entityTickTime, rotationYaw, rotationPitch, unitPixel, entity);
		EntityBlackAnt ant = (EntityBlackAnt) entity;

		Thx.render(unitPixel);
		ThxTop.render(unitPixel);
		ThxS.render(unitPixel);
		Thx2Ab.render(unitPixel);
		Ab.render(unitPixel);
		AbF.render(unitPixel);
		AbSide.render(unitPixel);
		AbTop.render(unitPixel);
		AbBack.render(unitPixel);
		Neck.render(unitPixel);
		Head1.render(unitPixel);
		;
		LBL1.render(unitPixel);
		LML1.render(unitPixel);
		LFL1.render(unitPixel);
		RFL1.render(unitPixel);
		RML1.render(unitPixel);
		RBL1.render(unitPixel);

		if (ant.getDataWatcher().getWatchableObjectByte(16) == Byte.valueOf((byte) 3)) {
			LeftPack.render(unitPixel);
			StrapPack.render(unitPixel);
			RightPack.render(unitPixel);

		}

		if (ant.getDataWatcher().getWatchableObjectByte(16) == Byte.valueOf((byte) 4)) {
			RightShears.render(unitPixel);
			LeftShears.render(unitPixel);
		}

		if (ant.getDataWatcher().getWatchableObjectByte(16) == Byte.valueOf((byte) 2)) {
			LeftPack.render(unitPixel);
			StrapPack.render(unitPixel);
			RightPack.render(unitPixel);
			GL11.glPushMatrix();
			GL11.glTranslated(0F, -0.0625F, 0.0625F);
			HatTop.render(unitPixel);
			HatBrimF.render(unitPixel);
			HatBrimL.render(unitPixel);
			HatBrimMain.render(unitPixel);
			HatBrimR.render(unitPixel);
			GL11.glPopMatrix();
		}

		if (ant.getDataWatcher().getWatchableObjectByte(16) == Byte.valueOf((byte) 5)) {
			LeftPack.render(unitPixel);
			StrapPack.render(unitPixel);
			RightPack.render(unitPixel);
			MachineThorax.render(unitPixel);
			ConduitR.render(unitPixel);
			ConduitL.render(unitPixel);
			SprayL.render(unitPixel);
			SpayR.render(unitPixel);
			SprayLConduit.render(unitPixel);
			SprayRConduit.render(unitPixel);
		}

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float prevLimbSwing, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
		Head1.rotateAngleY = rotationYaw / (180F / (float) Math.PI);
		Head1.rotateAngleX = rotationPitch / (180F / (float) Math.PI) - 1F;
		RightShears.rotateAngleY = LeftShears.rotateAngleY = Head1.rotateAngleY;
		RightShears.rotateAngleX = LeftShears.rotateAngleX = Head1.rotateAngleX + 1.64F;

		HatTop.rotateAngleY = HatBrimF.rotateAngleY = HatBrimL.rotateAngleY = HatBrimMain.rotateAngleY = HatBrimR.rotateAngleY = Head1.rotateAngleY;

		HatTop.rotateAngleX = HatBrimF.rotateAngleX = HatBrimL.rotateAngleX = HatBrimMain.rotateAngleX = HatBrimR.rotateAngleX = Head1.rotateAngleX + 0.5F;

		float stuff = MathHelper.cos(limbSwing * 1.5F) * 0.8F * prevLimbSwing;
		LBL1.rotateAngleX = stuff;
		LML1.rotateAngleX = -stuff;
		LFL1.rotateAngleX = stuff;
		RBL1.rotateAngleX = stuff;
		RML1.rotateAngleX = -stuff;
		RFL1.rotateAngleX = stuff;

	}
}