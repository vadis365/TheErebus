package erebus.client.item.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class WandOfAnimationItemModel extends Model {

	public final ModelPart Jewel1;
	public final ModelPart TopR3;
	public final ModelPart Shaft;
	boolean up;

	public WandOfAnimationItemModel(ModelPart root) {
		super(RenderType::entitySolid);
		this.Jewel1 = root.getChild("Jewel1");
		this.TopR3 = root.getChild("TopR3");
		this.Shaft = root.getChild("Shaft");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Jewel1 = partdefinition.addOrReplaceChild("Jewel1", CubeListBuilder.create().texOffs(0, 7).addBox(-2.0F, -7.7F, -2.0F, 4.0F, 4.0F, 4.0F,
				new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
		
		PartDefinition Jewel2 = Jewel1.addOrReplaceChild("Jewel2", CubeListBuilder.create().texOffs(0, 7).addBox(-2.0F, -6.0F, 2.0F, 4.0F, 4.0F, 4.0F,
				new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));
		
		PartDefinition Jewel3 = Jewel1.addOrReplaceChild("Jewel3", CubeListBuilder.create().texOffs(0, 7).addBox(-6.0F, -6.0F, -2.0F, 4.0F, 4.0F, 4.0F,
				new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition TopR3 = partdefinition.addOrReplaceChild("TopR3", CubeListBuilder.create().texOffs(0, 0).addBox(4.0F, -8.5F, -0.5F, 1.0F, 5.0F, 1.0F,
				new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
		
		PartDefinition TopR1 = TopR3.addOrReplaceChild("TopR1", CubeListBuilder.create().texOffs(5, 0).addBox(-2.0F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F,
				new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));
		
		PartDefinition TopR2 = TopR3.addOrReplaceChild("TopR2", CubeListBuilder.create().texOffs(10, 0).addBox(-2.0F, -6.0F, -1.0F, 1.0F, 2.0F, 2.0F,
				new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));

		PartDefinition TopR4 = TopR3.addOrReplaceChild("TopR4", CubeListBuilder.create().texOffs(17, 0).addBox(8.3F, -5.3F, -1.0F, 1.0F, 2.0F, 2.0F,
				new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.6981F));
		
		PartDefinition TopR5 = TopR3.addOrReplaceChild("TopR5", CubeListBuilder.create().texOffs(24, 0).addBox(9.0F, -8.5F, -0.5F, 1.0F, 5.0F, 1.0F,
				new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.8727F));

		PartDefinition Shaft = partdefinition.addOrReplaceChild("Shaft", CubeListBuilder.create().texOffs(0, 33).addBox(-1.0F, 6.0F, -1.0F, 2.0F, 19.0F, 2.0F,
				new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition Dec4 = Shaft.addOrReplaceChild("Dec4", CubeListBuilder.create().texOffs(0, 16).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F,
				new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Dec3 = Shaft.addOrReplaceChild("Dec3", CubeListBuilder.create().texOffs(0, 23).addBox(-1.0F, 3.0F, -1.0F, 2.0F, 2.0F, 2.0F,
				new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Dec2 = Shaft.addOrReplaceChild("Dec2", CubeListBuilder.create().texOffs(0, 28).addBox(-1.5F, 5.0F, -1.5F, 3.0F, 1.0F, 3.0F,
				new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		
		PartDefinition Dec1 = Shaft.addOrReplaceChild("Dec1", CubeListBuilder.create().texOffs(0, 28).addBox(-1.5F, 5.0F, -1.5F, 3.0F, 1.0F, 3.0F,
				new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition Pommel1 = Shaft.addOrReplaceChild("Pommel1", CubeListBuilder.create().texOffs(0, 55).addBox(-1.5F, 16.25F, -19.25F, 3.0F, 3.0F, 3.0F,
				new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));
		
		PartDefinition Pommel2 = Shaft.addOrReplaceChild("Pommel2", CubeListBuilder.create().texOffs(0, 55).addBox(16.3F, 16.3F, -1.5F, 3.0F, 3.0F, 3.0F,
				new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));
		
		PartDefinition Pommel3 = Shaft.addOrReplaceChild("Pommel3", CubeListBuilder.create().texOffs(0, 55).addBox(-1.5F, 23.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		float tick = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);
		if (tick <= 360)
			up = true;
		if (tick >= 361)
			up = false;
		
		//Jewel1.render(poseStack, buffer, packedLight, packedOverlay, color);
		//TopR3.render(poseStack, buffer, packedLight, packedOverlay, color);
		Shaft.render(poseStack, buffer, packedLight, packedOverlay, color);
		poseStack.mulPose(Axis.YP.rotationDegrees(tick));
		TopR3.render(poseStack, buffer, packedLight, packedOverlay, color);
		
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(90F));
		TopR3.render(poseStack, buffer, packedLight, packedOverlay, color);
		poseStack.mulPose(Axis.YP.rotationDegrees(180F));
		TopR3.render(poseStack, buffer, packedLight, packedOverlay, color);
		poseStack.mulPose(Axis.YP.rotationDegrees(270F));
		TopR3.render(poseStack, buffer, packedLight, packedOverlay, color);
		poseStack.translate(0f, -0.29f + (up ? tick / 360 : 1 + 1 - tick / 360) / 10f, 0f);
		poseStack.mulPose(Axis.YN.rotationDegrees(tick * 2));
		Jewel1.render(poseStack, buffer, packedLight, packedOverlay, color);
		poseStack.popPose();
		
	}
	/*
	public void render() {
		float tick = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);
		if (tick <= 360)
			up = true;
		if (tick >= 361)
			up = false;

		Shaft.render(0.0625F);
		GlStateManager.rotate(tick, 0.0F, 1.0F, 0.0F);
		TopR3.render(0.0625F);
		GlStateManager.pushMatrix();
		GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
		TopR3.render(0.0625F);
		GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
		TopR3.render(0.0625F);
		GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
		TopR3.render(0.0625F);

		GlStateManager.translate(0f, -0.29f + (up ? tick / 360 : 1 + 1 - tick / 360) / 10f, 0f);

		GlStateManager.rotate(-tick * 2, 0.0F, 1.0F, 0.0F);
		Jewel1.render(0.0625F);
		GlStateManager.popMatrix();
	}
	*/
}