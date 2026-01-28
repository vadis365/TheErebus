package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.BedBugRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
public class BedBugModel extends EntityModel<BedBugRenderState> {

	public ModelPart root;
    private final ModelPart LeftFrontLeg;
	private final ModelPart LeftMidLeg;
	private final ModelPart LeftBackLeg;
	private final ModelPart RightFrontLeg;
	private final ModelPart RightMidLeg;
	private final ModelPart RightBackLeg;

	public BedBugModel(ModelPart root) {
		super(root);
        root.getChild("HeadMain");
        root.getChild("Body");
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

		PartDefinition HeadMain = partdefinition.addOrReplaceChild("HeadMain", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 17.0F, -13.0F, 0.3927F, 0.0F, 0.0F));

        HeadMain.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(21, 0).addBox(-2.5F, -2.296F, -3.1679F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -1.0F, 0.1745F, 0.0F, 0.0F));

        HeadMain.addOrReplaceChild("Head2", CubeListBuilder.create().texOffs(41, 0).addBox(-2.0F, 0.0F, -5.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        HeadMain.addOrReplaceChild("LMandible", CubeListBuilder.create().texOffs(52, 0).addBox(0.5F, 1.0F, -8.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        HeadMain.addOrReplaceChild("RMandible", CubeListBuilder.create().texOffs(52, 0).addBox(-1.5F, 1.0F, -8.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        HeadMain.addOrReplaceChild("AntL", CubeListBuilder.create().texOffs(21, 9).addBox(-1.1158F, -0.5F, -0.3182F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 2.0F, -5.0F, 0.0F, 0.3491F, 0.0F));

        HeadMain.addOrReplaceChild("AntR", CubeListBuilder.create().texOffs(21, 9).addBox(-8.527F, -0.5F, -0.5521F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 2.0F, -5.0F, 0.0F, -0.3491F, 0.0F));

        HeadMain.addOrReplaceChild("Eyes", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0.0F, -4.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, -10.0F));

        Body.addOrReplaceChild("ThxL1", CubeListBuilder.create().texOffs(0, 5).addBox(-0.45F, -2.5F, -3.0F, 6.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.1309F, -0.0262F, 0.1745F));

        Body.addOrReplaceChild("ThxR1", CubeListBuilder.create().texOffs(46, 5).addBox(-5.55F, -2.5F, -3.0F, 6.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.1309F, 0.0262F, -0.1745F));

        Body.addOrReplaceChild("ThxL2", CubeListBuilder.create().texOffs(9, 14).addBox(-0.7F, -4.0F, 0.0F, 7.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.1745F, -0.0305F, 0.1745F));

        Body.addOrReplaceChild("ThxR2", CubeListBuilder.create().texOffs(32, 14).addBox(-6.3F, -4.0F, 0.0F, 7.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.1745F, 0.0305F, -0.1745F));

        Body.addOrReplaceChild("ThxL3", CubeListBuilder.create().texOffs(7, 24).addBox(-0.7F, -3.5F, 4.0F, 8.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.1745F, -0.0262F, 0.1745F));

        Body.addOrReplaceChild("ThxR3", CubeListBuilder.create().texOffs(32, 24).addBox(-7.3F, -3.5F, 4.0F, 8.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.1745F, 0.0262F, -0.1745F));

        Body.addOrReplaceChild("ThxR4", CubeListBuilder.create().texOffs(32, 35).addBox(-8.4F, -3.0F, 8.0F, 9.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0262F, -0.1745F));

        Body.addOrReplaceChild("ThxL5", CubeListBuilder.create().texOffs(1, 47).addBox(-0.5F, -2.5F, 11.0F, 10.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        Body.addOrReplaceChild("ThxR5", CubeListBuilder.create().texOffs(30, 47).addBox(-9.5F, -2.5F, 12.0F, 10.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -1.0F, 0.0F, 0.0F, -0.1745F));

        Body.addOrReplaceChild("ThxL6", CubeListBuilder.create().texOffs(0, 76).addBox(-0.9F, -5.0F, 15.0F, 11.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        Body.addOrReplaceChild("ThxR6", CubeListBuilder.create().texOffs(30, 61).addBox(-10.1F, -5.0F, 15.0F, 11.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        Body.addOrReplaceChild("ThxL7", CubeListBuilder.create().texOffs(1, 61).addBox(-1.0F, -5.5F, 21.0F, 10.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        Body.addOrReplaceChild("ThxR7", CubeListBuilder.create().texOffs(35, 78).addBox(-9.0F, -5.5F, 21.0F, 10.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        Body.addOrReplaceChild("ThxL8", CubeListBuilder.create().texOffs(10, 93).addBox(-1.6F, -9.0F, 23.0F, 8.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0262F, 0.1745F));

        Body.addOrReplaceChild("ThxR8", CubeListBuilder.create().texOffs(35, 93).addBox(-6.4F, -9.0F, 23.0F, 8.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, -0.0262F, -0.1745F));

        Body.addOrReplaceChild("ThxL9", CubeListBuilder.create().texOffs(10, 107).addBox(-1.5F, -8.5F, 27.0F, 7.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0262F, 0.1745F));

        Body.addOrReplaceChild("ThxR9", CubeListBuilder.create().texOffs(35, 107).addBox(-5.5F, -8.5F, 27.0F, 7.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, -0.0262F, -0.1745F));

        Body.addOrReplaceChild("ThxL10", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0262F, 0.1745F));

        Body.addOrReplaceChild("ThxR10", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, -0.0262F, -0.1745F));

        Body.addOrReplaceChild("Thx11", CubeListBuilder.create().texOffs(22, 121).addBox(-4.5F, -6.0F, 32.0F, 9.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

        Body.addOrReplaceChild("ThxL4", CubeListBuilder.create().texOffs(5, 35).addBox(-0.6F, -3.0F, 8.0F, 9.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, -0.0262F, 0.1745F));

        PartDefinition LeftFrontLeg = partdefinition.addOrReplaceChild("LeftFrontLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, 18.0F, -9.0F, 0.0F, 0.5236F, 0.0F));

        LeftFrontLeg.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(0, 107).addBox(0.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

        LeftFrontLeg.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(0, 99).addBox(3.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

        LeftFrontLeg.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(1, 93).mirror().addBox(1.5F, 4.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));

        LeftFrontLeg.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(0, 112).addBox(0.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

        PartDefinition LeftMidLeg = partdefinition.addOrReplaceChild("LeftMidLeg", CubeListBuilder.create(), PartPose.offset(7.0F, 18.0F, -6.0F));

        LeftMidLeg.addOrReplaceChild("LML1", CubeListBuilder.create().texOffs(48, 121).addBox(-1.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

        LeftMidLeg.addOrReplaceChild("LML2", CubeListBuilder.create().texOffs(0, 99).addBox(3.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

        LeftMidLeg.addOrReplaceChild("LML3", CubeListBuilder.create().texOffs(1, 93).mirror().addBox(1.5F, 4.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));

        LeftMidLeg.addOrReplaceChild("LML4", CubeListBuilder.create().texOffs(0, 112).addBox(0.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

        PartDefinition LeftBackLeg = partdefinition.addOrReplaceChild("LeftBackLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, 18.0F, -3.0F, 0.0F, -0.4363F, 0.0F));

        LeftBackLeg.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(0, 124).addBox(-1.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

        LeftBackLeg.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(0, 99).addBox(5.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

        LeftBackLeg.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(1, 93).mirror().addBox(3.5F, 5.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));

        LeftBackLeg.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(0, 112).addBox(2.5F, 10.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

        PartDefinition RightFrontLeg = partdefinition.addOrReplaceChild("RightFrontLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.0F, 18.0F, -9.0F, 0.0F, -0.5236F, 0.0F));

        RightFrontLeg.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(0, 107).mirror().addBox(-4.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

        RightFrontLeg.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(0, 99).mirror().addBox(-5.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

        RightFrontLeg.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(1, 93).addBox(-3.5F, 4.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

        RightFrontLeg.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(0, 112).mirror().addBox(-1.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8727F));

        PartDefinition RightMidLeg = partdefinition.addOrReplaceChild("RightMidLeg", CubeListBuilder.create(), PartPose.offset(-7.0F, 18.0F, -6.0F));

        RightMidLeg.addOrReplaceChild("RML1", CubeListBuilder.create().texOffs(48, 121).mirror().addBox(-4.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

        RightMidLeg.addOrReplaceChild("RML2", CubeListBuilder.create().texOffs(0, 99).mirror().addBox(-5.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

        RightMidLeg.addOrReplaceChild("RML3", CubeListBuilder.create().texOffs(1, 93).addBox(-3.5F, 4.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

        RightMidLeg.addOrReplaceChild("RML4", CubeListBuilder.create().texOffs(0, 112).addBox(-1.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8727F));

        PartDefinition RightBackLeg = partdefinition.addOrReplaceChild("RightBackLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.0F, 18.0F, -3.0F, 0.0F, 0.4363F, 0.0F));

        RightBackLeg.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(0, 124).mirror().addBox(-6.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

        RightBackLeg.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 99).mirror().addBox(-7.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

        RightBackLeg.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(1, 93).addBox(-5.5F, 5.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

        RightBackLeg.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(0, 112).addBox(-3.5F, 10.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8727F));

        return LayerDefinition.create(meshdefinition, 64, 128);
	}

	@Override
	public void setupAnim(BedBugRenderState state) {
		float sin = Mth.sin(state.walkAnimationPos) * 0.8F * state.walkAnimationSpeed;
		float cos = Mth.cos(state.walkAnimationPos) * 0.4F * state.walkAnimationSpeed;
		LeftBackLeg.zRot = -cos;
		LeftMidLeg.zRot = cos;
		LeftFrontLeg.zRot = -cos;
		RightBackLeg.zRot = -cos;
		RightMidLeg.zRot = cos;
		RightFrontLeg.zRot = -cos;
		LeftBackLeg.yRot = sin -0.4363F;
		LeftMidLeg.yRot = -sin;
		LeftFrontLeg.yRot = sin + 0.5236F;
		RightBackLeg.yRot = sin + 0.4363F;
		RightMidLeg.yRot = -sin;
		RightFrontLeg.yRot = sin -0.5236F;
	}
}
