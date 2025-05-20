package erebus.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.entity.Fly;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlyModel<T extends Fly> extends HierarchicalModel<T> {
	public ModelPart root;
	private final ModelPart thorax;
	private final ModelPart abdomen;
	private final ModelPart right_eye;
	private final ModelPart left_eye;
	private final ModelPart leg_left_back;
	private final ModelPart head;
	private final ModelPart leg_left_front;
	private final ModelPart leg_left_mid;
	private final ModelPart leg_right_back;
	private final ModelPart leg_right_front;
	private final ModelPart leg_right_mid;
	private final ModelPart wing_right;
	private final ModelPart wing_left;

	public FlyModel(ModelPart root) {
		this.root = root;
		this.thorax = root.getChild("thorax");
		this.abdomen = root.getChild("abdomen");
		this.right_eye = root.getChild("right_eye");
		this.left_eye = root.getChild("left_eye");
		this.leg_left_back = root.getChild("leg_left_back");
		this.head = root.getChild("head");
		this.leg_left_front = root.getChild("leg_left_front");
		this.leg_left_mid = root.getChild("leg_left_mid");
		this.leg_right_back = root.getChild("leg_right_back");
		this.leg_right_front = root.getChild("leg_right_front");
		this.leg_right_mid = root.getChild("leg_right_mid");
		this.wing_right = root.getChild("wing_right");
		this.wing_left = root.getChild("wing_left");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition thorax = partdefinition.addOrReplaceChild("thorax", CubeListBuilder.create().texOffs(24, 7).addBox(-2.5F, -2.5F, -2.0F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, 0.0F));

		PartDefinition abdomen = partdefinition.addOrReplaceChild("abdomen", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, 2.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, 0.0F));

		PartDefinition right_eye = partdefinition.addOrReplaceChild("right_eye", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-2.5F, -2.5F, -3.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 19.0F, -2.0F));

		PartDefinition left_eye = partdefinition.addOrReplaceChild("left_eye", CubeListBuilder.create().texOffs(24, 0).addBox(0.5F, -2.5F, -3.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, -2.0F));

		PartDefinition leg_left_back = partdefinition.addOrReplaceChild("leg_left_back", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, 0.0F, 0.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 19.0F, 0.0F, -0.3876F, -0.3613F, 0.8571F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(34, 0).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, -2.0F));

		PartDefinition leg_left_front = partdefinition.addOrReplaceChild("leg_left_front", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, 0.0F, -1.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 19.0F, -1.0F, 0.3876F, 0.3613F, 0.8571F));

		PartDefinition leg_left_mid = partdefinition.addOrReplaceChild("leg_left_mid", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, 0.0F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 19.0F, -0.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition leg_right_back = partdefinition.addOrReplaceChild("leg_right_back", CubeListBuilder.create().texOffs(0, 12).addBox(-6.0F, 0.0F, 0.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 19.0F, 0.0F, -0.3876F, 0.3613F, -0.8571F));

		PartDefinition leg_right_front = partdefinition.addOrReplaceChild("leg_right_front", CubeListBuilder.create().texOffs(0, 12).addBox(-6.0F, 0.0F, -1.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 19.0F, -1.0F, 0.3876F, -0.3613F, -0.8571F));

		PartDefinition leg_right_mid = partdefinition.addOrReplaceChild("leg_right_mid", CubeListBuilder.create().texOffs(0, 12).addBox(-6.0F, 0.0F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 19.0F, -0.5F, 0.0F, 0.0F, -0.7854F));

		PartDefinition wing_right = partdefinition.addOrReplaceChild("wing_right", CubeListBuilder.create().texOffs(0, 25).addBox(-6.0F, 0.0F, 0.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 16.0F, 0.0F, 0.5236F, -0.1745F, 0.0F));

		PartDefinition wing_left = partdefinition.addOrReplaceChild("wing_left", CubeListBuilder.create().texOffs(0, 25).mirror().addBox(0.0F, 0.0F, 0.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 16.0F, 0.0F, 0.5236F, 0.1745F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void prepareMobModel(T fly, float limbSwing, float limbSwingAmount, float partialRenderTicks) {
		float smoothedTicks = fly.animationTicks + (fly.animationTicks - (fly.prevAnimationTicks)) * partialRenderTicks;
		float flap = (float) (Math.sin((smoothedTicks) * 0.95F) * 1F);
		if (!fly.getIsFlyHanging()) {
			wing_left.xRot = 0.5235988F + flap * 0.2F;
			wing_right.xRot = 0.5235988F + flap * 0.2F;
			wing_left.zRot = 0F + flap * 0.5F;
			wing_right.zRot = 0F - flap * 0.5F;
			wing_left.yRot = 0.5235988F;
			wing_right.yRot = -0.5235988F;
		} else {
			wing_left.xRot = 0.25235988F;
			wing_right.xRot = 0.25235988F;
			wing_left.zRot = 0F;
			wing_right.zRot = 0F;
			wing_left.yRot = -0.1745329F;
			wing_right.yRot = 0.1745329F;
		}

	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		thorax.render(stack, consumer, light, overlay, colour);
		abdomen.render(stack, consumer, light, overlay, colour);
		right_eye.render(stack, consumer, light, overlay, colour);
		left_eye.render(stack, consumer, light, overlay, colour);
		leg_left_back.render(stack, consumer, light, overlay, colour);
		head.render(stack, consumer, light, overlay, colour);
		leg_left_front.render(stack, consumer, light, overlay, colour);
		leg_left_mid.render(stack, consumer, light, overlay, colour);
		leg_right_back.render(stack, consumer, light, overlay, colour);
		leg_right_front.render(stack, consumer, light, overlay, colour);
		leg_right_mid.render(stack, consumer, light, overlay, colour);
		
	}

	@Override
	public ModelPart root() {
		return root;
	}

	public void renderWings(PoseStack stack, VertexConsumer buffer, int light, int overlay, int colour) {
		wing_right.render(stack, buffer, light, overlay, colour);
		wing_left.render(stack, buffer, light, overlay, colour);
	}
}