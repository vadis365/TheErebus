package erebus.client.render.block.model.altar.repair;

import erebus.client.render.block.renderer.state.RepairAltarBlockEntityRenderState;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class RepairAltarMidModel extends Model<RepairAltarBlockEntityRenderState> {

    public RepairAltarMidModel(ModelPart root) {
		super(root, RenderTypes::entitySolid);
        root.getChild("Mid");
    }

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("Mid", CubeListBuilder.create().texOffs(130, 0).addBox(-12.0F, 0.0F, -12.0F, 24.0F, 24.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

        return LayerDefinition.create(mesh, 256, 64);
	}
}
