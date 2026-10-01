package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.CrushroomRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class CrushroomModel extends EntityModel<CrushroomRenderState> {

    private final ModelPart capTop;
    private final ModelPart capBottom;
    private final ModelPart head;
    private final ModelPart chest;
    private final ModelPart rightArm1;
    private final ModelPart leftArm1;
    private final ModelPart belly;
    private final ModelPart rightThigh;
    private final ModelPart leftThigh;

    public CrushroomModel(ModelPart root) {
        super(root);
        this.chest = root.getChild("chest");
        this.capTop = chest.getChild("capTop");
        this.capBottom = chest.getChild("capBottom");
        this.head = chest.getChild("head");
        this.rightArm1 = chest.getChild("rightArm1");
        this.leftArm1 = chest.getChild("leftArm1");
        this.belly = root.getChild("belly");
        this.rightThigh = root.getChild("rightThigh");
        this.leftThigh = root.getChild("leftThigh");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        PartDefinition chest = part.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(34, 54).addBox(-9.0F, -14.0F, -4.0F, 18.0F, 8.0F, 10.0F), PartPose.offset(0.0F, 10.0F, 7.0F));
        chest.addOrReplaceChild("capTop", CubeListBuilder.create().texOffs(39, 0).addBox(-6.0F, -8.0F, -11.0F, 12.0F, 2.0F, 12.0F), PartPose.offset(0.0F, -14.0F, 1.0F));
        chest.addOrReplaceChild("capBottom", CubeListBuilder.create().texOffs(30, 15).addBox(-8.0F, -7.0F, -13.0F, 16.0F, 3.0F, 16.0F), PartPose.offset(0.0F, -14.0F, 1.0F));
        chest.addOrReplaceChild("head", CubeListBuilder.create().texOffs(46, 35).addBox(-4.0F, -4.0F, -9.0F, 8.0F, 10.0F, 8.0F), PartPose.offset(0.0F, -14.0F, 1.0F));

        PartDefinition rightArm1 = chest.addOrReplaceChild("rightArm1", CubeListBuilder.create().texOffs(7, 43).addBox(-5.0F, -2.0F, -2.5F, 5.0F, 10.0F, 5.0F), PartPose.offset(-9.0F, -10.0F, 1.0F));
        rightArm1.addOrReplaceChild("rightArm2", CubeListBuilder.create().texOffs(9, 59).addBox(-4.5F, 5.0F, 2.0F, 4.0F, 7.0F, 4.0F), PartPose.rotation(-0.7504916F, 0.0F, 0.0F));
        rightArm1.addOrReplaceChild("rightFist", CubeListBuilder.create().texOffs(5, 71).addBox(-4.5F, 12.0F, 1.0F, 6.0F, 6.0F, 6.0F), PartPose.rotation(-0.7504916F, 0.0F, 0.0F));

        PartDefinition leftArm1 = chest.addOrReplaceChild("leftArm1", CubeListBuilder.create().texOffs(101, 43).addBox(0.0F, -2.0F, -2.5F, 5.0F, 10.0F, 5.0F), PartPose.offset(9.0F, -10.0F, 1.0F));
        leftArm1.addOrReplaceChild("leftArm2", CubeListBuilder.create().texOffs(103, 59).addBox(0.5F, 5.0F, 2.0F, 4.0F, 7.0F, 4.0F), PartPose.rotation(-0.7504916F, 0.0F, 0.0F));
        leftArm1.addOrReplaceChild("leftFist", CubeListBuilder.create().texOffs(99, 71).addBox(-1.5F, 12.0F, 1.0F, 6.0F, 6.0F, 6.0F), PartPose.rotation(-0.7504916F, 0.0F, 0.0F));

        part.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(44, 73).addBox(-5.0F, -6.0F, -3.0F, 10.0F, 7.0F, 8.0F), PartPose.offsetAndRotation(0.0F, 10.0F, 7.0F, 1.047198F, 0.0F, 0.0F));
        part.addOrReplaceChild("hips", CubeListBuilder.create().texOffs(42, 89).addBox(-6.0F, -4.0F, -4.0F, 12.0F, 8.0F, 9.0F), PartPose.offset(0.0F, 10.0F, 7.0F));

        PartDefinition rightThigh = part.addOrReplaceChild("rightThigh", CubeListBuilder.create().texOffs(7, 84).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 7.0F, 5.0F), PartPose.offsetAndRotation(-5.0F, 11.0F, 7.0F, 0.0F, 0.0F, 0.7853982F));
        rightThigh.addOrReplaceChild("rightAnkle", CubeListBuilder.create().texOffs(9, 97).addBox(-5.5F, 3.0F, -2.0F, 4.0F, 5.0F, 4.0F), PartPose.rotation(0.0F, 0.0F, -0.7853982F));
        rightThigh.addOrReplaceChild("rightFoot1", CubeListBuilder.create().texOffs(5, 107).addBox(-6.5F, 8.0F, -3.0F, 6.0F, 2.0F, 6.0F), PartPose.rotation(0.0F, 0.0F, -0.7853982F));
        rightThigh.addOrReplaceChild("rightFoot2", CubeListBuilder.create().texOffs(1, 116).addBox(-7.5F, 10.0F, -4.0F, 8.0F, 3.0F, 8.0F), PartPose.rotation(0.0F, 0.0F, -0.7853982F));

        PartDefinition leftThigh = part.addOrReplaceChild("leftThigh", CubeListBuilder.create().texOffs(101, 84).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 7.0F, 5.0F), PartPose.offsetAndRotation(5.0F, 11.0F, 7.0F, 0.0F, 0.0F, -0.7853982F));
        leftThigh.addOrReplaceChild("leftAnkle", CubeListBuilder.create().texOffs(103, 97).addBox(1.5F, 3.0F, -2.0F, 4.0F, 5.0F, 4.0F), PartPose.rotation(0.0F, 0.0F, 0.7853982F));
        leftThigh.addOrReplaceChild("leftFoot1", CubeListBuilder.create().texOffs(99, 107).addBox(0.5F, 8.0F, -3.0F, 6.0F, 2.0F, 6.0F), PartPose.rotation(0.0F, 0.0F, 0.7853982F));
        leftThigh.addOrReplaceChild("leftFoot2", CubeListBuilder.create().texOffs(95, 116).addBox(-0.5F, 10.0F, -4.0F, 8.0F, 3.0F, 8.0F), PartPose.rotation(0.0F, 0.0F, 0.7853982F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(CrushroomRenderState state) {
        super.setupAnim(state);
        float walkCos = Mth.cos(state.walkAnimationPos * 0.7F) * 0.3F * state.walkAnimationSpeed;
        float walkSin = Mth.sin(state.walkAnimationPos * 0.7F) * 0.3F * state.walkAnimationSpeed;
        float hit = Mth.sin(-state.smashCount * 0.1F);
        rightArm1.zRot = -walkCos * 0.5F;
        leftArm1.zRot = walkCos * 0.5F;
        rightThigh.xRot = -walkSin;
        leftThigh.xRot = walkSin;
        rightThigh.yRot = leftThigh.yRot = -walkCos;
        float pitch = state.xRot * Mth.DEG_TO_RAD;
        float body = 0, headPitch = pitch, rightArm = walkSin, leftArm = -walkSin;
        if (state.standing == 0) {
            body = 1.047198F;
            headPitch -= body;
            rightArm -= body;
            leftArm -= body;
            capTop.y = capBottom.y = head.y = -12;
            capTop.z = capBottom.z = head.z = 3;
        } else if (state.standing == 2) {
            body = hit * 0.5F;
            headPitch += body;
            rightArm = leftArm = hit * 3;
        } else if (state.standing == 3) {
            body = -hit * 2;
            headPitch += hit * 2;
            rightArm = leftArm = hit * 2.5F;
        }
        chest.xRot = belly.xRot = body;
        capTop.xRot = capBottom.xRot = head.xRot = headPitch;
        rightArm1.xRot = rightArm;
        leftArm1.xRot = leftArm;
    }
}
