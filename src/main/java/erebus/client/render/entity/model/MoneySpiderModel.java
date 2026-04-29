
package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.MoneySpiderRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class MoneySpiderModel extends EntityModel<MoneySpiderRenderState> {
	private final ModelPart frontLegLeft;
	private final ModelPart frontLegRight;
	private final ModelPart frontMidLegLeft;
	private final ModelPart frontMidLegRight;
	private final ModelPart backMidLegLeft;
	private final ModelPart backMidLegRight;
	private final ModelPart backLegLeft;
	private final ModelPart backLegRight;
	private final ModelPart lfl4;
	private final ModelPart lfl5;
	private final ModelPart rfl4;
	private final ModelPart rfl5;
	private final ModelPart lmfl4;
	private final ModelPart lmfl5;
	private final ModelPart rmfl4;
	private final ModelPart rmfl5;
	private final ModelPart lmbl4;
	private final ModelPart lmbl5;
	private final ModelPart rmbl4;
	private final ModelPart rmbl5;
	private final ModelPart lbl4;
	private final ModelPart lbl5;
	private final ModelPart rbl4;
	private final ModelPart rbl5;

	public MoneySpiderModel(ModelPart root) {
		super(root);
		this.frontLegLeft = root.getChild("FrontLegLeft");
		ModelPart lfl1 = frontLegLeft.getChild("LFL1");
		this.lfl4 = lfl1.getChild("LFL4");
		this.lfl5 = lfl1.getChild("LFL5");
		this.frontMidLegLeft = root.getChild("FrontMidLegLeft");
		ModelPart lmfl1 = frontMidLegLeft.getChild("LMFL1");
		this.lmfl4 = lmfl1.getChild("LMFL4");
		this.lmfl5 = lmfl1.getChild("LMFL5");
		this.backMidLegLeft = root.getChild("BackMidLegLeft");
		ModelPart lmbl1 = backMidLegLeft.getChild("LMBL1");
		this.lmbl4 = lmbl1.getChild("LMBL4");
		this.lmbl5 = lmbl1.getChild("LMBL5");
		this.backLegLeft = root.getChild("BackLegLeft");
		ModelPart lbl1 = backLegLeft.getChild("LBL1");
		this.lbl4 = lbl1.getChild("LBL4");
		this.lbl5 = lbl1.getChild("LBL5");
		this.frontLegRight = root.getChild("FrontLegRight");
		ModelPart rfl1 = frontLegRight.getChild("RFL1");
		this.rfl4 = rfl1.getChild("RFL4");
		this.rfl5 = rfl1.getChild("RFL5");
		this.frontMidLegRight = root.getChild("FrontMidLegRight");
		ModelPart rmfl1 = frontMidLegRight.getChild("RMFL1");
		this.rmfl4 = rmfl1.getChild("RMFL4");
		this.rmfl5 = rmfl1.getChild("RMFL5");
		this.backMidLegRight = root.getChild("BackMidLegRight");
		ModelPart rmbl1 = backMidLegRight.getChild("RMBL1");
		this.rmbl4 = rmbl1.getChild("RMBL4");
		this.rmbl5 = rmbl1.getChild("RMBL5");
		this.backLegRight = root.getChild("BackLegRight");
		ModelPart rbl1 = backLegRight.getChild("RBL1");
		this.rbl4 = rbl1.getChild("RBL4");
		this.rbl5 = rbl1.getChild("RBL5");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition part = mesh.getRoot();

		part.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition HeadMain = part.addOrReplaceChild("HeadMain", CubeListBuilder.create().texOffs(0, 21).addBox(-6.0F, -1.7F, -2.8F, 12.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, -7.0F));
		HeadMain.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(10, 0).addBox(-2.5F, 0.5F, -4.5F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));
		HeadMain.addOrReplaceChild("HeadFront", CubeListBuilder.create().texOffs(26, 0).addBox(-4.0F, -3.8667F, -1.9F, 8.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6981F, 0.0F, 0.0F));
		HeadMain.addOrReplaceChild("HeadBack", CubeListBuilder.create().texOffs(0, 7).addBox(-4.0F, -8.0F, -1.1F, 8.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));
		HeadMain.addOrReplaceChild("HeadBotB", CubeListBuilder.create().texOffs(36, 16).addBox(-4.0F, 1.0F, 4.2F, 8.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));
		HeadMain.addOrReplaceChild("HeadBot", CubeListBuilder.create().texOffs(0, 37).addBox(-4.0F, 2.3F, -0.8F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));
		HeadMain.addOrReplaceChild("HeadCore", CubeListBuilder.create().texOffs(27, 113).addBox(-5.0F, -2.5F, 0.0F, 10.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition Lmand1 = HeadMain.addOrReplaceChild("Lmand1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -3.6749F, 0.0195F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 2.0F, -5.0F, 0.6981F, 0.0F, 0.0F));
		Lmand1.addOrReplaceChild("Lmand2", CubeListBuilder.create().texOffs(5, 0).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.1749F, -0.4805F, -1.5708F, 0.0F, 0.0F));

		PartDefinition Rmand1 = HeadMain.addOrReplaceChild("Rmand1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -4.3177F, -0.7465F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 2.0F, -4.0F, 0.6981F, 0.0F, 0.0F));
		Rmand1.addOrReplaceChild("Rmand2", CubeListBuilder.create().texOffs(5, 0).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.8177F, -1.2465F, -1.5708F, 0.0F, 0.0F));

		part.addOrReplaceChild("AbTop1", CubeListBuilder.create().texOffs(0, 47).addBox(-4.0F, 2.9F, 6.1F, 8.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, 0.6981F, 0.0F, 0.0F));
		part.addOrReplaceChild("AbTop2", CubeListBuilder.create().texOffs(32, 43).addBox(-4.0F, -13.2F, 8.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.5236F, 0.0F, 0.0F));
		part.addOrReplaceChild("ABot1", CubeListBuilder.create().texOffs(0, 63).addBox(-4.0F, -5.6F, 7.8F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.3491F, 0.0F, 0.0F));
		part.addOrReplaceChild("AbBack", CubeListBuilder.create().texOffs(34, 56).addBox(-4.0F, 0.8F, 13.7F, 8.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, 0.2094F, 0.0F, 0.0F));
		part.addOrReplaceChild("AbCore1", CubeListBuilder.create().texOffs(25, 77).addBox(-6.0F, -11.7F, 8.6F, 12.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.5236F, 0.0F, 0.0F));
		part.addOrReplaceChild("AbCore2", CubeListBuilder.create().texOffs(26, 92).addBox(-7.0F, 2.3F, 13.6F, 14.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, 0.2618F, 0.0F, 0.0F));
		part.addOrReplaceChild("AbCore3", CubeListBuilder.create().texOffs(27, 102).addBox(-6.0F, 1.7F, 14.6F, 12.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 18.0F, -7.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition FrontLegLeft = part.addOrReplaceChild("FrontLegLeft", CubeListBuilder.create(), PartPose.offsetAndRotation(5.0F, 17.0F, -9.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition LFL1 = FrontLegLeft.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6109F));
		LFL1.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(0, 99).addBox(-0.4583F, -1.3558F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));
		LFL1.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(0, 103).addBox(-0.315F, -0.2659F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));
		LFL1.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(0, 107).addBox(-0.5985F, -0.5596F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.1396F));
		PartDefinition LFL5 = LFL1.addOrReplaceChild("LFL5", CubeListBuilder.create().texOffs(0, 115).addBox(-1.1684F, -1.148F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0349F));
		LFL5.addOrReplaceChild("LFL6", CubeListBuilder.create().texOffs(0, 123).addBox(-0.1F, 0.1701F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition FrontLegRight = part.addOrReplaceChild("FrontLegRight", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, 17.0F, -9.0F, 0.0F, -0.7854F, 0.0F));
		PartDefinition RFL1 = FrontLegRight.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -3.1416F, 0.6109F));
		RFL1.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(0, 99).addBox(-0.4583F, -1.3558F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));
		RFL1.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(0, 99).addBox(-0.315F, -0.2659F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));
		RFL1.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(0, 107).addBox(-0.5985F, -0.5596F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.1396F));
		PartDefinition RFL5 = RFL1.addOrReplaceChild("RFL5", CubeListBuilder.create().texOffs(0, 115).addBox(-1.1684F, -1.148F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0349F));
		RFL5.addOrReplaceChild("RFL6", CubeListBuilder.create().texOffs(0, 123).addBox(-0.1F, 0.1701F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition FrontMidLegLeft = part.addOrReplaceChild("FrontMidLegLeft", CubeListBuilder.create(), PartPose.offsetAndRotation(5.0F, 17.0F, -6.0F, 0.0F, 0.2182F, 0.0F));
		PartDefinition LMFL1 = FrontMidLegLeft.addOrReplaceChild("LMFL1", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6109F));
		LMFL1.addOrReplaceChild("LMFL2", CubeListBuilder.create().texOffs(0, 99).addBox(-0.4583F, -1.3558F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));
		LMFL1.addOrReplaceChild("LMFL3", CubeListBuilder.create().texOffs(0, 103).addBox(-0.315F, -0.2659F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));
		LMFL1.addOrReplaceChild("LMFL4", CubeListBuilder.create().texOffs(0, 107).addBox(-0.5985F, -0.5596F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.1396F));
		PartDefinition LMFL5 = LMFL1.addOrReplaceChild("LMFL5", CubeListBuilder.create().texOffs(0, 115).addBox(-1.1684F, -1.148F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0349F));
		LMFL5.addOrReplaceChild("LMFL6", CubeListBuilder.create().texOffs(0, 123).addBox(-0.1F, 0.1701F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition FrontMidLegRight = part.addOrReplaceChild("FrontMidLegRight", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, 17.0F, -6.0F, 0.0F, -0.2618F, 0.0F));
		PartDefinition RMFL1 = FrontMidLegRight.addOrReplaceChild("RMFL1", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.6109F));
		RMFL1.addOrReplaceChild("RMFL2", CubeListBuilder.create().texOffs(0, 99).addBox(-0.4583F, -1.3558F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));
		RMFL1.addOrReplaceChild("RMFL3", CubeListBuilder.create().texOffs(0, 103).addBox(-0.315F, -0.2659F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));
		RMFL1.addOrReplaceChild("RMFL4", CubeListBuilder.create().texOffs(0, 107).addBox(-0.5985F, -0.5596F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.1396F));
		PartDefinition RMFL5 = RMFL1.addOrReplaceChild("RMFL5", CubeListBuilder.create().texOffs(0, 115).addBox(-1.1684F, -1.148F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0349F));
		RMFL5.addOrReplaceChild("RMFL6", CubeListBuilder.create().texOffs(0, 123).addBox(-0.1F, 0.1701F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition BackMidLegLeft = part.addOrReplaceChild("BackMidLegLeft", CubeListBuilder.create(), PartPose.offsetAndRotation(5.0F, 17.0F, -3.0F, 0.0F, -0.2618F, 0.0F));
		PartDefinition LMBL1 = BackMidLegLeft.addOrReplaceChild("LMBL1", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6109F));
		LMBL1.addOrReplaceChild("LMBL2", CubeListBuilder.create().texOffs(0, 99).addBox(-0.4583F, -1.3558F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));
		LMBL1.addOrReplaceChild("LMBL3", CubeListBuilder.create().texOffs(0, 103).addBox(-0.315F, -0.2659F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));
		LMBL1.addOrReplaceChild("LMBL4", CubeListBuilder.create().texOffs(0, 107).addBox(-0.5985F, -0.5596F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.1396F));
		PartDefinition LMBL5 = LMBL1.addOrReplaceChild("LMBL5", CubeListBuilder.create().texOffs(0, 115).addBox(-1.25F, -1.5F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, 5.0F, 0.0F));
		LMBL5.addOrReplaceChild("LMBL6", CubeListBuilder.create().texOffs(0, 123).addBox(-0.3F, 0.1701F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition BackMidLegRight = part.addOrReplaceChild("BackMidLegRight", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, 17.0F, -3.0F, 0.0F, 0.2618F, 0.0F));
		PartDefinition RMBL1 = BackMidLegRight.addOrReplaceChild("RMBL1", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.6109F));
		RMBL1.addOrReplaceChild("RMBL2", CubeListBuilder.create().texOffs(0, 99).addBox(-0.4583F, -1.3558F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));
		RMBL1.addOrReplaceChild("RMBL3", CubeListBuilder.create().texOffs(0, 103).addBox(-0.315F, -0.2659F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));
		RMBL1.addOrReplaceChild("RMBL4", CubeListBuilder.create().texOffs(0, 107).addBox(-0.5985F, -0.5596F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.1396F));
		PartDefinition RMBL5 = RMBL1.addOrReplaceChild("RMBL5", CubeListBuilder.create().texOffs(0, 115).addBox(-1.1684F, -1.148F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0349F));
		RMBL5.addOrReplaceChild("RMBL6", CubeListBuilder.create().texOffs(0, 123).addBox(-0.1F, 0.1701F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition BackLegLeft = part.addOrReplaceChild("BackLegLeft", CubeListBuilder.create(), PartPose.offsetAndRotation(5.0F, 17.0F, 0.0F, 0.0F, -0.7418F, 0.0F));
		PartDefinition LBL1 = BackLegLeft.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6109F));
		LBL1.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(0, 99).addBox(-0.4583F, -1.3558F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));
		LBL1.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(0, 103).addBox(-0.315F, -0.2659F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));
		LBL1.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(0, 107).addBox(-0.5985F, -0.5596F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.1396F));
		PartDefinition LBL5 = LBL1.addOrReplaceChild("LBL5", CubeListBuilder.create().texOffs(0, 115).addBox(-1.1684F, -1.148F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0349F));
		LBL5.addOrReplaceChild("LBL6", CubeListBuilder.create().texOffs(0, 123).addBox(-0.1F, 0.1701F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition BackLegRight = part.addOrReplaceChild("BackLegRight", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, 17.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
		PartDefinition RBL1 = BackLegRight.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.6109F));
		RBL1.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 99).addBox(-0.4583F, -1.3558F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));
		RBL1.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(0, 103).addBox(-0.315F, -0.2659F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));
		RBL1.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(0, 107).addBox(-0.5985F, -0.5596F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.1396F));
		PartDefinition RBL5 = RBL1.addOrReplaceChild("RBL5", CubeListBuilder.create().texOffs(0, 115).addBox(-1.1684F, -1.148F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0349F));
		RBL5.addOrReplaceChild("RBL6", CubeListBuilder.create().texOffs(0, 123).addBox(-0.35F, -0.7958F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 6.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        return LayerDefinition.create(mesh, 64, 128);
	}

	@Override
	public void setupAnim(MoneySpiderRenderState state) {
		float sin = (float) (Math.sin(state.walkAnimationPos * 0.75F) * 0.5F * state.walkAnimationSpeed);
		float cos = (float) (Math.cos(state.walkAnimationPos * 0.75F) * 0.5F * state.walkAnimationSpeed);

		backLegLeft.zRot = 0F + cos;
		lbl4.zRot = 0.1396F + cos * 0.75F;
		lbl5.zRot = 0.0349F + cos * 0.75F;

		backMidLegLeft.zRot = 0F + sin;
		lmbl4.zRot = 0.1396F + sin * 0.75F;
		lmbl5.zRot = 0.0349F + sin * 0.75F;

		frontMidLegLeft.zRot = 0F + cos;
		lmfl4.zRot = 0.1396F + cos * 0.75F;
		lmfl5.zRot = 0.0349F + cos * 0.75F;

		frontLegLeft.zRot = 0F + sin;
		lfl4.zRot = 0.1396F + sin * 0.75F;
		lfl5.zRot = 0.0349F + sin * 0.75F;

		backLegRight.zRot = 0F + sin;
		rbl4.zRot = 0.1396F - sin * 0.75F;
		rbl5.zRot = 0.0349F - sin * 0.75F;

		backMidLegRight.zRot = 0F + cos;
		rmbl4.zRot = 0.1396F - cos * 0.75F;
		rmbl5.zRot = 0.0349F - cos * 0.75F;

		frontMidLegRight.zRot = 0F + sin;
		rmfl4.zRot = 0.1396F - sin * 0.75F;
		rmfl5.zRot = 0.0349F - sin * 0.75F;

		frontLegRight.zRot = 0F + cos;
		rfl4.zRot = 0.1396F - cos * 0.75F;
		rfl5.zRot = 0.0349F - cos * 0.75F;

		backLegLeft.yRot = -0.7418F - sin;
		backMidLegLeft.yRot = -0.2618F + cos;
		frontMidLegLeft.yRot = 0.2182F - sin;
		frontLegLeft.yRot = 0.7854F + cos;

		backLegRight.yRot = 0.7418F + cos;
		backMidLegRight.yRot = 0.2618F - sin;
		frontMidLegRight.yRot = -0.2182F + cos;
		frontLegRight.yRot = -0.7854F - sin;
	}
}
