package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.CropWeevilRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class CropWeevilModel extends EntityModel<CropWeevilRenderState> {

	public ModelPart root;
	private final ModelPart HeadMain;
	private final ModelPart Ab;
	private final ModelPart AbSideR;
	private final ModelPart AbSideL;
	private final ModelPart AbTop;
	private final ModelPart AbBack;
	private final ModelPart LeftFrontLeg;
	private final ModelPart LeftMidLeg;
	private final ModelPart LeftBackLeg;
	private final ModelPart RightFrontLeg;
	private final ModelPart RightMidLeg;
	private final ModelPart RightBackLeg;

	public CropWeevilModel(ModelPart root) {
		super(root);
		this.root = root;
		this.HeadMain = root.getChild("HeadMain");
		this.Ab = root.getChild("Ab");
		this.AbSideR = root.getChild("AbSideR");
		this.AbSideL = root.getChild("AbSideL");
		this.AbTop = root.getChild("AbTop");
		this.AbBack = root.getChild("AbBack");
		this.LeftFrontLeg = root.getChild("LeftFrontLeg");
		this.LeftMidLeg = root.getChild("LeftMidLeg");
		this.LeftBackLeg = root.getChild("LeftBackLeg");
		this.RightFrontLeg = root.getChild("RightFrontLeg");
		this.RightMidLeg = root.getChild("RightMidLeg");
		this.RightBackLeg = root.getChild("RightBackLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition HeadMain = partdefinition.addOrReplaceChild("HeadMain", CubeListBuilder.create().texOffs(16, 37).addBox(-4.0F, -1.0F, -3.0F, 8.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, -4.0F));

		PartDefinition Eyes = HeadMain.addOrReplaceChild("Eyes", CubeListBuilder.create().texOffs(24, 22).addBox(-3.0F, 1.0F, -5.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition HeadTop = HeadMain.addOrReplaceChild("HeadTop", CubeListBuilder.create().texOffs(17, 25).addBox(-3.0F, -2.4F, -3.0F, 6.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition HeadFront = HeadMain.addOrReplaceChild("HeadFront", CubeListBuilder.create().texOffs(23, 15).addBox(-2.5F, -1.4F, -6.0F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition Nose1 = HeadMain.addOrReplaceChild("Nose1", CubeListBuilder.create().texOffs(25, 5).addBox(-1.0F, -2.3F, -9.5F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition Nose2 = HeadMain.addOrReplaceChild("Nose2", CubeListBuilder.create().texOffs(27, 0).addBox(-0.5F, -2.0F, -12.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition Ant1 = HeadMain.addOrReplaceChild("Ant1", CubeListBuilder.create().texOffs(23, 12).addBox(-3.5F, -2.0F, -7.2F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition AntR2 = HeadMain.addOrReplaceChild("AntR2", CubeListBuilder.create().texOffs(40, 10).addBox(-4.0F, -2.0F, -10.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition AntL2 = HeadMain.addOrReplaceChild("AntL2", CubeListBuilder.create().texOffs(14, 10).addBox(3.0333F, -2.0F, -10.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition Ab = partdefinition.addOrReplaceChild("Ab", CubeListBuilder.create().texOffs(10, 49).addBox(-4.5F, -4.0F, 0.0F, 9.0F, 7.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition AbSideR = partdefinition.addOrReplaceChild("AbSideR", CubeListBuilder.create().texOffs(44, 49).addBox(-5.5F, -3.0F, 2.0F, 1.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition AbSideL = partdefinition.addOrReplaceChild("AbSideL", CubeListBuilder.create().texOffs(0, 48).addBox(4.5F, -3.0F, 2.0F, 1.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition AbTop = partdefinition.addOrReplaceChild("AbTop", CubeListBuilder.create().texOffs(16, 70).addBox(-3.5F, -5.0F, 2.0F, 7.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition AbBack = partdefinition.addOrReplaceChild("AbBack", CubeListBuilder.create().texOffs(23, 81).addBox(-3.5F, -2.0F, 13.0F, 7.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition LeftFrontLeg = partdefinition.addOrReplaceChild("LeftFrontLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0F, 20.0F, -2.0F, 0.0F, 0.5236F, 0.0F));

		PartDefinition LFL1 = LeftFrontLeg.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(0, 95).addBox(-6.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -0.3491F));

		PartDefinition LFL2 = LFL1.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(0, 88).addBox(-6.0F, 1.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LFL3 = LFL1.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(0, 82).addBox(-1.975F, 6.325F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7418F));

		PartDefinition LFL4 = LFL1.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(0, 76).addBox(-10.5F, 2.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition LeftMidLeg = partdefinition.addOrReplaceChild("LeftMidLeg", CubeListBuilder.create(), PartPose.offset(4.0F, 20.0F, 2.0F));

		PartDefinition LML1 = LeftMidLeg.addOrReplaceChild("LML1", CubeListBuilder.create().texOffs(0, 95).addBox(-6.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -0.3491F));

		PartDefinition LML2 = LML1.addOrReplaceChild("LML2", CubeListBuilder.create().texOffs(0, 88).addBox(-6.0F, 1.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LML3 = LML1.addOrReplaceChild("LML3", CubeListBuilder.create().texOffs(0, 82).addBox(-1.975F, 6.325F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7418F));

		PartDefinition LML4 = LML1.addOrReplaceChild("LML4", CubeListBuilder.create().texOffs(0, 76).addBox(-10.5F, 2.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition LeftBackLeg = partdefinition.addOrReplaceChild("LeftBackLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 20.0F, 7.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition LBL1 = LeftBackLeg.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(0, 95).addBox(-6.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, -0.3491F));

		PartDefinition LBL2 = LBL1.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(0, 88).addBox(-6.0F, 1.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LBL3 = LBL1.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(0, 82).addBox(-1.975F, 6.325F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7418F));

		PartDefinition LBL4 = LBL1.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(0, 76).addBox(-10.5F, 2.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition RightFrontLeg = partdefinition.addOrReplaceChild("RightFrontLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, 20.0F, -2.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition RFL1 = RightFrontLeg.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(0, 95).addBox(-6.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition RFL2 = RFL1.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(0, 88).addBox(-6.0F, 1.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RFL3 = RFL1.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(0, 82).addBox(-1.975F, 6.325F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7418F));

		PartDefinition RFL4 = RFL1.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(0, 76).addBox(-10.5F, 2.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition RightMidLeg = partdefinition.addOrReplaceChild("RightMidLeg", CubeListBuilder.create(), PartPose.offset(-4.0F, 20.0F, 2.0F));

		PartDefinition RML1 = RightMidLeg.addOrReplaceChild("RML1", CubeListBuilder.create().texOffs(0, 95).addBox(-6.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition RML2 = RML1.addOrReplaceChild("RML2", CubeListBuilder.create().texOffs(0, 88).addBox(-6.0F, 1.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RML3 = RML1.addOrReplaceChild("RML3", CubeListBuilder.create().texOffs(0, 82).addBox(-1.975F, 6.325F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7418F));

		PartDefinition RML4 = RML1.addOrReplaceChild("RML4", CubeListBuilder.create().texOffs(0, 76).addBox(-10.5F, 2.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition RightBackLeg = partdefinition.addOrReplaceChild("RightBackLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 20.0F, 7.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition RBL1 = RightBackLeg.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(0, 95).addBox(-6.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition RBL2 = RBL1.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 88).addBox(-6.0F, 1.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RBL3 = RBL1.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(0, 82).addBox(-1.975F, 6.325F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7418F));

		PartDefinition RBL4 = RBL1.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(0, 76).addBox(-10.5F, 2.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		return LayerDefinition.create(meshdefinition, 64, 128);
	}

	@Override
	public void setupAnim(CropWeevilRenderState state) {
		float correction = 0.3490659F;
		float sin = Mth.sin(state.walkAnimationPos) * 0.8F * state.walkAnimationSpeed;
		float cos = Mth.cos(state.walkAnimationPos) * 0.4F * state.walkAnimationSpeed;
		LeftBackLeg.zRot = -cos;
		LeftMidLeg.zRot = cos;
		LeftFrontLeg.zRot = -cos;
		RightBackLeg.zRot = -cos;
		RightMidLeg.zRot = cos;
		RightFrontLeg.zRot = -cos;
		LeftBackLeg.yRot = sin - correction;
		LeftMidLeg.yRot = -sin;
		LeftFrontLeg.yRot = sin + correction;
		RightBackLeg.yRot = sin + correction;
		RightMidLeg.yRot = -sin;
		RightFrontLeg.yRot = sin - correction;
	}
}
