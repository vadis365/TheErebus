package erebus.client.render.item.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.Unit;

public class WaspSwordModel extends Model<Unit> {
    public final ModelPart point;
    public final ModelPart blade;
    public final ModelPart spikeTop1;
    public final ModelPart spikeTop2;
    public final ModelPart spikeMid1;
    public final ModelPart spikeMid2;
    public final ModelPart spikeBot1;
    public final ModelPart spikeBot2;
    public final ModelPart Decl4;
    public final ModelPart Decl3;
    public final ModelPart Decl2;
    public final ModelPart Decl1;
    public final ModelPart DecR1;
    public final ModelPart DecR2;
    public final ModelPart DecR3;
    public final ModelPart DecR4;
    public final ModelPart tang;
    public final ModelPart tangJewel;
    public final ModelPart hilt;
    public final ModelPart pommel;

    public WaspSwordModel(ModelPart root) {
        super(root, RenderTypes::entitySolid);
        point = root.getChild("Point");
        blade = root.getChild("Blade");
        spikeTop1 = root.getChild("SpikeTop1");
        spikeTop2 = root.getChild("SpikeTop2");
        spikeMid1 = root.getChild("SpikeMid1");
        spikeMid2 = root.getChild("SpikeMid2");
        spikeBot1 = root.getChild("SpikeBot1");
        spikeBot2 = root.getChild("SpikeBot2");
        Decl4 = root.getChild("DecL4");
        Decl3 = root.getChild("DecL3");
        Decl2 = root.getChild("DecL2");
        Decl1 = root.getChild("DecL1");
        DecR1 = root.getChild("DecR1");
        DecR2 = root.getChild("DecR2");
        DecR3 = root.getChild("DecR3");
        DecR4 = root.getChild("DecR4");
        tang = root.getChild("Tang");
        tangJewel = root.getChild("TangJewel");
        hilt = root.getChild("Hilt");
        pommel = root.getChild("Pommel");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild(
                "Point",
                CubeListBuilder.create()
                        .texOffs(23, 0)
                        .addBox(
                                -1.5F,
                                -1.5F,
                                -0.5F,
                                3,
                                3,
                                1,
                                new CubeDeformation(0)
                        ),
                PartPose.offsetAndRotation(0, -41, 0, 0, 0, 0.7853982F)
        );

        partdefinition.addOrReplaceChild(
                "Blade",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(
                                -2,
                                -65,
                                -0.5F,
                                4,
                                45,
                                1,
                                new CubeDeformation(0)
                        ),
                PartPose.offset(0, 24, 0)
        );

        partdefinition.addOrReplaceChild(
                "SpikeTop1",
                CubeListBuilder.create()
                        .texOffs(11, 0)
                        .addBox(
                                1,
                                -60,
                                -1,
                                3,
                                2,
                                2,
                                new CubeDeformation(0)
                        ),
                PartPose.offset(0, 24, 0)
        );

        partdefinition.addOrReplaceChild(
                "SpikeTop2",
                CubeListBuilder.create()
                        .texOffs(23, 6)
                        .addBox(
                                0,
                                0,
                                -0.5F,
                                1,
                                4,
                                1,
                                new CubeDeformation(0)
                        ),
                PartPose.offsetAndRotation(3.2F, -35.5F, 0, 0, 0, -0.5235988F)
        );

        partdefinition.addOrReplaceChild(
                "SpikeMid1",
                CubeListBuilder.create()
                        .texOffs(11, 6)
                        .addBox(
                                1,
                                -52,
                                -1,
                                3,
                                2,
                                2,
                                new CubeDeformation(0)
                        ),
                PartPose.offset(0, 24, 0)
        );

        partdefinition.addOrReplaceChild(
                "SpikeMid2",
                CubeListBuilder.create()
                        .texOffs(28, 6)
                        .addBox(
                                0,
                                0,
                                -0.5F,
                                1,
                                4,
                                1,
                                new CubeDeformation(0)
                        ),
                PartPose.offsetAndRotation(3.2F, -27.5F, 0, 0, 0, -0.5235988F)
        );

        partdefinition.addOrReplaceChild(
                "SpikeBot1",
                CubeListBuilder.create()
                        .texOffs(11, 12)
                        .addBox(
                                1,
                                -44,
                                -1,
                                3,
                                2,
                                2,
                                new CubeDeformation(0)
                        ),
                PartPose.offset(0, 24, 0)
        );

        partdefinition.addOrReplaceChild(
                "SpikeBot2",
                CubeListBuilder.create()
                        .texOffs(23, 12)
                        .addBox(
                                0,
                                0,
                                -0.5F,
                                1,
                                4,
                                1,
                                new CubeDeformation(0)
                        ),
                PartPose.offsetAndRotation(3.2F, -19.5F, 0, 0, 0, -0.5235988F)
        );

        partdefinition.addOrReplaceChild(
                "DecL4",
                CubeListBuilder.create()
                        .texOffs(25, 20)
                        .addBox(
                                3.5F,
                                -27,
                                -0.5F,
                                1,
                                2,
                                1,
                                new CubeDeformation(0)
                        ),
                PartPose.offset(0, 24, 0)
        );

        partdefinition.addOrReplaceChild(
                "DecL3",
                CubeListBuilder.create()
                        .texOffs(25, 24)
                        .addBox(
                                15.5F,
                                -20,
                                -1,
                                1,
                                4,
                                2,
                                new CubeDeformation(0)
                        ),
                PartPose.offsetAndRotation(0, 24, 0, 0, 0, 0 - 0.5235988F)
        );

        partdefinition.addOrReplaceChild(
                "DecL2",
                CubeListBuilder.create()
                        .texOffs(27, 31)
                        .addBox(
                                -7,
                                -22,
                                -0.5F,
                                1,
                                4,
                                1,
                                new CubeDeformation(0)
                        ),
                PartPose.offsetAndRotation(0, 24, 0, 0, 0, 0.5235988F)
        );

        partdefinition.addOrReplaceChild(
                "DecL1",
                CubeListBuilder.create()
                        .texOffs(27, 52)
                        .addBox(
                                3,
                                -18,
                                -0.5F,
                                1,
                                2,
                                1,
                                new CubeDeformation(0)
                        ),
                PartPose.offset(0, 24, 0)
        );

        partdefinition.addOrReplaceChild(
                "DecR1",
                CubeListBuilder.create()
                        .texOffs(11, 52)
                        .addBox(
                                -4,
                                -18,
                                -0.5F,
                                1,
                                2,
                                1,
                                new CubeDeformation(0)
                        ),
                PartPose.offset(0, 24, 0)
        );

        partdefinition.addOrReplaceChild(
                "DecR2",
                CubeListBuilder.create()
                        .texOffs(11, 31)
                        .addBox(
                                6,
                                -22,
                                -0.5F,
                                1,
                                4,
                                1,
                                new CubeDeformation(0)
                        ),
                PartPose.offsetAndRotation(0, 24, 0, 0, 0, -0.5235988F)
        );

        partdefinition.addOrReplaceChild(
                "DecR3",
                CubeListBuilder.create()
                        .texOffs(11, 24)
                        .addBox(
                                -16.5F,
                                -20,
                                -1,
                                1,
                                4,
                                2,
                                new CubeDeformation(0)
                        ),
                PartPose.offsetAndRotation(0, 24, 0, 0, 0, 0.5235988F)
        );

        partdefinition.addOrReplaceChild(
                "DecR4",
                CubeListBuilder.create()
                        .texOffs(13, 20)
                        .addBox(
                                -4.5F,
                                -27,
                                -0.5F,
                                1,
                                2,
                                1,
                                new CubeDeformation(0)
                        ),
                PartPose.offset(0, 24, 0)
        );

        partdefinition.addOrReplaceChild(
                "Tang",
                CubeListBuilder.create()
                        .texOffs(10, 46)
                        .addBox(
                                -4,
                                -20,
                                -1.5F,
                                8,
                                2,
                                3,
                                new CubeDeformation(0)
                        ),
                PartPose.offset(0, 24, 0)
        );

        partdefinition.addOrReplaceChild(
                "TangJewel",
                CubeListBuilder.create()
                        .texOffs(13, 37)
                        .addBox(
                                -15,
                                -15,
                                -2,
                                4,
                                4,
                                4,
                                new CubeDeformation(0)
                        ),
                PartPose.offsetAndRotation(0, 24, 0, 0, 0, 0.7853982F)
        );

        partdefinition.addOrReplaceChild(
                "Hilt",
                CubeListBuilder.create()
                        .texOffs(0, 49)
                        .addBox(
                                -1.5F,
                                -18,
                                -1,
                                3,
                                13,
                                2,
                                new CubeDeformation(0)
                        ),
                PartPose.offset(0, 24, 0)
        );

        partdefinition.addOrReplaceChild(
                "Pommel",
                CubeListBuilder.create()
                        .texOffs(13, 56)
                        .addBox(
                                -5,
                                -5,
                                -2,
                                4,
                                4,
                                4,
                                new CubeDeformation(0)
                        ),
                PartPose.offsetAndRotation(0, 24, 0, 0, 0, 0.7853982F)
        );

        return LayerDefinition.create(meshdefinition, 32, 64);
    }
}
