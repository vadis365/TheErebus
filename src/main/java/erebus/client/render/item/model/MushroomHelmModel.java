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

public class MushroomHelmModel<T extends LivingEntity> extends PlayerModel<T> {

    private final ModelPart top;
    private final ModelPart middle;
    private final ModelPart front;
    private final ModelPart back;
    private final ModelPart right;
    private final ModelPart left;
    private final ModelPart mainHead;

    public MushroomHelmModel(ModelPart playerModel, boolean isSlim, ModelPart root) {
        super(playerModel, isSlim);
        top = root.getChild("Top");
        middle = top.getChild("Middle");
        front = top.getChild("Front");
        back = top.getChild("Back");
        right = top.getChild("Right");
        left = top.getChild("Left");
        mainHead = top.getChild("MainHead");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        PartDefinition top = part.addOrReplaceChild(
                "Top",
                CubeListBuilder.create()
                        .texOffs(28,  0)
                        .addBox(-4.5F, -12F, -4.5F, 9, 1, 9),
                PartPose.ZERO
        );

        top.addOrReplaceChild(
                "Middle",
                CubeListBuilder.create()
                        .texOffs(6, 21)
                        .addBox(-6.5F, -11F, -6.5F, 13, 2, 13),
                PartPose.ZERO
        );

        top.addOrReplaceChild(
                "Front",
                CubeListBuilder.create()
                        .texOffs(0, 37)
                        .addBox(-8.5F, -9F, -8.5F, 17, 2, 2),
                PartPose.ZERO
        );

        top.addOrReplaceChild(
                "Back",
                CubeListBuilder.create()
                        .texOffs(0, 42)
                        .addBox(-8.5F, -9F, 6.5F, 17, 2, 2),
                PartPose.ZERO
        );

        top.addOrReplaceChild(
                "Right",
                CubeListBuilder.create()
                        .texOffs(33, 48)
                        .addBox(-8.5F, -9F, -6.5F, 2, 2, 13),
                PartPose.ZERO
        );

        top.addOrReplaceChild(
                "Left",
                CubeListBuilder.create()
                        .texOffs(1, 48)
                        .addBox(6.5F, -9F, -6.5F, 2, 2, 13),
                PartPose.ZERO
        );

        top.addOrReplaceChild(
                "MainHead",
                CubeListBuilder.create()
                        .texOffs(0, 2)
                        .addBox(-4.5F, -9F, -4.5F, 9, 9, 9),
                PartPose.ZERO
        );

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack pose, @NotNull VertexConsumer vertex, int packedLight, int packedOverlay, int color) {
        top.render(pose, vertex, packedLight, packedOverlay, color);
    }

    @Override
    protected @NotNull Iterable<ModelPart> headParts() {
        return ImmutableList.of(top, middle, front, back, right, left, mainHead);
    }

    @Override
    protected @NotNull Iterable<ModelPart> bodyParts() {
        return ImmutableList.of();
    }

    @Override
    public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        top.copyFrom(head);
    }
}
