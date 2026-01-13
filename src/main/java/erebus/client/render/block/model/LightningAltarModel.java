package erebus.client.render.block.model;

import erebus.client.render.block.state.LightningAltarBlockEntityRenderState;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class LightningAltarModel extends Model<LightningAltarBlockEntityRenderState> {

    public LightningAltarModel(ModelPart root) {
		super(root, RenderTypes::entitySolid);
        root.getChild("Mid");
        root.getChild("Top");
        root.getChild("Bot");
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
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("Mid", CubeListBuilder.create().texOffs(130, 0).addBox(-12.0F, 0.0F, -12.0F, 24.0F, 24.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

        partdefinition.addOrReplaceChild("Top", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, 0.0F, -16.0F, 32.0F, 4.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

        partdefinition.addOrReplaceChild("Bot", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, 0.0F, -16.0F, 32.0F, 4.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 0.0F));

        partdefinition.addOrReplaceChild("SmallBox", CubeListBuilder.create().texOffs(0, 36).addBox(-7.0F, 0.0F, -7.0F, 14.0F, 14.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        partdefinition.addOrReplaceChild("ElectrodeF1", CubeListBuilder.create().texOffs(58, 38).addBox(-2.0F, -9.0F, -11.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("ElectrodeF2", CubeListBuilder.create().texOffs(78, 38).addBox(-1.0F, 1.0F, -11.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("ElectrodeL1", CubeListBuilder.create().texOffs(58, 38).addBox(-2.0F, -9.0F, -11.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, 0.5236F, -1.5708F, 0.0F));

        partdefinition.addOrReplaceChild("ElectrodeL2", CubeListBuilder.create().texOffs(78, 38).addBox(-1.0F, 1.0F, -11.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, -1.0472F, -1.5708F, 0.0F));

        partdefinition.addOrReplaceChild("ElectrodeB1", CubeListBuilder.create().texOffs(58, 38).addBox(-2.0F, -9.0F, -11.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, 0.5236F, 3.1416F, 0.0F));

        partdefinition.addOrReplaceChild("ElectrodeB2", CubeListBuilder.create().texOffs(78, 38).addBox(-1.0F, 1.0F, -11.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, -1.0472F, 3.1416F, 0.0F));

        partdefinition.addOrReplaceChild("ElectrodeR1", CubeListBuilder.create().texOffs(58, 38).addBox(-2.0F, -9.0F, -11.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, 0.5236F, 1.5708F, 0.0F));

        partdefinition.addOrReplaceChild("ElectrodeR2", CubeListBuilder.create().texOffs(78, 38).addBox(-1.0F, 1.0F, -11.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, -1.0472F, 1.5708F, 0.0F));


        return LayerDefinition.create(meshdefinition, 256, 64);
	}
}
