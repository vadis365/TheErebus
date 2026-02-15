package erebus.client.render.entity.model.layer.ant;

import erebus.client.render.entity.renderer.state.BlackAntRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BlackAntHarvesterModel extends EntityModel<BlackAntRenderState> {
    private final ModelPart RightShears;
    private final ModelPart LeftShears;

    public BlackAntHarvesterModel(ModelPart root) {
        super(root);
        RightShears = root.getChild("RightShears");
        LeftShears = root.getChild("LeftShears");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("RightShears", CubeListBuilder.create().texOffs(48, 55).addBox(-3.5F, -3.0F, -10.5F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -10.0F, 0.8727F, 0.0F, 0.0F));
        root.addOrReplaceChild("LeftShears", CubeListBuilder.create().texOffs(48, 40).addBox(0.5F, -3.0F, -10.5F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -10.0F, 0.8727F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 128);
    }

    @Override
    public void setupAnim(BlackAntRenderState state) {
        super.setupAnim(state);
        RightShears.yRot = LeftShears.yRot = state.yRot / (180F / (float) Math.PI);
        RightShears.xRot = LeftShears.xRot = state.xRot / (180F / (float) Math.PI) - 1F + 1.64F;
    }
}
