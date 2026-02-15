package erebus.client.render.block.model.altar.lightning;

import erebus.client.render.block.renderer.state.LightningAltarBlockEntityRenderState;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class LightningAltarBaseModel extends Model<LightningAltarBlockEntityRenderState> {

    public LightningAltarBaseModel(ModelPart root) {
		super(root, RenderTypes::entitySolid);
        root.getChild("Top");
        root.getChild("Bot");
    }

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("Top", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, 0.0F, -16.0F, 32.0F, 4.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));
        part.addOrReplaceChild("Bot", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, 0.0F, -16.0F, 32.0F, 4.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 0.0F));

        return LayerDefinition.create(mesh, 256, 64);
	}
}
