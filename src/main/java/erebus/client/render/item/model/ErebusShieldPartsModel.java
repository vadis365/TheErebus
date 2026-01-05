package erebus.client.render.item.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import org.jetbrains.annotations.NotNull;

public class ErebusShieldPartsModel extends Model {
    public final ModelPart handle;
    public final ModelPart boss1;
    public final ModelPart boss2;

    public ErebusShieldPartsModel(ModelPart root) {
        super(RenderType::entitySolid);
        handle = root.getChild("handle");
        boss1 = root.getChild("boss1");
        boss2 = root.getChild("boss2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition parts = mesh.getRoot();

        parts.addOrReplaceChild(
                "handle",
                CubeListBuilder.create()
                        .texOffs(26, 0)
                        .addBox(-1, 3, -1, 2, 6, 6, new CubeDeformation(0)),
                PartPose.ZERO
        );

        parts.addOrReplaceChild(
                "boss1",
                CubeListBuilder.create()
                        .texOffs(7, 4)
                        .addBox(-1.5F, -1.5F, -3.25F, 3, 3, 1, new CubeDeformation(0)),
                PartPose.ZERO
        );

        parts.addOrReplaceChild(
                "boss2",
                CubeListBuilder.create()
                        .texOffs(7, 9)
                        .addBox(-0.5F, -0.5F, -4.25F, 1, 1, 1, new CubeDeformation(0)),
                PartPose.ZERO
        );

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        handle.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        boss1.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        boss2.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
