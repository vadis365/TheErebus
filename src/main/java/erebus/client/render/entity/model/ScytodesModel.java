package erebus.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.Entity;

public class ScytodesModel<T extends Entity> extends EntityModel<T> {
	private final ModelPart Lmand1;
	private final ModelPart Lmand2;
	private final ModelPart Rmand1;
	private final ModelPart Rmand2;
	private final ModelPart Head1;
	private final ModelPart HeadMain;
	private final ModelPart HeadFront;
	private final ModelPart HeadBack;
	private final ModelPart HeadBotB;
	private final ModelPart HeadBot;
	private final ModelPart HeadCore;
	private final ModelPart AbTop1;
	private final ModelPart AbTop2;
	private final ModelPart ABot1;
	private final ModelPart AbBack;
	private final ModelPart AbCore1;
	private final ModelPart AbCore2;
	private final ModelPart AbCore3;
	private final ModelPart LFL1;
	private final ModelPart LMFL1;
	private final ModelPart LMBL1;
	private final ModelPart LBL1;
	private final ModelPart RFL1;
	private final ModelPart RMFL1;
	private final ModelPart RMBL1;
	private final ModelPart RBL1;

	public ScytodesModel(ModelPart root) {
		this.Lmand1 = root.getChild("Lmand1");
		this.Lmand2 = root.getChild("Lmand2");
		this.Rmand1 = root.getChild("Rmand1");
		this.Rmand2 = root.getChild("Rmand2");
		this.Head1 = root.getChild("Head1");
		this.HeadMain = root.getChild("HeadMain");
		this.HeadFront = root.getChild("HeadFront");
		this.HeadBack = root.getChild("HeadBack");
		this.HeadBotB = root.getChild("HeadBotB");
		this.HeadBot = root.getChild("HeadBot");
		this.HeadCore = root.getChild("HeadCore");
		this.AbTop1 = root.getChild("AbTop1");
		this.AbTop2 = root.getChild("AbTop2");
		this.ABot1 = root.getChild("ABot1");
		this.AbBack = root.getChild("AbBack");
		this.AbCore1 = root.getChild("AbCore1");
		this.AbCore2 = root.getChild("AbCore2");
		this.AbCore3 = root.getChild("AbCore3");
		this.LFL1 = root.getChild("LFL1");
		this.LMFL1 = root.getChild("LMFL1");
		this.LMBL1 = root.getChild("LMBL1");
		this.LBL1 = root.getChild("LBL1");
		this.RFL1 = root.getChild("RFL1");
		this.RMFL1 = root.getChild("RMFL1");
		this.RMBL1 = root.getChild("RMBL1");
		this.RBL1 = root.getChild("RBL1");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Lmand1 = partdefinition.addOrReplaceChild("Lmand1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 17.0F, -13.0F, 0.6981F, 0.0F, 0.0F));

		PartDefinition Lmand2 = partdefinition.addOrReplaceChild("Lmand2", CubeListBuilder.create().texOffs(5, 0).addBox(0.0F, 0.5F, -1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 17.0F, -13.0F, -0.8727F, 0.0F, 0.0F));

		PartDefinition Rmand1 = partdefinition.addOrReplaceChild("Rmand1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 17.0F, -13.0F, 0.6981F, 0.0F, 0.0F));

		PartDefinition Rmand2 = partdefinition.addOrReplaceChild("Rmand2", CubeListBuilder.create().texOffs(5, 0).addBox(-1.0F, 0.5F, -1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 17.0F, -13.0F, -0.8727F, 0.0F, 0.0F));

		PartDefinition Head1 = partdefinition.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(10, 0).addBox(-2.5F, 0.5F, -4.5F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -7.0F));

		PartDefinition HeadMain = partdefinition.addOrReplaceChild("HeadMain", CubeListBuilder.create().texOffs(0, 21).addBox(-6.0F, -1.7F, -2.8F, 12.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, -7.0F));

		PartDefinition HeadFront = partdefinition.addOrReplaceChild("HeadFront", CubeListBuilder.create().texOffs(26, 0).addBox(-4.0F, -3.8667F, -1.9F, 8.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, 0.6981F, 0.0F, 0.0F));

		PartDefinition HeadBack = partdefinition.addOrReplaceChild("HeadBack", CubeListBuilder.create().texOffs(0, 7).addBox(-4.0F, -8.0F, -1.1F, 8.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition HeadBotB = partdefinition.addOrReplaceChild("HeadBotB", CubeListBuilder.create().texOffs(36, 16).addBox(-4.0F, 1.0F, 4.2F, 8.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition HeadBot = partdefinition.addOrReplaceChild("HeadBot", CubeListBuilder.create().texOffs(0, 37).addBox(-4.0F, 2.3F, -0.8F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, -8.0F));

		PartDefinition HeadCore = partdefinition.addOrReplaceChild("HeadCore", CubeListBuilder.create().texOffs(27, 113).addBox(-5.0F, -2.5F, 0.0F, 10.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition AbTop1 = partdefinition.addOrReplaceChild("AbTop1", CubeListBuilder.create().texOffs(0, 47).addBox(-4.0F, 2.9F, 6.1F, 8.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, 0.6981F, 0.0F, 0.0F));

		PartDefinition AbTop2 = partdefinition.addOrReplaceChild("AbTop2", CubeListBuilder.create().texOffs(32, 43).addBox(-4.0F, -13.2F, 8.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition ABot1 = partdefinition.addOrReplaceChild("ABot1", CubeListBuilder.create().texOffs(0, 63).addBox(-4.0F, -5.6F, 7.8F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition AbBack = partdefinition.addOrReplaceChild("AbBack", CubeListBuilder.create().texOffs(34, 56).addBox(-4.0F, 0.8F, 13.7F, 8.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, 0.2094F, 0.0F, 0.0F));

		PartDefinition AbCore1 = partdefinition.addOrReplaceChild("AbCore1", CubeListBuilder.create().texOffs(25, 77).addBox(-6.0F, -11.7F, 8.6F, 12.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition AbCore2 = partdefinition.addOrReplaceChild("AbCore2", CubeListBuilder.create().texOffs(26, 92).addBox(-7.0F, 2.3F, 13.6F, 14.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition AbCore3 = partdefinition.addOrReplaceChild("AbCore3", CubeListBuilder.create().texOffs(27, 102).addBox(-6.0F, 1.7F, 14.6F, 12.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 18.0F, -7.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition LFL1 = partdefinition.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 17.0F, -9.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition LFL2 = LFL1.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(0, 99).addBox(2.5F, -7.7F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition LFL3 = LFL1.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(0, 103).addBox(3.7F, -6.0F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition LFL4 = LFL1.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(0, 107).addBox(10.0F, 3.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1396F));

		PartDefinition LFL5 = LFL1.addOrReplaceChild("LFL5", CubeListBuilder.create().texOffs(0, 115).addBox(9.0F, 3.5F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0349F));

		PartDefinition LFL6 = LFL1.addOrReplaceChild("LFL6", CubeListBuilder.create().texOffs(0, 123).addBox(11.0F, 7.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition LMFL1 = partdefinition.addOrReplaceChild("LMFL1", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 17.0F, -6.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition LMFL2 = LMFL1.addOrReplaceChild("LMFL2", CubeListBuilder.create().texOffs(0, 99).addBox(2.5F, -7.7F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition LMFL3 = LMFL1.addOrReplaceChild("LMFL3", CubeListBuilder.create().texOffs(0, 103).addBox(3.7F, -6.0F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition LMFL4 = LMFL1.addOrReplaceChild("LMFL4", CubeListBuilder.create().texOffs(0, 107).addBox(10.0F, 3.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1396F));

		PartDefinition LMFL5 = LMFL1.addOrReplaceChild("LMFL5", CubeListBuilder.create().texOffs(0, 115).addBox(9.0F, 3.5F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0349F));

		PartDefinition LMFL6 = LMFL1.addOrReplaceChild("LMFL6", CubeListBuilder.create().texOffs(0, 123).addBox(11.0F, 7.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition LMBL1 = partdefinition.addOrReplaceChild("LMBL1", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 17.0F, -3.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition LMBL2 = LMBL1.addOrReplaceChild("LMBL2", CubeListBuilder.create().texOffs(0, 99).addBox(2.5F, -7.7F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition LMBL3 = LMBL1.addOrReplaceChild("LMBL3", CubeListBuilder.create().texOffs(0, 103).addBox(3.7F, -6.0F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition LMBL4 = LMBL1.addOrReplaceChild("LMBL4", CubeListBuilder.create().texOffs(0, 107).addBox(10.0F, 3.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1396F));

		PartDefinition LMBL5 = LMBL1.addOrReplaceChild("LMBL5", CubeListBuilder.create().texOffs(0, 115).addBox(9.0F, 3.5F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LMBL6 = LMBL1.addOrReplaceChild("LMBL6", CubeListBuilder.create().texOffs(0, 123).addBox(11.0F, 7.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition LBL1 = partdefinition.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 17.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition LBL2 = LBL1.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(0, 99).addBox(2.5F, -7.7F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition LBL3 = LBL1.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(0, 103).addBox(3.7F, -6.0F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition LBL4 = LBL1.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(0, 107).addBox(10.0F, 3.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1396F));

		PartDefinition LBL5 = LBL1.addOrReplaceChild("LBL5", CubeListBuilder.create().texOffs(0, 115).addBox(9.0F, 3.5F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0349F));

		PartDefinition LBL6 = LBL1.addOrReplaceChild("LBL6", CubeListBuilder.create().texOffs(0, 123).addBox(11.0F, 7.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition RFL1 = partdefinition.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 17.0F, -9.0F, 0.0F, -3.1416F, 0.6109F));

		PartDefinition RFL2 = RFL1.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(0, 99).addBox(2.5F, -7.7F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition RFL3 = RFL1.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(0, 99).addBox(3.7F, -6.0F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition RFL4 = RFL1.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(0, 107).addBox(10.0F, 3.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1396F));

		PartDefinition RFL5 = RFL1.addOrReplaceChild("RFL5", CubeListBuilder.create().texOffs(0, 115).addBox(9.0F, 3.5F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0349F));

		PartDefinition RFL6 = RFL1.addOrReplaceChild("RFL6", CubeListBuilder.create().texOffs(0, 123).addBox(11.0F, 7.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition RMFL1 = partdefinition.addOrReplaceChild("RMFL1", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 17.0F, -6.0F, 0.0F, 3.1416F, 0.6109F));

		PartDefinition RMFL2 = RMFL1.addOrReplaceChild("RMFL2", CubeListBuilder.create().texOffs(0, 99).addBox(2.5F, -7.7F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition RMFL3 = RMFL1.addOrReplaceChild("RMFL3", CubeListBuilder.create().texOffs(0, 103).addBox(3.7F, -6.0F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition RMFL4 = RMFL1.addOrReplaceChild("RMFL4", CubeListBuilder.create().texOffs(0, 107).addBox(10.0F, 3.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1396F));

		PartDefinition RMFL5 = RMFL1.addOrReplaceChild("RMFL5", CubeListBuilder.create().texOffs(0, 115).addBox(9.0F, 3.5F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0349F));

		PartDefinition RMFL6 = RMFL1.addOrReplaceChild("RMFL6", CubeListBuilder.create().texOffs(0, 123).addBox(11.0F, 7.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition RMBL1 = partdefinition.addOrReplaceChild("RMBL1", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 17.0F, -3.0F, 0.0F, 3.1416F, 0.6109F));

		PartDefinition RMBL2 = RMBL1.addOrReplaceChild("RMBL2", CubeListBuilder.create().texOffs(0, 99).addBox(2.5F, -7.7F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition RMBL3 = RMBL1.addOrReplaceChild("RMBL3", CubeListBuilder.create().texOffs(0, 103).addBox(3.7F, -6.0F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition RMBL4 = RMBL1.addOrReplaceChild("RMBL4", CubeListBuilder.create().texOffs(0, 107).addBox(10.0F, 3.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1396F));

		PartDefinition RMBL5 = RMBL1.addOrReplaceChild("RMBL5", CubeListBuilder.create().texOffs(0, 115).addBox(9.0F, 3.5F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0349F));

		PartDefinition RMBL6 = RMBL1.addOrReplaceChild("RMBL6", CubeListBuilder.create().texOffs(0, 123).addBox(11.0F, 7.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition RBL1 = partdefinition.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(0, 94).addBox(0.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 17.0F, 0.0F, 0.0F, 3.1416F, 0.6109F));

		PartDefinition RBL2 = RBL1.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 99).addBox(2.5F, -7.7F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition RBL3 = RBL1.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(0, 103).addBox(3.7F, -6.0F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition RBL4 = RBL1.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(0, 107).addBox(10.0F, 3.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1396F));

		PartDefinition RBL5 = RBL1.addOrReplaceChild("RBL5", CubeListBuilder.create().texOffs(0, 115).addBox(9.0F, 3.5F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0349F));

		PartDefinition RBL6 = RBL1.addOrReplaceChild("RBL6", CubeListBuilder.create().texOffs(0, 123).addBox(11.0F, 7.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		return LayerDefinition.create(meshdefinition, 64, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float cosZ = (float) (Math.cos(limbSwing * 1.0F) * 0.3F * limbSwingAmount);
		float cosY = (float) (Math.cos(limbSwing * 1.0F) * 0.4F * limbSwingAmount);
		float fixZ = 0.611F;
		float PI = 3.141593F;
		LBL1.zRot = cosZ - fixZ;
		LMBL1.zRot = -cosZ - fixZ;
		LMFL1.zRot = cosZ - fixZ;
		LFL1.zRot = cosZ - fixZ;

		RBL1.zRot = -cosZ + fixZ;
		RMBL1.zRot = cosZ + fixZ;
		RMFL1.zRot = -cosZ + fixZ;
		RFL1.zRot = cosZ + fixZ;

		LBL1.yRot = -cosY;
		LMBL1.yRot = cosY;
		LMFL1.yRot = -cosY;
		LFL1.yRot = cosY;

		RBL1.yRot = -cosY + PI;
		RMBL1.yRot = cosY + PI;
		RMFL1.yRot = -cosY + PI;
		RFL1.yRot = cosY + PI;


	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		Lmand1.render(stack, consumer, light, overlay, colour);
		Lmand2.render(stack, consumer, light, overlay, colour);
		Rmand1.render(stack, consumer, light, overlay, colour);
		Rmand2.render(stack, consumer, light, overlay, colour);
		Head1.render(stack, consumer, light, overlay, colour);
		HeadMain.render(stack, consumer, light, overlay, colour);
		HeadFront.render(stack, consumer, light, overlay, colour);
		HeadBack.render(stack, consumer, light, overlay, colour);
		HeadBotB.render(stack, consumer, light, overlay, colour);
		HeadBot.render(stack, consumer, light, overlay, colour);
		HeadCore.render(stack, consumer, light, overlay, colour);
		AbTop1.render(stack, consumer, light, overlay, colour);
		AbTop2.render(stack, consumer, light, overlay, colour);
		ABot1.render(stack, consumer, light, overlay, colour);
		AbBack.render(stack, consumer, light, overlay, colour);
		AbCore1.render(stack, consumer, light, overlay, colour);
		AbCore2.render(stack, consumer, light, overlay, colour);
		AbCore3.render(stack, consumer, light, overlay, colour);

		stack.pushPose();
		stack.translate(0.625F, 0F, 0F);
		stack.mulPose(Axis.YP.rotationDegrees(60F));
		LFL1.render(stack, consumer, light, overlay, colour);
		stack.popPose();

		stack.pushPose();
		stack.translate(0.1875, -0.0625F, 0.0625F);
		stack.mulPose(Axis.YP.rotationDegrees(20F));
		LMFL1.render(stack, consumer, light, overlay, colour);
		stack.popPose();

		stack.pushPose();
		stack.translate(0F, -0.0625F, -0.125F);
		stack.mulPose(Axis.YN.rotationDegrees(20F));
		LMBL1.render(stack, consumer, light, overlay, colour);
		stack.popPose();

		stack.pushPose();
		stack.translate(0.1875F, -0.0625F, -0.25F);
		stack.mulPose(Axis.YN.rotationDegrees(60F));
		LBL1.render(stack, consumer, light, overlay, colour);
		stack.popPose();

		stack.pushPose();
		stack.translate(-0.625F, 0F, 0F);
		stack.mulPose(Axis.YN.rotationDegrees(60F));
		RFL1.render(stack, consumer, light, overlay, colour);
		stack.popPose();

		stack.pushPose();
		stack.translate(-0.1875, -0.0625F, 0.0625F);
		stack.mulPose(Axis.YN.rotationDegrees(20F));
		RMFL1.render(stack, consumer, light, overlay, colour);
		stack.popPose();

		stack.pushPose();
		stack.translate(0F, -0.0625F, -0.125F);
		stack.mulPose(Axis.YP.rotationDegrees(20F));
		RMBL1.render(stack, consumer, light, overlay, colour);
		stack.popPose();

		stack.pushPose();
		stack.translate(-0.1875F, -0.0625F, -0.25F);
		stack.mulPose(Axis.YP.rotationDegrees(60F));
		RBL1.render(stack, consumer, light, overlay, colour);
		stack.popPose();

	}
}