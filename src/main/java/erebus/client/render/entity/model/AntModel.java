package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.AntRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class AntModel extends EntityModel<AntRenderState> {
	public ModelPart root;
    private final ModelPart Ab;
	private final ModelPart AbF;
	private final ModelPart AbSide;
	private final ModelPart AbTop;
	private final ModelPart AbBack;
    private final ModelPart Head1;
	private final ModelPart RightFrontLeg;
	private final ModelPart RightMidLeg;
	private final ModelPart RightBackLeg;
	private final ModelPart LeftFrontLeg;
	private final ModelPart LeftMidLeg;
	private final ModelPart LeftBackLeg;

	public AntModel(ModelPart root) {
        super(root);
        this.root = root;
        root.getChild("Thx");
        root.getChild("ThxTop");
        root.getChild("ThxS");
        root.getChild("Thx2Ab");
        this.Ab = root.getChild("Ab");
		this.AbF = root.getChild("AbF");
		this.AbSide = root.getChild("AbSide");
		this.AbTop = root.getChild("AbTop");
		this.AbBack = root.getChild("AbBack");
        root.getChild("Neck");
        this.Head1 = root.getChild("Head1");
		this.RightFrontLeg = root.getChild("RightFrontLeg");
		this.RightMidLeg = root.getChild("RightMidLeg");
		this.RightBackLeg = root.getChild("RightBackLeg");
		this.LeftFrontLeg = root.getChild("LeftFrontLeg");
		this.LeftMidLeg = root.getChild("LeftMidLeg");
		this.LeftBackLeg = root.getChild("LeftBackLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("Thx", CubeListBuilder.create().texOffs(14, 13).addBox(-3.5F, -3.5F, 0.0F, 7.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, -8.0F));

        partdefinition.addOrReplaceChild("ThxTop", CubeListBuilder.create().texOffs(21, 30).addBox(-2.5F, -4.5F, 1.0F, 5.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, -8.0F));

        partdefinition.addOrReplaceChild("ThxS", CubeListBuilder.create().texOffs(15, 39).addBox(-4.5F, -2.5F, 1.0F, 9.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, -8.0F));

        partdefinition.addOrReplaceChild("Thx2Ab", CubeListBuilder.create().texOffs(27, 52).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 1.0F));

        partdefinition.addOrReplaceChild("Ab", CubeListBuilder.create().texOffs(9, 100).addBox(-5.5F, -4.5F, 0.0F, 11.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 3.0F));

        partdefinition.addOrReplaceChild("AbF", CubeListBuilder.create().texOffs(23, 57).addBox(-3.5F, -3.5F, -1.0F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 3.0F));

        partdefinition.addOrReplaceChild("AbSide", CubeListBuilder.create().texOffs(10, 66).addBox(-6.5F, -2.5F, 2.0F, 13.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 3.0F));

        partdefinition.addOrReplaceChild("AbTop", CubeListBuilder.create().texOffs(15, 80).addBox(-4.0F, -5.5F, 2.0F, 8.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 3.0F));

        partdefinition.addOrReplaceChild("AbBack", CubeListBuilder.create().texOffs(22, 122).addBox(-3.5F, -2.5F, 12.0F, 7.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 3.0F));

        partdefinition.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 12).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, -9.0F));

        PartDefinition Head1 = partdefinition.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(21, 0).addBox(-2.0F, 0.5F, -5.0F, 4.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -9.0F, -0.6981F, 0.0F, 0.0F));

        Head1.addOrReplaceChild("Head2", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -0.5F, -4.0F, 6.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        Head1.addOrReplaceChild("RMandible1", CubeListBuilder.create().texOffs(52, 0).addBox(-3.0F, 5.5F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        Head1.addOrReplaceChild("RMandible2", CubeListBuilder.create().texOffs(52, 9).addBox(-2.0F, 5.5F, -3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        Head1.addOrReplaceChild("LMandible1", CubeListBuilder.create().texOffs(47, 0).addBox(2.0F, 5.5F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        Head1.addOrReplaceChild("LMandible2", CubeListBuilder.create().texOffs(47, 9).addBox(1.0F, 5.5F, -3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        Head1.addOrReplaceChild("Eyes", CubeListBuilder.create().texOffs(0, 30).addBox(-4.0F, 1.5F, -3.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        Head1.addOrReplaceChild("AntLS", CubeListBuilder.create().texOffs(42, 6).addBox(3.0F, 4.5F, -3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        Head1.addOrReplaceChild("AntLE", CubeListBuilder.create().texOffs(42, 0).addBox(6.0F, 5.5F, -3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        Head1.addOrReplaceChild("AntRS", CubeListBuilder.create().texOffs(53, 6).addBox(-6.0F, 4.5F, -3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        Head1.addOrReplaceChild("AntRE", CubeListBuilder.create().texOffs(57, 0).addBox(-7.0F, 5.5F, -3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition RightFrontLeg = partdefinition.addOrReplaceChild("RightFrontLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 17.0F, -6.0F, 0.0F, -0.6981F, 0.0F));

		PartDefinition RFL1 = RightFrontLeg.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(0, 95).addBox(-3.6543F, -0.7758F, -0.9378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

        RFL1.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(0, 88).addBox(-1.7111F, -1.1971F, 5.0622F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.0F));

        RFL1.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(0, 82).addBox(-0.8188F, 2.2502F, 5.5622F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.3054F));

        RFL1.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(0, 76).addBox(0.7498F, 5.3188F, 5.5622F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.48F));

        PartDefinition RightMidLeg = partdefinition.addOrReplaceChild("RightMidLeg", CubeListBuilder.create(), PartPose.offset(-4.0F, 17.0F, -3.0F));

		PartDefinition RML1 = RightMidLeg.addOrReplaceChild("RML1", CubeListBuilder.create().texOffs(0, 95).addBox(-3.6543F, -0.7758F, -0.9378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

        RML1.addOrReplaceChild("RML2", CubeListBuilder.create().texOffs(0, 88).addBox(-1.7111F, -1.1971F, 5.0622F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.0F));

        RML1.addOrReplaceChild("RML3", CubeListBuilder.create().texOffs(0, 82).addBox(-0.8188F, 2.2502F, 5.5622F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.3054F));

        RML1.addOrReplaceChild("RML4", CubeListBuilder.create().texOffs(0, 76).addBox(0.7498F, 5.3188F, 5.5622F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.48F));

        PartDefinition RightBackLeg = partdefinition.addOrReplaceChild("RightBackLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 17.0F, 0.0F, 0.0F, 0.6981F, 0.0F));

		PartDefinition RBL1 = RightBackLeg.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(0, 95).addBox(-3.6543F, -0.7758F, -0.9378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

        RBL1.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 88).addBox(-1.7111F, -1.1971F, 5.0622F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.0F));

        RBL1.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(0, 82).addBox(-0.8188F, 2.2502F, 5.5622F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.3054F));

        RBL1.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(0, 76).addBox(0.7498F, 5.3188F, 5.5622F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.48F));

        PartDefinition LeftFrontLeg = partdefinition.addOrReplaceChild("LeftFrontLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 17.0F, -6.0F, 0.0F, -2.4435F, 0.0F));

		PartDefinition LFL1 = LeftFrontLeg.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(0, 95).addBox(-3.6543F, -0.7758F, -0.9378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

        LFL1.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(0, 88).addBox(-1.7111F, -1.1971F, 5.0622F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.0F));

        LFL1.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(0, 82).addBox(-0.8188F, 2.2502F, 5.5622F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.3054F));

        LFL1.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(0, 76).addBox(0.7498F, 5.3188F, 5.5622F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.48F));

        PartDefinition LeftMidLeg = partdefinition.addOrReplaceChild("LeftMidLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 17.0F, -3.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition LML1 = LeftMidLeg.addOrReplaceChild("LML1", CubeListBuilder.create().texOffs(0, 95).addBox(-3.6543F, -0.7758F, -0.9378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

        LML1.addOrReplaceChild("LML2", CubeListBuilder.create().texOffs(0, 88).addBox(-1.7111F, -1.1971F, 5.0622F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.0F));

        LML1.addOrReplaceChild("LML3", CubeListBuilder.create().texOffs(0, 82).addBox(-0.8188F, 2.2502F, 5.5622F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.3054F));

        LML1.addOrReplaceChild("LML4", CubeListBuilder.create().texOffs(0, 76).addBox(0.7498F, 5.3188F, 5.5622F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.48F));

        PartDefinition LeftBackLeg = partdefinition.addOrReplaceChild("LeftBackLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 17.0F, 0.0F, 0.0F, 2.4435F, 0.0F));

		PartDefinition LBL1 = LeftBackLeg.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(0, 95).addBox(-3.6543F, -0.7758F, -0.9378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

        LBL1.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(0, 88).addBox(-1.7111F, -1.1971F, 5.0622F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.0F));

        LBL1.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(0, 82).addBox(-0.8188F, 2.2502F, 5.5622F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.3054F));

        LBL1.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(0, 76).addBox(0.7498F, 5.3188F, 5.5622F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.48F));

        return LayerDefinition.create(meshdefinition, 64, 128);
	}

	@Override
	public void setupAnim(AntRenderState state) {
		Head1.yRot = state.yRot / (180F / (float) Math.PI);
		Head1.xRot = state.xRot / (180F / (float) Math.PI) - 1F;

		float sin = Mth.sin(state.walkAnimationPos) * 0.8F * state.walkAnimationSpeed;
		float cos = Mth.cos(state.walkAnimationPos) * 0.2F * state.walkAnimationSpeed;

		LeftBackLeg.zRot = -cos;
		LeftMidLeg.zRot = cos;
		LeftFrontLeg.zRot = -cos;
		RightBackLeg.zRot = -cos;
		RightMidLeg.zRot = cos;
		RightFrontLeg.zRot = -cos;
		LeftBackLeg.yRot = 2.4435F + sin;
		LeftMidLeg.yRot = 3.1416F - sin;
		LeftFrontLeg.yRot = -2.4435F + sin;
		RightBackLeg.yRot = 0.6981F + sin;
		RightMidLeg.yRot = 0F - sin;
		RightFrontLeg.yRot = -0.6981F + sin;

		if (state.isHoneyPotAnt) {
			Ab.xRot = state.honeyPotBelly * 0.25F;
			AbF.xRot = state.honeyPotBelly * 0.25F;
			AbSide.xRot = state.honeyPotBelly * 0.25F;
			AbTop.xRot = state.honeyPotBelly * 0.25F;
			AbBack.xRot = state.honeyPotBelly * 0.25F;
		}
	}
}
