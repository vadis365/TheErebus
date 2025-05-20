package erebus.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.entity.LavaWebSpider;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LavaWebSpiderModel<T extends LavaWebSpider> extends HierarchicalModel<T> {
	public ModelPart root;
	private final ModelPart ThxTop;
	private final ModelPart ThxS;
	private final ModelPart Lmand;
	private final ModelPart Rmand;
	private final ModelPart AbTop1;
	private final ModelPart AbTop2;
	private final ModelPart ABot1;
	private final ModelPart AbBack;
	private final ModelPart AbCore1;
	private final ModelPart AbCore2;
	private final ModelPart AbCore3;
	private final ModelPart FrontLegLeft;
	private final ModelPart BackLegLeft;
	private final ModelPart FrontMidLegLeft;
	private final ModelPart BackMidLegLeft;
	private final ModelPart FrontLegRight;
	private final ModelPart FrontMidLegRight;
	private final ModelPart BackMidLegRight;
	private final ModelPart BackLegRight;
	private final ModelPart LFL1;
	private final ModelPart LFL4;
	private final ModelPart LFL5;
	private final ModelPart RFL1;
	private final ModelPart RFL4;
	private final ModelPart RFL5;
	private final ModelPart LMFL1;
	private final ModelPart LMFL4;
	private final ModelPart LMFL5;
	private final ModelPart RMFL1;
	private final ModelPart RMFL4;
	private final ModelPart RMFL5;
	private final ModelPart LMBL1;
	private final ModelPart LMBL4;
	private final ModelPart LMBL5;
	private final ModelPart RMBL1;
	private final ModelPart RMBL4;
	private final ModelPart RMBL5;
	private final ModelPart LBL1;
	private final ModelPart LBL4;
	private final ModelPart LBL5;
	private final ModelPart RBL1;
	private final ModelPart RBL4;
	private final ModelPart RBL5;
	private final ModelPart HeadMain;
	private final ModelPart HeadBotB;

	public LavaWebSpiderModel(ModelPart root) {
		this.root = root;
		this.ThxTop = root.getChild("ThxTop");
		this.ThxS = root.getChild("ThxS");
		this.Lmand = root.getChild("Lmand");
		this.Rmand = root.getChild("Rmand");
		this.AbTop1 = root.getChild("AbTop1");
		this.AbTop2 = root.getChild("AbTop2");
		this.ABot1 = root.getChild("ABot1");
		this.AbBack = root.getChild("AbBack");
		this.AbCore1 = root.getChild("AbCore1");
		this.AbCore2 = root.getChild("AbCore2");
		this.AbCore3 = root.getChild("AbCore3");
		this.FrontLegLeft = root.getChild("FrontLegLeft");
		this.LFL1 = FrontLegLeft.getChild("LFL1");
		this.LFL4 = LFL1.getChild("LFL4");
		this.LFL5 = LFL1.getChild("LFL5");
		this.FrontMidLegLeft = root.getChild("FrontMidLegLeft");
		this.LMFL1 = FrontMidLegLeft.getChild("LMFL1");
		this.LMFL4 = LMFL1.getChild("LMFL4");
		this.LMFL5 = LMFL1.getChild("LMFL5");
		this.BackMidLegLeft = root.getChild("BackMidLegLeft");
		this.LMBL1 = BackMidLegLeft.getChild("LMBL1");
		this.LMBL4 = LMBL1.getChild("LMBL4");
		this.LMBL5 = LMBL1.getChild("LMBL5");
		this.BackLegLeft = root.getChild("BackLegLeft");
		this.LBL1 = BackLegLeft.getChild("LBL1");
		this.LBL4 = LBL1.getChild("LBL4");
		this.LBL5 = LBL1.getChild("LBL5");
		this.FrontLegRight = root.getChild("FrontLegRight");
		this.RFL1 = FrontLegRight.getChild("RFL1");
		this.RFL4 = RFL1.getChild("RFL4");
		this.RFL5 = RFL1.getChild("RFL5");
		this.FrontMidLegRight = root.getChild("FrontMidLegRight");
		this.RMFL1 = FrontMidLegRight.getChild("RMFL1");
		this.RMFL4 = RMFL1.getChild("RMFL4");
		this.RMFL5 = RMFL1.getChild("RMFL5");
		this.BackMidLegRight = root.getChild("BackMidLegRight");
		this.RMBL1 = BackMidLegRight.getChild("RMBL1");
		this.RMBL4 = RMBL1.getChild("RMBL4");
		this.RMBL5 = RMBL1.getChild("RMBL5");
		this.BackLegRight = root.getChild("BackLegRight");
		this.RBL1 = BackLegRight.getChild("RBL1");
		this.RBL4 = RBL1.getChild("RBL4");
		this.RBL5 = RBL1.getChild("RBL5");
		this.HeadMain = root.getChild("HeadMain");
		this.HeadBotB = root.getChild("HeadBotB");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ThxTop = partdefinition.addOrReplaceChild("ThxTop", CubeListBuilder.create().texOffs(62, 33).addBox(-2.5F, -2.5F, -12.0F, 5.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 2.0F));

		PartDefinition ThxS = partdefinition.addOrReplaceChild("ThxS", CubeListBuilder.create().texOffs(45, 1).addBox(-3.5F, -1.5F, -14.0F, 7.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 2.0F));

		PartDefinition Lmand = partdefinition.addOrReplaceChild("Lmand", CubeListBuilder.create().texOffs(40, 52).addBox(-1.0F, -0.6423F, -1.3716F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 17.0F, -12.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition Rmand = partdefinition.addOrReplaceChild("Rmand", CubeListBuilder.create().texOffs(32, 52).addBox(-1.0F, -0.6423F, -1.3716F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 17.0F, -12.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition AbTop1 = partdefinition.addOrReplaceChild("AbTop1", CubeListBuilder.create().texOffs(0, 18).addBox(-4.0F, 2.9F, 6.1F, 8.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -5.0F, 0.6981F, 0.0F, 0.0F));

		PartDefinition AbTop2 = partdefinition.addOrReplaceChild("AbTop2", CubeListBuilder.create().texOffs(30, 37).addBox(-4.0F, -13.2F, 8.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(0.0F, 17.0F, -5.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition ABot1 = partdefinition.addOrReplaceChild("ABot1", CubeListBuilder.create().texOffs(34, 22).addBox(-4.0F, -5.6F, 7.8F, 8.0F, 5.0F, 8.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(0.0F, 17.0F, -5.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition AbBack = partdefinition.addOrReplaceChild("AbBack", CubeListBuilder.create().texOffs(0, 33).addBox(-4.0F, 0.8F, 13.7F, 8.0F, 7.0F, 7.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 17.0F, -5.0F, 0.2094F, 0.0F, 0.0F));

		PartDefinition AbCore1 = partdefinition.addOrReplaceChild("AbCore1", CubeListBuilder.create().texOffs(76, 0).addBox(-6.0F, -11.7F, 8.6F, 12.0F, 7.0F, 7.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 17.0F, -5.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition AbCore2 = partdefinition.addOrReplaceChild("AbCore2", CubeListBuilder.create().texOffs(74, 51).addBox(-7.0F, 2.3F, 13.6F, 14.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -5.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition AbCore3 = partdefinition.addOrReplaceChild("AbCore3", CubeListBuilder.create().texOffs(67, 21).addBox(-6.0F, 1.7F, 14.6F, 12.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 18.0F, -5.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition FrontLegLeft = partdefinition.addOrReplaceChild("FrontLegLeft", CubeListBuilder.create(), PartPose.offsetAndRotation(5.0F, 17.0F, -10.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition LFL1 = FrontLegLeft.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(90, 37).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition LFL2 = LFL1.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(16, 55).addBox(-0.4583F, -1.3558F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition LFL3 = LFL1.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(16, 52).addBox(-0.315F, -0.2659F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition LFL4 = LFL1.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(60, 52).addBox(-0.5985F, -0.5596F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.1396F));

		PartDefinition LFL5 = LFL1.addOrReplaceChild("LFL5", CubeListBuilder.create().texOffs(56, 52).addBox(-1.1684F, -1.148F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0349F));

		PartDefinition LFL6 = LFL5.addOrReplaceChild("LFL6", CubeListBuilder.create().texOffs(68, 52).addBox(-0.1F, 0.1701F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition BackLegLeft = partdefinition.addOrReplaceChild("BackLegLeft", CubeListBuilder.create(), PartPose.offsetAndRotation(5.0F, 17.0F, -1.0F, 0.0F, -0.7418F, 0.0F));

		PartDefinition LBL1 = BackLegLeft.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(90, 37).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition LBL2 = LBL1.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(16, 55).addBox(-0.4583F, -1.3558F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition LBL3 = LBL1.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(16, 52).addBox(-0.315F, -0.2659F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition LBL4 = LBL1.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(60, 52).addBox(-0.5985F, -0.5596F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.1396F));

		PartDefinition LBL5 = LBL1.addOrReplaceChild("LBL5", CubeListBuilder.create().texOffs(56, 52).addBox(-1.1684F, -1.148F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0349F));

		PartDefinition LBL6 = LBL5.addOrReplaceChild("LBL6", CubeListBuilder.create().texOffs(68, 52).addBox(-0.1F, 0.1701F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition FrontMidLegLeft = partdefinition.addOrReplaceChild("FrontMidLegLeft", CubeListBuilder.create(), PartPose.offsetAndRotation(5.0F, 17.0F, -7.0F, 0.0F, 0.2182F, 0.0F));

		PartDefinition LMFL1 = FrontMidLegLeft.addOrReplaceChild("LMFL1", CubeListBuilder.create().texOffs(90, 37).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition LMFL2 = LMFL1.addOrReplaceChild("LMFL2", CubeListBuilder.create().texOffs(16, 55).addBox(-0.4583F, -1.3558F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition LMFL3 = LMFL1.addOrReplaceChild("LMFL3", CubeListBuilder.create().texOffs(16, 52).addBox(-0.315F, -0.2659F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition LMFL4 = LMFL1.addOrReplaceChild("LMFL4", CubeListBuilder.create().texOffs(60, 52).addBox(-0.5985F, -0.5596F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.1396F));

		PartDefinition LMFL5 = LMFL1.addOrReplaceChild("LMFL5", CubeListBuilder.create().texOffs(56, 52).addBox(-1.1684F, -1.148F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0349F));

		PartDefinition LMFL6 = LMFL5.addOrReplaceChild("LMFL6", CubeListBuilder.create().texOffs(68, 52).addBox(-0.1F, 0.1701F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition BackMidLegLeft = partdefinition.addOrReplaceChild("BackMidLegLeft", CubeListBuilder.create(), PartPose.offsetAndRotation(5.0F, 17.0F, -4.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition LMBL1 = BackMidLegLeft.addOrReplaceChild("LMBL1", CubeListBuilder.create().texOffs(90, 37).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition LMBL2 = LMBL1.addOrReplaceChild("LMBL2", CubeListBuilder.create().texOffs(16, 55).addBox(-0.4583F, -1.3558F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition LMBL3 = LMBL1.addOrReplaceChild("LMBL3", CubeListBuilder.create().texOffs(16, 52).addBox(-0.315F, -0.2659F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition LMBL4 = LMBL1.addOrReplaceChild("LMBL4", CubeListBuilder.create().texOffs(60, 52).addBox(-0.5985F, -0.5596F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.1396F));

		PartDefinition LMBL5 = LMBL1.addOrReplaceChild("LMBL5", CubeListBuilder.create().texOffs(56, 52).addBox(-1.25F, -1.5F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offset(10.0F, 5.0F, 0.0F));

		PartDefinition LMBL6 = LMBL5.addOrReplaceChild("LMBL6", CubeListBuilder.create().texOffs(68, 52).addBox(-0.3F, 0.1701F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition FrontLegRight = partdefinition.addOrReplaceChild("FrontLegRight", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, 17.0F, -10.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition RFL1 = FrontLegRight.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(90, 37).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -3.1416F, 0.6109F));

		PartDefinition RFL2 = RFL1.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(16, 55).addBox(-0.4583F, -1.3558F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition RFL3 = RFL1.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(16, 55).addBox(-0.315F, -0.2659F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition RFL4 = RFL1.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(60, 52).addBox(-0.5985F, -0.5596F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.1396F));

		PartDefinition RFL5 = RFL1.addOrReplaceChild("RFL5", CubeListBuilder.create().texOffs(56, 52).addBox(-1.1684F, -1.148F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0349F));

		PartDefinition RFL6 = RFL5.addOrReplaceChild("RFL6", CubeListBuilder.create().texOffs(68, 52).addBox(-0.1F, 0.1701F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition FrontMidLegRight = partdefinition.addOrReplaceChild("FrontMidLegRight", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, 17.0F, -7.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition RMFL1 = FrontMidLegRight.addOrReplaceChild("RMFL1", CubeListBuilder.create().texOffs(90, 37).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.6109F));

		PartDefinition RMFL2 = RMFL1.addOrReplaceChild("RMFL2", CubeListBuilder.create().texOffs(16, 55).addBox(-0.4583F, -1.3558F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition RMFL3 = RMFL1.addOrReplaceChild("RMFL3", CubeListBuilder.create().texOffs(16, 52).addBox(-0.315F, -0.2659F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition RMFL4 = RMFL1.addOrReplaceChild("RMFL4", CubeListBuilder.create().texOffs(60, 52).addBox(-0.5985F, -0.5596F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.1396F));

		PartDefinition RMFL5 = RMFL1.addOrReplaceChild("RMFL5", CubeListBuilder.create().texOffs(56, 52).addBox(-1.1684F, -1.148F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0349F));

		PartDefinition RMFL6 = RMFL5.addOrReplaceChild("RMFL6", CubeListBuilder.create().texOffs(68, 52).addBox(-0.1F, 0.1701F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition BackMidLegRight = partdefinition.addOrReplaceChild("BackMidLegRight", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, 17.0F, -4.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition RMBL1 = BackMidLegRight.addOrReplaceChild("RMBL1", CubeListBuilder.create().texOffs(90, 37).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.6109F));

		PartDefinition RMBL2 = RMBL1.addOrReplaceChild("RMBL2", CubeListBuilder.create().texOffs(16, 55).addBox(-0.4583F, -1.3558F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition RMBL3 = RMBL1.addOrReplaceChild("RMBL3", CubeListBuilder.create().texOffs(16, 52).addBox(-0.315F, -0.2659F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition RMBL4 = RMBL1.addOrReplaceChild("RMBL4", CubeListBuilder.create().texOffs(60, 52).addBox(-0.5985F, -0.5596F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.1396F));

		PartDefinition RMBL5 = RMBL1.addOrReplaceChild("RMBL5", CubeListBuilder.create().texOffs(56, 52).addBox(-1.1684F, -1.148F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0349F));

		PartDefinition RMBL6 = RMBL5.addOrReplaceChild("RMBL6", CubeListBuilder.create().texOffs(68, 52).addBox(-0.1F, 0.1701F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition BackLegRight = partdefinition.addOrReplaceChild("BackLegRight", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, 17.0F, -1.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition RBL1 = BackLegRight.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(90, 33).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.6109F));

		PartDefinition RBL2 = RBL1.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 55).addBox(-0.4583F, -1.3558F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition RBL3 = RBL1.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(0, 52).addBox(-0.315F, -0.2659F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition RBL4 = RBL1.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(52, 52).addBox(-0.5985F, -0.5596F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.1396F));

		PartDefinition RBL5 = RBL1.addOrReplaceChild("RBL5", CubeListBuilder.create().texOffs(48, 52).addBox(-1.1684F, -1.148F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(10.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0349F));

		PartDefinition RBL6 = RBL5.addOrReplaceChild("RBL6", CubeListBuilder.create().texOffs(64, 52).addBox(-0.35F, -0.7958F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 6.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition HeadMain = partdefinition.addOrReplaceChild("HeadMain", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -8.7F, -13.0F, 12.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 2.0F));

		PartDefinition HeadBotB = partdefinition.addOrReplaceChild("HeadBotB", CubeListBuilder.create().texOffs(97, 15).addBox(-4.0F, 1.0F, 4.2F, 8.0F, 4.0F, 6.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 17.0F, -5.0F, 0.3491F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float sin = (float) (Math.sin(limbSwing * 0.6F) * 0.4F * limbSwingAmount);
		float cos = (float) (Math.cos(limbSwing * 0.6F) * 0.4F * limbSwingAmount);

		BackLegLeft.zRot = 0F + cos;
		LBL4.zRot = 0.1396F - cos;
		LBL5.zRot = 0.0349F - cos;

		BackMidLegLeft.zRot = 0F + sin;
		LMBL4.zRot = 0.1396F - sin;
		LMBL5.zRot = 0.0349F - sin;

		FrontMidLegLeft.zRot = 0F + cos;
		LMFL4.zRot = 0.1396F - cos;
		LMFL5.zRot = 0.0349F - cos;

		FrontLegLeft.zRot = 0F + sin;
		LFL4.zRot = 0.1396F - sin;
		LFL5.zRot = 0.0349F - sin;

		BackLegRight.zRot = 0F + sin;
		RBL4.zRot = 0.1396F + sin;
		RBL5.zRot = 0.0349F + sin;

		BackMidLegRight.zRot = 0F + cos;
		RMBL4.zRot = 0.1396F + cos;
		RMBL5.zRot = 0.0349F + cos;

		FrontMidLegRight.zRot = 0F + sin;
		RMFL4.zRot = 0.1396F + sin;
		RMFL5.zRot = 0.0349F + sin;

		FrontLegRight.zRot = 0F + cos;
		RFL4.zRot = 0.1396F + cos;
		RFL5.zRot = 0.0349F + cos;

		BackLegLeft.yRot = -0.7418F - sin;
		BackMidLegLeft.yRot = -0.2618F + cos;
		FrontMidLegLeft.yRot = 0.2182F - sin;
		FrontLegLeft.yRot = 0.7854F + cos;

		BackLegRight.yRot = 0.7418F + cos;
		BackMidLegRight.yRot = 0.2618F - sin;
		FrontMidLegRight.yRot = -0.2182F + cos;
		FrontLegRight.yRot = -0.7854F - sin;
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		Lmand.render(stack, consumer, light, overlay, colour);
		Rmand.render(stack, consumer, light, overlay, colour);
		FrontLegLeft.render(stack, consumer, light, overlay, colour);
		BackLegLeft.render(stack, consumer, light, overlay, colour);
		FrontMidLegLeft.render(stack, consumer, light, overlay, colour);
		BackMidLegLeft.render(stack, consumer, light, overlay, colour);
		FrontLegRight.render(stack, consumer, light, overlay, colour);
		FrontMidLegRight.render(stack, consumer, light, overlay, colour);
		BackMidLegRight.render(stack, consumer, light, overlay, colour);
		BackLegRight.render(stack, consumer, light, overlay, colour);
		HeadMain.render(stack, consumer, light, overlay, colour); // may add a layer for eyes later
		ThxTop.render(stack, consumer, light, overlay, colour);
	}

	@Override
	public ModelPart root() {
		return root;
	}

	public void renderBody(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		ThxS.render(stack, consumer, light, overlay, colour);
		AbTop1.render(stack, consumer, light, overlay, colour);
		AbTop2.render(stack, consumer, light, overlay, colour);
		ABot1.render(stack, consumer, light, overlay, colour);
		AbBack.render(stack, consumer, light, overlay, colour);
		AbCore1.render(stack, consumer, light, overlay, colour);
		AbCore2.render(stack, consumer, light, overlay, colour);
		AbCore3.render(stack, consumer, light, overlay, colour);
		stack.pushPose();
		stack.translate(0F, 0.001F, 0F);
		HeadMain.render(stack, consumer, light, overlay, colour);
		ThxTop.render(stack, consumer, light, overlay, colour);
		stack.popPose();
		HeadBotB.render(stack, consumer, light, overlay, colour);
	}
}