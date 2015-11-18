package erebus.client.model.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelWebSlinger extends ModelBase {

	ModelRenderer ShapeA;
	ModelRenderer ShapeB;
	ModelRenderer ShapeC;
	ModelRenderer ShapeD;
	ModelRenderer ShapeE;
	ModelRenderer ShapeF;
	ModelRenderer ShapeG;
	ModelRenderer ShapeH;
	ModelRenderer ShapeI;
	ModelRenderer ShapeJ;
	ModelRenderer ShapeK;
	ModelRenderer ShapeL;
	ModelRenderer ShapeM;
	ModelRenderer ShapeN;
	ModelRenderer ShapeO;
	ModelRenderer ShapeP;
	ModelRenderer ShapeQ;
	ModelRenderer ShapeR;
	ModelRenderer ShapeS;
	ModelRenderer ShapeT;
	ModelRenderer ShapeU;
	ModelRenderer ShapeV;
	ModelRenderer ShapeW;
	ModelRenderer ShapeX;
	ModelRenderer Back;
	ModelRenderer BarrelDeco;
	ModelRenderer Barrel;
	ModelRenderer Grip;
	ModelRenderer Greeble1;
	ModelRenderer Greeble2;
	ModelRenderer Greeble3;

	public ModelWebSlinger() {
		textureWidth = 64;
		textureHeight = 32;

		ShapeA = new ModelRenderer(this, 0, 0);
		ShapeA.addBox(-2.5F, -4.466667F, 2.5F, 5, 1, 1);
		ShapeA.setRotationPoint(0F, 8F, -9F);
		setRotation(ShapeA, 0F, 0F, -3.141593F);
		ShapeC = new ModelRenderer(this, 0, 0);
		ShapeC.addBox(-2.5F, -4.466667F, 2.5F, 5, 1, 1);
		ShapeC.setRotationPoint(0F, 8F, -9F);
		setRotation(ShapeC, 0F, 0F, 2.094395F);
		ShapeD = new ModelRenderer(this, 0, 0);
		ShapeD.addBox(-2.5F, -4.466667F, 2.5F, 5, 1, 1);
		ShapeD.setRotationPoint(0F, 8F, -9F);
		setRotation(ShapeD, 0F, 0F, 1.047198F);
		ShapeE = new ModelRenderer(this, 0, 0);
		ShapeE.addBox(-3.5F, -6.466667F, -0.5F, 7, 1, 1);
		ShapeE.setRotationPoint(0F, 8F, -9F);
		setRotation(ShapeE, 0F, 0F, -1.047198F);
		ShapeF = new ModelRenderer(this, 0, 0);
		ShapeF.addBox(-2.5F, -4.466667F, 2.5F, 5, 1, 1);
		ShapeF.setRotationPoint(0F, 8F, -9F);
		setRotation(ShapeF, 0F, 0F, -1.047198F);
		ShapeG = new ModelRenderer(this, 0, 0);
		ShapeG.addBox(-2.5F, -4.466667F, 2.5F, 5, 1, 1);
		ShapeG.setRotationPoint(0F, 8F, -9F);
		setRotation(ShapeG, 0F, 0F, -2.094395F);
		ShapeH = new ModelRenderer(this, 0, 0);
		ShapeH.addBox(-1.5F, -2.466667F, 5.5F, 3, 1, 1);
		ShapeH.setRotationPoint(0F, 8F, -9F);
		setRotation(ShapeH, 0F, 0F, 3.141593F);
		ShapeI = new ModelRenderer(this, 0, 0);
		ShapeI.addBox(-3.5F, -6.466667F, -0.5F, 7, 1, 1);
		ShapeI.setRotationPoint(0F, 8F, -9F);
		setRotation(ShapeI, 0F, 0F, 0F);
		ShapeJ = new ModelRenderer(this, 0, 0);
		ShapeJ.addBox(-3.5F, -6.466667F, -0.5F, 7, 1, 1);
		ShapeJ.setRotationPoint(0F, 8F, -9F);
		setRotation(ShapeJ, 0F, 0F, 3.141593F);
		ShapeK = new ModelRenderer(this, 0, 0);
		ShapeK.addBox(-3.5F, -6.466667F, -0.5F, 7, 1, 1);
		ShapeK.setRotationPoint(0F, 8F, -9F);
		setRotation(ShapeK, 0F, 0F, 1.047198F);
		ShapeL = new ModelRenderer(this, 0, 0);
		ShapeL.addBox(-3.5F, -6.466667F, -0.5F, 7, 1, 1);
		ShapeL.setRotationPoint(0F, 8F, -9F);
		setRotation(ShapeL, 0F, 0F, 2.094395F);
		ShapeM = new ModelRenderer(this, 0, 0);
		ShapeM.addBox(-3.5F, -6.466667F, -0.5F, 7, 1, 1);
		ShapeM.setRotationPoint(0F, 8F, -9F);
		setRotation(ShapeM, 0F, 0F, -2.094395F);
		ShapeN = new ModelRenderer(this, 0, 0);
		ShapeN.addBox(-2.5F, -4.466667F, 2.5F, 5, 1, 1);
		ShapeN.setRotationPoint(0F, 8F, -9F);
		setRotation(ShapeN, 0F, 0F, 0F);
		ShapeO = new ModelRenderer(this, 0, 0);
		ShapeO.addBox(-1.5F, -2.466667F, 5.5F, 3, 1, 1);
		ShapeO.setRotationPoint(0F, 8F, -9F);
		setRotation(ShapeO, 0F, 0F, 0F);
		ShapeP = new ModelRenderer(this, 0, 0);
		ShapeP.addBox(-1.5F, -2.466667F, 5.5F, 3, 1, 1);
		ShapeP.setRotationPoint(0F, 8F, -9F);
		setRotation(ShapeP, 0F, 0F, 1.047198F);
		ShapeQ = new ModelRenderer(this, 0, 0);
		ShapeQ.addBox(-1.5F, -2.466667F, 5.5F, 3, 1, 1);
		ShapeQ.setRotationPoint(0F, 8F, -9F);
		setRotation(ShapeQ, 0F, 0F, 2.094395F);
		ShapeR = new ModelRenderer(this, 0, 0);
		ShapeR.addBox(-1.5F, -2.466667F, 5.5F, 3, 1, 1);
		ShapeR.setRotationPoint(0F, 8F, -9F);
		setRotation(ShapeR, 0F, 0F, -1.047198F);
		ShapeS = new ModelRenderer(this, 0, 0);
		ShapeS.addBox(-1.5F, -2.466667F, 5.5F, 3, 1, 1);
		ShapeS.setRotationPoint(0F, 8F, -9F);
		setRotation(ShapeS, 0F, 0F, -2.094395F);
		ShapeT = new ModelRenderer(this, 0, 0);
		ShapeT.addBox(0F, -0.5F, -1F, 13, 1, 2);
		ShapeT.setRotationPoint(0F, 8.1F, 0F);
		setRotation(ShapeT, 0F, 2.251475F, 0F);
		ShapeU = new ModelRenderer(this, 0, 0);
		ShapeU.addBox(0F, -0.4666667F, -1F, 13, 1, 2);
		ShapeU.setRotationPoint(0F, 8F, 0F);
		setRotation(ShapeU, 0F, 0.9075712F, 0F);
		Back = new ModelRenderer(this, 0, 16);
		Back.addBox(-2F, -2F, 7F, 4, 3, 6);
		Back.setRotationPoint(0F, 8F, 0F);
		setRotation(Back, 0F, 0F, 0F);
		BarrelDeco = new ModelRenderer(this, 36, 0);
		BarrelDeco.addBox(-2F, -0.5F, -1F, 4, 1, 7);
		BarrelDeco.setRotationPoint(0F, 8F, 0F);
		setRotation(BarrelDeco, 0F, 0F, 0F);
		Barrel = new ModelRenderer(this, 15, 4);
		Barrel.addBox(-1F, -1.5F, 0F, 2, 3, 8);
		Barrel.setRotationPoint(0F, 8F, 0F);
		setRotation(Barrel, 0F, 0F, 0F);
		Grip = new ModelRenderer(this, 0, 4);
		Grip.addBox(-1.5F, 1.5F, 7F, 3, 7, 4);
		Grip.setRotationPoint(0F, 8F, 0F);
		setRotation(Grip, 0.1396263F, 0F, 0F);
		Greeble1 = new ModelRenderer(this, 32, 0);
		Greeble1.addBox(-0.5F, -3F, -0.5F, 1, 3, 1);
		Greeble1.setRotationPoint(0F, 7.5F, 12F);
		setRotation(Greeble1, -0.5585054F, 0F, 0F);
		Greeble2 = new ModelRenderer(this, 32, 0);
		Greeble2.addBox(-0.5F, -3F, -0.5F, 1, 3, 1);
		Greeble2.setRotationPoint(1.5F, 7.5F, 12F);
		setRotation(Greeble2, -0.5585054F, 0.5585054F, 0F);
		Greeble3 = new ModelRenderer(this, 32, 0);
		Greeble3.addBox(-0.5F, -3F, -0.5F, 1, 3, 1);
		Greeble3.setRotationPoint(-1.5F, 7.5F, 12F);
		setRotation(Greeble3, -0.5585054F, -0.5585054F, 0F);
		ShapeV = new ModelRenderer(this, 36, 9);
		ShapeV.addBox(-0.5F, -13.5F, -1F, 1, 13, 2);
		ShapeV.setRotationPoint(0F, 8.1F, 0F);
		setRotation(ShapeV, 0.9075712F, 0F, 0.5235988F);
		ShapeX = new ModelRenderer(this, 36, 9);
		ShapeX.addBox(-0.5F, -13.5F, -1F, 1, 13, 2);
		ShapeX.setRotationPoint(0F, 8.1F, 0F);
		setRotation(ShapeX, 2.251475F, 0F, -0.5585054F);
		ShapeB = new ModelRenderer(this, 36, 9);
		ShapeB.addBox(-0.5F, -13.5F, -1F, 1, 13, 2);
		ShapeB.setRotationPoint(0F, 8.1F, 0F);
		setRotation(ShapeB, 0.9075712F, 0F, -0.5235988F);
		ShapeW = new ModelRenderer(this, 36, 9);
		ShapeW.addBox(-0.5F, -13.5F, -1F, 1, 13, 2);
		ShapeW.setRotationPoint(0F, 8.1F, 0F);
		setRotation(ShapeW, 2.251475F, 0F, 0.5235988F);

	}

	public void render() {
		ShapeA.render(0.0625F);
		ShapeB.render(0.0625F);
		ShapeC.render(0.0625F);
		ShapeD.render(0.0625F);
		ShapeE.render(0.0625F);
		ShapeF.render(0.0625F);
		ShapeG.render(0.0625F);
		ShapeH.render(0.0625F);
		ShapeI.render(0.0625F);
		ShapeJ.render(0.0625F);
		ShapeK.render(0.0625F);
		ShapeL.render(0.0625F);
		ShapeM.render(0.0625F);
		ShapeN.render(0.0625F);
		ShapeO.render(0.0625F);
		ShapeP.render(0.0625F);
		ShapeQ.render(0.0625F);
		ShapeR.render(0.0625F);
		ShapeS.render(0.0625F);
		ShapeT.render(0.0625F);
		ShapeU.render(0.0625F);
		ShapeV.render(0.0625F);
		ShapeW.render(0.0625F);
		ShapeX.render(0.0625F);
		Back.render(0.0625F);
		BarrelDeco.render(0.0625F);
		Barrel.render(0.0625F);
		Grip.render(0.0625F);
		Greeble1.render(0.0625F);
		Greeble2.render(0.0625F);
		Greeble3.render(0.0625F);

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
