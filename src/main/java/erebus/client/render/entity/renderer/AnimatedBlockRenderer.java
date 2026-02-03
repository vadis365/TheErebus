package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.client.render.entity.model.AnimatedBlockModel;
import erebus.client.render.entity.renderer.state.AnimatedBlockRenderState;
import erebus.entity.AnimatedBlock;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public class AnimatedBlockRenderer extends MobRenderer<AnimatedBlock, AnimatedBlockRenderState, AnimatedBlockModel> {

	private final ItemModelResolver itemModelResolver;

    public AnimatedBlockRenderer(Context context) {
        super(context, new AnimatedBlockModel(context.bakeLayer(ModEntityRendering.ANIMATED_BLOCK)), 0.75F);
		itemModelResolver = context.getItemModelResolver();
    }

	@Override
	public void extractRenderState(AnimatedBlock entity, AnimatedBlockRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.blockState = entity.getBlockType();
		itemModelResolver.updateForTopItem(state.itemStackRenderState, new ItemStack(entity.getBlockType().getBlock()), ItemDisplayContext.FIXED, entity.level(), null, 0);
	}

	@Override
	public AnimatedBlockRenderState createRenderState() {
		return new AnimatedBlockRenderState();
	}

	@Override
	public void submit(AnimatedBlockRenderState state, @NonNull PoseStack pose, @NonNull SubmitNodeCollector node, @NonNull CameraRenderState camera) {
		pose.pushPose();
		pose.translate(0F, 0F, 0F);
		pose.pushPose();
		pose.mulPose(Axis.YN.rotationDegrees(state.bodyRot));
		pose.scale(4F, 4F, 4F);
		state.itemStackRenderState.submit(pose, node, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
		pose.popPose();
		pose.popPose();
	}

	@Override
	public @NonNull Identifier getTextureLocation(AnimatedBlockRenderState state) {
		return BuiltInRegistries.BLOCK.getKey(state.blockState.getBlock());
	}
}
