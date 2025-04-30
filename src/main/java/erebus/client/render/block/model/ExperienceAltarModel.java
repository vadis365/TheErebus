package erebus.client.render.block.model;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import erebus.block.entity.ExperienceAltarBlockEntity;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;

public class ExperienceAltarModel extends Model {
	private final ModelPart GlassTop;
	private final ModelPart GlassBot;
	private final ModelPart GlassMid;
	private final ModelPart BPlate;
	private final ModelPart TPlate;
	private final ModelPart RFSupport;
	private final ModelPart RBSupport;
	private final ModelPart LFSupport;
	private final ModelPart LBSupport;
	private final ModelPart Top;
	private final ModelPart Mid;
	private final ModelPart Bot;

	public ExperienceAltarModel(ModelPart root) {
		super(RenderType::entitySolid);
		this.GlassTop = root.getChild("GlassTop");
		this.GlassBot = root.getChild("GlassBot");
		this.GlassMid = root.getChild("GlassMid");
		this.BPlate = root.getChild("BPlate");
		this.TPlate = root.getChild("TPlate");
		this.RFSupport = root.getChild("RFSupport");
		this.RBSupport = root.getChild("RBSupport");
		this.LFSupport = root.getChild("LFSupport");
		this.LBSupport = root.getChild("LBSupport");
		this.Top = root.getChild("Top");
		this.Mid = root.getChild("Mid");
		this.Bot = root.getChild("Bot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition GlassTop = partdefinition.addOrReplaceChild("GlassTop", CubeListBuilder.create().texOffs(0, 37).addBox(-3.5F, 8.0F, -3.5F, 7.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

		PartDefinition GlassBot = partdefinition.addOrReplaceChild("GlassBot", CubeListBuilder.create().texOffs(0, 51).addBox(-3.5F, 16.0F, -3.5F, 7.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

		PartDefinition GlassMid = partdefinition.addOrReplaceChild("GlassMid", CubeListBuilder.create().texOffs(29, 37).addBox(-1.5F, 14.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

		PartDefinition BPlate = partdefinition.addOrReplaceChild("BPlate", CubeListBuilder.create().texOffs(42, 37).addBox(-7.0F, 22.0F, -7.0F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

		PartDefinition TPlate = partdefinition.addOrReplaceChild("TPlate", CubeListBuilder.create().texOffs(42, 37).addBox(-7.0F, 6.0F, -7.0F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

		PartDefinition RFSupport = partdefinition.addOrReplaceChild("RFSupport", CubeListBuilder.create().texOffs(99, 37).addBox(-6.0F, 5.0F, -6.0F, 2.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

		PartDefinition RBSupport = partdefinition.addOrReplaceChild("RBSupport", CubeListBuilder.create().texOffs(99, 37).addBox(-6.0F, 5.0F, 4.0F, 2.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

		PartDefinition LFSupport = partdefinition.addOrReplaceChild("LFSupport", CubeListBuilder.create().texOffs(99, 37).addBox(4.0F, 5.0F, -6.0F, 2.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

		PartDefinition LBSupport = partdefinition.addOrReplaceChild("LBSupport", CubeListBuilder.create().texOffs(99, 37).addBox(4.0F, 5.0F, 4.0F, 2.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

		PartDefinition Top = partdefinition.addOrReplaceChild("Top", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, 0.0F, -16.0F, 32.0F, 4.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition Mid = partdefinition.addOrReplaceChild("Mid", CubeListBuilder.create().texOffs(130, 0).addBox(-12.0F, 0.0F, -12.0F, 24.0F, 24.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition Bot = partdefinition.addOrReplaceChild("Bot", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, 0.0F, -16.0F, 32.0F, 4.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 64);
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
	}

	public void renderWithTile(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour, ExperienceAltarBlockEntity tile, float partialTick) {
		float x = tile.animationTicks + (tile.animationTicks - tile.prevAnimationTicks) * partialTick;
		if (x > 20)
			x = 20;
		stack.pushPose();
		stack.mulPose(Axis.YP.rotationDegrees(-x * 9F + 90F));
		stack.scale(0.05F * x, 0.05F * x, 0.05F * x);
		GlassTop.render(stack, consumer, light, overlay, colour);
		GlassBot.render(stack, consumer, light, overlay, colour);
		GlassMid.render(stack, consumer, light, overlay, colour);
		BPlate.render(stack, consumer, light, overlay, colour);
		TPlate.render(stack, consumer, light, overlay, colour);
		RFSupport.render(stack, consumer, light, overlay, colour);
		RBSupport.render(stack, consumer, light, overlay, colour);
		LFSupport.render(stack, consumer, light, overlay, colour);
		LBSupport.render(stack, consumer, light, overlay, colour);
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