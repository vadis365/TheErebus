package erebus.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.entity.Dragonfly;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DragonflyModel<T extends Dragonfly> extends HierarchicalModel<T> {
	public ModelPart root;
	private final ModelPart REye;
	private final ModelPart LEye;
	private final ModelPart HeadFront;
	private final ModelPart HeadTop;
	private final ModelPart HeadMain;
	private final ModelPart Neck;
	private final ModelPart ThoraxFront;
	private final ModelPart ThoraxBottom;
	private final ModelPart ThoraxBack;
	private final ModelPart RFWing;
	private final ModelPart RBWing;
	private final ModelPart LFWing;
	private final ModelPart LBWing;
	private final ModelPart Tail1;
	private final ModelPart Tail2;
	private final ModelPart Tail3;
	private final ModelPart Tail4;
	private final ModelPart Tail5;
	private final ModelPart Tail6;
	private final ModelPart Tail7;
	private final ModelPart Tail8;
	private final ModelPart Tail9R;
	private final ModelPart Tail9L;
	private final ModelPart RFLeg1;
	private final ModelPart RFLeg2;
	private final ModelPart RMLeg1;
	private final ModelPart RMLeg2;
	private final ModelPart RBLeg1;
	private final ModelPart RBLeg2;
	private final ModelPart LFLeg1;
	private final ModelPart LFLeg2;
	private final ModelPart LMLeg1;
	private final ModelPart LMLeg2;
	private final ModelPart LBLeg1;
	private final ModelPart LBLeg2;

	public DragonflyModel(ModelPart root) {
		this.root = root;
		this.REye = root.getChild("REye");
		this.LEye = root.getChild("LEye");
		this.HeadFront = root.getChild("HeadFront");
		this.HeadTop = root.getChild("HeadTop");
		this.HeadMain = root.getChild("HeadMain");
		this.Neck = root.getChild("Neck");
		this.ThoraxFront = root.getChild("ThoraxFront");
		this.ThoraxBottom = root.getChild("ThoraxBottom");
		this.ThoraxBack = root.getChild("ThoraxBack");
		this.RFWing = root.getChild("RFWing");
		this.RBWing = root.getChild("RBWing");
		this.LFWing = root.getChild("LFWing");
		this.LBWing = root.getChild("LBWing");
		this.Tail1 = root.getChild("Tail1");
		this.Tail2 = root.getChild("Tail2");
		this.Tail3 = root.getChild("Tail3");
		this.Tail4 = root.getChild("Tail4");
		this.Tail5 = root.getChild("Tail5");
		this.Tail6 = root.getChild("Tail6");
		this.Tail7 = root.getChild("Tail7");
		this.Tail8 = root.getChild("Tail8");
		this.Tail9R = root.getChild("Tail9R");
		this.Tail9L = root.getChild("Tail9L");
		this.RFLeg1 = root.getChild("RFLeg1");
		this.RFLeg2 = root.getChild("RFLeg2");
		this.RMLeg1 = root.getChild("RMLeg1");
		this.RMLeg2 = root.getChild("RMLeg2");
		this.RBLeg1 = root.getChild("RBLeg1");
		this.RBLeg2 = root.getChild("RBLeg2");
		this.LFLeg1 = root.getChild("LFLeg1");
		this.LFLeg2 = root.getChild("LFLeg2");
		this.LMLeg1 = root.getChild("LMLeg1");
		this.LMLeg2 = root.getChild("LMLeg2");
		this.LBLeg1 = root.getChild("LBLeg1");
		this.LBLeg2 = root.getChild("LBLeg2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition REye = partdefinition.addOrReplaceChild("REye", CubeListBuilder.create().texOffs(71, 5).addBox(-1.5F, -2.5F, -3.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, -13.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition LEye = partdefinition.addOrReplaceChild("LEye", CubeListBuilder.create().texOffs(45, 5).addBox(-1.5F, -2.5F, -3.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, -13.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition HeadFront = partdefinition.addOrReplaceChild("HeadFront", CubeListBuilder.create().texOffs(62, 0).addBox(-0.5F, -1.5F, -4.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, -13.0F));

		PartDefinition HeadTop = partdefinition.addOrReplaceChild("HeadTop", CubeListBuilder.create().texOffs(61, 12).addBox(-0.5F, -2.5F, -3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, -13.0F));

		PartDefinition HeadMain = partdefinition.addOrReplaceChild("HeadMain", CubeListBuilder.create().texOffs(58, 5).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, -13.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition Neck = partdefinition.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(59, 16).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, -13.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition ThoraxFront = partdefinition.addOrReplaceChild("ThoraxFront", CubeListBuilder.create().texOffs(52, 22).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -10.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition ThoraxBottom = partdefinition.addOrReplaceChild("ThoraxBottom", CubeListBuilder.create().texOffs(52, 33).addBox(-2.0F, 1.5F, -2.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -10.0F));

		PartDefinition ThoraxBack = partdefinition.addOrReplaceChild("ThoraxBack", CubeListBuilder.create().texOffs(55, 43).addBox(-2.5F, -2.5F, 2.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -10.0F));

		PartDefinition RFWing = partdefinition.addOrReplaceChild("RFWing", CubeListBuilder.create().texOffs(80, 29).addBox(-18.0F, 0.0F, -3.0F, 18.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 13.0F, -10.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition RBWing = partdefinition.addOrReplaceChild("RBWing", CubeListBuilder.create().texOffs(80, 36).addBox(-18.0F, 0.0F, -3.0F, 18.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 13.0F, -6.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition LFWing = partdefinition.addOrReplaceChild("LFWing", CubeListBuilder.create().texOffs(0, 29).addBox(0.0F, 0.0F, -3.0F, 18.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 13.0F, -10.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition LBWing = partdefinition.addOrReplaceChild("LBWing", CubeListBuilder.create().texOffs(0, 36).addBox(0.0F, 0.0F, -3.0F, 18.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 13.0F, -6.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition Tail1 = partdefinition.addOrReplaceChild("Tail1", CubeListBuilder.create().texOffs(57, 52).addBox(-1.5F, -1.5F, 6.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -10.0F));

		PartDefinition Tail2 = partdefinition.addOrReplaceChild("Tail2", CubeListBuilder.create().texOffs(61, 60).addBox(-1.0F, -1.0F, 10.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -10.0F));

		PartDefinition Tail3 = partdefinition.addOrReplaceChild("Tail3", CubeListBuilder.create().texOffs(57, 64).addBox(-1.5F, -1.5F, 11.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -10.0F));

		PartDefinition Tail4 = partdefinition.addOrReplaceChild("Tail4", CubeListBuilder.create().texOffs(62, 72).addBox(-0.5F, -0.5F, 15.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -10.0F));

		PartDefinition Tail5 = partdefinition.addOrReplaceChild("Tail5", CubeListBuilder.create().texOffs(58, 75).addBox(-1.0F, -1.0F, 15.5F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -10.0F));

		PartDefinition Tail6 = partdefinition.addOrReplaceChild("Tail6", CubeListBuilder.create().texOffs(62, 82).addBox(-0.5F, -0.5F, 19.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -10.0F));

		PartDefinition Tail7 = partdefinition.addOrReplaceChild("Tail7", CubeListBuilder.create().texOffs(58, 85).addBox(-1.0F, -1.0F, 20.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -10.0F));

		PartDefinition Tail8 = partdefinition.addOrReplaceChild("Tail8", CubeListBuilder.create().texOffs(61, 92).addBox(-0.5F, -0.5F, 24.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -10.0F));

		PartDefinition Tail9R = partdefinition.addOrReplaceChild("Tail9R", CubeListBuilder.create().texOffs(68, 92).addBox(-1.5F, -0.5F, 25.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -10.0F));

		PartDefinition Tail9L = partdefinition.addOrReplaceChild("Tail9L", CubeListBuilder.create().texOffs(52, 92).addBox(0.5F, -0.5F, 25.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -10.0F));

		PartDefinition RFLeg1 = partdefinition.addOrReplaceChild("RFLeg1", CubeListBuilder.create().texOffs(80, 18).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 17.0F, -13.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition RFLeg2 = partdefinition.addOrReplaceChild("RFLeg2", CubeListBuilder.create().texOffs(80, 23).addBox(-0.5F, 2.4F, 1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 17.0F, -13.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition RMLeg1 = partdefinition.addOrReplaceChild("RMLeg1", CubeListBuilder.create().texOffs(80, 43).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 17.0F, -11.0F, 1.0472F, 0.0F, 0.0F));

		PartDefinition RMLeg2 = partdefinition.addOrReplaceChild("RMLeg2", CubeListBuilder.create().texOffs(80, 49).addBox(-0.5F, 0.5F, 3.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 17.0F, -11.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition RBLeg1 = partdefinition.addOrReplaceChild("RBLeg1", CubeListBuilder.create().texOffs(80, 58).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 16.0F, -9.0F, 1.0472F, 0.0F, 0.0F));

		PartDefinition RBLeg2 = partdefinition.addOrReplaceChild("RBLeg2", CubeListBuilder.create().texOffs(80, 65).addBox(-0.5F, 0.5F, 4.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 16.0F, -9.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition LFLeg1 = partdefinition.addOrReplaceChild("LFLeg1", CubeListBuilder.create().texOffs(44, 18).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 17.0F, -13.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition LFLeg2 = partdefinition.addOrReplaceChild("LFLeg2", CubeListBuilder.create().texOffs(44, 23).addBox(-0.5F, 2.4F, 1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 17.0F, -13.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition LMLeg1 = partdefinition.addOrReplaceChild("LMLeg1", CubeListBuilder.create().texOffs(44, 43).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 17.0F, -11.0F, 1.0472F, 0.0F, 0.0F));

		PartDefinition LMLeg2 = partdefinition.addOrReplaceChild("LMLeg2", CubeListBuilder.create().texOffs(44, 49).addBox(-0.5F, 0.5F, 3.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 17.0F, -11.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition LBLeg1 = partdefinition.addOrReplaceChild("LBLeg1", CubeListBuilder.create().texOffs(44, 58).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 16.0F, -9.0F, 1.0472F, 0.0F, 0.0F));

		PartDefinition LBLeg2 = partdefinition.addOrReplaceChild("LBLeg2", CubeListBuilder.create().texOffs(44, 65).addBox(-0.5F, 0.5F, 4.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 16.0F, -9.0F, -0.5236F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void prepareMobModel(T dragonfly, float limbSwing, float limbSwingAngle, float partialRenderTicks) {
		float smoothedTicks = dragonfly.animationTicks + (dragonfly.animationTicks - dragonfly.prevAnimationTicks)  * partialRenderTicks;
		float flapFront = (float) (Math.sin((smoothedTicks) * 1.8F) * 0.35F);
		float flapBack = (float) (Math.cos((smoothedTicks) * 1.8F) * 0.35F);
		RFWing.zRot = flapFront;
		LFWing.zRot = -flapFront;
		RBWing.zRot = flapBack;
		LBWing.zRot = -flapBack;
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		REye.render(stack, consumer, light, overlay, colour);
		LEye.render(stack, consumer, light, overlay, colour);
		HeadFront.render(stack, consumer, light, overlay, colour);
		HeadTop.render(stack, consumer, light, overlay, colour);
		HeadMain.render(stack, consumer, light, overlay, colour);
		Neck.render(stack, consumer, light, overlay, colour);
		ThoraxFront.render(stack, consumer, light, overlay, colour);
		ThoraxBottom.render(stack, consumer, light, overlay, colour);
		ThoraxBack.render(stack, consumer, light, overlay, colour);
		Tail1.render(stack, consumer, light, overlay, colour);
		Tail2.render(stack, consumer, light, overlay, colour);
		Tail3.render(stack, consumer, light, overlay, colour);
		Tail4.render(stack, consumer, light, overlay, colour);
		Tail5.render(stack, consumer, light, overlay, colour);
		Tail6.render(stack, consumer, light, overlay, colour);
		Tail7.render(stack, consumer, light, overlay, colour);
		Tail8.render(stack, consumer, light, overlay, colour);
		Tail9R.render(stack, consumer, light, overlay, colour);
		Tail9L.render(stack, consumer, light, overlay, colour);
		RFLeg1.render(stack, consumer, light, overlay, colour);
		RFLeg2.render(stack, consumer, light, overlay, colour);
		RMLeg1.render(stack, consumer, light, overlay, colour);
		RMLeg2.render(stack, consumer, light, overlay, colour);
		RBLeg1.render(stack, consumer, light, overlay, colour);
		RBLeg2.render(stack, consumer, light, overlay, colour);
		LFLeg1.render(stack, consumer, light, overlay, colour);
		LFLeg2.render(stack, consumer, light, overlay, colour);
		LMLeg1.render(stack, consumer, light, overlay, colour);
		LMLeg2.render(stack, consumer, light, overlay, colour);
		LBLeg1.render(stack, consumer, light, overlay, colour);
		LBLeg2.render(stack, consumer, light, overlay, colour);
	}
	
	@Override
	public ModelPart root() {
		return root;
	}

	public void renderWings(PoseStack stack, VertexConsumer buffer, int light, int overlay, int colour) {
		RFWing.render(stack, buffer, light, overlay, colour);
		RBWing.render(stack, buffer, light, overlay, colour);
		LFWing.render(stack, buffer, light, overlay, colour);
		LBWing.render(stack, buffer, light, overlay, colour);
	}
}
