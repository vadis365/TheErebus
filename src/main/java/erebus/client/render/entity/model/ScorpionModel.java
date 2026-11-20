package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.ScorpionRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class ScorpionModel extends EntityModel<ScorpionRenderState> {
    private final ModelPart RMand, LMand, Head, EyeL, EyeR, Body1, Body2;
    private final ModelPart RBL1;
    private final ModelPart RML1;
    private final ModelPart RFL1;
    private final ModelPart RFFL1;
    private final ModelPart LFFL1;
    private final ModelPart LFL1;
    private final ModelPart LML1;
    private final ModelPart LBL1;
    private final ModelPart ClawR1, ClawR2, ClawR3, ClawR4, ClawR5Top, ClawR5Bot;
    private final ModelPart ClawL1, ClawL2, ClawL3, ClawL4, ClawL5Top, ClawL5Bot;
    private final ModelPart Tail1, Tail2, Tail3, Tail4, Tail5, Tail6, Sting1, Sting2;
    public ModelPart root;

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

        this.RML1 = root.getChild("RML1");

        this.RFL1 = root.getChild("RFL1");

        this.RFFL1 = root.getChild("RFFL1");

        this.LFFL1 = root.getChild("LFFL1");

        this.LFL1 = root.getChild("LFL1");

        this.LML1 = root.getChild("LML1");

        this.LBL1 = root.getChild("LBL1");

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

        addLeg(root, "RBL", -5F, 11F, -2.443461F);
        addLeg(root, "RML", -5F, 8F, -2.879793F);
        addLeg(root, "RFL", -5F, 5F, 2.879793F);
        addLeg(root, "RFFL", -5F, 2F, 2.443461F);
        addLeg(root, "LFFL", 5F, 2F, 0.6981317F);
        addLeg(root, "LFL", 5F, 5F, 0.2617994F);
        addLeg(root, "LML", 5F, 8F, -0.2617994F);
        addLeg(root, "LBL", 5F, 11F, -0.6981317F);

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

    private static void addLeg(PartDefinition root, String name, float x, float z, float yaw) {
        float roll = x < 0F ? 0.3F : -0.3F;
        PartDefinition hip = root.addOrReplaceChild(name + "1",
                CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 5, 2, 2),
                PartPose.offsetAndRotation(x, 17F, z, 0F, yaw, roll));
        hip.addOrReplaceChild(name + "2", CubeListBuilder.create().texOffs(0, 88).addBox(1F, -5.5F, -1F, 4, 2, 2),
                PartPose.rotation(0F, 0F, 1.151917F));
        hip.addOrReplaceChild(name + "3", CubeListBuilder.create().texOffs(0, 82).addBox(5F, -4F, -0.5F, 4, 2, 1),
                PartPose.rotation(0F, 0F, 0.8203047F));
        hip.addOrReplaceChild(name + "4", CubeListBuilder.create().texOffs(0, 76).addBox(8F, -2.5F, -0.5F, 4, 1, 1),
                PartPose.rotation(0F, 0F, 0.6981317F));
    }

    private static void swingLeg(ModelPart hip, float yaw, float roll) {
        hip.yRot += yaw;
        hip.zRot += roll;
    }

    @Override
    public void setupAnim(ScorpionRenderState state) {
        super.setupAnim(state);
        float cosz1 = Mth.cos(state.walkAnimationPos + (float) Math.PI) * 0.3F * state.walkAnimationSpeed;
        float cosz2 = Mth.cos(state.walkAnimationPos) * 0.3F * state.walkAnimationSpeed;
        float cozy1 = Mth.cos(state.walkAnimationPos + (float) Math.PI) * 0.4F * state.walkAnimationSpeed;
        float cozy2 = Mth.cos(state.walkAnimationPos) * 0.4F * state.walkAnimationSpeed;
        float cosxnz1 = Mth.cos(state.walkAnimationPos / 3.0F + (float) Math.PI) * 0.1F * state.walkAnimationSpeed;
        float cosxnz2 = Mth.cos(state.walkAnimationPos / 3.0F + (float) Math.PI) * 0.05F * state.walkAnimationSpeed;
        swingLeg(LBL1, cozy1, cosz1);
        swingLeg(LML1, cozy2, cosz2);
        swingLeg(LFL1, cozy1, cosz1);
        swingLeg(LFFL1, cozy2, cosz2);
        swingLeg(RBL1, -cozy2, -cosz2);
        swingLeg(RML1, -cozy1, -cosz1);
        swingLeg(RFL1, -cozy2, -cosz2);
        swingLeg(RFFL1, -cozy1, -cosz1);
        ClawR1.xRot = -cosxnz1;
        ClawR1.zRot = cosxnz1;
        ClawR2.xRot = -cosxnz1;
        ClawR3.xRot = cosxnz1;
        ClawR4.xRot = cosxnz1;
        ClawR5Bot.xRot = cosxnz2;
        ClawR5Top.xRot = cosxnz2;
        ClawR5Bot.zRot = cosxnz2;
        ClawR5Top.zRot = cosxnz2;
        ClawL1.xRot = -cosxnz1;
        ClawL1.zRot = cosxnz1;
        ClawL2.xRot = -cosxnz1;
        ClawL3.xRot = cosxnz1;
        ClawL4.xRot = cosxnz1;
        ClawL5Bot.xRot = cosxnz2;
        ClawL5Top.xRot = cosxnz2;
        ClawL5Bot.zRot = cosxnz2;
        ClawL5Top.zRot = cosxnz2;
        Tail1.zRot = -cosxnz1;
        Tail2.zRot = -cosxnz1;
        Tail3.zRot = -cosxnz1;
        Tail4.zRot = -cosxnz1;
        Tail5.zRot = -cosxnz1;
        Tail6.zRot = -cosxnz1;
        Sting1.zRot = -cosxnz1;
        Sting2.zRot = -cosxnz1;
        if (state.captured) {
            Tail1.xRot = state.stingAngle;
            Tail2.xRot = state.stingAngle + 0.8726646F;
            Tail3.xRot = state.stingAngle + 1.22173F;
            Tail4.xRot = state.stingAngle + 1.570796F;
            Tail5.xRot = state.stingAngle + 1.919862F;
            Tail6.xRot = state.stingAngle + 2.268928F;
            Sting1.xRot = state.stingAngle + 2.617994F;
            Sting2.xRot = state.stingAngle + 1.570796F;
            ClawR1.zRot = 0.2943951F;
            ClawR2.xRot = -0F;
            ClawR2.zRot = 0.2943951F;
            ClawR3.zRot = 0.2943951F;
            ClawR4.zRot = 0.2943951F;
            ClawR5Bot.xRot = 0.14719755F;
            ClawR5Top.xRot = 0.14719755F;
            ClawL1.zRot = -0.2943951F;
            ClawL2.zRot = -0.2943951F;
            ClawL2.xRot = 0F;
            ClawL3.zRot = -0.2943951F;
            ClawL4.zRot = -0.2943951F;
            ClawL5Bot.xRot = -0.14719755F;
            ClawL5Top.xRot = -0.14719755F;
        }
        if (!state.captured) {
            Tail1.xRot = 0F;
            Tail2.xRot = 0.8726646F;
            Tail3.xRot = 1.22173F;
            Tail4.xRot = 1.570796F;
            Tail5.xRot = 1.919862F;
            Tail6.xRot = 2.268928F;
            Sting1.xRot = 2.617994F;
            Sting2.xRot = 1.570796F;
            ClawR1.zRot = 0F + cosxnz1;
            ClawR2.xRot = 0F - cosxnz1;
            ClawR2.zRot = 0F;
            ClawR3.zRot = 0F;
            ClawR4.zRot = 0F;
            ClawL5Bot.xRot = 0F + cosxnz2;
            ClawL5Top.xRot = 0F + cosxnz2;
            ClawL1.zRot = 0F + cosxnz1;
            ClawL2.xRot = 0F - cosxnz1;
            ClawL2.zRot = 0F;
            ClawL3.zRot = 0F;
            ClawL4.zRot = 0F;
            ClawL5Bot.xRot = 0F + cosxnz2;
            ClawL5Top.xRot = 0F + cosxnz2;
        }
        RMand.yRot = -Mth.cos(state.walkAnimationPos * 0.5F) * 0.2F * state.walkAnimationSpeed + 1.570796F;
        LMand.yRot = -Mth.cos(state.walkAnimationPos * 0.5F + (float) Math.PI) * 0.2F * state.walkAnimationSpeed + 1.570796F;
    }
}
