package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.UmberGolemRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class UmberGolemModel extends EntityModel<UmberGolemRenderState> {
    public ModelPart root;
    private final ModelPart HeadTop, HeadMain, HeadFront, HeadBottom, EyeR, EyeL;
    private final ModelPart MandibleR1, MandibleR2, MandibleL1, MandibleL2, Neck;
    private final ModelPart BodyTop, BodyMid, BodyMain, BodyBack;
    private final ModelPart ArmR1, ArmR2, ArmR3, PincerR1, PincerROuter, PincerRInner;
    private final ModelPart ArmL1, ArmL2, ArmL3, PincerL1, PincerLOuter, PincerLInner;
    private final ModelPart LegR1, LegR2, FootR, FootRFront, ToeROuter1, ToeROuter2, ToeRInner1, ToeRInner2, FootRBack, ToeRBack1, ToeRBack2;
    private final ModelPart LegL1, LegL2, FootL, FootLFront, ToeLOuter1, ToeLOuter2, ToeLInner1, ToeLInner2, FootLBack, ToeLBack1, ToeLBack2;

    public UmberGolemModel(ModelPart root) {
        super(root);
        this.root = root;
        this.HeadTop = root.getChild("HeadTop");
        this.HeadMain = root.getChild("HeadMain");
        this.HeadFront = root.getChild("HeadFront");
        this.HeadBottom = root.getChild("HeadBottom");
        this.EyeR = root.getChild("EyeR");
        this.EyeL = root.getChild("EyeL");
        this.MandibleR1 = root.getChild("MandibleR1");
        this.MandibleR2 = root.getChild("MandibleR2");
        this.MandibleL1 = root.getChild("MandibleL1");
        this.MandibleL2 = root.getChild("MandibleL2");
        this.Neck = root.getChild("Neck");
        this.BodyTop = root.getChild("BodyTop");
        this.BodyMid = root.getChild("BodyMid");
        this.BodyMain = root.getChild("BodyMain");
        this.BodyBack = root.getChild("BodyBack");

        this.ArmR1 = root.getChild("ArmR1");
        this.ArmR2 = ArmR1.getChild("ArmR2");
        this.ArmR3 = ArmR1.getChild("ArmR3");
        this.PincerR1 = ArmR1.getChild("PincerR1");
        this.PincerROuter = ArmR1.getChild("PincerROuter");
        this.PincerRInner = ArmR1.getChild("PincerRInner");

        this.ArmL1 = root.getChild("ArmL1");
        this.ArmL2 = ArmL1.getChild("ArmL2");
        this.ArmL3 = ArmL1.getChild("ArmL3");
        this.PincerL1 = ArmL1.getChild("PincerL1");
        this.PincerLOuter = ArmL1.getChild("PincerLOuter");
        this.PincerLInner = ArmL1.getChild("PincerLInner");

        this.LegR1 = root.getChild("LegR1");
        this.LegR2 = LegR1.getChild("LegR2");
        this.FootR = root.getChild("FootR");
        this.FootRFront = FootR.getChild("FootRFront");
        this.ToeROuter1 = FootR.getChild("ToeROuter1");
        this.ToeROuter2 = FootR.getChild("ToeROuter2");
        this.ToeRInner1 = FootR.getChild("ToeRInner1");
        this.ToeRInner2 = FootR.getChild("ToeRInner2");
        this.FootRBack = FootR.getChild("FootRBack");
        this.ToeRBack1 = FootR.getChild("ToeRBack1");
        this.ToeRBack2 = FootR.getChild("ToeRBack2");

        this.LegL1 = root.getChild("LegL1");
        this.LegL2 = LegL1.getChild("LegL2");
        this.FootL = root.getChild("FootL");
        this.FootLFront = FootL.getChild("FootLFront");
        this.ToeLOuter1 = FootL.getChild("ToeLOuter1");
        this.ToeLOuter2 = FootL.getChild("ToeLOuter2");
        this.ToeLInner1 = FootL.getChild("ToeLInner1");
        this.ToeLInner2 = FootL.getChild("ToeLInner2");
        this.FootLBack = FootL.getChild("FootLBack");
        this.ToeLBack1 = FootL.getChild("ToeLBack1");
        this.ToeLBack2 = FootL.getChild("ToeLBack2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("HeadTop", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3F, -4.5F, 3, 1, 3), PartPose.offsetAndRotation(0F, 5F, -5F, 0.2617994F, 0F, 0F));
        root.addOrReplaceChild("HeadMain", CubeListBuilder.create().texOffs(0, 6).addBox(-2.5F, -2F, -5.5F, 5, 5, 5), PartPose.offsetAndRotation(0F, 5F, -5F, 0.2617994F, 0F, 0F));
        root.addOrReplaceChild("HeadFront", CubeListBuilder.create().texOffs(0, 18).addBox(-2F, -1F, -6.5F, 4, 3, 1), PartPose.offsetAndRotation(0F, 5F, -5F, 0.2617994F, 0F, 0F));
        root.addOrReplaceChild("HeadBottom", CubeListBuilder.create().texOffs(11, 18).addBox(-1.5F, 3F, -4.5F, 3, 1, 3), PartPose.offsetAndRotation(0F, 5F, -5F, 0.2617994F, 0F, 0F));
        root.addOrReplaceChild("EyeR", CubeListBuilder.create().texOffs(21, 0).addBox(-3.5F, -2F, -4F, 1, 2, 3), PartPose.offsetAndRotation(0F, 5F, -5F, 0.6981317F, 0F, 0F));
        root.addOrReplaceChild("EyeL", CubeListBuilder.create().texOffs(21, 8).addBox(2.5F, -2F, -4F, 1, 2, 3), PartPose.offsetAndRotation(0F, 5F, -5F, 0.6981317F, 0F, 0F));
        root.addOrReplaceChild("MandibleR1", CubeListBuilder.create().texOffs(30, 56).addBox(-3.5F, 1F, -10.5F, 2, 1, 6), PartPose.offsetAndRotation(0F, 5F, -5F, 0.2617994F, 0F, 0F));
        root.addOrReplaceChild("MandibleR2", CubeListBuilder.create().texOffs(31, 75).addBox(-1.5F, 1F, -11.5F, 1, 1, 2), PartPose.offsetAndRotation(0F, 5F, -5F, 0.2617994F, 0F, 0F));
        root.addOrReplaceChild("MandibleL1", CubeListBuilder.create().texOffs(30, 65).addBox(1.5F, 1F, -10.5F, 2, 1, 6), PartPose.offsetAndRotation(0F, 5F, -5F, 0.2617994F, 0F, 0F));
        root.addOrReplaceChild("MandibleL2", CubeListBuilder.create().texOffs(31, 81).addBox(0.5F, 1F, -11.5F, 1, 1, 2), PartPose.offsetAndRotation(0F, 5F, -5F, 0.2617994F, 0F, 0F));
        root.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(37, 45).addBox(-1.5F, -1F, -0.5F, 3, 3, 2), PartPose.offsetAndRotation(0F, 5F, -5F, 0.2617994F, 0F, 0F));

        root.addOrReplaceChild("BodyTop", CubeListBuilder.create().texOffs(0, 23).addBox(-4F, -2F, -1.5F, 8, 1, 4), PartPose.offsetAndRotation(0F, 4F, -3F, 0.2617994F, 0F, 0F));
        root.addOrReplaceChild("BodyMid", CubeListBuilder.create().texOffs(0, 43).addBox(-6F, 0F, -2.5F, 12, 5, 6), PartPose.offsetAndRotation(0F, 3F, -3F, 0.2617994F, 0F, 0F));
        root.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 56).addBox(-4.5F, 5F, -1.5F, 9, 7, 5), PartPose.offsetAndRotation(0F, 3F, -3F, 0.2617994F, 0F, 0F));
        root.addOrReplaceChild("BodyBack", CubeListBuilder.create().texOffs(0, 30).addBox(-4F, 1F, 3.5F, 8, 9, 2), PartPose.offsetAndRotation(0F, 3F, -3F, 0.2617994F, 0F, 0F));

        PartDefinition ArmR1 = root.addOrReplaceChild("ArmR1", CubeListBuilder.create().texOffs(30, 0).addBox(-4F, -2F, -1.5F, 4, 4, 5), PartPose.offsetAndRotation(-6F, 5F, -3F, -0.2617994F, 0F, 0F));
        ArmR1.addOrReplaceChild("ArmR2", CubeListBuilder.create().texOffs(30, 12).addBox(-3.5F, 2F, -0.5F, 3, 4, 3), PartPose.rotation(0.2617994F + 0.2617994F, 0F, 0F));
        ArmR1.addOrReplaceChild("ArmR3", CubeListBuilder.create().texOffs(30, 22).addBox(-3F, 1F, 3.5F, 2, 4, 3), PartPose.rotation(-0.7853982F + 0.2617994F, 0F, 0F));
        ArmR1.addOrReplaceChild("PincerR1", CubeListBuilder.create().texOffs(30, 32).addBox(-4F, 5F, 2.5F, 4, 4, 4), PartPose.rotation(-0.7853982F + 0.2617994F, 0F, 0F));
        ArmR1.addOrReplaceChild("PincerROuter", CubeListBuilder.create().texOffs(43, 12).addBox(-3.5F, 9F, 3.5F, 1, 3, 2), PartPose.rotation(-0.7853982F + 0.2617994F, 0F, 0F));
        ArmR1.addOrReplaceChild("PincerRInner", CubeListBuilder.create().texOffs(43, 19).addBox(-1.5F, 9F, 3.5F, 1, 3, 2), PartPose.rotation(-0.7853982F + 0.2617994F, 0F, 0F));

        PartDefinition ArmL1 = root.addOrReplaceChild("ArmL1", CubeListBuilder.create().texOffs(30, 0).addBox(0F, -2F, -1.5F, 4, 4, 5), PartPose.offsetAndRotation(6F, 5F, -3F, -0.2617994F, 0F, 0F));
        ArmL1.addOrReplaceChild("ArmL2", CubeListBuilder.create().texOffs(30, 12).addBox(0.5F, 2F, -0.5F, 3, 4, 3), PartPose.rotation(0.2617994F + 0.2617994F, 0F, 0F));
        ArmL1.addOrReplaceChild("ArmL3", CubeListBuilder.create().texOffs(30, 22).addBox(1F, 1F, 3.5F, 2, 4, 3), PartPose.rotation(-0.7853982F + 0.2617994F, 0F, 0F));
        ArmL1.addOrReplaceChild("PincerL1", CubeListBuilder.create().texOffs(30, 32).addBox(0F, 5F, 2.5F, 4, 4, 4), PartPose.rotation(-0.7853982F + 0.2617994F, 0F, 0F));
        ArmL1.addOrReplaceChild("PincerLOuter", CubeListBuilder.create().texOffs(43, 19).addBox(2.5F, 9F, 3.5F, 1, 3, 2), PartPose.rotation(-0.7853982F + 0.2617994F, 0F, 0F));
        ArmL1.addOrReplaceChild("PincerLInner", CubeListBuilder.create().texOffs(43, 12).addBox(0.5F, 9F, 3.5F, 1, 3, 2), PartPose.rotation(-0.7853982F + 0.2617994F, 0F, 0F));

        PartDefinition LegR1 = root.addOrReplaceChild("LegR1", CubeListBuilder.create().texOffs(0, 70).addBox(-1.5F, -1F, -1.5F, 3, 6, 3), PartPose.offsetAndRotation(-3F, 14F, 0F, -1.047198F, 0F, 0F));
        LegR1.addOrReplaceChild("LegR2", CubeListBuilder.create().texOffs(0, 82).addBox(-1F, 0F, -5.5F, 2, 7, 3), PartPose.rotation(0.5235988F + 1.047198F, 0F, 0F));

        PartDefinition FootR = root.addOrReplaceChild("FootR", CubeListBuilder.create().texOffs(0, 102).addBox(-2F, 7F, -2.5F, 4, 3, 4), PartPose.offset(-3F, 14F, 0F));
        FootR.addOrReplaceChild("FootRFront", CubeListBuilder.create().texOffs(0, 112).addBox(-2F, 8F, -4.5F, 4, 2, 2), PartPose.ZERO);
        FootR.addOrReplaceChild("ToeROuter1", CubeListBuilder.create().texOffs(0, 118).addBox(-1.5F, 8F, -6.5F, 1, 1, 2), PartPose.ZERO);
        FootR.addOrReplaceChild("ToeROuter2", CubeListBuilder.create().texOffs(0, 124).addBox(-1.5F, 9.8F, -3.4F, 1, 2, 1), PartPose.rotation(-0.3490659F, 0F, 0F));
        FootR.addOrReplaceChild("ToeRInner1", CubeListBuilder.create().texOffs(0, 118).addBox(0.5F, 8F, -6.5F, 1, 1, 2), PartPose.ZERO);
        FootR.addOrReplaceChild("ToeRInner2", CubeListBuilder.create().texOffs(0, 124).addBox(0.5F, 9.8F, -3.4F, 1, 2, 1), PartPose.rotation(-0.3490659F, 0F, 0F));
        FootR.addOrReplaceChild("FootRBack", CubeListBuilder.create().texOffs(0, 96).addBox(-1F, 8F, 1.5F, 2, 2, 1), PartPose.ZERO);
        FootR.addOrReplaceChild("ToeRBack1", CubeListBuilder.create().texOffs(0, 118).addBox(-0.5F, 8F, -4.5F, 1, 1, 2), PartPose.rotation(0F, 3.141593F, 0F));
        FootR.addOrReplaceChild("ToeRBack2", CubeListBuilder.create().texOffs(0, 124).addBox(-0.5F, 9.2F, -1.5F, 1, 2, 1), PartPose.rotation(-0.3490659F, 3.141593F, 0F));

        PartDefinition LegL1 = root.addOrReplaceChild("LegL1", CubeListBuilder.create().texOffs(0, 70).addBox(-1.5F, -1F, -1.5F, 3, 6, 3), PartPose.offsetAndRotation(3F, 14F, 0F, -1.047198F, 0F, 0F));
        LegL1.addOrReplaceChild("LegL2", CubeListBuilder.create().texOffs(0, 82).addBox(-1F, 0F, -5.5F, 2, 7, 3), PartPose.rotation(0.5235988F + 1.047198F, 0F, 0F));

        PartDefinition FootL = root.addOrReplaceChild("FootL", CubeListBuilder.create().texOffs(0, 102).addBox(-2F, 7F, -2.5F, 4, 3, 4), PartPose.offset(3F, 14F, 0F));
        FootL.addOrReplaceChild("FootLFront", CubeListBuilder.create().texOffs(0, 112).addBox(-2F, 8F, -4.5F, 4, 2, 2), PartPose.ZERO);
        FootL.addOrReplaceChild("ToeLOuter1", CubeListBuilder.create().texOffs(0, 118).addBox(0.5F, 8F, -6.5F, 1, 1, 2), PartPose.ZERO);
        FootL.addOrReplaceChild("ToeLOuter2", CubeListBuilder.create().texOffs(0, 124).addBox(0.5F, 9.8F, -3.4F, 1, 2, 1), PartPose.rotation(-0.3490659F, 0F, 0F));
        FootL.addOrReplaceChild("ToeLInner1", CubeListBuilder.create().texOffs(0, 118).addBox(-1.5F, 8F, -6.5F, 1, 1, 2), PartPose.ZERO);
        FootL.addOrReplaceChild("ToeLInner2", CubeListBuilder.create().texOffs(0, 124).addBox(-1.5F, 9.8F, -3.4F, 1, 2, 1), PartPose.rotation(-0.3490659F, 0F, 0F));
        FootL.addOrReplaceChild("FootLBack", CubeListBuilder.create().texOffs(0, 96).addBox(-1F, 8F, 1.5F, 2, 2, 1), PartPose.ZERO);
        FootL.addOrReplaceChild("ToeLBack1", CubeListBuilder.create().texOffs(0, 118).addBox(-0.5F, 8F, -4.5F, 1, 1, 2), PartPose.rotation(0F, 3.141593F, 0F));
        FootL.addOrReplaceChild("ToeLBack2", CubeListBuilder.create().texOffs(0, 124).addBox(-0.5F, 9.2F, -1.5F, 1, 2, 1), PartPose.rotation(-0.3490659F, 3.141593F, 0F));

        return LayerDefinition.create(mesh, 64, 128);
    }
}
