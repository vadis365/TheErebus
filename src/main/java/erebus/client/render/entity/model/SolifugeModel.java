package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.SolifugeRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class SolifugeModel extends EntityModel<SolifugeRenderState> {
    public ModelPart root;
    private final ModelPart HeadMain, HeadTop, HeadFront, Neck, REye, LEye, Thorax;
    private final ModelPart RMand1, RMand2, LMand1, LMand2;
    private final ModelPart Ab1, Ab2, Ab3, Ab4;
    private final ModelPart RBL1, RBL2, RBL3, RBL4;
    private final ModelPart LBL1, LBL2, LBL3, LBL4;
    private final ModelPart RMBL1, RMBL2, RMBL3, RMBL4;
    private final ModelPart LMBL1, LMBL2, LMBL3, LMBL4;
    private final ModelPart RMFL1, RMFL2, RMFL3, RMFL4;
    private final ModelPart LMFL1, LMFL2, LMFL3, LMFL4;
    private final ModelPart RFL1, RFL2, RFL3, RFL4;
    private final ModelPart LFL1, LFL2, LFL3, LFL4;
    private final ModelPart ClawR1, ClawR2, ClawR3;
    private final ModelPart ClawL1, ClawL2, ClawL3;

    public SolifugeModel(ModelPart root) {
        super(root);
        this.root = root;
        this.HeadMain = root.getChild("HeadMain");
        this.HeadTop = root.getChild("HeadTop");
        this.HeadFront = root.getChild("HeadFront");
        this.Neck = root.getChild("Neck");
        this.REye = root.getChild("REye");
        this.LEye = root.getChild("LEye");
        this.Thorax = root.getChild("Thorax");
        this.RMand1 = root.getChild("RMand1");
        this.RMand2 = root.getChild("RMand2");
        this.LMand1 = root.getChild("LMand1");
        this.LMand2 = root.getChild("LMand2");
        this.Ab1 = root.getChild("Ab1");
        this.Ab2 = root.getChild("Ab2");
        this.Ab3 = root.getChild("Ab3");
        this.Ab4 = root.getChild("Ab4");

        this.RBL1 = root.getChild("RBL1");
        this.RBL2 = RBL1.getChild("RBL2");
        this.RBL3 = RBL1.getChild("RBL3");
        this.RBL4 = RBL1.getChild("RBL4");

        this.LBL1 = root.getChild("LBL1");
        this.LBL2 = LBL1.getChild("LBL2");
        this.LBL3 = LBL1.getChild("LBL3");
        this.LBL4 = LBL1.getChild("LBL4");

        this.RMBL1 = root.getChild("RMBL1");
        this.RMBL2 = RMBL1.getChild("RMBL2");
        this.RMBL3 = RMBL1.getChild("RMBL3");
        this.RMBL4 = RMBL1.getChild("RMBL4");

        this.LMBL1 = root.getChild("LMBL1");
        this.LMBL2 = LMBL1.getChild("LMBL2");
        this.LMBL3 = LMBL1.getChild("LMBL3");
        this.LMBL4 = LMBL1.getChild("LMBL4");

        this.RMFL1 = root.getChild("RMFL1");
        this.RMFL2 = RMFL1.getChild("RMFL2");
        this.RMFL3 = RMFL1.getChild("RMFL3");
        this.RMFL4 = RMFL1.getChild("RMFL4");

        this.LMFL1 = root.getChild("LMFL1");
        this.LMFL2 = LMFL1.getChild("LMFL2");
        this.LMFL3 = LMFL1.getChild("LMFL3");
        this.LMFL4 = LMFL1.getChild("LMFL4");

        this.RFL1 = root.getChild("RFL1");
        this.RFL2 = RFL1.getChild("RFL2");
        this.RFL3 = RFL1.getChild("RFL3");
        this.RFL4 = RFL1.getChild("RFL4");

        this.LFL1 = root.getChild("LFL1");
        this.LFL2 = LFL1.getChild("LFL2");
        this.LFL3 = LFL1.getChild("LFL3");
        this.LFL4 = LFL1.getChild("LFL4");

        this.ClawR1 = root.getChild("ClawR1");
        this.ClawR2 = ClawR1.getChild("ClawR2");
        this.ClawR3 = ClawR1.getChild("ClawR3");

        this.ClawL1 = root.getChild("ClawL1");
        this.ClawL2 = ClawL1.getChild("ClawL2");
        this.ClawL3 = ClawL1.getChild("ClawL3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("HeadMain", CubeListBuilder.create().texOffs(21, 34).addBox(-3.5F, -1F, -3F, 7, 4, 4), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("HeadTop", CubeListBuilder.create().texOffs(24, 29).addBox(-2.5F, -2F, -3F, 5, 1, 3), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("HeadFront", CubeListBuilder.create().texOffs(22, 21).addBox(-3F, -1F, -6F, 6, 3, 4), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(25, 43).addBox(-2.5F, -0.5F, -3.5F, 5, 2, 2), PartPose.offset(0F, 17F, 4F));
        root.addOrReplaceChild("REye", CubeListBuilder.create().texOffs(33, 9).addBox(-1.2F, -1.5F, -5F, 1, 1, 2), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("LEye", CubeListBuilder.create().texOffs(25, 9).addBox(0.2F, -1.5F, -5F, 1, 1, 2), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("Thorax", CubeListBuilder.create().texOffs(16, 48).addBox(-3.5F, -1.5F, -1.5F, 7, 3, 9), PartPose.offset(0F, 17F, 4F));
        root.addOrReplaceChild("RMand1", CubeListBuilder.create().texOffs(33, 13).addBox(-3.5F, -2.5F, -9.5F, 3, 3, 4), PartPose.offsetAndRotation(0F, 17F, 0F, 0.3490659F, 0F, 0F));
        root.addOrReplaceChild("RMand2", CubeListBuilder.create().texOffs(40, 7).addBox(-5F, -1.5F, -11F, 1, 2, 3), PartPose.offsetAndRotation(0F, 17F, 0F, 0.3490659F, -0.2617994F, 0F));
        root.addOrReplaceChild("LMand1", CubeListBuilder.create().texOffs(17, 13).addBox(0.5F, -2.5F, -9.5F, 3, 3, 4), PartPose.offsetAndRotation(0F, 17F, 0F, 0.3490659F, 0F, 0F));
        root.addOrReplaceChild("LMand2", CubeListBuilder.create().texOffs(16, 7).addBox(4F, -1.5F, -11F, 1, 2, 3), PartPose.offsetAndRotation(0F, 17F, 0F, 0.3490659F, 0.2617994F, 0F));
        root.addOrReplaceChild("Ab1", CubeListBuilder.create().texOffs(20, 61).addBox(-4F, -1.5F, 7.5F, 8, 5, 4), PartPose.offset(0F, 16F, 4F));
        root.addOrReplaceChild("Ab2", CubeListBuilder.create().texOffs(18, 71).addBox(-5F, -2F, 11.5F, 10, 6, 4), PartPose.offset(0F, 16F, 4F));
        root.addOrReplaceChild("Ab3", CubeListBuilder.create().texOffs(20, 82).addBox(-4F, -1.5F, 15.5F, 8, 5, 4), PartPose.offset(0F, 16F, 4F));
        root.addOrReplaceChild("Ab4", CubeListBuilder.create().texOffs(23, 92).addBox(-3F, -1F, 19.5F, 6, 4, 3), PartPose.offset(0F, 16F, 4F));

        PartDefinition RBL1 = root.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 9, 2, 2), PartPose.offsetAndRotation(-3F, 17F, 10F, 0F, -2.443461F, 0.2617994F));
        RBL1.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 88).addBox(7F, 3F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, -0.3490658F));
        RBL1.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(0, 82).addBox(7F, 6.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, -0.3490658F));
        RBL1.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(0, 76).addBox(5.5F, 11F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, -0.5235988F));

        PartDefinition LBL1 = root.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 9, 2, 2), PartPose.offsetAndRotation(3F, 17F, 10F, 0F, -0.6981317F, -0.2617994F));
        LBL1.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(0, 88).addBox(7F, 3F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, -0.3490658F));
        LBL1.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(0, 82).addBox(7F, 6.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, -0.3490658F));
        LBL1.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(0, 76).addBox(5.5F, 11F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, -0.5235988F));

        PartDefinition RMBL1 = root.addOrReplaceChild("RMBL1", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 9, 2, 2), PartPose.offsetAndRotation(-3F, 17F, 8F, 0F, -2.879793F, 0.2617994F));
        RMBL1.addOrReplaceChild("RMBL2", CubeListBuilder.create().texOffs(0, 88).addBox(7F, 3F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, -0.3490658F));
        RMBL1.addOrReplaceChild("RMBL3", CubeListBuilder.create().texOffs(0, 82).addBox(7F, 6.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, -0.3490658F));
        RMBL1.addOrReplaceChild("RMBL4", CubeListBuilder.create().texOffs(0, 76).addBox(5.5F, 11F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, -0.5235988F));

        PartDefinition LMBL1 = root.addOrReplaceChild("LMBL1", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 9, 2, 2), PartPose.offsetAndRotation(3F, 17F, 8F, 0F, -0.3490659F, -0.2617994F));
        LMBL1.addOrReplaceChild("LMBL2", CubeListBuilder.create().texOffs(0, 88).addBox(7F, 3F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, -0.3490658F));
        LMBL1.addOrReplaceChild("LMBL3", CubeListBuilder.create().texOffs(0, 82).addBox(7F, 6.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, -0.3490658F));
        LMBL1.addOrReplaceChild("LMBL4", CubeListBuilder.create().texOffs(0, 76).addBox(5.5F, 11F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, -0.5235988F));

        PartDefinition RMFL1 = root.addOrReplaceChild("RMFL1", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 9, 2, 2), PartPose.offsetAndRotation(-3F, 17F, 6F, 0F, 2.879793F, 0.2617994F));
        RMFL1.addOrReplaceChild("RMFL2", CubeListBuilder.create().texOffs(0, 88).addBox(7F, 3F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, -0.3490658F));
        RMFL1.addOrReplaceChild("RMFL3", CubeListBuilder.create().texOffs(0, 82).addBox(7F, 6.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, -0.3490658F));
        RMFL1.addOrReplaceChild("RMFL4", CubeListBuilder.create().texOffs(0, 76).addBox(5.5F, 11F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, -0.5235988F));

        PartDefinition LMFL1 = root.addOrReplaceChild("LMFL1", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 9, 2, 2), PartPose.offsetAndRotation(3F, 17F, 6F, 0F, 0.1745329F, -0.2617994F));
        LMFL1.addOrReplaceChild("LMFL2", CubeListBuilder.create().texOffs(0, 88).addBox(7F, 3F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, -0.3490658F));
        LMFL1.addOrReplaceChild("LMFL3", CubeListBuilder.create().texOffs(0, 82).addBox(7F, 6.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, -0.3490658F));
        LMFL1.addOrReplaceChild("LMFL4", CubeListBuilder.create().texOffs(0, 76).addBox(5.5F, 11F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, -0.5235988F));

        PartDefinition RFL1 = root.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 9, 2, 2), PartPose.offsetAndRotation(-3F, 17F, 4F, 0F, 2.443461F, 0.2617994F));
        RFL1.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(0, 88).addBox(7F, 3F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, -0.3490658F));
        RFL1.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(0, 82).addBox(7F, 6.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, -0.3490658F));
        RFL1.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(0, 76).addBox(5.5F, 11F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, -0.5235988F));

        PartDefinition LFL1 = root.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(0, 95).addBox(0F, -1F, -1F, 9, 2, 2), PartPose.offsetAndRotation(3F, 17F, 4F, 0F, 0.6981317F, -0.2617994F));
        LFL1.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(0, 88).addBox(7F, 3F, -1F, 2, 4, 2), PartPose.rotation(0F, 0F, -0.3490658F));
        LFL1.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(0, 82).addBox(7F, 6.5F, -0.5F, 2, 4, 1), PartPose.rotation(0F, 0F, -0.3490658F));
        LFL1.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(0, 76).addBox(5.5F, 11F, -0.5F, 1, 4, 1), PartPose.rotation(0F, 0F, -0.5235988F));

        PartDefinition ClawR1 = root.addOrReplaceChild("ClawR1", CubeListBuilder.create().texOffs(42, 29).addBox(-9F, -1F, -1F, 9, 2, 2), PartPose.offsetAndRotation(-2F, 18F, 0F, 0F, -1.047198F, 0F));
        ClawR1.addOrReplaceChild("ClawR2", CubeListBuilder.create().texOffs(49, 16).addBox(-1F, -11F, -8F, 2, 7, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0.5235988F, 1.5707968F, 0F));
        ClawR1.addOrReplaceChild("ClawR3", CubeListBuilder.create().texOffs(44, 26).addBox(-19F, -6F, -0.5F, 8, 1, 1), PartPose.ZERO);

        PartDefinition ClawL1 = root.addOrReplaceChild("ClawL1", CubeListBuilder.create().texOffs(0, 29).addBox(-9F, -1F, -1F, 9, 2, 2), PartPose.offsetAndRotation(2F, 18F, 0F, 0F, -2.094395F, 0F));
        ClawL1.addOrReplaceChild("ClawL2", CubeListBuilder.create().texOffs(7, 16).addBox(-1F, -11F, -8F, 2, 7, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0.5235988F, 1.5707962F, 0F));
        ClawL1.addOrReplaceChild("ClawL3", CubeListBuilder.create().texOffs(2, 26).addBox(-19F, -6F, -0.5F, 8, 1, 1), PartPose.ZERO);

        return LayerDefinition.create(mesh, 64, 128);
    }
}
