package erebus.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.entity.Grasshopper;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrasshopperModel<T extends Grasshopper> extends HierarchicalModel<T> {
	public ModelPart root;
	private final ModelPart HeadMain;
	private final ModelPart RAnt;
	private final ModelPart LAnt;
	private final ModelPart LeftFrontLeg;
	private final ModelPart LeftMidLeg;
	private final ModelPart LeftBackLeg;
	private final ModelPart RightFrontLeg;
	private final ModelPart RightMidLeg;
	private final ModelPart RightBackLeg;
	private final ModelPart Body;
	private final ModelPart RBL4;
	private final ModelPart RBL5;
	private final ModelPart RBL6;
	private final ModelPart LBL4;
	private final ModelPart LBL5;
	private final ModelPart LBL6;

	public GrasshopperModel(ModelPart root) {
		this.root = root;
		this.HeadMain = root.getChild("HeadMain");
		this.RAnt = HeadMain.getChild("RAnt");
		this.LAnt = HeadMain.getChild("LAnt");
		this.LeftFrontLeg = root.getChild("LeftFrontLeg");
		this.LeftMidLeg = root.getChild("LeftMidLeg");
		this.LeftBackLeg = root.getChild("LeftBackLeg");
		this.RightFrontLeg = root.getChild("RightFrontLeg");
		this.RightMidLeg = root.getChild("RightMidLeg");
		this.RightBackLeg = root.getChild("RightBackLeg");
		this.Body = root.getChild("Body");
		RBL4 = RightBackLeg.getChild("RBL3").getChild("RBL4");
		RBL5 = RBL4.getChild("RBL5");
		RBL6 = RBL5.getChild("RBL6");
		LBL4 = LeftBackLeg.getChild("LBL3").getChild("LBL4");
		LBL5 = LBL4.getChild("LBL5");
		LBL6 = LBL5.getChild("LBL6");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();

		PartDefinition HeadMain = root.addOrReplaceChild("HeadMain", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, -9.0F));

		HeadMain.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(22, 13).addBox(-2.0F, -3.5F, -4.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		HeadMain.addOrReplaceChild("Head2", CubeListBuilder.create().texOffs(24, 8).addBox(-1.5F, -2.0F, -6.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		HeadMain.addOrReplaceChild("Head3", CubeListBuilder.create().texOffs(19, 18).addBox(-2.5F, -2.5F, -5.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		HeadMain.addOrReplaceChild("HeadLBot", CubeListBuilder.create().texOffs(12, 0).addBox(1.5F, 1.5F, -4.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		HeadMain.addOrReplaceChild("HeadRBot", CubeListBuilder.create().texOffs(36, 0).addBox(-2.5F, 1.5F, -4.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		HeadMain.addOrReplaceChild("HeadCBot", CubeListBuilder.create().texOffs(21, 0).addBox(-1.5F, 1.5F, -5.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		HeadMain.addOrReplaceChild("LEye", CubeListBuilder.create().texOffs(5, 0).addBox(2.5F, -2.5F, -3.5F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		HeadMain.addOrReplaceChild("REye", CubeListBuilder.create().texOffs(45, 0).addBox(-3.5F, -2.5F, -3.5F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		HeadMain.addOrReplaceChild("LAnt", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -6.851F, -0.5817F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -3.0F, -4.0F, 0.1745F, 0.0F, 0.0F));

		HeadMain.addOrReplaceChild("RAnt", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -6.851F, -0.5817F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -3.0F, -4.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition LeftFrontLeg = root.addOrReplaceChild("LeftFrontLeg", CubeListBuilder.create(), PartPose.offset(2.0F, 17.0F, -7.0F));

		LeftFrontLeg.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(52, 0).addBox(0.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		LeftFrontLeg.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(43, 6).addBox(3.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

		LeftFrontLeg.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(52, 12).addBox(1.5F, 6.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.698F));

		LeftFrontLeg.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(52, 18).addBox(0.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition LeftMidLeg = root.addOrReplaceChild("LeftMidLeg", CubeListBuilder.create(), PartPose.offset(2.0F, 17.0F, -2.0F));

		LeftMidLeg.addOrReplaceChild("LML1", CubeListBuilder.create().texOffs(52, 0).addBox(0.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		LeftMidLeg.addOrReplaceChild("LML2", CubeListBuilder.create().texOffs(43, 6).addBox(3.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

		LeftMidLeg.addOrReplaceChild("LML3", CubeListBuilder.create().texOffs(52, 12).addBox(1.5F, 6.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));

		LeftMidLeg.addOrReplaceChild("LML4", CubeListBuilder.create().texOffs(52, 18).addBox(0.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition LeftBackLeg = root.addOrReplaceChild("LeftBackLeg", CubeListBuilder.create(), PartPose.offset(2.0F, 18.0F, 3.0F));

		LeftBackLeg.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(0, 34).addBox(0.5F, -4.0F, -1.5F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		LeftBackLeg.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(0, 24).addBox(1.0F, -10.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition LBL3 = LeftBackLeg.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(9, 8).addBox(0.5F, -13.0F, -1.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition LBL4 = LBL3.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(0, 9).addBox(-1.0F, -0.4737F, -1.2321F, 2.0F, 12.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(2.0F, -11.0F, 2.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition LBL5 = LBL4.addOrReplaceChild("LBL5", CubeListBuilder.create().texOffs(52, 5).addBox(-0.5F, -1.0326F, 0.3521F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.5263F, -0.2321F, -0.6981F, 0.0F, 0.0F));

		PartDefinition LBL6 = LBL5.addOrReplaceChild("LBL6", CubeListBuilder.create().texOffs(41, 18).addBox(-0.5F, -0.7821F, 0.131F, 1.0F, 1.0F, 4.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, -0.0326F, 3.3521F, 0.1745F, 0.0F, 0.0F));

		PartDefinition RightFrontLeg = root.addOrReplaceChild("RightFrontLeg", CubeListBuilder.create(), PartPose.offset(-2.0F, 17.0F, -7.0F));

		RightFrontLeg.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(52, 0).addBox(-4.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		RightFrontLeg.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(43, 6).addBox(-5.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

		RightFrontLeg.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(52, 12).addBox(-3.5F, 6.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

		RightFrontLeg.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(52, 18).addBox(-1.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8727F));

		PartDefinition RightMidLeg = root.addOrReplaceChild("RightMidLeg", CubeListBuilder.create(), PartPose.offset(-2.0F, 17.0F, -2.0F));

		RightMidLeg.addOrReplaceChild("RML1", CubeListBuilder.create().texOffs(52, 0).addBox(-4.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		RightMidLeg.addOrReplaceChild("RML2", CubeListBuilder.create().texOffs(43, 6).addBox(-5.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

		RightMidLeg.addOrReplaceChild("RML3", CubeListBuilder.create().texOffs(52, 12).addBox(-3.5F, 6.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

		RightMidLeg.addOrReplaceChild("RML4", CubeListBuilder.create().texOffs(52, 18).addBox(-1.5F, 8.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8727F));

		PartDefinition RightBackLeg = root.addOrReplaceChild("RightBackLeg", CubeListBuilder.create(), PartPose.offset(-2.0F, 18.0F, 3.0F));

		RightBackLeg.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(0, 34).addBox(-3.5F, -4.0F, -1.5F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		RightBackLeg.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 24).addBox(-3.0F, -10.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition RBL3 = RightBackLeg.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(9, 8).addBox(-3.5F, -13.0F, -1.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition RBL4 = RBL3.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(0, 9).addBox(-1.0F, -0.4737F, -1.2321F, 2.0F, 12.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-2.0F, -11.0F, 2.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition RBL5 = RBL4.addOrReplaceChild("RBL5", CubeListBuilder.create().texOffs(52, 5).addBox(-0.5F, -1.0326F, 0.3521F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.5263F, -0.2321F, -0.6981F, 0.0F, 0.0F));

		PartDefinition RBL6 = RBL5.addOrReplaceChild("RBL6", CubeListBuilder.create().texOffs(41, 18).addBox(-0.5F, -0.7821F, 0.131F, 1.0F, 1.0F, 4.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, -0.0326F, 3.3521F, 0.1745F, 0.0F, 0.0F));
		
		PartDefinition Body = root.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, -9.0F));

		Body.addOrReplaceChild("Thorax1", CubeListBuilder.create().texOffs(18, 28).addBox(-3.0F, -3.5F, 0.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		Body.addOrReplaceChild("Thorax2", CubeListBuilder.create().texOffs(13, 40).addBox(-2.5F, -3.0F, 5.0F, 5.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		Body.addOrReplaceChild("Ab1", CubeListBuilder.create().texOffs(28, 42).addBox(-2.0F, -2.0F, 14.0F, 4.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		Body.addOrReplaceChild("Ab2", CubeListBuilder.create().texOffs(13, 56).addBox(-1.5F, -1.5F, 28.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T grasshopper, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		HeadMain.yRot = netHeadYaw / (180F / (float) Math.PI);
	}

	@Override
	public void prepareMobModel(T grasshopper, float limbSwing, float limbSwingAngle, float partialRenderTicks) {
		float smoothedTicks = grasshopper.animationTicks + (grasshopper.animationTicks - grasshopper.prevAnimationTicks)  * partialRenderTicks;
		float antSin = Mth.sin((smoothedTicks) * 0.25F) * 0.125F;
		float antCos = Mth.cos((smoothedTicks) * 0.25F) * 0.125F;
		float jumpAngle = Mth.sin(grasshopper.getJumpCompletion(partialRenderTicks) * (float) Math.PI);

		LeftFrontLeg.xRot = -jumpAngle * 50.0F * (float) (Math.PI / 180.0);
		LeftMidLeg.xRot = jumpAngle * 50.0F * (float) (Math.PI / 180.0);
		RightFrontLeg.xRot = -jumpAngle * 50.0F * (float) (Math.PI / 180.0);
		RightMidLeg.xRot = jumpAngle * 50.0F * (float) (Math.PI / 180.0);
		LeftBackLeg.xRot = -jumpAngle * 75.0F * (float) (Math.PI / 180.0);
		RightBackLeg.xRot = -jumpAngle * 75.0F * (float) (Math.PI / 180.0);
		RBL4.xRot = 0.5236F - RightBackLeg.xRot + jumpAngle * 75.0F * (float) (Math.PI / 180.0);
		RBL5.xRot = -0.6981F + RightBackLeg.xRot + jumpAngle * 50.0F * (float) (Math.PI / 180.0);
		RBL6.xRot = 0.1745F + RightBackLeg.xRot + jumpAngle * 50.0F * (float) (Math.PI / 180.0);
		LBL4.xRot = 0.5236F - LeftBackLeg.xRot + jumpAngle * 75.0F * (float) (Math.PI / 180.0);
		LBL5.xRot = -0.6981F + LeftBackLeg.xRot + jumpAngle * 50.0F * (float) (Math.PI / 180.0);
		LBL6.xRot = 0.1745F + LeftBackLeg.xRot + jumpAngle * 50.0F * (float) (Math.PI / 180.0);
		HeadMain.xRot = -0.1745F + jumpAngle * 20.0F * (float) (Math.PI / 180.0);

		if (grasshopper.onGround()) {
			LAnt.zRot = 0F + antSin;
			LAnt.yRot = 0F + antCos;
			RAnt.zRot = 0F - antCos;
			RAnt.yRot = 0F - antSin;
		}
		else {
			LAnt.zRot = 0F;
			LAnt.yRot = 0F;
			RAnt.zRot = 0F;
			RAnt.yRot = 0F;
		}
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
	}
	
	@Override
	public ModelPart root() {
		return root;
	}
}
