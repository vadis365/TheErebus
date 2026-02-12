package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.PrayingMantisRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class PrayingMantisModel extends EntityModel<PrayingMantisRenderState> {
    public ModelPart root;
    private final ModelPart Head1, Head2, Head3, REye, LEye, RAnt, LAnt1;
    private final ModelPart Neck, Thorax1, Ab1, Ab2, Ab3, Ab4;
    private final ModelPart LArm1, LArm2, LArm3, LArm4;
    private final ModelPart RArm1, RArm2, RArm3, RArm4;
    private final ModelPart RFLeg1, RFLeg2, RFLeg3, RFLeg4, RFLeg5, RFLeg6;
    private final ModelPart LFLeg1, LFLeg2, LFLeg3, LFLeg4, LFLeg5, LFLeg6;
    private final ModelPart RBLeg1, RBLeg2, RBLeg3, RBLeg4, RBLeg5, RBLeg6;
    private final ModelPart LBLeg1, LBLeg2, LBLeg3, LBLeg4, LBLeg5, LBLeg6;

    public PrayingMantisModel(ModelPart root) {
        super(root);
        this.root = root;
        this.Head1 = root.getChild("Head1");
        this.Head2 = root.getChild("Head2");
        this.Head3 = root.getChild("Head3");
        this.REye = root.getChild("REye");
        this.LEye = root.getChild("LEye");
        this.RAnt = root.getChild("RAnt");
        this.LAnt1 = root.getChild("LAnt1");
        this.Neck = root.getChild("Neck");
        this.Thorax1 = root.getChild("Thorax1");
        this.Ab1 = root.getChild("Ab1");
        this.Ab2 = root.getChild("Ab2");
        this.Ab3 = root.getChild("Ab3");
        this.Ab4 = root.getChild("Ab4");
        this.LArm1 = root.getChild("LArm1");
        this.LArm2 = root.getChild("LArm2");
        this.LArm3 = root.getChild("LArm3");
        this.LArm4 = root.getChild("LArm4");
        this.RArm1 = root.getChild("RArm1");
        this.RArm2 = root.getChild("RArm2");
        this.RArm3 = root.getChild("RArm3");
        this.RArm4 = root.getChild("RArm4");
        this.RFLeg1 = root.getChild("RFLeg1");
        this.RFLeg2 = root.getChild("RFLeg2");
        this.RFLeg3 = root.getChild("RFLeg3");
        this.RFLeg4 = root.getChild("RFLeg4");
        this.RFLeg5 = root.getChild("RFLeg5");
        this.RFLeg6 = root.getChild("RFLeg6");
        this.LFLeg1 = root.getChild("LFLeg1");
        this.LFLeg2 = root.getChild("LFLeg2");
        this.LFLeg3 = root.getChild("LFLeg3");
        this.LFLeg4 = root.getChild("LFLeg4");
        this.LFLeg5 = root.getChild("LFLeg5");
        this.LFLeg6 = root.getChild("LFLeg6");
        this.RBLeg1 = root.getChild("RBLeg1");
        this.RBLeg2 = root.getChild("RBLeg2");
        this.RBLeg3 = root.getChild("RBLeg3");
        this.RBLeg4 = root.getChild("RBLeg4");
        this.RBLeg5 = root.getChild("RBLeg5");
        this.RBLeg6 = root.getChild("RBLeg6");
        this.LBLeg1 = root.getChild("LBLeg1");
        this.LBLeg2 = root.getChild("LBLeg2");
        this.LBLeg3 = root.getChild("LBLeg3");
        this.LBLeg4 = root.getChild("LBLeg4");
        this.LBLeg5 = root.getChild("LBLeg5");
        this.LBLeg6 = root.getChild("LBLeg6");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(20, 11).addBox(-3.5F, -3.0F, -2.5F, 7, 4, 4), PartPose.offsetAndRotation(0F, -6F, -12F, -0.2617994F, 0F, 0F));
        root.addOrReplaceChild("Head2", CubeListBuilder.create().texOffs(23, 5).addBox(-2.5F, 1.0F, -2.5F, 5, 2, 3), PartPose.offsetAndRotation(0F, -6F, -12F, -0.6108652F, 0F, 0F));
        root.addOrReplaceChild("Head3", CubeListBuilder.create().texOffs(26, 0).addBox(-1.5F, 3.0F, -2.0F, 3, 2, 2), PartPose.offsetAndRotation(0F, -6F, -12F, -0.6283185F, 0F, 0F));
        root.addOrReplaceChild("REye", CubeListBuilder.create().texOffs(40, 0).addBox(-4.0F, -4.0F, -3.0F, 3, 4, 3), PartPose.offsetAndRotation(0F, -6F, -12F, -0.2617994F, 0F, -0.2617994F));
        root.addOrReplaceChild("LEye", CubeListBuilder.create().texOffs(10, 0).addBox(1.0F, -4.0F, -3.0F, 3, 4, 3), PartPose.offsetAndRotation(0F, -6F, -12F, -0.2617994F, 0F, 0.2617994F));
        root.addOrReplaceChild("RAnt", CubeListBuilder.create().texOffs(0, 99).addBox(0F, -11.0F, -1.0F, 1, 8, 1), PartPose.offsetAndRotation(0F, -6F, -12F, 0F, 0F, -0.3490659F));
        root.addOrReplaceChild("LAnt1", CubeListBuilder.create().texOffs(0, 99).addBox(-1.0F, -11.0F, -1.0F, 1, 8, 1), PartPose.offsetAndRotation(0F, -6F, -12F, 0F, 0F, 0.3490659F));

        root.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(23, 20).addBox(-2.0F, -4.0F, -2.5F, 4, 5, 4), PartPose.offsetAndRotation(0F, -4F, -8F, 0.9599311F, 0F, 0F));
        root.addOrReplaceChild("Thorax1", CubeListBuilder.create().texOffs(21, 30).addBox(-2.5F, 0.0F, -3.0F, 5, 10, 5), PartPose.offsetAndRotation(0F, -4F, -8F, 0.6981317F, 0F, 0F));
        root.addOrReplaceChild("Ab1", CubeListBuilder.create().texOffs(19, 46).addBox(-3.0F, 10.0F, -4.0F, 6, 10, 6), PartPose.offsetAndRotation(0F, -4F, -8F, 0.7330383F, 0F, 0F));
        root.addOrReplaceChild("Ab2", CubeListBuilder.create().texOffs(16, 63).addBox(-4.0F, 17.0F, -9.0F, 8, 12, 7), PartPose.offsetAndRotation(0F, -4F, -8F, 0.9773844F, 0F, 0F));
        root.addOrReplaceChild("Ab3", CubeListBuilder.create().texOffs(19, 83).addBox(-3.0F, 29.0F, -8.5F, 6, 5, 6), PartPose.offsetAndRotation(0F, -4F, -8F, 0.9773844F, 0F, 0F));
        root.addOrReplaceChild("Ab4", CubeListBuilder.create().texOffs(23, 95).addBox(-2.0F, 34.0F, -7.0F, 4, 2, 4), PartPose.offsetAndRotation(0F, -4F, -8F, 0.9773844F, 0F, 0F));

        root.addOrReplaceChild("LArm1", CubeListBuilder.create().texOffs(0, 20).addBox(0.0F, 0.0F, -1.5F, 2, 10, 3), PartPose.offsetAndRotation(2F, -2F, -9F, 0.3490659F, 0F, 0F));
        root.addOrReplaceChild("LArm2", CubeListBuilder.create().texOffs(0, 34).addBox(-0.5F, -9.0F, 4.5F, 3, 10, 3), PartPose.offsetAndRotation(2F, -2F, -9F, -2.268928F, 0F, 0F));
        root.addOrReplaceChild("LArm3", CubeListBuilder.create().texOffs(0, 48).addBox(0.0F, 2.5F, -7.5F, 2, 11, 2), PartPose.offsetAndRotation(2F, -2F, -9F, 0.3490659F, 0F, 0F));
        root.addOrReplaceChild("LArm4", CubeListBuilder.create().texOffs(0, 62).addBox(0.0F, 3.5F, -8.5F, 2, 8, 1), PartPose.offsetAndRotation(2F, -2F, -9F, 0.3490659F, 0F, 0F));

        root.addOrReplaceChild("RArm1", CubeListBuilder.create().texOffs(54, 20).addBox(-2.0F, 0.0F, -1.5F, 2, 10, 3), PartPose.offsetAndRotation(-2F, -2F, -9F, 0.3490659F, 0F, 0F));
        root.addOrReplaceChild("RArm2", CubeListBuilder.create().texOffs(52, 34).addBox(-2.5F, -9.0F, 4.5F, 3, 10, 3), PartPose.offsetAndRotation(-2F, -2F, -9F, -2.268928F, 0F, 0F));
        root.addOrReplaceChild("RArm3", CubeListBuilder.create().texOffs(56, 48).addBox(-2.0F, 2.5F, -7.5F, 2, 11, 2), PartPose.offsetAndRotation(-2F, -2F, -9F, 0.3490659F, 0F, 0F));
        root.addOrReplaceChild("RArm4", CubeListBuilder.create().texOffs(58, 62).addBox(-2.0F, 3.5F, -8.5F, 2, 8, 1), PartPose.offsetAndRotation(-2F, -2F, -9F, 0.3490659F, 0F, 0F));

        root.addOrReplaceChild("RFLeg1", CubeListBuilder.create().texOffs(52, 72).addBox(-1.5F, 0.0F, -2.0F, 3, 5, 3), PartPose.offsetAndRotation(-2F, 5F, -2F, -0.3490659F, -0.7853982F, 0.3490659F));
        root.addOrReplaceChild("RFLeg2", CubeListBuilder.create().texOffs(56, 81).addBox(-1.0F, 5.0F, -1.5F, 2, 4, 2), PartPose.offsetAndRotation(-2F, 5F, -2F, -0.3490659F, -0.7853982F, 0.3490659F));
        root.addOrReplaceChild("RFLeg3", CubeListBuilder.create().texOffs(21, 89).addBox(-9.0F, 8.0F, -1.0F, 9, 2, 1), PartPose.offsetAndRotation(-2F, 5F, -2F, -0.3490659F, -0.7853982F, 0.3490659F));
        root.addOrReplaceChild("RFLeg4", CubeListBuilder.create().texOffs(60, 99).addBox(-9.0F, 10.0F, -1.0F, 1, 12, 1), PartPose.offsetAndRotation(-2F, 5F, -2F, -0.3490659F, -0.7853982F, 0.3490659F));
        root.addOrReplaceChild("RFLeg5", CubeListBuilder.create().texOffs(56, 113).addBox(-9.5F, 19.5F, -1.5F, 2, 2, 2), PartPose.offsetAndRotation(-2F, 5F, -2F, -0.3490659F, -0.7853982F, 0.3490659F));
        root.addOrReplaceChild("RFLeg6", CubeListBuilder.create().texOffs(60, 118).addBox(-10.0F, 20.0F, -1.0F, 1, 4, 1), PartPose.offsetAndRotation(-2F, 5F, -2F, -0.3490659F, -0.7853982F, 0.3490659F));

        root.addOrReplaceChild("LFLeg1", CubeListBuilder.create().texOffs(0, 72).addBox(-1.5F, 0.0F, -2.0F, 3, 5, 3), PartPose.offsetAndRotation(2F, 5F, -2F, -0.3490659F, 0.7853982F, -0.3490659F));
        root.addOrReplaceChild("LFLeg2", CubeListBuilder.create().texOffs(0, 81).addBox(-1.0F, 5.0F, -1.5F, 2, 4, 2), PartPose.offsetAndRotation(2F, 5F, -2F, -0.3490659F, 0.7853982F, -0.3490659F));
        root.addOrReplaceChild("LFLeg3", CubeListBuilder.create().texOffs(21, 89).addBox(0.0F, 8.0F, -1.0F, 9, 2, 1), PartPose.offsetAndRotation(2F, 5F, -2F, -0.3490659F, 0.7853982F, -0.3490659F));
        root.addOrReplaceChild("LFLeg4", CubeListBuilder.create().texOffs(0, 99).addBox(7.95F, 10.0F, -1.0F, 1, 12, 1), PartPose.offsetAndRotation(2F, 5F, -2F, -0.3490659F, 0.7853982F, -0.3490659F));
        root.addOrReplaceChild("LFLeg5", CubeListBuilder.create().texOffs(0, 113).addBox(7.5F, 19.5F, -1.5F, 2, 2, 2), PartPose.offsetAndRotation(2F, 5F, -2F, -0.3490659F, 0.7853982F, -0.3490659F));
        root.addOrReplaceChild("LFLeg6", CubeListBuilder.create().texOffs(0, 118).addBox(9.0F, 20.0F, -1.0F, 1, 4, 1), PartPose.offsetAndRotation(2F, 5F, -2F, -0.3490659F, 0.7853982F, -0.3490659F));

        root.addOrReplaceChild("RBLeg1", CubeListBuilder.create().texOffs(8, 95).addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3), PartPose.offsetAndRotation(-2F, 8F, 0F, 0.3490659F, 0.7853982F, 0.3490659F));
        root.addOrReplaceChild("RBLeg2", CubeListBuilder.create().texOffs(10, 103).addBox(-1.0F, 4.0F, -1.5F, 2, 3, 2), PartPose.offsetAndRotation(-2F, 8F, 0F, 0.3490659F, 0.7853982F, 0.3490659F));
        root.addOrReplaceChild("RBLeg3", CubeListBuilder.create().texOffs(21, 89).addBox(-9.0F, 6.0F, -0.5F, 9, 2, 1), PartPose.offsetAndRotation(-2F, 8F, 0F, 0.3490659F, 0.7853982F, 0.3490659F));
        root.addOrReplaceChild("RBLeg4", CubeListBuilder.create().texOffs(18, 109).addBox(-9.0F, 8.0F, -0.5F, 1, 10, 1), PartPose.offsetAndRotation(-2F, 8F, 0F, 0.3490659F, 0.7853982F, 0.3490659F));
        root.addOrReplaceChild("RBLeg5", CubeListBuilder.create().texOffs(14, 121).addBox(-9.5F, 15.5F, -1.0F, 2, 2, 2), PartPose.offsetAndRotation(-2F, 8F, 0F, 0.3490659F, 0.7853982F, 0.3490659F));
        root.addOrReplaceChild("RBLeg6", CubeListBuilder.create().texOffs(9, 120).addBox(-10.0F, 16.0F, -0.5F, 1, 4, 1), PartPose.offsetAndRotation(-2F, 8F, 0F, 0.3490659F, 0.7853982F, 0.3490659F));

        root.addOrReplaceChild("LBLeg1", CubeListBuilder.create().texOffs(42, 95).addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3), PartPose.offsetAndRotation(2F, 8F, 0F, 0.3490659F, -0.7853982F, -0.3490659F));
        root.addOrReplaceChild("LBLeg2", CubeListBuilder.create().texOffs(44, 103).addBox(-1.0F, 4.0F, -1.5F, 2, 3, 2), PartPose.offsetAndRotation(2F, 8F, 0F, 0.3490659F, -0.7853982F, -0.3490659F));
        root.addOrReplaceChild("LBLeg3", CubeListBuilder.create().texOffs(21, 89).addBox(0.0F, 6.0F, -0.5F, 9, 2, 1), PartPose.offsetAndRotation(2F, 8F, 0F, 0.3490659F, -0.7853982F, -0.3490659F));
        root.addOrReplaceChild("LBLeg4", CubeListBuilder.create().texOffs(40, 109).addBox(8.0F, 8.0F, -0.5F, 1, 10, 1), PartPose.offsetAndRotation(2F, 8F, 0F, 0.3490659F, -0.7853982F, -0.3490659F));
        root.addOrReplaceChild("LBLeg5", CubeListBuilder.create().texOffs(40, 121).addBox(7.5F, 15.5F, -1.0F, 2, 2, 2), PartPose.offsetAndRotation(2F, 8F, 0F, 0.3490659F, -0.7853982F, -0.3490659F));
        root.addOrReplaceChild("LBLeg6", CubeListBuilder.create().texOffs(49, 120).addBox(9.0F, 16.0F, -0.5F, 1, 4, 1), PartPose.offsetAndRotation(2F, 8F, 0F, 0.3490659F, -0.7853982F, -0.3490659F));

        return LayerDefinition.create(mesh, 64, 128);
    }
}
