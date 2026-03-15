package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.client.render.entity.model.AnimatedBlockModel;
import erebus.client.render.entity.renderer.state.AnimatedBlockRenderState;
import erebus.entity.AnimatedBlock;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public class AnimatedBlockRenderer extends MobRenderer<AnimatedBlock, AnimatedBlockRenderState, AnimatedBlockModel> {

	private final ItemModelResolver itemModelResolver;

    public AnimatedBlockRenderer(Context context) {
        super(context, new AnimatedBlockModel(context.bakeLayer(ModEntityRendering.ANIMATED_BLOCK)), 0.75F);
		itemModelResolver = context.getItemModelResolver();
		model = new AnimatedBlockModel(context.bakeLayer(ModEntityRendering.ANIMATED_BLOCK));
    }

	@Override
	public void extractRenderState(AnimatedBlock entity, AnimatedBlockRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.blockState = entity.getBlockType();
		state.sprite = Minecraft.getInstance().getBlockRenderer().getBlockModel(state.blockState).particleMaterial().sprite();
		itemModelResolver.updateForTopItem(state.itemStackRenderState, new ItemStack(entity.getBlockType().getBlock()), ItemDisplayContext.FIXED, entity.level(), null, 0);
	}

	@Override
	public AnimatedBlockRenderState createRenderState() {
		return new AnimatedBlockRenderState();
	}

	@Override
	public void submit(AnimatedBlockRenderState state, @NonNull PoseStack pose, @NonNull SubmitNodeCollector node, @NonNull CameraRenderState camera) {
		pose.pushPose();
		pose.translate(0F, 0.8F, 0F);
		pose.mulPose(Axis.YN.rotationDegrees(state.bodyRot));
		pose.scale(2F, 2F, 2F);
		state.itemStackRenderState.submit(pose, node, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
		pose.popPose();

		RenderType rt = RenderTypes.entityCutout(getTextureLocation(state));
		model.setupAnim(state);
		pose.pushPose();
		pose.scale(-1.0F, -1.0F, 1.0F);
		pose.translate(0.0F, -1.501F, 0.0F);
		node.submitModelPart(model.root, pose, rt, state.lightCoords, OverlayTexture.NO_OVERLAY, state.sprite, false, false, -1, null, state.outlineColor);
		pose.popPose();
	}

	@Override
	public @NonNull Identifier getTextureLocation(AnimatedBlockRenderState state) {
		return Sheets.BLOCKS_MAPPER.sheet();
	}
}
