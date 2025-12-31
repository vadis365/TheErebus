package erebus.client.render.item.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.util.Mth;

public class ArmorGliderModel extends EntityModel<HumanoidRenderState> {

    private final ModelPart body;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightWingBase;
    private final ModelPart leftWingBase;
    private final ModelPart rightWing;
    private final ModelPart leftWing;

    public ArmorGliderModel(ModelPart root) {
        super(root);
        body = root.getChild("Body");
        rightArm = root.getChild("RightArm");
        leftArm = root.getChild("LeftArm");
        rightWingBase = root.getChild("RightWingBase");
        leftWingBase = root.getChild("LeftWingBase");
        rightWing = root.getChild("RightWing");
        leftWing = root.getChild("LeftWing");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild(
                "Body",
                CubeListBuilder.create()
                        .texOffs(38, 24)
                        .addBox(-4, 0, -2, 16, 22, 8),
                PartPose.ZERO
        );

        part.addOrReplaceChild(
                "RightArm",
                CubeListBuilder.create()
                        .texOffs(84, 0)
                        .addBox(-3, -2, -2, 8, 10, 8),
                PartPose.offset(-5, 2, 0)
        );

        part.addOrReplaceChild(
                "LeftArm",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-1, -2, -2, 8, 10, 8),
                PartPose.offset(5, 2, 0)
        );

        part.addOrReplaceChild(
                "RightWingBase",
                CubeListBuilder.create()
                        .texOffs(104, 32)
                        .addBox(-1.5F, -1.5F, -1.5F, 6, 6, 6),
                PartPose.offset(-2, 2, -3.5F)
        );

        part.addOrReplaceChild(
                "LeftWingBase",
                CubeListBuilder.create()
                        .texOffs(0, 32)
                        .addBox(-1.5F, -1.5F, -1.5F, 6, 6, 6),
                PartPose.offset(2, 2, 3.5F)
        );

        part.addOrReplaceChild(
                "RightWing",
                CubeListBuilder.create()
                        .texOffs(88, 66)
                        .addBox(-4.5F, 0, -0.51F, 18, 28, 1),
                PartPose.offsetAndRotation(-2, 3, 3.5F, 0, 0, 1.570796F)
        );

        part.addOrReplaceChild(
                "LeftWing",
                CubeListBuilder.create()
                        .texOffs(0, 66)
                        .addBox(-4.5F, 0, -0.5F, 18, 28, 1),
                PartPose.offsetAndRotation(2, 3, 3.5F, 0, 0, -1.570796F)
        );

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(HumanoidRenderState state) {
        super.setupAnim(state);
        rightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.5F * limbSwingAmount * 0.5F;
        leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.5F * limbSwingAmount * 0.5F;

        if (!state.isFallFlying) {
            rightWing.zRot = 0F;
            leftWing.zRot = 0F;
            if (entity.xOld != entity.getX() || entity.zOld != entity.getZ()) {
                rightWing.xRot = 0.7F;
                leftWing.xRot = 0.7F;
            } else {
                rightWing.xRot = 0.0F;
                leftWing.xRot = 0.0F;
            }
        }
        if (entity.isFallFlying() && !entity.onGround()) {
            rightWing.zRot = 1.570796F;
            leftWing.zRot = -1.570796F;
        }
        if (entity.isCrouching()) {
            body.xRot = 0.5F;
            rightArm.xRot += 0.4F;
            leftArm.xRot += 0.4F;
            rightWingBase.xRot = 0.5F;
            leftWingBase.xRot = 0.5F;
            if (!entity.isFallFlying()) {
                rightWing.xRot = 0.5F;
                leftWing.xRot = 0.5F;
            }
            rightWingBase.z = 4.5F;
            leftWingBase.z = 4.5F;
            rightWing.z = 4.5F;
            leftWing.z = 4.5F;
            rightArm.z = -0.5F;
            leftArm.z = -0.5F;
            rightArm.y = 4F;
            leftArm.y = 4F;
        } else {
            body.xRot = 0.0F;
            rightWingBase.xRot = 0.0F;
            leftWingBase.xRot = 0.0F;
            rightWingBase.z = 3.5F;
            leftWingBase.z = 3.5F;
            rightWing.z = 3.5F;
            leftWing.z = 3.5F;
        }
    }
}
