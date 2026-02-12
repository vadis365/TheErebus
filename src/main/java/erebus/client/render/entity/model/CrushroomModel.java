package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.CrushroomRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class CrushroomModel extends EntityModel<CrushroomRenderState> {

    public ModelPart root;
    private final ModelPart capTop;
    private final ModelPart capBottom;
    private final ModelPart head;
    private final ModelPart chest;
    private final ModelPart rightArm1;
    private final ModelPart rightArm2;
    private final ModelPart rightFist;
    private final ModelPart leftArm1;
    private final ModelPart leftArm2;
    private final ModelPart leftFist;
    private final ModelPart belly;
    private final ModelPart hips;
    private final ModelPart rightThigh;
    private final ModelPart rightAnkle;
    private final ModelPart rightFoot1;
    private final ModelPart rightFoot2;
    private final ModelPart leftThigh;
    private final ModelPart leftAnkle;
    private final ModelPart leftFoot1;
    private final ModelPart leftFoot2;

    public CrushroomModel(ModelPart root) {
        super(root);
        this.root = root;
        this.capTop = root.getChild("capTop");
        this.capBottom = root.getChild("capBottom");
        this.head = root.getChild("head");
        this.chest = root.getChild("chest");
        this.rightArm1 = root.getChild("rightArm1");
        this.rightArm2 = rightArm1.getChild("rightArm2");
        this.rightFist = rightArm1.getChild("rightFist");
        this.leftArm1 = root.getChild("leftArm1");
        this.leftArm2 = leftArm1.getChild("leftArm2");
        this.leftFist = leftArm1.getChild("leftFist");
        this.belly = root.getChild("belly");
        this.hips = root.getChild("hips");
        this.rightThigh = root.getChild("rightThigh");
        this.rightAnkle = rightThigh.getChild("rightAnkle");
        this.rightFoot1 = rightThigh.getChild("rightFoot1");
        this.rightFoot2 = rightThigh.getChild("rightFoot2");
        this.leftThigh = root.getChild("leftThigh");
        this.leftAnkle = leftThigh.getChild("leftAnkle");
        this.leftFoot1 = leftThigh.getChild("leftFoot1");
        this.leftFoot2 = leftThigh.getChild("leftFoot2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("capTop", CubeListBuilder.create().texOffs(39, 0).addBox(-6.0F, -8.0F, -11.0F, 12.0F, 2.0F, 12.0F), PartPose.offset(0.0F, 1.0F, 0.0F));
        part.addOrReplaceChild("capBottom", CubeListBuilder.create().texOffs(30, 15).addBox(-8.0F, -7.0F, -13.0F, 16.0F, 3.0F, 16.0F), PartPose.offset(0.0F, 2.0F, 0.0F));
        part.addOrReplaceChild("head", CubeListBuilder.create().texOffs(46, 35).addBox(-4.0F, -4.0F, -9.0F, 8.0F, 10.0F, 8.0F), PartPose.offset(0.0F, 2.0F, 0.0F));
        part.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(34, 54).addBox(-9.0F, -14.0F, -4.0F, 18.0F, 8.0F, 10.0F), PartPose.offsetAndRotation(0.0F, 10.0F, 7.0F, 1.047198F, 0.0F, 0.0F));

        PartDefinition rightArm1 = part.addOrReplaceChild("rightArm1", CubeListBuilder.create().texOffs(7, 43).addBox(-5.0F, -2.0F, -2.5F, 5.0F, 10.0F, 5.0F), PartPose.offset(-9.0F, 2.0F, -2.0F));
        rightArm1.addOrReplaceChild("rightArm2", CubeListBuilder.create().texOffs(9, 59).addBox(-4.5F, 5.0F, 2.0F, 4.0F, 7.0F, 4.0F), PartPose.rotation(-0.7504916F, 0.0F, 0.0F));
        rightArm1.addOrReplaceChild("rightFist", CubeListBuilder.create().texOffs(5, 71).addBox(-4.5F, 12.0F, 1.0F, 6.0F, 6.0F, 6.0F), PartPose.rotation(-0.7504916F, 0.0F, 0.0F));

        PartDefinition leftArm1 = part.addOrReplaceChild("leftArm1", CubeListBuilder.create().texOffs(101, 43).addBox(0.0F, -2.0F, -2.5F, 5.0F, 10.0F, 5.0F), PartPose.offset(9.0F, 2.0F, -2.0F));
        leftArm1.addOrReplaceChild("leftArm2", CubeListBuilder.create().texOffs(103, 59).addBox(0.5F, 5.0F, 2.0F, 4.0F, 7.0F, 4.0F), PartPose.rotation(-0.7504916F, 0.0F, 0.0F));
        leftArm1.addOrReplaceChild("leftFist", CubeListBuilder.create().texOffs(99, 71).addBox(-1.5F, 12.0F, 1.0F, 6.0F, 6.0F, 6.0F), PartPose.rotation(-0.7504916F, 0.0F, 0.0F));

        part.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(44, 73).addBox(-5.0F, -6.0F, -3.0F, 10.0F, 7.0F, 8.0F), PartPose.offsetAndRotation(0.0F, 10.0F, 7.0F, 1.047198F, 0.0F, 0.0F));
        part.addOrReplaceChild("hips", CubeListBuilder.create().texOffs(42, 89).addBox(-6.0F, -4.0F, -4.0F, 12.0F, 8.0F, 9.0F), PartPose.offset(0.0F, 10.0F, 7.0F));

        PartDefinition rightThigh = part.addOrReplaceChild("rightThigh", CubeListBuilder.create().texOffs(7, 84).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 7.0F, 5.0F), PartPose.offsetAndRotation(-5.0F, 11.0F, 7.0F, 0.0F, 0.0F, 0.7853982F));
        rightThigh.addOrReplaceChild("rightAnkle", CubeListBuilder.create().texOffs(9, 97).addBox(-5.5F, 3.0F, -2.0F, 4.0F, 5.0F, 4.0F), PartPose.rotation(0.0F, 0.0F, -0.7853982F));
        rightThigh.addOrReplaceChild("rightFoot1", CubeListBuilder.create().texOffs(5, 107).addBox(-6.5F, 8.0F, -3.0F, 6.0F, 2.0F, 6.0F), PartPose.rotation(0.0F, 0.0F, -0.7853982F));
        rightThigh.addOrReplaceChild("rightFoot2", CubeListBuilder.create().texOffs(1, 116).addBox(-7.5F, 10.0F, -4.0F, 8.0F, 3.0F, 8.0F), PartPose.rotation(0.0F, 0.0F, -0.7853982F));

        PartDefinition leftThigh = part.addOrReplaceChild("leftThigh", CubeListBuilder.create().texOffs(101, 84).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 7.0F, 5.0F), PartPose.offsetAndRotation(5.0F, 11.0F, 7.0F, 0.0F, 0.0F, -0.7853982F));
        leftThigh.addOrReplaceChild("leftAnkle", CubeListBuilder.create().texOffs(103, 97).addBox(1.5F, 3.0F, -2.0F, 4.0F, 5.0F, 4.0F), PartPose.rotation(0.0F, 0.0F, 0.7853982F));
        leftThigh.addOrReplaceChild("leftFoot1", CubeListBuilder.create().texOffs(99, 107).addBox(0.5F, 8.0F, -3.0F, 6.0F, 2.0F, 6.0F), PartPose.rotation(0.0F, 0.0F, 0.7853982F));
        leftThigh.addOrReplaceChild("leftFoot2", CubeListBuilder.create().texOffs(95, 116).addBox(-0.5F, 10.0F, -4.0F, 8.0F, 3.0F, 8.0F), PartPose.rotation(0.0F, 0.0F, 0.7853982F));

        return LayerDefinition.create(mesh, 128, 128);
    }
}
