package erebus.client.render.block.model.altar.lightning;

import erebus.client.render.block.renderer.state.LightningAltarBlockEntityRenderState;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class LightningAltarElectrodeModel extends Model<LightningAltarBlockEntityRenderState> {

    public LightningAltarElectrodeModel(ModelPart root) {
		super(root, RenderTypes::entitySolid);
        root.getChild("SmallBox");
        root.getChild("ElectrodeF1");
        root.getChild("ElectrodeF2");
        root.getChild("ElectrodeL1");
        root.getChild("ElectrodeL2");
        root.getChild("ElectrodeB1");
        root.getChild("ElectrodeB2");
        root.getChild("ElectrodeR1");
        root.getChild("ElectrodeR2");
    }

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("SmallBox", CubeListBuilder.create().texOffs(0, 36).addBox(-7.0F, 0.0F, -7.0F, 14.0F, 14.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        part.addOrReplaceChild("ElectrodeF1", CubeListBuilder.create().texOffs(58, 38).addBox(-2.0F, -9.0F, -11.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, 0.5236F, 0.0F, 0.0F));
        part.addOrReplaceChild("ElectrodeF2", CubeListBuilder.create().texOffs(78, 38).addBox(-1.0F, 1.0F, -11.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, -1.0472F, 0.0F, 0.0F));
        part.addOrReplaceChild("ElectrodeL1", CubeListBuilder.create().texOffs(58, 38).addBox(-2.0F, -9.0F, -11.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, 0.5236F, -1.5708F, 0.0F));
        part.addOrReplaceChild("ElectrodeL2", CubeListBuilder.create().texOffs(78, 38).addBox(-1.0F, 1.0F, -11.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, -1.0472F, -1.5708F, 0.0F));
        part.addOrReplaceChild("ElectrodeB1", CubeListBuilder.create().texOffs(58, 38).addBox(-2.0F, -9.0F, -11.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, 0.5236F, 3.1416F, 0.0F));
        part.addOrReplaceChild("ElectrodeB2", CubeListBuilder.create().texOffs(78, 38).addBox(-1.0F, 1.0F, -11.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, -1.0472F, 3.1416F, 0.0F));
        part.addOrReplaceChild("ElectrodeR1", CubeListBuilder.create().texOffs(58, 38).addBox(-2.0F, -9.0F, -11.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, 0.5236F, 1.5708F, 0.0F));
        part.addOrReplaceChild("ElectrodeR2", CubeListBuilder.create().texOffs(78, 38).addBox(-1.0F, 1.0F, -11.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, -1.0472F, 1.5708F, 0.0F));

        return LayerDefinition.create(mesh, 256, 64);
	}
}
