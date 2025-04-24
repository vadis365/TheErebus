package erebus.client.render.block.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;

public class OfferingAltarModel extends Model {

	private final ModelPart boxes0;
	private final ModelPart boxes1;
	private final ModelPart boxes2;
	private final ModelPart boxes3;
	private final ModelPart boxes4;
	private final ModelPart boxes5;
	private final ModelPart boxes6;
	private final ModelPart boxes7;

	public OfferingAltarModel(ModelPart root) {
		super(RenderType::entitySolid);
		this.boxes0 = root.getChild("boxes0");
		this.boxes1 = root.getChild("boxes1");
		this.boxes2 = root.getChild("boxes2");
		this.boxes3 = root.getChild("boxes3");
		this.boxes4 = root.getChild("boxes4");
		this.boxes5 = root.getChild("boxes5");
		this.boxes6 = root.getChild("boxes6");
		this.boxes7 = root.getChild("boxes7");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition boxes0 = partdefinition.addOrReplaceChild("boxes0", CubeListBuilder.create().texOffs(0, 43).addBox(-7.5F, 0.0F, -7.5F, 15.0F, 2.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition boxes1 = partdefinition.addOrReplaceChild("boxes1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, 0.0F, -6.0F, 12.0F, 10.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition boxes2 = partdefinition.addOrReplaceChild("boxes2", CubeListBuilder.create().texOffs(0, 24).addBox(-7.5F, 0.0F, -7.5F, 15.0F, 2.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 0.0F));

		PartDefinition boxes3 = partdefinition.addOrReplaceChild("boxes3", CubeListBuilder.create().texOffs(84, 4).addBox(-7.5F, 0.0F, 0.0F, 15.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, -7.5F));

		PartDefinition boxes4 = partdefinition.addOrReplaceChild("boxes4", CubeListBuilder.create().texOffs(84, 0).addBox(-7.5F, 0.0F, 0.0F, 15.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 5.5F));

		PartDefinition boxes5 = partdefinition.addOrReplaceChild("boxes5", CubeListBuilder.create().texOffs(64, 12).addBox(0.0F, 0.0F, -5.5F, 2.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.5F, 9.0F, 0.0F));

		PartDefinition boxes6 = partdefinition.addOrReplaceChild("boxes6", CubeListBuilder.create().texOffs(64, 0).addBox(0.0F, 0.0F, -5.5F, 2.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, 9.0F, 0.0F));

		PartDefinition boxes7 = partdefinition.addOrReplaceChild("boxes7", CubeListBuilder.create().texOffs(60, 33).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		boxes0.render(stack, consumer, light, overlay, colour);
		boxes1.render(stack, consumer, light, overlay, colour);
		boxes2.render(stack, consumer, light, overlay, colour);
		boxes3.render(stack, consumer, light, overlay, colour);
		boxes4.render(stack, consumer, light, overlay, colour);
		boxes5.render(stack, consumer, light, overlay, colour);
		boxes6.render(stack, consumer, light, overlay, colour);
		boxes7.render(stack, consumer, light, overlay, colour);
	}

}