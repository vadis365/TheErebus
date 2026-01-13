package erebus.client.render.block.model;

import erebus.client.render.block.state.OfferingAltarBlockEntityRenderState;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class OfferingAltarModel extends Model<OfferingAltarBlockEntityRenderState> {

    public OfferingAltarModel(ModelPart root) {
		super(root, RenderTypes::entitySolid);
        root.getChild("boxes0");
        root.getChild("boxes1");
        root.getChild("boxes2");
        root.getChild("boxes3");
        root.getChild("boxes4");
        root.getChild("boxes5");
        root.getChild("boxes6");
        root.getChild("boxes7");
    }

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("boxes0", CubeListBuilder.create().texOffs(0, 43).addBox(-7.5F, 0.0F, -7.5F, 15.0F, 2.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));

        partdefinition.addOrReplaceChild("boxes1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, 0.0F, -6.0F, 12.0F, 10.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));

        partdefinition.addOrReplaceChild("boxes2", CubeListBuilder.create().texOffs(0, 24).addBox(-7.5F, 0.0F, -7.5F, 15.0F, 2.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 0.0F));

        partdefinition.addOrReplaceChild("boxes3", CubeListBuilder.create().texOffs(84, 4).addBox(-7.5F, 0.0F, 0.0F, 15.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, -7.5F));

        partdefinition.addOrReplaceChild("boxes4", CubeListBuilder.create().texOffs(84, 0).addBox(-7.5F, 0.0F, 0.0F, 15.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 5.5F));

        partdefinition.addOrReplaceChild("boxes5", CubeListBuilder.create().texOffs(64, 12).addBox(0.0F, 0.0F, -5.5F, 2.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.5F, 9.0F, 0.0F));

        partdefinition.addOrReplaceChild("boxes6", CubeListBuilder.create().texOffs(64, 0).addBox(0.0F, 0.0F, -5.5F, 2.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, 9.0F, 0.0F));

        partdefinition.addOrReplaceChild("boxes7", CubeListBuilder.create().texOffs(60, 33).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
	}
}
