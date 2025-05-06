package erebus.client.render.block.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import org.jetbrains.annotations.NotNull;

public class BlenderModel extends Model {

    private static final CubeDeformation ZERO = new CubeDeformation(0);
    private final ModelPart base;
    private final ModelPart top;
    private final ModelPart bottomBlender;
    private final ModelPart middleBlender;
    private final ModelPart lid;
    private final ModelPart honeyJar;
    private final ModelPart honeyJarLiquid;
    private final ModelPart honeyJarLid;
    private final ModelPart milkJar;
    private final ModelPart milkJarLiquid;
    private final ModelPart milkJarLid;
    private final ModelPart antivenomJar;
    private final ModelPart antivenomJarLiquid;
    private final ModelPart antivenomJarLid;
    private final ModelPart beetleJuiceJar;
    private final ModelPart beetleJuiceJarLiquid;
    private final ModelPart beetleJuiceJarLid;
    private final ModelPart handleTop;
    private final ModelPart handleSide;

    public BlenderModel(ModelPart root) {
        super(RenderType::entitySolid);
        base = root.getChild("Base");
        top = root.getChild("Top");
        bottomBlender = root.getChild("BottomBlender");
        middleBlender = root.getChild("MiddleBlender");
        lid = root.getChild("Lid");
        honeyJar = root.getChild("HoneyJar");
        honeyJarLiquid = root.getChild("HoneyJarLiquid");
        honeyJarLid = root.getChild("HoneyJarLid");
        milkJar = root.getChild("MilkJar");
        milkJarLiquid = root.getChild("MilkJarLiquid");
        milkJarLid = root.getChild("MilkJarLid");
        antivenomJar = root.getChild("AntivenomJar");
        antivenomJarLiquid = root.getChild("AntivenomJarLiquid");
        antivenomJarLid = root.getChild("AntivenomJarLid");
        beetleJuiceJar = root.getChild("BeetleJuiceJar");
        beetleJuiceJarLiquid = root.getChild("BeetleJuiceJarLiquid");
        beetleJuiceJarLid = root.getChild("BeetleJuiceJarLid");
        handleTop = root.getChild("HandleTop");
        handleSide = root.getChild("HandleSide");
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

    @Override
    public void renderToBuffer(@NotNull PoseStack stack, @NotNull VertexConsumer consumer, int light, int overlay, int color) {
        base.render(stack, consumer, light, overlay, color);
        top.render(stack, consumer, light, overlay, color);
        bottomBlender.render(stack, consumer, light, overlay, color);
        middleBlender.render(stack, consumer, light, overlay, color);
        lid.render(stack, consumer, light, overlay, color);
        honeyJar.render(stack, consumer, light, overlay, color);
        honeyJarLiquid.render(stack, consumer, light, overlay, color);
        honeyJarLid.render(stack, consumer, light, overlay, color);
        milkJar.render(stack, consumer, light, overlay, color);
        milkJarLiquid.render(stack, consumer, light, overlay, color);
        milkJarLid.render(stack, consumer, light, overlay, color);
        antivenomJar.render(stack, consumer, light, overlay, color);
        antivenomJarLiquid.render(stack, consumer, light, overlay, color);
        antivenomJarLid.render(stack, consumer, light, overlay, color);
        beetleJuiceJar.render(stack, consumer, light, overlay, color);
        beetleJuiceJarLiquid.render(stack, consumer, light, overlay, color);
        beetleJuiceJarLid.render(stack, consumer, light, overlay, color);
        handleTop.render(stack, consumer, light, overlay, color);
        handleSide.render(stack, consumer, light, overlay, color);
    }
}
