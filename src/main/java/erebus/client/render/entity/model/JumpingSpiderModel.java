package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.JumpingSpiderRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class JumpingSpiderModel extends EntityModel<JumpingSpiderRenderState> {
    private static final float HIP_ROLL = 35.0F * Mth.DEG_TO_RAD;
    private final ModelPart frontLeftHip;
    private final ModelPart middleFrontLeftHip;
    private final ModelPart middleBackLeftHip;
    private final ModelPart backLeftHip;
    private final ModelPart frontRightHip;
    private final ModelPart middleFrontRightHip;
    private final ModelPart middleBackRightHip;
    private final ModelPart backRightHip;

    public JumpingSpiderModel(ModelPart root) {
        super(root);
        frontLeftHip = root.getChild("front_left").getChild("hip");
        middleFrontLeftHip = root.getChild("middle_front_left").getChild("hip");
        middleBackLeftHip = root.getChild("middle_back_left").getChild("hip");
        backLeftHip = root.getChild("back_left").getChild("hip");
        frontRightHip = root.getChild("front_right").getChild("hip");
        middleFrontRightHip = root.getChild("middle_front_right").getChild("hip");
        middleBackRightHip = root.getChild("middle_back_right").getChild("hip");
        backRightHip = root.getChild("back_right").getChild("hip");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // Keep each leg's fixed placement separate from its animated hip pivot.
        addLegPair(root, "front", 10.0F, 0.0F, 0.0F, -9.0F, 60.0F, 99);
        addLegPair(root, "middle_front", 3.0F, -1.0F, 1.0F, -6.0F, 20.0F, 103);
        addLegPair(root, "middle_back", 0.0F, -1.0F, -2.0F, -3.0F, -20.0F, 103);
        addLegPair(root, "back", 3.0F, -1.0F, -4.0F, 0.0F, -60.0F, 103);

        root.addOrReplaceChild("Ab", CubeListBuilder.create().texOffs(10, 51).addBox(-5.0F, -5.5F, -1.0F, 10, 9, 13), PartPose.offsetAndRotation(0.0F, 17.0F, 3.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("AbSide", CubeListBuilder.create().texOffs(12, 74).addBox(-6.5F, -4.0F, 2.0F, 13, 7, 8), PartPose.offsetAndRotation(0.0F, 17.0F, 3.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("AbTop", CubeListBuilder.create().texOffs(23, 90).addBox(-4.0F, -6.5F, 2.0F, 8, 11, 8), PartPose.offsetAndRotation(0.0F, 17.0F, 3.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("AbBack", CubeListBuilder.create().texOffs(30, 110).addBox(-4.5F, -3.0F, 12.0F, 9, 6, 2), PartPose.offsetAndRotation(0.0F, 17.0F, 3.0F, -0.1745329F, 0.0F, 0.0F));

        root.addOrReplaceChild("HeadToAb", CubeListBuilder.create().texOffs(35, 41).addBox(-3.5F, -5.75F, -3.0F, 7, 6, 3), PartPose.offset(0.0F, 18.0F, 3.0F));
        root.addOrReplaceChild("HeadBack", CubeListBuilder.create().texOffs(12, 41).addBox(-4.0F, -0.5F, 5.5F, 8, 6, 3), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, 0.7853982F, 0.0F, 0.0F));
        root.addOrReplaceChild("HeadMid", CubeListBuilder.create().texOffs(14, 11).addBox(-5.0F, -4.5F, -2.0F, 10, 3, 10), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("HeadTop", CubeListBuilder.create().texOffs(16, 0).addBox(-5.5F, -7.5F, -2.0F, 11, 3, 7), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("HeadBot", CubeListBuilder.create().texOffs(12, 25).addBox(-6.0F, -2.5F, -3.5F, 12, 5, 10), PartPose.offset(0.0F, 17.0F, -6.0F));

        root.addOrReplaceChild("Lmand1", CubeListBuilder.create().texOffs(0, 0).addBox(1.5F, -1.0F, 1.0F, 1, 5, 2), PartPose.offsetAndRotation(1.0F, 17.0F, -13.0F, 0.7853982F, 0.0F, 0.0F));
        root.addOrReplaceChild("Lmand2", CubeListBuilder.create().texOffs(7, 0).addBox(1.0F, -1.0F, -0.5F, 2, 5, 2), PartPose.offset(1.0F, 16.0F, -13.0F));
        root.addOrReplaceChild("Rmand1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -1.0F, 1.0F, 1, 5, 2), PartPose.offsetAndRotation(1.0F, 17.0F, -13.0F, 0.7853982F, 0.0F, 0.0F));
        root.addOrReplaceChild("Rmand2", CubeListBuilder.create().texOffs(7, 0).addBox(-5.0F, -2.0F, -0.5F, 2, 5, 2), PartPose.offset(1.0F, 17.0F, -13.0F));
        root.addOrReplaceChild("Jaw", CubeListBuilder.create().texOffs(0, 8).addBox(-1.5F, 0.0F, -4.0F, 3, 4, 2), PartPose.offsetAndRotation(0.0F, 17.0F, -6.0F, -0.6283185F, 0.0F, 0.0F));

        root.addOrReplaceChild("LFEye", CubeListBuilder.create().texOffs(0, 15).addBox(0.5F, -6.5F, -3.0F, 3, 3, 1), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("LMEye", CubeListBuilder.create().texOffs(0, 20).addBox(4.0F, -7.0F, -2.5F, 2, 2, 2), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("LBEye", CubeListBuilder.create().texOffs(0, 25).addBox(5.0F, -7.0F, 0.5F, 1, 1, 1), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("RFEye", CubeListBuilder.create().texOffs(0, 15).addBox(-3.5F, -6.5F, -3.0F, 3, 3, 1), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("RMEye", CubeListBuilder.create().texOffs(0, 20).addBox(-6.0F, -7.0F, -2.5F, 2, 2, 2), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("RBEye", CubeListBuilder.create().texOffs(0, 25).addBox(-6.0F, -8.0F, 0.5F, 1, 1, 1), PartPose.offsetAndRotation(0.0F, 18.0F, -7.0F, -0.1745329F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 128);
    }

    private static void addLegPair(PartDefinition root, String name, float x, float y, float z, float hipZ, float yawDegrees,
                                   int rightUpperDetailTextureV) {
        float yaw = yawDegrees * Mth.DEG_TO_RAD;
        PartDefinition left = root.addOrReplaceChild(name + "_left", CubeListBuilder.create(),
                PartPose.offsetAndRotation(x, y, z, 0.0F, yaw, 0.0F));
        PartDefinition right = root.addOrReplaceChild(name + "_right", CubeListBuilder.create(),
                PartPose.offsetAndRotation(-x, y, z, 0.0F, -yaw, 0.0F));
        addLeg(left, 5.0F, hipZ, 0.0F, -HIP_ROLL, 103);
        addLeg(right, -5.0F, hipZ, Mth.PI, HIP_ROLL, rightUpperDetailTextureV);
    }

    private static void addLeg(PartDefinition anchor, float hipX, float hipZ, float yaw, float roll, int upperDetailTextureV) {
        PartDefinition hip = anchor.addOrReplaceChild("hip",
                CubeListBuilder.create().texOffs(0, 93).addBox(-1.0F, -1.0F, -1.0F, 9, 3, 2),
                PartPose.offsetAndRotation(hipX, 17.0F, hipZ, 0.0F, yaw, roll));
        hip.addOrReplaceChild("upper", CubeListBuilder.create().texOffs(0, 99).addBox(2.5F, -7.7F, -1.0F, 6, 1, 2),
                PartPose.rotation(0.0F, 0.0F, 0.5235988F + HIP_ROLL));
        hip.addOrReplaceChild("upper_detail", CubeListBuilder.create().texOffs(0, upperDetailTextureV).addBox(3.7F, -6.0F, -1.0F, 6, 1, 2),
                PartPose.rotation(0.0F, 0.0F, 0.3490659F + HIP_ROLL));
        hip.addOrReplaceChild("lower", CubeListBuilder.create().texOffs(0, 107).addBox(10.0F, 3.0F, -0.5F, 1, 6, 1),
                PartPose.rotation(0.0F, 0.0F, -0.4712389F + HIP_ROLL));
        hip.addOrReplaceChild("lower_detail", CubeListBuilder.create().texOffs(0, 115).addBox(9.0F, 3.5F, -0.5F, 1, 6, 1),
                PartPose.rotation(0.0F, 0.0F, -0.5759587F + HIP_ROLL));
        hip.addOrReplaceChild("foot", CubeListBuilder.create().texOffs(0, 123).addBox(11.0F, 7.5F, -0.5F, 1, 4, 1),
                PartPose.rotation(0.0F, 0.0F, -0.3490659F + HIP_ROLL));
    }

    @Override
    public void setupAnim(JumpingSpiderRenderState state) {
        super.setupAnim(state);
        float cycle = Mth.cos(state.walkAnimationPos * 1.75F) * state.walkAnimationSpeed;
        float swing = cycle * 0.4F;
        float lift = cycle * 0.3F;
        animateHip(frontLeftHip, swing, lift);
        animateHip(middleFrontLeftHip, -swing, lift);
        animateHip(middleBackLeftHip, swing, -lift);
        animateHip(backLeftHip, -swing, lift);
        animateHip(frontRightHip, swing, lift);
        animateHip(middleFrontRightHip, -swing, -lift);
        animateHip(middleBackRightHip, swing, lift);
        animateHip(backRightHip, -swing, -lift);
    }

    private static void animateHip(ModelPart hip, float swing, float lift) {
        hip.yRot += swing;
        hip.zRot += lift;
    }
}
