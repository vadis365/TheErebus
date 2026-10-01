package erebus.client.render.block.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class UmberGolemStatueModel extends Model<Void> {
    public UmberGolemStatueModel(ModelPart root) {
        super(root, RenderTypes::entityCutout);
    }

    public static LayerDefinition createBodyLayer() {
        var mesh = new MeshDefinition();
        var root = mesh.getRoot();
        var HeadTop = root.addOrReplaceChild("HeadTop", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3F, -4.5F, 3, 1, 3), PartPose.offsetAndRotation(0F, 5F, -5F, 0.2617994F, 0F, 0F));
        var HeadMain = root.addOrReplaceChild("HeadMain", CubeListBuilder.create().texOffs(0, 6).addBox(-2.5F, -2F, -5.5F, 5, 5, 5), PartPose.offsetAndRotation(0F, 5F, -5F, 0.2617994F, 0F, 0F));
        var HeadFront = root.addOrReplaceChild("HeadFront", CubeListBuilder.create().texOffs(0, 18).addBox(-2F, -1F, -6.5F, 4, 3, 1), PartPose.offsetAndRotation(0F, 5F, -5F, 0.2617994F, 0F, 0F));
        var HeadBottom = root.addOrReplaceChild("HeadBottom", CubeListBuilder.create().texOffs(11, 18).addBox(-1.5F, 3F, -4.5F, 3, 1, 3), PartPose.offsetAndRotation(0F, 5F, -5F, 0.2617994F, 0F, 0F));
        var EyeR = root.addOrReplaceChild("EyeR", CubeListBuilder.create().texOffs(21, 0).addBox(-3.5F, -2F, -4F, 1, 2, 3), PartPose.offsetAndRotation(0F, 5F, -5F, 0.6981317F, 0F, 0F));
        var EyeL = root.addOrReplaceChild("EyeL", CubeListBuilder.create().texOffs(21, 8).addBox(2.5F, -2F, -4F, 1, 2, 3), PartPose.offsetAndRotation(0F, 5F, -5F, 0.6981317F, 0F, 0F));
        var MandibleR1 = root.addOrReplaceChild("MandibleR1", CubeListBuilder.create().texOffs(30, 56).addBox(-3.5F, 1F, -10.5F, 2, 1, 6), PartPose.offsetAndRotation(0F, 5F, -5F, 0.2617994F, 0F, 0F));
        var MandibleR2 = root.addOrReplaceChild("MandibleR2", CubeListBuilder.create().texOffs(31, 75).addBox(-1.5F, 1F, -11.5F, 1, 1, 2), PartPose.offsetAndRotation(0F, 5F, -5F, 0.2617994F, 0F, 0F));
        var MandibleL1 = root.addOrReplaceChild("MandibleL1", CubeListBuilder.create().texOffs(30, 65).addBox(1.5F, 1F, -10.5F, 2, 1, 6), PartPose.offsetAndRotation(0F, 5F, -5F, 0.2617994F, 0F, 0F));
        var MandibleL2 = root.addOrReplaceChild("MandibleL2", CubeListBuilder.create().texOffs(31, 81).addBox(0.5F, 1F, -11.5F, 1, 1, 2), PartPose.offsetAndRotation(0F, 5F, -5F, 0.2617994F, 0F, 0F));
        var Neck = root.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(37, 45).addBox(-1.5F, -1F, -0.5F, 3, 3, 2), PartPose.offsetAndRotation(0F, 5F, -5F, 0.2617994F, 0F, 0F));
        var BodyTop = root.addOrReplaceChild("BodyTop", CubeListBuilder.create().texOffs(0, 23).addBox(-4F, -2F, -1.5F, 8, 1, 4), PartPose.offsetAndRotation(0F, 4F, -3F, 0.2617994F, 0F, 0F));
        var BodyMid = root.addOrReplaceChild("BodyMid", CubeListBuilder.create().texOffs(0, 43).addBox(-6F, 0F, -2.5F, 12, 5, 6), PartPose.offsetAndRotation(0F, 3F, -3F, 0.2617994F, 0F, 0F));
        var BodyMain = root.addOrReplaceChild("BodyMain", CubeListBuilder.create().texOffs(0, 56).addBox(-4.5F, 5F, -1.5F, 9, 7, 5), PartPose.offsetAndRotation(0F, 3F, -3F, 0.2617994F, 0F, 0F));
        var BodyBack = root.addOrReplaceChild("BodyBack", CubeListBuilder.create().texOffs(0, 30).addBox(-4F, 1F, 3.5F, 8, 9, 2), PartPose.offsetAndRotation(0F, 3F, -3F, 0.2617994F, 0F, 0F));
        var ArmR1 = root.addOrReplaceChild("ArmR1", CubeListBuilder.create().texOffs(30, 0).addBox(-4F, -2F, -1.5F, 4, 4, 5), PartPose.offsetAndRotation(-6F, 5F, -3F, -0.2617994F, 0F, 0F));
        var ArmR2 = ArmR1.addOrReplaceChild("ArmR2", CubeListBuilder.create().texOffs(30, 12).addBox(-3.5F, 2F, -0.5F, 3, 4, 3), PartPose.offsetAndRotation(0F, 0F, 0F, 0.2617994F, 0F, 0F));
        var ArmR3 = ArmR1.addOrReplaceChild("ArmR3", CubeListBuilder.create().texOffs(30, 22).addBox(-3F, 1F, 3.5F, 2, 4, 3), PartPose.offsetAndRotation(0F, 0F, 0F, -0.7853982F, 0F, 0F));
        var PincerR1 = ArmR1.addOrReplaceChild("PincerR1", CubeListBuilder.create().texOffs(30, 32).addBox(-4F, 5F, 2.5F, 4, 4, 4), PartPose.offsetAndRotation(0F, 0F, 0F, -0.7853982F, 0F, 0F));
        var PincerROuter = ArmR1.addOrReplaceChild("PincerROuter", CubeListBuilder.create().texOffs(43, 12).addBox(-3.5F, 9F, 3.5F, 1, 3, 2), PartPose.offsetAndRotation(0F, 0F, 0F, -0.7853982F, 0F, 0F));
        var PincerRInner = ArmR1.addOrReplaceChild("PincerRInner", CubeListBuilder.create().texOffs(43, 19).addBox(-1.5F, 9F, 3.5F, 1, 3, 2), PartPose.offsetAndRotation(0F, 0F, 0F, -0.7853982F, 0F, 0F));
        var ArmL1 = root.addOrReplaceChild("ArmL1", CubeListBuilder.create().texOffs(30, 0).addBox(0F, -2F, -1.5F, 4, 4, 5), PartPose.offsetAndRotation(6F, 5F, -3F, -0.2617994F, 0F, 0F));
        var ArmL2 = ArmL1.addOrReplaceChild("ArmL2", CubeListBuilder.create().texOffs(30, 12).addBox(0.5F, 2F, -0.5F, 3, 4, 3), PartPose.offsetAndRotation(0F, 0F, 0F, 0.2617994F, 0F, 0F));
        var ArmL3 = ArmL1.addOrReplaceChild("ArmL3", CubeListBuilder.create().texOffs(30, 22).addBox(1F, 1F, 3.5F, 2, 4, 3), PartPose.offsetAndRotation(0F, 0F, 0F, -0.7853982F, 0F, 0F));
        var PincerL1 = ArmL1.addOrReplaceChild("PincerL1", CubeListBuilder.create().texOffs(30, 32).addBox(0F, 5F, 2.5F, 4, 4, 4), PartPose.offsetAndRotation(0F, 0F, 0F, -0.7853982F, 0F, 0F));
        var PincerLOuter = ArmL1.addOrReplaceChild("PincerLOuter", CubeListBuilder.create().texOffs(43, 19).addBox(2.5F, 9F, 3.5F, 1, 3, 2), PartPose.offsetAndRotation(0F, 0F, 0F, -0.7853982F, 0F, 0F));
        var PincerLInner = ArmL1.addOrReplaceChild("PincerLInner", CubeListBuilder.create().texOffs(43, 12).addBox(0.5F, 9F, 3.5F, 1, 3, 2), PartPose.offsetAndRotation(0F, 0F, 0F, -0.7853982F, 0F, 0F));
        var LegR1 = root.addOrReplaceChild("LegR1", CubeListBuilder.create().texOffs(0, 70).addBox(-1.5F, -1F, -1.5F, 3, 6, 3), PartPose.offsetAndRotation(-3F, 14F, 0F, -1.047198F, 0F, 0F));
        var LegR2 = LegR1.addOrReplaceChild("LegR2", CubeListBuilder.create().texOffs(0, 82).addBox(-1F, 0F, -5.5F, 2, 7, 3), PartPose.offsetAndRotation(0F, 0F, 0F, 1.5707964F, 0F, 0F));
        var FootR = root.addOrReplaceChild("FootR", CubeListBuilder.create().texOffs(0, 102).addBox(-2F, 7F, -2.5F, 4, 3, 4), PartPose.offsetAndRotation(-3F, 14F, 0F, 0F, 0F, 0F));
        var FootRFront = FootR.addOrReplaceChild("FootRFront", CubeListBuilder.create().texOffs(0, 112).addBox(-2F, 8F, -4.5F, 4, 2, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0F));
        var ToeROuter1 = FootR.addOrReplaceChild("ToeROuter1", CubeListBuilder.create().texOffs(0, 118).addBox(-1.5F, 8F, -6.5F, 1, 1, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0F));
        var ToeROuter2 = FootR.addOrReplaceChild("ToeROuter2", CubeListBuilder.create().texOffs(0, 124).addBox(-1.5F, 9.8F, -3.4F, 1, 2, 1), PartPose.offsetAndRotation(0F, 0F, 0F, -0.3490659F, 0F, 0F));
        var ToeRInner1 = FootR.addOrReplaceChild("ToeRInner1", CubeListBuilder.create().texOffs(0, 118).addBox(0.5F, 8F, -6.5F, 1, 1, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0F));
        var ToeRInner2 = FootR.addOrReplaceChild("ToeRInner2", CubeListBuilder.create().texOffs(0, 124).addBox(0.5F, 9.8F, -3.4F, 1, 2, 1), PartPose.offsetAndRotation(0F, 0F, 0F, -0.3490659F, 0F, 0F));
        var FootRBack = FootR.addOrReplaceChild("FootRBack", CubeListBuilder.create().texOffs(0, 96).addBox(-1F, 8F, 1.5F, 2, 2, 1), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0F));
        var ToeRBack1 = FootR.addOrReplaceChild("ToeRBack1", CubeListBuilder.create().texOffs(0, 118).addBox(-0.5F, 8F, -4.5F, 1, 1, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 3.141593F, 0F));
        var ToeRBack2 = FootR.addOrReplaceChild("ToeRBack2", CubeListBuilder.create().texOffs(0, 124).addBox(-0.5F, 9.2F, -1.5F, 1, 2, 1), PartPose.offsetAndRotation(0F, 0F, 0F, -0.3490659F, 3.141593F, 0F));
        var LegL1 = root.addOrReplaceChild("LegL1", CubeListBuilder.create().texOffs(0, 70).addBox(-1.5F, -1F, -1.5F, 3, 6, 3), PartPose.offsetAndRotation(3F, 14F, 0F, -1.047198F, 0F, 0F));
        var LegL2 = LegL1.addOrReplaceChild("LegL2", CubeListBuilder.create().texOffs(0, 82).addBox(-1F, 0F, -5.5F, 2, 7, 3), PartPose.offsetAndRotation(0F, 0F, 0F, 1.5707964F, 0F, 0F));
        var FootL = root.addOrReplaceChild("FootL", CubeListBuilder.create().texOffs(0, 102).addBox(-2F, 7F, -2.5F, 4, 3, 4), PartPose.offsetAndRotation(3F, 14F, 0F, 0F, 0F, 0F));
        var FootLFront = FootL.addOrReplaceChild("FootLFront", CubeListBuilder.create().texOffs(0, 112).addBox(-2F, 8F, -4.5F, 4, 2, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0F));
        var ToeLOuter1 = FootL.addOrReplaceChild("ToeLOuter1", CubeListBuilder.create().texOffs(0, 118).addBox(0.5F, 8F, -6.5F, 1, 1, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0F));
        var ToeLOuter2 = FootL.addOrReplaceChild("ToeLOuter2", CubeListBuilder.create().texOffs(0, 124).addBox(0.5F, 9.8F, -3.4F, 1, 2, 1), PartPose.offsetAndRotation(0F, 0F, 0F, -0.3490659F, 0F, 0F));
        var ToeLInner1 = FootL.addOrReplaceChild("ToeLInner1", CubeListBuilder.create().texOffs(0, 118).addBox(-1.5F, 8F, -6.5F, 1, 1, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0F));
        var ToeLInner2 = FootL.addOrReplaceChild("ToeLInner2", CubeListBuilder.create().texOffs(0, 124).addBox(-1.5F, 9.8F, -3.4F, 1, 2, 1), PartPose.offsetAndRotation(0F, 0F, 0F, -0.3490659F, 0F, 0F));
        var FootLBack = FootL.addOrReplaceChild("FootLBack", CubeListBuilder.create().texOffs(0, 96).addBox(-1F, 8F, 1.5F, 2, 2, 1), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0F));
        var ToeLBack1 = FootL.addOrReplaceChild("ToeLBack1", CubeListBuilder.create().texOffs(0, 118).addBox(-0.5F, 8F, -4.5F, 1, 1, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 3.141593F, 0F));
        var ToeLBack2 = FootL.addOrReplaceChild("ToeLBack2", CubeListBuilder.create().texOffs(0, 124).addBox(-0.5F, 9.2F, -1.5F, 1, 2, 1), PartPose.offsetAndRotation(0F, 0F, 0F, -0.3490659F, 3.141593F, 0F));
        return LayerDefinition.create(mesh, 64, 128);
    }
}
