package erebus.client.render.item.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class WandOfPreservationModel extends Model {

    public final ModelPart topMid;
    public final ModelPart topMain;
    public final ModelPart topBase;
    public final ModelPart shaft1;
    public final ModelPart shaft2;
    public final ModelPart pommel1;
    public final ModelPart pommel2;

    public WandOfPreservationModel(ModelPart root) {
        super(RenderType::entitySolid);
        topMid = root.getChild("topMid");
        topMain = root.getChild("topMain");
        topBase = root.getChild("topBase");
        shaft1 = root.getChild("shaft1");
        shaft2 = root.getChild("shaft2");
        pommel1 = root.getChild("pommel1");
        pommel2 = root.getChild("pommel2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition topMid = partdefinition.addOrReplaceChild(
                "topMid",
                CubeListBuilder.create()
                        .texOffs(21, 0)
                        .addBox(
                                -3,
                                1,
                                -1,
                                6,
                                6,
                                6,
                                new CubeDeformation(0)
                        ),
                PartPose.ZERO
        );

        PartDefinition topMain = partdefinition.addOrReplaceChild(
                "topMain",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(
                                -2.5F,
                                2,
                                -2.5F,
                                5,
                                6,
                                5,
                                new CubeDeformation(0)
                        ),
                PartPose.offsetAndRotation(0, 0, 0, 0, 0.7853982F, 0)
        );

        PartDefinition topBase = partdefinition.addOrReplaceChild(
                "topBase",
                CubeListBuilder.create()
                        .texOffs(46, 5)
                        .addBox(
                                -1.5F,
                                8,
                                -1.5F,
                                3,
                                1,
                                3,
                                new CubeDeformation(0)
                        ),
                PartPose.ZERO
        );

        PartDefinition shaft1 = partdefinition.addOrReplaceChild(
                "shaft1",
                CubeListBuilder.create()
                        .texOffs(0, 14)
                        .addBox(
                                -1,
                                9,
                                -1,
                                2,
                                11,
                                2,
                                new CubeDeformation(0)
                        ),
                PartPose.ZERO
        );

        PartDefinition shaft2 = partdefinition.addOrReplaceChild(
                "shaft2",
                CubeListBuilder.create()
                        .texOffs(9, 14)
                        .addBox(
                                -1,
                                9,
                                -1,
                                2,
                                11,
                                2,
                                new CubeDeformation(0)
                        ),
                PartPose.offsetAndRotation(0, 0, 0, 0, 0.7853982F, 0)
        );

        PartDefinition pommel1 = partdefinition.addOrReplaceChild(
                "pommel1",
                CubeListBuilder.create()
                        .texOffs(21, 13)
                        .addBox(
                                -1.5F,
                                21,
                                -1.5F,
                                3,
                                3,
                                3,
                                new CubeDeformation(0)
                        ),
                PartPose.ZERO
        );

        PartDefinition pommel2 = partdefinition.addOrReplaceChild(
                "pommel2",
                CubeListBuilder.create()
                        .texOffs(21, 20)
                        .addBox(
                                -1.5F,
                                20,
                                -1.5F,
                                3,
                                3,
                                3,
                                new CubeDeformation(0)
                        ),
                PartPose.offsetAndRotation(0, 0, 0, 0, 0.7853982F, 0)
        );

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        topMid.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        topMain.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        topBase.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        shaft1.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        shaft2.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        pommel1.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        pommel2.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
