package erebus.client.render.block.model;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.block.renderer.state.GlowingJarBlockEntityRenderState;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.jspecify.annotations.Nullable;

public class GlowingJarModel extends Model<GlowingJarBlockEntityRenderState> {

    public GlowingJarModel(ModelPart root) {
        super(root, RenderTypes::entityTranslucent);
        root.getChild("jar");
        root.getChild("lid");
        root.getChild("neck");
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

    public void submit(PoseStack pose, SubmitNodeCollector collector,
                       RenderType renderType, int light, int overlay,
                       @Nullable TextureAtlasSprite sprite,
                       int outline, ModelFeatureRenderer.@Nullable CrumblingOverlay breaking) {
        collector.submitModelPart(root().getChild("lid"), pose, renderType, light, overlay, sprite, false, false, -1, breaking, outline);
        collector.submitModelPart(root().getChild("jar"), pose, renderType, light, overlay, sprite, false, false, 0x99CCCCCC, breaking, outline);
        collector.submitModelPart(root().getChild("neck"), pose, renderType, light, overlay, sprite, false, false, 0x99CCCCCC, breaking, outline);
    }
}
