package erebus.client.render.block.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import erebus.block.entity.HealingAltarBlockEntity;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class HealingAltarModel extends Model {
	private final ModelPart ROutPetal1;
	private final ModelPart ROutPetal2;
	private final ModelPart ROutPetal3;
	private final ModelPart ROutPelal4;
	private final ModelPart LOutPetal1;
	private final ModelPart LOutPetal2;
	private final ModelPart LOutPetal3;
	private final ModelPart LOutPetal4;
	private final ModelPart BOutPetal1;
	private final ModelPart BOutPetal2;
	private final ModelPart BOutPetal3;
	private final ModelPart BOutPetal4;
	private final ModelPart RInPetal1;
	private final ModelPart RInPetal2;
	private final ModelPart RInPetal3;
	private final ModelPart RInPetal4;
	private final ModelPart LInPetal1;
	private final ModelPart LInPetal2;
	private final ModelPart LinPetal3;
	private final ModelPart LInPetal4;
	private final ModelPart FInPetal1;
	private final ModelPart FInPetal2;
	private final ModelPart FInPetal3;
	private final ModelPart FInPetal4;
	private final ModelPart RLeafBack;
	private final ModelPart RLeafMain;
	private final ModelPart RLeafFront;
	private final ModelPart RLeafTop;
	private final ModelPart RLeafEnd;
	private final ModelPart LLeafBack;
	private final ModelPart LLeafMain;
	private final ModelPart LLeafFront;
	private final ModelPart LLeafTop;
	private final ModelPart LLeafEnd;
	private final ModelPart Stem;
	private final ModelPart Top;
	private final ModelPart Mid;
	private final ModelPart Bot;

	public HealingAltarModel(ModelPart root) {
		super(RenderType::entitySolid);
		this.ROutPetal1 = root.getChild("ROutPetal1");
		this.ROutPetal2 = root.getChild("ROutPetal2");
		this.ROutPetal3 = root.getChild("ROutPetal3");
		this.ROutPelal4 = root.getChild("ROutPelal4");
		this.LOutPetal1 = root.getChild("LOutPetal1");
		this.LOutPetal2 = root.getChild("LOutPetal2");
		this.LOutPetal3 = root.getChild("LOutPetal3");
		this.LOutPetal4 = root.getChild("LOutPetal4");
		this.BOutPetal1 = root.getChild("BOutPetal1");
		this.BOutPetal2 = root.getChild("BOutPetal2");
		this.BOutPetal3 = root.getChild("BOutPetal3");
		this.BOutPetal4 = root.getChild("BOutPetal4");
		this.RInPetal1 = root.getChild("RInPetal1");
		this.RInPetal2 = root.getChild("RInPetal2");
		this.RInPetal3 = root.getChild("RInPetal3");
		this.RInPetal4 = root.getChild("RInPetal4");
		this.LInPetal1 = root.getChild("LInPetal1");
		this.LInPetal2 = root.getChild("LInPetal2");
		this.LinPetal3 = root.getChild("LinPetal3");
		this.LInPetal4 = root.getChild("LInPetal4");
		this.FInPetal1 = root.getChild("FInPetal1");
		this.FInPetal2 = root.getChild("FInPetal2");
		this.FInPetal3 = root.getChild("FInPetal3");
		this.FInPetal4 = root.getChild("FInPetal4");
		this.RLeafBack = root.getChild("RLeafBack");
		this.RLeafMain = root.getChild("RLeafMain");
		this.RLeafFront = root.getChild("RLeafFront");
		this.RLeafTop = root.getChild("RLeafTop");
		this.RLeafEnd = root.getChild("RLeafEnd");
		this.LLeafBack = root.getChild("LLeafBack");
		this.LLeafMain = root.getChild("LLeafMain");
		this.LLeafFront = root.getChild("LLeafFront");
		this.LLeafTop = root.getChild("LLeafTop");
		this.LLeafEnd = root.getChild("LLeafEnd");
		this.Stem = root.getChild("Stem");
		this.Top = root.getChild("Top");
		this.Mid = root.getChild("Mid");
		this.Bot = root.getChild("Bot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition ROutPetal1 = partdefinition.addOrReplaceChild("ROutPetal1", CubeListBuilder.create().texOffs(0, 37).addBox(-2.0F, 0.0F, 5.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.7854F, -2.0944F, 0.0F));

		PartDefinition ROutPetal2 = partdefinition.addOrReplaceChild("ROutPetal2", CubeListBuilder.create().texOffs(20, 43).addBox(-3.0F, 0.0F, 4.5F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, -2.0944F, 0.0F));

		PartDefinition ROutPetal3 = partdefinition.addOrReplaceChild("ROutPetal3", CubeListBuilder.create().texOffs(20, 51).addBox(-4.0F, -4.0F, 4.5F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, -2.0944F, 0.0F));

		PartDefinition ROutPelal4 = partdefinition.addOrReplaceChild("ROutPelal4", CubeListBuilder.create().texOffs(20, 37).addBox(-3.0F, -7.0F, 5.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

		PartDefinition LOutPetal1 = partdefinition.addOrReplaceChild("LOutPetal1", CubeListBuilder.create().texOffs(0, 37).addBox(-2.0F, 0.0F, 5.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.7854F, 2.0944F, 0.0F));

		PartDefinition LOutPetal2 = partdefinition.addOrReplaceChild("LOutPetal2", CubeListBuilder.create().texOffs(20, 43).addBox(-3.0F, 0.0F, 4.5F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, 2.0944F, 0.0F));

		PartDefinition LOutPetal3 = partdefinition.addOrReplaceChild("LOutPetal3", CubeListBuilder.create().texOffs(20, 51).addBox(-4.0F, -4.0F, 4.5F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, 2.0944F, 0.0F));

		PartDefinition LOutPetal4 = partdefinition.addOrReplaceChild("LOutPetal4", CubeListBuilder.create().texOffs(20, 37).addBox(-3.0F, -7.0F, 5.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.0F, 2.0944F, 0.0F));

		PartDefinition BOutPetal1 = partdefinition.addOrReplaceChild("BOutPetal1", CubeListBuilder.create().texOffs(0, 37).addBox(-2.0F, 0.0F, 5.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition BOutPetal2 = partdefinition.addOrReplaceChild("BOutPetal2", CubeListBuilder.create().texOffs(20, 43).addBox(-3.0F, 0.0F, 4.5F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, 0.0F, 0.0F));

		PartDefinition BOutPetal3 = partdefinition.addOrReplaceChild("BOutPetal3", CubeListBuilder.create().texOffs(20, 51).addBox(-4.0F, -4.0F, 4.5F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, 0.0F, 0.0F));

		PartDefinition BOutPetal4 = partdefinition.addOrReplaceChild("BOutPetal4", CubeListBuilder.create().texOffs(20, 37).addBox(-3.0F, -7.0F, 5.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

		PartDefinition RInPetal1 = partdefinition.addOrReplaceChild("RInPetal1", CubeListBuilder.create().texOffs(0, 43).addBox(-1.5F, 1.0F, 4.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.7854F, -1.0472F, 0.0F));

		PartDefinition RInPetal2 = partdefinition.addOrReplaceChild("RInPetal2", CubeListBuilder.create().texOffs(35, 43).addBox(-3.0F, 0.0F, 0.5F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, -1.0472F, 0.0F));

		PartDefinition RInPetal3 = partdefinition.addOrReplaceChild("RInPetal3", CubeListBuilder.create().texOffs(39, 51).addBox(-3.5F, -4.0F, 1.5F, 7.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, -1.0472F, 0.0F));

		PartDefinition RInPetal4 = partdefinition.addOrReplaceChild("RInPetal4", CubeListBuilder.create().texOffs(20, 37).addBox(-3.0F, -7.0F, 3.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

		PartDefinition LInPetal1 = partdefinition.addOrReplaceChild("LInPetal1", CubeListBuilder.create().texOffs(0, 43).addBox(-1.5F, 1.0F, 4.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.7854F, 1.0472F, 0.0F));

		PartDefinition LInPetal2 = partdefinition.addOrReplaceChild("LInPetal2", CubeListBuilder.create().texOffs(35, 43).addBox(-3.0F, 0.0F, 0.5F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, 1.0472F, 0.0F));

		PartDefinition LinPetal3 = partdefinition.addOrReplaceChild("LinPetal3", CubeListBuilder.create().texOffs(39, 51).addBox(-3.5F, -4.0F, 1.5F, 7.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, 1.0472F, 0.0F));

		PartDefinition LInPetal4 = partdefinition.addOrReplaceChild("LInPetal4", CubeListBuilder.create().texOffs(20, 37).addBox(-3.0F, -7.0F, 3.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.0F, 1.0472F, 0.0F));

		PartDefinition FInPetal1 = partdefinition.addOrReplaceChild("FInPetal1", CubeListBuilder.create().texOffs(0, 43).addBox(-1.5F, 1.0F, 4.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.7854F, -3.1416F, 0.0F));

		PartDefinition FInPetal2 = partdefinition.addOrReplaceChild("FInPetal2", CubeListBuilder.create().texOffs(35, 43).addBox(-3.0F, 0.0F, 0.5F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, 3.1416F, 0.0F));

		PartDefinition FInPetal3 = partdefinition.addOrReplaceChild("FInPetal3", CubeListBuilder.create().texOffs(39, 51).addBox(-3.5F, -4.0F, 1.5F, 7.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.1571F, 3.1416F, 0.0F));

		PartDefinition FInPetal4 = partdefinition.addOrReplaceChild("FInPetal4", CubeListBuilder.create().texOffs(20, 37).addBox(-3.0F, -7.0F, 3.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition RLeafBack = partdefinition.addOrReplaceChild("RLeafBack", CubeListBuilder.create().texOffs(8, 43).addBox(-2.0F, 11.0F, -9.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.3491F, 1.5708F, 0.0F));

		PartDefinition RLeafMain = partdefinition.addOrReplaceChild("RLeafMain", CubeListBuilder.create().texOffs(13, 37).addBox(-1.0F, 7.5F, -9.5F, 2.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.3491F, 1.5708F, 0.0F));

		PartDefinition RLeafFront = partdefinition.addOrReplaceChild("RLeafFront", CubeListBuilder.create().texOffs(8, 54).addBox(1.0F, 11.0F, -9.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.3491F, 1.5708F, 0.0F));

		PartDefinition RLeafTop = partdefinition.addOrReplaceChild("RLeafTop", CubeListBuilder.create().texOffs(13, 53).addBox(-1.0F, 12.0F, -1.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.4363F, 1.5708F, 0.0F));

		PartDefinition RLeafEnd = partdefinition.addOrReplaceChild("RLeafEnd", CubeListBuilder.create().texOffs(13, 58).addBox(-0.5F, 15.0F, -1.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, -0.4363F, 1.5708F, 0.0F));

		PartDefinition LLeafBack = partdefinition.addOrReplaceChild("LLeafBack", CubeListBuilder.create().texOffs(8, 43).addBox(1.0F, 11.0F, -9.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.3491F, -1.5708F, 0.0F));

		PartDefinition LLeafMain = partdefinition.addOrReplaceChild("LLeafMain", CubeListBuilder.create().texOffs(13, 37).addBox(-1.0F, 7.5F, -9.5F, 2.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.3491F, -1.5708F, 0.0F));

		PartDefinition LLeafFront = partdefinition.addOrReplaceChild("LLeafFront", CubeListBuilder.create().texOffs(8, 54).addBox(-2.0F, 11.0F, -9.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.3491F, -1.5708F, 0.0F));

		PartDefinition LLeafTop = partdefinition.addOrReplaceChild("LLeafTop", CubeListBuilder.create().texOffs(13, 53).addBox(-1.0F, 12.0F, 0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.4363F, -4.7124F, 0.0F));

		PartDefinition LLeafEnd = partdefinition.addOrReplaceChild("LLeafEnd", CubeListBuilder.create().texOffs(13, 58).addBox(-0.5F, 15.0F, 0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.4363F, -4.7124F, 0.0F));

		PartDefinition Stem = partdefinition.addOrReplaceChild("Stem", CubeListBuilder.create().texOffs(58, 40).addBox(-1.0F, 6.0F, -1.0F, 2.0F, 18.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition Top = partdefinition.addOrReplaceChild("Top", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, 0.0F, -16.0F, 32.0F, 4.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition Mid = partdefinition.addOrReplaceChild("Mid", CubeListBuilder.create().texOffs(130, 0).addBox(-12.0F, 0.0F, -12.0F, 24.0F, 24.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition Bot = partdefinition.addOrReplaceChild("Bot", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, 0.0F, -16.0F, 32.0F, 4.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 64);
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
	}

	public void renderWithTile(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour, HealingAltarBlockEntity tile, float partialTick) {
		float x = tile.animationTicks + (tile.animationTicks - tile.prevAnimationTicks) * partialTick;
		if (x > 20)
			x = 20;
		stack.pushPose();
		stack.mulPose(Axis.YP.rotationDegrees(-x * 9F + 90F));
		stack.scale(0.05F * x, 0.05F * x, 0.05F * x);
		ROutPetal1.render(stack, consumer, light, overlay, colour);
		ROutPetal2.render(stack, consumer, light, overlay, colour);
		ROutPetal3.render(stack, consumer, light, overlay, colour);
		ROutPelal4.render(stack, consumer, light, overlay, colour);
		LOutPetal1.render(stack, consumer, light, overlay, colour);
		LOutPetal2.render(stack, consumer, light, overlay, colour);
		LOutPetal3.render(stack, consumer, light, overlay, colour);
		LOutPetal4.render(stack, consumer, light, overlay, colour);
		BOutPetal1.render(stack, consumer, light, overlay, colour);
		BOutPetal2.render(stack, consumer, light, overlay, colour);
		BOutPetal3.render(stack, consumer, light, overlay, colour);
		BOutPetal4.render(stack, consumer, light, overlay, colour);
		RInPetal1.render(stack, consumer, light, overlay, colour);
		RInPetal2.render(stack, consumer, light, overlay, colour);
		RInPetal3.render(stack, consumer, light, overlay, colour);
		RInPetal4.render(stack, consumer, light, overlay, colour);
		LInPetal1.render(stack, consumer, light, overlay, colour);
		LInPetal2.render(stack, consumer, light, overlay, colour);
		LinPetal3.render(stack, consumer, light, overlay, colour);
		LInPetal4.render(stack, consumer, light, overlay, colour);
		FInPetal1.render(stack, consumer, light, overlay, colour);
		FInPetal2.render(stack, consumer, light, overlay, colour);
		FInPetal3.render(stack, consumer, light, overlay, colour);
		FInPetal4.render(stack, consumer, light, overlay, colour);
		RLeafBack.render(stack, consumer, light, overlay, colour);
		RLeafMain.render(stack, consumer, light, overlay, colour);
		RLeafFront.render(stack, consumer, light, overlay, colour);
		RLeafTop.render(stack, consumer, light, overlay, colour);
		RLeafEnd.render(stack, consumer, light, overlay, colour);
		LLeafBack.render(stack, consumer, light, overlay, colour);
		LLeafMain.render(stack, consumer, light, overlay, colour);
		LLeafFront.render(stack, consumer, light, overlay, colour);
		LLeafTop.render(stack, consumer, light, overlay, colour);
		LLeafEnd.render(stack, consumer, light, overlay, colour);
		Stem.render(stack, consumer, light, overlay, colour);
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
