package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.AntlionBossRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class AntlionBossModel extends EntityModel<AntlionBossRenderState> {

    public ModelPart root;
    private final ModelPart MandR1;
    private final ModelPart MandR2;
    private final ModelPart MandR3;
    private final ModelPart MandL1;
    private final ModelPart MandL2;
    private final ModelPart MandL3;
    private final ModelPart Head;
    private final ModelPart Neck1;
    private final ModelPart Neck2;
    private final ModelPart HR2;
    private final ModelPart HL2;
    private final ModelPart ThxR2;
    private final ModelPart ThxL2;
    private final ModelPart ThxR3;
    private final ModelPart ThxL3;
    private final ModelPart ThxR4;
    private final ModelPart ThxL4;
    private final ModelPart ThxR5;
    private final ModelPart ThxL5;
    private final ModelPart ThxR6;
    private final ModelPart ThxL6;
    private final ModelPart ThxR7;
    private final ModelPart ThxL7;
    private final ModelPart ThxR9;
    private final ModelPart ThxL9;
    private final ModelPart ThxR10;
    private final ModelPart ThxL10;
    private final ModelPart Thx11;
    private final ModelPart Thx12;
    private final ModelPart RF1;
    private final ModelPart RF2;
    private final ModelPart RF3;
    private final ModelPart RM1;
    private final ModelPart RM2;
    private final ModelPart RM3;
    private final ModelPart RB1;
    private final ModelPart RB2;
    private final ModelPart RB3;
    private final ModelPart LF1;
    private final ModelPart LF2;
    private final ModelPart LF3;
    private final ModelPart LM1;
    private final ModelPart LM2;
    private final ModelPart LM3;
    private final ModelPart LB1;
    private final ModelPart LB2;
    private final ModelPart LB3;
    private float addHead = 0;

    public AntlionBossModel(ModelPart root) {
        super(root);
        this.root = root;
        this.Head = root.getChild("Head");
        this.MandR1 = Head.getChild("MandR1");
        this.MandR2 = Head.getChild("MandR2");
        this.MandR3 = Head.getChild("MandR3");
        this.MandL1 = Head.getChild("MandL1");
        this.MandL2 = Head.getChild("MandL2");
        this.MandL3 = Head.getChild("MandL3");
        this.Neck1 = root.getChild("Neck1");
        this.Neck2 = root.getChild("Neck2");
        this.HR2 = root.getChild("HR2");
        this.HL2 = root.getChild("HL2");
        this.ThxR2 = root.getChild("ThxR2");
        this.ThxL2 = root.getChild("ThxL2");
        this.ThxR3 = root.getChild("ThxR3");
        this.ThxL3 = root.getChild("ThxL3");
        this.ThxR4 = root.getChild("ThxR4");
        this.ThxL4 = root.getChild("ThxL4");
        this.ThxR5 = root.getChild("ThxR5");
        this.ThxL5 = root.getChild("ThxL5");
        this.ThxR6 = root.getChild("ThxR6");
        this.ThxL6 = root.getChild("ThxL6");
        this.ThxR7 = root.getChild("ThxR7");
        this.ThxL7 = root.getChild("ThxL7");
        this.ThxR9 = root.getChild("ThxR9");
        this.ThxL9 = root.getChild("ThxL9");
        this.ThxR10 = root.getChild("ThxR10");
        this.ThxL10 = root.getChild("ThxL10");
        this.Thx11 = root.getChild("Thx11");
        this.Thx12 = root.getChild("Thx12");
        this.RF1 = root.getChild("RF1");
        this.RF2 = RF1.getChild("RF2");
        this.RF3 = RF1.getChild("RF3");
        this.RM1 = root.getChild("RM1");
        this.RM2 = RM1.getChild("RM2");
        this.RM3 = RM1.getChild("RM3");
        this.RB1 = root.getChild("RB1");
        this.RB2 = RB1.getChild("RB2");
        this.RB3 = RB1.getChild("RB3");
        this.LF1 = root.getChild("LF1");
        this.LF2 = LF1.getChild("LF2");
        this.LF3 = LF1.getChild("LF3");
        this.LM1 = root.getChild("LM1");
        this.LM2 = LM1.getChild("LM2");
        this.LM3 = LM1.getChild("LM3");
        this.LB1 = root.getChild("LB1");
        this.LB2 = LB1.getChild("LB2");
        this.LB3 = LB1.getChild("LB3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        PartDefinition Head = part.addOrReplaceChild("Head", CubeListBuilder.create()
                .texOffs(88, 64)
                .addBox(-4.5F, 3F, -13.5F, 9, 5, 8, CubeDeformation.NONE), PartPose.offset(0, 16, -15.5F));

        Head.addOrReplaceChild("MandR1", CubeListBuilder.create()
                .texOffs(110, 54)
                .addBox(-0.5F, 4F, -17F, 3, 4, 5, CubeDeformation.NONE), PartPose.rotation(0F, 0.3490659F, 0F));

        Head.addOrReplaceChild("MandR2", CubeListBuilder.create()
                .texOffs(108, 43)
                .addBox(-0.5F, 4.5F, -24F, 2, 3, 7, CubeDeformation.NONE), PartPose.rotation(0F, 0.3490659F, 0F));

        Head.addOrReplaceChild("MandR3", CubeListBuilder.create()
                .texOffs(114, 35)
                .addBox(-15.5F, 5F, -23F, 1, 2, 5, CubeDeformation.NONE), PartPose.rotation(0F, -0.3490659F, 0F));

        Head.addOrReplaceChild("MandL1", CubeListBuilder.create()
                .texOffs(84, 54)
                .addBox(-2.5F, 4F, -17F, 3, 4, 5, CubeDeformation.NONE), PartPose.rotation(0F, -0.3490659F, 0F));

        Head.addOrReplaceChild("MandL2", CubeListBuilder.create()
                .texOffs(83, 43)
                .addBox(-1.5F, 4.5F, -24F, 2, 3, 7, CubeDeformation.NONE), PartPose.rotation(0F, -0.3490659F, 0F));

        Head.addOrReplaceChild("MandL3", CubeListBuilder.create()
                .texOffs(83, 35)
                .addBox(14.5F, 5F, -23F, 1, 2, 5, CubeDeformation.NONE), PartPose.rotation(0F, 0.3490659F, 0F));

        PartDefinition Neck1 = part.addOrReplaceChild("Neck1", CubeListBuilder.create()
                .texOffs(93, 78)
                .addBox(-3.5F, -2F, -8.5F, 7, 4, 5, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 16F, -15.5F, 0.6981317F, 0F, 0F));

        PartDefinition Neck2 = part.addOrReplaceChild("Neck2", CubeListBuilder.create()
                .texOffs(88, 88)
                .addBox(-5.5F, -2F, -4F, 11, 5, 6, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 16F, -15.5F, 0.5235988F, 0F, 0F));

        PartDefinition HR2 = part.addOrReplaceChild("HR2", CubeListBuilder.create()
                .texOffs(100, 100)
                .addBox(-0.4F, -2F, 0F, 8, 7, 5, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 13F, -10.5F, -0.1745329F, -3.141593F, -0.1745329F));

        PartDefinition HL2 = part.addOrReplaceChild("HL2", CubeListBuilder.create()
                .texOffs(73, 100)
                .addBox(-7.6F, -2F, 0F, 8, 7, 5, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 13F, -10.5F, -0.1745329F, 3.141593F, 0.1745329F));

        PartDefinition ThxR2 = part.addOrReplaceChild("ThxR2", CubeListBuilder.create()
                .texOffs(40, 0)
                .addBox(-10.1F, -5F, 0F, 11, 9, 5, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 14F, -11F, 0F, 0F, -0.1745329F));

        PartDefinition ThxL2 = part.addOrReplaceChild("ThxL2", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-0.9F, -5F, 0F, 11, 9, 5, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 14F, -11F, 0F, 0F, 0.1745329F));

        PartDefinition ThxR3 = part.addOrReplaceChild("ThxR3", CubeListBuilder.create()
                .texOffs(40, 15)
                .addBox(-11F, -5.5F, 0F, 12, 11, 4, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 13F, -6F, 0F, 0F, -0.1745329F));

        PartDefinition ThxL3 = part.addOrReplaceChild("ThxL3", CubeListBuilder.create()
                .texOffs(0, 15)
                .addBox(-1F, -5.5F, -6F, 12, 11, 4, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 13F, 0F, 0F, 0F, 0.1745329F));

        PartDefinition ThxR4 = part.addOrReplaceChild("ThxR4", CubeListBuilder.create()
                .texOffs(40, 31)
                .addBox(-11.9F, -6F, 0F, 13, 12, 5, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 13F, -2F, 0F, 0F, -0.1745329F));

        PartDefinition ThxL4 = part.addOrReplaceChild("ThxL4", CubeListBuilder.create()
                .texOffs(0, 31)
                .addBox(-1.1F, -6F, 0F, 13, 12, 5, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 13F, -2F, 0F, 0F, 0.1745329F));

        PartDefinition ThxR5 = part.addOrReplaceChild("ThxR5", CubeListBuilder.create()
                .texOffs(44, 49)
                .addBox(-11.8F, -6.5F, 0F, 13, 12, 5, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 13F, 3F, 0F, 0F, -0.1745329F));

        PartDefinition ThxL5 = part.addOrReplaceChild("ThxL5", CubeListBuilder.create()
                .texOffs(0, 49)
                .addBox(-1.2F, -6.5F, 0F, 13, 12, 5, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 13F, 3F, 0F, 0F, 0.1745329F));

        PartDefinition ThxR6 = part.addOrReplaceChild("ThxR6", CubeListBuilder.create()
                .texOffs(44, 67)
                .addBox(-11.9F, -6F, 0F, 13, 10, 6, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 13F, 8F, 0F, 0F, -0.1745329F));

        PartDefinition ThxL6 = part.addOrReplaceChild("ThxL6", CubeListBuilder.create()
                .texOffs(0, 67)
                .addBox(-1.1F, -6F, 0F, 13, 10, 6, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 13F, 8F, 0F, 0F, 0.1745329F));

        PartDefinition ThxR7 = part.addOrReplaceChild("ThxR7", CubeListBuilder.create()
                .texOffs(44, 84)
                .addBox(-11F, -5.5F, 0F, 12, 9, 5, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 14F, 14F, 0F, 0F, -0.1745329F));

        PartDefinition ThxL7 = part.addOrReplaceChild("ThxL7", CubeListBuilder.create()
                .texOffs(0, 84)
                .addBox(-1F, -5.5F, 0F, 12, 9, 5, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 14F, 14F, 0F, 0F, 0.1745329F));

        PartDefinition ThxR9 = part.addOrReplaceChild("ThxR9", CubeListBuilder.create()
                .texOffs(44, 99)
                .addBox(-9.4F, -3.5F, 0F, 10, 8, 3, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 14F, 18.5F, -0.1745329F, 0F, -0.1745329F));

        PartDefinition ThxL9 = part.addOrReplaceChild("ThxL9", CubeListBuilder.create()
                .texOffs(0, 99)
                .addBox(-0.6F, -3.5F, 0F, 10, 8, 3, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 14F, 18.5F, -0.1745329F, 0F, 0.1745329F));

        PartDefinition ThxR10 = part.addOrReplaceChild("ThxR10", CubeListBuilder.create()
                .texOffs(44, 111)
                .addBox(-7.6F, -2F, 0F, 8, 7, 2, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 14F, 21.5F, -0.1745329F, 0F, -0.1745329F));

        PartDefinition ThxL10 = part.addOrReplaceChild("ThxL10", CubeListBuilder.create()
                .texOffs(0, 111)
                .addBox(-0.4F, -2F, 0F, 8, 7, 2, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 14F, 21.5F, -0.1745329F, 0F, 0.1745329F));

        PartDefinition Thx11 = part.addOrReplaceChild("Thx11", CubeListBuilder.create()
                .texOffs(0, 121)
                .addBox(-6.5F, 0F, 0F, 13, 5, 2, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 15F, 23.5F, -0.1745329F, 0F, 0F));

        PartDefinition Thx12 = part.addOrReplaceChild("Thx12", CubeListBuilder.create()
                .texOffs(40, 122)
                .addBox(-4.5F, 2F, 0F, 9, 3, 2, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 15F, 25.5F, -0.1745329F, 0F, 0F));

        PartDefinition RF1 = part.addOrReplaceChild("RF1", CubeListBuilder.create()
                .texOffs(82, 0)
                .addBox(0F, -1F, -1F, 5, 2, 2, CubeDeformation.NONE), PartPose.offsetAndRotation(-6F, 19F, -13F, -0.8726646F, 2.617994F, -0.8726646F));

        RF1.addOrReplaceChild("RF2", CubeListBuilder.create()
                .texOffs(82, 10)
                .addBox(1.5F, 2.7F, -0.5F, 5, 2, 1, CubeDeformation.NONE), PartPose.rotation(0F, 0F, -1.134464F));

        RF1.addOrReplaceChild("RF3", CubeListBuilder.create()
                .texOffs(82, 14)
                .addBox(6F, -2.5F, -0.5F, 5, 1, 1, CubeDeformation.NONE), PartPose.rotation(0F, 0F, -0.2617994F));

        PartDefinition RM1 = part.addOrReplaceChild("RM1", CubeListBuilder.create()
                .texOffs(82, 5)
                .addBox(0F, -1F, -1F, 7, 2, 2, CubeDeformation.NONE), PartPose.offsetAndRotation(-9F, 18F, -8.5F, 0F, 3.141593F, -0.6981317F));

        RM1.addOrReplaceChild("RM2", CubeListBuilder.create()
                .texOffs(82, 10)
                .addBox(3.5F, 4F, -0.5F, 5, 2, 1, CubeDeformation.NONE), PartPose.rotation(0F, 0F, -0.8726646F));

        RM1.addOrReplaceChild("RM3", CubeListBuilder.create()
                .texOffs(82, 14)
                .addBox(9F, -2F, -0.5F, 5, 1, 1, CubeDeformation.NONE), PartPose.rotation(0F, 0F, -0.1745329F));

        PartDefinition RB1 = part.addOrReplaceChild("RB1", CubeListBuilder.create()
                .texOffs(82, 0)
                .addBox(0F, -1F, -1F, 5, 2, 2, CubeDeformation.NONE), PartPose.offsetAndRotation(-9F, 19F, -1.5F, 0.7504916F, -2.4434610F, -0.7504916F));

        RB1.addOrReplaceChild("RB2", CubeListBuilder.create()
                .texOffs(82, 10)
                .addBox(2.5F, 2.5F, -0.5F, 5, 2, 1, CubeDeformation.NONE), PartPose.rotation(0F, 0F, -0.9250245F));

        RB1.addOrReplaceChild("RB3", CubeListBuilder.create()
                .texOffs(82, 14)
                .addBox(7F, -2.5F, -0.5F, 5, 1, 1, CubeDeformation.NONE), PartPose.rotation(0F, 0F, -0.2268928F));

        PartDefinition LF1 = part.addOrReplaceChild("LF1", CubeListBuilder.create()
                .texOffs(82, 0)
                .addBox(0F, -1F, -1F, 5, 2, 2, CubeDeformation.NONE), PartPose.offsetAndRotation(6F, 19F, -13F, 0.5235988F, 0.5235988F, 0.8726646F));

        LF1.addOrReplaceChild("LF2", CubeListBuilder.create()
                .texOffs(82, 10)
                .addBox(1.5F, 2.7F, -0.5F, 5, 2, 1, CubeDeformation.NONE), PartPose.rotation(0F, 0F, -1.134464F));

        LF1.addOrReplaceChild("LF3", CubeListBuilder.create()
                .texOffs(82, 14)
                .addBox(6F, -2.5F, -0.5F, 5, 1, 1, CubeDeformation.NONE), PartPose.rotation(0F, 0F, -0.2617994F));

        PartDefinition LM1 = part.addOrReplaceChild("LM1", CubeListBuilder.create()
                .texOffs(82, 5)
                .addBox(0F, -1F, -1F, 7, 2, 2, CubeDeformation.NONE), PartPose.offsetAndRotation(9F, 18F, -8.5F, 0F, 0F, 0.6981317F));

        LM1.addOrReplaceChild("LM2", CubeListBuilder.create()
                .texOffs(82, 10)
                .addBox(3.5F, 4F, -0.5F, 5, 2, 1, CubeDeformation.NONE), PartPose.rotation(0F, 0F, -0.8726646F));

        LM1.addOrReplaceChild("LM3", CubeListBuilder.create()
                .texOffs(82, 14)
                .addBox(9F, -2F, -0.5F, 5, 1, 1, CubeDeformation.NONE), PartPose.rotation(0F, 0F, -0.1745329F));

        PartDefinition LB1 = part.addOrReplaceChild("LB1", CubeListBuilder.create()
                .texOffs(82, 0)
                .addBox(0F, -1F, -1F, 5, 2, 2, CubeDeformation.NONE), PartPose.offsetAndRotation(9F, 19F, -1.5F, -0.7504916F, -0.6981317F, 0.7504916F));

        LB1.addOrReplaceChild("LB2", CubeListBuilder.create()
                .texOffs(82, 10)
                .addBox(2.5F, 2.5F, -0.5F, 5, 2, 1, CubeDeformation.NONE), PartPose.rotation(0F, 0F, -0.9250245F));

        LB1.addOrReplaceChild("LB3", CubeListBuilder.create()
                .texOffs(82, 14)
                .addBox(7F, -2.5F, -0.5F, 5, 1, 1, CubeDeformation.NONE), PartPose.rotation(0F, 0F, -0.2268928F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(AntlionBossRenderState state) {
        super.setupAnim(state);
        float cos1 = Mth.cos(state.walkAnimationPos + Mth.PI) * 0.5F * state.walkAnimationSpeed;
        float cos2 = Mth.cos(state.walkAnimationPos) * 0.5F * state.walkAnimationSpeed;
        float headY = state.yRot / (180F / (float) Math.PI);
        float headX = state.xRot / (180F / (float) Math.PI);

        if(state.blam == 2) {
            for(addHead = 0; addHead < 17; addHead++) {
                headX = headX - addHead * 0.0053702F;
            }
        }

        Head.yRot = headY;
        Neck1.yRot = headY;
        Head.xRot = headX;
        Neck1.xRot = headX + 0.6981317F;
        MandR1.yRot = -Mth.cos(state.walkAnimationPos * 0.75F) * 0.2F * state.walkAnimationSpeed + 0.35F;
        MandR2.yRot = -Mth.cos(state.walkAnimationPos * 0.75F) * 0.2F * state.walkAnimationSpeed + 0.35F;
        MandR3.yRot = -Mth.cos(state.walkAnimationPos * 0.75F) * 0.2F * state.walkAnimationSpeed - 0.35F;
        MandL1.yRot = -Mth.cos(state.walkAnimationPos * 0.75F + Mth.PI) * 0.2F * state.walkAnimationSpeed - 0.35F;
        MandL2.yRot = -Mth.cos(state.walkAnimationPos * 0.75F + Mth.PI) * 0.2F * state.walkAnimationSpeed - 0.35F;
        MandL3.yRot = -Mth.cos(state.walkAnimationPos * 0.75F + Mth.PI) * 0.2F * state.walkAnimationSpeed + 0.35F;
        LB1.xRot = cos1;
        LM1.xRot = cos2;
        LF1.xRot = cos1;
        RB1.xRot = -cos2;
        RM1.xRot = -cos1;
        RF1.xRot = -cos2;
        LB1.yRot = cos1 - 0.6981317F;
        LM1.yRot = cos2;
        LF1.yRot = cos1 + 0.5235988F;
        RB1.yRot = -cos2 + 3.8397247F;
        RM1.yRot = -cos1 + 3.141593F;
        RF1.yRot = -cos2 + 2.6179942F;
    }
}
