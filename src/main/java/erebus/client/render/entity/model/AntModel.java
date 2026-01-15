package erebus.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.entity.HoneyPotAnt;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class AntModel<T extends Entity> extends HierarchicalModel<T> {
	public ModelPart root;
	private final ModelPart Thx;
	private final ModelPart ThxTop;
	private final ModelPart ThxS;
	private final ModelPart Thx2Ab;
	private final ModelPart Ab;
	private final ModelPart AbF;
	private final ModelPart AbSide;
	private final ModelPart AbTop;
	private final ModelPart AbBack;
	private final ModelPart Neck;
	private final ModelPart Head1;
	private final ModelPart RightFrontLeg;
	private final ModelPart RightMidLeg;
	private final ModelPart RightBackLeg;
	private final ModelPart LeftFrontLeg;
	private final ModelPart LeftMidLeg;
	private final ModelPart LeftBackLeg;

	public AntModel(ModelPart root) {
		this.root = root;
		this.Thx = root.getChild("Thx");
		this.ThxTop = root.getChild("ThxTop");
		this.ThxS = root.getChild("ThxS");
		this.Thx2Ab = root.getChild("Thx2Ab");
		this.Ab = root.getChild("Ab");
		this.AbF = root.getChild("AbF");
		this.AbSide = root.getChild("AbSide");
		this.AbTop = root.getChild("AbTop");
		this.AbBack = root.getChild("AbBack");
		this.Neck = root.getChild("Neck");
		this.Head1 = root.getChild("Head1");
		this.RightFrontLeg = root.getChild("RightFrontLeg");
		this.RightMidLeg = root.getChild("RightMidLeg");
		this.RightBackLeg = root.getChild("RightBackLeg");
		this.LeftFrontLeg = root.getChild("LeftFrontLeg");
		this.LeftMidLeg = root.getChild("LeftMidLeg");
		this.LeftBackLeg = root.getChild("LeftBackLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Thx = partdefinition.addOrReplaceChild("Thx", CubeListBuilder.create().texOffs(14, 13).addBox(-3.5F, -3.5F, 0.0F, 7.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, -8.0F));

		PartDefinition ThxTop = partdefinition.addOrReplaceChild("ThxTop", CubeListBuilder.create().texOffs(21, 30).addBox(-2.5F, -4.5F, 1.0F, 5.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, -8.0F));

		PartDefinition ThxS = partdefinition.addOrReplaceChild("ThxS", CubeListBuilder.create().texOffs(15, 39).addBox(-4.5F, -2.5F, 1.0F, 9.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, -8.0F));

		PartDefinition Thx2Ab = partdefinition.addOrReplaceChild("Thx2Ab", CubeListBuilder.create().texOffs(27, 52).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 1.0F));

		PartDefinition Ab = partdefinition.addOrReplaceChild("Ab", CubeListBuilder.create().texOffs(9, 100).addBox(-5.5F, -4.5F, 0.0F, 11.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 3.0F));

		PartDefinition AbF = partdefinition.addOrReplaceChild("AbF", CubeListBuilder.create().texOffs(23, 57).addBox(-3.5F, -3.5F, -1.0F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 3.0F));

		PartDefinition AbSide = partdefinition.addOrReplaceChild("AbSide", CubeListBuilder.create().texOffs(10, 66).addBox(-6.5F, -2.5F, 2.0F, 13.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 3.0F));

		PartDefinition AbTop = partdefinition.addOrReplaceChild("AbTop", CubeListBuilder.create().texOffs(15, 80).addBox(-4.0F, -5.5F, 2.0F, 8.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 3.0F));

		PartDefinition AbBack = partdefinition.addOrReplaceChild("AbBack", CubeListBuilder.create().texOffs(22, 122).addBox(-3.5F, -2.5F, 12.0F, 7.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 3.0F));

		PartDefinition Neck = partdefinition.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 12).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, -9.0F));

		PartDefinition Head1 = partdefinition.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(21, 0).addBox(-2.0F, 0.5F, -5.0F, 4.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, -9.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition Head2 = Head1.addOrReplaceChild("Head2", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -0.5F, -4.0F, 6.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RMandible1 = Head1.addOrReplaceChild("RMandible1", CubeListBuilder.create().texOffs(52, 0).addBox(-3.0F, 5.5F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RMandible2 = Head1.addOrReplaceChild("RMandible2", CubeListBuilder.create().texOffs(52, 9).addBox(-2.0F, 5.5F, -3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LMandible1 = Head1.addOrReplaceChild("LMandible1", CubeListBuilder.create().texOffs(47, 0).addBox(2.0F, 5.5F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LMandible2 = Head1.addOrReplaceChild("LMandible2", CubeListBuilder.create().texOffs(47, 9).addBox(1.0F, 5.5F, -3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Eyes = Head1.addOrReplaceChild("Eyes", CubeListBuilder.create().texOffs(0, 30).addBox(-4.0F, 1.5F, -3.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition AntLS = Head1.addOrReplaceChild("AntLS", CubeListBuilder.create().texOffs(42, 6).addBox(3.0F, 4.5F, -3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition AntLE = Head1.addOrReplaceChild("AntLE", CubeListBuilder.create().texOffs(42, 0).addBox(6.0F, 5.5F, -3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition AntRS = Head1.addOrReplaceChild("AntRS", CubeListBuilder.create().texOffs(53, 6).addBox(-6.0F, 4.5F, -3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition AntRE = Head1.addOrReplaceChild("AntRE", CubeListBuilder.create().texOffs(57, 0).addBox(-7.0F, 5.5F, -3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition RightFrontLeg = partdefinition.addOrReplaceChild("RightFrontLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 17.0F, -6.0F, 0.0F, -0.6981F, 0.0F));

		PartDefinition RFL1 = RightFrontLeg.addOrReplaceChild("RFL1", CubeListBuilder.create().texOffs(0, 95).addBox(-3.6543F, -0.7758F, -0.9378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition RFL2 = RFL1.addOrReplaceChild("RFL2", CubeListBuilder.create().texOffs(0, 88).addBox(-1.7111F, -1.1971F, 5.0622F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition RFL3 = RFL1.addOrReplaceChild("RFL3", CubeListBuilder.create().texOffs(0, 82).addBox(-0.8188F, 2.2502F, 5.5622F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.3054F));

		PartDefinition RFL4 = RFL1.addOrReplaceChild("RFL4", CubeListBuilder.create().texOffs(0, 76).addBox(0.7498F, 5.3188F, 5.5622F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.48F));

		PartDefinition RightMidLeg = partdefinition.addOrReplaceChild("RightMidLeg", CubeListBuilder.create(), PartPose.offset(-4.0F, 17.0F, -3.0F));

		PartDefinition RML1 = RightMidLeg.addOrReplaceChild("RML1", CubeListBuilder.create().texOffs(0, 95).addBox(-3.6543F, -0.7758F, -0.9378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition RML2 = RML1.addOrReplaceChild("RML2", CubeListBuilder.create().texOffs(0, 88).addBox(-1.7111F, -1.1971F, 5.0622F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition RML3 = RML1.addOrReplaceChild("RML3", CubeListBuilder.create().texOffs(0, 82).addBox(-0.8188F, 2.2502F, 5.5622F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.3054F));

		PartDefinition RML4 = RML1.addOrReplaceChild("RML4", CubeListBuilder.create().texOffs(0, 76).addBox(0.7498F, 5.3188F, 5.5622F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.48F));

		PartDefinition RightBackLeg = partdefinition.addOrReplaceChild("RightBackLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 17.0F, 0.0F, 0.0F, 0.6981F, 0.0F));

		PartDefinition RBL1 = RightBackLeg.addOrReplaceChild("RBL1", CubeListBuilder.create().texOffs(0, 95).addBox(-3.6543F, -0.7758F, -0.9378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition RBL2 = RBL1.addOrReplaceChild("RBL2", CubeListBuilder.create().texOffs(0, 88).addBox(-1.7111F, -1.1971F, 5.0622F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition RBL3 = RBL1.addOrReplaceChild("RBL3", CubeListBuilder.create().texOffs(0, 82).addBox(-0.8188F, 2.2502F, 5.5622F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.3054F));

		PartDefinition RBL4 = RBL1.addOrReplaceChild("RBL4", CubeListBuilder.create().texOffs(0, 76).addBox(0.7498F, 5.3188F, 5.5622F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.48F));

		PartDefinition LeftFrontLeg = partdefinition.addOrReplaceChild("LeftFrontLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 17.0F, -6.0F, 0.0F, -2.4435F, 0.0F));

		PartDefinition LFL1 = LeftFrontLeg.addOrReplaceChild("LFL1", CubeListBuilder.create().texOffs(0, 95).addBox(-3.6543F, -0.7758F, -0.9378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition LFL2 = LFL1.addOrReplaceChild("LFL2", CubeListBuilder.create().texOffs(0, 88).addBox(-1.7111F, -1.1971F, 5.0622F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition LFL3 = LFL1.addOrReplaceChild("LFL3", CubeListBuilder.create().texOffs(0, 82).addBox(-0.8188F, 2.2502F, 5.5622F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.3054F));

		PartDefinition LFL4 = LFL1.addOrReplaceChild("LFL4", CubeListBuilder.create().texOffs(0, 76).addBox(0.7498F, 5.3188F, 5.5622F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.48F));

		PartDefinition LeftMidLeg = partdefinition.addOrReplaceChild("LeftMidLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 17.0F, -3.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition LML1 = LeftMidLeg.addOrReplaceChild("LML1", CubeListBuilder.create().texOffs(0, 95).addBox(-3.6543F, -0.7758F, -0.9378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition LML2 = LML1.addOrReplaceChild("LML2", CubeListBuilder.create().texOffs(0, 88).addBox(-1.7111F, -1.1971F, 5.0622F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition LML3 = LML1.addOrReplaceChild("LML3", CubeListBuilder.create().texOffs(0, 82).addBox(-0.8188F, 2.2502F, 5.5622F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.3054F));

		PartDefinition LML4 = LML1.addOrReplaceChild("LML4", CubeListBuilder.create().texOffs(0, 76).addBox(0.7498F, 5.3188F, 5.5622F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.48F));

		PartDefinition LeftBackLeg = partdefinition.addOrReplaceChild("LeftBackLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 17.0F, 0.0F, 0.0F, 2.4435F, 0.0F));

		PartDefinition LBL1 = LeftBackLeg.addOrReplaceChild("LBL1", CubeListBuilder.create().texOffs(0, 95).addBox(-3.6543F, -0.7758F, -0.9378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition LBL2 = LBL1.addOrReplaceChild("LBL2", CubeListBuilder.create().texOffs(0, 88).addBox(-1.7111F, -1.1971F, 5.0622F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition LBL3 = LBL1.addOrReplaceChild("LBL3", CubeListBuilder.create().texOffs(0, 82).addBox(-0.8188F, 2.2502F, 5.5622F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.3054F));

		PartDefinition LBL4 = LBL1.addOrReplaceChild("LBL4", CubeListBuilder.create().texOffs(0, 76).addBox(0.7498F, 5.3188F, 5.5622F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.1543F, 1.2242F, -6.0F, 0.0F, 0.0F, 0.48F));

		return LayerDefinition.create(meshdefinition, 64, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		Head1.yRot = netHeadYaw / (180F / (float) Math.PI);
		Head1.xRot = headPitch / (180F / (float) Math.PI) - 1F;

		float sin = Mth.sin(limbSwing) * 0.8F * limbSwingAmount;
		float cos = Mth.cos(limbSwing) * 0.2F * limbSwingAmount;

		LeftBackLeg.zRot = -cos;
		LeftMidLeg.zRot = cos;
		LeftFrontLeg.zRot = -cos;
		RightBackLeg.zRot = -cos;
		RightMidLeg.zRot = cos;
		RightFrontLeg.zRot = -cos;
		LeftBackLeg.yRot = 2.4435F + sin;
		LeftMidLeg.yRot = 3.1416F - sin;
		LeftFrontLeg.yRot = -2.4435F + sin;
		RightBackLeg.yRot = 0.6981F + sin;
		RightMidLeg.yRot = 0F - sin;
		RightFrontLeg.yRot = -0.6981F + sin;

		if (entity instanceof HoneyPotAnt ant) {
			Ab.xRot = ant.getHoneyBelly() * 0.25F;
			AbF.xRot = ant.getHoneyBelly() * 0.25F;
			AbSide.xRot = ant.getHoneyBelly() * 0.25F;
			AbTop.xRot = ant.getHoneyBelly() * 0.25F;
			AbBack.xRot = ant.getHoneyBelly() * 0.25F;
		}
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		Thx.render(stack, consumer, light, overlay, colour);
		ThxTop.render(stack, consumer, light, overlay, colour);
		ThxS.render(stack, consumer, light, overlay, colour);
		Thx2Ab.render(stack, consumer, light, overlay, colour);
		Neck.render(stack, consumer, light, overlay, colour);
		Head1.render(stack, consumer, light, overlay, colour);
		RightFrontLeg.render(stack, consumer, light, overlay, colour);
		RightMidLeg.render(stack, consumer, light, overlay, colour);
		RightBackLeg.render(stack, consumer, light, overlay, colour);
		LeftFrontLeg.render(stack, consumer, light, overlay, colour);
		LeftMidLeg.render(stack, consumer, light, overlay, colour);
		LeftBackLeg.render(stack, consumer, light, overlay, colour);
	}

	public void renderAbdomen(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		Ab.render(stack, consumer, light, overlay, colour);
		AbF.render(stack, consumer, light, overlay, colour);
		AbSide.render(stack, consumer, light, overlay, colour);
		AbTop.render(stack, consumer, light, overlay, colour);
		AbBack.render(stack, consumer, light, overlay, colour);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}
