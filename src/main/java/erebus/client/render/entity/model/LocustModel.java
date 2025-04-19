package erebus.client.render.entity.model;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import erebus.entity.Locust;
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
public class LocustModel<T extends Locust>  extends HierarchicalModel<T> {
	public ModelPart root;
	private final ModelPart HeadMain;
	private final ModelPart LeftFrontLeg;
	private final ModelPart LeftMidLeg;
	private final ModelPart LeftBackLeg;
	private final ModelPart RightFrontLeg;
	private final ModelPart RightMidLeg;
	private final ModelPart RightBackLeg;
	private final ModelPart Body;
	private final ModelPart LFWing;
	private final ModelPart RFWing;
	private final ModelPart LBWing;
	private final ModelPart RBWing;

	public LocustModel(ModelPart root) {
		this.root = root;
		this.HeadMain = root.getChild("HeadMain");
		this.LeftFrontLeg = root.getChild("LeftFrontLeg");
		this.LeftMidLeg = root.getChild("LeftMidLeg");
		this.LeftBackLeg = root.getChild("LeftBackLeg");
		this.RightFrontLeg = root.getChild("RightFrontLeg");
		this.RightMidLeg = root.getChild("RightMidLeg");
		this.RightBackLeg = root.getChild("RightBackLeg");
		this.Body = root.getChild("Body");
		this.LFWing = root.getChild("LFWing");
		this.RFWing = root.getChild("RFWing");
		this.LBWing = root.getChild("LBWing");
		this.RBWing = root.getChild("RBWing");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition HeadMain = partdefinition.addOrReplaceChild("HeadMain", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, -9.0F));

		PartDefinition Head1 = HeadMain.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(22, 13).addBox(-2.0F, -3.5F, -4.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition Head2 = HeadMain.addOrReplaceChild("Head2", CubeListBuilder.create().texOffs(24, 8).addBox(-1.5F, -2.0F, -6.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition Head3 = HeadMain.addOrReplaceChild("Head3", CubeListBuilder.create().texOffs(19, 18).addBox(-2.5F, -2.5F, -5.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition HeadLBot = HeadMain.addOrReplaceChild("HeadLBot", CubeListBuilder.create().texOffs(12, 0).addBox(1.5F, 1.5F, -4.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition HeadRBot = HeadMain.addOrReplaceChild("HeadRBot", CubeListBuilder.create().texOffs(36, 0).addBox(-2.5F, 1.5F, -4.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition HeadCBot = HeadMain.addOrReplaceChild("HeadCBot", CubeListBuilder.create().texOffs(21, 0).addBox(-1.5F, 1.5F, -5.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition LEye = HeadMain.addOrReplaceChild("LEye", CubeListBuilder.create().texOffs(5, 0).addBox(2.5F, -2.5F, -3.5F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition REye = HeadMain.addOrReplaceChild("REye", CubeListBuilder.create().texOffs(45, 0).addBox(-3.5F, -2.5F, -3.5F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition LAnt = HeadMain.addOrReplaceChild("LAnt", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, -10.5F, -4.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition RAnt = HeadMain.addOrReplaceChild("RAnt", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -10.5F, -4.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition LeftFrontLeg = partdefinition.addOrReplaceChild("LeftFrontLeg", CubeListBuilder.create(), PartPose.offset(2.0F, 17.0F, -7.0F));

		PartDefinition LFL1 = LeftFrontLeg.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(52, 0).addBox(0.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition LFL2 = LeftFrontLeg.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(43, 6).addBox(3.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition LFL3 = LeftFrontLeg.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(52, 12).addBox(1.5F, 6.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.698F));

		PartDefinition LFL4 = LeftFrontLeg.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(52, 18).addBox(0.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition LeftMidLeg = partdefinition.addOrReplaceChild("LeftMidLeg", CubeListBuilder.create(), PartPose.offset(2.0F, 17.0F, -2.0F));

		PartDefinition LML1 = LeftMidLeg.addOrReplaceChild("LML1", CubeListBuilder.create().texOffs(52, 0).addBox(0.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition LML2 = LeftMidLeg.addOrReplaceChild("LML2", CubeListBuilder.create().texOffs(43, 6).addBox(3.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition LML3 = LeftMidLeg.addOrReplaceChild("LML3", CubeListBuilder.create().texOffs(52, 12).addBox(1.5F, 6.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));

		PartDefinition LML4 = LeftMidLeg.addOrReplaceChild("LML4", CubeListBuilder.create().texOffs(52, 18).addBox(0.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition LeftBackLeg = partdefinition.addOrReplaceChild("LeftBackLeg", CubeListBuilder.create(), PartPose.offset(2.0F, 18.0F, 3.0F));

		PartDefinition LBL1 = LeftBackLeg.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(0, 34).addBox(0.5F, -4.0F, -1.5F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition LBL2 = LeftBackLeg.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(0, 24).addBox(1.0F, -10.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition LBL3 = LeftBackLeg.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(9, 8).addBox(0.5F, -13.0F, -1.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition LBL4 = LeftBackLeg.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(0, 9).addBox(1.0F, -9.0F, 6.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LBL5 = LeftBackLeg.addOrReplaceChild("LBL5", CubeListBuilder.create().texOffs(52, 5).addBox(1.5F, -4.0F, 7.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition LBL6 = LeftBackLeg.addOrReplaceChild("LBL6", CubeListBuilder.create().texOffs(41, 18).addBox(1.5F, -2.0F, 10.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition RightFrontLeg = partdefinition.addOrReplaceChild("RightFrontLeg", CubeListBuilder.create(), PartPose.offset(-2.0F, 17.0F, -7.0F));

		PartDefinition RFL1 = RightFrontLeg.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(52, 0).addBox(-4.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition RFL2 = RightFrontLeg.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(43, 6).addBox(-5.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition RFL3 = RightFrontLeg.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(52, 12).addBox(-3.5F, 6.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

		PartDefinition RFL4 = RightFrontLeg.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(52, 18).addBox(-1.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8727F));

		PartDefinition RightMidLeg = partdefinition.addOrReplaceChild("RightMidLeg", CubeListBuilder.create(), PartPose.offset(-2.0F, 17.0F, -2.0F));

		PartDefinition RML1 = RightMidLeg.addOrReplaceChild("RML1", CubeListBuilder.create().texOffs(52, 0).addBox(-4.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition RML2 = RightMidLeg.addOrReplaceChild("RML2", CubeListBuilder.create().texOffs(43, 6).addBox(-5.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition RML3 = RightMidLeg.addOrReplaceChild("RML3", CubeListBuilder.create().texOffs(52, 12).addBox(-3.5F, 6.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

		PartDefinition RML4 = RightMidLeg.addOrReplaceChild("RML4", CubeListBuilder.create().texOffs(52, 18).addBox(-1.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8727F));

		PartDefinition RightBackLeg = partdefinition.addOrReplaceChild("RightBackLeg", CubeListBuilder.create(), PartPose.offset(-2.0F, 18.0F, 3.0F));

		PartDefinition RBL1 = RightBackLeg.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(0, 34).addBox(-3.5F, -4.0F, -1.5F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition RBL2 = RightBackLeg.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 24).addBox(-3.0F, -10.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition RBL3 = RightBackLeg.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(9, 8).addBox(-3.5F, -13.0F, -1.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition RBL4 = RightBackLeg.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(0, 9).addBox(-3.0F, -9.0F, 6.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RBL5 = RightBackLeg.addOrReplaceChild("RBL5", CubeListBuilder.create().texOffs(52, 5).addBox(-2.5F, -4.0F, 7.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition RBL6 = RightBackLeg.addOrReplaceChild("RBL6", CubeListBuilder.create().texOffs(41, 18).addBox(-2.5F, -2.0F, 10.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, -9.0F));

		PartDefinition Thorax1 = Body.addOrReplaceChild("Thorax1", CubeListBuilder.create().texOffs(18, 28).addBox(-3.0F, -3.5F, 0.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition Thorax2 = Body.addOrReplaceChild("Thorax2", CubeListBuilder.create().texOffs(13, 40).addBox(-2.5F, -3.0F, 5.0F, 5.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition Ab1 = Body.addOrReplaceChild("Ab1", CubeListBuilder.create().texOffs(28, 42).addBox(-2.0F, -2.0F, 14.0F, 4.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition Ab2 = Body.addOrReplaceChild("Ab2", CubeListBuilder.create().texOffs(13, 56).addBox(-1.5F, -1.5F, 28.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition LFWing = partdefinition.addOrReplaceChild("LFWing", CubeListBuilder.create().texOffs(0, 62).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 1.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, -5.0F));

		PartDefinition RFWing = partdefinition.addOrReplaceChild("RFWing", CubeListBuilder.create().texOffs(0, 62).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 1.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, -5.0F));

		PartDefinition LBWing = partdefinition.addOrReplaceChild("LBWing", CubeListBuilder.create().texOffs(0, 62).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 1.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, -5.0F));

		PartDefinition RBWing = partdefinition.addOrReplaceChild("RBWing", CubeListBuilder.create().texOffs(0, 62).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 1.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, -5.0F));

		return LayerDefinition.create(meshdefinition, 64, 128);
	}

	@Override
	public void setupAnim(T locust, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {	
	}
		@Override
		public void prepareMobModel(T locust, float limbSwing, float limbSwingAmount, float partialRenderTicks) {
		float smoothedTicks = locust.animationTicks + (locust.animationTicks - locust.prevAnimationTicks)  * partialRenderTicks;
		float flap = (float) (Math.sin((smoothedTicks) * 1.2F) * 0.5F);
		float legx1 = (float) (Math.cos(limbSwing * 2.0F) * 0.7F * limbSwingAmount);

		if (locust.onGround()) {
			flap = 0;
			RFWing.yRot = 0F;
			RBWing.yRot = 0F;
			LFWing.yRot = 0F;
			LBWing.yRot = 0F;
		}

		if (!locust.onGround()) {
			RFWing.yRot = -1.7F;
			RBWing.yRot = -0.9F;
			LFWing.yRot = 1.7F;
			LBWing.yRot = 0.9F;
		}

		RFWing.xRot = flap;
		RBWing.xRot = flap;
		LFWing.xRot = flap;
		LBWing.xRot = flap;
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		HeadMain.render(stack, consumer, light, overlay, colour);
		LeftFrontLeg.render(stack, consumer, light, overlay, colour);
		LeftMidLeg.render(stack, consumer, light, overlay, colour);
		LeftBackLeg.render(stack, consumer, light, overlay, colour);
		RightFrontLeg.render(stack, consumer, light, overlay, colour);
		RightMidLeg.render(stack, consumer, light, overlay, colour);
		RightBackLeg.render(stack, consumer, light, overlay, colour);
		Body.render(stack, consumer, light, overlay, colour);
		LFWing.render(stack, consumer, light, overlay, colour);
		RFWing.render(stack, consumer, light, overlay, colour);
		LBWing.render(stack, consumer, light, overlay, colour);
		RBWing.render(stack, consumer, light, overlay, colour);
	}
	
	@Override
	public ModelPart root() {
		return root;
	}
}