package erebus.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import erebus.entity.BotFly;
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
public class BotFlyModel<T extends BotFly> extends HierarchicalModel<T> {
	public ModelPart root;
	private final ModelPart Head;
	private final ModelPart HeadFront;
	private final ModelPart HeadTop;
	private final ModelPart HeadBottom;
	private final ModelPart EyeR;
	private final ModelPart EveL;
	private final ModelPart Thorax1;
	private final ModelPart Thorax2;
	private final ModelPart Thorax3;
	private final ModelPart Ab1;
	private final ModelPart Ab2;
	private final ModelPart Ab3;
	private final ModelPart Ab4;
	private final ModelPart RWing1;
	private final ModelPart RWing2;
	private final ModelPart LWing1;
	private final ModelPart LWing2;
	private final ModelPart LegRF1;
	private final ModelPart LegRF2;
	private final ModelPart LegRF3;
	private final ModelPart LegLF1;
	private final ModelPart LegLF2;
	private final ModelPart LegLF3;
	private final ModelPart LegRM1;
	private final ModelPart LegRM2;
	private final ModelPart LegRM3;
	private final ModelPart LegLM1;
	private final ModelPart LegLM2;
	private final ModelPart LegLM3;
	private final ModelPart LegRB1;
	private final ModelPart LegRB2;
	private final ModelPart LegRB3;
	private final ModelPart LegLB1;
	private final ModelPart LegLB2;
	private final ModelPart LegLB3;

	public BotFlyModel(ModelPart root) {
		this.root = root;
		this.Head = root.getChild("Head");
		this.HeadFront = root.getChild("HeadFront");
		this.HeadTop = root.getChild("HeadTop");
		this.HeadBottom = root.getChild("HeadBottom");
		this.EyeR = root.getChild("EyeR");
		this.EveL = root.getChild("EveL");
		this.Thorax1 = root.getChild("Thorax1");
		this.Thorax2 = root.getChild("Thorax2");
		this.Thorax3 = root.getChild("Thorax3");
		this.Ab1 = root.getChild("Ab1");
		this.Ab2 = root.getChild("Ab2");
		this.Ab3 = root.getChild("Ab3");
		this.Ab4 = root.getChild("Ab4");
		this.RWing1 = root.getChild("RWing1");
		this.RWing2 = root.getChild("RWing2");
		this.LWing1 = root.getChild("LWing1");
		this.LWing2 = root.getChild("LWing2");
		this.LegRF1 = root.getChild("LegRF1");
		this.LegRF2 = root.getChild("LegRF2");
		this.LegRF3 = root.getChild("LegRF3");
		this.LegLF1 = root.getChild("LegLF1");
		this.LegLF2 = root.getChild("LegLF2");
		this.LegLF3 = root.getChild("LegLF3");
		this.LegRM1 = root.getChild("LegRM1");
		this.LegRM2 = root.getChild("LegRM2");
		this.LegRM3 = root.getChild("LegRM3");
		this.LegLM1 = root.getChild("LegLM1");
		this.LegLM2 = root.getChild("LegLM2");
		this.LegLM3 = root.getChild("LegLM3");
		this.LegRB1 = root.getChild("LegRB1");
		this.LegRB2 = root.getChild("LegRB2");
		this.LegRB3 = root.getChild("LegRB3");
		this.LegLB1 = root.getChild("LegLB1");
		this.LegLB2 = root.getChild("LegLB2");
		this.LegLB3 = root.getChild("LegLB3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(24, 0).addBox(-3.0F, -2.0F, -4.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -4.0F));

		PartDefinition HeadFront = partdefinition.addOrReplaceChild("HeadFront", CubeListBuilder.create().texOffs(15, 0).addBox(-2.0F, -1.5F, -4.5F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -4.0F));

		PartDefinition HeadTop = partdefinition.addOrReplaceChild("HeadTop", CubeListBuilder.create().texOffs(11, 5).addBox(-2.0F, -2.5F, -3.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -4.0F));

		PartDefinition HeadBottom = partdefinition.addOrReplaceChild("HeadBottom", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 1.5F, -3.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -4.0F));

		PartDefinition EyeR = partdefinition.addOrReplaceChild("EyeR", CubeListBuilder.create().texOffs(0, 25).addBox(-4.0F, -1.0F, -3.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -4.0F, -0.2967F, 0.0F, 0.0F));

		PartDefinition EveL = partdefinition.addOrReplaceChild("EveL", CubeListBuilder.create().texOffs(0, 25).addBox(3.0F, -1.0F, -3.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -4.0F, -0.2967F, 0.0F, 0.0F));

		PartDefinition Thorax1 = partdefinition.addOrReplaceChild("Thorax1", CubeListBuilder.create().texOffs(20, 10).addBox(-4.0F, -4.0F, 2.0F, 8.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -4.0F, -0.2793F, 0.0F, 0.0F));

		PartDefinition Thorax2 = partdefinition.addOrReplaceChild("Thorax2", CubeListBuilder.create().texOffs(19, 22).addBox(-3.5F, -2.0F, 0.0F, 7.0F, 5.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, 15.0F, -4.0F));

		PartDefinition Thorax3 = partdefinition.addOrReplaceChild("Thorax3", CubeListBuilder.create().texOffs(37, 28).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -4.0F));

		PartDefinition Ab1 = partdefinition.addOrReplaceChild("Ab1", CubeListBuilder.create().texOffs(47, 0).addBox(-3.0F, -3.5F, 5.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -4.0F, -0.2793F, 0.0F, 0.0F));

		PartDefinition Ab2 = partdefinition.addOrReplaceChild("Ab2", CubeListBuilder.create().texOffs(42, 7).addBox(-4.0F, -4.0F, 6.0F, 8.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -4.0F, -0.2793F, 0.0F, 0.0F));

		PartDefinition Ab3 = partdefinition.addOrReplaceChild("Ab3", CubeListBuilder.create().texOffs(44, 18).addBox(-3.5F, -3.5F, 9.0F, 7.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -4.0F, -0.2793F, 0.0F, 0.0F));

		PartDefinition Ab4 = partdefinition.addOrReplaceChild("Ab4", CubeListBuilder.create().texOffs(52, 27).addBox(-2.5F, -2.5F, 11.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 15.0F, -4.0F, -0.2793F, 0.0F, 0.0F));

		PartDefinition RWing1 = partdefinition.addOrReplaceChild("RWing1", CubeListBuilder.create().texOffs(0, 20).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 11.2F, -1.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition RWing2 = partdefinition.addOrReplaceChild("RWing2", CubeListBuilder.create().texOffs(0, 9).addBox(-3.5F, -0.5F, 1.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 11.2F, -1.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition LWing1 = partdefinition.addOrReplaceChild("LWing1", CubeListBuilder.create().texOffs(0, 20).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 11.2F, -1.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition LWing2 = partdefinition.addOrReplaceChild("LWing2", CubeListBuilder.create().texOffs(0, 9).addBox(1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 11.2F, -1.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition LegRF1 = partdefinition.addOrReplaceChild("LegRF1", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 18.0F, -3.0F, -1.3963F, 0.0F, 0.0F));

		PartDefinition LegRF2 = partdefinition.addOrReplaceChild("LegRF2", CubeListBuilder.create().texOffs(5, 19).addBox(-0.5F, -1.3F, -3.2F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.0F, 18.0F, -3.0F, 0.6981F, 0.0F, 0.0F));

		PartDefinition LegRF3 = partdefinition.addOrReplaceChild("LegRF3", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, 2.7F, -0.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 18.0F, -3.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition LegLF1 = partdefinition.addOrReplaceChild("LegLF1", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 18.0F, -3.0F, -1.3963F, 0.0F, 0.0F));

		PartDefinition LegLF2 = partdefinition.addOrReplaceChild("LegLF2", CubeListBuilder.create().texOffs(5, 19).addBox(-0.5F, -1.3F, -3.2F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(3.0F, 18.0F, -3.0F, 0.6981F, 0.0F, 0.0F));

		PartDefinition LegLF3 = partdefinition.addOrReplaceChild("LegLF3", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, 2.7F, -0.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 18.0F, -3.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition LegRM1 = partdefinition.addOrReplaceChild("LegRM1", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 18.0F, -2.0F, -1.3963F, 1.5708F, 0.0F));

		PartDefinition LegRM2 = partdefinition.addOrReplaceChild("LegRM2", CubeListBuilder.create().texOffs(5, 19).addBox(-0.5F, -1.3F, -3.2F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.0F, 18.0F, -2.0F, 0.6981F, 1.5708F, 0.0F));

		PartDefinition LegRM3 = partdefinition.addOrReplaceChild("LegRM3", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, 2.7F, -0.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 18.0F, -2.0F, -0.4363F, 1.5708F, 0.0F));

		PartDefinition LegLM1 = partdefinition.addOrReplaceChild("LegLM1", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 18.0F, -2.0F, -1.3963F, -1.5708F, 0.0F));

		PartDefinition LegLM2 = partdefinition.addOrReplaceChild("LegLM2", CubeListBuilder.create().texOffs(5, 19).addBox(-0.5F, -1.3F, -3.2F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(3.0F, 18.0F, -2.0F, 0.6981F, -1.5708F, 0.0F));

		PartDefinition LegLM3 = partdefinition.addOrReplaceChild("LegLM3", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, 2.7F, -0.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 18.0F, -2.0F, -0.4363F, -1.5708F, 0.0F));

		PartDefinition LegRB1 = partdefinition.addOrReplaceChild("LegRB1", CubeListBuilder.create().texOffs(0, 10).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 18.0F, -1.0F, -1.5708F, 2.618F, 0.0F));

		PartDefinition LegRB2 = partdefinition.addOrReplaceChild("LegRB2", CubeListBuilder.create().texOffs(5, 19).addBox(-0.5F, 0.5F, -4.4F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.0F, 18.0F, -1.0F, -0.1047F, 2.618F, 0.0F));

		PartDefinition LegRB3 = partdefinition.addOrReplaceChild("LegRB3", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, 4.3F, -3.1F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 18.0F, -1.0F, -0.4363F, 2.618F, 0.0F));

		PartDefinition LegLB1 = partdefinition.addOrReplaceChild("LegLB1", CubeListBuilder.create().texOffs(0, 10).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 18.0F, -1.0F, -1.5708F, -2.618F, 0.0F));

		PartDefinition LegLB2 = partdefinition.addOrReplaceChild("LegLB2", CubeListBuilder.create().texOffs(5, 19).addBox(-0.5F, 0.5F, -4.4F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(3.0F, 18.0F, -1.0F, -0.1047F, -2.618F, 0.0F));

		PartDefinition LegLB3 = partdefinition.addOrReplaceChild("LegLB3", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, 4.3F, -3.1F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 18.0F, -1.0F, -0.4363F, -2.618F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		Head.render(stack, consumer, light, overlay, colour);
		HeadFront.render(stack, consumer, light, overlay, colour);
		HeadTop.render(stack, consumer, light, overlay, colour);
		HeadBottom.render(stack, consumer, light, overlay, colour);
		EyeR.render(stack, consumer, light, overlay, colour);
		EveL.render(stack, consumer, light, overlay, colour);
		Thorax1.render(stack, consumer, light, overlay, colour);
		Thorax2.render(stack, consumer, light, overlay, colour);
		Thorax3.render(stack, consumer, light, overlay, colour);
		Ab1.render(stack, consumer, light, overlay, colour);
		Ab2.render(stack, consumer, light, overlay, colour);
		Ab3.render(stack, consumer, light, overlay, colour);
		Ab4.render(stack, consumer, light, overlay, colour);
		RWing1.render(stack, consumer, light, overlay, colour);
		RWing2.render(stack, consumer, light, overlay, colour);
		LWing1.render(stack, consumer, light, overlay, colour);
		LWing2.render(stack, consumer, light, overlay, colour);
		LegRF1.render(stack, consumer, light, overlay, colour);
		LegRF2.render(stack, consumer, light, overlay, colour);
		LegRF3.render(stack, consumer, light, overlay, colour);
		LegLF1.render(stack, consumer, light, overlay, colour);
		LegLF2.render(stack, consumer, light, overlay, colour);
		LegLF3.render(stack, consumer, light, overlay, colour);
		LegRM1.render(stack, consumer, light, overlay, colour);
		LegRM2.render(stack, consumer, light, overlay, colour);
		LegRM3.render(stack, consumer, light, overlay, colour);
		LegLM1.render(stack, consumer, light, overlay, colour);
		LegLM2.render(stack, consumer, light, overlay, colour);
		LegLM3.render(stack, consumer, light, overlay, colour);
		LegRB1.render(stack, consumer, light, overlay, colour);
		LegRB2.render(stack, consumer, light, overlay, colour);
		LegRB3.render(stack, consumer, light, overlay, colour);
		LegLB1.render(stack, consumer, light, overlay, colour);
		LegLB2.render(stack, consumer, light, overlay, colour);
		LegLB3.render(stack, consumer, light, overlay, colour);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}