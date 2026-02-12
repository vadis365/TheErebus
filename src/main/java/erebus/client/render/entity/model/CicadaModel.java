package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.CIcadaRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class CicadaModel extends EntityModel<CIcadaRenderState> {

    public ModelPart root;
    private final ModelPart LEye;
    private final ModelPart REye;
    private final ModelPart HeadL;
    private final ModelPart HeadR;
    private final ModelPart HeadFront;
    private final ModelPart HeadMain;
    private final ModelPart HeadTop;
    private final ModelPart HeadBack;
    private final ModelPart ThoraxTop;
    private final ModelPart AbMain;
    private final ModelPart AbBack1;
    private final ModelPart AbBack2;
    private final ModelPart LBLeg1;
    private final ModelPart LBLeg2;
    private final ModelPart LBLeg3;
    private final ModelPart LBLeg4;
    private final ModelPart RBLeg1;
    private final ModelPart RBLeg2;
    private final ModelPart RBLeg3;
    private final ModelPart RBLeg4;
    private final ModelPart LFLeg1;
    private final ModelPart LFLeg2;
    private final ModelPart LFLeg3;
    private final ModelPart LFLeg4;
    private final ModelPart RFLeg1;
    private final ModelPart RFLeg2;
    private final ModelPart RFLeg3;
    private final ModelPart RFLeg4;
    private final ModelPart LMLeg1;
    private final ModelPart LMLeg2;
    private final ModelPart LMLeg3;
    private final ModelPart LMLeg4;
    private final ModelPart RMLeg1;
    private final ModelPart RMLeg2;
    private final ModelPart RMLeg3;
    private final ModelPart RMLeg4;
    private final ModelPart RWingFront;
    private final ModelPart RWingTop;
    private final ModelPart RWingMain;
    private final ModelPart RWingBack;
    private final ModelPart LWingFront;
    private final ModelPart LWingTop;
    private final ModelPart LWingMain;
    private final ModelPart LWingBack;

    protected CicadaModel(ModelPart root) {
        super(root);
        this.root = root;
        this.LEye = root.getChild("LEye");
        this.REye = root.getChild("REye");
        this.HeadL = root.getChild("HeadL");
        this.HeadR = root.getChild("HeadR");
        this.HeadFront = root.getChild("HeadFront");
        this.HeadMain = root.getChild("HeadMain");
        this.HeadTop = root.getChild("HeadTop");
        this.HeadBack = root.getChild("HeadBack");
        this.ThoraxTop = root.getChild("ThoraxTop");
        this.AbMain = root.getChild("AbMain");
        this.AbBack1 = root.getChild("AbBack1");
        this.AbBack2 = root.getChild("AbBack2");
        this.LBLeg1 = root.getChild("LBLeg1");
        this.LBLeg2 = root.getChild("LBLeg2");
        this.LBLeg3 = root.getChild("LBLeg3");
        this.LBLeg4 = root.getChild("LBLeg4");
        this.RBLeg1 = root.getChild("RBLeg1");
        this.RBLeg2 = root.getChild("RBLeg2");
        this.RBLeg3 = root.getChild("RBLeg3");
        this.RBLeg4 = root.getChild("RBLeg4");
        this.LFLeg1 = root.getChild("LFLeg1");
        this.LFLeg2 = root.getChild("LFLeg2");
        this.LFLeg3 = root.getChild("LFLeg3");
        this.LFLeg4 = root.getChild("LFLeg4");
        this.RFLeg1 = root.getChild("RFLeg1");
        this.RFLeg2 = root.getChild("RFLeg2");
        this.RFLeg3 = root.getChild("RFLeg3");
        this.RFLeg4 = root.getChild("RFLeg4");
        this.LMLeg1 = root.getChild("LMLeg1");
        this.LMLeg2 = root.getChild("LMLeg2");
        this.LMLeg3 = root.getChild("LMLeg3");
        this.LMLeg4 = root.getChild("LMLeg4");
        this.RMLeg1 = root.getChild("RMLeg1");
        this.RMLeg2 = root.getChild("RMLeg2");
        this.RMLeg3 = root.getChild("RMLeg3");
        this.RMLeg4 = root.getChild("RMLeg4");
        this.RWingFront = root.getChild("RWingFront");
        this.RWingTop = root.getChild("RWingTop");
        this.RWingMain = root.getChild("RWingMain");
        this.RWingBack = root.getChild("RWingBack");
        this.LWingFront = root.getChild("LWingFront");
        this.LWingTop = root.getChild("LWingTop");
        this.LWingMain = root.getChild("LWingMain");
        this.LWingBack = root.getChild("LWingBack");
    }



    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("LEye", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(4.0F, -3.5F, -6.5F, 2, 3, 3, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 19.0F, -7.0F, 0.0F, 0.0F, -0.2617994F));

        part.addOrReplaceChild("REye", CubeListBuilder.create()
                .texOffs(54, 0)
                .addBox(-6.0F, -3.5F, -6.5F, 2, 3, 3, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 19.0F, -7.0F, 0.0F, 0.0F, 0.2617994F));

        part.addOrReplaceChild("HeadL", CubeListBuilder.create()
                .texOffs(11, 0)
                .addBox(2.0F, -3.5F, -7.0F, 2, 4, 4, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 19.0F, -7.0F, 0.0F, 0.0F, -0.2617994F));

        part.addOrReplaceChild("HeadR", CubeListBuilder.create()
                .texOffs(41, 0)
                .addBox(-4.0F, -3.5F, -7.0F, 2, 4, 4, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 19.0F, -7.0F, 0.0F, 0.0F, 0.2617994F));

        part.addOrReplaceChild("HeadFront", CubeListBuilder.create()
                .texOffs(24, 0)
                .addBox(-6.0F, -4.0F, -6.0F, 4, 3, 4, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 19.0F, -7.0F, 0.2617994F, -0.7853982F, -0.2150F));

        part.addOrReplaceChild("HeadMain", CubeListBuilder.create()
                .texOffs(23, 9)
                .addBox(-3.0F, -4.0F, -7.0F, 6, 5, 3, CubeDeformation.NONE), PartPose.offset(0.0F, 19.0F, -7.0F));

        part.addOrReplaceChild("HeadTop", CubeListBuilder.create()
                .texOffs(23, 18)
                .addBox(-3.5F, -5.0F, -3.0F, 7, 1, 3, CubeDeformation.NONE), PartPose.offset(0.0F, 19.0F, -7.0F));

        part.addOrReplaceChild("HeadBack", CubeListBuilder.create()
                .texOffs(20, 23)
                .addBox(-4.5F, -4.0F, -4.0F, 9, 5, 4, CubeDeformation.NONE), PartPose.offset(0.0F, 19.0F, -7.0F));

        part.addOrReplaceChild("ThoraxTop", CubeListBuilder.create()
                .texOffs(20, 33)
                .addBox(-4.0F, -6.0F, 0.0F, 8, 2, 5, CubeDeformation.NONE), PartPose.offset(0.0F, 19.0F, -7.0F));

        part.addOrReplaceChild("AbMain", CubeListBuilder.create()
                .texOffs(10, 41)
                .addBox(-5.0F, -4.0F, 0.0F, 10, 6, 13, CubeDeformation.NONE), PartPose.offset(0.0F, 19.0F, -7.0F));

        part.addOrReplaceChild("AbBack1", CubeListBuilder.create()
                .texOffs(23, 61)
                .addBox(-4.0F, -3.0F, 13.0F, 8, 5, 2, CubeDeformation.NONE), PartPose.offset(0.0F, 19.0F, -7.0F));

        part.addOrReplaceChild("AbBack2", CubeListBuilder.create()
                .texOffs(27, 69)
                .addBox(-2.0F, -2.0F, 15.0F, 4, 4, 2, CubeDeformation.NONE), PartPose.offset(0.0F, 19.0F, -7.0F));

        part.addOrReplaceChild("LBLeg1", CubeListBuilder.create()
                .texOffs(0, 72)
                .addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3, CubeDeformation.NONE), PartPose.offsetAndRotation(5.0F, 19.0F, 0.0F, 0.0F, -0.7853982F, -0.3490659F));

        part.addOrReplaceChild("LBLeg2", CubeListBuilder.create()
                .texOffs(0, 81)
                .addBox(-1.0F, 3.0F, -1.0F, 2, 2, 2, CubeDeformation.NONE), PartPose.offsetAndRotation(5.0F, 19.0F, 0.0F, 0.0F, -0.7853982F, -0.3490659F));

        part.addOrReplaceChild("LBLeg3", CubeListBuilder.create()
                .texOffs(0, 89)
                .addBox(1.0F, 3.0F, -0.5F, 5, 2, 1, CubeDeformation.NONE), PartPose.offsetAndRotation(5.0F, 19.0F, 0.0F, 0.0F, -0.7853982F, -0.3490659F));

        part.addOrReplaceChild("LBLeg4", CubeListBuilder.create()
                .texOffs(0, 99)
                .addBox(5.95F, 3.0F, -0.5F, 1, 5, 1, CubeDeformation.NONE), PartPose.offsetAndRotation(5.0F, 19.0F, 0.0F, 0.0F, -0.7853982F, -0.3490659F));

        part.addOrReplaceChild("RBLeg1", CubeListBuilder.create()
                .texOffs(0, 72)
                .addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3, CubeDeformation.NONE), PartPose.offsetAndRotation(-5.0F, 19.0F, 0.0F, 0.0F, -2.356194F, 0.3490659F));

        part.addOrReplaceChild("RBLeg2", CubeListBuilder.create()
                .texOffs(0, 81)
                .addBox(-1.0F, 3.0F, -1.0F, 2, 2, 2, CubeDeformation.NONE), PartPose.offsetAndRotation(-5.0F, 19.0F, 0.0F, 0.0F, -2.356194F, 0.3490659F));

        part.addOrReplaceChild("RBLeg3", CubeListBuilder.create()
                .texOffs(0, 89)
                .addBox(1.0F, 3.0F, -0.5F, 5, 2, 1, CubeDeformation.NONE), PartPose.offsetAndRotation(-5.0F, 19.0F, 0.0F, 0.0F, -2.356194F, 0.3490659F));

        part.addOrReplaceChild("RBLeg4", CubeListBuilder.create()
                .texOffs(0, 99)
                .addBox(5.95F, 3.0F, -0.5F, 1, 5, 1, CubeDeformation.NONE), PartPose.offsetAndRotation(-5.0F, 19.0F, 0.0F, 0.0F, -2.356194F, 0.3490659F));

        part.addOrReplaceChild("LFLeg1", CubeListBuilder.create()
                .texOffs(0, 72)
                .addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3, CubeDeformation.NONE), PartPose.offsetAndRotation(5.0F, 19.0F, -8.0F, 0.0F, 1.22173F, -0.3490659F));

        part.addOrReplaceChild("LFLeg2", CubeListBuilder.create()
                .texOffs(0, 81)
                .addBox(-1.0F, 3.0F, -1.0F, 2, 2, 2, CubeDeformation.NONE), PartPose.offsetAndRotation(5.0F, 19.0F, -8.0F, 0.0F, 1.22173F, -0.3490659F));

        part.addOrReplaceChild("LFLeg3", CubeListBuilder.create()
                .texOffs(0, 89)
                .addBox(1.0F, 3.0F, -0.5F, 5, 2, 1, CubeDeformation.NONE), PartPose.offsetAndRotation(5.0F, 19.0F, -8.0F, 0.0F, 1.22173F, -0.3490659F));

        part.addOrReplaceChild("LFLeg4", CubeListBuilder.create()
                .texOffs(0, 99)
                .addBox(5.95F, 3.0F, -0.5F, 1, 5, 1, CubeDeformation.NONE), PartPose.offsetAndRotation(5.0F, 19.0F, -8.0F, 0.0F, 1.22173F, -0.3490659F));

        part.addOrReplaceChild("RFLeg1", CubeListBuilder.create()
                .texOffs(0, 72)
                .addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3, CubeDeformation.NONE), PartPose.offsetAndRotation(-5.0F, 19.0F, -8.0F, 0.0F, 1.919862F, 0.3490659F));

        part.addOrReplaceChild("RFLeg2", CubeListBuilder.create()
                .texOffs(0, 81)
                .addBox(-1.0F, 3.0F, -1.0F, 2, 2, 2, CubeDeformation.NONE), PartPose.offsetAndRotation(-5.0F, 19.0F, -8.0F, 0.0F, 1.919862F, 0.3490659F));

        part.addOrReplaceChild("RFLeg3", CubeListBuilder.create()
                .texOffs(0, 89)
                .addBox(1.0F, 3.0F, -0.5F, 5, 2, 1, CubeDeformation.NONE), PartPose.offsetAndRotation(-5.0F, 19.0F, -8.0F, 0.0F, 1.919862F, 0.3490659F));

        part.addOrReplaceChild("RFLeg4", CubeListBuilder.create()
                .texOffs(0, 99)
                .addBox(5.95F, 3.0F, -0.5F, 1, 5, 1, CubeDeformation.NONE), PartPose.offsetAndRotation(-5.0F, 19.0F, -8.0F, 0.0F, 1.919862F, 0.3490659F));

        part.addOrReplaceChild("LMLeg1", CubeListBuilder.create()
                .texOffs(0, 72)
                .addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3, CubeDeformation.NONE), PartPose.offsetAndRotation(5.0F, 19.0F, -4.0F, 0.0F, 0.0F, -0.3490659F));

        part.addOrReplaceChild("LMLeg2", CubeListBuilder.create()
                .texOffs(0, 81)
                .addBox(-1.0F, 3.0F, -1.0F, 2, 2, 2, CubeDeformation.NONE), PartPose.offsetAndRotation(5.0F, 19.0F, -4.0F, 0.0F, 0.0F, -0.3490659F));

        part.addOrReplaceChild("LMLeg3", CubeListBuilder.create()
                .texOffs(0, 89)
                .addBox(1.0F, 3.0F, -0.5F, 5, 2, 1, CubeDeformation.NONE), PartPose.offsetAndRotation(5.0F, 19.0F, -4.0F, 0.0F, 0.0F, -0.3490659F));

        part.addOrReplaceChild("LMLeg4", CubeListBuilder.create()
                .texOffs(0, 99)
                .addBox(5.95F, 3.0F, -0.5F, 1, 5, 1, CubeDeformation.NONE), PartPose.offsetAndRotation(5.0F, 19.0F, -4.0F, 0.0F, 0.0F, -0.3490659F));

        part.addOrReplaceChild("RMLeg1", CubeListBuilder.create()
                .texOffs(0, 72)
                .addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3, CubeDeformation.NONE), PartPose.offsetAndRotation(-5.0F, 19.0F, -4.0F, 0.0F, 3.141593F, 0.3490659F));

        part.addOrReplaceChild("RMLeg2", CubeListBuilder.create()
                .texOffs(0, 81)
                .addBox(-1.0F, 3.0F, -1.0F, 2, 2, 2, CubeDeformation.NONE), PartPose.offsetAndRotation(-5.0F, 19.0F, -4.0F, 0.0F, 3.141593F, 0.3490659F));

        part.addOrReplaceChild("RMLeg3", CubeListBuilder.create()
                .texOffs(0, 89)
                .addBox(1.0F, 3.0F, -0.5F, 5, 2, 1, CubeDeformation.NONE), PartPose.offsetAndRotation(-5.0F, 19.0F, -4.0F, 0.0F, 3.141593F, 0.3490659F));

        part.addOrReplaceChild("RMLeg4", CubeListBuilder.create()
                .texOffs(0, 99)
                .addBox(5.95F, 3.0F, -0.5F, 1, 5, 1, CubeDeformation.NONE), PartPose.offsetAndRotation(-5.0F, 19.0F, -4.0F, 0.0F, 3.141593F, 0.3490659F));

        PartDefinition RWingFront = part.addOrReplaceChild("RWingFront", CubeListBuilder.create()
                .texOffs(13, 95)
                .addBox(-1.0F, -2.0F, -1.0F, 1, 4, 2, CubeDeformation.NONE), PartPose.offsetAndRotation(-4.0F, 14.0F, -6.0F, -0.1745329F, 0.0F, 0.4363323F));

        RWingFront.addOrReplaceChild("RWingTop", CubeListBuilder.create()
                .texOffs(20, 87)
                .addBox(-1.0F, -4.0F, 4.0F, 1, 1, 13, CubeDeformation.NONE), PartPose.ZERO);

        RWingFront.addOrReplaceChild("RWingMain", CubeListBuilder.create()
                .texOffs(16, 102)
                .addBox(-1.0F, -3.0F, 1.0F, 1, 6, 17, CubeDeformation.NONE), PartPose.ZERO);

        RWingFront.addOrReplaceChild("RWingBack", CubeListBuilder.create()
                .texOffs(49, 96)
                .addBox(-1.0F, -2.0F, 18.0F, 1, 4, 1, CubeDeformation.NONE), PartPose.ZERO);

        PartDefinition LWingFront = part.addOrReplaceChild("LWingFront", CubeListBuilder.create()
                .texOffs(13, 95)
                .addBox(0.0F, -2.0F, -1.0F, 1, 4, 2, CubeDeformation.NONE), PartPose.offsetAndRotation(4.0F, 14.0F, -6.0F, -0.1745329F, 0.0F, -0.4363323F));

        LWingFront.addOrReplaceChild("LWingTop", CubeListBuilder.create()
                .texOffs(20, 87)
                .addBox(0.0F, -4.0F, 4.0F, 1, 1, 13, CubeDeformation.NONE), PartPose.ZERO);

        LWingFront.addOrReplaceChild("LWingMain", CubeListBuilder.create()
                .texOffs(16, 102)
                .addBox(0.0F, -3.0F, 1.0F, 1, 6, 17, CubeDeformation.NONE), PartPose.ZERO);

        LWingFront.addOrReplaceChild("LWingBack", CubeListBuilder.create()
                .texOffs(16, 63)
                .addBox(0.0F, -2.0F, 18.0F, 1, 4, 1, CubeDeformation.NONE), PartPose.ZERO);

        return LayerDefinition.create(mesh, 64, 128);
    }
}
