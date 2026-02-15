package erebus.client.render.entity.model.layer.mosquito;

import erebus.client.render.entity.renderer.state.MosquitoRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class MosquitoHeadModel extends EntityModel<MosquitoRenderState> {

    public ModelPart root;

    public MosquitoHeadModel(ModelPart root) {
        super(root);
        this.root = root;
        root.getChild("Head2");
        root.getChild("Head3");
        root.getChild("Head4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("Head2", CubeListBuilder.create().texOffs(0, 45).addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8), PartPose.offset(0.0F, 4.0F, -4.0F));
        root.addOrReplaceChild("Head3", CubeListBuilder.create().texOffs(0, 33).addBox(-2.0F, 3.0F, -3.0F, 4, 8, 4), PartPose.offsetAndRotation(0.0F, 4.0F, -4.0F, -0.1745329F, 0.0F, 0.0F));
        root.addOrReplaceChild("Head4", CubeListBuilder.create().texOffs(16, 35).addBox(-1.0F, 10.0F, 0.0F, 2, 8, 2), PartPose.offsetAndRotation(0.0F, 4.0F, -4.0F, -0.3490659F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }
}
