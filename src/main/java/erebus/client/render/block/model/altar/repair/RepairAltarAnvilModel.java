package erebus.client.render.block.model.altar.repair;

import erebus.client.render.block.renderer.state.RepairAltarBlockEntityRenderState;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class RepairAltarAnvilModel extends Model<RepairAltarBlockEntityRenderState> {

    public RepairAltarAnvilModel(ModelPart root) {
		super(root, RenderTypes::entitySolid);
        root.getChild("AnvilFrontFoot");
        root.getChild("AnvilRearFoot");
        root.getChild("AnvilBase");
        root.getChild("AnvilWaist");
        root.getChild("AnvilFace");
        root.getChild("AnvilTable");
        root.getChild("AnvilHorn");
        root.getChild("AnvilHeel");
    }

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("AnvilFrontFoot", CubeListBuilder.create().texOffs(0, 37).addBox(-6.0F, 20.0F, -6.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));
        part.addOrReplaceChild("AnvilRearFoot", CubeListBuilder.create().texOffs(0, 37).addBox(2.0F, 20.0F, -6.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));
        part.addOrReplaceChild("AnvilBase", CubeListBuilder.create().texOffs(33, 37).addBox(-5.0F, 19.0F, -4.0F, 10.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));
        part.addOrReplaceChild("AnvilWaist", CubeListBuilder.create().texOffs(0, 54).addBox(-4.0F, 15.0F, -2.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));
        part.addOrReplaceChild("AnvilFace", CubeListBuilder.create().texOffs(70, 37).addBox(-7.0F, 8.0F, -5.0F, 14.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));
        part.addOrReplaceChild("AnvilTable", CubeListBuilder.create().texOffs(33, 51).addBox(-10.0F, 9.0F, -3.5F, 3.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));
        part.addOrReplaceChild("AnvilHorn", CubeListBuilder.create().texOffs(55, 51).addBox(-13.0F, 9.0F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));
        part.addOrReplaceChild("AnvilHeel", CubeListBuilder.create().texOffs(109, 45).addBox(7.0F, 8.0F, -5.0F, 3.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

        return LayerDefinition.create(mesh, 256, 64);
	}
}
