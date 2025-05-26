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

public class BambooExtenderModel extends Model {

	private final ModelPart BambooStep1;
	private final ModelPart BambooStep2;
	private final ModelPart SupportR1;
	private final ModelPart SupportL1;
	private final ModelPart String1;
	private final ModelPart String2;
	private final ModelPart String3;
	private final ModelPart String4;
	private final ModelPart StringR1;
	private final ModelPart StringL1;
	private final ModelPart Polebit;
	private final ModelPart Main;

	public BambooExtenderModel(ModelPart root) {
		super(RenderType::entityCutout);
		this.BambooStep1 = root.getChild("BambooStep1");
		this.BambooStep2 = root.getChild("BambooStep2");
		this.SupportR1 = root.getChild("SupportR1");
		this.SupportL1 = root.getChild("SupportL1");
		this.String1 = root.getChild("String1");
		this.String2 = root.getChild("String2");
		this.String3 = root.getChild("String3");
		this.String4 = root.getChild("String4");
		this.StringR1 = root.getChild("StringR1");
		this.StringL1 = root.getChild("StringL1");
		this.Polebit = root.getChild("Polebit");
		this.Main = root.getChild("Main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition BambooStep1 = partdefinition.addOrReplaceChild("BambooStep1", CubeListBuilder.create().texOffs(25, 31).addBox(0.0F, 0.0F, 0.0F, 14.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 22.0F, -4.5F, -1.5708F, 0.0F, 0.0F));

		PartDefinition BambooStep2 = partdefinition.addOrReplaceChild("BambooStep2", CubeListBuilder.create().texOffs(25, 31).addBox(0.0F, 0.0F, 0.0F, 14.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 22.0F, -0.5F, -1.5708F, 0.0F, 0.0F));

		PartDefinition SupportR1 = partdefinition.addOrReplaceChild("SupportR1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 14.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 24.0F, -7.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition SupportL1 = partdefinition.addOrReplaceChild("SupportL1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 14.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 24.0F, -7.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition String1 = partdefinition.addOrReplaceChild("String1", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, 9.0F, 0.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 22.5F, 8.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition String2 = partdefinition.addOrReplaceChild("String2", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, 9.0F, 0.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 22.5F, 8.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition String3 = partdefinition.addOrReplaceChild("String3", CubeListBuilder.create().texOffs(5, 6).addBox(0.0F, 0.0F, 0.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.6F, 12.0F, -8.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition String4 = partdefinition.addOrReplaceChild("String4", CubeListBuilder.create().texOffs(5, 6).addBox(0.0F, 0.0F, 0.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.4F, 12.0F, -8.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition StringR1 = partdefinition.addOrReplaceChild("StringR1", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, 0.2F, 0.0F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.4F, 11.0F, -2.5F, 0.0F, 0.0F, -0.1222F));

		PartDefinition StringL1 = partdefinition.addOrReplaceChild("StringL1", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, 0.1F, 0.0F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.6F, 11.0F, -2.5F, 0.0F, 0.0F, 0.1222F));

		PartDefinition Polebit = partdefinition.addOrReplaceChild("Polebit", CubeListBuilder.create().texOffs(0, 31).addBox(-3.5F, 8.0F, -3.5F, 7.0F, 14.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Main = partdefinition.addOrReplaceChild("Main", CubeListBuilder.create().texOffs(10, 7).addBox(-8.0F, 10.0F, -1.0F, 16.0F, 14.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		BambooStep1.render(stack, consumer, light, overlay, colour);
		BambooStep2.render(stack, consumer, light, overlay, colour);
		SupportR1.render(stack, consumer, light, overlay, colour);
		SupportL1.render(stack, consumer, light, overlay, colour);
		String1.render(stack, consumer, light, overlay, colour);
		String2.render(stack, consumer, light, overlay, colour);
		String3.render(stack, consumer, light, overlay, colour);
		String4.render(stack, consumer, light, overlay, colour);
		StringR1.render(stack, consumer, light, overlay, colour);
		StringL1.render(stack, consumer, light, overlay, colour);
		Polebit.render(stack, consumer, light, overlay, colour);
		Main.render(stack, consumer, light, overlay, colour);
	}

	public void render2(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		Polebit.render(stack, consumer, light, overlay, colour);
	}
}