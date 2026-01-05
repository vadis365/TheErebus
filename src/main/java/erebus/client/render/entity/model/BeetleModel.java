package erebus.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.entity.Beetle;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BeetleModel<T extends Beetle> extends HierarchicalModel<T> {
	public ModelPart root;
	private final ModelPart backbody;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart bum;
	private final ModelPart head2;
	private final ModelPart LeftFrontLeg;
	private final ModelPart LeftMidLeg;
	private final ModelPart LeftBackLeg;
	private final ModelPart RightFrontLeg;
	private final ModelPart RightMidLeg;
	private final ModelPart RightBackLeg;

	public BeetleModel(ModelPart root) {
		super(RenderType::entityCutout);
		this.root = root;
		this.backbody = root.getChild("backbody");
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.bum = root.getChild("bum");
		this.head2 = root.getChild("head2");
		this.LeftFrontLeg = root.getChild("LeftFrontLeg");
		this.LeftMidLeg = root.getChild("LeftMidLeg");
		this.LeftBackLeg = root.getChild("LeftBackLeg");
		this.RightFrontLeg = root.getChild("RightFrontLeg");
		this.RightMidLeg = root.getChild("RightMidLeg");
		this.RightBackLeg = root.getChild("RightBackLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();

		PartDefinition backbody = root.addOrReplaceChild("backbody", CubeListBuilder.create().texOffs(35, 0).addBox(-6.0F, -1.0F, -6.0F, 14.0F, 9.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 15.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 20).addBox(-3.0F, 1.0F, -11.0F, 12.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 14.0F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(1, 10).addBox(-1.0F, 2.0F, -8.0F, 6.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 15.0F, -6.0F));

		PartDefinition bum = root.addOrReplaceChild("bum", CubeListBuilder.create().texOffs(35, 27).addBox(-5.0F, 1.0F, -6.0F, 12.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 15.0F, 17.0F));

		PartDefinition head2 = root.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(2, 6).addBox(-4.0F, 4.0F, -11.0F, 12.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 15.0F, -6.0F));

		PartDefinition LeftFrontLeg = root.addOrReplaceChild("LeftFrontLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, 20.0F, -4.0F, 0.0F, 0.5236F, 0.0F));

		PartDefinition LFL1 = LeftFrontLeg.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(0, 95).addBox(-4.9397F, -0.658F, -2.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-1.0F, 0.0F, -1.0F, 0.0F, 3.1416F, -0.3491F));

		LFL1.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(0, 88).addBox(-3.8377F, 2.9409F, 5.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0261F, -2.8191F, -7.0F, 0.0F, 0.0F, 0.3491F));

		LFL1.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(0, 82).addBox(-1.4019F, 6.866F, 5.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0261F, -2.8191F, -7.0F, 0.0F, 0.0F, 0.6981F));

		LFL1.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(0, 76).addBox(0.9771F, 9.9658F, 5.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-1.0261F, -2.8191F, -7.0F, 0.0F, 0.0F, 0.8727F));

		PartDefinition LeftMidLeg = root.addOrReplaceChild("LeftMidLeg", CubeListBuilder.create(), PartPose.offset(6.0F, 20.0F, 2.5F));

		PartDefinition LML1 = LeftMidLeg.addOrReplaceChild("LML1", CubeListBuilder.create().texOffs(0, 95).addBox(-3.9136F, 2.1611F, 5.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-1.0F, -3.0F, 6.0F, 0.0F, -3.1416F, -0.3491F));

		LML1.addOrReplaceChild("LML2", CubeListBuilder.create().texOffs(0, 88).addBox(-3.8377F, 2.9409F, 5.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		LML1.addOrReplaceChild("LML3", CubeListBuilder.create().texOffs(0, 82).addBox(-1.4019F, 6.866F, 5.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

		LML1.addOrReplaceChild("LML4", CubeListBuilder.create().texOffs(0, 76).addBox(0.9771F, 9.9658F, 5.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8727F));

		PartDefinition LeftBackLeg = root.addOrReplaceChild("LeftBackLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, 20.0F, 9.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition LBL1 = LeftBackLeg.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(0, 95).addBox(-3.9136F, 2.1611F, 5.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-1.0F, -3.0F, 6.0F, 0.0F, 3.1416F, -0.3491F));

		LBL1.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(0, 88).addBox(-3.8377F, 2.9409F, 5.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		LBL1.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(0, 82).addBox(-1.4019F, 6.866F, 5.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

		LBL1.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(0, 76).addBox(0.9771F, 9.9658F, 5.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8727F));

		PartDefinition RightFrontLeg = root.addOrReplaceChild("RightFrontLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.0F, 20.0F, -4.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition RFL1 = RightFrontLeg.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(0, 95).addBox(-2.0342F, 1.4771F, -7.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-1.0F, -3.0F, 6.0F, 0.0F, 0.0F, 0.3491F));

		RFL1.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(0, 88).addBox(-2.3056F, 1.6553F, -7.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		RFL1.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(0, 82).addBox(-0.4019F, 5.134F, -6.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

		RFL1.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(0, 76).addBox(1.6611F, 8.0864F, -6.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8727F));

		PartDefinition RightMidLeg = root.addOrReplaceChild("RightMidLeg", CubeListBuilder.create(), PartPose.offset(-6.0F, 20.0F, 2.5F));

		PartDefinition RML1 = RightMidLeg.addOrReplaceChild("RML1", CubeListBuilder.create().texOffs(0, 95).addBox(-2.0342F, 1.4771F, -7.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-1.0F, -3.0F, 6.0F, 0.0F, 0.0F, 0.3491F));

		RML1.addOrReplaceChild("RML2", CubeListBuilder.create().texOffs(0, 88).addBox(-2.3056F, 1.6553F, -7.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		RML1.addOrReplaceChild("RML3", CubeListBuilder.create().texOffs(0, 82).addBox(-0.4019F, 5.134F, -6.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

		RML1.addOrReplaceChild("RML4", CubeListBuilder.create().texOffs(0, 76).addBox(1.6611F, 8.0864F, -6.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8727F));

		PartDefinition RightBackLeg = root.addOrReplaceChild("RightBackLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.0F, 20.0F, 9.0F, 0.0F, 0.5236F, 0.0F));

		PartDefinition RBL1 = RightBackLeg.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(0, 95).addBox(-2.0342F, 1.4771F, -7.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-1.0F, -3.0F, 6.0F, 0.0F, 0.0F, 0.3491F));

		RBL1.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 88).addBox(-2.3056F, 1.6553F, -7.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		RBL1.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(0, 82).addBox(-0.4019F, 5.134F, -6.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

		RBL1.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(0, 76).addBox(1.6611F, 8.0864F, -6.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8727F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float sin = Mth.sin(limbSwing) * 0.8F * limbSwingAmount * 0.8F;
		float cos = Mth.cos(limbSwing) * 0.2F * limbSwingAmount;

		LeftBackLeg.zRot = -cos;
		LeftMidLeg.zRot = cos;
		LeftFrontLeg.zRot = -cos;
		RightBackLeg.zRot = -cos;
		RightMidLeg.zRot = cos;
		RightFrontLeg.zRot = -cos;
		LeftBackLeg.yRot = -0.5236F + sin;
		LeftMidLeg.yRot = 0F - sin;
		LeftFrontLeg.yRot = 0.5236F + sin;
		RightBackLeg.yRot = 0.5236F + sin;
		RightMidLeg.yRot = 0F - sin;
		RightFrontLeg.yRot = -0.5236F + sin;
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		backbody.render(stack, consumer, light, overlay, colour);
		body.render(stack, consumer, light, overlay, colour);
		head.render(stack, consumer, light, overlay, colour);
		bum.render(stack, consumer, light, overlay, colour);
		head2.render(stack, consumer, light, overlay, colour);
		LeftFrontLeg.render(stack, consumer, light, overlay, colour);
		LeftMidLeg.render(stack, consumer, light, overlay, colour);
		LeftBackLeg.render(stack, consumer, light, overlay, colour);
		RightFrontLeg.render(stack, consumer, light, overlay, colour);
		RightMidLeg.render(stack, consumer, light, overlay, colour);
		RightBackLeg.render(stack, consumer, light, overlay, colour);
	}
	
	@Override
	public ModelPart root() {
		return root;
	}
}
