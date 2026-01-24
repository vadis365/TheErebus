package erebus.client.render.block.model;

import erebus.client.render.block.renderer.state.GlowingJarBlockEntityRenderState;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.rendertype.RenderTypes;

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
}
