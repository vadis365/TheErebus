package erebus.client.render.block.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.block.entity.LiquifierBlockEntity;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class LiquifierModel extends Model {
	private final ModelPart tank;
	private final ModelPart lid_mid;
	private final ModelPart lid_bottom;
	private final ModelPart feed_pipe;

	public LiquifierModel(ModelPart root) {
		super(RenderType::entityTranslucent);
		this.tank = root.getChild("tank");
		this.lid_mid = root.getChild("lid_mid");
		this.lid_bottom = root.getChild("lid_bottom");
		this.feed_pipe = root.getChild("feed_pipe");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition tank = partdefinition.addOrReplaceChild("tank", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -3.0F, -8.0F, 16.0F, 11.0F, 16.0F, new CubeDeformation(0.001F))
				.texOffs(48, 0).addBox(-7.0F, -6.0F, -7.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(110, 0).addBox(-7.0F, -6.0F, 5.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(102, 0).addBox(5.0F, -6.0F, -7.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(118, 0).addBox(5.0F, -6.0F, 5.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 14).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

				PartDefinition lid_mid = partdefinition.addOrReplaceChild("lid_mid", CubeListBuilder.create().texOffs(48, 0).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

				PartDefinition lid_bottom = partdefinition.addOrReplaceChild("lid_bottom", CubeListBuilder.create().texOffs(84, 0).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

				PartDefinition feed_pipe = partdefinition.addOrReplaceChild("feed_pipe", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 16.0F, 0.0F));

				PartDefinition paddle_left = feed_pipe.addOrReplaceChild("paddle_left", CubeListBuilder.create().texOffs(0, 8).addBox(-6.5F, 0.0F, -0.5F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

				PartDefinition crossbar_left = feed_pipe.addOrReplaceChild("crossbar_left", CubeListBuilder.create().texOffs(48, 5).addBox(-6.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

				PartDefinition crossbar_right = feed_pipe.addOrReplaceChild("crossbar_right", CubeListBuilder.create().texOffs(108, 5).addBox(2.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

				PartDefinition paddle_right = feed_pipe.addOrReplaceChild("paddle_right", CubeListBuilder.create().texOffs(119, 7).addBox(3.5F, 0.0F, -0.5F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		tank.render(stack, consumer, light, overlay, colour);
	}

	public void renderBlades(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		feed_pipe.render(stack, consumer, light, overlay, colour);
	}
	
	public void renderLidAnimated(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour, LiquifierBlockEntity tile, float partialTick) {
		float size = 1F / 360F * tile.animationTicks;
		float prevSize = 1F / 360F * tile.prevAnimationTicks;
		float interAnimationSize = prevSize + (size - prevSize) * partialTick;
		stack.pushPose();
		if (interAnimationSize <= 0.5F)
			stack.scale(1F - interAnimationSize * 0.5F, 1F, 1F - interAnimationSize * 0.5F);
		if (interAnimationSize > 0.5F)
			stack.scale(0.5F + interAnimationSize * 0.5F, 1F, 0.5F + interAnimationSize * 0.5F);
		lid_mid.render(stack, consumer, light, overlay, colour);
		lid_bottom.render(stack, consumer, light, overlay, colour);
		stack.popPose();
	}

	public void renderLidStatic(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour) {
		lid_mid.render(stack, consumer, light, overlay, colour);
		lid_bottom.render(stack, consumer, light, overlay, colour);
	}
}