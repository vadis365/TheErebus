package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.RhinoBeetleRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class RhinoBeetleModel extends EntityModel<RhinoBeetleRenderState> {
    private final ModelPart leftFrontLeg, leftMiddleLeg, leftBackLeg;
    private final ModelPart rightFrontLeg, rightMiddleLeg, rightBackLeg;
    private final ModelPart HeadA, HeadB, HeadC, Eyes, Lplate, Rplate, TplateA, TplateB, TplateC, NoseA, NoseB, NoseC;

    public RhinoBeetleModel(ModelPart root) {
        super(root);
        leftFrontLeg = root.getChild("left_front_leg");
        leftMiddleLeg = root.getChild("left_middle_leg");
        leftBackLeg = root.getChild("left_back_leg");
        rightFrontLeg = root.getChild("right_front_leg");
        rightMiddleLeg = root.getChild("right_middle_leg");
        rightBackLeg = root.getChild("right_back_leg");

        ModelPart body = root.getChild("body");
        this.HeadA = body.getChild("HeadA");
        this.HeadB = body.getChild("HeadB");
        this.HeadC = body.getChild("HeadC");
        this.Eyes = body.getChild("Eyes");
        this.Lplate = body.getChild("Lplate");
        this.Rplate = body.getChild("Rplate");
        this.TplateA = body.getChild("TplateA");
        this.TplateB = body.getChild("TplateB");
        this.TplateC = body.getChild("TplateC");
        this.NoseA = body.getChild("NoseA");
        this.NoseB = body.getChild("NoseB");
        this.NoseC = body.getChild("NoseC");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0F, -1F, -4F));
        body.addOrReplaceChild("Ab", CubeListBuilder.create().texOffs(9, 95).addBox(-5.5F, -4.5F, 0F, 11, 9, 15), PartPose.offset(0F, 17F, 0F));
        body.addOrReplaceChild("AbSide", CubeListBuilder.create().texOffs(10, 64).addBox(-6.5F, -3.5F, 2F, 13, 5, 11), PartPose.offset(0F, 17F, 0F));
        body.addOrReplaceChild("AbTop", CubeListBuilder.create().texOffs(15, 81).addBox(-4F, -6.5F, 2F, 8, 2, 11), PartPose.offset(0F, 17F, 0F));
        body.addOrReplaceChild("AbBack", CubeListBuilder.create().texOffs(27, 120).addBox(-3.5F, -2.5F, 15F, 7, 5, 2), PartPose.offset(0F, 17F, 0F));

        body.addOrReplaceChild("HeadA", CubeListBuilder.create().texOffs(22, 40).addBox(-2F, -3F, -3F, 4, 6, 7), PartPose.offset(0F, 17F, -4F));
        body.addOrReplaceChild("HeadB", CubeListBuilder.create().texOffs(28, 33).addBox(-2F, -1F, -4F, 4, 4, 2), PartPose.offset(0F, 17F, -4F));
        body.addOrReplaceChild("HeadC", CubeListBuilder.create().texOffs(29, 27).addBox(-1.5F, 0F, -6F, 3, 3, 2), PartPose.offset(0F, 17F, -4F));
        body.addOrReplaceChild("Eyes", CubeListBuilder.create().texOffs(0, 36).addBox(-2F, 0.5F, -5.5F, 4, 1, 1), PartPose.offset(0F, 17F, -4F));
        body.addOrReplaceChild("Lplate", CubeListBuilder.create().texOffs(0, 40).addBox(1F, -3F, -1F, 2, 6, 6), PartPose.offsetAndRotation(0F, 17F, -4F, 0F, 0.5235988F, 0F));
        body.addOrReplaceChild("Rplate", CubeListBuilder.create().texOffs(47, 40).addBox(-3F, -3F, -1F, 2, 6, 6), PartPose.offsetAndRotation(0F, 17F, -4F, 0F, -0.5235988F, 0F));
        body.addOrReplaceChild("TplateA", CubeListBuilder.create().texOffs(23, 54).addBox(-1.5F, -3.9F, -0.5F, 3, 2, 7), PartPose.offsetAndRotation(0F, 17F, -4F, 0.6981317F, 0F, 0F));
        body.addOrReplaceChild("TplateB", CubeListBuilder.create().texOffs(0, 60).addBox(-2.5F, -3F, 0.5F, 5, 2, 5), PartPose.offsetAndRotation(0F, 17F, -4F, 0.6981317F, 0F, 0F));
        body.addOrReplaceChild("TplateC", CubeListBuilder.create().texOffs(0, 53).addBox(-3.5F, -1.9F, 2F, 7, 2, 3), PartPose.offsetAndRotation(0F, 17F, -4F, 0.6981317F, 0F, 0F));
        body.addOrReplaceChild("NoseA", CubeListBuilder.create().texOffs(35, 65).addBox(-1F, -5.3F, -6.5F, 2, 6, 2), PartPose.offsetAndRotation(0F, 17F, -4F, 0.3490659F, 0F, 0F));
        body.addOrReplaceChild("NoseB", CubeListBuilder.create().texOffs(36, 69).addBox(-0.5F, -3.5F, -8.2F, 1, 2, 2), PartPose.offsetAndRotation(0F, 17F, -4F, -0.1396263F, 0F, 0F));
        body.addOrReplaceChild("NoseC", CubeListBuilder.create().texOffs(32, 22).addBox(-0.5F, -6.5F, -8.2F, 1, 3, 1), PartPose.offsetAndRotation(0F, 17F, -4F, -0.1396263F, 0F, 0F));
        body.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(21, 66).addBox(-2F, -4F, 2F, 4, 6, 2), PartPose.offset(0F, 17F, -4F));

        // These hip positions include the original renderer's shared leg offset.
        addLeg(root, "left_front_leg", 5F, -4F, -2.617994F, -0.3490659F);
        addLeg(root, "left_middle_leg", 5F, 2.5F, -3.142F, -0.3490659F);
        addLeg(root, "left_back_leg", 5F, 9F, 2.617994F, -0.3490659F);
        addLeg(root, "right_front_leg", -5F, -4F, -0.3490659F, 0.3490659F);
        addLeg(root, "right_middle_leg", -5F, 2.5F, 0F, 0.3490659F);
        addLeg(root, "right_back_leg", -5F, 9F, 0.3490659F, 0.3490659F);

        return LayerDefinition.create(mesh, 64, 128);
    }

    private static void addLeg(PartDefinition root, String name, float x, float z, float yaw, float roll) {
        PartDefinition hip = root.addOrReplaceChild(name, CubeListBuilder.create().texOffs(0, 95).addBox(-4F, -1F, -1F, 4, 2, 2),
                PartPose.offsetAndRotation(x, 19F, z, 0F, yaw, roll));
        hip.addOrReplaceChild("upper", CubeListBuilder.create().texOffs(0, 88).addBox(-5F, 0F, -1F, 2, 4, 2),
                PartPose.rotation(0F, 0F, 0.3490659F));
        hip.addOrReplaceChild("lower", CubeListBuilder.create().texOffs(0, 82).addBox(-3.5F, 4.5F, -0.5F, 2, 4, 1),
                PartPose.rotation(0F, 0F, 0.6981317F));
        hip.addOrReplaceChild("foot", CubeListBuilder.create().texOffs(0, 76).addBox(-1.5F, 8F, -0.5F, 1, 4, 1),
                PartPose.rotation(0F, 0F, 0.8726646F));
    }

    @Override
    public void setupAnim(RhinoBeetleRenderState state) {
        super.setupAnim(state);
        float cos1 = Mth.cos(state.walkAnimationPos + (float) Math.PI) * 0.5F * state.walkAnimationSpeed;
        float cos2 = Mth.cos(state.walkAnimationPos) * 0.5F * state.walkAnimationSpeed;
        float headAngle = state.ramCharge * 0.01F;

        HeadA.xRot = headAngle;
        HeadB.xRot = headAngle;
        HeadC.xRot = headAngle;
        Eyes.xRot = headAngle;
        Lplate.xRot = headAngle;
        Rplate.xRot = headAngle;
        TplateA.xRot = headAngle + 0.6981317F;
        TplateB.xRot = headAngle + 0.6981317F;
        TplateC.xRot = headAngle + 0.6981317F;
        NoseA.xRot = headAngle + 0.3490659F;
        NoseB.xRot = headAngle - 0.1396263F;
        NoseC.xRot = headAngle - 0.1396263F;

        swingLeg(leftFrontLeg, cos1);
        swingLeg(leftMiddleLeg, cos2);
        swingLeg(leftBackLeg, cos1);
        swingLeg(rightFrontLeg, -cos2);
        swingLeg(rightMiddleLeg, -cos1);
        swingLeg(rightBackLeg, -cos2);
    }

    private static void swingLeg(ModelPart leg, float swing) {
        leg.xRot += swing;
        leg.yRot += swing;
    }
}
