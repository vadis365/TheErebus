package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.PondSkaterRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class PondSkaterModel extends EntityModel<PondSkaterRenderState> {
    public ModelPart root;
    private final ModelPart proboscis1, proboscis2, proboscis3;
    private final ModelPart leftAnt1, leftAnt2, rightAnt1, rightAnt2;
    private final ModelPart leftEye, rightEye, head;
    private final ModelPart leftLegFront1, leftLegFront2, rightLegFront1, rightLegFront2;
    private final ModelPart bodyLower, bodyTop;
    private final ModelPart leftLegMid1, leftLegMid2, leftLegMid3, leftLegMid4;
    private final ModelPart rightLegMid1, rightLegMid2, rightLegMid3, rightLegMid4;
    private final ModelPart leftLegBack1, leftLegBack2, leftLegBack3, leftLegBack4;
    private final ModelPart rightLegBack1, rightLegBack2, rightLegBack3, rightLegBack4;
    private final ModelPart bum1, bum2;

    public PondSkaterModel(ModelPart root) {
        super(root);
        this.root = root;
        this.proboscis1 = root.getChild("proboscis1");
        this.proboscis2 = root.getChild("proboscis2");
        this.proboscis3 = root.getChild("proboscis3");
        this.leftAnt1 = root.getChild("leftAnt1");
        this.leftAnt2 = root.getChild("leftAnt2");
        this.rightAnt1 = root.getChild("rightAnt1");
        this.rightAnt2 = root.getChild("rightAnt2");
        this.leftEye = root.getChild("leftEye");
        this.rightEye = root.getChild("rightEye");
        this.head = root.getChild("head");
        this.leftLegFront1 = root.getChild("leftLegFront1");
        this.leftLegFront2 = root.getChild("leftLegFront2");
        this.rightLegFront1 = root.getChild("rightLegFront1");
        this.rightLegFront2 = root.getChild("rightLegFront2");
        this.bodyLower = root.getChild("bodyLower");
        this.bodyTop = root.getChild("bodyTop");
        this.leftLegMid1 = root.getChild("leftLegMid1");
        this.leftLegMid2 = root.getChild("leftLegMid2");
        this.leftLegMid3 = root.getChild("leftLegMid3");
        this.leftLegMid4 = root.getChild("leftLegMid4");
        this.rightLegMid1 = root.getChild("rightLegMid1");
        this.rightLegMid2 = root.getChild("rightLegMid2");
        this.rightLegMid3 = root.getChild("rightLegMid3");
        this.rightLegMid4 = root.getChild("rightLegMid4");
        this.leftLegBack1 = root.getChild("leftLegBack1");
        this.leftLegBack2 = root.getChild("leftLegBack2");
        this.leftLegBack3 = root.getChild("leftLegBack3");
        this.leftLegBack4 = root.getChild("leftLegBack4");
        this.rightLegBack1 = root.getChild("rightLegBack1");
        this.rightLegBack2 = root.getChild("rightLegBack2");
        this.rightLegBack3 = root.getChild("rightLegBack3");
        this.rightLegBack4 = root.getChild("rightLegBack4");
        this.bum1 = root.getChild("bum1");
        this.bum2 = root.getChild("bum2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("proboscis1", CubeListBuilder.create().texOffs(37, 0).addBox(-1.5F, -2.5F, -6F, 3, 3, 3), PartPose.offsetAndRotation(0F, 16F, -4F, 0.4014257F, 0F, 0F));
        root.addOrReplaceChild("proboscis2", CubeListBuilder.create().texOffs(28, 5).addBox(-1F, -3.5F, -7F, 2, 2, 2), PartPose.offsetAndRotation(0F, 16F, -4F, 0.7679449F, 0F, 0F));
        root.addOrReplaceChild("proboscis3", CubeListBuilder.create().texOffs(28, 0).addBox(-0.5F, -5.5F, -7F, 1, 1, 3), PartPose.offsetAndRotation(0F, 16F, -4F, 1.291544F, 0F, 0F));
        root.addOrReplaceChild("leftAnt1", CubeListBuilder.create().texOffs(3, 13).addBox(-1F, -0.5F, -17F, 1, 1, 12), PartPose.offsetAndRotation(0F, 16F, -4F, 0F, -0.3490659F, 0F));
        root.addOrReplaceChild("leftAnt2", CubeListBuilder.create().texOffs(3, 13).addBox(-6.7F, -0.5F, -27.6F, 1, 1, 12), PartPose.offsetAndRotation(0F, 16F, -4F, 0F, -0.6981317F, 0F));
        root.addOrReplaceChild("rightAnt1", CubeListBuilder.create().texOffs(35, 13).addBox(0F, -0.5F, -17F, 1, 1, 12), PartPose.offsetAndRotation(0F, 16F, -4F, 0F, 0.3490659F, 0F));
        root.addOrReplaceChild("rightAnt2", CubeListBuilder.create().texOffs(35, 13).addBox(5.7F, -0.5F, -27.6F, 1, 1, 12), PartPose.offsetAndRotation(0F, 16F, -4F, 0F, 0.6981317F, 0F));
        root.addOrReplaceChild("leftEye", CubeListBuilder.create().texOffs(10, 27).addBox(1F, -2.5F, -3F, 3, 3, 3), PartPose.offset(0F, 16F, -4F));
        root.addOrReplaceChild("rightEye", CubeListBuilder.create().texOffs(42, 27).addBox(-4F, -2.5F, -3F, 3, 3, 3), PartPose.offset(0F, 16F, -4F));
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(23, 27).addBox(-2F, -2F, -4F, 4, 4, 5), PartPose.offset(0F, 16F, -4F));
        root.addOrReplaceChild("leftLegFront1", CubeListBuilder.create().texOffs(0, 37).addBox(-1.5F, -1.5F, 0F, 2, 2, 15), PartPose.offsetAndRotation(2F, 19F, -2F, -0.1919862F, 2.96706F, 0F));
        root.addOrReplaceChild("leftLegFront2", CubeListBuilder.create().texOffs(35, 44).addBox(-0.5F, -0.5F, 0F, 1, 1, 9), PartPose.offsetAndRotation(5F, 21.5F, -16.5F, -0.2094395F, 1.745329F, 0F));
        root.addOrReplaceChild("rightLegFront1", CubeListBuilder.create().texOffs(0, 37).addBox(-1.5F, -1.5F, 0F, 2, 2, 15), PartPose.offsetAndRotation(-3F, 19F, -2F, -0.1919862F, -2.96706F, 0F));
        root.addOrReplaceChild("rightLegFront2", CubeListBuilder.create().texOffs(35, 44).addBox(-0.5F, -0.5F, 0F, 1, 1, 9), PartPose.offsetAndRotation(-5F, 21.5F, -16.5F, -0.2094395F, -1.745329F, 0F));
        root.addOrReplaceChild("bodyLower", CubeListBuilder.create().texOffs(29, 62).addBox(-5F, 0F, 0.5F, 10, 4, 6), PartPose.offset(0F, 16F, -4F));
        root.addOrReplaceChild("bodyTop", CubeListBuilder.create().texOffs(0, 55).addBox(-2.5F, -3F, 0F, 5, 5, 18), PartPose.offset(0F, 16F, -4F));
        root.addOrReplaceChild("leftLegMid1", CubeListBuilder.create().texOffs(30, 89).addBox(-1.5F, -1.5F, 0F, 3, 3, 9), PartPose.offsetAndRotation(3F, 18F, -2F, -0.1919862F, 0.1570796F, 0F));
        root.addOrReplaceChild("leftLegMid2", CubeListBuilder.create().texOffs(0, 95).addBox(0.5F, -0.5F, 7.5F, 3, 3, 3), PartPose.offsetAndRotation(3F, 18F, -2F, -0.1919862F, 0.1570796F, 0F));
        root.addOrReplaceChild("leftLegMid3", CubeListBuilder.create().texOffs(0, 79).addBox(-1F, -1F, 0F, 2, 2, 25), PartPose.offsetAndRotation(6.5F, 21F, 6F, -3.071779F, -0.7853982F, 0F));
        root.addOrReplaceChild("leftLegMid4", CubeListBuilder.create().texOffs(0, 107).addBox(14F, 0.5F, 20F, 1, 1, 20), PartPose.offsetAndRotation(6.5F, 21F, 6F, -0.0523599F, 1.745329F, 0F));
        root.addOrReplaceChild("rightLegMid1", CubeListBuilder.create().texOffs(30, 89).addBox(-1.5F, -1.5F, 0F, 3, 3, 9), PartPose.offsetAndRotation(-3F, 18F, -2F, -0.1919862F, -0.1570796F, 0F));
        root.addOrReplaceChild("rightLegMid2", CubeListBuilder.create().texOffs(0, 95).addBox(-4.5F, -0.5F, 7.5F, 3, 3, 3), PartPose.offsetAndRotation(-2F, 18F, -2F, -0.1919862F, -0.1570796F, 0F));
        root.addOrReplaceChild("rightLegMid3", CubeListBuilder.create().texOffs(0, 79).addBox(-1F, -1F, 0F, 2, 2, 25), PartPose.offsetAndRotation(-6F, 21F, 6F, -3.071779F, 0.7853982F, 0F));
        root.addOrReplaceChild("rightLegMid4", CubeListBuilder.create().texOffs(0, 107).addBox(-15F, 0.5F, 20F, 1, 1, 20), PartPose.offsetAndRotation(-6F, 21F, 6F, -0.0523599F, -1.745329F, 0F));
        root.addOrReplaceChild("leftLegBack1", CubeListBuilder.create().texOffs(0, 85).addBox(-1.5F, -1.5F, 0F, 3, 3, 6), PartPose.offsetAndRotation(2F, 18F, 7F, -0.3316126F, 0.1570796F, 0F));
        root.addOrReplaceChild("leftLegBack2", CubeListBuilder.create().texOffs(0, 95).addBox(-1F, -1F, 0F, 3, 3, 3), PartPose.offsetAndRotation(3F, 20F, 12F, -0.1919862F, 0.1570796F, 0F));
        root.addOrReplaceChild("leftLegBack3", CubeListBuilder.create().texOffs(0, 79).addBox(-1F, -1F, 0F, 2, 2, 25), PartPose.offsetAndRotation(3F, 21F, 13F, -3.071779F, -2.356194F, 0F));
        root.addOrReplaceChild("leftLegBack4", CubeListBuilder.create().texOffs(0, 107).addBox(10.5F, 0.5F, 22F, 1, 1, 20), PartPose.offsetAndRotation(3F, 21F, 13F, -0.0523599F, 0.3490659F, 0F));
        root.addOrReplaceChild("rightLegBack1", CubeListBuilder.create().texOffs(0, 85).addBox(-1.5F, -1.5F, 0F, 3, 3, 6), PartPose.offsetAndRotation(-2F, 18F, 7F, -0.3316126F, -0.1570796F, 0F));
        root.addOrReplaceChild("rightLegBack2", CubeListBuilder.create().texOffs(0, 95).addBox(-2F, -1F, 0F, 3, 3, 3), PartPose.offsetAndRotation(-3F, 20F, 12F, -0.1919862F, -0.1570796F, 0F));
        root.addOrReplaceChild("rightLegBack3", CubeListBuilder.create().texOffs(0, 79).addBox(-1F, -1F, 0F, 2, 2, 25), PartPose.offsetAndRotation(-3F, 21F, 13F, -3.071779F, 2.356194F, 0F));
        root.addOrReplaceChild("rightLegBack4", CubeListBuilder.create().texOffs(0, 107).addBox(-11.5F, 0.5F, 22F, 1, 1, 20), PartPose.offsetAndRotation(-3F, 21F, 13F, -0.0523599F, -0.3490659F, 0F));
        root.addOrReplaceChild("bum1", CubeListBuilder.create().texOffs(30, 110).addBox(-2F, -2F, 0F, 4, 4, 10), PartPose.offsetAndRotation(0F, 16F, 13F, -0.1745329F, 0F, 0F));
        root.addOrReplaceChild("bum2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1.5F, -1F, 3, 3, 4), PartPose.offsetAndRotation(0F, 18F, 23F, -0.1745329F, 0F, 0F));

        return LayerDefinition.create(mesh, 128, 128);
    }
}
