package erebus.client.render.item.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class WebSlingerModel extends Model {
    public final ModelPart ShapeA;
    public final ModelPart ShapeB;
    public final ModelPart ShapeC;
    public final ModelPart ShapeD;
    public final ModelPart ShapeE;
    public final ModelPart ShapeF;
    public final ModelPart ShapeG;
    public final ModelPart ShapeH;
    public final ModelPart ShapeI;
    public final ModelPart ShapeJ;
    public final ModelPart ShapeK;
    public final ModelPart ShapeL;
    public final ModelPart ShapeM;
    public final ModelPart ShapeN;
    public final ModelPart ShapeO;
    public final ModelPart ShapeP;
    public final ModelPart ShapeQ;
    public final ModelPart ShapeR;
    public final ModelPart ShapeS;
    public final ModelPart ShapeT;
    public final ModelPart ShapeU;
    public final ModelPart ShapeV;
    public final ModelPart ShapeW;
    public final ModelPart ShapeX;
    public final ModelPart Back;
    public final ModelPart BarrelDeco;
    public final ModelPart Barrel;
    public final ModelPart Grip;
    public final ModelPart Greeble1;
    public final ModelPart Greeble2;
    public final ModelPart Greeble3;

    public WebSlingerModel(ModelPart root) {
        super(RenderType::entitySolid);
        ShapeA = root.getChild("ShapeA");
        ShapeB = root.getChild("ShapeB");
        ShapeC = root.getChild("ShapeC");
        ShapeD = root.getChild("ShapeD");
        ShapeE = root.getChild("ShapeE");
        ShapeF = root.getChild("ShapeF");
        ShapeG = root.getChild("ShapeG");
        ShapeH = root.getChild("ShapeH");
        ShapeI = root.getChild("ShapeI");
        ShapeJ = root.getChild("ShapeJ");
        ShapeK = root.getChild("ShapeK");
        ShapeL = root.getChild("ShapeL");
        ShapeM = root.getChild("ShapeM");
        ShapeN = root.getChild("ShapeN");
        ShapeO = root.getChild("ShapeO");
        ShapeP = root.getChild("ShapeP");
        ShapeQ = root.getChild("ShapeQ");
        ShapeR = root.getChild("ShapeR");
        ShapeS = root.getChild("ShapeS");
        ShapeT = root.getChild("ShapeT");
        ShapeU = root.getChild("ShapeU");
        ShapeV = root.getChild("ShapeV");
        ShapeW = root.getChild("ShapeW");
        ShapeX = root.getChild("ShapeX");
        Back = root.getChild("Back");
        BarrelDeco = root.getChild("BarrelDeco");
        Barrel = root.getChild("Barrel");
        Grip = root.getChild("Grip");
        Greeble1 = root.getChild("Greeble1");
        Greeble2 = root.getChild("Greeble2");
        Greeble3 = root.getChild("Greeble3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("ShapeA", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2.5F, -4.466667F, 2.5F, 5, 5, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8, -9, 0, 0, -3.141593F));

        partdefinition.addOrReplaceChild("ShapeB", CubeListBuilder.create()
                        .texOffs(36, 9)
                        .addBox(-0.5F, -13.5F, -1, 1, 13, 2, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8.1F, 0, 0.9075712F, 0, -0.5235988F));

        partdefinition.addOrReplaceChild("ShapeC", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2.5F, -4.466667F, 2.5F, 5, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8, -9, 0, 0, 2.094395F));

        partdefinition.addOrReplaceChild("ShapeD", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2.5F, -4.466667F, 2.5F, 5, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8, -9, 0, 0, 1.047198F));

        partdefinition.addOrReplaceChild("ShapeE", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.5F, -6.466667F, -0.5F, 7, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8, -9, 0, 0, -1.047198F));

        partdefinition.addOrReplaceChild("ShapeF", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2.5F, -4.466667F, 2.5F, 5, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8, -9, 0, 0, -1.047198F));

        partdefinition.addOrReplaceChild("ShapeG", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2.5F, -4.466667F, 2.5F, 5, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8, -9, 0, 0, -2.094395F));

        partdefinition.addOrReplaceChild("ShapeH", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-1.5F, -2.466667F, 5.5F, 3, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8, -9, 0, 0, 3.141593F));

        partdefinition.addOrReplaceChild("ShapeI", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.5F, -6.466667F, -0.5F, 7, 1, 1, new CubeDeformation(0)),
                PartPose.offset(0, 8, -9));

        partdefinition.addOrReplaceChild("ShapeJ", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.5F, -6.466667F, -0.5F, 7, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8, -9, 0, 0, 3.141593F));

        partdefinition.addOrReplaceChild("ShapeK", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.5F, -6.466667F, -0.5F, 7, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8, -9, 0, 0, 1.047198F));

        partdefinition.addOrReplaceChild("ShapeL", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.5F, -6.466667F, -0.5F, 7, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8, -9, 0, 0, 2.094395F));

        partdefinition.addOrReplaceChild("ShapeM", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.5F, -6.466667F, -0.5F, 7, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8, -9, 0, 0, -2.094395F));

        partdefinition.addOrReplaceChild("ShapeN", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2.5F, -4.466667F, 2.5F, 5, 1, 1, new CubeDeformation(0)),
                PartPose.offset(0, 8, -9));

        partdefinition.addOrReplaceChild("ShapeO", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-1.5F, -2.466667F, 5.5F, 3, 1, 1, new CubeDeformation(0)),
                PartPose.offset(0, 8, -9));

        partdefinition.addOrReplaceChild("ShapeP", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-1.5F, -2.466667F, 5.5F, 3, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8, -9, 0, 0, 1.047198F));

        partdefinition.addOrReplaceChild("ShapeQ", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-1.5F, -2.466667F, 5.5F, 3, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8, -9, 0, 0, 2.094395F));

        partdefinition.addOrReplaceChild("ShapeR", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-1.5F, -2.466667F, 5.5F, 3, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8, -9, 0, 0, -1.047198F));

        partdefinition.addOrReplaceChild("ShapeS", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-1.5F, -2.466667F, 5.5F, 3, 1, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8, -9, 0, 0, -2.094395F));

        partdefinition.addOrReplaceChild("ShapeT", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(0, -0.5F, -1, 13, 1, 2, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8.1F, 0, 0, 2.251475F, 0));

        partdefinition.addOrReplaceChild("ShapeU", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(0, -0.4666667F, -1, 13, 1, 2, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8F, 0, 0, 0.9075712F, 0));

        partdefinition.addOrReplaceChild("ShapeV", CubeListBuilder.create()
                        .texOffs(36, 9)
                        .addBox(-0.5F, -13.5F, -1, 1, 13, 2, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8.1F, 0, 0.907512F, 0, 0.5235988F));

        partdefinition.addOrReplaceChild("ShapeW", CubeListBuilder.create()
                        .texOffs(36, 9)
                        .addBox(-0.5F, -13.5F, -1F, 1, 13, 2, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8.1F, 0, 2.251475F, 0F, 0.5235988F));

        partdefinition.addOrReplaceChild("ShapeX", CubeListBuilder.create()
                        .texOffs(36, 9)
                        .addBox(-0.5F, -13.5F, -1F, 1, 13, 2, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8.1F, 0, 2.251475F, 0F, -0.5585054F));

        partdefinition.addOrReplaceChild("Back", CubeListBuilder.create()
                        .texOffs(0, 16)
                        .addBox(-2F, -2F, 7F, 4, 3, 6, new CubeDeformation(0)),
                PartPose.offset(0, 8, 0));

        partdefinition.addOrReplaceChild("BarrelDeco", CubeListBuilder.create()
                        .texOffs(36, 0)
                        .addBox(-2F, -0.5F, -1F, 4, 1, 7, new CubeDeformation(0)),
                PartPose.offset(0, 8, 0));

        partdefinition.addOrReplaceChild("Barrel", CubeListBuilder.create()
                        .texOffs(15, 4)
                        .addBox(-1F, -1.5F, 0F, 2, 3, 8, new CubeDeformation(0)),
                PartPose.offset(0, 8, 0));

        partdefinition.addOrReplaceChild("Grip", CubeListBuilder.create()
                        .texOffs(0, 4)
                        .addBox(-1.5F, 1.5F, 7F, 3, 7, 4, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 8, 0, 0.1396263F, 0F, 0F));

        partdefinition.addOrReplaceChild("Greeble1", CubeListBuilder.create()
                        .texOffs(32, 0)
                        .addBox(-0.5F, -3F, -0.5F, 1, 3, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 7.5F, 12, -0.5585054F, 0F, 0F));

        partdefinition.addOrReplaceChild("Greeble2", CubeListBuilder.create()
                        .texOffs(32, 0)
                        .addBox(-0.5F, -3F, -0.5F, 1, 3, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(1.5F, 7.5F, 12F, -0.5585054F, 0.5585054F, 0F));

        partdefinition.addOrReplaceChild("Greeble3", CubeListBuilder.create()
                        .texOffs(32, 0)
                        .addBox(-0.5F, -3F, -0.5F, 1, 3, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(-1.5F, 7.5F, 12F, -0.5585054F, -0.5585054F, 0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        ShapeA.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeB.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeC.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeD.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeE.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeF.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeG.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeH.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeI.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeJ.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeK.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeL.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeM.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeN.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeO.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeP.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeQ.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeR.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeS.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeT.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeU.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeV.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeW.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        ShapeX.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        Back.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        BarrelDeco.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        Barrel.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        Grip.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        Greeble1.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        Greeble2.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        Greeble3.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
