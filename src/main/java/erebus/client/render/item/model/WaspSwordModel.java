package erebus.client.render.item.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class WaspSwordModel extends Model {
    public final ModelPart clawR4;
    public final ModelPart clawR5Top;
    public final ModelPart clawR5Bot;

    public WaspSwordModel(ModelPart root) {
        super(RenderType::entitySolid);
        clawR4 = root.getChild("ClawR4");
        clawR5Top = root.getChild("ClawR5Top");
        clawR5Bot = root.getChild("ClawR5Bot");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild(
                "ClawR4",
                CubeListBuilder.create()
                        .texOffs(0, 6)
                        .addBox(
                                -2,
                                0,
                                -2,
                                4,
                                6,
                                4,
                                new CubeDeformation(0)
                        ),
                PartPose.ZERO
        );

        partdefinition.addOrReplaceChild(
                "ClawR5Top",
                CubeListBuilder.create()
                        .texOffs(11, 0)
                        .addBox(
                                -3,
                                5,
                                0.5F,
                                1,
                                4,
                                1,
                                new CubeDeformation(0)
                        ),
                PartPose.offsetAndRotation(0, 0, 0, 0, 0, -0.3490659F)
        );

        partdefinition.addOrReplaceChild(
                "ClawR5Bot",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(
                                -3,
                                5,
                                -1.5F,
                                1,
                                4,
                                1,
                                new CubeDeformation(0)
                        ),
                PartPose.offsetAndRotation(0, 0, 0, 0, 0, -0.3490659F)
        );

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        clawR4.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        clawR5Top.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        clawR5Bot.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
