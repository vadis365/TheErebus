package erebus.client.render.block.model;

import org.jetbrains.annotations.NotNull;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;

public class GlowingJarModel extends Model {

    private final ModelPart jar, lid, neck;

    public GlowingJarModel(ModelPart root) {
        super(RenderType::entityTranslucent);
        jar = root.getChild("jar");
        lid = root.getChild("lid");
        neck = root.getChild("neck");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild(
                "jar",
                CubeListBuilder.create()
                        .texOffs(0, 27)
                        .addBox(-6, -1, -6, 12, 1, 12),
                PartPose.ZERO
        );

        part.addOrReplaceChild(
                "lid",
                CubeListBuilder.create()
                        .texOffs(0, 41)
                        .addBox(-7, -4, -7, 14, 3, 14),
                PartPose.ZERO
        );

        part.addOrReplaceChild(
                "neck",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-7, 0, -7, 14, 12, 14),
                PartPose.ZERO
        );

        return LayerDefinition.create(mesh, 128, 64);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertex, int light, int overlay, int colour) {
		lid.render(poseStack, vertex, light, overlay, colour);
    }

    public void renderGlassParts(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertex, int light, int overlay, int colour) {
        neck.render(poseStack, vertex, light, overlay, colour);
        jar.render(poseStack, vertex, light, overlay, colour);
    }
}
