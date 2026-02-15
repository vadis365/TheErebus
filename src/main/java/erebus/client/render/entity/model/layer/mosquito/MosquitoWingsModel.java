package erebus.client.render.entity.model.layer.mosquito;

import erebus.client.render.entity.renderer.state.MosquitoRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class MosquitoWingsModel extends EntityModel<MosquitoRenderState> {

    public ModelPart root;
    private final ModelPart WingLeft, WingRight;

    public MosquitoWingsModel(ModelPart root) {
        super(root);
        this.root = root;
        this.WingLeft = root.getChild("WingLeft");
        this.WingRight = root.getChild("WingRight");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("WingLeft", CubeListBuilder.create().texOffs(0, 87).addBox(-16.0F, 0.0F, 0.0F, 16, 1, 32), PartPose.offset(-4.0F, -9.0F, 4.0F));
        root.addOrReplaceChild("WingRight", CubeListBuilder.create().texOffs(0, 87).addBox(0.0F, 0.0F, 0.0F, 16, 1, 32), PartPose.offset(4.0F, -9.0F, 4.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(MosquitoRenderState state) {
        super.setupAnim(state);
        WingLeft.zRot = state.wingAngle;
        WingRight.zRot = -state.wingAngle;
    }
}
