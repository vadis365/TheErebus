package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.JumpingSpiderRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class JumpingSpiderModel extends EntityModel<JumpingSpiderRenderState> {
    public ModelPart root;
    private final ModelPart LFL1, LFL2, LFL3, LFL4, LFL5, LFL6;
    private final ModelPart LMFL1, LMFL2, LMFL3, LMFL4, LMFL5, LMFL6;
    private final ModelPart LMBL1, LMBL2, LMBL3, LMBL4, LMBL5, LMBL6;
    private final ModelPart LBL1, LBL2, LBL3, LBL4, LBL5, LBL6;
    private final ModelPart RFL1, RFL2, RFL3, RFL4, RFL5, RFL6;
    private final ModelPart RMFL1, RMFL2, RMFL3, RMFL4, RMFL5, RMFL6;
    private final ModelPart RMBL1, RMBL2, RMBL3, RMBL4, RMBL5, RMBL6;
    private final ModelPart RBL1, RBL2, RBL3, RBL4, RBL5, RBL6;
    private final ModelPart Ab, AbSide, AbTop, AbBack;
    private final ModelPart HeadToAb, HeadBack, HeadMid, HeadTop, HeadBot;
    private final ModelPart Lmand1, Lmand2, Rmand1, Rmand2, Jaw;
    private final ModelPart LFEye, LMEye, LBEye, RFEye, RMEye, RBEye;

    public JumpingSpiderModel(ModelPart root) {
        super(root);
        this.root = root;
        this.LFL1 = root.getChild("LFL1");
        this.LFL2 = LFL1.getChild("LFL2");
        this.LFL3 = LFL1.getChild("LFL3");
        this.LFL4 = LFL1.getChild("LFL4");
        this.LFL5 = LFL1.getChild("LFL5");
        this.LFL6 = LFL1.getChild("LFL6");
        this.LMFL1 = root.getChild("LMFL1");
        this.LMFL2 = LMFL1.getChild("LMFL2");
        this.LMFL3 = LMFL1.getChild("LMFL3");
        this.LMFL4 = LMFL1.getChild("LMFL4");
        this.LMFL5 = LMFL1.getChild("LMFL5");
        this.LMFL6 = LMFL1.getChild("LMFL6");
        this.LMBL1 = root.getChild("LMBL1");
        this.LMBL2 = LMBL1.getChild("LMBL2");
        this.LMBL3 = LMBL1.getChild("LMBL3");
        this.LMBL4 = LMBL1.getChild("LMBL4");
        this.LMBL5 = LMBL1.getChild("LMBL5");
        this.LMBL6 = LMBL1.getChild("LMBL6");
        this.LBL1 = root.getChild("LBL1");
        this.LBL2 = LBL1.getChild("LBL2");
        this.LBL3 = LBL1.getChild("LBL3");
        this.LBL4 = LBL1.getChild("LBL4");
        this.LBL5 = LBL1.getChild("LBL5");
        this.LBL6 = LBL1.getChild("LBL6");
        this.RFL1 = root.getChild("RFL1");
        this.RFL2 = RFL1.getChild("RFL2");
        this.RFL3 = RFL1.getChild("RFL3");
        this.RFL4 = RFL1.getChild("RFL4");
        this.RFL5 = RFL1.getChild("RFL5");
        this.RFL6 = RFL1.getChild("RFL6");
        this.RMFL1 = root.getChild("RMFL1");
        this.RMFL2 = RMFL1.getChild("RMFL2");
        this.RMFL3 = RMFL1.getChild("RMFL3");
        this.RMFL4 = RMFL1.getChild("RMFL4");
        this.RMFL5 = RMFL1.getChild("RMFL5");
        this.RMFL6 = RMFL1.getChild("RMFL6");
        this.RMBL1 = root.getChild("RMBL1");
        this.RMBL2 = RMBL1.getChild("RMBL2");
        this.RMBL3 = RMBL1.getChild("RMBL3");
        this.RMBL4 = RMBL1.getChild("RMBL4");
        this.RMBL5 = RMBL1.getChild("RMBL5");
        this.RMBL6 = RMBL1.getChild("RMBL6");
        this.RBL1 = root.getChild("RBL1");
        this.RBL2 = RBL1.getChild("RBL2");
        this.RBL3 = RBL1.getChild("RBL3");
        this.RBL4 = RBL1.getChild("RBL4");
        this.RBL5 = RBL1.getChild("RBL5");
        this.RBL6 = RBL1.getChild("RBL6");
        this.Ab = root.getChild("Ab");
        this.AbSide = root.getChild("AbSide");
        this.AbTop = root.getChild("AbTop");
        this.AbBack = root.getChild("AbBack");
        this.HeadToAb = root.getChild("HeadToAb");
        this.HeadBack = root.getChild("HeadBack");
        this.HeadMid = root.getChild("HeadMid");
        this.HeadTop = root.getChild("HeadTop");
        this.HeadBot = root.getChild("HeadBot");
        this.Lmand1 = root.getChild("Lmand1");
        this.Lmand2 = root.getChild("Lmand2");
        this.Rmand1 = root.getChild("Rmand1");
        this.Rmand2 = root.getChild("Rmand2");
        this.Jaw = root.getChild("Jaw");
        this.LFEye = root.getChild("LFEye");
        this.LMEye = root.getChild("LMEye");
        this.LBEye = root.getChild("LBEye");
        this.RFEye = root.getChild("RFEye");
        this.RMEye = root.getChild("RMEye");
        this.RBEye = root.getChild("RBEye");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        float correction = 0.6108652F;

        PartDefinition LFL1 = root.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(0, 93).addBox(-1.0F, -1.0F, -1.0F, 9, 3, 2), PartPose.offsetAndRotation(5.0F, 17.0F, -9.0F, 0.0F, 1.047198F, -0.6108652F));
        LFL1.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(0, 99).addBox(2.5F, -7.7F, -1.0F, 6, 1, 2), PartPose.rotation(0.0F, 0.0F, 0.5235988F + correction + 0.6108652F));
        LFL1.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(0, 103).addBox(3.7F, -6.0F, -1.0F, 6, 1, 2), PartPose.rotation(0.0F, 0.0F, 0.3490659F + correction + 0.6108652F));
        LFL1.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(0, 107).addBox(10.0F, 3.0F, -0.5F, 1, 6, 1), PartPose.rotation(0.0F, 0.0F, -0.4712389F + correction + 0.6108652F));
        LFL1.addOrReplaceChild("LFL5", CubeListBuilder.create().texOffs(0, 115).addBox(9.0F, 3.5F, -0.5F, 1, 6, 1), PartPose.rotation(0.0F, 0.0F, -0.5759587F + correction + 0.6108652F));
        LFL1.addOrReplaceChild("LFL6", CubeListBuilder.create().texOffs(0, 123).addBox(11.0F, 7.5F, -0.5F, 1, 4, 1), PartPose.rotation(0.0F, 0.0F, -0.3490659F + correction + 0.6108652F));

        PartDefinition LMFL1 = root.addOrReplaceChild("LMFL1", CubeListBuilder.create().texOffs(0, 93).addBox(-1.0F, -1.0F, -1.0F, 9, 3, 2), PartPose.offsetAndRotation(5.0F, 17.0F, -6.0F, 0.0F, 0.3839724F, -0.6108652F));
        LMFL1.addOrReplaceChild("LMFL2", CubeListBuilder.create().texOffs(0, 99).addBox(2.5F, -7.7F, -1.0F, 6, 1, 2), PartPose.rotation(0.0F, 0.0F, 0.5235988F + correction + 0.6108652F));
        LMFL1.addOrReplaceChild("LMFL3", CubeListBuilder.create().texOffs(0, 103).addBox(3.7F, -6.0F, -1.0F, 6, 1, 2), PartPose.rotation(0.0F, 0.0F, 0.3490659F + correction + 0.6108652F));
        LMFL1.addOrReplaceChild("LMFL4", CubeListBuilder.create().texOffs(0, 107).addBox(10.0F, 3.0F, -0.5F, 1, 6, 1), PartPose.rotation(0.0F, 0.0F, -0.4712389F + correction + 0.6108652F));
        LMFL1.addOrReplaceChild("LMFL5", CubeListBuilder.create().texOffs(0, 115).addBox(9.0F, 3.5F, -0.5F, 1, 6, 1), PartPose.rotation(0.0F, 0.0F, -0.5759587F + correction + 0.6108652F));
        LMFL1.addOrReplaceChild("LMFL6", CubeListBuilder.create().texOffs(0, 123).addBox(11.0F, 7.5F, -0.5F, 1, 4, 1), PartPose.rotation(0.0F, 0.0F, -0.3490659F + correction + 0.6108652F));

        PartDefinition LMBL1 = root.addOrReplaceChild("LMBL1", CubeListBuilder.create().texOffs(0, 93).addBox(-1.0F, -1.0F, -1.0F, 9, 3, 2), PartPose.offsetAndRotation(5.0F, 17.0F, -3.0F, 0.0F, -0.4363323F, -0.6108652F));
        LMBL1.addOrReplaceChild("LMBL2", CubeListBuilder.create().texOffs(0, 99).addBox(2.5F, -7.7F, -1.0F, 6, 1, 2), PartPose.rotation(0.0F, 0.0F, 0.5235988F + correction + 0.6108652F));
        LMBL1.addOrReplaceChild("LMBL3", CubeListBuilder.create().texOffs(0, 103).addBox(3.7F, -6.0F, -1.0F, 6, 1, 2), PartPose.rotation(0.0F, 0.0F, 0.3490659F + correction + 0.6108652F));
        LMBL1.addOrReplaceChild("LMBL4", CubeListBuilder.create().texOffs(0, 107).addBox(10.0F, 3.0F, -0.5F, 1, 6, 1), PartPose.rotation(0.0F, 0.0F, -0.4712389F + correction + 0.6108652F));
        LMBL1.addOrReplaceChild("LMBL5", CubeListBuilder.create().texOffs(0, 115).addBox(9.0F, 3.5F, -0.5F, 1, 6, 1), PartPose.rotation(0.0F, 0.0F, -0.5759587F + correction + 0.6108652F));
        LMBL1.addOrReplaceChild("LMBL6", CubeListBuilder.create().texOffs(0, 123).addBox(11.0F, 7.5F, -0.5F, 1, 4, 1), PartPose.rotation(0.0F, 0.0F, -0.3490659F + correction + 0.6108652F));

        PartDefinition LBL1 = root.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(0, 93).addBox(-1.0F, -1.0F, -1.0F, 9, 3, 2), PartPose.offsetAndRotation(5.0F, 17.0F, 0.0F, 0.0F, -1.047198F, -0.6108652F));
        LBL1.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(0, 99).addBox(2.5F, -7.7F, -1.0F, 6, 1, 2), PartPose.rotation(0.0F, 0.0F, 0.5235988F + correction + 0.6108652F));
        LBL1.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(0, 103).addBox(3.7F, -6.0F, -1.0F, 6, 1, 2), PartPose.rotation(0.0F, 0.0F, 0.3490659F + correction + 0.6108652F));
        LBL1.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(0, 107).addBox(10.0F, 3.0F, -0.5F, 1, 6, 1), PartPose.rotation(0.0F, 0.0F, -0.4712389F + correction + 0.6108652F));
        LBL1.addOrReplaceChild("LBL5", CubeListBuilder.create().texOffs(0, 115).addBox(9.0F, 3.5F, -0.5F, 1, 6, 1), PartPose.rotation(0.0F, 0.0F, -0.5759587F + correction + 0.6108652F));
        LBL1.addOrReplaceChild("LBL6", CubeListBuilder.create().texOffs(0, 123).addBox(11.0F, 7.5F, -0.5F, 1, 4, 1), PartPose.rotation(0.0F, 0.0F, -0.3490659F + correction + 0.6108652F));

        PartDefinition RFL1 = root.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(0, 93).addBox(-1.0F, -1.0F, -1.0F, 9, 3, 2), PartPose.offsetAndRotation(-5.0F, 17.0F, -9.0F, 0.0F, 2.094395F, -0.6108652F));
        RFL1.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(0, 99).addBox(2.5F, -7.7F, -1.0F, 6, 1, 2), PartPose.rotation(0.0F, 0.0F, 0.5235988F + correction + 0.6108652F));
        RFL1.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(0, 99).addBox(3.7F, -6.0F, -1.0F, 6, 1, 2), PartPose.rotation(0.0F, 0.0F, 0.3490659F + correction + 0.6108652F));
        RFL1.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(0, 107).addBox(10.0F, 3.0F, -0.5F, 1, 6, 1), PartPose.rotation(0.0F, 0.0F, -0.4712389F + correction + 0.6108652F));
        RFL1.addOrReplaceChild("RFL5", CubeListBuilder.create().texOffs(0, 115).addBox(9.0F, 3.5F, -0.5F, 1, 6, 1), PartPose.rotation(0.0F, 0.0F, -0.5759587F + correction + 0.6108652F));
        RFL1.addOrReplaceChild("RFL6", CubeListBuilder.create().texOffs(0, 123).addBox(11.0F, 7.5F, -0.5F, 1, 4, 1), PartPose.rotation(0.0F, 0.0F, -0.3490659F + correction + 0.6108652F));

        PartDefinition RMFL1 = root.addOrReplaceChild("RMFL1", CubeListBuilder.create().texOffs(0, 93).addBox(-1.0F, -1.0F, -1.0F, 9, 3, 2), PartPose.offsetAndRotation(-5.0F, 17.0F, -6.0F, 0.0F, 2.879793F, -0.6108652F));
        RMFL1.addOrReplaceChild("RMFL2", CubeListBuilder.create().texOffs(0, 99).addBox(2.5F, -7.7F, -1.0F, 6, 1, 2), PartPose.rotation(0.0F, 0.0F, 0.5235988F + correction + 0.6108652F));
        RMFL1.addOrReplaceChild("RMFL3", CubeListBuilder.create().texOffs(0, 103).addBox(3.7F, -6.0F, -1.0F, 6, 1, 2), PartPose.rotation(0.0F, 0.0F, 0.3490659F + correction + 0.6108652F));
        RMFL1.addOrReplaceChild("RMFL4", CubeListBuilder.create().texOffs(0, 107).addBox(10.0F, 3.0F, -0.5F, 1, 6, 1), PartPose.rotation(0.0F, 0.0F, -0.4712389F + correction + 0.6108652F));
        RMFL1.addOrReplaceChild("RMFL5", CubeListBuilder.create().texOffs(0, 115).addBox(9.0F, 3.5F, -0.5F, 1, 6, 1), PartPose.rotation(0.0F, 0.0F, -0.5759587F + correction + 0.6108652F));
        RMFL1.addOrReplaceChild("RMFL6", CubeListBuilder.create().texOffs(0, 123).addBox(11.0F, 7.5F, -0.5F, 1, 4, 1), PartPose.rotation(0.0F, 0.0F, -0.3490659F + correction + 0.6108652F));

        PartDefinition RMBL1 = root.addOrReplaceChild("RMBL1", CubeListBuilder.create().texOffs(0, 93).addBox(-1.0F, -1.0F, -1.0F, 9, 3, 2), PartPose.offsetAndRotation(-5.0F, 17.0F, -3.0F, 0.0F, -2.879793F, -0.6108652F));
        RMBL1.addOrReplaceChild("RMBL2", CubeListBuilder.create().texOffs(0, 99).addBox(2.5F, -7.7F, -1.0F, 6, 1, 2), PartPose.rotation(0.0F, 0.0F, 0.5235988F + correction + 0.6108652F));
        RMBL1.addOrReplaceChild("RMBL3", CubeListBuilder.create().texOffs(0, 103).addBox(3.7F, -6.0F, -1.0F, 6, 1, 2), PartPose.rotation(0.0F, 0.0F, 0.3490659F + correction + 0.6108652F));
        RMBL1.addOrReplaceChild("RMBL4", CubeListBuilder.create().texOffs(0, 107).addBox(10.0F, 3.0F, -0.5F, 1, 6, 1), PartPose.rotation(0.0F, 0.0F, -0.4712389F + correction + 0.6108652F));
        RMBL1.addOrReplaceChild("RMBL5", CubeListBuilder.create().texOffs(0, 115).addBox(9.0F, 3.5F, -0.5F, 1, 6, 1), PartPose.rotation(0.0F, 0.0F, -0.5759587F + correction + 0.6108652F));
        RMBL1.addOrReplaceChild("RMBL6", CubeListBuilder.create().texOffs(0, 123).addBox(11.0F, 7.5F, -0.5F, 1, 4, 1), PartPose.rotation(0.0F, 0.0F, -0.3490659F + correction + 0.6108652F));

        PartDefinition RBL1 = root.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(0, 93).addBox(-1.0F, -1.0F, -1.0F, 9, 3, 2), PartPose.offsetAndRotation(-5.0F, 17.0F, 0.0F, 0.0F, -2.094395F, -0.6108652F));
        RBL1.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 99).addBox(2.5F, -7.7F, -1.0F, 6, 1, 2), PartPose.rotation(0.0F, 0.0F, 0.5235988F + correction + 0.6108652F));
        RBL1.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(0, 103).addBox(3.7F, -6.0F, -1.0F, 6, 1, 2), PartPose.rotation(0.0F, 0.0F, 0.3490659F + correction + 0.6108652F));
        RBL1.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(0, 107).addBox(10.0F, 3.0F, -0.5F, 1, 6, 1), PartPose.rotation(0.0F, 0.0F, -0.4712389F + correction + 0.6108652F));
        RBL1.addOrReplaceChild("RBL5", CubeListBuilder.create().texOffs(0, 115).addBox(9.0F, 3.5F, -0.5F, 1, 6, 1), PartPose.rotation(0.0F, 0.0F, -0.5759587F + correction + 0.6108652F));
        RBL1.addOrReplaceChild("RBL6", CubeListBuilder.create().texOffs(0, 123).addBox(11.0F, 7.5F, -0.5F, 1, 4, 1), PartPose.rotation(0.0F, 0.0F, -0.3490659F + correction + 0.6108652F));

        root.addOrReplaceChild("Ab", CubeListBuilder.create().texOffs(10, 51).addBox(-5.0F, -5.5F, -1.0F, 10, 9, 13), PartPose.offsetAndRotation(0.0F, 17.0F, 3.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("AbSide", CubeListBuilder.create().texOffs(12, 74).addBox(-6.5F, -4.0F, 2.0F, 13, 7, 8), PartPose.offsetAndRotation(0.0F, 17.0F, 3.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("AbTop", CubeListBuilder.create().texOffs(23, 90).addBox(-4.0F, -6.5F, 2.0F, 8, 11, 8), PartPose.offsetAndRotation(0.0F, 17.0F, 3.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("AbBack", CubeListBuilder.create().texOffs(30, 110).addBox(-4.5F, -3.0F, 12.0F, 9, 6, 2), PartPose.offsetAndRotation(0.0F, 17.0F, 3.0F, -0.1745329F, 0.0F, 0.0F));

        root.addOrReplaceChild("HeadToAb", CubeListBuilder.create().texOffs(35, 41).addBox(-3.5F, -5.75F, -3.0F, 7, 6, 3), PartPose.offset(0.0F, 18.0F, 3.0F));
        root.addOrReplaceChild("HeadBack", CubeListBuilder.create().texOffs(12, 41).addBox(-4.0F, -0.5F, 5.5F, 8, 6, 3), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, 0.7853982F, 0.0F, 0.0F));
        root.addOrReplaceChild("HeadMid", CubeListBuilder.create().texOffs(14, 11).addBox(-5.0F, -4.5F, -2.0F, 10, 3, 10), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("HeadTop", CubeListBuilder.create().texOffs(16, 0).addBox(-5.5F, -7.5F, -2.0F, 11, 3, 7), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("HeadBot", CubeListBuilder.create().texOffs(12, 25).addBox(-6.0F, -2.5F, -3.5F, 12, 5, 10), PartPose.offset(0.0F, 17.0F, -6.0F));

        root.addOrReplaceChild("Lmand1", CubeListBuilder.create().texOffs(0, 0).addBox(1.5F, -1.0F, 1.0F, 1, 5, 2), PartPose.offsetAndRotation(1.0F, 17.0F, -13.0F, 0.7853982F, 0.0F, 0.0F));
        root.addOrReplaceChild("Lmand2", CubeListBuilder.create().texOffs(7, 0).addBox(1.0F, -1.0F, -0.5F, 2, 5, 2), PartPose.offset(1.0F, 16.0F, -13.0F));
        root.addOrReplaceChild("Rmand1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -1.0F, 1.0F, 1, 5, 2), PartPose.offsetAndRotation(1.0F, 17.0F, -13.0F, 0.7853982F, 0.0F, 0.0F));
        root.addOrReplaceChild("Rmand2", CubeListBuilder.create().texOffs(7, 0).addBox(-5.0F, -2.0F, -0.5F, 2, 5, 2), PartPose.offset(1.0F, 17.0F, -13.0F));
        root.addOrReplaceChild("Jaw", CubeListBuilder.create().texOffs(0, 8).addBox(-1.5F, 0.0F, -4.0F, 3, 4, 2), PartPose.offsetAndRotation(0.0F, 17.0F, -6.0F, -0.6283185F, 0.0F, 0.0F));

        root.addOrReplaceChild("LFEye", CubeListBuilder.create().texOffs(0, 15).addBox(0.5F, -6.5F, -3.0F, 3, 3, 1), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("LMEye", CubeListBuilder.create().texOffs(0, 20).addBox(4.0F, -7.0F, -2.5F, 2, 2, 2), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("LBEye", CubeListBuilder.create().texOffs(0, 25).addBox(5.0F, -7.0F, 0.5F, 1, 1, 1), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("RFEye", CubeListBuilder.create().texOffs(0, 15).addBox(-3.5F, -6.5F, -3.0F, 3, 3, 1), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("RMEye", CubeListBuilder.create().texOffs(0, 20).addBox(-6.0F, -7.0F, -2.5F, 2, 2, 2), PartPose.offsetAndRotation(0.0F, 17.0F, -7.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("RBEye", CubeListBuilder.create().texOffs(0, 25).addBox(-6.0F, -8.0F, 0.5F, 1, 1, 1), PartPose.offsetAndRotation(0.0F, 18.0F, -7.0F, -0.1745329F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 128);
    }
}
