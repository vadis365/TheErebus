package erebus.client.render.item.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class WarHammerModel extends Model {
    public final ModelPart head;
    public final ModelPart head2;
    public final ModelPart handle;
    public final ModelPart counterWeight;

    public WarHammerModel(ModelPart root) {
        super(RenderType::entitySolid);
        head = root.getChild("head");
        head2 = root.getChild("head2");
        handle = root.getChild("handle");
        counterWeight = root.getChild("counterWeight");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(
                                -2,
                                -4,
                                -4.5F,
                                4,
                                4,
                                9,
                                new CubeDeformation(0)
                        ),
                PartPose.offset(0, 9, 0)
        );

        partdefinition.addOrReplaceChild(
                "head2",
                CubeListBuilder.create()
                        .texOffs(0, 14)
                        .addBox(
                                -1,
                                0,
                                -1,
                                2,
                                1,
                                2,
                                new CubeDeformation(0)
                        ),
                PartPose.offset(0, 9, 0)
        );

        partdefinition.addOrReplaceChild(
                "handle",
                CubeListBuilder.create()
                        .texOffs(27, 0)
                        .addBox(
                                -0.5F,
                                1,
                                -0.5F,
                                1,
                                12,
                                1,
                                new CubeDeformation(0)
                        ),
                PartPose.offset(0, 9, 0)
        );

        partdefinition.addOrReplaceChild(
                "counterWeight",
                CubeListBuilder.create()
                        .texOffs(0, 18)
                        .addBox(
                                -1,
                                13,
                                -1,
                                2,
                                2,
                                2,
                                new CubeDeformation(0)
                        ),
                PartPose.offset(0, 9, 0)
        );

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        head2.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        handle.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        counterWeight.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
