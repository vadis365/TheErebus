package erebus.client.render.entity.model;

import erebus.client.render.entity.renderer.state.TarantulaBabyRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class TarantulaBabyModel extends EntityModel<TarantulaBabyRenderState> {
    public ModelPart root;

    public TarantulaBabyModel(ModelPart root) {
        super(root);
        this.root = root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        return LayerDefinition.create(mesh, 16, 16);
    }
}
