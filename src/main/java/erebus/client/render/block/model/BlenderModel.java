package erebus.client.render.block.model;

import erebus.client.render.block.renderer.state.BlenderBlockEntityRenderState;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class BlenderModel extends Model<BlenderBlockEntityRenderState> {

    private static final CubeDeformation ZERO = new CubeDeformation(0);

    public BlenderModel(ModelPart root) {
        super(root, RenderTypes::entitySolid);
        root.getChild("Base");
        root.getChild("Top");
        root.getChild("BottomBlender");
        root.getChild("MiddleBlender");
        root.getChild("Lid");
        root.getChild("HoneyJar");
        root.getChild("HoneyJarLiquid");
        root.getChild("HoneyJarLid");
        root.getChild("MilkJar");
        root.getChild("MilkJarLiquid");
        root.getChild("MilkJarLid");
        root.getChild("AntivenomJar");
        root.getChild("AntivenomJarLiquid");
        root.getChild("AntivenomJarLid");
        root.getChild("BeetleJuiceJar");
        root.getChild("BeetleJuiceJarLiquid");
        root.getChild("BeetleJuiceJarLid");
        root.getChild("HandleTop");
        root.getChild("HandleSide");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition parts = mesh.getRoot();

        parts.addOrReplaceChild(
                "Base",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(0F, 0F, 0F, 16, 15, 16, ZERO),
                PartPose.offset(-8F, 9F, -8F)
        );

        parts.addOrReplaceChild(
                "Top",
                CubeListBuilder.create()
                        .texOffs(66, 0)
                        .addBox(0F, 0F, 0F, 18, 1, 18, ZERO),
                PartPose.offset(-9F, 8F, -9F)
        );

        parts.addOrReplaceChild(
                "BottomBlender",
                CubeListBuilder.create()
                        .texOffs(43, 116)
                        .addBox(0F, 0F, 0F, 6, 3, 6, ZERO),
                PartPose.offset(-3F, 5F, -3F)
        );

        parts.addOrReplaceChild(
                "MiddleBlender",
                CubeListBuilder.create()
                        .texOffs(43, 100)
                        .addBox(0F, 0F, 0F, 4, 7, 4, ZERO),
                PartPose.offset(-2F, -2F, -2F)
        );

        parts.addOrReplaceChild(
                "Lid",
                CubeListBuilder.create()
                        .texOffs(40, 87)
                        .addBox(0F, -3F, 0F, 6, 1, 6, ZERO),
                PartPose.offset(-3F, 0F, -3F)
        );

        parts.addOrReplaceChild(
                "HoneyJar",
                CubeListBuilder.create()
                        .texOffs(87, 71)
                        .addBox(0, 0, 0, 3, 4, 3, ZERO),
                PartPose.offsetAndRotation(-1, 4, 6, 0, 0.5235988F, 0)
        );

        parts.addOrReplaceChild(
                "HoneyJarLiquid",
                CubeListBuilder.create()
                        .texOffs(108, 60)
                        .addBox(0F, 0F, 0F, 1, 4, 1, ZERO),
                PartPose.offsetAndRotation(0.4F, 4F, 6.35F, 0F, 0.5235988F, 0F)
        );

        parts.addOrReplaceChild(
                "HoneyJarLid",
                CubeListBuilder.create()
                        .texOffs(30, 40)
                        .addBox(0F, 0F, 0F, 2, 1, 2, ZERO),
                PartPose.offsetAndRotation(-0.3F, 3F, 6.3F, 0F, 0.5235988F, 0F)
        );

        parts.addOrReplaceChild(
                "MilkJar",
                CubeListBuilder.create()
                        .texOffs(87, 71)
                        .addBox(0F, 0F, 0F, 3, 4, 3, ZERO),
                PartPose.offsetAndRotation(3F, 4F, 4F, 0F, 0.7853982F, 0F)
        );

        parts.addOrReplaceChild(
                "MilkJarLiquid",
                CubeListBuilder.create()
                        .texOffs(108, 50)
                        .addBox(0F, 0F, 0F, 1, 4, 1, ZERO),
                PartPose.offsetAndRotation(4.5F, 8F, 4F, 0F, 0.7853982F, 0F)
        );

        parts.addOrReplaceChild(
                "MilkJarLid",
                CubeListBuilder.create()
                        .texOffs(30, 40)
                        .addBox(0F, 0F, 0F, 2, 1, 2, ZERO),
                PartPose.offsetAndRotation(3.7F, 3F, 4F, 0F, 0.7853982F, 0F)
        );

        parts.addOrReplaceChild(
                "AntivenomJar",
                CubeListBuilder.create()
                        .texOffs(87, 71)
                        .addBox(0F, 0F, 0F, 3, 4, 3, ZERO),
                PartPose.offsetAndRotation(1F, 4F, -8F, 0F, -0.5235988F, 0F)
        );

        parts.addOrReplaceChild(
                "AntivenomJarLiquid",
                CubeListBuilder.create()
                        .texOffs(108, 50)
                        .addBox(0F, 0F, 0F, 1, 4, 1, ZERO),
                PartPose.offsetAndRotation(1.4F, 8F, -6.6F, 0F, -0.5235988F, 0F)
        );

        parts.addOrReplaceChild(
                "AntivenomJarLid",
                CubeListBuilder.create()
                        .texOffs(30, 40)
                        .addBox(0F, 0F, 0F, 2, 1, 2, ZERO),
                PartPose.offsetAndRotation(1.3F, 3F, -7.3F, 0F, -0.5235988F, 0F)
        );

        parts.addOrReplaceChild(
                "BeetleJuiceJar",
                CubeListBuilder.create()
                        .texOffs(87, 71)
                        .addBox(0F, 0F, 0F, 3, 4, 3, ZERO),
                PartPose.offsetAndRotation(3F, 4F, -4F, 0F, 0.7853982F, 0F)
        );

        parts.addOrReplaceChild(
                "BeetleJuiceJarLiquid",
                CubeListBuilder.create()
                        .texOffs(108, 62)
                        .addBox(0F, 0F, 0F, 1, 4, 1, ZERO),
                PartPose.offsetAndRotation(4.5F, 4F, -4F, 0F, 0.7853982F, 0F)
        );

        parts.addOrReplaceChild(
                "BeetleJuiceJarLid",
                CubeListBuilder.create()
                        .texOffs(30, 40)
                        .addBox(0F, 0F, 0F, 2, 1, 2, ZERO),
                PartPose.offsetAndRotation(3.7F, 3F, -4F, 0F, 0.7853982F, 0F)
        );

        parts.addOrReplaceChild(
                "HandleTop",
                CubeListBuilder.create()
                        .texOffs(0, 72)
                        .addBox(0F, 0F, 0F, 2, 1, 1, ZERO),
                PartPose.offset(-1F, -1F, -3F)
        );

        parts.addOrReplaceChild(
                "HandleSide",
                CubeListBuilder.create()
                        .texOffs(0, 61)
                        .addBox(0F, 0F, 0F, 2, 3, 1, ZERO),
                PartPose.offset(-1F, 0F, -4F)
        );

        return LayerDefinition.create(mesh, 256, 128);
    }
}
