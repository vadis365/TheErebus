package erebus.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.entity.AnimatedBlock;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AnimatedBlockModel<T extends AnimatedBlock> extends HierarchicalModel<T> {
	public ModelPart root;
	public ModelPart LBL1;
	public ModelPart LBL2;
	public ModelPart LBL3;
	public ModelPart LBL4;
	public ModelPart LML1;
	public ModelPart LML2;
	public ModelPart LML3;
	public ModelPart LML4;
	public ModelPart LFL1;
	public ModelPart LFL2;
	public ModelPart LFL3;
	public ModelPart LFL4;
	public ModelPart RBL1;
	public ModelPart RBL2;
	public ModelPart RBL3;
	public ModelPart RBL4;
	public ModelPart RML1;
	public ModelPart RML2;
	public ModelPart RML3;
	public ModelPart RML4;
	public ModelPart RFL1;
	public ModelPart RFL2;
	public ModelPart RFL3;
	public ModelPart RFL4;

	public AnimatedBlockModel(ModelPart root) {
		this.root = root;
		LBL1 = root.getChild("root").getChild("LBL1");
		LBL2 = root.getChild("root").getChild("LBL2");
		LBL3 = root.getChild("root").getChild("LBL3");
		LBL4 = root.getChild("root").getChild("LBL4");
		LML1 = root.getChild("root").getChild("LML1");
		LML2 = root.getChild("root").getChild("LML2");
		LML3 = root.getChild("root").getChild("LML3");
		LML4 = root.getChild("root").getChild("LML4");
		LFL1 = root.getChild("root").getChild("LFL1");
		LFL2 = root.getChild("root").getChild("LFL2");
		LFL3 = root.getChild("root").getChild("LFL3");
		LFL4 = root.getChild("root").getChild("LFL4");
		RBL1 = root.getChild("root").getChild("RBL1");
		RBL2 = root.getChild("root").getChild("RBL2");
		RBL3 = root.getChild("root").getChild("RBL3");
		RBL4 = root.getChild("root").getChild("RBL4");
		RML1 = root.getChild("root").getChild("RML1");
		RML2 = root.getChild("root").getChild("RML2");
		RML3 = root.getChild("root").getChild("RML3");
		RML4 = root.getChild("root").getChild("RML4");
		RFL1 = root.getChild("root").getChild("RFL1");
		RFL2 = root.getChild("root").getChild("RFL2");
		RFL3 = root.getChild("root").getChild("RFL3");
		RFL4 = root.getChild("root").getChild("RFL4");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LBL1 = root.addOrReplaceChild("LBL1",
				CubeListBuilder.create().texOffs(0, 16).addBox(1F, -1F, -1.5F, 5.0F, 3.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6F, 18F, 6F, 0F, -0.3490659F, -0.3490659F));

		PartDefinition LBL2 = root.addOrReplaceChild("LBL2",
				CubeListBuilder.create().texOffs(0, 16).addBox(5F, 0F, -1F, 2.0F, 4.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6F, 18F, 6F, 0F, -0.3490659F, -0.3490659F));

		PartDefinition LBL3 = root.addOrReplaceChild("LBL3",
				CubeListBuilder.create().texOffs(0, 16).addBox(3.5F, 5.5F, -0.5F, 2.0F, 4.0F, 1F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6F, 18F, 6F, 0F, -0.3490659F, -0.6981317F));

		PartDefinition LBL4 = root.addOrReplaceChild("LBL4",
				CubeListBuilder.create().texOffs(0, 16).addBox(2.5F, 9F, -0.5F, 1.0F, 4.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6F, 18F, 6F, 0F, -0.3490659F, -0.8726646F));

		PartDefinition LML1 = root
				.addOrReplaceChild("LML1",
						CubeListBuilder.create().texOffs(0, 16).addBox(-1F, -1F, -1.5F, 5.0F, 3.0F, 3.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(8F, 17F, 0F, 0F, 0F, -0.3490659F));

		PartDefinition LML2 = root.addOrReplaceChild("LML2",
				CubeListBuilder.create().texOffs(0, 16).addBox(3F, 0F, -1F, 2F, 4F, 2F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8F, 17F, 0F, 0F, 0F, -0.3490659F));

		PartDefinition LML3 = root
				.addOrReplaceChild("LML3",
						CubeListBuilder.create().texOffs(0, 16).addBox(1.5F, 4.5F, -0.5F, 2F, 4F, 1F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(8F, 17F, 0F, 0F, 0F, -0.6981317F));

		PartDefinition LML4 = root.addOrReplaceChild("LML4",
				CubeListBuilder.create().texOffs(0, 16).addBox(0.5F, 8F, -0.5F, 1F, 4F, 1F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8F, 17F, 0F, 0F, 0F, -0.8726646F));

		PartDefinition LFL1 = root.addOrReplaceChild("LFL1",
				CubeListBuilder.create().texOffs(0, 16).addBox(-1F, -1F, -1.5F, 5F, 3F, 3F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8F, 17F, -6F, 0F, 0.3490659F, -0.3490659F));

		PartDefinition LFL2 = root.addOrReplaceChild("LFL2",
				CubeListBuilder.create().texOffs(0, 16).addBox(3F, 0F, -1F, 2F, 4F, 2F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8F, 17F, -6F, 0F, 0.3490659F, -0.3490659F));

		PartDefinition LFL3 = root.addOrReplaceChild("LFL3",
				CubeListBuilder.create().texOffs(0, 16).addBox(1.5F, 4.5F, -0.5F, 2F, 4F, 1F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8F, 17F, -6F, 0F, 0.3490659F, -0.6981317F));

		PartDefinition LFL4 = root.addOrReplaceChild("LFL4",
				CubeListBuilder.create().texOffs(0, 16).addBox(0.5F, 8F, -0.5F, 1F, 4F, 1F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8F, 17F, -6F, 0F, 0.3490659F, -0.8726646F));

		PartDefinition RBL1 = root.addOrReplaceChild("RBL1",
				CubeListBuilder.create().texOffs(0, 16).addBox(-6F, -1F, -1.5F, 5F, 3F, 3F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6F, 18F, 6F, 0F, 0.3490659F, 0.3490659F));

		PartDefinition RBL2 = root.addOrReplaceChild("RBL2",
				CubeListBuilder.create().texOffs(0, 16).addBox(-7F, 0F, -1F, 2F, 4F, 2F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6F, 18F, 6F, 0F, 0.3490659F, 0.3490659F));

		PartDefinition RBL3 = root.addOrReplaceChild("RBL3",
				CubeListBuilder.create().texOffs(0, 16).addBox(-5.5F, 5.5F, -0.5F, 2F, 4F, 1F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6F, 18F, 6F, 0F, 0.3490659F, 0.6981317F));

		PartDefinition RBL4 = root.addOrReplaceChild("RBL4",
				CubeListBuilder.create().texOffs(0, 16).addBox(-3.5F, 9F, -0.5F, 1F, 4F, 1F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6F, 18F, 6F, 0F, 0.3490659F, 0.8726646F));

		PartDefinition RML1 = root.addOrReplaceChild("RML1",
				CubeListBuilder.create().texOffs(0, 16).addBox(-4F, -1F, -1.5F, 5F, 3F, 3F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8F, 17F, 0F, 0F, 0F, 0.3490659F));

		PartDefinition RML2 = root.addOrReplaceChild("RML2",
				CubeListBuilder.create().texOffs(0, 16).addBox(-5F, 0F, -1F, 2F, 4F, 2F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8F, 17F, 0F, 0F, 0F, 0.3490659F));

		PartDefinition RML3 = root
				.addOrReplaceChild("RML3",
						CubeListBuilder.create().texOffs(0, 16).addBox(-3.5F, 4.5F, -0.5F, 2F, 4F, 1F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(-8F, 17F, 0F, 0F, 0F, 0.6981317F));

		PartDefinition RML4 = root.addOrReplaceChild("RML4",
				CubeListBuilder.create().texOffs(0, 16).addBox(-1.5F, 8F, -0.5F, 1F, 4F, 1F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8F, 17F, 0F, 0F, 0F, 0.8726646F));

		PartDefinition RFL1 = root.addOrReplaceChild("RFL1",
				CubeListBuilder.create().texOffs(0, 16).addBox(-4F, -1F, -1.5F, 5F, 3F, 3F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8F, 17F, -6F, 0F, -0.3490659F, 0.3490659F));

		PartDefinition RFL2 = root.addOrReplaceChild("RFL2",
				CubeListBuilder.create().texOffs(0, 16).addBox(-5F, 0F, -1F, 2F, 4F, 2F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8F, 17F, -6F, 0F, -0.3490659F, 0.3490659F));

		PartDefinition RFL3 = root.addOrReplaceChild("RFL3",
				CubeListBuilder.create().texOffs(0, 16).addBox(-3.5F, 4.5F, -0.5F, 2F, 4F, 1F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8F, 17F, -6F, 0F, -0.3490659F, 0.6981317F));

		PartDefinition RFL4 = root.addOrReplaceChild("RFL4",
				CubeListBuilder.create().texOffs(0, 16).addBox(-1.5F, 8F, -0.5F, 1F, 4F, 1F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8F, 17F, -6F, 0F, -0.3490659F, 0.8726646F));
		
		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		root().render(stack, consumer, light, overlay, colour);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		LBL1.xRot = (float) (Math.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAmount + 0.25F);
		LBL2.xRot = (float) (Math.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAmount + 0.25F);
		LBL3.xRot = (float) (Math.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAmount + 0.3F);
		LBL4.xRot = (float) (Math.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAmount + 0.334F);
		LML1.xRot = (float) (Math.cos(limbSwing * 2.0F) * 0.7F * limbSwingAmount);
		LML2.xRot = (float) (Math.cos(limbSwing * 2.0F) * 0.7F * limbSwingAmount);
		LML3.xRot = (float) (Math.cos(limbSwing * 2.0F) * 0.7F * limbSwingAmount);
		LML4.xRot = (float) (Math.cos(limbSwing * 2.0F) * 0.7F * limbSwingAmount);
		LFL1.xRot = (float) (Math.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAmount - 0.25F);
		LFL2.xRot = (float) (Math.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAmount - 0.25F);
		LFL3.xRot = (float) (Math.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAmount - 0.3F);
		LFL4.xRot = (float) (Math.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAmount - 0.334F);
		RBL1.xRot = (float) (Math.cos(limbSwing * 2.0F) * 0.7F * limbSwingAmount + 0.25F);
		RBL2.xRot = (float) (Math.cos(limbSwing * 2.0F) * 0.7F * limbSwingAmount + 0.25F);
		RBL3.xRot = (float) (Math.cos(limbSwing * 2.0F) * 0.7F * limbSwingAmount + 0.3F);
		RBL4.xRot = (float) (Math.cos(limbSwing * 2.0F) * 0.7F * limbSwingAmount + 0.334F);
		RML1.xRot = (float) (Math.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAmount);
		RML2.xRot = (float) (Math.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAmount);
		RML3.xRot = (float) (Math.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAmount);
		RML4.xRot = (float) (Math.cos(limbSwing * 2.0F + (float) Math.PI) * 0.7F * limbSwingAmount);
		RFL1.xRot = (float) (Math.cos(limbSwing * 2.0F) * 0.7F * limbSwingAmount - 0.25F);
		RFL2.xRot = (float) (Math.cos(limbSwing * 2.0F) * 0.7F * limbSwingAmount - 0.25F);
		RFL3.xRot = (float) (Math.cos(limbSwing * 2.0F) * 0.7F * limbSwingAmount - 0.3F);
		RFL4.xRot = (float) (Math.cos(limbSwing * 2.0F) * 0.7F * limbSwingAmount - 0.334F);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}