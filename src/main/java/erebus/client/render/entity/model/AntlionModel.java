package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.AntlionRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class AntlionModel extends EntityModel<AntlionRenderState> {
	public ModelPart root;
	private final ModelPart Head;
	private final ModelPart Neck1;
    private final ModelPart LeftFrontLeg;
	private final ModelPart LeftMidLeg;
	private final ModelPart LeftBackLeg;
	private final ModelPart RightFrontLeg;
	private final ModelPart RightMidLeg;
	private final ModelPart RightBackLeg;
	private final ModelPart MandR1;
	private final ModelPart MandR2;
	private final ModelPart MandR3;
	private final ModelPart MandL1;
	private final ModelPart MandL2;
	private final ModelPart MandL3;

	public AntlionModel(ModelPart root) {
		super(root);
		this.root = root;
		this.Head = root.getChild("Head");
		this.MandR1 = Head.getChild("MandR1");
		this.MandR2 = Head.getChild("MandR2");
		this.MandR3 = Head.getChild("MandR3");
		this.MandL1 = Head.getChild("MandL1");
		this.MandL2 = Head.getChild("MandL2");
		this.MandL3 = Head.getChild("MandL3");
		this.Neck1 = root.getChild("Neck1");
        root.getChild("Neck2");
        root.getChild("Thorax");
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
		partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(88, 64).addBox(-4.5F, 3.0F, -13.5F, 9.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, -15.5F));

        Head.addOrReplaceChild("MandR1", CubeListBuilder.create().texOffs(110, 54).addBox(-0.5F, 4.0F, -17.0F, 3.0F, 4.0F, 5.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

        Head.addOrReplaceChild("MandR2", CubeListBuilder.create().texOffs(108, 43).addBox(-0.5F, 4.5F, -24.0F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

        Head.addOrReplaceChild("MandR3", CubeListBuilder.create().texOffs(114, 35).addBox(-15.5F, 5.0F, -23.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

        Head.addOrReplaceChild("MandL1", CubeListBuilder.create().texOffs(84, 54).addBox(-2.5F, 4.0F, -17.0F, 3.0F, 4.0F, 5.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

        Head.addOrReplaceChild("MandL2", CubeListBuilder.create().texOffs(83, 43).addBox(-1.5F, 4.5F, -24.0F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

        Head.addOrReplaceChild("MandL3", CubeListBuilder.create().texOffs(83, 35).addBox(14.5F, 5.0F, -23.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

        partdefinition.addOrReplaceChild("Neck1", CubeListBuilder.create().texOffs(93, 78).addBox(-3.5F, -2.0F, -8.5F, 7.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, -15.5F, 0.6981F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("Neck2", CubeListBuilder.create().texOffs(88, 88).addBox(-5.5F, -2.0F, -4.0F, 11.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, -15.5F, 0.5236F, 0.0F, 0.0F));

        PartDefinition Thorax = partdefinition.addOrReplaceChild("Thorax", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        Thorax.addOrReplaceChild("HR2", CubeListBuilder.create().texOffs(100, 100).addBox(-0.4F, -2.0F, 0.0F, 8.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -10.5F, -0.1745F, -3.1416F, -0.1745F));

        Thorax.addOrReplaceChild("HL2", CubeListBuilder.create().texOffs(73, 100).addBox(-7.6F, -2.0F, 0.0F, 8.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -10.5F, -0.1745F, 3.1416F, 0.1745F));

        Thorax.addOrReplaceChild("ThxR2", CubeListBuilder.create().texOffs(40, 0).addBox(-10.1F, -5.0F, 0.0F, 11.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -11.0F, 0.0F, 0.0F, -0.1745F));

        Thorax.addOrReplaceChild("ThxL2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.9F, -5.0F, 0.0F, 11.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -11.0F, 0.0F, 0.0F, 0.1745F));

        Thorax.addOrReplaceChild("ThxR3", CubeListBuilder.create().texOffs(40, 15).addBox(-11.0F, -5.5F, 0.0F, 12.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -6.0F, 0.0F, 0.0F, -0.1745F));

        Thorax.addOrReplaceChild("ThxL3", CubeListBuilder.create().texOffs(0, 15).addBox(-1.0F, -5.5F, -6.0F, 12.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        Thorax.addOrReplaceChild("ThxR4", CubeListBuilder.create().texOffs(40, 31).addBox(-11.9F, -6.0F, 0.0F, 13.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -2.0F, 0.0F, 0.0F, -0.1745F));

        Thorax.addOrReplaceChild("ThxL4", CubeListBuilder.create().texOffs(0, 31).addBox(-1.1F, -6.0F, 0.0F, 13.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -2.0F, 0.0F, 0.0F, 0.1745F));

        Thorax.addOrReplaceChild("ThxR5", CubeListBuilder.create().texOffs(44, 49).addBox(-11.8F, -6.5F, 0.0F, 13.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, 3.0F, 0.0F, 0.0F, -0.1745F));

        Thorax.addOrReplaceChild("ThxL5", CubeListBuilder.create().texOffs(0, 49).addBox(-1.2F, -6.5F, 0.0F, 13.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, 3.0F, 0.0F, 0.0F, 0.1745F));

        Thorax.addOrReplaceChild("ThxR6", CubeListBuilder.create().texOffs(44, 67).addBox(-11.9F, -6.0F, 0.0F, 13.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, 8.0F, 0.0F, 0.0F, -0.1745F));

        Thorax.addOrReplaceChild("ThxL6", CubeListBuilder.create().texOffs(0, 67).addBox(-1.1F, -6.0F, 0.0F, 13.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, 8.0F, 0.0F, 0.0F, 0.1745F));

        Thorax.addOrReplaceChild("ThxR7", CubeListBuilder.create().texOffs(44, 84).addBox(-11.0F, -5.5F, 0.0F, 12.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, 14.0F, 0.0F, 0.0F, -0.1745F));

        Thorax.addOrReplaceChild("ThxL7", CubeListBuilder.create().texOffs(0, 84).addBox(-1.0F, -5.5F, 0.0F, 12.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, 14.0F, 0.0F, 0.0F, 0.1745F));

        Thorax.addOrReplaceChild("ThxR9", CubeListBuilder.create().texOffs(44, 99).addBox(-9.4F, -3.5F, 0.0F, 10.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, 18.5F, -0.1745F, 0.0F, -0.1745F));

        Thorax.addOrReplaceChild("ThxL9", CubeListBuilder.create().texOffs(0, 99).addBox(-0.6F, -3.5F, 0.0F, 10.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, 18.5F, -0.1745F, 0.0F, 0.1745F));

        Thorax.addOrReplaceChild("ThxR10", CubeListBuilder.create().texOffs(44, 111).addBox(-7.6F, -2.0F, 0.0F, 8.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, 21.5F, -0.1745F, 0.0F, -0.1745F));

        Thorax.addOrReplaceChild("ThxL10", CubeListBuilder.create().texOffs(0, 111).addBox(-0.4F, -2.0F, 0.0F, 8.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, 21.5F, -0.1745F, 0.0F, 0.1745F));

        Thorax.addOrReplaceChild("Thx11", CubeListBuilder.create().texOffs(0, 121).addBox(-6.5F, 0.0F, 0.0F, 13.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 23.5F, -0.1745F, 0.0F, 0.0F));

        Thorax.addOrReplaceChild("Thx12", CubeListBuilder.create().texOffs(40, 122).addBox(-4.5F, 2.0F, 0.0F, 9.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 25.5F, -0.1745F, 0.0F, 0.0F));

        PartDefinition LeftFrontLeg = partdefinition.addOrReplaceChild("LeftFrontLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, 19.0F, -13.0F, 0.0F, 0.6109F, 0.0F));

		PartDefinition LF1 = LeftFrontLeg.addOrReplaceChild("LF1", CubeListBuilder.create().texOffs(82, 0).addBox(0.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.829F));

        LF1.addOrReplaceChild("LF2", CubeListBuilder.create().texOffs(82, 10).addBox(-0.1905F, -0.9252F, -0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.9599F));

        LF1.addOrReplaceChild("LF3", CubeListBuilder.create().texOffs(82, 14).addBox(0.1503F, -0.104F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(6.0F, -4.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

        PartDefinition LeftMidLeg = partdefinition.addOrReplaceChild("LeftMidLeg", CubeListBuilder.create(), PartPose.offset(9.0F, 18.0F, -8.5F));

		PartDefinition LM1 = LeftMidLeg.addOrReplaceChild("LM1", CubeListBuilder.create().texOffs(82, 5).addBox(0.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

        LM1.addOrReplaceChild("LM2", CubeListBuilder.create().texOffs(82, 10).addBox(-0.3567F, -0.5963F, -0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

        LM1.addOrReplaceChild("LM3", CubeListBuilder.create().texOffs(82, 14).addBox(-0.0579F, -0.1236F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(8.5F, -3.5F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition LeftBackLeg = partdefinition.addOrReplaceChild("LeftBackLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(9.0F, 19.0F, -2.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition LB1 = LeftBackLeg.addOrReplaceChild("LB1", CubeListBuilder.create().texOffs(82, 0).addBox(0.0F, -1.0F, -0.5F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, -0.5F, 0.0F, 0.0F, 0.7854F));

        LB1.addOrReplaceChild("LB2", CubeListBuilder.create().texOffs(82, 10).addBox(0.0927F, -0.6945F, -0.2456F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.8927F, 0.0F, 0.2456F, 0.0F, 0.0F, -0.925F));

        LB1.addOrReplaceChild("LB3", CubeListBuilder.create().texOffs(82, 14).addBox(-0.3277F, -0.1772F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(6.5F, -4.0F, 0.5F, 0.0F, 0.0F, -0.2269F));

        PartDefinition RightFrontLeg = partdefinition.addOrReplaceChild("RightFrontLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.0F, 19.0F, -13.0F, 0.0F, 2.5307F, 0.0F));

		PartDefinition RF1 = RightFrontLeg.addOrReplaceChild("RF1", CubeListBuilder.create().texOffs(82, 0).addBox(0.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.829F));

        RF1.addOrReplaceChild("RF2", CubeListBuilder.create().texOffs(82, 10).addBox(-0.1905F, -0.9252F, -0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.9599F));

        RF1.addOrReplaceChild("RF3", CubeListBuilder.create().texOffs(82, 14).addBox(0.1503F, -0.104F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(6.0F, -4.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

        PartDefinition RightMidLeg = partdefinition.addOrReplaceChild("RightMidLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-9.0F, 18.0F, -8.5F, 0.0F, 3.1416F, 0.0F));

		PartDefinition RM1 = RightMidLeg.addOrReplaceChild("RM1", CubeListBuilder.create().texOffs(82, 5).addBox(0.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

        RM1.addOrReplaceChild("RM2", CubeListBuilder.create().texOffs(82, 10).addBox(-0.3567F, -0.5963F, -0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

        RM1.addOrReplaceChild("RM3", CubeListBuilder.create().texOffs(82, 14).addBox(-0.0579F, -0.1236F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(8.5F, -3.5F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition RightBackLeg = partdefinition.addOrReplaceChild("RightBackLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-9.0F, 19.0F, -2.0F, 0.0F, -2.618F, 0.0F));

		PartDefinition RB1 = RightBackLeg.addOrReplaceChild("RB1", CubeListBuilder.create().texOffs(82, 0).addBox(0.0F, -1.0F, -0.5F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, -0.5F, 0.0F, 0.0F, 0.7854F));

        RB1.addOrReplaceChild("RB2", CubeListBuilder.create().texOffs(82, 10).addBox(0.0927F, -0.6945F, -0.2456F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.8927F, 0.0F, 0.2456F, 0.0F, 0.0F, -0.925F));

        RB1.addOrReplaceChild("RB3", CubeListBuilder.create().texOffs(82, 14).addBox(-0.3277F, -0.1772F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(6.5F, -4.0F, 0.5F, 0.0F, 0.0F, -0.2269F));

        return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(AntlionRenderState state) {
		float sin = (float) (Math.sin(state.walkAnimationPos * 0.75F) * 0.5F * state.walkAnimationSpeed);
		float sinJaw = (float) (Math.sin(state.walkAnimationPos * 0.75F) * 0.2F * state.walkAnimationSpeed);
		float headY = state.yRot / (180F / (float) Math.PI);
		float headX = state.xRot / (180F / (float) Math.PI);
		Head.yRot = headY;
		Neck1.yRot = headY;
		Head.xRot = headX;
		Neck1.xRot = 0.6981F + headX;

		LeftFrontLeg.yRot = 0.6109F - sin;
		LeftMidLeg.yRot = 0F + sin;
		LeftBackLeg.yRot = -0.5236F - sin;
		RightFrontLeg.yRot = 2.5307F -sin;
		RightMidLeg.yRot = 3.1416F + sin;
		RightBackLeg.yRot = -2.618F - sin;

		LeftFrontLeg.zRot = 0F + sin * 0.75F;
		LeftMidLeg.zRot = 0F - sin * 0.75F;
		LeftBackLeg.zRot = 0F + sin * 0.75F;
		RightFrontLeg.zRot = 0F + sin * 0.75F;
		RightMidLeg.zRot = 0F - sin * 0.75F;
		RightBackLeg.zRot = 0F + sin * 0.75F;

		MandR1.yRot = -sinJaw + 0.3491F;
		MandR2.yRot = -sinJaw + 0.3491F;
		MandR3.yRot = -sinJaw - 0.3491F;
		MandL1.yRot = sinJaw - 0.3491F;
		MandL2.yRot = sinJaw - 0.3491F;
		MandL3.yRot = sinJaw + 0.3491F;
	}
}
