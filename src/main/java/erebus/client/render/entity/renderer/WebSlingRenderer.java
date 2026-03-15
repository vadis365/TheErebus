package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.client.render.entity.renderer.state.ThrownBlockAsItemRenderState;
import erebus.entity.projectile.ThrownBlockAsItem;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public class WebSlingRenderer extends EntityRenderer<ThrownBlockAsItem, ThrownBlockAsItemRenderState> {

	private final ItemModelResolver itemModelResolver;

	public WebSlingRenderer(EntityRendererProvider.Context renderContext) {
		super(renderContext);
		this.itemModelResolver = renderContext.getItemModelResolver();
	}

	@Override
	public ThrownBlockAsItemRenderState createRenderState() {
		return new ThrownBlockAsItemRenderState();
	}

	@Override
	public void extractRenderState(ThrownBlockAsItem entity, ThrownBlockAsItemRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.blockState = entity.getBlockType();
		itemModelResolver.updateForTopItem(state.itemStackRenderState, new ItemStack(state.blockState.getBlock()), ItemDisplayContext.FIXED, entity.level(), null, 0);
	}

	@Override
	public void submit(ThrownBlockAsItemRenderState state, @NonNull PoseStack stack, @NonNull SubmitNodeCollector submit, @NonNull CameraRenderState camera) {
		stack.pushPose();
		stack.translate(0.5F, 0.625F, 0.5F);
		stack.mulPose(Axis.XP.rotationDegrees(180F));
		stack.mulPose(Axis.YN.rotationDegrees(90F));
		stack.scale(1F, 1F, 1F);
		state.itemStackRenderState.submit(stack, submit, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
		stack.popPose();
	}
}
