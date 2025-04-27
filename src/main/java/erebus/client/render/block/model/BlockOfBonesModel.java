package erebus.client.render.block.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import org.jetbrains.annotations.NotNull;

public class BlockOfBonesModel extends Model {

    private final ModelPart skullMain;
    private final ModelPart skullTop;
    private final ModelPart skullLeft;
    private final ModelPart skullRight;
    private final ModelPart skullBack;
    private final ModelPart skullNose;
    private final ModelPart skullJaw;

    private final ModelPart boneL1;
    private final ModelPart boneL2;
    private final ModelPart boneL3;
    private final ModelPart boneL4;

    private final ModelPart boneR1;
    private final ModelPart boneR2;
    private final ModelPart boneR3;
    private final ModelPart boneR4;

    public BlockOfBonesModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.skullMain = root.getChild("Skull_Main");
        this.skullTop = root.getChild("SkullTop");
        this.skullLeft = root.getChild("SkullLeft");
        this.skullRight = root.getChild("SkullRight");
        this.skullBack = root.getChild("Skull_Back");
        this.skullNose = root.getChild("SkullNose");
        this.skullJaw = root.getChild("SkullJaw");

        this.boneL1 = root.getChild("BoneL1");
        this.boneL2 = root.getChild("BoneL2");
        this.boneL3 = root.getChild("BoneL3");
        this.boneL4 = root.getChild("BoneL4");

        this.boneR1 = root.getChild("BoneR1");
        this.boneR2 = root.getChild("BoneR2");
        this.boneR3 = root.getChild("BoneR3");
        this.boneR4 = root.getChild("BoneR4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild(
                "Skull_Main",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.5F, -9.0F, 0.0F, 8, 8, 7, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, -0.122173F, 0.5235988F, 0.0F)
        );

        part.addOrReplaceChild(
                "SkullTop",
                CubeListBuilder.create()
                        .texOffs(35, 0)
                        .addBox(-2.5F, -10.5F, 1.0F, 6, 2, 5, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, -0.1396263F, 0.5235988F, 0.0F)
        );

        part.addOrReplaceChild(
                "SkullLeft",
                CubeListBuilder.create()
                        .texOffs(32, 7)
                        .addBox(4.0F, -8.0F, 1.0F, 1, 5, 5, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, -0.1396263F, 0.5235988F, 0.0F)
        );

        part.addOrReplaceChild(
                "SkullRight",
                CubeListBuilder.create()
                        .texOffs(45, 7)
                        .addBox(-4.0F, -8.0F, 1.0F, 1, 5, 5, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, -0.1396263F, 0.5235988F, 0.0F)
        );

        part.addOrReplaceChild(
                "Skull_Back",
                CubeListBuilder.create()
                        .texOffs(38, 18)
                        .addBox(-2.5F, -8.0F, 7.0F, 6, 6, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, -0.122173F, 0.5235988F, 0.0F)
        );

        part.addOrReplaceChild(
                "SkullNose",
                CubeListBuilder.create()
                        .texOffs(21, 6)
                        .addBox(0.0F, -5.0F, 1.3F, 1, 2, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.296706F, 0.5235988F, 0.0F)
        );

        part.addOrReplaceChild(
                "SkullJaw",
                CubeListBuilder.create()
                        .texOffs(15, 20)
                        .addBox(-2.5F, -3.0F, 0.0F, 5, 3, 6, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0.0F, 24.0F, -1.0F, -0.122173F, 0.5235988F, 0F)
        );

        part.addOrReplaceChild(
                "BoneL1",
                CubeListBuilder.create()
                        .texOffs(0, 26)
                        .addBox(4.5F, -1F, 2F, 3, 1, 2, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0F, 0.837758F, 0F)
        );

        part.addOrReplaceChild(
                "BoneL2",
                CubeListBuilder.create()
                        .texOffs(0, 23)
                        .addBox(1F, -1F, -3F, 5, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0F, 0.3490659F, 0F)
        );

        part.addOrReplaceChild(
                "BoneL3",
                CubeListBuilder.create()
                        .texOffs(0, 23)
                        .addBox(1F, -1F, -5F, 5, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0F, 0.296706F, 0F)
        );

        part.addOrReplaceChild(
                "BoneL4",
                CubeListBuilder.create()
                        .texOffs(0, 23)
                        .addBox(0F, -1F, -7F, 5, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0F, 0.2268928F, 0F)
        );

        part.addOrReplaceChild(
                "BoneR1",
                CubeListBuilder.create()
                        .texOffs(0, 17)
                        .addBox(-6.5F, -2F, 4F, 4, 2, 3, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0F, -0.2094395F, 0F)
        );

        part.addOrReplaceChild(
                "BoneR2",
                CubeListBuilder.create()
                        .texOffs(0, 23)
                        .addBox(-5F, -1F, -3F, 5, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0F, 0.5235988F, 0F)
        );

        part.addOrReplaceChild(
                "BoneR3",
                CubeListBuilder.create()
                        .texOffs(0, 23)
                        .addBox(-5F, -1F, -5F, 5, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0F, 0.4363323F, 0F)
        );

        part.addOrReplaceChild(
                "BoneR4",
                CubeListBuilder.create()
                        .texOffs(0, 23)
                        .addBox(-6F, -1F, -7F, 5, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0F, 0.3490659F, 0F)
        );

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack stack, @NotNull VertexConsumer consumer, int light, int overlay, int color) {
        skullMain.render(stack, consumer, light, overlay, color);
        skullTop.render(stack, consumer, light, overlay, color);
        skullLeft.render(stack, consumer, light, overlay, color);
        skullRight.render(stack, consumer, light, overlay, color);
        skullBack.render(stack, consumer, light, overlay, color);
        skullNose.render(stack, consumer, light, overlay, color);
        skullJaw.render(stack, consumer, light, overlay, color);
        boneL1.render(stack, consumer, light, overlay, color);
        boneL2.render(stack, consumer, light, overlay, color);
        boneL3.render(stack, consumer, light, overlay, color);
        boneL4.render(stack, consumer, light, overlay, color);
        boneR1.render(stack, consumer, light, overlay, color);
        boneR2.render(stack, consumer, light, overlay, color);
        boneR3.render(stack, consumer, light, overlay, color);
        boneR4.render(stack, consumer, light, overlay, color);
    }
}
