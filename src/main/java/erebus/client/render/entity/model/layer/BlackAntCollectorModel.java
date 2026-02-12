package erebus.client.render.entity.model.layer;

import erebus.client.render.entity.renderer.state.BlackAntRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BlackAntCollectorModel extends EntityModel<BlackAntRenderState> {
    private final ModelPart LeftPack;
    private final ModelPart StrapPack;
    private final ModelPart RightPack;

    public BlackAntCollectorModel(ModelPart root) {
        super(root);
        LeftPack = root.getChild("LeftPack");
        StrapPack = root.getChild("StrapPack");
        RightPack = root.getChild("RightPack");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("LeftPack", CubeListBuilder.create().texOffs(47, 15).addBox(4.0F, 0.5F, -2.5F, 3.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, 8.0F, 0.0F, 0.0F, -0.2443F));
        root.addOrReplaceChild("StrapPack", CubeListBuilder.create().texOffs(42, 52).addBox(-5.0F, -0.5F, -0.5F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 8.0F));
        root.addOrReplaceChild("RightPack", CubeListBuilder.create().texOffs(47, 27).addBox(-7.0F, 0.5F, -2.5F, 3.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, 8.0F, 0.0F, 0.0F, 0.2618F));

        return LayerDefinition.create(mesh, 64, 128);
    }

    @Override
    public void setupAnim(BlackAntRenderState state) {
        super.setupAnim(state);
    }
}
