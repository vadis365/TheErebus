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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VelvetWormModel<T extends VelvetWorm> extends HierarchicalModel<T> {
	public ModelPart root;
	public ModelPart Head1;
	public ModelPart BodA1;
	public ModelPart RLA1;
	public ModelPart LLA1;
	public ModelPart BodB1;
	public ModelPart RLB1;
	public ModelPart LLB1;
	public ModelPart BodC1;
	public ModelPart RLC1;
	public ModelPart LLC1;
	public ModelPart BodD1;
	public ModelPart RLD1;
	public ModelPart LLD1;
	public ModelPart BodE1;
	public ModelPart RLE1;
	public ModelPart LLE1;
	public ModelPart BodF1;
	public ModelPart BodF2;

	public VelvetWormModel(ModelPart root) {
		this.root = root;
		this.Head1 = root.getChild("Head1");
		this.BodA1 = root.getChild("BodA1");
		this.RLA1 = root.getChild("RLA1");
		this.LLA1 = root.getChild("LLA1");
		this.BodB1 = root.getChild("BodB1");
		this.RLB1 = root.getChild("RLB1");
		this.LLB1 = root.getChild("LLB1");
		this.BodC1 = root.getChild("BodC1");
		this.RLC1 = root.getChild("RLC1");
		this.LLC1 = root.getChild("LLC1");
		this.BodD1 = root.getChild("BodD1");
		this.RLD1 = root.getChild("RLD1");
		this.LLD1 = root.getChild("LLD1");
		this.BodE1 = root.getChild("BodE1");
		this.RLE1 = root.getChild("RLE1");
		this.LLE1 = root.getChild("LLE1");
		this.BodF1 = root.getChild("BodF1");
		this.BodF2 = root.getChild("BodF2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head1 = partdefinition.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(21, 19).addBox(-2.5F, -1.5F, -5.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, -10.0F));

		PartDefinition Head2 = Head1.addOrReplaceChild("Head2", CubeListBuilder.create().texOffs(26, 12).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 2.0F, -5.0F));

		PartDefinition Head3 = Head1.addOrReplaceChild("Head3", CubeListBuilder.create().texOffs(26, 12).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 2.0F, -5.0F));

		PartDefinition LAnt = Head1.addOrReplaceChild("LAnt", CubeListBuilder.create().texOffs(23, 0).addBox(-0.6014F, 0.0F, -6.7287F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, -5.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition RAnt = Head1.addOrReplaceChild("RAnt", CubeListBuilder.create().texOffs(23, 0).addBox(-0.3986F, 0.0F, -6.7287F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, -5.0F, 0.0F, 0.1745F, 0.0F));

		PartDefinition BodA1 = partdefinition.addOrReplaceChild("BodA1", CubeListBuilder.create().texOffs(40, 0).addBox(-3.0F, -2.5F, -3.0F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, -7.0F));

		PartDefinition RLA1 = partdefinition.addOrReplaceChild("RLA1", CubeListBuilder.create().texOffs(13, 0).addBox(-3.5F, -0.9F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 21.5F, -7.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition LLA1 = partdefinition.addOrReplaceChild("LLA1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.9F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 21.5F, -7.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition BodB1 = partdefinition.addOrReplaceChild("BodB1", CubeListBuilder.create().texOffs(0, 7).addBox(-3.0F, -3.5F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, -2.0F));

		PartDefinition RLB1 = partdefinition.addOrReplaceChild("RLB1", CubeListBuilder.create().texOffs(13, 0).addBox(-3.5F, -0.9F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 21.5F, -2.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition LLB1 = partdefinition.addOrReplaceChild("LLB1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.9F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 21.5F, -2.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition BodC1 = partdefinition.addOrReplaceChild("BodC1", CubeListBuilder.create().texOffs(0, 7).addBox(-3.0F, -3.5F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 3.0F));

		PartDefinition RLC1 = partdefinition.addOrReplaceChild("RLC1", CubeListBuilder.create().texOffs(13, 0).addBox(-3.5F, -0.9F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 21.5F, 3.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition LLC1 = partdefinition.addOrReplaceChild("LLC1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.9F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 21.5F, 3.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition BodD1 = partdefinition.addOrReplaceChild("BodD1", CubeListBuilder.create().texOffs(40, 0).addBox(-3.0F, -2.5F, -3.0F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 8.0F));

		PartDefinition RLD1 = partdefinition.addOrReplaceChild("RLD1", CubeListBuilder.create().texOffs(13, 0).addBox(-3.5F, -0.9F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 21.5F, 8.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition LLD1 = partdefinition.addOrReplaceChild("LLD1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.9F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 21.5F, 8.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition BodE1 = partdefinition.addOrReplaceChild("BodE1", CubeListBuilder.create().texOffs(40, 11).addBox(-3.0F, -1.5F, -3.0F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 13.0F));

		PartDefinition RLE1 = partdefinition.addOrReplaceChild("RLE1", CubeListBuilder.create().texOffs(13, 0).addBox(-3.5F, -0.9F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 21.5F, 13.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition LLE1 = partdefinition.addOrReplaceChild("LLE1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.9F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 21.5F, 13.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition BodF1 = partdefinition.addOrReplaceChild("BodF1", CubeListBuilder.create().texOffs(0, 20).addBox(-2.0F, -0.5F, -3.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 18.0F));

		PartDefinition BodF2 = partdefinition.addOrReplaceChild("BodF2", CubeListBuilder.create().texOffs(38, 21).addBox(-3.0F, 1.5F, -3.0F, 6.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 18.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

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

	@Override
	public ModelPart root() {
		return root;
	}
}