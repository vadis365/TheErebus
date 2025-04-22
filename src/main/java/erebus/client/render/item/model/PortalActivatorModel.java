package erebus.client.render.item.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class PortalActivatorModel extends Model {

    public final ModelPart[] boxes = new ModelPart[8];

    public PortalActivatorModel(ModelPart root) {
        super(RenderType::entitySolid);
        for (int c = 0; c < boxes.length; c++) {
            ModelPart box = root.getChild("box" + c);
            boxes[c] = box;
        }
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition box0 = partdefinition.addOrReplaceChild(
                "box0",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2,
                                -2,
                                -2,
                                4,
                                4,
                                4,
                                new CubeDeformation(0)),
                PartPose.ZERO
        );

        PartDefinition box1 = partdefinition.addOrReplaceChild(
                "box1",
                CubeListBuilder.create()
                        .texOffs(0, 8)
                        .addBox(-0.5F,
                                0,
                                -0.5F,
                                1,
                                14,
                                1,
                                new CubeDeformation(0)),
                PartPose.ZERO
        );

        PartDefinition box2 = partdefinition.addOrReplaceChild(
                "box2",
                CubeListBuilder.create()
                        .texOffs(16, 4)
                        .addBox(-1.5F,
                                2,
                                -1.5F,
                                3,
                                1,
                                3,
                                new CubeDeformation(0)),
                PartPose.ZERO
        );

        PartDefinition box3 = partdefinition.addOrReplaceChild(
                "box3",
                CubeListBuilder.create()
                        .texOffs(4, 20)
                        .addBox(-1,
                                12,
                                -1,
                                2,
                                1,
                                2,
                                new CubeDeformation(0)),
                PartPose.ZERO
        );

        PartDefinition box4 = partdefinition.addOrReplaceChild(
                "box4",
                CubeListBuilder.create()
                        .texOffs(5, 9)
                        .addBox(-0.5F,
                                -0.5F,
                                -2.5F,
                                1,
                                3,
                                1,
                                new CubeDeformation(0)),
                PartPose.ZERO
        );

        PartDefinition box5 = partdefinition.addOrReplaceChild(
                "box5",
                CubeListBuilder.create()
                        .texOffs(5, 9)
                        .addBox(-0.5F,
                                -0.5F,
                                1.5F,
                                1,
                                3,
                                1,
                                new CubeDeformation(0)),
                PartPose.ZERO
        );

        PartDefinition box6 = partdefinition.addOrReplaceChild(
                "box6",
                CubeListBuilder.create()
                        .texOffs(5, 9)
                        .addBox(-2.5F,
                                -0.5F,
                                -0.5F,
                                1,
                                3,
                                1,
                                new CubeDeformation(0)),
                PartPose.ZERO
        );

        PartDefinition box7 = partdefinition.addOrReplaceChild(
                "box7",
                CubeListBuilder.create()
                        .texOffs(5, 9)
                        .addBox(1.5F,
                                -0.5F,
                                -0.5F,
                                1,
                                3,
                                1,
                                new CubeDeformation(0)),
                PartPose.ZERO
        );

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        for (ModelPart box : boxes) {
            box.render(poseStack, buffer, packedLight, packedOverlay, color);
        }
    }
}
