package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.BogMawRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BogMawModel extends EntityModel<BogMawRenderState> {
    public ModelPart root;
    private final ModelPart roots;
    private final ModelPart main;
    private final ModelPart rim1;
    private final ModelPart rim2;
    private final ModelPart rim3;
    private final ModelPart rim4;
    private final ModelPart jawmain1;
    private final ModelPart jawtip1;
    private final ModelPart jawmain2;
    private final ModelPart jawtip2;
    private final ModelPart jawmain3;
    private final ModelPart jawtip3;
    private final ModelPart jawmain4;
    private final ModelPart jawtip4;

    public BogMawModel(ModelPart root) {
        super(root);
        this.root = root;
        roots = root.getChild("roots");
        main = roots.getChild("main");
        rim1 = main.getChild("rim1");
        rim2 = main.getChild("rim2");
        rim3 = main.getChild("rim3");
        rim4 = main.getChild("rim4");
        jawmain1 = main.getChild("jawmain1");
        jawtip1 = jawmain1.getChild("jawtip1");
        jawmain2 = main.getChild("jawmain2");
        jawtip2 = jawmain2.getChild("jawtip2");
        jawmain3 = main.getChild("jawmain3");
        jawtip3 = jawmain3.getChild("jawtip3");
        jawmain4 = main.getChild("jawmain4");
        jawtip4 = jawmain4.getChild("jawtip4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        PartDefinition roots = part.addOrReplaceChild("roots", CubeListBuilder.create()
                .texOffs(-26, 0)
                .addBox(0F, 0F, 0F, 25, 0, 25, CubeDeformation.NONE), PartPose.offset(-12.5F, 24F, -12.5F));

        PartDefinition main = roots.addOrReplaceChild("main", CubeListBuilder.create()
                .texOffs(50, 0)
                .addBox(0F, 0F, 0F, 6, 2, 6, CubeDeformation.NONE), PartPose.offset(9.5F, -2F, 9.5F));

        main.addOrReplaceChild("rim1", CubeListBuilder.create()
                .texOffs(50, 8)
                .addBox(0F, 0F, 0F, 6, 2, 1, CubeDeformation.NONE), PartPose.offset(0F, -2F, 0F));

        main.addOrReplaceChild("rim2", CubeListBuilder.create()
                .texOffs(50, 11)
                .addBox(0F, 0F, 0F, 1, 2, 4, CubeDeformation.NONE), PartPose.offset(0F, -2F, 1F));

        main.addOrReplaceChild("rim3", CubeListBuilder.create()
                .texOffs(50, 8)
                .addBox(0F, 0F, 0F, 6, 2, 1, CubeDeformation.NONE), PartPose.offset(0F, -2F, 5F));

        main.addOrReplaceChild("rim4", CubeListBuilder.create()
                .texOffs(50, 11)
                .addBox(0F, 0F, 0F, 1, 2, 4, CubeDeformation.NONE), PartPose.offset(5F, -2F, 1F));

        PartDefinition jawmain1 = main.addOrReplaceChild("jawmain1", CubeListBuilder.create()
                .texOffs(50, 17)
                .addBox(-2F, 0F, 0F, 4, 1, 9, CubeDeformation.NONE), PartPose.offsetAndRotation(3F, -0.5F, 6F, -0.122173F, 0F, 0F));

        jawmain1.addOrReplaceChild("jawtip1", CubeListBuilder.create()
                .texOffs(50, 27)
                .addBox(-1F, 0F, 9F, 2, 1, 1, CubeDeformation.NONE), PartPose.rotation(-0.122173F, 0F, 0F));

        PartDefinition jawmain2 = main.addOrReplaceChild("jawmain2", CubeListBuilder.create()
                .texOffs(50, 17)
                .addBox(-2F, 0F, -9F, 4, 1, 9, CubeDeformation.NONE), PartPose.offsetAndRotation(3F, -0.5F, 0F, 0.122173F, 0F, 0F));

        jawmain2.addOrReplaceChild("jawtip2", CubeListBuilder.create()
                .texOffs(50, 27)
                .addBox(-1F, 0F, -10F, 2, 1, 1, CubeDeformation.NONE), PartPose.rotation(0.122173F, 0F, 0F));

        PartDefinition jawmain3 = main.addOrReplaceChild("jawmain3", CubeListBuilder.create()
                .texOffs(50, 17)
                .addBox(-2F, 0F, -9F, 4, 1, 9, CubeDeformation.NONE), PartPose.offsetAndRotation(6F, -0.5F, 3F, 0.122173F, -1.570796F, 0F));

        jawmain3.addOrReplaceChild("jawtip3", CubeListBuilder.create()
                .texOffs(50, 27)
                .addBox(-1F, 0F, 9F, 2, 1, 1, CubeDeformation.NONE), PartPose.rotation(-0.122173F, 1.570796F, 0F));

        PartDefinition jawmain4 = main.addOrReplaceChild("jawmain4", CubeListBuilder.create()
                .texOffs(50, 17)
                .addBox(-2F, 0F, 0F, 4, 1, 9, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, -0.5F, 3F, -0.122173F, -1.570796F, 0F));

        jawmain4.addOrReplaceChild("jawtip4", CubeListBuilder.create()
                .texOffs(50, 27)
                .addBox(-1F, 0F, 9F, 2, 1, 1, CubeDeformation.NONE), PartPose.rotation(-0.122173F, -1.570796F, 0F));

        return LayerDefinition.create(mesh, 128, 32);
    }
}
