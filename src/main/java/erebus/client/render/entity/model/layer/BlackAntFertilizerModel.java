package erebus.client.render.entity.model.layer;

import erebus.client.render.entity.renderer.state.BlackAntRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BlackAntFertilizerModel extends EntityModel<BlackAntRenderState> {
    private final ModelPart LeftPack;
    private final ModelPart StrapPack;
    private final ModelPart RightPack;

    private final ModelPart MachineThorax;
    private final ModelPart ConduitR;
    private final ModelPart ConduitL;
    private final ModelPart SprayL;
    private final ModelPart SpayR;
    private final ModelPart SprayLConduit;
    private final ModelPart SprayRConduit;

    public BlackAntFertilizerModel(ModelPart root) {
        super(root);
        LeftPack = root.getChild("LeftPack");
        StrapPack = root.getChild("StrapPack");
        RightPack = root.getChild("RightPack");

        MachineThorax = root.getChild("MachineThorax");
        ConduitR = root.getChild("ConduitR");
        ConduitL = root.getChild("ConduitL");
        SprayL = root.getChild("SprayL");
        SpayR = root.getChild("SpayR");
        SprayLConduit = root.getChild("SprayLConduit");
        SprayRConduit = root.getChild("SprayRConduit");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("LeftPack", CubeListBuilder.create().texOffs(47, 15).addBox(4.0F, 0.5F, -2.5F, 3.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, 8.0F, 0.0F, 0.0F, -0.2443F));
        root.addOrReplaceChild("StrapPack", CubeListBuilder.create().texOffs(42, 52).addBox(-5.0F, -0.5F, -0.5F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 8.0F));
        root.addOrReplaceChild("RightPack", CubeListBuilder.create().texOffs(47, 27).addBox(-7.0F, 0.5F, -2.5F, 3.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, 8.0F, 0.0F, 0.0F, 0.2618F));

        root.addOrReplaceChild("MachineThorax", CubeListBuilder.create().texOffs(44, 85).addBox(-2.5F, -1.5F, -14.0F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 8.0F));
        root.addOrReplaceChild("ConduitR", CubeListBuilder.create().texOffs(18, 77).addBox(-1.0F, -0.5F, -5.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, 8.0F, 0.0F, 1.0472F, 0.0F));
        root.addOrReplaceChild("ConduitL", CubeListBuilder.create().texOffs(18, 77).addBox(-8.0F, -0.5F, -5.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, 8.0F, 0.0F, -1.0647F, 0.0F));
        root.addOrReplaceChild("SprayL", CubeListBuilder.create().texOffs(0, 19).addBox(11.0F, 3.5F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 8.0F));
        root.addOrReplaceChild("SpayR", CubeListBuilder.create().texOffs(0, 19).addBox(-13.0F, 3.5F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 8.0F));
        root.addOrReplaceChild("SprayLConduit", CubeListBuilder.create().texOffs(0, 24).addBox(8.0F, 3.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 8.0F));
        root.addOrReplaceChild("SprayRConduit", CubeListBuilder.create().texOffs(0, 24).addBox(-11.0F, 3.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 8.0F));

        return LayerDefinition.create(mesh, 64, 128);
    }

    @Override
    public void setupAnim(BlackAntRenderState state) {
        super.setupAnim(state);
    }
}
