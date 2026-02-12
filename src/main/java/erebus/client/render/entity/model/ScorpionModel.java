package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.ScorpionRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ScorpionModel extends EntityModel<ScorpionRenderState> {
    public ModelPart root;
    private final ModelPart RMand, LMand, Head, EyeL, EyeR, Body1, Body2;
    private final ModelPart RBL1, RBL2, RBL3, RBL4;
    private final ModelPart RML1, RML2, RML3, RML4;
    private final ModelPart RFL1, RFL2, RFL3, RFL4;
    private final ModelPart RFFL1, RFFL2, RFFL3, RFFL4;
    private final ModelPart LFFL1, LFFL2, LFFL3, LFFL4;
    private final ModelPart LFL1, LFL2, LFL3, LFL4;
    private final ModelPart LML1, LML2, LML3, LML4;
    private final ModelPart LBL1, LBL2, LBL3, LBL4;
    private final ModelPart ClawR1, ClawR2, ClawR3, ClawR4, ClawR5Top, ClawR5Bot;
    private final ModelPart ClawL1, ClawL2, ClawL3, ClawL4, ClawL5Top, ClawL5Bot;
    private final ModelPart Tail1, Tail2, Tail3, Tail4, Tail5, Tail6, Sting1, Sting2;

    public ScorpionModel(ModelPart root) {
        super(root);
        this.root = root;
        this.RMand = root.getChild("RMand");
        this.LMand = root.getChild("LMand");
        this.Head = root.getChild("Head");
        this.EyeL = root.getChild("EyeL");
        this.EyeR = root.getChild("EyeR");
        this.Body1 = root.getChild("Body1");
        this.Body2 = root.getChild("Body2");

        this.RBL1 = root.getChild("RBL1");
        this.RBL2 = RBL1.getChild("RBL2");
        this.RBL3 = RBL1.getChild("RBL3");
        this.RBL4 = RBL1.getChild("RBL4");

        this.RML1 = root.getChild("RML1");
        this.RML2 = RML1.getChild("RML2");
        this.RML3 = RML1.getChild("RML3");
        this.RML4 = RML1.getChild("RML4");

        this.RFL1 = root.getChild("RFL1");
        this.RFL2 = RFL1.getChild("RFL2");
        this.RFL3 = RFL1.getChild("RFL3");
        this.RFL4 = RFL1.getChild("RFL4");

        this.RFFL1 = root.getChild("RFFL1");
        this.RFFL2 = RFFL1.getChild("RFFL2");
        this.RFFL3 = RFFL1.getChild("RFFL3");
        this.RFFL4 = RFFL1.getChild("RFFL4");

        this.LFFL1 = root.getChild("LFFL1");
        this.LFFL2 = LFFL1.getChild("LFFL2");
        this.LFFL3 = LFFL1.getChild("LFFL3");
        this.LFFL4 = LFFL1.getChild("LFFL4");

        this.LFL1 = root.getChild("LFL1");
        this.LFL2 = LFL1.getChild("LFL2");
        this.LFL3 = LFL1.getChild("LFL3");
        this.LFL4 = LFL1.getChild("LFL4");

        this.LML1 = root.getChild("LML1");
        this.LML2 = LML1.getChild("LML2");
        this.LML3 = LML1.getChild("LML3");
        this.LML4 = LML1.getChild("LML4");

        this.LBL1 = root.getChild("LBL1");
        this.LBL2 = LBL1.getChild("LBL2");
        this.LBL3 = LBL1.getChild("LBL3");
        this.LBL4 = LBL1.getChild("LBL4");

        this.ClawR1 = root.getChild("ClawR1");
        this.ClawR2 = root.getChild("ClawR2");
        this.ClawR3 = root.getChild("ClawR3");
        this.ClawR4 = root.getChild("ClawR4");
        this.ClawR5Top = root.getChild("ClawR5Top");
        this.ClawR5Bot = root.getChild("ClawR5Bot");

        this.ClawL1 = root.getChild("ClawL1");
        this.ClawL2 = root.getChild("ClawL2");
        this.ClawL3 = root.getChild("ClawL3");
        this.ClawL4 = root.getChild("ClawL4");
        this.ClawL5Top = root.getChild("ClawL5Top");
        this.ClawL5Bot = root.getChild("ClawL5Bot");

        this.Tail1 = root.getChild("Tail1");
        this.Tail2 = Tail1.getChild("Tail2");
        this.Tail3 = Tail1.getChild("Tail3");
        this.Tail4 = Tail1.getChild("Tail4");
        this.Tail5 = Tail1.getChild("Tail5");
        this.Tail6 = Tail1.getChild("Tail6");
        this.Sting1 = Tail1.getChild("Sting1");
        this.Sting2 = Tail1.getChild("Sting2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("RMand", CubeListBuilder.create().texOffs(33, 0).addBox(0F, -0.5F, -0.5F, 3, 2, 1), PartPose.offsetAndRotation(-1F, 18F, -5F, 0F, 1.570796F, 0F));
        root.addOrReplaceChild("LMand", CubeListBuilder.create().texOffs(25, 0).addBox(0F, -0.5F, -0.5F, 3, 2, 1), PartPose.offsetAndRotation(1F, 18F, -5F, 0F, 1.570796F, 0F));
        root.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(22, 6).addBox(-3F, -1F, -6F, 6, 3, 4), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("EyeL", CubeListBuilder.create().texOffs(18, 3).addBox(-1F, -1.5F, -5.5F, 1, 1, 2), PartPose.offset(1.5F, 17F, 0F));
        root.addOrReplaceChild("EyeR", CubeListBuilder.create().texOffs(40, 3).addBox(-1F, -1.5F, -5.5F, 1, 1, 2), PartPose.offset(-0.5F, 17F, 0F));
        root.addOrReplaceChild("Body1", CubeListBuilder.create().texOffs(7, 15).addBox(-5.5F, -1.5F, -4.5F, 11, 3, 14), PartPose.offset(0F, 17F, 4F));
        root.addOrReplaceChild("Body2", CubeListBuilder.create().texOffs(9, 33).addBox(-3.5F, -2F, -2F, 7, 4, 17), PartPose.offset(0F, 17F, 0F));

        // Legs R
        PartDefinition RBL1 = root.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(-5F, 17F, 11F, 0F, -2.443461F, -0.83490659F));
        RBL1.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 88).addBox(1F, -5.5F, -1F, 4, 2, 2), PartPose.rotation(0F, 0F, 1.151917F + 0.83490659F));
        RBL1.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(0, 82).addBox(5F, -4F, -0.5F, 4, 2, 1), PartPose.rotation(0F, 0F, 0.8203047F + 0.83490659F));
        RBL1.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(0, 76).addBox(8F, -2.5F, -0.5F, 4, 1, 1), PartPose.rotation(0F, 0F, 0.6981317F + 0.83490659F));

        PartDefinition RML1 = root.addOrReplaceChild("RML1", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 5, 2, 2).mirror(), PartPose.offsetAndRotation(-5F, 17F, 8F, 0F, -2.879793F, -0.83490659F));
        RML1.addOrReplaceChild("RML2", CubeListBuilder.create().texOffs(0, 88).addBox(1F, -5.5F, -1F, 4, 2, 2), PartPose.rotation(0F, 0F, 1.151917F + 0.83490659F));
        RML1.addOrReplaceChild("RML3", CubeListBuilder.create().texOffs(0, 82).addBox(5F, -4F, -0.5F, 4, 2, 1), PartPose.rotation(0F, 0F, 0.8203047F + 0.83490659F));
        RML1.addOrReplaceChild("RML4", CubeListBuilder.create().texOffs(0, 76).addBox(8F, -2.5F, -0.5F, 4, 1, 1), PartPose.rotation(0F, 0F, 0.6981317F + 0.83490659F));

        PartDefinition RFL1 = root.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(-5F, 17F, 5F, 0F, 2.879793F, -0.83490659F));
        RFL1.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(0, 88).addBox(1F, -5.5F, -1F, 4, 2, 2), PartPose.rotation(0F, 0F, 1.151917F + 0.83490659F));
        RFL1.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(0, 82).addBox(5F, -4F, -0.5F, 4, 2, 1), PartPose.rotation(0F, 0F, 0.8203047F + 0.83490659F));
        RFL1.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(0, 76).addBox(8F, -2.5F, -0.5F, 4, 1, 1), PartPose.rotation(0F, 0F, 0.6981317F + 0.83490659F));

        PartDefinition RFFL1 = root.addOrReplaceChild("RFFL1", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(-5F, 17F, 2F, 0F, 2.443461F, -0.83490659F));
        RFFL1.addOrReplaceChild("RFFL2", CubeListBuilder.create().texOffs(0, 88).addBox(1F, -5.5F, -1F, 4, 2, 2), PartPose.rotation(0F, 0F, 1.151917F + 0.83490659F));
        RFFL1.addOrReplaceChild("RFFL3", CubeListBuilder.create().texOffs(0, 82).addBox(5F, -4F, -0.5F, 4, 2, 1), PartPose.rotation(0F, 0F, 0.8203047F + 0.83490659F));
        RFFL1.addOrReplaceChild("RFFL4", CubeListBuilder.create().texOffs(0, 76).addBox(8F, -2.5F, -0.5F, 4, 1, 1), PartPose.rotation(0F, 0F, 0.6981317F + 0.83490659F));

        // Legs L
        PartDefinition LFFL1 = root.addOrReplaceChild("LFFL1", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(5F, 17F, 2F, 0F, 0.6981317F, 0.3490659F));
        LFFL1.addOrReplaceChild("LFFL2", CubeListBuilder.create().texOffs(0, 88).addBox(1F, -5.5F, -1F, 4, 2, 2), PartPose.rotation(0F, 0F, 1.151917F - 0.3490659F));
        LFFL1.addOrReplaceChild("LFFL3", CubeListBuilder.create().texOffs(0, 82).addBox(5F, -4F, -0.5F, 4, 2, 1), PartPose.rotation(0F, 0F, 0.8203047F - 0.3490659F));
        LFFL1.addOrReplaceChild("LFFL4", CubeListBuilder.create().texOffs(0, 76).addBox(8F, -2.5F, -0.5F, 4, 1, 1), PartPose.rotation(0F, 0F, 0.6981317F - 0.3490659F));

        PartDefinition LFL1 = root.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(5F, 17F, 5F, 0F, 0.2617994F, 0.3490659F));
        LFL1.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(0, 88).addBox(1F, -5.5F, -1F, 4, 2, 2), PartPose.rotation(0F, 0F, 1.151917F - 0.3490659F));
        LFL1.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(0, 82).addBox(5F, -4F, -0.5F, 4, 2, 1), PartPose.rotation(0F, 0F, 0.8203047F - 0.3490659F));
        LFL1.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(0, 76).addBox(8F, -2.5F, -0.5F, 4, 1, 1), PartPose.rotation(0F, 0F, 0.6981317F - 0.3490659F));

        PartDefinition LML1 = root.addOrReplaceChild("LML1", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(5F, 17F, 8F, 0F, -0.2617994F, 0.3490659F));
        LML1.addOrReplaceChild("LML2", CubeListBuilder.create().texOffs(0, 88).addBox(1F, -5.5F, -1F, 4, 2, 2), PartPose.rotation(0F, 0F, 1.151917F - 0.3490659F));
        LML1.addOrReplaceChild("LML3", CubeListBuilder.create().texOffs(0, 82).addBox(5F, -4F, -0.5F, 4, 2, 1), PartPose.rotation(0F, 0F, 0.8203047F - 0.3490659F));
        LML1.addOrReplaceChild("LML4", CubeListBuilder.create().texOffs(0, 76).addBox(8F, -2.5F, -0.5F, 4, 1, 1), PartPose.rotation(0F, 0F, 0.6981317F - 0.3490659F));

        PartDefinition LBL1 = root.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 5, 2, 2), PartPose.offsetAndRotation(5F, 17F, 11F, 0F, -0.6981317F, 0.3490659F));
        LBL1.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(0, 88).addBox(1F, -5.5F, -1F, 4, 2, 2), PartPose.rotation(0F, 0F, 1.151917F - 0.3490659F));
        LBL1.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(0, 82).addBox(5F, -4F, -0.5F, 4, 2, 1), PartPose.rotation(0F, 0F, 0.8203047F - 0.3490659F));
        LBL1.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(0, 76).addBox(8F, -2.5F, -0.5F, 4, 1, 1), PartPose.rotation(0F, 0F, 0.6981317F - 0.3490659F));

        // Claws
        root.addOrReplaceChild("ClawR1", CubeListBuilder.create().texOffs(44, 24).addBox(-2F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(-3F, 18F, -5F, 0F, -1.047198F, 0F));
        root.addOrReplaceChild("ClawR2", CubeListBuilder.create().texOffs(44, 19).addBox(-5F, -1F, -4F, 6, 2, 2), PartPose.offsetAndRotation(-3F, 18F, -5F, 0F, 0.523599F, 0F));
        root.addOrReplaceChild("ClawR3", CubeListBuilder.create().texOffs(44, 12).addBox(-6F, -1.5F, 3F, 4, 3, 3), PartPose.offsetAndRotation(-3F, 18F, -5F, 0F, -1.396263F, 0F));
        root.addOrReplaceChild("ClawR4", CubeListBuilder.create().texOffs(44, 3).addBox(-9.5F, -2F, 4F, 6, 4, 4), PartPose.offsetAndRotation(-3F, 18F, -5F, 0F, -1.745329F, 0F));
        root.addOrReplaceChild("ClawR5Top", CubeListBuilder.create().texOffs(43, 0).addBox(-8.5F, -1.5F, 10F, 4, 1, 1), PartPose.offsetAndRotation(-3F, 18F, -5F, 0F, -2.268928F, 0F));
        root.addOrReplaceChild("ClawR5Bot", CubeListBuilder.create().texOffs(54, 0).addBox(-8.5F, 0.5F, 10F, 4, 1, 1), PartPose.offsetAndRotation(-3F, 18F, -5F, 0F, -2.268928F, 0F));

        root.addOrReplaceChild("ClawL1", CubeListBuilder.create().texOffs(8, 24).addBox(-2F, -1F, -1F, 4, 2, 2), PartPose.offsetAndRotation(3F, 18F, -5F, 0F, -2.094395F, 0F));
        root.addOrReplaceChild("ClawL2", CubeListBuilder.create().texOffs(4, 19).addBox(-5F, -1F, 2F, 6, 2, 2), PartPose.offsetAndRotation(3F, 18F, -5F, 0F, 2.617994F, 0F));
        root.addOrReplaceChild("ClawL3", CubeListBuilder.create().texOffs(6, 12).addBox(-6F, -1.5F, -6F, 4, 3, 3), PartPose.offsetAndRotation(3F, 18F, -5F, 0F, -1.745329F, 0F));
        root.addOrReplaceChild("ClawL4", CubeListBuilder.create().texOffs(0, 3).addBox(-9.5F, -2F, -8F, 6, 4, 4), PartPose.offsetAndRotation(3F, 18F, -5F, 0F, -1.396263F, 0F));
        root.addOrReplaceChild("ClawL5Top", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, -1.5F, -11F, 4, 1, 1), PartPose.offsetAndRotation(3F, 18F, -5F, 0F, -0.8726646F, 0F));
        root.addOrReplaceChild("ClawL5Bot", CubeListBuilder.create().texOffs(11, 0).addBox(-8.5F, 0.5F, -11F, 4, 1, 1), PartPose.offsetAndRotation(3F, 18F, -5F, 0F, -0.8726646F, 0F));

        // Tail
        PartDefinition Tail1 = root.addOrReplaceChild("Tail1", CubeListBuilder.create().texOffs(23, 55).addBox(-3F, -0.5F, -2.5F, 6, 3, 4), PartPose.offset(0F, 16F, 17.5F));
        Tail1.addOrReplaceChild("Tail2", CubeListBuilder.create().texOffs(24, 63).addBox(-2.5F, -1.5F, 0F, 5, 3, 5), PartPose.rotation(0.8726646F, 0F, 0F));
        Tail1.addOrReplaceChild("Tail3", CubeListBuilder.create().texOffs(24, 72).addBox(-2F, -0.3F, 4F, 4, 3, 5), PartPose.rotation(1.22173F, 0F, 0F));
        Tail1.addOrReplaceChild("Tail4", CubeListBuilder.create().texOffs(24, 81).addBox(-1.5F, 2.5F, 7.5F, 3, 3, 6), PartPose.rotation(1.570796F, 0F, 0F));
        Tail1.addOrReplaceChild("Tail5", CubeListBuilder.create().texOffs(25, 91).addBox(-1F, 7F, 11F, 2, 2, 6), PartPose.rotation(1.919862F, 0F, 0F));
        Tail1.addOrReplaceChild("Tail6", CubeListBuilder.create().texOffs(25, 100).addBox(-1F, 12F, 13F, 2, 2, 6), PartPose.rotation(2.268928F, 0F, 0F));
        Tail1.addOrReplaceChild("Sting1", CubeListBuilder.create().texOffs(24, 109).addBox(-2F, 16F, 13F, 4, 4, 5), PartPose.rotation(2.617994F, 0F, 0F));
        Tail1.addOrReplaceChild("Sting2", CubeListBuilder.create().texOffs(27, 119).addBox(-0.5F, -6.5F, 18F, 1, 1, 5), PartPose.rotation(1.570796F, 0F, 0F));

        return LayerDefinition.create(mesh, 64, 128);
    }
}
