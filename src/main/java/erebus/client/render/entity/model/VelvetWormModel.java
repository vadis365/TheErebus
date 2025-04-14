package erebus.client.render.entity.model;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import erebus.entity.VelvetWorm;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VelvetWormModel<T extends VelvetWorm> extends HierarchicalModel<T> {
	public ModelPart root;
	private final ModelPart Head1;
	private final ModelPart Body1;
	private final ModelPart Body1RightLeg;
	private final ModelPart Body1LeftLeg;
	private final ModelPart Body2;
	private final ModelPart Body2RightLeg;
	private final ModelPart Body2LeftLeg;
	private final ModelPart Tail;
	private final ModelPart TailFin;
	private final ModelPart LAnt;
	private final ModelPart RAnt;

	public VelvetWormModel(ModelPart root) {
		this.root = root;
		this.Head1 = root.getChild("Head1");
		this.Body1 = root.getChild("Body1");
		this.Body1RightLeg = root.getChild("Body1RightLeg");
		this.Body1LeftLeg = root.getChild("Body1LeftLeg");
		this.Body2 = root.getChild("Body2");
		this.Body2RightLeg = root.getChild("Body2RightLeg");
		this.Body2LeftLeg = root.getChild("Body2LeftLeg");
		this.Tail = root.getChild("Tail");
		this.TailFin = root.getChild("TailFin");
		LAnt = Head1.getChild("LAnt");
		RAnt =  Head1.getChild("RAnt");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head1 = partdefinition.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(12, 0).addBox(-2.5F, -2.0F, -5.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 1.0F));

		PartDefinition Head2 = Head1.addOrReplaceChild("Head2", CubeListBuilder.create().texOffs(26, 18).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 1.0F, -5.0F));

		PartDefinition Head3 = Head1.addOrReplaceChild("Head3", CubeListBuilder.create().texOffs(26, 18).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 1.0F, -5.0F));

		PartDefinition LAnt = Head1.addOrReplaceChild("LAnt", CubeListBuilder.create().texOffs(16, 21).addBox(-0.6014F, -0.5F, -6.7287F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -0.5F, -5.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition RAnt = Head1.addOrReplaceChild("RAnt", CubeListBuilder.create().texOffs(16, 21).addBox(-0.3986F, -0.5F, -6.7287F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -0.5F, -5.0F, 0.0F, 0.1745F, 0.0F));

		PartDefinition Body1 = partdefinition.addOrReplaceChild("Body1", CubeListBuilder.create().texOffs(0, 10).addBox(-3.0F, -2.5F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition Body1RightLeg = partdefinition.addOrReplaceChild("Body1RightLeg", CubeListBuilder.create().texOffs(0, 5).addBox(-3.5F, -0.9F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 21.5F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition Body1LeftLeg = partdefinition.addOrReplaceChild("Body1LeftLeg", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.9F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 21.5F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition Body2 = partdefinition.addOrReplaceChild("Body2", CubeListBuilder.create().texOffs(0, 10).addBox(-3.0F, -2.5F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition Body2RightLeg = partdefinition.addOrReplaceChild("Body2RightLeg", CubeListBuilder.create().texOffs(0, 5).addBox(-3.5F, -0.9F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 21.5F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition Body2LeftLeg = partdefinition.addOrReplaceChild("Body2LeftLeg", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.9F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 21.5F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition Tail = partdefinition.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(1, 24).addBox(-2.0F, -1.0F, -0.5F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0F));

		PartDefinition TailFin = partdefinition.addOrReplaceChild("TailFin", CubeListBuilder.create().texOffs(13, 9).addBox(-3.0F, 0.5F, -0.5F, 6.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float legs = (float) (Math.sin(limbSwing) * limbSwingAmount);
		Body1RightLeg.yRot = -legs;
		Body1LeftLeg.yRot = legs;
	/*	float legs = (float) (Math.sin(limbSwing) * limbSwingAmount);
		float bb = (float) (Math.sin(limbSwing + 1.0F) * 2.0F * limbSwingAmount);
		float bc = (float) (Math.sin(limbSwing + 2.0F) * 2.6F * limbSwingAmount);
		float bd = (float) (Math.sin(limbSwing + 3.0F) * 2.0F * limbSwingAmount);
		float be = (float) (Math.sin(limbSwing + 4.0F) * limbSwingAmount);
		float bf = (float) (Math.sin(limbSwing + 5.0F) * 0.35F * limbSwingAmount);

		Head1.x = bf;

		BodA1.y = 21F + legs;

		BodB1.y = 21F + bb;

		BodC1.y = 21F + bc;

		BodD1.y = 21F + bd;

		BodE1.y = 21F + be;

		BodF1.y = 21F + bf;
		BodF2.y = 21F + bf;

		RLA1.y = 21.5F + legs;
		LLA1.y = 21.5F + legs;

		RLB1.y = 21.5F + bb;
		LLB1.y = 21.5F + bb;

		RLC1.y = 21.5F + bc;
		LLC1.y = 21.5F + bc;

		RLD1.y = 21.5F + bd;
		LLD1.y = 21.5F + bd;

		RLE1.y = 21.5F + be;
		LLE1.y = 21.5F + be;



		RLB1.yRot = legs;
		LLB1.yRot = -legs;

		RLC1.yRot = -legs;
		LLC1.yRot = legs;

		RLD1.yRot = legs;
		LLD1.yRot = -legs;

		RLE1.yRot = -legs;
		LLE1.yRot = legs;

		Head1.yRot = netHeadYaw / (180F / (float) Math.PI);
		Head1.xRot = headPitch / (180F / (float) Math.PI);

		BodF1.xRot = headPitch / (180F / (float) Math.PI);
		BodF2.xRot = headPitch / (180F / (float) Math.PI);
		*/
	}
/*
	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		Head1.render(stack, consumer, light, overlay, colour);
		BodA1.render(stack, consumer, light, overlay, colour);
		RLA1.render(stack, consumer, light, overlay, colour);
		LLA1.render(stack, consumer, light, overlay, colour);
		BodB1.render(stack, consumer, light, overlay, colour);
		RLB1.render(stack, consumer, light, overlay, colour);
		LLB1.render(stack, consumer, light, overlay, colour);
		BodC1.render(stack, consumer, light, overlay, colour);
		RLC1.render(stack, consumer, light, overlay, colour);
		LLC1.render(stack, consumer, light, overlay, colour);
		BodD1.render(stack, consumer, light, overlay, colour);
		RLD1.render(stack, consumer, light, overlay, colour);
		LLD1.render(stack, consumer, light, overlay, colour);
		BodE1.render(stack, consumer, light, overlay, colour);
		RLE1.render(stack, consumer, light, overlay, colour);
		LLE1.render(stack, consumer, light, overlay, colour);
		BodF1.render(stack, consumer, light, overlay, colour);
		BodF2.render(stack, consumer, light, overlay, colour);
	}
*/
	@Override
	public ModelPart root() {
		return root;
	}

	public void renderHead(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour, VelvetWorm worm, int frame, float wibbleStrength, float partialTicks) {
		float limbSwing = worm.walkAnimation.position(partialTicks);
		float limbSwingAmount = worm.walkAnimation.speed(partialTicks);
		float smoothedTicks = limbSwing + frame;
		float wibble = (float) (Math.sin((smoothedTicks) * 0.3F) * 0.25F * limbSwingAmount * wibbleStrength);
		float antTicks = worm.tickCount + frame + (worm.tickCount + frame - (worm.tickCount + frame - 1)) * partialTicks;
		float ant_wibbleSin = (float) (Math.sin(1F + (antTicks) * 0.25F) * 0.25F);
		float ant_wibbleCos = (float) (Math.cos(1F + (antTicks) * 0.25F) * 0.25F);
		
		stack.translate(0F, 0F - wibble * 2F, 0F + wibble * 2F);
		stack.scale(1F + wibble * 2F, 1F + wibble, 1.5F - wibble * 1.25F);
		Head1.xRot = worm.getXRot() / Mth.RAD_TO_DEG;
		LAnt.xRot = 0F + ant_wibbleSin;
		RAnt.xRot = 0F + ant_wibbleCos;
		LAnt.yRot = -0.1745F  - ant_wibbleCos;
		RAnt.yRot = 0.1745F + ant_wibbleSin;
		Head1.render(stack, consumer, light, overlay, colour); 
	}

	public void renderBody(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour, VelvetWorm worm, int frame, float wibbleStrength, float partialTicks, boolean isPartA) {
		float limbSwing = worm.walkAnimation.position(partialTicks);
		float limbSwingAmount = worm.walkAnimation.speed(partialTicks);
		float smoothedTicks = limbSwing + frame;
		float wibble = (float) (Math.sin((smoothedTicks) * 0.3F) * 0.25F * limbSwingAmount * wibbleStrength);
		float legsSin = (float) (Math.sin(smoothedTicks * 0.3F) * 0.75F * limbSwingAmount * wibbleStrength);
		float legsCos = (float) (Math.cos(smoothedTicks * 0.3F) * 0.5F * limbSwingAmount);

		stack.translate(0F, 0F - wibble, 0F - wibble * 2F);

		stack.pushPose();
		stack.scale(1F + wibble * 2F, 1F + wibble, 1.5F - wibble * 1.25F);
		stack.translate(0F,  0F - wibble, 0F);
		Body1.render(stack, consumer, light, overlay, colour);
		stack.popPose();
		if(isPartA) {
			stack.pushPose();
			stack.scale(1F, 1F, 1F);
			stack.translate(0F - wibble * 0.5F, 0F + wibble * 0.5F, 0F);
			Body1RightLeg.yRot = -legsSin;
			Body1RightLeg.zRot = -0.4363F - legsCos;
			Body1RightLeg.render(stack, consumer, light, overlay, colour);
			stack.popPose();

			stack.pushPose();
			stack.scale(1F, 1F, 1F);
			stack.translate(0F + wibble * 0.5F, 0F + wibble * 0.5F, 0F);
			Body1LeftLeg.yRot = legsSin;
			Body1LeftLeg.zRot = 0.4363F + legsCos;
			Body1LeftLeg.render(stack, consumer, light, overlay, colour);
			stack.popPose();
		}
		else {
			stack.pushPose();
			stack.scale(1F, 1F, 1F);
			stack.translate(0F - wibble * 0.5F, 0F + wibble * 0.5F, 0F);
			Body2RightLeg.yRot = legsSin;
			Body2RightLeg.zRot = -0.4363F + legsCos;
			Body2RightLeg.render(stack, consumer, light, overlay, colour);
			stack.popPose();

			stack.pushPose();
			stack.scale(1F, 1F, 1F);
			stack.translate(0F + wibble * 0.5F, 0F + wibble * 0.5F, 0F);
			Body2LeftLeg.yRot = -legsSin;
			Body2LeftLeg.zRot = 0.4363F - legsCos;
			Body2LeftLeg.render(stack, consumer, light, overlay, colour);
			stack.popPose();
		}
	}

	public void renderTail(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour, VelvetWorm worm, int frame, float wibbleStrength, float partialTicks) {
		float limbSwing = worm.walkAnimation.position(partialTicks);
		float limbSwingAmount = worm.walkAnimation.speed(partialTicks);
		float smoothedTicks = limbSwing + frame;
		float wibble = (float) (Math.sin((smoothedTicks) * 0.3F) * 0.25F * limbSwingAmount * wibbleStrength);

		stack.translate(0F, 0F - wibble * 2F, 0F + wibble * 2F);
		stack.scale(1F + wibble * 2F, 1F + wibble, 1.625F - wibble * 1.25F);
		Tail.render(stack, consumer, light, overlay, colour);
		TailFin.render(stack, consumer, light, overlay, colour);
	}
}