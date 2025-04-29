package erebus.client.render.block.model;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import erebus.block.entity.LightningAltarBlockEntity;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;

public class LightningAltarModel extends Model {
	private final ModelPart Mid;
	private final ModelPart Top;
	private final ModelPart Bot;
	private final ModelPart SmallBox;
	private final ModelPart ElectrodeF1;
	private final ModelPart ElectrodeF2;
	private final ModelPart ElectrodeL1;
	private final ModelPart ElectrodeL2;
	private final ModelPart ElectrodeB1;
	private final ModelPart ElectrodeB2;
	private final ModelPart ElectrodeR1;
	private final ModelPart ElectrodeR2;
	private final ModelPart Sparks;

	public LightningAltarModel(ModelPart root) {
		super(RenderType::entitySolid);
		this.Mid = root.getChild("Mid");
		this.Top = root.getChild("Top");
		this.Bot = root.getChild("Bot");
		this.SmallBox = root.getChild("SmallBox");
		this.ElectrodeF1 = root.getChild("ElectrodeF1");
		this.ElectrodeF2 = root.getChild("ElectrodeF2");
		this.ElectrodeL1 = root.getChild("ElectrodeL1");
		this.ElectrodeL2 = root.getChild("ElectrodeL2");
		this.ElectrodeB1 = root.getChild("ElectrodeB1");
		this.ElectrodeB2 = root.getChild("ElectrodeB2");
		this.ElectrodeR1 = root.getChild("ElectrodeR1");
		this.ElectrodeR2 = root.getChild("ElectrodeR2");
		this.Sparks = root.getChild("Sparks");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Mid = partdefinition.addOrReplaceChild("Mid", CubeListBuilder.create().texOffs(130, 0).addBox(-12.0F, 0.0F, -12.0F, 24.0F, 24.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition Top = partdefinition.addOrReplaceChild("Top", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, 0.0F, -16.0F, 32.0F, 4.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition Bot = partdefinition.addOrReplaceChild("Bot", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, 0.0F, -16.0F, 32.0F, 4.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 0.0F));

		PartDefinition SmallBox = partdefinition.addOrReplaceChild("SmallBox", CubeListBuilder.create().texOffs(0, 36).addBox(-7.0F, 0.0F, -7.0F, 14.0F, 14.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition ElectrodeF1 = partdefinition.addOrReplaceChild("ElectrodeF1", CubeListBuilder.create().texOffs(58, 38).addBox(-2.0F, -9.0F, -11.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition ElectrodeF2 = partdefinition.addOrReplaceChild("ElectrodeF2", CubeListBuilder.create().texOffs(78, 38).addBox(-1.0F, 1.0F, -11.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition ElectrodeL1 = partdefinition.addOrReplaceChild("ElectrodeL1", CubeListBuilder.create().texOffs(58, 38).addBox(-2.0F, -9.0F, -11.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, 0.5236F, -1.5708F, 0.0F));

		PartDefinition ElectrodeL2 = partdefinition.addOrReplaceChild("ElectrodeL2", CubeListBuilder.create().texOffs(78, 38).addBox(-1.0F, 1.0F, -11.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, -1.0472F, -1.5708F, 0.0F));

		PartDefinition ElectrodeB1 = partdefinition.addOrReplaceChild("ElectrodeB1", CubeListBuilder.create().texOffs(58, 38).addBox(-2.0F, -9.0F, -11.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, 0.5236F, 3.1416F, 0.0F));

		PartDefinition ElectrodeB2 = partdefinition.addOrReplaceChild("ElectrodeB2", CubeListBuilder.create().texOffs(78, 38).addBox(-1.0F, 1.0F, -11.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, -1.0472F, 3.1416F, 0.0F));

		PartDefinition ElectrodeR1 = partdefinition.addOrReplaceChild("ElectrodeR1", CubeListBuilder.create().texOffs(58, 38).addBox(-2.0F, -9.0F, -11.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, 0.5236F, 1.5708F, 0.0F));

		PartDefinition ElectrodeR2 = partdefinition.addOrReplaceChild("ElectrodeR2", CubeListBuilder.create().texOffs(78, 38).addBox(-1.0F, 1.0F, -11.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, -1.0472F, 1.5708F, 0.0F));

		PartDefinition Sparks = partdefinition.addOrReplaceChild("Sparks", CubeListBuilder.create().texOffs(90, 166).addBox(-5.0F, -11.0F, -5.0F, 10.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 64);
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
	}

	public void renderWithTile(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour, LightningAltarBlockEntity tile, float partialTick) {
		float x = tile.animationTicks + (tile.animationTicks - tile.prevAnimationTicks) * partialTick;
		if (x > 20)
			x = 20;
		stack.pushPose();
		stack.scale(0.04F * x, 0.04F * x, 0.04F * x);
		SmallBox.render(stack, consumer, light, overlay, colour);
		ElectrodeF1.render(stack, consumer, light, overlay, colour);
		ElectrodeF2.render(stack, consumer, light, overlay, colour);
		ElectrodeL1.render(stack, consumer, light, overlay, colour);
		ElectrodeL2.render(stack, consumer, light, overlay, colour);
		ElectrodeB1.render(stack, consumer, light, overlay, colour);
		ElectrodeB2.render(stack, consumer, light, overlay, colour);
		ElectrodeR1.render(stack, consumer, light, overlay, colour);
		ElectrodeR2.render(stack, consumer, light, overlay, colour);
		Sparks.render(stack, consumer, light, overlay, colour);
		stack.popPose();

		stack.pushPose();
		stack.mulPose(Axis.YN.rotationDegrees(x * 9F));
		Mid.render(stack, consumer, light, overlay, colour);
		stack.popPose();

		stack.pushPose();
		stack.mulPose(Axis.YP.rotationDegrees(x * 9F));
		Top.render(stack, consumer, light, overlay, colour);
		Bot.render(stack, consumer, light, overlay, colour);
		stack.popPose();
	}
}