package erebus.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.entity.BeetleLarva;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;

public class BeetleLarvaModel<T extends BeetleLarva> extends HierarchicalModel<T> {
	public ModelPart root;
	private final ModelPart torso1;
	private final ModelPart torso2;
	private final ModelPart torso3;
	private final ModelPart torso4;
	private final ModelPart torso5;
	private final ModelPart torso6;
	private final ModelPart torso7;
	private final ModelPart torso8;
	private final ModelPart legleft1;
	private final ModelPart legright1;
	private final ModelPart legleft2;
	private final ModelPart legright2;
	private final ModelPart legleft3;
	private final ModelPart legright3;
	private final ModelPart head;
	private final ModelPart jawleft;
	private final ModelPart jawright;
	private final ModelPart mouthjaw;
	private final ModelPart sensorleft;
	private final ModelPart sensorright;
	private final ModelPart horn1;
	private final ModelPart horn2;
	private final ModelPart horn3;
	private final ModelPart titanL1;
	private final ModelPart titanL2;
	private final ModelPart titanR1;
	private final ModelPart titanR2;
	private final ModelPart jawStagLeft;
	private final ModelPart jawStagRight;

	public BeetleLarvaModel(ModelPart root) {
		super(RenderType::entityCutout);
		this.root = root;
		this.torso1 = root.getChild("torso1");
		this.torso2 = root.getChild("torso2");
		this.torso3 = root.getChild("torso3");
		this.torso4 = root.getChild("torso4");
		this.torso5 = root.getChild("torso5");
		this.torso6 = root.getChild("torso6");
		this.torso7 = root.getChild("torso7");
		this.torso8 = root.getChild("torso8");
		this.legleft1 = root.getChild("legleft1");
		this.legright1 = root.getChild("legright1");
		this.legleft2 = root.getChild("legleft2");
		this.legright2 = root.getChild("legright2");
		this.legleft3 = root.getChild("legleft3");
		this.legright3 = root.getChild("legright3");
		this.head = root.getChild("head");
		this.jawleft = root.getChild("jawleft");
		this.jawright = root.getChild("jawright");
		this.mouthjaw = root.getChild("mouthjaw");
		this.sensorleft = root.getChild("sensorleft");
		this.sensorright = root.getChild("sensorright");
		this.horn1 = root.getChild("horn1");
		this.horn2 = root.getChild("horn2");
		this.horn3 = root.getChild("horn3");
		this.titanL1 = root.getChild("titanL1");
		this.titanL2 = root.getChild("titanL2");
		this.titanR1 = root.getChild("titanR1");
		this.titanR2 = root.getChild("titanR2");
		this.jawStagLeft = root.getChild("jawStagLeft");
		this.jawStagRight = root.getChild("jawStagRight");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();

		PartDefinition torso1 = root.addOrReplaceChild("torso1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, 0.1487F, 0.0F, 0.0F));

		PartDefinition torso2 = root.addOrReplaceChild("torso2", CubeListBuilder.create().texOffs(0, 8).addBox(-3.5F, -2.5F, 1.0F, 7.0F, 6.0F, 2.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, 0.0744F, 0.0F, 0.0F));

		PartDefinition torso3 = root.addOrReplaceChild("torso3", CubeListBuilder.create().texOffs(0, 17).addBox(-3.5F, -3.5F, 2.5F, 7.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.0546F, 0.0F, 0.0F));

		PartDefinition torso4 = root.addOrReplaceChild("torso4", CubeListBuilder.create().texOffs(0, 31).addBox(-3.5F, -3.1F, 8.0F, 7.0F, 7.0F, 5.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, 20.0F, -10.0F));

		PartDefinition torso5 = root.addOrReplaceChild("torso5", CubeListBuilder.create().texOffs(0, 44).addBox(-3.5F, -3.5F, 12.5F, 7.0F, 6.0F, 3.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.0766F, 0.0F, 0.0F));

		PartDefinition torso6 = root.addOrReplaceChild("torso6", CubeListBuilder.create().texOffs(0, 54).addBox(-3.0F, -3.5F, 15.5F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.0766F, 0.0F, 0.0F));

		PartDefinition torso7 = root.addOrReplaceChild("torso7", CubeListBuilder.create().texOffs(17, 54).addBox(-2.5F, -6.0F, 16.5F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.2603F, 0.0F, 0.0F));

		PartDefinition torso8 = root.addOrReplaceChild("torso8", CubeListBuilder.create().texOffs(32, 54).addBox(-2.0F, -6.5F, 18.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.3346F, 0.0F, 0.0F));

		PartDefinition legleft1 = root.addOrReplaceChild("legleft1", CubeListBuilder.create().texOffs(65, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 21.5F, -7.0F, -0.2231F, 0.1115F, -0.1859F));

		PartDefinition legright1 = root.addOrReplaceChild("legright1", CubeListBuilder.create().texOffs(70, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 21.5F, -7.0F, -0.2231F, -0.1115F, 0.1859F));

		PartDefinition legleft2 = root.addOrReplaceChild("legleft2", CubeListBuilder.create().texOffs(65, 5).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 21.5F, -6.0F, 0.0F, 0.0F, -0.3346F));

		PartDefinition legright2 = root.addOrReplaceChild("legright2", CubeListBuilder.create().texOffs(70, 5).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 21.5F, -6.0F, 0.0F, 0.0F, 0.3346F));

		PartDefinition legleft3 = root.addOrReplaceChild("legleft3", CubeListBuilder.create().texOffs(65, 10).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 21.5F, -5.0F, 0.1859F, -0.2231F, -0.3346F));

		PartDefinition legright3 = root.addOrReplaceChild("legright3", CubeListBuilder.create().texOffs(70, 10).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 21.5F, -5.0F, 0.1859F, 0.2231F, 0.3346F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(40, 0).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, 1.2269F, 0.0F, 0.0F));

		PartDefinition jawleft = root.addOrReplaceChild("jawleft", CubeListBuilder.create().texOffs(40, 8).addBox(1.8F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.2603F, 0.0F, -0.1859F));

		PartDefinition jawright = root.addOrReplaceChild("jawright", CubeListBuilder.create().texOffs(45, 8).addBox(-2.8F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.2602F, 0.0F, 0.1859F));

		PartDefinition mouthjaw = root.addOrReplaceChild("mouthjaw", CubeListBuilder.create().texOffs(40, 13).addBox(-1.5F, 2.3F, 1.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.632F, 0.0F, 0.0F));

		PartDefinition sensorleft = root.addOrReplaceChild("sensorleft", CubeListBuilder.create().texOffs(40, 16).addBox(1.0F, 0.0F, -1.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.7064F, -0.1487F, 0.0F));

		PartDefinition sensorright = root.addOrReplaceChild("sensorright", CubeListBuilder.create().texOffs(43, 16).addBox(-2.0F, 0.0F, -1.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.7064F, 0.1487F, 0.0F));

		PartDefinition horn1 = root.addOrReplaceChild("horn1", CubeListBuilder.create().texOffs(44, 3).addBox(-1.0F, -0.5F, -2.6F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition horn2 = root.addOrReplaceChild("horn2", CubeListBuilder.create().texOffs(45, 3).addBox(-0.5F, -1.0F, -2.4F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.1396F, 0.0F, 0.0F));

		PartDefinition horn3 = root.addOrReplaceChild("horn3", CubeListBuilder.create().texOffs(46, 4).addBox(-0.5F, -3.0F, -2.4F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.1396F, 0.0F, 0.0F));

		PartDefinition titanL1 = root.addOrReplaceChild("titanL1", CubeListBuilder.create().texOffs(45, 3).addBox(0.5F, -1.0F, -1.4F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.1396F, 0.0F, 0.0F));

		PartDefinition titanL2 = root.addOrReplaceChild("titanL2", CubeListBuilder.create().texOffs(44, 0).addBox(0.5F, -4.0F, -1.4F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.1396F, 0.0F, 0.0F));

		PartDefinition titanR1 = root.addOrReplaceChild("titanR1", CubeListBuilder.create().texOffs(45, 3).addBox(-1.5F, -1.0F, -1.4F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.1396F, 0.0F, 0.0F));

		PartDefinition titanR2 = root.addOrReplaceChild("titanR2", CubeListBuilder.create().texOffs(44, 0).addBox(-1.5F, -4.0F, -1.4F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -0.1396F, 0.0F, 0.0F));

		PartDefinition jawStagLeft = root.addOrReplaceChild("jawStagLeft", CubeListBuilder.create().texOffs(35, 8).addBox(1.8F, 0.0F, 0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -1.0472F, -0.1309F, 0.0F));

		PartDefinition jawStagRight = root.addOrReplaceChild("jawStagRight", CubeListBuilder.create().texOffs(50, 8).addBox(-2.8F, 0.0F, 0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -10.0F, -1.0472F, -0.1309F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float ba = Mth.cos(limbSwing) * 1.5F * limbSwingAmount;
		float bb = Mth.cos(limbSwing + 1.0f) * 2.25F * limbSwingAmount;
		float bc = Mth.cos(limbSwing + 2.0F) * 3.F * limbSwingAmount;
		float bd = Mth.cos(limbSwing + 3.0F) * 2.5F * limbSwingAmount;
		float be = Mth.cos(limbSwing + 4.0F) * 1.5F * limbSwingAmount;
		float bf = Mth.cos(limbSwing + 5.0F) * 0.75F * limbSwingAmount;

		head.x = bf;
		jawleft.x = bf;
		jawright.x = bf;
		mouthjaw.x = bf;
		sensorleft.x = bf;
		sensorright.x = bf;
		horn1.x = bf;
		horn2.x = bf;
		horn3.x = bf;
		titanL1.x = bf;
		titanL2.x = bf;
		titanR1.x = bf;
		titanR2.x = bf;
		jawStagLeft.x = bf;
		jawStagRight.x = bf;

		torso1.y = ba + 20F;

		torso2.y = bb + 20F;

		torso3.y = bc + 20F;

		torso4.y = bd + 20F;

		torso5.y = be + 20F;

		torso6.y = bf + 20F;

		legright1.y = bc + 21F;
		legleft1.y = bc + 21F;

		legright2.y = bc + 21F;
		legleft2.y = bc + 21F;

		legright3.y = bc + 21F;
		legleft3.y = bc + 21F;

		legright1.xRot = -Mth.cos(limbSwing) * 1.3F * limbSwingAmount;
		legleft1.xRot = Mth.cos(limbSwing) * 1.3F * limbSwingAmount;

		legright1.xRot = -Mth.cos(limbSwing) * 0.5F * limbSwingAmount;
		legleft1.xRot = Mth.cos(limbSwing) * 0.5F * limbSwingAmount;

		legright2.xRot = -Mth.cos(limbSwing + (float) Math.PI) * 1.3F * limbSwingAmount;
		legleft2.xRot = Mth.cos(limbSwing + (float) Math.PI) * 1.3F * limbSwingAmount;

		legright2.xRot = -Mth.cos(limbSwing + 0.5F + (float) Math.PI) * 0.5F * limbSwingAmount;
		legleft2.xRot = Mth.cos(limbSwing + 0.5F + (float) Math.PI) * 0.5F * limbSwingAmount;

		legright3.xRot = -Mth.cos(limbSwing) * 1.3F * limbSwingAmount;
		legleft3.xRot = Mth.cos(limbSwing) * 1.3F * limbSwingAmount;

		legright3.xRot = -Mth.cos(limbSwing + 1.0f) * 0.5F * limbSwingAmount;
		legleft3.xRot = Mth.cos(limbSwing + 1.0f) * 0.5F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		torso1.render(stack, consumer, light, overlay, colour);
		torso2.render(stack, consumer, light, overlay, colour);
		torso3.render(stack, consumer, light, overlay, colour);
		torso4.render(stack, consumer, light, overlay, colour);
		torso5.render(stack, consumer, light, overlay, colour);
		torso6.render(stack, consumer, light, overlay, colour);
		torso7.render(stack, consumer, light, overlay, colour);
		torso8.render(stack, consumer, light, overlay, colour);
		legleft1.render(stack, consumer, light, overlay, colour);
		legright1.render(stack, consumer, light, overlay, colour);
		legleft2.render(stack, consumer, light, overlay, colour);
		legright2.render(stack, consumer, light, overlay, colour);
		legleft3.render(stack, consumer, light, overlay, colour);
		legright3.render(stack, consumer, light, overlay, colour);
		head.render(stack, consumer, light, overlay, colour);
		jawleft.render(stack, consumer, light, overlay, colour);
		jawright.render(stack, consumer, light, overlay, colour);
		mouthjaw.render(stack, consumer, light, overlay, colour);
		sensorleft.render(stack, consumer, light, overlay, colour);
		sensorright.render(stack, consumer, light, overlay, colour);
	}

	public void renderLarvaRhino(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
			horn1.render(stack, consumer, light, overlay, colour);
			horn2.render(stack, consumer, light, overlay, colour);
			horn3.render(stack, consumer, light, overlay, colour);
	}

	public void renderLarvaTitan(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
			titanL1.render(stack, consumer, light, overlay, colour);
			titanL2.render(stack, consumer, light, overlay, colour);
			titanR1.render(stack, consumer, light, overlay, colour);
			titanR2.render(stack, consumer, light, overlay, colour);
	}

	public void renderLarvaStag(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
			jawleft.visible = false;
			jawright.visible = false;
			jawStagLeft.render(stack, consumer, light, overlay, colour);
			jawStagRight.render(stack, consumer, light, overlay, colour);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}
