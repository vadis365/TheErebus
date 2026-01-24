package erebus.client.render.item.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

public class RhinoHeadModel extends EntityModel<HumanoidRenderState> {

    public RhinoHeadModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        PartDefinition helmFront = part.addOrReplaceChild(
                "HelmFront",
                CubeListBuilder.create()
                        .texOffs(22, 0)
                        .addBox(-4.5F, -8F, -5.5F, 9, 2, 1),
                PartPose.ZERO
        );

        helmFront.addOrReplaceChild(
                "HelmTop",
                CubeListBuilder.create()
                        .texOffs(17, 4)
                        .addBox(-3.5F, -8F, -4.5F, 7, 1, 8),
                PartPose.ZERO
        );

        helmFront.addOrReplaceChild(
                "HelmRight",
                CubeListBuilder.create()
                        .texOffs(46, 8)
                        .addBox(-4.5F, -8F, -4.5F, 1, 8, 8),
                PartPose.ZERO
        );

        helmFront.addOrReplaceChild(
                "HelmLeft",
                CubeListBuilder.create()
                        .texOffs(1, 8)
                        .addBox(3.5F, -8F, -4.5F, 1, 8, 8),
                PartPose.ZERO
        );

        helmFront.addOrReplaceChild(
                "HelmBack",
                CubeListBuilder.create()
                        .texOffs(25, 15)
                        .addBox(-4.5F, -8F, 3.5F, 9, 8, 1),
                PartPose.ZERO
        );

        helmFront.addOrReplaceChild(
                "CrestTop",
                CubeListBuilder.create()
                        .texOffs(8, 18)
                        .addBox(-1F, -10F, -5.5F, 2, 2, 12),
                PartPose.ZERO
        );

        helmFront.addOrReplaceChild(
                "CrestBack",
                CubeListBuilder.create()
                        .texOffs(0, 5)
                        .addBox(-1F, -8F, 4.5F, 2, 8, 2),
                PartPose.ZERO
        );

        helmFront.addOrReplaceChild(
                "RightHorn1",
                CubeListBuilder.create()
                        .texOffs(43, 0)
                        .addBox(-3F, -14.3F, -1.5F, 2, 6, 2),
                PartPose
                        .rotation(0.3490659F, 0.1745329F, 0F)
        );

        helmFront.addOrReplaceChild(
                "RightHorn2",
                CubeListBuilder.create()
                        .texOffs(52, 0)
                        .addBox(-2.5F, -13.5F, -7.5F, 1, 2, 2),
                PartPose
                        .rotation(-0.1396263F, 0.1745329F, 0F)
        );

        helmFront.addOrReplaceChild(
                "RightHorn3",
                CubeListBuilder.create()
                        .texOffs(59, 0)
                        .addBox(-2.5F, -16.5F, -7.5F, 1, 3, 1),
                PartPose
                        .rotation(-0.1396263F, 0.1745329F, 0F)
        );

        helmFront.addOrReplaceChild(
                "LeftHorn1",
                CubeListBuilder.create()
                        .texOffs(13, 0)
                        .addBox(1F, -14.3F, -1.5F, 2, 6, 2),
                PartPose
                        .rotation(0.3490659F, -0.1745329F, 0F)
        );

        helmFront.addOrReplaceChild(
                "LeftHorn2",
                CubeListBuilder.create()
                        .texOffs(6, 0)
                        .addBox(1.5F, -13.5F, -7.5F, 1, 2, 2),
                PartPose
                        .rotation(-0.1396263F, -0.1745329F, 0F)
        );

        helmFront.addOrReplaceChild(
                "LeftHorn3",
                CubeListBuilder.create()
                        .texOffs(1, 0)
                        .addBox(1.5F, -16.5F, -7.5F, 1, 3, 1),
                PartPose
                        .rotation(-0.1396263F, -0.1745329F, 0F)
        );

        helmFront.addOrReplaceChild(
                "RightTopPlate",
                CubeListBuilder.create()
                        .texOffs(45, 25)
                        .addBox(-3F, -6.8F, 4.5F, 1, 3, 3),
                PartPose
                        .rotation(0.7853982F, 0F, 0F)
        );

        helmFront.addOrReplaceChild(
                "LeftTopPlate",
                CubeListBuilder.create()
                        .texOffs(45, 25)
                        .addBox(2F, -6.8F, 4.5F, 1, 3, 3),
                PartPose
                        .rotation(0.7853982F, 0F, 0F)
        );

        helmFront.addOrReplaceChild(
                "RightEar",
                CubeListBuilder.create()
                        .texOffs(54, 25)
                        .addBox(-5.5F, -4F, 1.5F, 1, 3, 3),
                PartPose
                        .rotation(0.7853982F, 0F, 0F)
        );

        helmFront.addOrReplaceChild(
                "LeftEar",
                CubeListBuilder.create()
                        .texOffs(54, 25)
                        .addBox(4.5F, -4F, 1.5F, 1, 3, 3),
                PartPose
                        .rotation(0.7853982F, 0F, 0F)
        );

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(HumanoidRenderState state) {
        super.setupAnim(state);
    }
}
