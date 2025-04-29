package erebus.client.render.block.model;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import erebus.block.entity.RepairAltarBlockEntity;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;

public class RepairAltarModel extends Model {
	private final ModelPart AnvilFrontFoot;
	private final ModelPart AnvilRearFoot;
	private final ModelPart AnvilBase;
	private final ModelPart AnvilWaist;
	private final ModelPart AnvilFace;
	private final ModelPart AnvilTable;
	private final ModelPart AnvilHorn;
	private final ModelPart AnvilHeel;
	private final ModelPart Top;
	private final ModelPart Mid;
	private final ModelPart Bot;

	public RepairAltarModel(ModelPart root) {
		super(RenderType::entitySolid);
		this.AnvilFrontFoot = root.getChild("AnvilFrontFoot");
		this.AnvilRearFoot = root.getChild("AnvilRearFoot");
		this.AnvilBase = root.getChild("AnvilBase");
		this.AnvilWaist = root.getChild("AnvilWaist");
		this.AnvilFace = root.getChild("AnvilFace");
		this.AnvilTable = root.getChild("AnvilTable");
		this.AnvilHorn = root.getChild("AnvilHorn");
		this.AnvilHeel = root.getChild("AnvilHeel");
		this.Top = root.getChild("Top");
		this.Mid = root.getChild("Mid");
		this.Bot = root.getChild("Bot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition AnvilFrontFoot = partdefinition.addOrReplaceChild("AnvilFrontFoot", CubeListBuilder.create().texOffs(0, 37).addBox(-6.0F, 20.0F, -6.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

		PartDefinition AnvilRearFoot = partdefinition.addOrReplaceChild("AnvilRearFoot", CubeListBuilder.create().texOffs(0, 37).addBox(2.0F, 20.0F, -6.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

		PartDefinition AnvilBase = partdefinition.addOrReplaceChild("AnvilBase", CubeListBuilder.create().texOffs(33, 37).addBox(-5.0F, 19.0F, -4.0F, 10.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

		PartDefinition AnvilWaist = partdefinition.addOrReplaceChild("AnvilWaist", CubeListBuilder.create().texOffs(0, 54).addBox(-4.0F, 15.0F, -2.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

		PartDefinition AnvilFace = partdefinition.addOrReplaceChild("AnvilFace", CubeListBuilder.create().texOffs(70, 37).addBox(-7.0F, 8.0F, -5.0F, 14.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

		PartDefinition AnvilTable = partdefinition.addOrReplaceChild("AnvilTable", CubeListBuilder.create().texOffs(33, 51).addBox(-10.0F, 9.0F, -3.5F, 3.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

		PartDefinition AnvilHorn = partdefinition.addOrReplaceChild("AnvilHorn", CubeListBuilder.create().texOffs(55, 51).addBox(-13.0F, 9.0F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

		PartDefinition AnvilHeel = partdefinition.addOrReplaceChild("AnvilHeel", CubeListBuilder.create().texOffs(109, 45).addBox(7.0F, 8.0F, -5.0F, 3.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

		PartDefinition Top = partdefinition.addOrReplaceChild("Top", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, 0.0F, -16.0F, 32.0F, 4.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition Mid = partdefinition.addOrReplaceChild("Mid", CubeListBuilder.create().texOffs(130, 0).addBox(-12.0F, 0.0F, -12.0F, 24.0F, 24.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition Bot = partdefinition.addOrReplaceChild("Bot", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, 0.0F, -16.0F, 32.0F, 4.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 64);
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		AnvilFrontFoot.render(stack, consumer, light, overlay, colour);
		AnvilRearFoot.render(stack, consumer, light, overlay, colour);
		AnvilBase.render(stack, consumer, light, overlay, colour);
		AnvilWaist.render(stack, consumer, light, overlay, colour);
		AnvilFace.render(stack, consumer, light, overlay, colour);
		AnvilTable.render(stack, consumer, light, overlay, colour);
		AnvilHorn.render(stack, consumer, light, overlay, colour);
		AnvilHeel.render(stack, consumer, light, overlay, colour);
		Top.render(stack, consumer, light, overlay, colour);
		Mid.render(stack, consumer, light, overlay, colour);
		Bot.render(stack, consumer, light, overlay, colour);
	}

	public void renderWithTile(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour, RepairAltarBlockEntity tile, float partialTick) {
		float x = tile.animationTicks + (tile.animationTicks - tile.prevAnimationTicks) * partialTick;
		if (x > 20)
			x = 20;
		stack.pushPose();
		stack.scale(0.04F * x, 0.04F * x, 0.04F * x);
		AnvilFrontFoot.render(stack, consumer, light, overlay, colour);
		AnvilRearFoot.render(stack, consumer, light, overlay, colour);
		AnvilBase.render(stack, consumer, light, overlay, colour);
		AnvilWaist.render(stack, consumer, light, overlay, colour);
		AnvilFace.render(stack, consumer, light, overlay, colour);
		AnvilTable.render(stack, consumer, light, overlay, colour);
		AnvilHorn.render(stack, consumer, light, overlay, colour);
		AnvilHeel.render(stack, consumer, light, overlay, colour);
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