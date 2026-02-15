package erebus.client.render.entity.model.layer.ant;

import erebus.client.render.entity.renderer.state.BlackAntRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BlackAntPlanterModel extends EntityModel<BlackAntRenderState> {
    private final ModelPart LeftPack;
    private final ModelPart StrapPack;
    private final ModelPart RightPack;

    private final ModelPart HatTop;
    private final ModelPart HatBrimF;
    private final ModelPart HatBrimL;
    private final ModelPart HatBrimMain;
    private final ModelPart HatBrimR;

    public BlackAntPlanterModel(ModelPart root) {
        super(root);
        LeftPack = root.getChild("LeftPack");
        StrapPack = root.getChild("StrapPack");
        RightPack = root.getChild("RightPack");

        HatTop = root.getChild("HatTop");
        HatBrimF = root.getChild("HatBrimF");
        HatBrimL = root.getChild("HatBrimL");
        HatBrimMain = root.getChild("HatBrimMain");
        HatBrimR = root.getChild("HatBrimR");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("LeftPack", CubeListBuilder.create().texOffs(47, 15).addBox(4.0F, 0.5F, -2.5F, 3.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, 8.0F, 0.0F, 0.0F, -0.2443F));
        root.addOrReplaceChild("StrapPack", CubeListBuilder.create().texOffs(42, 52).addBox(-5.0F, -0.5F, -0.5F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 8.0F));
        root.addOrReplaceChild("RightPack", CubeListBuilder.create().texOffs(47, 27).addBox(-7.0F, 0.5F, -2.5F, 3.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, 8.0F, 0.0F, 0.0F, 0.2618F));

        root.addOrReplaceChild("HatTop", CubeListBuilder.create().texOffs(44, 77).addBox(-2.5F, -4.0F, -5.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -9.0F, -0.4363F, 0.0F, 0.0F));
        root.addOrReplaceChild("HatBrimF", CubeListBuilder.create().texOffs(0, 40).addBox(-3.0F, -2.0F, -8.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -9.0F, -0.4363F, 0.0F, 0.0F));
        root.addOrReplaceChild("HatBrimL", CubeListBuilder.create().texOffs(0, 27).addBox(4.0F, -2.0F, -6.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -9.0F, -0.4363F, 0.0F, 0.0F));
        root.addOrReplaceChild("HatBrimMain", CubeListBuilder.create().texOffs(0, 52).addBox(-4.0F, -2.0F, -7.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -9.0F, -0.4363F, 0.0F, 0.0F));
        root.addOrReplaceChild("HatBrimR", CubeListBuilder.create().texOffs(0, 43).addBox(-5.0F, -2.0F, -6.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -9.0F, -0.4363F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 128);
    }

    @Override
    public void setupAnim(BlackAntRenderState state) {
        super.setupAnim(state);
        HatTop.yRot = HatBrimF.yRot = HatBrimL.yRot = HatBrimMain.yRot = HatBrimR.yRot = state.yRot / (180F / (float) Math.PI);
        HatTop.xRot = HatBrimF.xRot = HatBrimL.xRot = HatBrimMain.xRot = HatBrimR.xRot = state.xRot / (180F / (float) Math.PI) - 1F + 0.5F;
    }
}
