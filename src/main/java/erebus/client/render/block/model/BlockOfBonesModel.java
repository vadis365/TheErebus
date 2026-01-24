package erebus.client.render.block.model;

import erebus.client.render.block.renderer.state.BlockOfBonesBlockEntityRenderState;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class BlockOfBonesModel extends Model<BlockOfBonesBlockEntityRenderState> {

    public BlockOfBonesModel(ModelPart root) {
        super(root, RenderTypes::entitySolid);
        root.getChild("Skull_Main");
        root.getChild("SkullTop");
        root.getChild("SkullLeft");
        root.getChild("SkullRight");
        root.getChild("Skull_Back");
        root.getChild("SkullNose");
        root.getChild("SkullJaw");

        root.getChild("BoneL1");
        root.getChild("BoneL2");
        root.getChild("BoneL3");
        root.getChild("BoneL4");

        root.getChild("BoneR1");
        root.getChild("BoneR2");
        root.getChild("BoneR3");
        root.getChild("BoneR4");
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
}
