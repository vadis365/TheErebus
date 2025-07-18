package erebus.client.render.item.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class RhinoHeadModel<T extends LivingEntity> extends PlayerModel<T> {

    private final ModelPart helmFront, helmTop, helmRight, helmLeft, helmBack;
    private final ModelPart crestTop, crestBack;
    private final ModelPart rightHorn1, rightHorn2, rightHorn3;
    private final ModelPart leftHorn1, leftHorn2, leftHorn3;
    private final ModelPart rightTopPlate, leftTopPlate;
    private final ModelPart rightEar, leftEar;

    public RhinoHeadModel(ModelPart playerModel, boolean isSlim, ModelPart root) {
        super(playerModel, isSlim);
        helmFront = root.getChild("HelmFront");
        helmTop = helmFront.getChild("HelmTop");
        helmRight = helmFront.getChild("HelmRight");
        helmLeft = helmFront.getChild("HelmLeft");
        helmBack = helmFront.getChild("HelmBack");
        crestTop = helmFront.getChild("CrestTop");
        crestBack = helmFront.getChild("CrestBack");
        rightHorn1 = helmFront.getChild("RightHorn1");
        rightHorn2 = helmFront.getChild("RightHorn2");
        rightHorn3 = helmFront.getChild("RightHorn3");
        leftHorn1 = helmFront.getChild("LeftHorn1");
        leftHorn2 = helmFront.getChild("LeftHorn2");
        leftHorn3 = helmFront.getChild("LeftHorn3");
        rightTopPlate = helmFront.getChild("RightTopPlate");
        leftTopPlate = helmFront.getChild("LeftTopPlate");
        rightEar = helmFront.getChild("RightEar");
        leftEar = helmFront.getChild("LeftEar");
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
    public void renderToBuffer(@NotNull PoseStack pose, @NotNull VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        helmFront.render(pose, buffer, packedLight, packedOverlay, color);
    }

    @Override
    protected @NotNull Iterable<ModelPart> headParts() {
        return ImmutableList.of(
                helmFront, helmTop, helmRight, helmLeft, helmBack,
                crestTop, crestBack,
                rightHorn1, rightHorn2, rightHorn3,
                leftHorn1, leftHorn2, leftHorn3,
                rightTopPlate, leftTopPlate,
                rightEar, leftEar
        );
    }

    @Override
    protected @NotNull Iterable<ModelPart> bodyParts() {
        return ImmutableList.of();
    }

    @Override
    public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        helmFront.copyFrom(head);
    }
}
