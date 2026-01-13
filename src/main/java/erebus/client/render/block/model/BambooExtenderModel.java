package erebus.client.render.block.model;

import erebus.client.render.block.state.BambooExtenderBlockEntityRenderState;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class BambooExtenderModel extends Model<BambooExtenderBlockEntityRenderState> {

    public BambooExtenderModel(ModelPart root) {
		super(root, RenderTypes::entityCutout);
        root.getChild("BambooStep1");
        root.getChild("BambooStep2");
        root.getChild("SupportR1");
        root.getChild("SupportL1");
        root.getChild("String1");
        root.getChild("String2");
        root.getChild("String3");
        root.getChild("String4");
        root.getChild("StringR1");
        root.getChild("StringL1");
        root.getChild("Polebit");
        root.getChild("Main");
    }

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("BambooStep1", CubeListBuilder.create().texOffs(25, 31).addBox(0.0F, 0.0F, 0.0F, 14.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 22.0F, -4.5F, -1.5708F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("BambooStep2", CubeListBuilder.create().texOffs(25, 31).addBox(0.0F, 0.0F, 0.0F, 14.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 22.0F, -0.5F, -1.5708F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("SupportR1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 14.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 24.0F, -7.5F, 0.0F, 0.0F, -1.5708F));

        partdefinition.addOrReplaceChild("SupportL1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 14.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 24.0F, -7.5F, 0.0F, 0.0F, -1.5708F));

        partdefinition.addOrReplaceChild("String1", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, 9.0F, 0.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 22.5F, 8.0F, -1.5708F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("String2", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, 9.0F, 0.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 22.5F, 8.0F, -1.5708F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("String3", CubeListBuilder.create().texOffs(5, 6).addBox(0.0F, 0.0F, 0.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.6F, 12.0F, -8.0F, 1.5708F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("String4", CubeListBuilder.create().texOffs(5, 6).addBox(0.0F, 0.0F, 0.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.4F, 12.0F, -8.0F, 1.5708F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("StringR1", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, 0.2F, 0.0F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.4F, 11.0F, -2.5F, 0.0F, 0.0F, -0.1222F));

        partdefinition.addOrReplaceChild("StringL1", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, 0.1F, 0.0F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.6F, 11.0F, -2.5F, 0.0F, 0.0F, 0.1222F));

        partdefinition.addOrReplaceChild("Polebit", CubeListBuilder.create().texOffs(0, 31).addBox(-3.5F, 8.0F, -3.5F, 7.0F, 14.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("Main", CubeListBuilder.create().texOffs(10, 7).addBox(-8.0F, 10.0F, -1.0F, 16.0F, 14.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
	}
}
